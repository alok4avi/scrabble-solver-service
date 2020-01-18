package com.bluenile.scrabble.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluenile.scrabble.domain.ScrabbleWord;

/**
 * The purpose of this class is to provide utility methods 
 * to be used by the Scrabble service in order to fulfill the 
 * incoming request
 * 
 *
 */
public class ScrabbleUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrabbleWord.class);

	/**
	 * Method to filter the words based on no of a particular character exist in the
	 * incoming request
	 * 
	 * @return
	 */
	private static List<ScrabbleWord> filterWordsFromDictionary(Map<Character, Integer> inputCharMap,
			List<ScrabbleWord> words) {
		LOGGER.debug("Filtering the words found from dictionary");
		List<ScrabbleWord> filteredWords = words.stream().filter(word -> areEqualCharWords(inputCharMap, word.getCharCountMap()))
				.collect(Collectors.toList());
		return filteredWords;

	}

	/**
	 * Method to sort the list of words by their score
	 * 
	 * @param wordsList
	 * @param letters
	 * @return
	 */
	public static List<String> sortWordsByScore(List<String> wordsList, String letters) {
		LOGGER.debug("Sorting the words found from dictionary as per the score");
		List<ScrabbleWord> scrabbleWords = new ArrayList<>();
		scrabbleWords = wordsList.stream().map(word -> new ScrabbleWord(word)).collect(Collectors.toList());

		Collections.sort(scrabbleWords, new Comparator<ScrabbleWord>() {

			@Override
			public int compare(ScrabbleWord o1, ScrabbleWord o2) {
				return o2.getScore() > o1.getScore() ? 1 : o2.getScore() < o1.getScore() ? -1 : 0;
			}
		});
		return convertToListOfStrings(filterWordsFromDictionary(getCharCountMap(letters), scrabbleWords));

	}
	/**
	 * This method is used to generate list of strings(words) from given list of ScrabbleWord
	 * @param scrabbleWords
	 * @return
	 */

	private static List<String> convertToListOfStrings(List<ScrabbleWord> scrabbleWords) {
		return scrabbleWords.stream().map(ScrabbleWord::getWord).collect(Collectors.toList());
	}

	/**
	 * This utility method is used to check if the two maps being compared are having same character(if present) counts
	 * @param first
	 * @param second
	 * @return
	 */
	private static boolean areEqualCharWords(Map<Character, Integer> first, Map<Character, Integer> second) {
		boolean isMatched = true;
		for (Character test : first.keySet()) {
			if (second.get(test) != null && first.get(test)<second.get(test)) {
				isMatched = false;
				break;
			}
		}
		return isMatched;
	}

	/**
	 * This method is to generate character count Map for the given word
	 * @param word
	 * @return
	 */
	public static Map<Character, Integer> getCharCountMap(String word) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] charArray = word.toCharArray();
		for (char key : charArray) {
			int count = 0;
			if (map.containsKey(key)) {
				count = map.get(key);
			}
			map.put(key, ++count);
		}
		return map;
	}

}
