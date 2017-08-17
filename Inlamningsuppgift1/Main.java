package com.hotmail.a_asultan.Inlamningsuppgift1;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        AddressBookStart addressBookStart = new AddressBookStart();
        AddressBookLogging addressBookLogging = new AddressBookLogging();

         addressBookLogging.setupLogging();
         addressBookStart.runAddressBook();
    }
}


