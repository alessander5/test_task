package com.test.controller;

import com.test.domain.User;
import com.test.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractRestController<User, UserService> {

}
