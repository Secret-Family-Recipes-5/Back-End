package com.lambdaschool.secretrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartid;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "cart",
            allowSetters = true)
    private List<CartItem> recipes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "carts",
            allowSetters = true)
    private User user;

    public Cart()
    {

    }

    public long getCartid()
    {
        return cartid;
    }

    public void setCartid(long cartid)
    {
        this.cartid = cartid;
    }

    public List<CartItem> getrecipes()
    {
        return recipes;
    }

    public void setrecipes(List<CartItem> recipes)
    {
        this.recipes = recipes;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
