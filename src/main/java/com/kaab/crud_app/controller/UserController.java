package com.kaab.crud_app.controller;

import com.kaab.crud_app.entity.User;
import com.kaab.crud_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String list(@ModelAttribute("user") User user){
        return "createuser";
    }



    @GetMapping("/createuser")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createuser";
    }
    @PostMapping("/createuser")
    public String createSingleUser(@ModelAttribute("user") User user) {
        userService.createSingleUser(user);
        return "redirect:/api/user/users";
    }

    @GetMapping("/users")
    public String findAllUser(Model model){
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "list";
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


    @GetMapping("/deleteuser")
    public String showDeleteUserByIdForm(Model model) {
        return "deleteuser";
    }
    @PostMapping("/deleteuser")
    public String deleteUserById(@ModelAttribute("userId") Integer userId, RedirectAttributes redirectAttributes) {
        System.out.println(userId);

        // Check if the user exists
        if (!userService.existUserById(userId)) {
            System.out.println("user is not found by this id");
            // Redirect to an error page or display an error message
            redirectAttributes.addFlashAttribute("message", "user is not exist");

            return "redirect:/api/user/deleteUserResult";
        }
        userService.deleteUserById(userId);
        redirectAttributes.addFlashAttribute("message", "user deleteted successfully");
        return "redirect:/api/user/deleteUserResult";
//        return "redirect:/api/user/users";
    }


    @GetMapping("/deleteUserResult")
    public String showDeleteUserResult() {
        return "deleteUserResult";
    }

}
