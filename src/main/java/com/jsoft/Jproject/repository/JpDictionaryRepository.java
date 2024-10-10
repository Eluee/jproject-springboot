package com.jsoft.Jproject.repository;

import com.jsoft.Jproject.model.JpDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpDictionaryRepository extends JpaRepository<JpDictionary, Long> {
    List<JpDictionary> findByDifflevel(int difflevel);
}
