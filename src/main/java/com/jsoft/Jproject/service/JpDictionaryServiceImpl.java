package com.jsoft.Jproject.service;

import com.jsoft.Jproject.model.JpDictionary;
import com.jsoft.Jproject.repository.JpDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class JpDictionaryServiceImpl implements JpDictionaryService {
    private final JpDictionaryRepository jpDictionaryRepository;

    @Autowired
    public JpDictionaryServiceImpl(JpDictionaryRepository jpDictionaryRepository) {
        this.jpDictionaryRepository = jpDictionaryRepository;
    }

    @Override
    public List<JpDictionary> getRandomWordsByDifflevel(int difflevel, int count) {
        List<JpDictionary> words = jpDictionaryRepository.findByDifflevel(difflevel);
        
        // Shuffle and limit to the requested count
        Collections.shuffle(words, new Random());
        return words.stream().limit(count).toList();
    }
}
