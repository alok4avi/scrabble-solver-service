package com.bluenile.scrabble.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluenile.scrabble.util.ScrabbleUtil;

/**
 * Class to represent a word in game of Scrabbles
 *
 */
public class ScrabbleWord {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScrabbleWord.class);
	private String word;
	private int score;
	public static final Map<Character, Integer> CHAR_SCORE_MAP = new HashMap<Character, Integer>();
	private static final List<Character> SCORE_1_ALPHABETS = Arrays.asList(new Character[]{'A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U'});
	private static final List<Character> SCORE_2_ALPHABETS = Arrays.asList(new Character[]{'D', 'G'});
	private static final List<Character> SCORE_3_ALPHABETS = Arrays.asList(new Character[]{'B', 'C', 'M', 'P'});
	private static final List<Character> SCORE_4_ALPHABETS = Arrays.asList(new Character[]{'F', 'H', 'V', 'W', 'Y'});
	private static final List<Character> SCORE_5_ALPHABETS = Arrays.asList(new Character[]{'K'});
	private static final List<Character> SCORE_8_ALPHABETS = Arrays.asList(new Character[]{'J', 'X'});
	private static final List<Character> SCORE_10_ALPHABETS = Arrays.asList(new Character[]{'Q', 'Z'});

	static {
		try {
			loadCharScoreMap();
		}  catch (Throwable t) {
			LOGGER.error("Failure during static initialization", t);
		    throw t;
		}
		
	}

	public ScrabbleWord(String word) {
		this.word = word;
		this.score = calculateScore();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getScore() {
		return score;
	}

	private int calculateScore() {
		char[] letters = this.word.toUpperCase().toCharArray();
		int score = 0;
		for (char c : letters) {
			score += CHAR_SCORE_MAP.get(c);
		}
		return score;
	}

	public Map<Character, Integer> getCharCountMap() {
		return ScrabbleUtil.getCharCountMap(this.word);
	}

	private static void loadCharScoreMap() {
		char alphabet;
		
		for (alphabet = 'A'; alphabet <= 'Z'; ++alphabet) {
			if (SCORE_1_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(1));
			} else if (SCORE_2_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(2));
			} else if (SCORE_3_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(3));
			} else if (SCORE_4_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(4));
			} else if (SCORE_5_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(5));
			} else if (SCORE_8_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(8));
			} else if (SCORE_10_ALPHABETS.contains(alphabet)) {
				CHAR_SCORE_MAP.put(alphabet, Integer.valueOf(10));
			}
		}

	}

}
