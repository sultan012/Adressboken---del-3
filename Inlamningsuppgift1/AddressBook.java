package com.hotmail.a_asultan.Inlamningsuppgift1;


import java.security.Provider;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddressBook {


    private static final Logger log = Logger.getLogger(Provider.Service.class.getName());

    ArrayList<Person> personList = new ArrayList<>();
    AddressBookManager addressBookManager = new AddressBookManager();

    ArrayList<Person> getPersonList() {
        return personList;
    }


    public void addContact(String firstName, String lastName, String email) {
        personList.add(new Person(UUID.randomUUID().toString(), firstName, lastName, email));

        System.out.println("a new contact added to Address Book");
        log.fine("add commando and a new person info is added to the Address Book");
    }


    public void search(ArrayList<Person> localList, ArrayList<Person> centralCatalogsList, String searchWord) {
        log.fine("search commando and search for contact");
        ArrayList<Person> searchResult = new ArrayList<>();
        ArrayList<Person> allContacts = new ArrayList<>();
        allContacts.addAll(localList);
        allContacts.addAll(centralCatalogsList);

        boolean found = false;
        for (int i = 0; i < allContacts.size(); i++) {
            try {
                if (searchWord.equalsIgnoreCase(allContacts.get(i).getFirstName().substring(0, searchWord.length())) ||
                        searchWord.equalsIgnoreCase(allContacts.get(i).getLastName().substring(0, searchWord.length()))) {
                    searchResult.add(allContacts.get(i));
                    found = true;
                    log.fine("contact found after search for " + searchWord);
                }
            } catch (StringIndexOutOfBoundsException e) {
                log.log(Level.SEVERE, "Something wrong occurred", e);
            }
        }

        if (found) {
            addressBookManager.showPersonList(searchResult);
        } else {
            System.out.printf("No Contact Found have (%s) in first name \n", searchWord);
            log.fine("contact not found after search for " + searchWord);
        }
    }


    public void deleteContact(ArrayList<Person> localList, String userId) {
        log.fine("delete commando!");
        boolean found = false;
        for (int i = 0; i < localList.size(); i++) {
            if (userId.equalsIgnoreCase(String.valueOf(localList.get(i).getId()))) {
                localList.remove(i);
                found = true;
                log.fine("delete commando and a contact is deleted");
            }
        }
        System.out.println(found ? "The Contact Deleted !" : "The Contact is Not Found! or the contact from Central Catalogs!");
    }


    public void ListContatctsInfo(ArrayList<Person> personList, ArrayList<Person> centralCatalogsList) {
        ArrayList<Person> allContacts = new ArrayList<>();
        allContacts.addAll(personList);
        allContacts.addAll(centralCatalogsList);
        addressBookManager.sortAddressBook(allContacts);
        addressBookManager.showPersonList(allContacts);
        log.fine("list commando and show list for contacts info");
    }


    public void printHelp() {
        log.fine("help commando and show help list");
        System.out.println(
                "(add)     To add a new contact \n" +
                        "(delete)  To delete a contact\n" +
                        "(list)    TO show list for all contacts\n" +
                        "(search)  To search  a contact\n");
    }
}
