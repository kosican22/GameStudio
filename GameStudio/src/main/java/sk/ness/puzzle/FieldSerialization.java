package sk.ness.puzzle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FieldSerialization {

    private static final String RESTORE_FILE = "restore_file.bin";

	public static Field newOrRestore()  {
        Field field = null;
        try {
            FileInputStream in = new FileInputStream(RESTORE_FILE);
            ObjectInputStream s = new ObjectInputStream(in);
            field = (Field) s.readObject();
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Subor "+RESTORE_FILE+" nenajdeny vytvaram hru nanovo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (field != null){
            return field;
        }else {
            return new Field(4, 4);
        }
    }

    public static void save(Field field) {
        try {
            FileOutputStream out = new FileOutputStream(RESTORE_FILE);
            ObjectOutputStream s = new ObjectOutputStream(out);
            s.writeObject(field);
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
