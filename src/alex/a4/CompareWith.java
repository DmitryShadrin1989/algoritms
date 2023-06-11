package alex.a4;

public interface CompareWith<T> {
    // is a < b?
    boolean lessThan(T a, T b);
}
