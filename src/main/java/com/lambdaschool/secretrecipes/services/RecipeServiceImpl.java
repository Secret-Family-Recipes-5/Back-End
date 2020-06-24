package com.lambdaschool.secretrecipes.services;



import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.handlers.HelperFunctions;
import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.repository.RecipeRepository;
import com.lambdaschool.secretrecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository reciperepos;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelperFunctions helper;

//    @Transactional
//    @Override
//    public Recipe addNewRecipe(String title) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userRepository.findByUsername(authentication.getName());
//        Recipe newRecipe = new Recipe(title, currentUser);
//
//
//        return reciperepos.save(newRecipe);
//    }

//    String title, String source, String ingredients, String instructions, String category, User user
    @Transactional
    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName());
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipeid(recipe.getRecipeid());
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setSource(recipe.getSource());
        newRecipe.setIngredients(recipe.getIngredients());
        newRecipe.setInstructions(recipe.getInstructions());
        newRecipe.setCategory(recipe.getCategory());
        newRecipe.setUser(currentUser);

        return reciperepos.save(newRecipe);
    }


    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> items = new ArrayList<>();

       reciperepos.findAll().iterator().forEachRemaining(items::add);

        return items;
    }

    @Transactional
    @Override
    public Recipe update(Recipe newRecipe, long id) {
        Recipe currentRecipe = reciperepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        currentRecipe.setTitle(newRecipe.getTitle());

        return reciperepos.save(currentRecipe);
    }

    @Override
    public List<Recipe> getItemsByUserId(long id) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        return currentUser.getRecipes();
    }

    @Override
    public Recipe findItemById(long id) {
        return reciperepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public void delete(long id) {
        Recipe item = reciperepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        reciperepos.delete(item);
    }

}

