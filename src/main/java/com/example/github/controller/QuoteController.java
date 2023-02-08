package com.example.github.controller;

import com.example.github.model.Quote;
import com.example.github.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping
    ResponseEntity<List<Quote>> getAllQuote() {
        return ResponseEntity.ok().body(quoteService.getAllQuotes());
    }

    @GetMapping("/{id}")
    ResponseEntity<Quote> getQuote(@PathVariable Long id) {
        return ResponseEntity.ok().body(quoteService.getQuoteById(id));
    }

    @GetMapping("/random")
    ResponseEntity<Quote> getQuote() {
        return ResponseEntity.ok().body(quoteService.getRandomQuote());
    }

    @GetMapping("/top={cnt}")
    ResponseEntity<List<Quote>> getTopQuotes(@PathVariable int cnt) {
        return ResponseEntity.ok().body(quoteService.getTopQuotes(cnt));
    }

    @GetMapping("/worst")
    ResponseEntity<List<Quote>> getWorstQuotes() {
        return ResponseEntity.ok().body(quoteService.getWorstQuotes());
    }

    @PostMapping
    ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        quote.setCreateDate(Date.valueOf(LocalDate.now()));
        return ResponseEntity.ok().body(quoteService.createQuote(quote));
    }

    @PutMapping("/{id}")
    ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote quote) {
        quote.setQuoteId(id);
        return ResponseEntity.ok().body(quoteService.updateQuote(quote));
    }

    @DeleteMapping("/{id}")
    HttpStatus deleteUser(@PathVariable Long id) {
        this.quoteService.deleteQuote(id);
        return HttpStatus.OK;
    }
}
