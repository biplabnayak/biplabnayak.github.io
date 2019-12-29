package com.biplab.user.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bipnayak on 6/16/2017.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/get")
    public Map getUser() {
        LOGGER.info("Get User Requested ...");
        Map map = new HashMap();
        map.put("name", "Biplab");
        map.put("email", "biplab.nayak@capgemini.ocom");
        return map;
    }
}
