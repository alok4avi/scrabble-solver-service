package com.bluenile.scrabble;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import com.bluenile.scrabble.controller.ScrabbleController;
import com.bluenile.scrabble.domain.WordDictionary;

@SpringBootTest
public class ScrabbleServiceApplicationTest {

	@Autowired
	ApplicationContext context;


	@Test
	public void contextLoads() {
		WordDictionary dictionary = context.getBean(WordDictionary.class);
		assertTrue(dictionary instanceof WordDictionary);

		ScrabbleController controller = context.getBean(ScrabbleController.class);
		assertNotNull(controller);
	}
	
	@Test
	public void OnStartupDictionaryIsLoaded() {
		WordDictionary dictionary = context.getBean(WordDictionary.class);
		assertNotNull(dictionary.getDictionaryRoot());
	}

}
