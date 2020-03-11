package com.thinkhr.app.service.impl;

import com.thinkhr.app.response.WordResponse;
import com.thinkhr.app.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class WordServiceImpl implements WordService {
    private static final Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);

    @Value("classpath:words_alpha.txt")
    Resource resourceFile;
    /**
     * This method reads the txt file from the resource folder. It will find a random string if there are no parameter passed.
     * This method also takes wordToSearch and searchOccurrences to return user passed in search word along with the number of
     * occurrences.
     *
     * @param wordToSearch
     * @param searchOccurrences
     * @return
     * @throws IOException
     */
    @Override
    public WordResponse searchWord(Optional<String> wordToSearch, Optional<Boolean> searchOccurrences) throws IOException {
        Scanner scanner = new Scanner(resourceFile.getInputStream());
        Random random = new Random();
        int randomSkip = random.nextInt(ThreadLocalRandom.current().nextInt(10, 13));
        String randomString;
        WordResponse wordResponse = new WordResponse();
        Supplier<Stream<String>> streamSupplier = () -> streamScanner(scanner);

        if(!wordToSearch.isPresent()) {
            randomString = streamSupplier.get().skip(randomSkip).findAny().get();
            if(searchOccurrences.isPresent() && searchOccurrences.get().booleanValue()) {
                long count = streamSupplier.get().filter(x -> x.contains(randomString)).count();
                wordResponse.setTotalOccurrences(Long.valueOf(count).intValue()+1);
            }
        } else {
            randomString = streamSupplier.get().filter(line -> line.contains(wordToSearch.get())).findFirst().get();
            if(searchOccurrences.isPresent() && searchOccurrences.get().booleanValue()) {
                long count = streamSupplier.get().filter(line -> line.contains(wordToSearch.get())).count();
                wordResponse.setTotalOccurrences(Long.valueOf(count).intValue()+1);
            }
        }
        populateResponse(randomString,wordResponse);
        return wordResponse;
    }

    public Stream<String> streamScanner(final Scanner scanner) {
        final Spliterator<String> split = Spliterators.spliterator(scanner, Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.NONNULL);
        return StreamSupport.stream(split, false)
                .onClose(scanner::close);
    }

    private WordResponse populateResponse(String randomString, WordResponse wordResponse) {
        wordResponse.setWordSearched(randomString);
        wordResponse.setWordFound(!StringUtils.isEmpty(randomString) ? true : false);
        if(wordResponse.getTotalOccurrences() == null)
            wordResponse.setTotalOccurrences(0);
        return wordResponse;
    }
}
