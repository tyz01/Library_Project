package by.tyzcorporation.library.model.entity;

public interface PublicationAbstract {
     Integer idPublication(Integer id);

     String title(String title);
     int pageCount(int pageCount);
     boolean borrow(boolean isBorrow);
     int countBorrowPublication(int countBorrow);
}
