package com.lambdaschool.secretrecipes.controllers;

import com.lambdaschool.secretrecipes.models.Cart;
import com.lambdaschool.secretrecipes.models.Recipe;
import com.lambdaschool.secretrecipes.models.User;
import com.lambdaschool.secretrecipes.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> listAllCarts(@PathVariable long userid)
    {
        List<Cart> myCarts = cartService.findAllByUserId(userid);
        return new ResponseEntity<>(myCarts, HttpStatus.OK);
    }

    @GetMapping(value = "/cart/{cartId}",
            produces = {"application/json"})
    public ResponseEntity<?> getCartById(
            @PathVariable
                    Long cartId)
    {
        Cart p = cartService.findCartById(cartId);
        return new ResponseEntity<>(p,
                                    HttpStatus.OK);
    }

    @PostMapping(value = "/create/user/{userid}/recipe/{recipeid}")
    public ResponseEntity<?> addNewCart(@PathVariable long userid,
                                        @PathVariable long recipeid)
    {
        User dataUser = new User();
        dataUser.setUserid(userid);

        Recipe datarecipe = new Recipe();
        datarecipe.setrecipeid(recipeid);

        cartService.save(dataUser, datarecipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/cart/{cartid}/recipe/{recipeid}")
    public ResponseEntity<?> updateCart(@PathVariable long cartid,
                                        @PathVariable long recipeid)
    {
        Cart dataCart = new Cart();
        dataCart.setCartid(cartid);

        Recipe datarecipe = new Recipe();
        datarecipe.setrecipeid(recipeid);

        cartService.save(dataCart, datarecipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/cart/{cartid}/recipe/{recipeid}")
    public ResponseEntity<?> deleteFromCart(@PathVariable long cartid,
                                            @PathVariable long recipeid)
    {
        Cart dataCart = new Cart();
        dataCart.setCartid(cartid);

        Recipe datarecipe = new Recipe();
        datarecipe.setrecipeid(recipeid);

        cartService.delete(dataCart, datarecipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
