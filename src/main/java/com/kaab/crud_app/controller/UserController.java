package com.kaab.crud_app.controller;

import com.kaab.crud_app.entity.User;
import com.kaab.crud_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/home")
    @ResponseBody
    public String home(){
        System.out.println("this is home page");
        return "home";
    }
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<User> createSingleUser(@RequestBody User user){
            User createdUser = userService.createSingleUser(user);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<User>> findAllUser(){
        return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
    }

    @GetMapping("/user/{userID}")
    @ResponseBody
    public ResponseEntity<Optional<User>> findUserById(@PathVariable("userID") Integer userId){
        if(!userService.existUserById(userId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.findUserById(userId),HttpStatus.FOUND);
    }


    @PutMapping("/update/{userId}")
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId,@RequestBody User updatedUser){
        if (!userService.existUserById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedUser.setId(userId);
        User savedUser = userService.updateUserById(userId,updatedUser);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<Optional<User>> deleteUserById(@PathVariable("userId") Integer userId){
        if (!userService.existUserById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<User> deletedUser = userService.deleteUserById(userId);
        return new ResponseEntity<>(deletedUser,HttpStatus.OK);
    }


}
