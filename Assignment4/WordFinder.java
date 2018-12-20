// Name: Ziwei Fang
// USC NetID: ziweifan
// CS 455 PA4
// Fall 2018
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 this class contains the main() method and it is used to start the whole program.
 to start the program, user need to provide a file containing words in dictionary
 and another file to construct Rack.
 Finally, it will print all words constructed from Rack that are in the dictionary
 sorted by their scores.
 this class may throw an exception when the file of the dictionary does not exist.
 */
public class WordFinder {
    public static void main(String[] args) {
        String fileName = "";
        if(args.length == 0) {
           fileName = "sowpods.txt";
        }
        else {
           fileName  = args[0];
        }
        if(fileName == null || fileName.isEmpty()) {
           
        }
        try {
            AnagramDictionary dict = new AnagramDictionary(fileName);
            Scanner in = new Scanner(System.in);
            System.out.println("Type . to quit.");
            while(true) {
                System.out.print("Rack? ");
                String target = in.next();
                if(target.equals(".")) {
                    break;
                }
                Rack rack = new Rack(target);
                rack.printList(dict);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Error: File does not exist!");
            System.out.println("No such File: " + e.getMessage());
        }
    }
}
