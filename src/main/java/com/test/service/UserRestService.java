package com.test.service;

import com.test.domain.User;

public interface UserRestService<T extends User>{

    Iterable<T> getAllUsers();

    void removeUserById(long id);

    T createOrUpdateUser(T user);

}
