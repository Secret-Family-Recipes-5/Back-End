package com.lambdaschool.secretrecipes.controllers;

import com.lambdaschool.secretrecipes.models.ErrorDetail;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * The entry point for clients to access user data
 */

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> getAllUsers(){
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @ApiOperation(value = "returns the currently authenticated user",
            response = User.class)
    @GetMapping(value = "/currentuser",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = {"application/json"})
    public ResponseEntity<?> postUser(@Valid @RequestBody User newUser){
        newUser.setUserid(0);
        newUser = userService.save(newUser);

        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{id}", produces = {"application/json"})
    public ResponseEntity<?> getUserById(@PathVariable long id){
        User rtnUser = userService.findUserById(id);
        return new ResponseEntity<>(rtnUser, HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullUser(@PathVariable long id, @Valid @RequestBody User user){
        user.setUserid(id);
        userService.save(user);
        return new ResponseEntity<>(user.getUsername() + " was updated", HttpStatus.OK);
    }

    @PatchMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> updatePartialUser(@Valid @RequestBody User user){
        userService.update(user);
        return new ResponseEntity<>(user.getUsername() + " has been updated", HttpStatus.OK);
    }

    @GetMapping(value = "/contains/{query}", produces = {"application/json"})
    public ResponseEntity<?> getByNameContaining(@PathVariable String query){
        List<User> rtnUserList = userService.findByNameContaining(query);
        return new ResponseEntity<>(rtnUserList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteUserById(@PathVariable long id){
        userService.delete(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }
}