package com.thinkhr.app;

import com.thinkhr.app.response.WordResponse;
import com.thinkhr.app.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class WordServiceTest {
    @Autowired
    private WordService wordService;

    private final static String INVALID_INPUT_PARAM =  "TestFoo";
    private final static String INPUT_PARAM_1 =  "aalii";

    @Test
    void inCorrectWordSearchTest() throws IOException {
        assertNotEquals(INVALID_INPUT_PARAM, wordService.searchWord(Optional.empty(),Optional.empty()));
    }

    @Test
    void correctWordSearchWithRandomPickTest() throws IOException {
        assertNotNull(wordService.searchWord(Optional.empty(),Optional.empty()));
    }

    @Test
    void correctWordSearchWithRandomPickStatsTest() throws IOException {
        WordResponse wordResponse = wordService.searchWord(Optional.empty(),Optional.of(Boolean.TRUE));
        assertNotNull(wordResponse);
        assertNotNull(wordResponse.getWordSearched());
        assertTrue(wordResponse.getTotalOccurrences() > 0);
    }

    @Test
    void correctWordSearchWithPassedInWordTest() throws IOException {
        assertNotNull(wordService.searchWord(Optional.of(INPUT_PARAM_1),Optional.empty()));
    }

    @Test
    void correctWordSearchWithPassedInParamStatsTest() throws IOException {
        WordResponse wordResponse = wordService.searchWord(Optional.of(INPUT_PARAM_1), Optional.of(Boolean.TRUE));
        assertNotNull(wordResponse);
        assertEquals(INPUT_PARAM_1,wordResponse.getWordSearched());
        assertTrue(wordResponse.getTotalOccurrences() > 0);
    }
}
