package com.kaab.crud_app.controller;

import com.kaab.crud_app.entity.User;
import com.kaab.crud_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
    @GetMapping("/")
    public String userCreationForm(){
        return "userView/usercreation";
    }
    @PostMapping("/")
    public String createSingleUser(@ModelAttribute User user){
            User createdUser = userService.createSingleUser(user);
            return "redirect:/api/user/users";
    }

    @GetMapping("/users")

    public String findAllUser(Model model){
        List<User> userList = userService.findAllUser();
        model.addAttribute("users", userList);
        return "userView/userList"; // Return the name
    }

    @GetMapping("/user/{userID}")
    @ResponseBody
    public ResponseEntity<Optional<User>> findUserById(@PathVariable("userID") Integer userId){
        if(!userService.existUserById(userId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.findUserById(userId),HttpStatus.FOUND);
    }

    @GetMapping("/update")
    public String updateUserForm(){
        return "updateuserForm";
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


    @GetMapping("/delete")
    public String deleteUserForm(){
        return "userView/deleteuserform";
    }

    @PostMapping("/delete")
    public String deleteUserById(@RequestParam("id")Integer userId){
       userService.deleteUserById(userId);
       return "home";
    }


}
