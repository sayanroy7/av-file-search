package com.av.search.service;

import java.util.Collection;

public interface SearchService<SOURCE, RANK> {

    Collection<RANK> find(String input, SOURCE source);

}
