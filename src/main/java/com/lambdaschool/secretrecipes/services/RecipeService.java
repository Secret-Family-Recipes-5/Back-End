package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findRecipeById(long id);

    void delete(long id);

    Recipe update(long recipeid,
                  String title,
                  String source,
                  String ingredients,
                  String instructions,
                  String category,
                  User user);

    Recipe save(  String title,
                  String source,
                  String ingredients,
                  String instructions,
                  String category,
                  User user);

    List<Recipe> findByUserName(String username);
}
