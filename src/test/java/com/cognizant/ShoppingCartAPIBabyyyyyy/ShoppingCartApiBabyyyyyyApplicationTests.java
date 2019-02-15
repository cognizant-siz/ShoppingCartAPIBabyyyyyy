package com.cognizant.ShoppingCartAPIBabyyyyyy;

import com.cognizant.ShoppingCartAPIBabyyyyyy.model.Item;
import com.cognizant.ShoppingCartAPIBabyyyyyy.repository.ItemRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartApiBabyyyyyyApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ItemRepository itemRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void before() {
		itemRepository.deleteAll();
	}

	@After
	public void after() {
		//itemRepository.deleteAll();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllItems() throws Exception {
		// Setup
		Item expectedItem = new Item();
		expectedItem.setName("Thomas Fowler");

		itemRepository.save(expectedItem);

		String response = mvc.perform(get("/api/item"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		List<Item> actual = objectMapper.readValue(response,
				new TypeReference<List<Item>>(){});

		assertEquals("GET call to /api/Items should return one item.",
				1, actual.size());

		Item actualItem = actual.get(0);
		assertEquals("GET response should match the record in the database.",
				expectedItem, actualItem);
	}

	@Test
	public void testPostItem() throws Exception {
		// Setup
		Item expectedItem = new Item();
		expectedItem.setName("Thomas Fowler");
		expectedItem.setPrice(1000f);

		String generatedId = mvc.perform(post("/api/item")
				.content(asJsonString(expectedItem))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();

		expectedItem.setId(Integer.valueOf(generatedId));

		System.out.println("\n\ntestPostItem: expected: " + asJsonString(expectedItem));

		Item actualItem = itemRepository.findById(expectedItem.getId()).orElse(null);
		System.out.println("testPostItem: actual: " + asJsonString(actualItem) + "\n");
		assertEquals("GET response should match the record in the database.",
				expectedItem, actualItem);
	}

	@Test
	public void testGetItemById() throws Exception {
		// Setup
		Item expectedItem = new Item();
		expectedItem.setName("Thomas Fowler");
		expectedItem.setPrice(1000f);

		String generatedId = mvc.perform(post("/api/item")
				.content(asJsonString(expectedItem))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();

		expectedItem.setId(Integer.valueOf(generatedId));

		//System.out.println(generatedId + "\n\n\n");

		String response = mvc.perform(get("/api/item/" + expectedItem.getId()))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Item actual = objectMapper.readValue(response,
				new TypeReference<Item>(){});

		assertEquals("GET response should match the record in the database.",
				expectedItem, actual);
	}

	@Test
	public void testPutItemById() throws Exception {
		// Setup
		Item expectedItem = new Item();
		expectedItem.setName("Thomas Fowler");
		expectedItem.setPrice(1000f);

		expectedItem = itemRepository.save(expectedItem);
		//System.out.println(generatedId + "\n\n\n");

		String response = mvc.perform(put("/api/item/")
				.content(asJsonString(expectedItem))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Item actual = objectMapper.readValue(response,
				new TypeReference<Item>(){});

		assertEquals("PUT response should match the record in the database.",
				expectedItem, actual);
	}

	@Test
	public void testDeleteItemById() throws Exception {
		// Setup
		Item expectedItemToDelete = new Item();
		expectedItemToDelete.setName("Thomas Fowler");
		expectedItemToDelete.setPrice(1000f);

		int generatedId = itemRepository.save(expectedItemToDelete).getId();

		expectedItemToDelete.setId(generatedId);

		//System.out.println(generatedId + "\n\n\n");

		mvc.perform(delete("/api/item/" + generatedId));

		Item response = itemRepository.findById(generatedId).orElse(null);

		Assert.assertNull("Delete item from database.", response);
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

