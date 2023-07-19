package by.tyzcorporation.library.model.repository;

import by.tyzcorporation.library.model.entity.*;

import java.io.Serializable;

public class ContainerPublication implements Serializable {
        public static Library repositoryLibrary() {
        Library publications = new Library();
        publications.addPublication(new Book(1, "t1", 2, "Pushkin", "genre1", false, 0));
        publications.addPublication(new Magazine(1,"t2", 3, "comix", "fun", false, 0));
       // publications.addPublication(new ConcreteAlbum("t3", 4, false, 0));
        //publications.addPublication(new AlbumDecorator(new AlbumSticker(new ConcreteAlbum("t4", 5, false, 0))));

        publications.addPublication(new Book(2, "t4", 2, "tyz1", "genre1", false, 0));
        publications.addPublication(new Magazine(2, "t5", 3, "comix", "fun", false, 0));
       // publications.addPublication(new ConcreteAlbum("t6", 4, false, 0));
     //   publications.addPublication(new AlbumDecorator(new AlbumSticker(new ConcreteAlbum("t7", 5, false, 0))));

        return publications;
    }
}
