package com.av.search;

import com.av.search.model.Directory;
import com.av.search.model.FileRank;
import com.av.search.service.FileSearchService;
import com.av.search.service.SearchService;
import com.av.search.util.FileUtils;
import com.av.search.util.Matcher;
import com.av.search.util.SimpleWordMatcher;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleFileSearchApplication {

    private static final String NO_MATCH_RESULT = "no matches found";

    private static final String MATCH_RESULT = "%s : %s%%";

    private static final String INDEX_MESSAGE = "%s files read in directory %s";

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        final Matcher matcher = new SimpleWordMatcher();
        final SearchService<Directory, FileRank> fileRankSearchService = new FileSearchService(matcher);
        final String pathToDirectory = args[0];
        final Directory directory = FileUtils.scanDir(pathToDirectory);
        System.out.printf(INDEX_MESSAGE + "\n", directory.getFiles().size(), pathToDirectory);
        try (Scanner keyboard = new Scanner(System.in)) {
            while (true) {
                System.out.print("search> ");
                final String line = keyboard.nextLine();
                Collection<FileRank> fileRanks = new ArrayList<>();
                if (!line.isBlank()) {
                    if (line.equals(":quit") || line.equals(":q")) {
                        break;
                    }
                    fileRanks = fileRankSearchService.find(line, directory);
                }
                if (fileRanks.isEmpty()) {
                    System.out.println(NO_MATCH_RESULT);
                } else {
                    fileRanks.forEach(fr -> System.out.printf((MATCH_RESULT) + "\n",
                            fr.getFile().getFileName(), fr.getMatch()));
                }
            }
        }
    }

}
