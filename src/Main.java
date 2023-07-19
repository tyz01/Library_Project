import by.tyzcorporation.library.controller.AppController;
import by.tyzcorporation.library.service.db.PoolController;
import by.tyzcorporation.library.service.db.connector.PosgresConnector;

public class Main {
    public static void main(String[] args) {
        PosgresConnector.connect();
        PoolController.managePool();
        AppController appController = new AppController();
        appController.start();
    }
}