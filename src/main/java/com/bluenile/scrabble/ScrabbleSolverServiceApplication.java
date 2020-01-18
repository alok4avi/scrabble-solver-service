package com.bluenile.scrabble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.bluenile.scrabble.domain.TrieNode;
import com.bluenile.scrabble.domain.WordDictionary;


@SpringBootApplication
public class ScrabbleSolverServiceApplication {

	/**
	 * This bean gets loaded at the startup of the service and while starting up
	 *  it will load all the words from the dictionary(words.txt file) and made
	 *  available for the use even before the first request comes in
	 *
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
	@Bean
	public WordDictionary dictionary() {
		List<String> wordList = new ArrayList<String>();
		try {
			
			 ClassPathResource resource = new ClassPathResource("words.txt");
		     InputStream inputStream = resource.getInputStream();
		     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = "";
            while ((readLine = reader.readLine()) != null) {
            	wordList.add(readLine.toLowerCase());
            }
            reader.close();
            
        } catch (IOException e) {
        	LOGGER.error("Error while loading the data from text file");
            e.printStackTrace();
        } 
		TrieNode root = new TrieNode(); 
	       
        for(String word : wordList) {
        	root.insert(root, word);
        }
        WordDictionary dictionary = new WordDictionary();
        dictionary.setDictionaryRoot(root);
		return dictionary;
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ScrabbleSolverServiceApplication.class, args);
	}

}
