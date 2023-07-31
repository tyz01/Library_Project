package by.tyzcorporation.library.service;

public class LibraryStatistics {
    private static int bookCount = 0;
    private static int magazineCount = 0;
    private static int albumCount = 0;
    public static void incrementAlbumCount() {
        albumCount++;
    }
    public static void incrementBookCount() {
        bookCount++;
    }

    public static void incrementMagazineCount() {
        magazineCount++;
    }

    public static int getBookCount() {
        return bookCount;
    }

    public static int getMagazineCount() {
        return magazineCount;
    }
}