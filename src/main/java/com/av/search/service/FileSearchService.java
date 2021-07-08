package com.av.search.service;

import com.av.search.model.Directory;
import com.av.search.model.FileRank;
import com.av.search.util.Matcher;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FileSearchService implements SearchService<Directory, FileRank> {

    private final Matcher matcher;

    @Override
    public Collection<FileRank> find(String input, Directory directory) {
        return directory.getFiles().values().parallelStream().map(fe -> {
            var matchPercentage = matcher.match(input, fe.getContent());
            return FileRank.builder().file(fe)
                    .match(matchPercentage).build();
        }).filter(fr -> fr.getMatch() > 0.0)
                .sorted(Comparator.comparing(FileRank::getMatch).reversed())
                .limit(10).collect(Collectors.toList());
    }
}
