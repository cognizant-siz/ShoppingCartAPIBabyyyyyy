package com.cognizant.ShoppingCartAPIBabyyyyyy.controller;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import com.cognizant.ShoppingCartAPIBabyyyyyy.model.LineItem;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.ItemRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lineItem")
public class LineItemController {
    @Autowired
    private LineItemRepository repository;

    @GetMapping
    public List<LineItem> getAllLineItems() {
        return (List<LineItem>)repository.findAll();
    }

    @PostMapping
    public LineItem postLineItem(@RequestBody LineItem lineItem) {
        return repository.save(lineItem);
    }

    @PutMapping
    public LineItem putLineItem(@RequestBody LineItem lineItem) {
        return repository.save(lineItem);
    }
}
