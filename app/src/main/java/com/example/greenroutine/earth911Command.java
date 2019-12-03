package com.example.greenroutine;

import org.json.JSONArray;

import java.util.ArrayList;

public class earth911Command implements Command {
    String itemID;
    String ZIPCode;
    earth911 command;

    public earth911Command(ArrayList<ArrayList<String>> parameters) {
        command = new earth911(parameters);
    }

    @Override
    public void execute() {
        JSONArray holder = command.request();
        command.parseSearchLoc(holder);
    }

    public ArrayList<Object> commandRequest() {
        return command.getResult();
    }
}

