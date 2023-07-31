package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.service.annotation.Entity;

@Entity
public interface PublicationAbstract {
     Integer idPublication(Integer id);

     String title(String title);
     int pageCount(int pageCount);
     boolean borrow(boolean isBorrow);
     int countBorrowPublication(int countBorrow);
}
