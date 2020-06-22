package com.lambdaschool.secretrecipes.services;



import com.lambdaschool.secretrecipes.models.Cart;
import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;

import java.util.List;

/**
 * The Service that works with Cart Model.
 */

public interface CartService
{
    // find all carts for a user
    // find cart by id
    // user adds Cart without cart create new cart
    // user adds Cart with cart
    // user removed Cart from cart. If cart is empty, delete cart

    /**
     * Returns a list of all the Carts for the given user id
     *
     * @param userid userid that you seek
     * @return List of Carts. If no Carts, empty list.
     */
    List<Cart> findAllByUserId(Long userid);

    /**
     * Returns the Cart with the given primary key.
     *
     * @param id The primary key (long) of the Cart you seek.
     * @return The given Cart or throws an exception if not found.
     */
    Cart findCartById(long id);

    /**
     * Creates a new cart for this user populated with this recipe
     *
     * @param user    the user to be saved
     * @param recipe the recipe to be saved
     * @return the saved Cart object including any automatically generated fields
     */
    Cart save(User user,
              Recipe recipe);

    /**
     * Creates a new recipe for this cart
     *
     * @param cart    the cart to gain the recipe
     * @param recipe the recipe to be added
     * @return the saved Cart object including any automatically generated fields
     */
    Cart save(Cart cart,
              Recipe recipe);

    /**
     * Removes recipe from this cart
     *
     * @param cart    the cart to lose the recipe
     * @param recipe the recipe to be removed
     */
    void delete(Cart cart,
                Recipe recipe);
}
