package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.Useremail;

import java.util.List;

public interface RecipeService {
    Recipe findrecipeById(long id);

    void deleterecipeById(long id);

    List<Recipe> findAllrecipes();

    Recipe save(Recipe recipe);

    Recipe save(
            long userid,
            String userrecipes);
}
