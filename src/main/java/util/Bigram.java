package util;

public final class Bigram<T> {
	T left;
	T right;
	public Bigram(T l, T r) {
		left = l;
		right = r;
	}
	public T getLeft() {
		return left;
	}
	public T getRight() {
		return right;
	}
}
