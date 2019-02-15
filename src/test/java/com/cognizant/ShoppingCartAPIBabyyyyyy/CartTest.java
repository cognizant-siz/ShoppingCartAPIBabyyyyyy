package com.cognizant.ShoppingCartAPIBabyyyyyy;

import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.CartRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.ItemRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.LineItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    LineItemRepository lineItemRepository;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() {
        cartRepository.deleteAll();
        lineItemRepository.deleteAll();
    }

    @After
    public void after() {
        cartRepository.deleteAll();
        lineItemRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
    }




}
