package com.jsoft.Jproject.service;

import com.jsoft.Jproject.model.JpDictionary;

import java.util.List;

public interface JpDictionaryService {
    List<JpDictionary> getRandomWordsByDifflevel(int difflevel, int count);
}
