package com.lambdaschool.secretrecipes.repository;

import com.lambdaschool.secretrecipes.models.Recipe;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET name = :name, price = :price, description = :description, comments = :comments, last_modified_by = :uname, last_modified_date = CURRENT_TIMESTAMP where productid = :productid", nativeQuery = true)
    void updateRecipeInformation(String uname,
                                  long productid,
                                  String name,
                                  double price,
                                  String description,
                                  String comments);
}
