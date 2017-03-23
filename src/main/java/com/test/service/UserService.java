package com.test.service;

import com.test.domain.User;
import com.test.domain.UserRoleEnum;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements SimpleRestService<User>, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllItems() {
        return userRepository.findAll();
    }

    @Override
    public void removeItemById(long id) {
        userRepository.delete(id);
    }

    @Override
    public User createOrUpdateItem(User user) {
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User findedUser = userRepository.findByUserName(login);
        if (findedUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(findedUser.getUserName(), findedUser.getPassWord(), findedUser.isActive(), true, true, true, buildGrantedAuthority(findedUser.getRoles()));
    }

    private Collection<GrantedAuthority> buildGrantedAuthority(Set<UserRoleEnum> permitions){
        return permitions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
