package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.handlers.HelperFunctions;
import com.lambdaschool.secretrecipes.models.Recipe;
//import com.lambdaschool.secretrecipes.models.Section;
//import com.lambdaschool.secretrecipes.models.Wrote;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.models.Useremail;
import com.lambdaschool.secretrecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helper;

    @Override
    public Recipe findrecipeById(long id) {
        return recipeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("recipe id " + id + " not found"));

    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {
        Recipe newrecipe = new Recipe();

        if (recipe.getRecipeid() != 0) {
            Recipe existingrecipe = recipeRepository.findById(recipe.getRecipeid())
                    .orElseThrow(() -> new ResourceNotFoundException("recipe id " + recipe.getRecipeid() + " not found!"));


            newrecipe.setRecipeid(recipe.getRecipeid());
        }

        newrecipe.setRecipetitle(recipe.getRecipetitle());
        newrecipe.setSource(recipe.getSource());
        newrecipe.setCopy(recipe.getCopy());
//        newrecipe.setSection(new Section(recipe.getSection().getSectionname()));

//        List<Wrote> list = new ArrayList<>();
//        for (Wrote wr : recipe.getAuthors())
//        {
//            list.add(wr);
//            newrecipe.setWrotes(list);
//        }

        return recipeRepository.save(newrecipe);
    }

    @Override
    public List<Recipe> findAllrecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeList::add);
        return recipeList;
    }

    @Transactional
    @Override
    public void deleterecipeById(long id) {
        Recipe recipeToDel = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found for recipe"));

        recipeRepository.delete(recipeToDel);
    }

    @Transactional
    @Override
    public Recipe save(
            long userid,
            String userrecipes)
    {
        User currentUser = userService.findUserById(userid);

        if (helper.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            Recipe newUserRecipe = new Recipe(userrecipes,
                    currentUser);
            return recipeRepository.save(newUserRecipe);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}