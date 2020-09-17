package Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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



    public static void createFolder(List<File> files, File sorted) throws IOException {

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

        // check if files doesn't exists, if so copy it, if not Files have been copied before!
        for (File file : files) {
            if (getExtension(file.getName()) != null) {
                Path source = file.toPath();
                Path destination = sorted.toPath().resolve(getExtension(file.getName())).resolve(file.getName());
                if (!destination.toFile().exists()) {
                    Files.copy(source, destination);
                } else{
                    System.out.println("Files already copied!");
                    continue;
                }
            }
        }

    }

    // creating summary file for sorted
    public static void createSummary(File files) throws IOException {

        //create a list with the sorted files showing the directory
        List<File> sortedFiles = new ArrayList<>();
        SortingAlgo.listOfUnsortedFiles(files, sortedFiles);
        Set<String> extension = new HashSet<>();
        String pathSum = "/summary.txt";

        for (File file : sortedFiles) {
            extension.add(getExtension(file.getName()));
        }

        //formatting for summary
        List<String> summary = new ArrayList<>();
        summary.add(String.format("%-50s%s%13s%24s%13s%10s", "name","|" ,"readable", "|", "writeable", "|"));

        for (String ext : extension) {
            summary.add("\n" );
            summary.add(ext + ":");
            summary.add("--------");
            for (File file : sortedFiles) {
                if (ext.equals(file.getParentFile().getName())) {
                    summary.add(String.format("%-50s%s%13s%25s%10s%13s", file.getName(),"|" ,file.canRead() ? "/" : "x","|",
                            file.canWrite() ? "/" : "x", "|"));
                }
            }
        }

        //Creating summary, check if it exists first!
        if (!Paths.get(files + pathSum).toFile().exists()) {
            Files.createFile(Paths.get(files + pathSum));
        }
        Files.write(Paths.get(files + pathSum), summary);
    }











}
