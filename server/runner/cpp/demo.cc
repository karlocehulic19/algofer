#include "split_array.h"
#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

using namespace std;

template <typename T> class ListElement {
public:
  T i;
  ListElement<T> *next;
};

template <typename T> class Solution {
private:
  ListElement<T> *head;

public:
  Solution() { this->head = nullptr; };

  void AddUnique(T val) {
    if (this->Contains(val)) {
      return;
    }

    ListElement<T> *dummy = new ListElement<T>();
    dummy->next = head;

    ListElement<T> *curr = dummy;

    while (curr->next != nullptr) {
      curr = curr->next;
    }

    ListElement<T> *newUnique = new ListElement<T>;
    newUnique->i = val;
    curr->next = newUnique;

    head = dummy->next;
  }

  void Remove(T val) {
    ListElement<T> *dummy = new ListElement<T>();
    dummy->next = head;

    ListElement<T> *curr = dummy;

    while (curr->next != nullptr) {
      ListElement<T> *nextPtr = curr->next;
      if (nextPtr->i == val) {
        curr->next = nextPtr->next;
        delete nextPtr;
        break;
      }

      curr = nextPtr;
    }

    head = dummy->next; // in case head is delted
  }

  bool Contains(T val) {
    ListElement<T> *curr = head;

    while (curr != nullptr) {
      if (curr->i == val) {
        return true;
      }
      curr = curr->next;
    }

    return false;
  }
};

// INSERT HERE
template <typename T> class MyList {
private:
  ListElement<T> *head;

public:
  MyList() { this->head = nullptr; };

  void AddUnique(T val) {
    if (this->Contains(val)) {
      return;
    }

    ListElement<T> *dummy = new ListElement<T>();
    dummy->next = head;

    ListElement<T> *curr = dummy;

    while (curr->next != nullptr) {
      curr = curr->next;
    }

    ListElement<T> *newUnique = new ListElement<T>;
    newUnique->i = val;
    curr->next = newUnique;

    head = dummy->next;
  }

  void Remove(T val) {
    ListElement<T> *dummy = new ListElement<T>();
    dummy->next = head;

    ListElement<T> *curr = dummy;

    while (curr->next != nullptr) {
      ListElement<T> *nextPtr = curr->next;
      if (nextPtr->i == val) {
        curr->next = nextPtr->next;
        delete nextPtr;
        break;
      }

      curr = nextPtr;
    }

    head = dummy->next; // in case head is delted
  }

  bool Contains(T val) {
    ListElement<T> *curr = head;

    while (curr != nullptr) {
      if (curr->i == val) {
        return true;
      }
      curr = curr->next;
    }

    return false;
  }
};

vector<string> runner(string testCase) {
  vector<string> actions = splitArray(testCase);
  vector<string> result;
  MyList<int> list;

  int i = 1; // Constructor can be avoided
  while (i < actions.size()) {
    string action = actions[i];

    i++;
    if (i == actions.size())
      throw invalid_argument("Test Case has invalid number of arguments");

    string value = actions[i];
    if (action == "AddUnique") {
      list.AddUnique(stoi(value));
      result.push_back("null");
    } else if (action == "Remove") {
      list.Remove(stoi(value));
      result.push_back("null");
    } else if (action == "Contains") {
      if (list.Contains(stoi(value))) {
        result.push_back("true");
      } else {
        result.push_back("false");
      }
    }
    i++;
  }

  return result;
}

int main(void) {
  vector<string> runnerRes =
      runner("[MyList,AddUnique,3,AddUnique,5,Contains,3,Remove,3,Contains,3]");
  cout << "Hello Runner";
}
