package com.bluenile.scrabble.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bluenile.scrabble.domain.WordDictionary;
import com.bluenile.scrabble.util.ScrabbleUtil;

/**
 * Service class to service the request coming from controller
 *
 */
@Service
public class ScrabbleServiceImpl implements ScrabbleService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WordDictionary dictionary;

	@Override
	public List<String> findAllPossibleWords(String letters) {
		LOGGER.debug("Invoking the service method to find all possible words");
		char[] charArray = letters.toLowerCase().toCharArray();
		return ScrabbleUtil.sortWordsByScore(dictionary.findAllPossibleWords(charArray), letters.toLowerCase());
	}

}
