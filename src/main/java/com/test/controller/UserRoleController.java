package com.test.controller;

import com.test.domain.UserRole;
import com.test.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class UserRoleController extends AbstractRestController<UserRole, UserRoleService> {

}
