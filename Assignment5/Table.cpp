// Name:Ziwei Fang
// USC NetID:ziweifan
// CSCI 455 PA5
// Fall 2018

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   hashTable = new ListType[HASH_SIZE];
   hashSize = HASH_SIZE;
   entries = 0;
}


Table::Table(unsigned int hSize) {
   hashTable = new ListType[hSize];
   hashSize = hSize;
   entries = 0;
}

/** this method looks for a key in the hashtable, if
* the key is found, it returns null, otherwise a pointer
* to the value associated with the key
* @param key a reference to string
* @return a pointer to an int value
**/
int * Table::lookup(const string &key) {
   int index = hashCode(key);
   ListType p = hashTable[index];
   while(p != NULL) {
      if(p->key == key) {
         return &(p->value);
      }
      p = p->next;
   }
   return NULL;
}
/** this method looks for a key in the hashtable, if
* the key is found, it remove the pair containing the key
* and return true, otherwise false
* @param key a reference to string
* @return a boolean value indicating whether removing is successful
**/
bool Table::remove(const string &key) {
   int index = hashCode(key);
   bool success = listRemove(hashTable[index], key);
   if(success) {
      entries--;
   }
   return success;
}
/** this method inser a key-value pair into the hashtable, if
* the key is found, it does nothing to table and returns false
* otherwise insert the entry into right bucket and returns true
* @param key a reference to string
* @param value a value associated with key
* @return a boolean value indicating whether inserting is successful
**/
bool Table::insert(const string &key, int value) {
   int *num = lookup(key);
   if(num == NULL) {
      insertFront(hashTable[hashCode(key)], key, value);
      entries++;
      return true;
   }
   return false;
}

/** 
   this method returns number of entries in the table
**/
int Table::numEntries() const {
   return entries;      
}

/** this method print all key-value pairs in the table
**/
void Table::printAll() const {
   for(int i = 0; i < hashSize; i++) {
      ListType p = hashTable[i];
      while(p != NULL) {
         cout << p->key << " " << p->value << endl;
         p = p->next;
      }
   }
}
/** 
   this method print some statistcs about hashtable
**/
void Table::hashStats(ostream &out) const {
   int nonEmptyBuckets = 0;
   int longestChain = 0;
   for(int i = 0; i < hashSize; i++) {
      ListType p = hashTable[i];
      if(p != NULL) {
         nonEmptyBuckets++;
         longestChain = max(longestChain, size(p));
      }
   }
   out << "number of buckets: " << hashSize << endl;
   out << "number of entries: " << numEntries() << endl;
   out << "number of non-empty buckets: " << nonEmptyBuckets << endl;
   out << "longest chain: " << longestChain << endl;
   out << "cmd> " << endl;
}



// add definitions for your private methods here
