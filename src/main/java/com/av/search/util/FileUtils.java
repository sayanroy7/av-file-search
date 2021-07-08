package com.av.search.util;

import com.av.search.FileReadingException;
import com.av.search.model.Directory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    public static Directory scanDir(String pathToIndexDir) {
        if (pathToIndexDir == null || pathToIndexDir.isBlank()) {
            throw new IllegalArgumentException("path to directory is empty or null");
        }
        try (Stream<Path> s = Files.list(Paths.get(pathToIndexDir))) {
            var files = s.parallel().filter(Predicate.not(Files::isDirectory))
                    .filter(path -> path.getFileName().toString().endsWith(".txt")).map(fl -> {
                        String content;
                        try (Stream<String> lines = Files.lines(fl,  StandardCharsets.UTF_8)){
                            content = lines.collect(Collectors.joining());
                            //System.out.println("The content: "+ content);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new FileReadingException("Error reading from file: "+ fl.getFileName().toString(), e);
                        }
                        return new com.av.search.model.File(fl.getFileName().toString(), content);
                    }).collect(Collectors.toConcurrentMap(com.av.search.model.File::getFileName, Function.identity()));
            return new Directory(pathToIndexDir, files);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileReadingException("Unable to index directory: "+ pathToIndexDir, e);
        }
    }

}
