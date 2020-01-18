package com.bluenile.scrabble.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluenile.scrabble.service.ScrabbleService;

import io.swagger.annotations.ApiOperation;

/**
 * Main controller which intercepts all the incoming request with given url
 * pattern
 *
 */
@RestController
@RequestMapping(value = "/words")
public class ScrabbleController {

	@Autowired
	private ScrabbleService scrabbleService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * This method is used to to intake request to find possible words from the
	 * given set of letters
	 * 
	 * @param letters
	 * @return
	 */
	@ApiOperation(value = "Returns list of unique words formed with given letters", notes = "Returns the unique list of list of words formed with given set of letters")
	@GetMapping("/{letters}")
	public List<String> getAllPossibleWords(@PathVariable String letters) {
		LOGGER.debug("Invoking the service request to find words from letters : " + letters );
		return scrabbleService.findAllPossibleWords(letters);

	}

}
