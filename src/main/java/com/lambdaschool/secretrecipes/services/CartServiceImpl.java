package com.lambdaschool.secretrecipes.services;


import com.lambdaschool.secretrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretrecipes.models.Cart;
import com.lambdaschool.secretrecipes.models.CartItem;
import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.repository.CartRepository;
import com.lambdaschool.secretrecipes.repository.RecipeRepository;
import com.lambdaschool.secretrecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "cartService")
public class CartServiceImpl
        implements CartService
{
    /**
     * Connects this service to the cart repository
     */
    @Autowired
    private CartRepository cartrepos;

    /**
     * Connects this service the user repository
     */
    @Autowired
    private UserRepository userrepos;

    /**
     * Connects this service to the recipe repository
     */
    @Autowired
    private RecipeRepository reciperepos;

    /**
     * Connects this service to the auditing service in order to get current user name
     */
    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Cart> findAllByUserId(Long userid)
    {
        return cartrepos.findAllByUser_Userid(userid);
    }

    @Override
    public Cart findCartById(long id)
    {
        return cartrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Cart save(User user,
                     Recipe recipe)
    {
        Cart newCart = new Cart();

        User dbuser = userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found"));
        newCart.setUser(dbuser);

        Recipe dbrecipe = reciperepos.findById(recipe.getrecipeid())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + recipe.getrecipeid() + " not found"));

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(newCart);
        newCartItem.setrecipe(dbrecipe);
        newCartItem.setComments("");
        newCartItem.setQuantity(1);
        newCart.getrecipes()
                .add(newCartItem);

        return cartrepos.save(newCart);
    }

    @Transactional
    @Override
    public Cart save(Cart cart,
                     Recipe recipe)
    {
        Cart updateCart = cartrepos.findById(cart.getCartid())
                .orElseThrow(() -> new ResourceNotFoundException("Cart Id " + cart.getCartid() + " not found"));
        Recipe updateRecipe = reciperepos.findById(recipe.getrecipeid())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + recipe.getrecipeid() + " not found"));

        if (cartrepos.checkCartItems(updateCart.getCartid(), updateRecipe.getrecipeid())
                .getCount() > 0)
        {
            cartrepos.updateCartItemsQuantity(userAuditing.getCurrentAuditor()
                                                      .get(), updateCart.getCartid(), updateRecipe.getrecipeid(), 1);
        } else
        {
            cartrepos.addCartItems(userAuditing.getCurrentAuditor()
                                           .get(), updateCart.getCartid(), updateRecipe.getrecipeid());
        }

        return cartrepos.save(updateCart);
    }

    @Transactional
    @Override
    public void delete(Cart cart,
                       Recipe recipe)
    {
        Cart updateCart = cartrepos.findById(cart.getCartid())
                .orElseThrow(() -> new ResourceNotFoundException("Cart Id " + cart.getCartid() + " not found"));
        Recipe updateRecipe = reciperepos.findById(recipe.getrecipeid())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + recipe.getrecipeid() + " not found"));

        if (cartrepos.checkCartItems(updateCart.getCartid(), updateRecipe.getrecipeid())
                .getCount() > 0)
        {
            cartrepos.updateCartItemsQuantity(userAuditing.getCurrentAuditor()
                                                      .get(), updateCart.getCartid(), updateRecipe.getrecipeid(), -1);
            cartrepos.removeCartItemsQuantityZero();
            cartrepos.removeCartWithNoRecipes();
        } else
        {
            throw new ResourceNotFoundException("Cart id " + updateCart.getCartid() + " Recipe id " + updateRecipe.getrecipeid() + " combo not found");
        }
    }
}
