 // Name: Ziwei Fang
 // USC NetID: ziweifan
 // CS 455 PA4
 // Fall 2018

 /**
    A ScoreTable to store the score for each particular letter.
    it can be used to calculate the score for a word using a static method
    getScore()
 */

public class ScoreTable {
    private int[] letterTable;
    private static final char[] POINT1 = new char[] {'a', 'e', 'i', 'o', 'u', 'l', 'n', 's', 't', 'r'};
    private static final char[] POINT2 = new char[] {'d', 'g'};
    private static final char[] POINT3 = new char[] {'b', 'c', 'm', 'p'};
    private static final char[] POINT4 = new char[] {'f', 'h', 'v', 'w', 'y'};
    private static final char[] POINT5 = new char[] {'k'};
    private static final char[] POINT8 = new char[] {'j', 'x'};
    private static final char[] POINT10 = new char[] {'q', 'z'};
    private static final int LETTERS = 26;
    private static final char LOWER_A = 'a';
    private static final int SCORE1 = 1;
    private static final int SCORE2 = 2;
    private static final int SCORE3 = 3;
    private static final int SCORE4 = 4;
    private static final int SCORE5 = 5;
    private static final int SCORE6 = 8;
    private static final int SCORE7 = 10;

    /**
     Create a ScoreTable according to the score for each particular letter
     */
    public ScoreTable() {
        letterTable = new int[LETTERS];
        for(char c1 : POINT1) {
            letterTable[c1 - LOWER_A] = SCORE1;
        }
        for(char c2 : POINT2) {
            letterTable[c2 - LOWER_A] = SCORE2;
        }
        for(char c3 : POINT3) {
            letterTable[c3 - LOWER_A] = SCORE3;
        }
        for(char c4 : POINT4) {
            letterTable[c4 - LOWER_A] = SCORE4;
        }
        for(char c5 : POINT5) {
            letterTable[c5 - LOWER_A] = SCORE5;
        }
        for(char c8 : POINT8) {
            letterTable[c8 - LOWER_A] = SCORE6;
        }
        for(char c10 : POINT10) {
            letterTable[c10 - LOWER_A] = SCORE7;
        }
    }

    /**
     Get the score for a English word
     PRE: word is not empty or null.
     @param word String to be processed to get the score
     */
    public  int getScore(String word) {
        word = word.toLowerCase();
        int res = 0;
        for(int i = 0; i < word.length(); i++) {
            res += getLetterScore(word.charAt(i));
        }
        return res;
    }

    /**
     Get the score for a particular letter
     PRE: Character c must be a valid English letter(a to z or A to Z).
     @param c letter to be processed to get the score
     */
    private  int getLetterScore(char c) {
        return letterTable[c - LOWER_A];
    }
}
