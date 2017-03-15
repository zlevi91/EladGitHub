package com.company;

import java.io.File;

/**
 * Created by hackeru on 2/28/2017.
 */
public class FileOperations {

    public boolean checkpath(String filePathString){

       if (!new File(filePathString).exists()||!(new File(filePathString).isFile())){

            return false;
        }

        return true;
    }


}