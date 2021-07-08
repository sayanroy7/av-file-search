package com.av.search.model;

import lombok.Data;

import java.util.Map;

@Data
public class Directory {
        private final String pathName;
        private final Map<String, File> files;
    }