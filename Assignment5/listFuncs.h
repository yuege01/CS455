// Name:Ziwei Fang
// USC NetID:ziweifan
// CSCI 455 PA5
// Fall 2018


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in header files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// inserts val at the front of list
// PRE: list is a well-formed list
void insertFront(ListType &list, const std::string &theKey, int theValue);

// remove a node with its key equal to "theKey" in list
// the function returns false if the node does not exist in the list
// PRE: list is a well-formed list
bool listRemove(ListType &list, std::string target);

// append a new node at the end of the list
// POST: list' is same as list, but with item appended
void append (ListType &list, const std::string &theKey, int theValue);



// prints list elements, space separated, ending with '\n'
// prints emty list as "<empty>"
// PRE: list is a well-formed list
void printList(ListType list);

// get the number of nodes in the list
// PRE: list is a well-formed list
int size(ListType list);


// keep the following line at the end of the file
#endif
