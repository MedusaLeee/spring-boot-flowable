package com.viathink.flowable.service;


import org.flowable.idm.api.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
}
