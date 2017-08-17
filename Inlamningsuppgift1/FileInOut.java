package com.hotmail.a_asultan.Inlamningsuppgift1;

import com.hotmail.a_asultan.LogginProject.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Sultan on 2016-11-18.
 */
public class FileInOut {
    private static final Logger log = Logger.getLogger(Service.class.getName());


    public synchronized void saveToFile(ArrayList<Person> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("addressBook.ser"))) {
            out.writeObject(list);
            log.info("Saving to file successful");
        } catch (IOException e) {
            log.severe(e.getLocalizedMessage());
            System.out.println("Something wrong occurred when reading data from file" + e.getMessage());
        }
    }


    public synchronized ArrayList<Person> loadFromFile() {
        ArrayList<Person> list = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("addressBook.ser"))) {

            list = (ArrayList<Person>) in.readObject();
            log.info("Loading from file successful");

        } catch (IOException e) {
            System.out.println("Something wrong occurred when reading data from file" + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.severe(e.getMessage());
            System.out.println("Fil not Found");
        }
        return list;
    }

}
