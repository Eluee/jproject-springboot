package com.jsoft.Jproject.controller;

import com.jsoft.Jproject.model.JpDictionary;
import com.jsoft.Jproject.service.JpDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpdictionary")
@CrossOrigin(origins = "http://localhost:3000") // React 앱의 주소
public class JpDictionaryController {

    private final JpDictionaryService jpDictionaryService;

    @Autowired
    public JpDictionaryController(JpDictionaryService jpDictionaryService) {
        this.jpDictionaryService = jpDictionaryService;
    }

    // 해당 레벨의
    @GetMapping("/difflevel/{difflevel}")
    public ResponseEntity<List<JpDictionary>> getRandomWords(@PathVariable int difflevel, @RequestParam(defaultValue = "20") int count) {
        List<JpDictionary> words = jpDictionaryService.getRandomWordsByDifflevel(difflevel, count);
        return ResponseEntity.ok(words);
    }
}
