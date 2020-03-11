package com.thinkhr.app.response;

public class WordResponse {
    private boolean wordFound;
    private String wordSearched;
    private Integer totalOccurrences;

    public WordResponse() {}

    public boolean isWordFound() {
        return wordFound;
    }

    public void setWordFound(boolean wordFound) {
        this.wordFound = wordFound;
    }

    public String getWordSearched() {
        return wordSearched;
    }

    public void setWordSearched(String wordSearched) {
        this.wordSearched = wordSearched;
    }

    public Integer getTotalOccurrences() {
        return totalOccurrences;
    }

    public void setTotalOccurrences(Integer totalOccurrences) {
        this.totalOccurrences = totalOccurrences;
    }
}
