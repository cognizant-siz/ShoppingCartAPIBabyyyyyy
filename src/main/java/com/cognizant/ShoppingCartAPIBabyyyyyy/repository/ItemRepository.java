package com.cognizant.ShoppingCartAPIBabyyyyyy.repository;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
}
