package com.company;

import java.io.File;

public interface Operations {
    public void encrypted(File file, int key);
    public void decrypted(File file, int key);

}
