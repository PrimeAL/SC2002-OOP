package hmsProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataSerialization {
    public void serialiseUser(User user) {
        try {
            FileOutputStream out = new FileOutputStream("hmsProject/src/hmsProject/database/" + user.gethID() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(user);
            oos.flush();
            oos.close();
            out.close();
            System.out.println("Serialization Successful");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public User deserialiseUser(String id) {
        try {
            FileInputStream in = new FileInputStream("hmsProject/src/hmsProject/database/" + id + ".dat");
            ObjectInputStream ois = new ObjectInputStream(in);
            User retrieved = (User) ois.readObject();
            ois.close();
            in.close();
            System.out.println("Deserialization Successful");
            return retrieved;
        } catch (ClassNotFoundException ex) {
            System.out.println("File not found");
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public void serialiseApptSys(AppointmentSystem apptSys) {
        try {
            FileOutputStream out = new FileOutputStream("hmsProject/src/hmsProject/database/System/apptSys.dat");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(apptSys);
            oos.flush();
            oos.close();
            out.close();
            System.out.println("Serialization Successful");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public AppointmentSystem deserialiseApptSys() {
        try {
            FileInputStream in = new FileInputStream("hmsProject/src/hmsProject/database/System/apptSys.dat");
            ObjectInputStream ois = new ObjectInputStream(in);
            AppointmentSystem retrieved = (AppointmentSystem) ois.readObject();
            ois.close();
            in.close();
            System.out.println("Deserialization Successful");
            return retrieved;
        } catch (ClassNotFoundException ex) {
            System.out.println("File not found");
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public void serialiseMedicine(Medicine medicine) {
        try {
            FileOutputStream out = new FileOutputStream("hmsProject/src/hmsProject/database/Medicine/" + medicine.getName() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(medicine);
            oos.flush();
            oos.close();
            out.close();
            System.out.println("Serialization Successful");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Medicine deserialiseMedicine(String medicine) {
        try {
            FileInputStream in = new FileInputStream("hmsProject/src/hmsProject/database/Medicine/" + medicine + ".dat");
            ObjectInputStream ois = new ObjectInputStream(in);
            Medicine retrieved = (Medicine) ois.readObject();
            ois.close();
            in.close();
            System.out.println("Deserialization Successful");
            return retrieved;
        } catch (ClassNotFoundException ex) {
            System.out.println("File not found");
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public void serialiseInventory(Inventory inventory) {
        try {
            FileOutputStream out = new FileOutputStream("hmsProject/src/hmsProject/database/System/inventory.dat");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(inventory);
            oos.flush();
            oos.close();
            out.close();
            System.out.println("Serialization Successful");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Inventory deserialiseInventory() {
        try {
            FileInputStream in = new FileInputStream("hmsProject/src/hmsProject/database/System/inventory.dat");
            ObjectInputStream ois = new ObjectInputStream(in);
            Inventory retrieved = (Inventory) ois.readObject();
            ois.close();
            in.close();
            System.out.println("Deserialization Successful");
            return retrieved;
        } catch (ClassNotFoundException ex) {
            System.out.println("File not found");
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
