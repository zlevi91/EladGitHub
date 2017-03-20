package com.company;

public class Main {

    public static void main(String[] args) {

        /*byte b=127;
        b=(byte)((int)b+200);
        System.out.println(b);
        b=(byte)((int)b-200);
        System.out.println(b);*/

        System.out.println("jhfiu");
        byte b=0;
        int key=6;
        byte original=10;
        byte en=(byte)(original*key);
        for (int i = 1; i <=255 ; i++) {
            int k= i*key;
            if((byte)k==1) {
                System.out.println(k);
                System.out.println(i);
                System.out.println((byte)i*en);
            }
        }






        /*Menu myMenu = new Menu(new ScreenOutput(), new ScreenInput());
        myMenu.printMenu();*/
    }
}
