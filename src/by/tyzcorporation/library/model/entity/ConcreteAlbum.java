package by.tyzcorporation.library.model.entity;

public class ConcreteAlbum extends Publication implements Album {
    public ConcreteAlbum(String title, int pageCount) {
        super(title, pageCount);
    }

    @Override
    public void draw() {
        System.out.println("draw");
    }

    @Override
    public void setPage(int page) {

    }
}
