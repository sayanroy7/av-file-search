package com.av.search.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileRank {

    private File file;

    private Double match;

}
