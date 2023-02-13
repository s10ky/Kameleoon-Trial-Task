package com.example.github.service;

import com.example.github.exceptions.ResourseNotFoundException;
import com.example.github.model.Quote;
import com.example.github.model.User;
import com.example.github.model.Vote;
import com.example.github.repository.QuoteRepository;
import com.example.github.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    public Quote createQuote(Quote quote) {
        Optional<User> optionalUser = userRepository.findById(quote.getUser().getUserId());
        if (optionalUser.isEmpty())
            throw new ResourseNotFoundException("User not exists");
        quote.setUser(optionalUser.get());
        return quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuoteById(Long quoteId) {
        Optional<Quote> optionalQuote = quoteRepository.findById(quoteId);
        if (optionalQuote.isPresent()) return optionalQuote.get();
        throw new ResourseNotFoundException("Record not found with id = " + quoteId);
    }

    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        var random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }

    public List<Quote> getTopQuotes(int cnt) {
        return quoteRepository.findAll().stream().sorted((q1, q2) -> {
            var r1 = q1.getVotes().stream().map(Vote::getVal).reduce(Integer::sum).orElse(0);
            var r2 = q2.getVotes().stream().map(Vote::getVal).reduce(Integer::sum).orElse(0);
            return Integer.compare(r2, r1);
        }).limit(cnt).toList();
    }


    public List<Quote> getWorstQuotes() {
        return quoteRepository.findWorstTen();
    }

    public Quote updateQuote(Quote quote) {
        Optional<Quote> optionalQuote = quoteRepository.findById(quote.getQuoteId());
        if (optionalQuote.isEmpty()) throw new ResourseNotFoundException("Record not found with id = " + quote.getQuoteId());
        Optional<User> optionalUser = userRepository.findById(quote.getUser() == null ? optionalQuote.get().getUser().getUserId() : quote.getUser().getUserId());
        if (optionalUser.isEmpty()) throw new ResourseNotFoundException("Author not exists");

        Quote quoteUpdate = optionalQuote.get();
        quoteUpdate.setQuoteId(quote.getQuoteId());
        quoteUpdate.setUser(optionalUser.get());
        quoteUpdate.setContent(quote.getContent());
        quoteUpdate.setCreateDate(Date.valueOf(LocalDate.now()));
        quoteRepository.save(quoteUpdate);
        return quoteUpdate;
    }

    public void deleteQuote(Long quoteId) {
        Optional<Quote> optionalQuote = this.quoteRepository.findById(quoteId);
        if (optionalQuote.isPresent()) {
            this.quoteRepository.delete(optionalQuote.get());
        } else {
            throw new ResourseNotFoundException("Record not found with id = " + quoteId);
        }
    }
}
