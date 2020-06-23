package com.lambdaschool.secretrecipes.controllers;


import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/recipes", produces = {"application/json"})
    public ResponseEntity<?> listAllRecipes() {
        List<Recipe> recipeList = recipeService.findAll();
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/recipe/{recipeId}",
            produces = {"application/json"})
    public ResponseEntity<?> getListingById(
            @PathVariable
                    Long recipeId)
    {
        Recipe r = recipeService.findRecipeById(recipeId);
        return new ResponseEntity<>(r,
                HttpStatus.OK);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable long id) {
        recipeService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping(value = "/recipe/{recipeId}")
    public ResponseEntity<?> updateRecipe(
            @PathVariable
                    long recipeId, String title, String source, String ingredients, String instructions, String category, User user)
    {
        recipeService.update(
                recipeId, title, source, ingredients,instructions, category, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/recipe/{recipeId}")
    public ResponseEntity<?> addNewRecipe(
            @PathVariable
                    long recipeId, String title, String source, String ingredients, String instructions, String category, User user)
            throws
            URISyntaxException
    {
        Recipe newRecipe = recipeService.save(
        title, source, ingredients, instructions, category, user);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("/recipes/recipe/{recipeId}")
                .buildAndExpand(newRecipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }


    @GetMapping(value = "/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> findRecipeByUserName(
            @PathVariable
                    String userName)
    {
        List<Recipe> theRecipes = recipeService.findByUserName(userName);
        return new ResponseEntity<>(theRecipes,
                HttpStatus.OK);
    }



}
