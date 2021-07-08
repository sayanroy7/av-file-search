package com.av.search.service;

import com.av.search.model.Directory;
import com.av.search.model.File;
import com.av.search.util.Matcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileSearchServiceTest {

    @Mock
    private Matcher matcher;

    @InjectMocks
    private FileSearchService fileSearchService;

    private static Directory directory;

    @BeforeAll
    public static void init() {
        directory = new Directory(".",
                Map.of("1.txt", new File("1.txt", "Some file content"),
                "2.txt", new File("2.txt", "text file with new lines")));
    }

    @Test
    public void find() {
        when(matcher.match("content", "Some file content")).thenReturn(100.00);
        when(matcher.match("content", "text file with new lines")).thenReturn(0.0);
        var fileRanks = fileSearchService.find("content", directory);
        assertNotNull(fileRanks);
        assertFalse(fileRanks.isEmpty());
        assertEquals(1, fileRanks.size());
        assertEquals(100.0, fileRanks.stream().findAny().get().getMatch());

    }

    @Test
    public void findRanked() {
        when(matcher.match("file content", "Some file content")).thenReturn(100.00);
        when(matcher.match("file content", "text file with new lines")).thenReturn(50.0);
        var fileRanks = fileSearchService.find("file content", directory);
        assertNotNull(fileRanks);
        assertFalse(fileRanks.isEmpty());
        assertEquals(2, fileRanks.size());
        assertEquals(100.0, fileRanks.stream().findFirst().get().getMatch());

    }

    @Test
    public void findMatchNone() {
        when(matcher.match(anyString(), anyString())).thenReturn(0.0);
        when(matcher.match(anyString(), anyString())).thenReturn(0.0);
        var fileRanks = fileSearchService.find("content", directory);
        assertNotNull(fileRanks);
        assertTrue(fileRanks.isEmpty());
    }
}