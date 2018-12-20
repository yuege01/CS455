// Name:Ziwei Fang
// USC NetID:ziweifan
// CSCI 455 PA5
// Fall 2018

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

/**
*  this method read a string from input
*/
string getString();

/**
*   this method get an int value from intput
*/
int getScore();

/**
*   this method print a list of help information to users
*/
void printHelper();

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }
   grades->hashStats(cout);
   
   string c1;
   string name;
   int score = 0;
   bool keepgoing = true;
   do {
         c1 = getString();
         if(c1 == "insert") {
            name = getString();
            score = getScore();
            if(!grades->insert(name, score)) {
               cout << "this name was already present" << endl;
               cout << "insert failed!" << endl;
            }
         }
         else if(c1 == "change"){
            name = getString();
            score = getScore();
            int *newScore = grades->lookup(name);
            if(newScore != NULL) {
               *newScore = score;
            }
            else {
               cout << "this name does not exist" << endl;
               cout << "change score failed!" << endl;
            }
         }
         else if(c1 == "lookup") {
            name = getString();
            if(grades->lookup(name) == NULL) {
               cout << "this name does not exist" << endl;
            }
            else {
               cout << name << "'s" << " score is " << *(grades->lookup(name)) << endl;
            }
         }
         else if(c1 == "remove") {
            name = getString();
            if(!grades->remove(name)) {
               cout << "this name does not exist" << endl;
               cout << "remove failed!" << endl;
            }
         }
         else if(c1 == "print") {
            grades->printAll();
         }
         else if(c1 == "size") {
            cout << grades->numEntries() << " entries in the table." << endl;
         }
         else if(c1 == "stats") {
            grades->hashStats(cout);
            continue;
         }
         else if(c1 == "help") {
            printHelper();
         }
         else if(c1 == "quit"){
            break;
         }
         else {
            cout << "ERROR: invalid command." << endl;
         }
         cout << "cmd> " << endl;
   } while (keepgoing);

   return 0;
}

string getString() {
   string s = "";
   cin >> s;
   return s;
}

int getScore() {
   int i = 0;
   cin >> i;
   return i;
}


void printHelper() {
   cout << "insert name score     --- Insert this name and score in the grade table." << endl;
   cout << "change name newscore  --- Change the score for name." << endl;
   cout << "lookup name           --- Lookup the name, and print out his or her score." << endl;
   cout << "remove name           --- Remove this student." << endl;
   cout << "print                 --- Prints out all names and scores." << endl;
   cout << "size                  --- Prints out the number of entries." << endl;
   cout << "stats                 --- Prints out statistics about the hash table at this point." << endl;
   cout << "help                  --- Prints out a brief command summary." << endl;
   cout << "quit                  --- Exits the program." << endl;
}
