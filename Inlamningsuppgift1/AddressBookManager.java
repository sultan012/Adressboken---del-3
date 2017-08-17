package com.hotmail.a_asultan.Inlamningsuppgift1;

import com.hotmail.a_asultan.LogginProject.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

public class AddressBookManager {

    FileInOut fileInOut = new FileInOut();


    private static final Logger log = Logger.getLogger(Service.class.getName());


    public void showPersonList(ArrayList<Person> personInfoList) {
        for (int i = 0; i < personInfoList.size(); i++) {
            System.out.println("\n----------------------------------------" +
                    "\nId: " + personInfoList.get(i).getId() +
                    "\nFirst Name: " + personInfoList.get(i).getFirstName() +
                    "\nLast Name: " + personInfoList.get(i).getLastName() +
                    "\nE.mail address : " + personInfoList.get(i).getEmail() +
                    "\n----------------------------------------");
        }
        if(personInfoList.size()==0){
            System.out.println("Contacts list is empty\n");
        }else {
            System.out.printf("\nIt's '%s' contacts in this contacts list.\n", personInfoList.size());
        }
    }

    public void sortAddressBook(ArrayList<Person> personInfoList) {
        Collections.sort(personInfoList, new Comparator() {
            public int compare(Object person1, Object person2) {
                if (person1 instanceof Person && person2 instanceof Person) {
                    return ((Person) person1).getFirstName().toLowerCase().compareTo(
                            ((Person) person2).getFirstName().toLowerCase());
                } else return 0;
            }

        });
    }

    public void autoSaveData(ArrayList<Person> personInfoList) {
        Thread newThread1 = new Thread(() -> {
            log.info("Great a New Thread");
            while (true) {
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fileInOut.saveToFile(personInfoList);
                log.info("Auto Saved Data to File");
            }
        });

        newThread1.start();
    }

    public void exitProgram(ArrayList<Person> PersonList) throws IOException {
        fileInOut.saveToFile(PersonList);
        System.out.println("\n****** ( Good Bye! ) ******");
        log.info("Address Book Program Exited");
        System.exit(0);
    }
}
