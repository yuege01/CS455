// Name:Ziwei Fang
// USC NetID: ziweifan
// CS 455 PA4
// Fall 2018

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;


/**
   A Rack of Scrabble tiles
   This class is responsible to construct a Rack and find all dictionary words
   that can be constructed by characters on rack
   And it also has the method to print all words.
 */

public class Rack {
   
   Map<Character, Integer> rack;
   Map<String, Integer> wordList;
   String rackWord;

   public Rack(String str) {
      rack = new HashMap<>();
      for(int i = 0; i < str.length(); i++) {
         rack.put(str.charAt(i), rack.getOrDefault(str.charAt(i), 0) + 1);
      }
      wordList = new HashMap<>();
      rackWord = str;
   }
   /**
    Finds all words in all subsets of rack that exist in the target dictionary/
    @param dict the dictonary to search for valid words
    */
   private void getList(AnagramDictionary dict) {
      ScoreTable table = new ScoreTable();
      String unique = new String();
      int[] mult = new int[rack.size()];
      int index = 0;
      for(char c : rack.keySet()) {
         unique = unique + c;
         mult[index++] = rack.get(c);
      }
      ArrayList<String> stringArrayList = allSubsets(unique, mult, 0);
      for(String subset : stringArrayList) {
         if (dict.getAnagramsOf(subset) != null) {
            for (String target : dict.getAnagramsOf(subset)) {
               wordList.put(target, table.getScore(target));
            }
         }
      }
   }

   /**
    Print words that can be got from Rack in the order of their scores
    @param dict the dictonary to search for valid words
    */
   public void printList(AnagramDictionary dict) {
      getList(dict);
      ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>();
      for(Map.Entry<String, Integer> e : wordList.entrySet()) {
         list.add(e);
      }
      Collections.sort(list, new entryComparator());
      System.out.println("We can make " + wordList.size() + " words from " + "\"" + rackWord + "\"");
      if(list.size() > 0) {
         System.out.println("All of the words with their scores (sorted by score):");
         for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getValue() + ": " + e.getKey());
         }
      }
   }

   /**
    A entryComparator class implementing Compartor that is used to sort words in the order by their scores
    And if two words have the score, they are sorted by words.
    */
   private class entryComparator implements Comparator<Map.Entry<String, Integer>> {
      public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
         int num1 = e1.getValue();
         int num2 = e2.getValue();
         String s1 = e1.getKey();
         String s2 = e2.getKey();
         if(num1 < num2) {
            return 1;
         }
         else if(num1 > num2) {
            return -1;
         }
         else {
            return s1.compareTo(s2);
         }
      }
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
}
