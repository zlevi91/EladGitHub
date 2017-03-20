package com.company;

import java.io.File;

/**
 * Created by hackeru on 3/20/2017.
 */
public abstract class Algorithms {
    File destinationFile;

    public void makeFile(File sourceFile, boolean type){
        String fileName = sourceFile.getAbsolutePath();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        if (type) {
            destinationFile = new File(new String(fileName + "_encrypted.txt"));
        }
        else{
            destinationFile = new File(new String(fileName + "_decrypted.txt"));
        }
    }


}
