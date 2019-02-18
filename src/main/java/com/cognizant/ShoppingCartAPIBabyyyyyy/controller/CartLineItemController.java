package com.cognizant.ShoppingCartAPIBabyyyyyy.controller;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Cart;
import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import com.cognizant.ShoppingCartAPIBabyyyyyy.model.LineItem;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.CartRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.ItemRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart/")
public class CartLineItemController {
    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("{id}/lineItem")
    public List<LineItem> getAllLineItems(@PathVariable int id) {
        System.out.println("======CartLineItemController.getAllLineItems: " + cartRepository.findById(id).orElse(new Cart()).getLineItems());
        return cartRepository.findById(id).orElse(new Cart()).getLineItems();
        //return (List<LineItem>)lineItemRepository.findAll();
    }

    @PostMapping("{id}/lineItem")
    public Cart postLineItemToCart(@PathVariable int id, @RequestBody LineItem lineItem) {
        Cart cart = cartRepository.findById(id).orElse(null);
        lineItem.setCart(cart);
        lineItemRepository.save(lineItem);
        cart.getLineItems().add(lineItem);
        return cartRepository.save(cart);
    }

    @PutMapping("lineItem")
    public Cart putLineItemToCart(@RequestBody LineItem lineItem) {
        lineItemRepository.save(lineItem);
        return lineItem.getCart();
    }
}
