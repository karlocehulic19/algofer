#ifndef MY_LIST_H
#define MY_LIST_H

#include "list_element.h"

template <typename T> class MyList {
private:
  ListElement<T> *head;

public:
  MyList() { this->head = nullptr; };

  void AddUnique(T val) {
    // if (this->Contains(val)) {
    //   return;
    // }

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

#endif
