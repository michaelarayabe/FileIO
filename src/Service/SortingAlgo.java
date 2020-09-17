package Service;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortingAlgo {

    //listing files from unsorted folder
    public static void listOfUnsortedFiles(File unsorted, List<File> files){

        File[] unsortedArray = unsorted.listFiles();
        if(unsortedArray != null){
            for(File file : unsortedArray){
                if(file.isFile()){
                    files.add(file);
                } else if (file.isDirectory()){
                    listOfUnsortedFiles(file.getAbsoluteFile(),files);
                }
            }
        }
    }

    /*
    Todos:

    grab the extension of file, and add to collection then create a directory based on it
     */

    // Extension of file

    public static String getExtension (String file){

        String extension = null;
        if(file != null){
            extension = file.substring(file.lastIndexOf('.') + 1);
        }
        return extension;
    }



    public static void createFolder(List<File> files, File sorted){

        Set<String> newFolder = new HashSet<>();

        for(File file : files){
            newFolder.add(getExtension(file.getName()));
        }

        // Create directories
        for(String str : newFolder){
            if(!new File(sorted + "/" + str).exists()){
                new File(sorted + "/" + str).mkdir();
            }
        }

    }









}
