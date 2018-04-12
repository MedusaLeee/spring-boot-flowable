package com.viathink.flowable.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viathink.flowable.service.UserService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/users")
    public JSONArray getAllUser() {
        List<User> userList = userService.findAllUser();
        JSONArray jsonArray = userList.stream().map(u -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", u.getId());
            jsonObject.put("name", u.getFirstName() + u.getLastName());
            jsonObject.put("email", u.getEmail());
            return  jsonObject;
        }).collect(Collectors.toCollection(JSONArray:: new));
        // JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(userService.findAllUser()));
        return jsonArray;
    }
}
