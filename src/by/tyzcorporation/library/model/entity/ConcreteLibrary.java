package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public class ConcreteLibrary extends Library implements Comparable<ConcreteLibrary>, Serializable{
    private String nameLibrary;
    private String city;

    public ConcreteLibrary(String nameLibrary, String city) {
        this.nameLibrary = nameLibrary;
        this.city = city;
    }

    public ConcreteLibrary() {
    }

    public String getNameLibrary() {
        return nameLibrary;
    }

    public void setNameLibrary(String nameLibrary) {
        if (nameLibrary != null && !nameLibrary.isEmpty()) {
            this.nameLibrary = nameLibrary;
        } else {
            throw new IllegalArgumentException("incorrect name library");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null && !city.isEmpty()) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("incorrect name city");
        }
    }

    @Override
    public int compareTo(ConcreteLibrary o) {
        return this.nameLibrary.compareTo(o.nameLibrary);
    }
}
