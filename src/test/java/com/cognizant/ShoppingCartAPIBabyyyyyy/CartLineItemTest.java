package com.cognizant.ShoppingCartAPIBabyyyyyy;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Cart;
import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import com.cognizant.ShoppingCartAPIBabyyyyyy.model.LineItem;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.CartRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.ItemRepository;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.LineItemRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.cognizant.ShoppingCartAPIBabyyyyyy.ShoppingCartApiBabyyyyyyApplicationTests.asJsonString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartLineItemTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    LineItemRepository lineItemRepository;

    @Autowired
    ItemRepository itemRepository;

    private Cart cart;
    private LineItem lineItem;
    private Item item;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() {
        lineItemRepository.deleteAll();
        itemRepository.deleteAll();

        cart = new Cart();
        lineItem = new LineItem();
        item = new Item("item1");
        itemRepository.save(item);
        cartRepository.save(cart);

        lineItem.setCart(cart);
        lineItem.setItem(item);
        //System.out.println(cart.toString());
        lineItemRepository.save(lineItem);
        cart.getLineItems().add(lineItem);
        //System.out.println(lineItem.toString() + "\n\n" + cart.toString());
        cartRepository.save(cart);
//        System.out.println(lineItemRepository.findById(lineItem.getId()) + "==========");
//        System.out.println(cartRepository.findById(cart.getId()) + "==========");


    }

    @After
    public void after() {
        lineItemRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetLineItem() throws Exception {

        List<LineItem> expectedLineItem = new ArrayList<>();
        expectedLineItem.add(lineItem);
        System.out.println("======CartLineItemTest.testGetLineItem: url="+"/api/cart/" + cart.getId()+"/lineItem");
        String response = mvc.perform(get("/api/cart/" + cart.getId()+"/lineItem"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //System.out.println("======CartLineItemTest.testGetLineItem: response="+response);
        List<LineItem> actual = objectMapper.readValue(response,
                new TypeReference<List<LineItem>>(){});

        assertEquals("GET call to /api/LineItems should return one item.",
                1, actual.size());

        LineItem actualLineItem = actual.get(0);
        assertEquals("GET response should match the record in the database.",
                expectedLineItem, actual);
    }

//    @Test
//    public void testPostLineItem() throws Exception {
//        LineItem expectedLineItem = new LineItem();
//        Item item = new Item("Turnip");
//        itemRepository.save(item);
//        expectedLineItem.setItem(item);
//        expectedLineItem.setQuantity(3);
//        expectedLineItem.setId(-1);
//
//        String response = mvc.perform(post("/api/cart/lineItem")
//                .content(asJsonString(expectedLineItem))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        LineItem actualLineItem = objectMapper.readValue(response,
//                new TypeReference<LineItem>(){});
//
//        assertNotEquals("Post response should generate a id and not match the original expected.",
//                expectedLineItem.getId(), actualLineItem.getId());
//
//        expectedLineItem.setId(actualLineItem.getId());
//
//        assertEquals("POST response should match the record in the database.",
//                expectedLineItem, actualLineItem);
//    }
//
//    @Test
//    public void testPutLineItem() throws Exception {
//        LineItem expectedLineItem = new LineItem();
//        Item item = new Item("Turnip");
//        itemRepository.save(item);
//        expectedLineItem.setItem(item);
//        expectedLineItem.setQuantity(3);
//
//        System.out.println(asJsonString(expectedLineItem));
//
//        lineItemRepository.save(expectedLineItem);
//
//        expectedLineItem.setQuantity(12);
//
//        System.out.println(asJsonString(expectedLineItem));
//
//        String response = mvc.perform(put("/api/cart/lineItem")
//                .content("["+asJsonString(cart) +","+asJsonString(expectedLineItem)+"]")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        LineItem actualLineItem = objectMapper.readValue(response,
//                new TypeReference<LineItem>(){});
//
//        assertEquals("PUT response should match the record in the database with values that were changed.",
//                expectedLineItem, actualLineItem);
//    }
}
