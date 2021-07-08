package com.av.search.util;

import java.util.Arrays;

public class SimpleWordMatcher implements Matcher {

    @Override
    public double match(String search, String content) {
        if (search == null || search.isBlank() || content == null || content.isBlank()) {
            return 0.0;
        }
        final String[] words = search.split("\\s");
        int searchSize = words.length;
        long matchCounter = Arrays.stream(words).filter(content::contains).count();
        var matchPercentage = (double) (matchCounter * 100) / searchSize;
        return Math.round(matchPercentage * 100.0) / 100.0;
    }
}

// This is a search String m - O(m)

// content = n
// word = w

// O(m) + O (w*n)