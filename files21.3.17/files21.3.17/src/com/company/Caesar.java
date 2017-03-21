package com.company;

import java.io.*;

/**
 * Created by hackeru on 3/16/2017.
 */
public class Caesar extends AlgorithmOperation{

    @Override
    public int ActionEncryption(int oneByte, int key) {
        return oneByte+key;
    }

    @Override
    public int ActionDecryption(int oneByte, int key) {
        return oneByte-key;
    }
}
