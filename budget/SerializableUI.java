package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SerializableUI {


    public ArrayList<Object> load() {
        ArrayList<Object> deserialize = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("purchases.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            deserialize = (ArrayList<Object>) ois.readObject();
            System.out.println("Purchases were loaded!");
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return deserialize;
    }


    public void save(String filename, double balance, LinkedHashMap<String, LinkedHashMap<String, Double>> purchasesList) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(balance);
        list.add(purchasesList);
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(list);
            System.out.println("Purchases were saved!");
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
