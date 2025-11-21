#ifndef LIST_ELEMENT_H
#define LIST_ELEMENT_H

template <typename T> class ListElement {
public:
  T i;
  ListElement<T> *next;
};

#endif
