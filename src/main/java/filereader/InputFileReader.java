package filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads MovieTests file based on regex.
 *
 * @author ypark29
 */
public class InputFileReader {

  private List<ArrayList<String>> data;
  private final String filePath;
  private boolean read;

  /**
   * The constructor.
   *
   * @param filePath The path of MovieTests file.
   */
  public InputFileReader(String filePath) {
    this.filePath = filePath;
    this.data = new ArrayList<ArrayList<String>>();
  }

  /**
   * This is the primary method that reads the file based on regex pattern.
   * @param regex The regex pattern to split lines by
   * @return A list of lists, where the inner lists correspond to MovieTests line.
   */
  public List<List<String>> read(String regex) {
    String line = "";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      while ((line = br.readLine()) != null) {
        String[] singleLine = line.split(regex);
        this.data.add(new ArrayList<String>(Arrays.asList(singleLine)));
      }
      br.close();
      this.read = true;
    } catch (FileNotFoundException e) {
      this.read = false;
      System.out.println("ERROR: File not found.");
    } catch (IOException e) {
      this.read = false;
      System.out.println("ERROR: File has no more tokens to read.");
    }
    return new ArrayList<>(this.data);
  }

  /**
   * @return True if the file was read properly.
   */
  public boolean isRead() {
    return this.read;
  }
}
