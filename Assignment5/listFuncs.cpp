// Name:Ziwei Fang
// USC NetID:ziweifan
// CSCI 455 PA5
// Fall 2018


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below
void insertFront(ListType &list, const string &theKey, int theValue) {
   list = new Node(theKey, theValue, list);
}

bool listRemove(ListType &list, string target) {
   if(list == NULL) {
      return false;
   }
   if(list->key == target) {
      ListType temp = list;
      list = list->next;
      delete temp;
      return true;
   }
   ListType node = list;
   while(node->next != NULL && node->next->key != target) {
      node = node->next;
   }
   if(node->next == NULL) {
      return false;
   }
   else {
      ListType temp = node->next;
      node->next = node->next->next;
      delete temp;
   }
   return true;
}

void append (ListType &list, const string &theKey, int theValue) {
   ListType p = list;

   if (p == NULL) {
      insertFront(list, theKey, theValue);
      return;
   }

   while (p->next != NULL) {
      p = p->next;
   } 

   p->next = new Node(theKey, theValue);
}

void printList(ListType list) {
   if (list == NULL) {
      cout << "<empty>";
   }

   ListType p = list;
   while (p != NULL) {
      cout << p->key << " " << p->value << "&&";
      p = p->next;
   }
   cout << endl;
}

int size(ListType list) {
   int size = 0;
   while(list != NULL) {
      size++;
      list = list->next;
   }
   return size;
}
