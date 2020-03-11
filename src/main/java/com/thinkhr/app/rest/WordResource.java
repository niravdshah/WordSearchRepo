package com.thinkhr.app.rest;

import com.thinkhr.app.response.WordResponse;
import com.thinkhr.app.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/searchWord")
public class WordResource  {
    private static final Logger logger = LoggerFactory.getLogger(WordResource.class);

    @Autowired
    private WordService wordService;

    @RequestMapping(method= RequestMethod.GET,produces="application/json")
    public WordResponse searchWord(@RequestParam("wordToSearch")  Optional<String> wordToSearch,@RequestParam("searchOccurrences") Optional<Boolean> searchOccurrences) {
        WordResponse wordResponse;
        try {
            logger.info("Search initiated for word:"+wordToSearch);
            wordResponse = wordService.searchWord(wordToSearch,searchOccurrences);
            return wordResponse;
        } catch(Exception e) {
            logger.error("Error searching words:"+e.getMessage());
            wordResponse = new WordResponse();
            wordResponse.setTotalOccurrences(0);
            wordResponse.setWordFound(false);
            wordResponse.setWordSearched(wordToSearch.get());
            return wordResponse;
        }
    }
}
