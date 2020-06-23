package com.lambdaschool.secretrecipes.services;



import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.handlers.HelperFunctions;
import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private HelperFunctions helper;

    @Override
    public List<Recipe> findAll() {

        List<Recipe> recipe = new ArrayList<>();

        reciperepos.findAll().iterator().forEachRemaining(recipe::add);
        return recipe;
    }

    @Override
    public Recipe findRecipeById(long id) {

        return reciperepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe with id " + id + " not found."));
    }

    @Transactional
    @Override
    public void delete(long id) {

        if(reciperepos.findById(id).isPresent()) {

            if(helper.isAuthorizedToMakeChange(reciperepos.findById(id).get().getUser().getUsername())) {

                reciperepos.deleteById(id);
            }
        } else {

            throw new ResourceNotFoundException("Listing with id " + id + " not found.");
        }
    }

    @Override
    public List<Recipe> findByUserName(String username)
    {
        return reciperepos.findAllByUser_Username(username.toLowerCase());
    }

    @Transactional
    @Override
    public Recipe update(long recipeid,
                         String title,
                         String source,
                         String ingredients,
                         String instructions,
                         String category,
                         User user) {

        if (reciperepos.findById(recipeid)
                .isPresent())
        {
            if (helper.isAuthorizedToMakeChange(reciperepos.findById(recipeid)
                    .get()
                    .getUser()
                    .getUsername()))
            {
                Recipe recipe = findRecipeById(recipeid);
                recipe.setTitle(title);
                recipe.setSource(source);
                recipe.setIngredients(ingredients);
                recipe.setInstructions(instructions);
                recipe.setCategory(category);
                recipe.setUser(user);
                return reciperepos.save(recipe);
            } else
            {

                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Listing with id " + recipeid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Recipe save(String title,
                         String source,
                         String ingredients,
                         String instructions,
                         String category,
                         User user) {

        User currentUser = userService.findUserById(user.getUserid());

        if (helper.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            Recipe newRecipe = new Recipe(
                    title,
                    source,
                    ingredients,
                    instructions,
                    category,
                    user);

            return reciperepos.save(newRecipe);
        } else
        {
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }

}

