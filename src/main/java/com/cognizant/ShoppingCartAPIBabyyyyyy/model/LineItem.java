package com.cognizant.ShoppingCartAPIBabyyyyyy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "item", nullable = false)
    private Item item;

    @OneToOne
    @JoinColumn(name="cart_id", nullable=false)
    @JsonBackReference
    private Cart cart;

    public LineItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return id == lineItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", item=" + item +
                '}';
    }
}
