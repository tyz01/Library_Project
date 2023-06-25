package by.tyzcorporation.library.service;

@FunctionalInterface
public interface SearchStrategy<T> {
    boolean test(T item);
}
