package com.viathink.flowable.user.service.impl;

import com.viathink.flowable.user.service.UserService;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IdentityService identityService;

    @Override
    @Transactional
    public List<User> findAllUser() {
        List<User> userList = identityService.createUserQuery().list();
        for (User u: userList) {
            System.out.println("name: " + u.getFirstName() + u.getLastName());
        }
        return userList;
    }
}
