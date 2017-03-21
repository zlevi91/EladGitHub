package com.company;

import java.io.File;
import java.util.Random;

/**
 * Created by hackeru on 3/15/2017.
 */
public class Menu implements Listener {
    public static final String ENCRYPT = "1";
    public static final String DECRYPT = "2";
    public static final String CAESAR = "1";
    public static final String XOR = "2";
    public static final String MULT = "3";
    public static final String REVERS = "4";

    Output myOutput; //= new ScreenOutput();
    Input myInput ;//= new ScreenInput();
    FileOperations myFileOperations = new FileOperations();
    String filePathString;
    File filePath;
    Operations crypt;
    String input;

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
                case ENCRYPT:
                    selectionAlgorithm();
                    getPathFromUser();
                    encryptFile(filePath);
                    return "encrypt";
                case DECRYPT:
                    selectionAlgorithm();
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
        myOutput.output("Select the algorithm to use:\n 1. Caesar 1\n 2. XorAlgorithm Press 2\n 3. MultAlgorithm Press 3\n 4. ReverseAlgorithm press 4\n your choice:");
        input=myInput.input();
        if (input.length()!=0){
            switch (input) {
                case CAESAR:
                    crypt = new Caesar();
                    return;
                case XOR:
                    crypt = new XorAlgorithm();
                    return;
                case MULT:
                    crypt = new MultAlgorithm();
                    return;
                case REVERS:
                    crypt= new ReverseAlgorithm(myOutput,myInput);
                    return;

                default:
                    myOutput.output("invalid option. try again.");
                    selectionAlgorithm();
            }
        }

    }

    public int keyLottery(){
        Random random = new Random(System.currentTimeMillis());
        int key = random.nextInt(255);
        return key;
    }

    public void encryptFile (File filePath){
        int key=keyLottery();
        if(Integer.parseInt(input)==3&&Integer.parseInt(input)==0){
            if(key%2==0)
                key=key+1;
        }
        myOutput.output("The key is: " + key);
        crypt.encrypted(filePath, key);
    }

    public void decryptFile (File filePath){
        myOutput.output("Enter the encryption key in the file");
        String k= myInput.input();
        int key= Integer.valueOf(k);
        crypt.decrypted(filePath,key);
    }


    @Override
    public void StartDetect() {
        myOutput.output("the action is start \nthe time now is"+System.nanoTime() );
    }

    @Override
    public void EndDetect() {
        myOutput.output("the action is end \nthe time now is" + System.nanoTime());
    }
}
