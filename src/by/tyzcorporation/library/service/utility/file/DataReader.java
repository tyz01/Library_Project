package by.tyzcorporation.library.service.utility.file;

import by.tyzcorporation.library.model.entity.Publication;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataReader<T> implements Serializable{
    public List<T> readArray(String fileName) {
        List<T> result = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            T[] publications = (T[]) inputStream.readObject();
            result = Arrays.asList(publications);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<T> readList(String fileName) {
        List<T> result = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            result = (List<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public T read(String fileName) {
        T result = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            result = (T) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
//    public Publication read(String fileName) {
//        Publication result = null;
//        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
//            result = (Publication) inputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
