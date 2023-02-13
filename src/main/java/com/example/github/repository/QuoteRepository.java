package com.example.github.repository;

import com.example.github.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "select * from v_top_worst_quotes q", nativeQuery = true)
    List<Quote> findWorstTen();
}
