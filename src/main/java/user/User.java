package user;

import java.util.Objects;

public class User {
  private String password;
  private String login;

  public User(String login, String password) {
    this.password = password;
    this.login = login;
  }

  public Boolean verifyPassword(String attempt) {
    if (attempt.equals(this.password)) {
      return true;
    }
    return false;
  }

  public String getLogin() {
    return this.login;
  }

  public String getPassword() {
    return this.password;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.login);
  }

  @Override
  public boolean equals(Object o) {
    User u2 = (User) o;
    if (o == this) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    return this.hashCode() == u2.hashCode();
  }

  @Override
  public String toString() {
    return "{username: " + this.login + ", password: " + this.password + "}";
  }
}
