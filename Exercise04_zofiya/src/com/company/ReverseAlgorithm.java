package com.company;

import java.io.File;

/**
 * Created by hackeru on 3/20/2017.
 */
public class ReverseAlgorithm extends Algorithms implements Operations {
    Operations o;
    Output output;
    Input input;

    public ReverseAlgorithm(Output output, Input input) {
        output.output("Select the algorithm to use:\n 1. Caesar 1\n 2. XorAlgorithm Press 2\n 3. MultAlgorithm Press 3\n your choice:");
        String input1=input.input();
        if (input1.length()!=0) {
            switch (input1) {
                case "1":
                    o = new Caesar();
                    return;
                case "2":
                    o = new XorAlgorithm();
                    return;
                case "3":
                    o = new MultAlgorithm();
                    return;
            }
        }
    }
    @Override
    public void crypt(File sourceFile, int key, boolean type){
        o.crypt(sourceFile,key,type);
    }

    @Override
    public void encrypted(File sourceFile, int key) {
        //makeFile(sourceFile,false);
        crypt(sourceFile,key,false);
    }

    @Override
    public void decrypted(File sourceFile, int key) {
        //makeFile(sourceFile,true);
        crypt(sourceFile,key,true);

    }
}
