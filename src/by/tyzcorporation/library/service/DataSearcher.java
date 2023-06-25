package by.tyzcorporation.library.service;

import java.util.ArrayList;
import java.util.List;

public class DataSearcher<T> {
    private List<T> data;

    public DataSearcher(List<T> data) {
        this.data = data;
    }

    public List<T> search(SearchStrategy<T> strategy) {
        List<T> result = new ArrayList<>();
        for (T item : data) {
            if (strategy.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
