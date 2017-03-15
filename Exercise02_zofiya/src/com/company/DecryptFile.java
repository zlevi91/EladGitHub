package com.company;

import java.io.File;

/**
 * Created by hackeru on 2/28/2017.
 */
public class DecryptFile implements Operations {


    public DecryptFile() {
    }

    @Override
    public String crypt(File file) {
        return "Decrypt";
    }

    /*public static void decipher(){
        if(pathTesting())
            System.out.println("Right path");
        else
            throw new InvalidParameterException("Improper lane");

    }*/
}
