package by.tyzcorporation.library.model.repository;

import by.tyzcorporation.library.model.entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContainerPublication implements Serializable {
        public static Library repositoryLibrary() {
        Library publications = new ConcreteLibrary();
        publications.addPublication(new ConcreteBook("t1", 2, "tyz1", "genre1", false, 0));
        publications.addPublication(new ConcreteMagazine("t2", 3, "comix", "fun", false, 0));
        publications.addPublication(new ConcreteAlbum("t3", 4, false, 0));
        publications.addPublication(new AlbumDecorator(new AlbumSticker(new ConcreteAlbum("t4", 5, false, 0))));

        publications.addPublication(new ConcreteBook("t4", 2, "tyz1", "genre1", false, 0));
        publications.addPublication(new ConcreteMagazine("t5", 3, "comix", "fun", false, 0));
        publications.addPublication(new ConcreteAlbum("t6", 4, false, 0));
        publications.addPublication(new AlbumDecorator(new AlbumSticker(new ConcreteAlbum("t7", 5, false, 0))));

        return publications;
    }
//    public static List<Publication> repositoryLibrary() {
//        Library publications = new ConcreteLibrary();
//        publications.addPublication(new ConcreteBook("t1", 2, "tyz1", "genre1", false, 0));
//        publications.addPublication(new ConcreteMagazine("t2", 3, "comix", "fun", false, 0));
//        publications.addPublication(new ConcreteAlbum("t3", 4, false, 0));
//        publications.addPublication(new AlbumDecorator(new AlbumSticker(new ConcreteAlbum("t4", 5, false, 0))));
//
//        publications.addPublication(new ConcreteBook("t4", 2, "tyz1", "genre1", false, 0));
//        publications.addPublication(new ConcreteMagazine("t5", 3, "comix", "fun", false, 0));
//        publications.addPublication(new ConcreteAlbum("t6", 4, false, 0));
//        publications.addPublication(new AlbumDecorator(new AlbumSticker(new ConcreteAlbum("t7", 5, false, 0))));
//
//        List<Publication> publicationList = new ArrayList<>();
//        for (Publication publication : publications) {
//            publicationList.add(publication);
//        }
//
//        return publicationList;
//    }
}
