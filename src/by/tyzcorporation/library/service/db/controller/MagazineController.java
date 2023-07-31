package by.tyzcorporation.library.service.db.controller;

import by.tyzcorporation.library.model.entity.Magazine;
import by.tyzcorporation.library.service.annotation.TextController;
import by.tyzcorporation.library.service.db.bisneslogic.MagazineService;

import java.sql.Connection;

@TextController
public class MagazineController {
    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    public void createBook(Magazine magazine, Connection connection) {
        magazineService.createMagazine(magazine, connection);
    }

    public void getAll() {
        magazineService.getAll();
    }

    public void removeMagazine(int idMagazine) {
        magazineService.removeMagazine(idMagazine);
    }

    public Magazine getById(Integer idMagazine) {
        return magazineService.getMagazineById(idMagazine);
    }
}
