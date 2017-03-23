package com.test.service;

public interface SimpleRestService<T> {

    Iterable<? extends T> getAllItems();

    void removeItemById(long id);

    T createOrUpdateItem(T item);

}
