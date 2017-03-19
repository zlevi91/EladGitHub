package com.company;

import java.io.*;
import java.util.Random;

/**
 * Created by hackeru on 2/28/2017.
 */
public class EncryptFile implements Operations {
    Input input;
    Output output;
    int key;

    public int getKey() {
        return key;
    }



    public EncryptFile(Output output, Input input) {
        this.input = input;
        this.output = output;
    }


    public void keyLottery(){
        Random random = new Random(System.currentTimeMillis());
        key = random.nextInt(255);
        output.output("The key is: " + key);
    }

    @Override
    public String crypt(File sourceFile) {
        keyLottery();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        String fileName = sourceFile.getAbsolutePath();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        File encrypted = new File(new String(fileName + "_encrypted.txt"));
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(encrypted);
            int buffer;
            while ((buffer = inputStream.read()) != -1) {
                outputStream.write(buffer + key);
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

        return "encrypt";
    }


}
