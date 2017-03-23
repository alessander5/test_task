package com.test.controller;

import com.test.domain.User;
import com.test.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/user")
public class UserController<T extends User> {

    @Autowired
    private UserRestService userService;

    @RequestMapping(path="/", method = GET)
    public Iterable getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(path="/", method = DELETE)
    public ResponseEntity removeUserById(@RequestParam long id){
        userService.removeUserById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path="/", method = POST)
    public ResponseEntity createUser(@RequestBody T user){
        userService.createOrUpdateUser(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path="/", method = PUT)
    public ResponseEntity updateUser(@RequestBody T user){
        userService.createOrUpdateUser(user);
        return ResponseEntity.ok().build();
    }

}
