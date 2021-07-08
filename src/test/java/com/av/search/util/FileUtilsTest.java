package com.av.search.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

    @Test
    public void scanDir() {
        var pathToDir = "src/test/resources/txt";
        var dir = FileUtils.scanDir(pathToDir);
        assertNotNull(dir);
        assertEquals(pathToDir, dir.getPathName());
        assertEquals(1, dir.getFiles().size());
        assertTrue(dir.getFiles().values().stream().findAny().isPresent());
    }

    @Test
    public void scanDirNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> FileUtils.scanDir(null));
        assertEquals("path to directory is empty or null", exception.getMessage());
    }
}