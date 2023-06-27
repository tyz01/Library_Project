package by.tyzcorporation.library.service.utility.search;

@FunctionalInterface
public interface SearchStrategy<T> {
    boolean test(T item);
}
