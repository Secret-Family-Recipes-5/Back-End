package com.lambdaschool.secretrecipes.repository;

import com.lambdaschool.secretrecipes.models.Recipe;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
