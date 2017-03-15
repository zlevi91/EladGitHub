package com.company;

import java.io.File;

/**
 * Created by hackeru on 3/15/2017.
 */
public class Menu {
    Output myOutput = new ScreenOutput();
    Input myInput = new ScreenInput();
    FileOperations myFileOperations = new FileOperations();
    EncryptFile encryptFile = new EncryptFile();
    DecryptFile decryptFile = new DecryptFile();
    String filePathString;
    File filePath;


    public void printMenu() {

        myOutput.output("please choose:\n 1. If you want to encrypt the file Press 1\n 2. If you want to decrypt the file Press 2\n 0. exit\n your choice:   ");
        chooseOption();

    }

    public void chooseOption() {
        String input;
        while (!(input = myInput.input()).equals("0")) {
            myOutput.output(myChoice(input));
            printMenu();
        }
    }

    public String myChoice(String input) {
        if (input.length() != 0) {
            switch (input) {
                case "1":
                    getPathFromUser();
                    return encryptFile.crypt(filePath);
                case "2":
                    getPathFromUser();
                    return decryptFile.crypt(filePath);
                case "0":
                    return "exit" ;

                default:
                    return "invalid option. try again.";
            }
        }
        return "Something went wrong. Please try again";
    }

    // פונקציה שקולטת את הנתיב מהמשתמש
    public void getPathFromUser() {
        myOutput.output("Enter a file path:");
        filePathString = myInput.input();
        while (!(myFileOperations.checkpath(filePathString))) {
            filePathString = myInput.input();
        }
        filePath = new File(filePathString);
    }

}
