//package com.lambdaschool.secretrecipes.controllers;
//
//import com.lambdaschool.secretrecipes.models.ErrorDetail;
//import com.lambdaschool.secretrecipes.models.User;
//import com.lambdaschool.secretrecipes.models.UserMinimum;
//import com.lambdaschool.secretrecipes.repository.RoleRepository;
//import com.lambdaschool.secretrecipes.repository.UserRepository;
//import com.lambdaschool.secretrecipes.services.UserService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.persistence.EntityExistsException;
//import javax.persistence.EntityNotFoundException;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//@RestController
//public class Oauthendpoints {
//
////    @Autowired
////    RoleRepository rolerepos;
////
////    @Autowired
////    UserRepository userrepos;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @ApiOperation(value = "adds a user given in the request body",
//            response = Void.class)
//    @ApiResponses(value = {@ApiResponse(code = 200,
//            message = "User Found",
//            response = User.class), @ApiResponse(code = 404,
//            message = "User Not Found",
//            response = ErrorDetail.class)})
//    @PostMapping(value = "/user",
//            consumes = {"application/json"})
//    public ResponseEntity<?> addNewUser(
//            @Valid
//            @RequestBody
//                    User newuser)
//            throws
//            URISyntaxException
//    {
//        newuser.setUserid(0);
//        newuser = userService.save(newuser);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{userid}")
//                .buildAndExpand(newuser.getUserid())
//                .toUri();
//        responseHeaders.setLocation(newUserURI);
//
//        return new ResponseEntity<>(null,
//                responseHeaders,
//                HttpStatus.CREATED);
//    }
//}
