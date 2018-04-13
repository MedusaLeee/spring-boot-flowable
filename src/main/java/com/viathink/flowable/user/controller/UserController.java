package com.viathink.flowable.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viathink.flowable.user.dto.UserForm;
import com.viathink.flowable.user.service.UserService;
import org.flowable.idm.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
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
    public JSONObject addUser(@Valid @RequestBody UserForm userForm) {
        System.out.println("jsonObject = [" + userForm.toString() + "]");
        return JSONObject.parseObject(JSON.toJSONString(userForm));
    }
    @GetMapping(path = "/users/{id}")
    public JSONObject getOneUser(@PathVariable(value="id") String id) {
        logger.debug("id: " + id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", "tom");
        return jsonObject;
    }
}
