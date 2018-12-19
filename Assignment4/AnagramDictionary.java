// Name: Ziwei Fang
// USC NetID: ziweifan
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   private Map<String, ArrayList<String>> wordMap;


   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      File dictionary = new File(fileName);
      wordMap = new HashMap<String, ArrayList<String>>();
      try (Scanner fileScanner = new Scanner(dictionary)) {
         while(fileScanner.hasNext()) {
            String word = fileScanner.next();
            char[] c = word.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            if(!wordMap.containsKey(key)) {
               wordMap.put(key, new ArrayList<String>());
            }
            wordMap.get(key).add(word);
         }
      }
      catch(FileNotFoundException e) {
         throw new FileNotFoundException(fileName);
      }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
       char[] c = s.toCharArray();
       Arrays.sort(c);
       String str = new String(c);
       if(!wordMap.containsKey(str)) {
          return null;
       }
       return new ArrayList<String>(wordMap.get(str));
   }
}
