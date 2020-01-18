package com.bluenile.scrabble.domain;

import java.util.List;

public class WordDictionary {

	private TrieNode dictionaryRoot;

	public List<String> findAllPossibleWords(char arr[]) {

		int size = arr.length;
		dictionaryRoot.findAllWords(arr, size);
		return dictionaryRoot.getWordList();
	}

	public TrieNode getDictionaryRoot() {
		return dictionaryRoot;
	}

	public void setDictionaryRoot(TrieNode dictionaryRoot) {
		this.dictionaryRoot = dictionaryRoot;
	}

}
