package com.lambdaschool.secretrecipes.repository;

import com.lambdaschool.secretrecipes.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
