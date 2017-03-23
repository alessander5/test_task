package com.test.service;

import com.test.domain.UserRole;
import com.test.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements SimpleRestService<UserRole>{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Iterable<UserRole> getAllItems() {
        return userRoleRepository.findAll();
    }

    @Override
    public void removeItemById(long id) {
        userRoleRepository.delete(id);
    }

    @Override
    public UserRole createOrUpdateItem(UserRole item) {
        return userRoleRepository.save(item);
    }
}
