package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Menu implements AlgorithmOperation.Listener   {

    public static final String encryption = "1";
    public static final String decryption = "2";
    public static final String EXIT = "0";
    UserInterface userInterface;
    Factory factory ;
    public Menu(UserInterface userInterface) {
        this.userInterface = userInterface;

    }

    public Menu() {
        this.userInterface = new UserInterface() {
            @Override
            public void output(String s) {
                System.out.println(s);
            }

            @Override
            public String input() {
                return scan();
            }
        };
    }

    public void start() {
        String select ="";
        userInterface.output("please enter your choice:\n1: encryption  \n2: decryption   \n0: exit");
        select = userInterface.input();
        menu(select);
    }


    void menu(String mySelect) {
        switch (mySelect) {
            case encryption:
                if (Factory.flag)
                encrypted();
                else
                  decryption();
                break;
            case decryption:
                if (Factory.flag)
                    decryption();
                else
                encrypted();
                break;
            case EXIT:
                userInterface.output("bye bye");;
                return;
            default:
                userInterface.output("incorrect option");
                start();
        }
    }

    private void decryption() {
        factory = new Factory();
        Algorithm algorithm;
        File file1 =getFile();
        algorithm = chooseTypeOfCipher();
        int myKey = insertKey();
        algorithm.decryption(file1,myKey);
        if(Factory.flag)
        userInterface.output("your decryption succeed ");
        else
            userInterface.output("your encryption succeed ");
        start();
    }

    private void encrypted() {
        Algorithm algorithm =chooseTypeOfCipher();
        factory = new Factory();
        File file = getFile();
        int key = RandomKey.randomKey();
        userInterface.output("Your key is: " + key);
        algorithm.encryption(file,key);
        if(Factory.flag)
        userInterface.output("your encryption succeed ");
        else
            userInterface.output("your decryption succeed ");
        start();
    }

    int insertKey(){
        int key = 0;
        try {
            userInterface.output("please enter your key");
            String keyTemp = scan();
            key = Integer.parseInt(keyTemp);
        }
        catch (NumberFormatException e){
            userInterface.output("you not enter num ");
            insertKey();
        }
        return key;
    }

    Algorithm chooseTypeOfCipher(){
        factory = new Factory();
        userInterface.output("choose Type Of Cipher\n1: caesar Algorithm \n2: Xor Algorithm\n3: ReversAlgorithm");
        Algorithm algorithm  = factory.chooseAlgorithm(userInterface.input());
        return algorithm;
    }



    public  File getFile() {
        userInterface.output("enter your path");
        File myFile = new File(userInterface.input());
       // checkFile(myFile);
        while (!checkFile(myFile)) {
        userInterface.output("enter your path");
        myFile = new File(userInterface.input());
            //checkFile(myFile);
        }
        return myFile;
    }
     boolean checkFile(File filePath){
         if (filePath.exists()) {
            if (filePath.isFile())
                return true;
            else userInterface.output("you not enter file");
        }
        else userInterface.output("your path wrong");
        return false;

    }

   String scan() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    void returnAgain(int type){
        factory =new Factory();
        if(type==1)
        userInterface.output("your select not correct enter again\n1: caesar Algorithm\n2: Xor Algorithm \n3: ReversAlgorithm");
        else
            userInterface.output("you need to choose type of cipher\n1: caesar Algorithm\n2: Xor Algorithm \n3: ReversAlgorithm ");
        factory.chooseAlgorithm(userInterface.input());
    }


    @Override
    public void StartDetect() {
        userInterface.output("the cipher is start \nthe time now is"+System.nanoTime() );
    }

    @Override
    public void EndDetect() {
        userInterface.output("the cipher is end \nthe time now is" + System.nanoTime());
    }





}