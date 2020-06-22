package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.exceptions.ResourceFoundException;
import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.handlers.HelperFunctions;
//import com.lambdaschool.secretrecipes.models.Section;
//import com.lambdaschool.secretrecipes.models.Wrote;
import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.repository.CartRepository;
import com.lambdaschool.secretrecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository reciperepos;

    @Autowired
    private CartRepository cartrepos;

    /**
     * Connects this service to the auditing service in order to find the current user
     */
    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Recipe> findAll()
    {
        List<Recipe> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        reciperepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Recipe findRecipeById(long id)
    {
        return reciperepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        reciperepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
        reciperepos.deleteById(id);
        cartrepos.removeCartWithNoRecipes();
    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe)
    {
        if (recipe.getCarts()
                .size() > 0)
        {
            throw new ResourceFoundException("Carts are not updated through Recipes");
        }
        ;

        return reciperepos.save(recipe);
    }

    @Transactional
    @Override
    public Recipe update(long id,
                          Recipe recipe)
    {
        if (recipe.getCarts()
                .size() > 0)
        {
            throw new ResourceFoundException("Carts cannot be updated through this process");
        }

        Recipe currentRecipe = reciperepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));

        if (recipe.getTitle() != null)
        {
            currentRecipe.setTitle(recipe.getTitle());
        }

        if (recipe.hasprice)
        {
            currentRecipe.setPrice(recipe.getPrice());
        }

        if (recipe.getInstructions() != null)
        {
            currentRecipe.setInstructions(recipe.getInstructions());
        }

        if (recipe.getComments() != null)
        {
            currentRecipe.setComments(recipe.getComments());
        }

        reciperepos.updateRecipeInformation(userAuditing.getCurrentAuditor()
                        .get(),
                id,
                currentRecipe.getTitle(),
                currentRecipe.getPrice(),
                currentRecipe.getTitle(),
                currentRecipe.getComments());
        return findRecipeById(id);
    }
}