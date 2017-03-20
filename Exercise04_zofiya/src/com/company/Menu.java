package com.company;

import java.io.File;
import java.util.Random;

/**
 * Created by hackeru on 3/15/2017.
 */
public class Menu {
    Output myOutput; //= new ScreenOutput();
    Input myInput ;//= new ScreenInput();
    FileOperations myFileOperations = new FileOperations();
    String filePathString;
    File filePath;
    Operations crypt;

    public Menu(Output myOutput, Input myInput) {
        this.myOutput = myOutput;
        this.myInput = myInput;
    }


    public void printMenu() {

        myOutput.output("please choose:\n 1. If you want to encrypt the file Press 1\n 2. If you want to decrypt the file Press 2\n 0. exit\n your choice:");
        chooseOption();

    }

    public void chooseOption() {
        String input = "";
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
                    encryptFile(filePath);
                    return "encrypt";
                case "2":
                    getPathFromUser();
                    decryptFile(filePath);
                    return "decrypt";
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

    public void selectionAlgorithm(){

    }

    public int keyLottery(){
        Random random = new Random(System.currentTimeMillis());
        int key = random.nextInt(255);
        return key;
    }

    public void encryptFile (File filePath){
        int key=keyLottery();
        myOutput.output("The key is: " + key);
        Operations encrypt= new Caesar();
        encrypt.encrypted(filePath, key);
    }

    public void decryptFile (File filePath){
        myOutput.output("Enter the encryption key in the file");
        String k= myInput.input();
        Operations decrypt= new Caesar();
        int key= Integer.valueOf(k);
        decrypt.decrypted(filePath,key);
    }


}
