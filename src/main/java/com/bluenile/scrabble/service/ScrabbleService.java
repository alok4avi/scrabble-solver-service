package com.bluenile.scrabble.service;

import java.util.List;


public interface ScrabbleService {
	
	/**
	 * This method is used to find all the possible words from given set of letters
	 * @param letters
	 * @return
	 */
	List<String> findAllPossibleWords(String letters);

}
