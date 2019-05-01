package util;

public final class Bigram<T, V> {
  
	T left;
	V right;
	
	public Bigram(T l, V r) {
		left = l;
		right = r;
	}
	
	public T getLeft() {
		return left;
	}
	public V getRight() {
		return right;
	}
}
