package com.av.search.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleWordMatcherTest {

    private SimpleWordMatcher matcher;

    @BeforeEach
    public void setUp() {
        if (matcher == null) {
            matcher = new SimpleWordMatcher();
        }
    }

    @Test
    public void testMatchingAllWords() {
        double match = matcher.match("keyword based search", "This text contains keywords that " +
                "could be used for search and rank based on the search");
        assertEquals(100.00, match);
    }

    @Test
    public void testMatchingSomeWords() {
        double match = matcher.match("keyword to search", "This text contains keywords that " +
                "could be used for search and rank based on the search");
        assertEquals(66.67, match);
    }

    @Test
    public void testMatchingNoWords() {
        double match = matcher.match("verb don't match", "This text contains keywords that " +
                "could be used for search and rank based on the search");
        assertEquals(0.0, match);
    }

    @Test
    public void testMatchingNullWords() {
        double match = matcher.match(null, "This text contains keywords that " +
                "could be used for search and rank based on the search");
        assertEquals(0.0, match);
    }

    @Test
    public void testMatchingNullMatch() {
        double match = matcher.match(null, "   ");
        assertEquals(0.0, match);
    }
}