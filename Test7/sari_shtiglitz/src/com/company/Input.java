package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Input implements InputInterface {
    @Override
    public String input() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "exit";
    }
}