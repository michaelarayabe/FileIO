package Service;

import java.io.File;
import java.util.List;

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








}
