package com.cognizant.ShoppingCartAPIBabyyyyyy;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import com.cognizant.ShoppingCartAPIBabyyyyyy.model.LineItem;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LineItemTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    LineItemRepository lineItemRepository;

    @Autowired
    ItemRepository itemRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before() {
        lineItemRepository.deleteAll();
        itemRepository.deleteAll();
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
        LineItem expectedLineItem = new LineItem();
        Item item = new Item("item1");
        itemRepository.save(item);
        expectedLineItem.setItem(item);

        lineItemRepository.save(expectedLineItem);

        String response = mvc.perform(get("/api/lineItem"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<LineItem> actual = objectMapper.readValue(response,
                new TypeReference<List<LineItem>>(){});

        assertEquals("GET call to /api/LineItems should return one item.",
                1, actual.size());

        LineItem actualLineItem = actual.get(0);
        assertEquals("GET response should match the record in the database.",
                expectedLineItem, actualLineItem);
    }
}
