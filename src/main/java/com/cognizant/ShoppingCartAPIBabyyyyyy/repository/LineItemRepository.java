package com.cognizant.ShoppingCartAPIBabyyyyyy.repository;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.LineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, Integer> {
}
