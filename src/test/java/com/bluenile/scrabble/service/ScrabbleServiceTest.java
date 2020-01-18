package com.bluenile.scrabble.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
public class ScrabbleServiceTest {
	
	@Autowired
	private ScrabbleService scrabbleService;
	
	private List<String> words1;
	private List<String> words2;
	private List<String> words3;
	
	@BeforeEach
	public void setup() {
		words1 = new ArrayList<>();
		words2 = new ArrayList<>();
		words3 = new ArrayList<>();
		
		String word1 = new String("hat");
		String word2 = new String("ah");
		String word3 = new String("ha");
		String word4 = new String("th");
		String word5 = new String("at");
		String word6 = new String("a");
		String word7 = new String("aha");
		String word8 = new String("aah");
		
		words1.add(word2);
		words1.add(word3);
		words1.add(word6);
		
		words2.add(word1);
		words2.add(word2);
		words2.add(word3);
		words2.add(word4);
		words2.add(word5);
		words2.add(word6);
		
		words3.add(word8);
		words3.add(word7);
		words3.add(word2);
		words3.add(word3);
		words3.add(word6);
	}
	
	
	@Test
	public void whenTwoNonRepeatedLowercaseLetters() throws Exception {

		assertEquals(words1, scrabbleService.findAllPossibleWords("ah"));

	}
	
	@Test
	public void whenThreeNonRepeatedLowercaseLetters() throws Exception {
		
		assertEquals(words2, scrabbleService.findAllPossibleWords("aht"));
	}
	
	@Test
	public void whenThreeNonRepeatedUppercaseLetters() throws Exception {
		
		assertEquals(words2, scrabbleService.findAllPossibleWords("HAT"));

	}
	
	@Test
	public void whenThreeNonRepeatedMixedcaseLetters() throws Exception {
		
		assertEquals(words2, scrabbleService.findAllPossibleWords("Hat"));

	}
	
	@Test
	public void whenOneRepeatedLetters() throws Exception {
		
		assertEquals(words3, scrabbleService.findAllPossibleWords("aha"));

	}
	
	@Test
	public void whenWordCantBeSpelledWithLetters() throws Exception {
		
		assertEquals(new ArrayList<>(), scrabbleService.findAllPossibleWords("zzz"));

	}

}
