package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.models.Recipe;

import java.util.List;

public interface RecipeService {

        /**
         * Returns a list of all the Recipes
         *
         * @return List of Recipes. If no Recipes, empty list.
         */
        List<Recipe> findAll();

        /**
         * Returns the Recipe with the given primary key.
         *
         * @param id The primary key (long) of the Recipe you seek.
         * @return The given Recipe or throws an exception if not found.
         */
        Recipe findRecipeById(long id);

        /**
         * Deletes the Recipe record from the database based off of the provided primary key
         *
         * @param id id The primary key (long) of the Recipe you seek.
         */
        void delete(long id);

        /**
         * Given a complete Recipe object, saves that Recipe object in the database.
         * If a primary key is provided, the record is completely replaced
         * If no primary key is provided, one is automatically generated and the record is added to the database.
         *
         * @param recipe the Recipe object to be saved
         * @return the saved Recipe object including any automatically generated fields
         */
        Recipe save(Recipe recipe);

        /**
         * Updates the given recipe
         *
         * @param id      The primary key (long) of the recipe you wish to update
         * @param recipe The recipe object containing the new information - only the recipe can be updated here, no carts!
         * @return The complete recipe with the field changes
         */
        Recipe update(
                long id,
                Recipe recipe);
    }
