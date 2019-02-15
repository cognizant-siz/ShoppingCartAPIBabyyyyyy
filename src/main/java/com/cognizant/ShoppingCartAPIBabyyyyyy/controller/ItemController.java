package com.cognizant.ShoppingCartAPIBabyyyyyy.controller;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemRepository repository;

    @GetMapping
    public List<Item> getAllItems() {
        return (List<Item>)repository.findAll();
    }

    @PostMapping
    public String postItem(@RequestBody Item itemToPost) {
        Item saveResponse = repository.save(itemToPost);

        if (saveResponse == null)
        {
            return "-1";
        }

        return String.valueOf(saveResponse.getId());
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id) {
        Item item = repository.findById(id).orElse(new Item("Item with Id " + id + " not found."));

        return item;
    }

    @PutMapping
    public Item putItem(@RequestBody Item itemToPost) {
        return repository.save(itemToPost);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable int id) {
        repository.deleteById(id);
    }
}
