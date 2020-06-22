package com.lambdaschool.secretrecipes.controllers;


import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/recipes", produces = {"application/json"})
    public ResponseEntity<?> getAllRecipes() {
        List<Recipe> recipeList = recipeService.findAll();
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/recipes", consumes = {"application/json"})
    public ResponseEntity<?> postRecipe(@Valid @RequestBody Recipe recipe) {
        recipe.setrecipeid(0);
        recipe = recipeService.save(recipe);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI recipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeid}").buildAndExpand(recipe.getrecipeid()).toUri();
        responseHeaders.setLocation(recipeURI);

        return new ResponseEntity<>(recipe.getTitle() + " has been created", responseHeaders, HttpStatus.CREATED);
    }

//    @PostMapping(value = "/user/{userid}/recipe/{recipe}")
//    public ResponseEntity<?> addNewUserEmail(
//            @PathVariable
//                    long userid,
//            @PathVariable
//                    long recipeid)
//    {
//        User dataUser = new User();
//        dataUser.setUserid(userid);
//
//        Recipe dataRecipe = new Recipe();
//        dataRecipe.setRecipeid(recipeid);
//
//        recipeService.save(dataUser, dataRecipe);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable long id) {
        recipeService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
