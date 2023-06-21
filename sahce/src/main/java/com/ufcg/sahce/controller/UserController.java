package com.ufcg.sahce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufcg.sahce.domain.user.MyUser;
import com.ufcg.sahce.domain.user.MyUserLogin;
import com.ufcg.sahce.domain.user.UserReset;
import com.ufcg.sahce.service.MyUserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired(required = true)
    private MyUserService myUserService;

    @GetMapping("/user/{emailUser}")
    public MyUser getUser(@PathVariable String emailUser) {
        return myUserService.findByEmail(emailUser);
    }

    @GetMapping("/users")
    public List<MyUser> getAllUsers() {
        return myUserService.findAll();

    }

    @PostMapping("/user")
    public MyUser creatUser(@RequestBody MyUser user) {
        System.out.println(user);
        return myUserService.save(user);

    }

    @PostMapping("/user/login")
    public MyUserLogin login(@RequestBody MyUser user) {
        System.out.println(user);
        return myUserService.login(user);

    }

    @PutMapping("/user/active/{emailUser}")
    public MyUser activeUser(@PathVariable String emailUser) {
        return myUserService.activeUser(emailUser);

    }

    @PutMapping("/user/inactive/{emailUser}")
    public MyUser inactiveUser(@PathVariable String emailUser) {
        return myUserService.inactiveUser(emailUser);
    }

    @PostMapping("/user/request-reset/{emailUser}")
    public ResponseEntity<HttpStatus> requestReset(@PathVariable String emailUser) {
        try {
            myUserService.requestResetPassword(emailUser);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/user/reset-password")
    public MyUser resetPassword(@RequestBody UserReset userReset) {
        return myUserService.resetPassword(userReset);
    }
}
