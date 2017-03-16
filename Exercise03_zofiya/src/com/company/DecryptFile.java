package com.company;

import java.io.*;

/**
 * Created by hackeru on 2/28/2017.
 */
public class DecryptFile implements Operations {
    Input input;
    Output output;

    public DecryptFile(Output output, Input input) {
        this.input = input;
        this.output = output;
    }
  public DecryptFile() {
       input=new ScreenInput();
       output=new ScreenOutput();
    }

    public int enterKey(){
        output.output("Enter the encryption key in the file");
        String k= input.input();
        return Integer.valueOf(k);//פונקצית המרה מסטרינג לאינט

    }

    @Override
    public String crypt(File sourceFile) {
        int key= enterKey();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        String fileName = sourceFile.getAbsolutePath();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        File encrypted = new File(new String(fileName + "_decrypted.txt"));
        try {
            outputStream = new FileOutputStream(encrypted);
            inputStream = new FileInputStream(sourceFile);
            int oneByte;
            while ((oneByte = inputStream.read()) != -1) {
                outputStream.write(oneByte - key);
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
        }
        return "decrypt";
    }


}
