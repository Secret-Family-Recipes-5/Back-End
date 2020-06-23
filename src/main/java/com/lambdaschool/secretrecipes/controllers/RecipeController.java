package com.lambdaschool.secretrecipes.controllers;


import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    @PostMapping(value = "/recipe", consumes = {"application/json"})
    public ResponseEntity<?> postNewRecipe(@Valid @RequestBody String title){
        recipeService.addNewRecipe(title);
        return new ResponseEntity<>("created item: " + title, HttpStatus.CREATED);
    }

    @GetMapping(value = "/recipes", produces = {"application/json"})
    public ResponseEntity<?> getAllRecipes(){
        List<Recipe> rtnLst = recipeService.getAllRecipes();
        return new ResponseEntity<>(rtnLst, HttpStatus.OK);
    }

    @GetMapping(value = "/recipes/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getRecipesByUserId(@PathVariable long userid){
        List<Recipe> recipes = recipeService.getItemsByUserId(userid);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PutMapping(value = "/recipe/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe newRecipe){
        recipeService.update(newRecipe, id);
        return new ResponseEntity<>("Item has been updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{id}", produces = {"application/json"})
    public ResponseEntity<?> deleteRecipeById(@PathVariable long id){
        recipeService.delete(id);
        return new ResponseEntity<>("Item has been deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/recipe/{id}", produces = {"application/json"})
    public ResponseEntity<?> findRecipeById(@PathVariable long id){
        Recipe recipe = recipeService.findItemById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


//    @PostMapping(value = "/recipe/{recipeId}")
//    public ResponseEntity<?> addNewRecipe(
//            @PathVariable
//                    long recipeId, String title, String source, String ingredients, String instructions, String category, User user)
//            throws
//            URISyntaxException
//    {
//        Recipe newRecipe = recipeService.save(
//        title, source, ingredients, instructions, category, user);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
//                .path("/recipes/recipe/{recipeId}")
//                .buildAndExpand(newRecipe.getRecipeid())
//                .toUri();
//        responseHeaders.setLocation(newRecipeURI);
//
//        return new ResponseEntity<>(null,
//                responseHeaders,
//                HttpStatus.CREATED);
//    }



//    @PostMapping(value = "/recipe",
//            consumes = {"application/json"})
//    public ResponseEntity<?> addRecipe(
//            @Valid
//            @RequestBody
//                    Recipe newrecipe) throws
//            URISyntaxException
//    {
//        newrecipe.setRecipeid(0);
//        newrecipe = recipeService.save(newrecipe);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newCourseURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{recipeid}")
//                .buildAndExpand(newrecipe.getRecipeid())
//                .toUri();
//        responseHeaders.setLocation(newCourseURI);
//
//        return new ResponseEntity<>(null,
//                responseHeaders,
//                HttpStatus.CREATED);
//    }


}
