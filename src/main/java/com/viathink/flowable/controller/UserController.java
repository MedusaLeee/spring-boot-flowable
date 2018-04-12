package com.viathink.flowable.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viathink.flowable.dto.UserForm;
import com.viathink.flowable.service.UserService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public JSONObject addUser(@Validated @RequestBody UserForm userForm, BindingResult bindingResult) {
        System.out.println("bindingResult = [" + bindingResult.getAllErrors() + "]");
        System.out.println("jsonObject = [" + userForm.toString() + "]");
        return JSONObject.parseObject(JSON.toJSONString(userForm));
    }
}
