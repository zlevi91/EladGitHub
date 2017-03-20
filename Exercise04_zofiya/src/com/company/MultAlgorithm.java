package com.company;

import java.io.*;

/**
 * Created by hackeru on 3/20/2017.
 */
public class MultAlgorithm extends Algorithms implements Operations {
    public void crypt(File sourceFile, int key, boolean type) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);
            int buffer;
            if(type) {
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer * key);
                }
            }
            else{
                while ((buffer = inputStream.read()) != -1) {
                    for (int i = 1; i <=255 ; i++) {
                        int k = (byte) (i * key);
                        if (k == 1) {
                            key=i;
                        }
                    }
                    outputStream.write(buffer *key);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
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
                }
        }
    }


    @Override
    public void encrypted(File sourceFile, int key) {
        makeFile(sourceFile,true);
        crypt(sourceFile,key,true);

    }

    @Override
    public void decrypted(File sourceFile, int key) {
        makeFile(sourceFile,false);
        crypt(sourceFile,key,false);
    }
}
