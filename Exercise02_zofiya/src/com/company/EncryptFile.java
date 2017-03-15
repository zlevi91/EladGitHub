package com.company;

import java.io.File;

/**
 * Created by hackeru on 2/28/2017.
 */
public class EncryptFile implements Operations {

    public EncryptFile() {
    }

    @Override
    public String crypt(File file) {
        return "encrypt";
    }


}
