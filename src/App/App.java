package App;

import Service.SortingAlgo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        //Set up our sorted and unsorted directories

        List<File> allFiles = new ArrayList<>();
        File unsorted = new File("unsorted");
        File sorted = new File("sorted");


        SortingAlgo.listOfUnsortedFiles(unsorted,allFiles);
        SortingAlgo.createFolder(allFiles,sorted);

    }
}
