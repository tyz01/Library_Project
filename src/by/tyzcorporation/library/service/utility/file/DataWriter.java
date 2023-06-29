package by.tyzcorporation.library.service.utility.file;

import java.io.*;
import java.util.List;

public class DataWriter<T> implements Serializable{
        public void write(List<T> data, String fileName) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
                T[] dataArray = (T[]) data.toArray();
                outputStream.writeObject(dataArray);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void write(T[] data, String fileName) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
                outputStream.writeObject(data);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void write(T data, String fileName) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
                outputStream.writeObject(data);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
