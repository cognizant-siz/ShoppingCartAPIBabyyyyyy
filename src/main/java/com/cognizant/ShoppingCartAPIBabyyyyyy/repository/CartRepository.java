package com.cognizant.ShoppingCartAPIBabyyyyyy.repository;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
}
