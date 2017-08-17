package com.hotmail.a_asultan.Inlamningsuppgift1;


import com.hotmail.a_asultan.LogginProject.Service;

import java.util.logging.Logger;


public class InputCommando {
    private static final Logger log = Logger.getLogger(Service.class.getName());

    public String testCommando(String[] inputData) {
        String commando = inputData[0];

        switch (commando) {
            case "add":
                if (inputData.length == 4) {
                    log.fine("add commando and add a new contact");
                    return commando;
                } else
                    return "Input parameters with (add) commando should be (three)";

            case "list":
                if (inputData.length == 1) {
                    log.fine("list commando and show contact list");
                    return commando;
                } else
                    return "No Input parameters should be with (list) commando";

            case "search":
                if (inputData.length == 2) {
                    log.fine("search commando and search about" + inputData[1]);
                    return commando;
                } else return "Input parameters with (search) commando should be (one)";

            case "help":
                if (inputData.length == 1) {
                    log.fine("help commando and show help list ");
                    return commando;
                } else return "No Input parameters should be with (help) commando";

            case "delete":
                if (inputData.length == 2) {
                    log.fine("delete commando ");
                    return commando;
                } else
                    return "Input parameters with (delete) commando should be (one)";

            case "quit":
                if (inputData.length == 1) {
                    log.fine("quit commando and exit program");
                    return commando;
                } else return "No Input parameters should be with (quit) commando";

            default: {
                return commando;
            }
        }
    }
}

