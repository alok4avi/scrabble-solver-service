package com.bluenile.scrabble.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a particular node in the Trie data structure
 *
 */
public class TrieNode {

	private final int SIZE = 26;
	private List<String> wordList = new ArrayList<String>();
	TrieNode[] child = new TrieNode[SIZE];
	
	boolean leaf;

	public TrieNode() {
		leaf = false;
		for (int i = 0; i < SIZE; i++)
			child[i] = null;
	}

	/**
	 * This method is used to insert a given word in the Trie data structure
	 * @param root
	 * @param word
	 */
	public void insert(TrieNode root, String word) {
		int n = word.length();
		TrieNode pChild = root;

		for (int i = 0; i < n; i++) {
			int index = word.charAt(i) - 'a';
			if (pChild.child[index] == null)
				pChild.child[index] = new TrieNode();

			pChild = pChild.child[index];
		}
		pChild.leaf = true;
	}

	/**
	 * A recursive function to search all possible valid words present in array
	 * @param root
	 * @param letterHash
	 * @param word
	 * @param lengthOfWord
	 */
	private void searchWord(TrieNode root, boolean letterHash[], String word, int lengthOfWord) {
		if (root.leaf == true && word.length() <= lengthOfWord) {
			wordList.add(word);
		}

		for (int i = 0; i < SIZE; i++) {
			if (letterHash[i] == true && root.child[i] != null) {
				char c = (char) (i + 'a');

				searchWord(root.child[i], letterHash, word + c, lengthOfWord);
			}
		}
	}

	/**
	 * Finds all the words present in dictionary form given set of characters and having given length
	 * @param wordCharArray
	 * @param lengthOfWord
	 */
	public void findAllWords(char wordCharArray[], int lengthOfWord) {
		wordList.clear();
		boolean[] letterHash = new boolean[SIZE];

		for (int i = 0; i < lengthOfWord; i++)
			letterHash[wordCharArray[i] - 'a'] = true;

		TrieNode pChild = this;

		String str = "";

		for (int i = 0; i < SIZE; i++) {
			if (letterHash[i] == true && pChild.child[i] != null) {
				str = str + (char) (i + 'a');
				searchWord(pChild.child[i], letterHash, str, lengthOfWord);
				str = "";
			}
		}
	}

	public List<String> getWordList() {
		return wordList;
	}

}
