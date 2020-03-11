package com.thinkhr.app.service;

import com.thinkhr.app.response.WordResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * This service returns the random word or passed in word from the text file along with its occurrences if requested.
 */
public interface WordService {
    WordResponse searchWord(Optional<String> wordToSearch, Optional<Boolean> searchOccurrences) throws IOException;
}
