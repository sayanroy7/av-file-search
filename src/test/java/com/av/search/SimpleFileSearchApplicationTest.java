package com.av.search;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleFileSearchApplicationTest {

    @Test
    public void mainNoFile() throws IOException {
        final InputStream original = System.in;
        final InputStream is = Files.newInputStream(Paths.get("src/test/resources/commands/q.txt"));
        System.setIn(is);
        IllegalArgumentException err = assertThrows(IllegalArgumentException.class,
                () -> SimpleFileSearchApplication.main(new String[]{}));
        assertEquals("No directory given to index.", err.getMessage());
        System.setIn(original);
    }

    @Test
    public void mainQuit() throws IOException {
        final InputStream original = System.in;
        final InputStream is = Files.newInputStream(Paths.get("src/test/resources/commands/q.txt"));
        System.setIn(is);
        SimpleFileSearchApplication.main(new String[]{"src/test/resources/txt"});
        System.setIn(original);
    }

    @Test
    public void mainSearch() throws IOException {
        final InputStream original = System.in;
        final InputStream is = Files.newInputStream(Paths.get("src/test/resources/commands/search.txt"));
        System.setIn(is);
        SimpleFileSearchApplication.main(new String[]{"src/test/resources/txt"});
        System.setIn(original);
    }

    @Test
    public void mainSearchNotFound() throws IOException {
        final InputStream original = System.in;
        final InputStream is = Files.newInputStream(Paths.get("src/test/resources/commands/search1.txt"));
        System.setIn(is);
        SimpleFileSearchApplication.main(new String[]{"src/test/resources/txt"});
        System.setIn(original);
    }
}