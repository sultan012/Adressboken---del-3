package com.hotmail.a_asultan.Inlamningsuppgift1;

import com.hotmail.a_asultan.LogginProject.Service;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class Client {
    private static final Logger log = Logger.getLogger(Service.class.getName());


    ArrayList<Person> centralCatalogsList = new ArrayList<>();

    public ArrayList<Person> getCentralCatalogsList() {
        return centralCatalogsList;
    }

    public void readCentralCatalogs() {

        Thread newThread2 = new Thread(() -> {
            try {

                Socket socket = new Socket("localhost", 61616);

                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream);


                try {
                    writer.println("getall");
                    writer.flush();
                    System.out.println("Loading for central catalogs ..........");
                    for (String line = reader.readLine(); !line.equals(""); line = reader.readLine()) {
                        String[] resevedContact = line.split(" ");
                        centralCatalogsList.add(new Person(resevedContact[0], resevedContact[1], resevedContact[2], resevedContact[3]));

                    }
                    System.out.println("Loading is successful...\n");
                    writer.println("exit");
                    writer.flush();

                         } catch (IOException e) {
                    System.out.println("We can't load central catalogs!");
                         log.severe("We can't load central catalogs!"+e.getMessage());
                         } finally {
                                        writer.close();
                                        reader.close();
                                        socket.close();
                                         }

            } catch (UnknownHostException e) {
                System.out.println("We can't load central catalogs!");
                log.severe("We can't load central catalogs!"+e.getMessage());
                } catch (ConnectException e) {
                System.out.println("No connect to server! so can't load central catalogs");
                log.severe( "No connect to server! so We can't load central catalogs" + e.getMessage());
                  } catch (IOException e) {
                System.out.println("We can't load central catalogs!");
                log.severe("We can't load central catalogs!"+e.getMessage());
                     }
        });

        newThread2.start();
    }
}