package com.company;

import java.io.*;

import static com.company.FileHandler.CloseFile;

/**
 * Created by hackeru on 3/20/2017.
 */
public class Caesar extends Algorithms  {
    //File destinationFile;

    /*public void makeFile(File sourceFile, boolean type){
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

    }*/
    @Override
    public void crypt(File sourceFile, int key, boolean type) {
        started();
        makeFile(sourceFile,type);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);
            int buffer;
            if(type) {
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer + key);
                }
            }
            else{
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer - key);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ended();
            CloseFile(outputStream, inputStream);
            /*if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (inputStream != null)
                try{
                    inputStream.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }*/
        }
    }


    @Override
    public void encrypted(File sourceFile, int key) {
        //makeFile(sourceFile,true);
        crypt(sourceFile,key,true);

    }

    @Override
    public void decrypted(File sourceFile, int key) {
        //makeFile(sourceFile,false);
        crypt(sourceFile,key,false);
    }
}
