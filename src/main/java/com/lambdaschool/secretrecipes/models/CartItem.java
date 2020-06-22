package com.lambdaschool.secretrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cartitems")
public class CartItem
        extends Auditable
        implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "cartid")
    @JsonIgnoreProperties(value = "recipes")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "carts")
    private Recipe recipe;

    private long quantity;
    private String comments;

    public CartItem()
    {

    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public Recipe getrecipe()
    {
        return recipe;
    }

    public void setrecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CartItem cart_item = (CartItem) o;
        return quantity == cart_item.quantity &&
                cart.equals(cart_item.cart) &&
                recipe.equals(cart_item.recipe) &&
                Objects.equals(comments,
                               cart_item.comments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cart,
                            recipe,
                            quantity,
                            comments);
    }
}
