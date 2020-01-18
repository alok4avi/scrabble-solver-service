package com.bluenile.scrabble.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bluenile.scrabble.service.ScrabbleService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ScrabbleControllerTest {

	@Mock
	ScrabbleService scrabbleService;

	@InjectMocks
	ScrabbleController scrabbleController;

	@Test
	public void testFindWords() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		List<String> wordsList = new ArrayList<>();
		wordsList.add("a");
		
		Mockito.when(scrabbleService.findAllPossibleWords(Mockito.anyString())).thenReturn(wordsList);

		List<String> response = scrabbleController.getAllPossibleWords("a");

		assertEquals(wordsList, response);
	}

}
