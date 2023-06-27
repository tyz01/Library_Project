package by.tyzcorporation.library.model.entity;

public class ConcreteLibrary extends Library implements Comparable<ConcreteLibrary>{
    private String nameLibrary;
    private String city;

    @Override
    public int compareTo(ConcreteLibrary o) {
        return this.nameLibrary.compareTo(o.nameLibrary);
    }

}
