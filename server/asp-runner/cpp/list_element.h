#pragma once

template <typename T> class ListElement {
public:
  T i;
  ListElement<T> *next;
};
