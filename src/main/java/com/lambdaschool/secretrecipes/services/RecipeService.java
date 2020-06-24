package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RecipeService {

//    Recipe addNewRecipe(String title);

     Recipe addNewRecipe(Recipe recipe);



//    Recipe addNewRecipe(String title, String source, String ingredients, String instructions, String category);

    List<Recipe> getAllRecipes();

    Recipe findItemById(long id);

    Recipe update(Recipe recipe, long id);

    void delete(long id);

    List<Recipe> getItemsByUserId(long id);
}
