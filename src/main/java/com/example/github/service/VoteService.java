package com.example.github.service;

import com.example.github.exceptions.ResourseNotFoundException;
import com.example.github.model.Quote;
import com.example.github.model.User;
import com.example.github.model.Vote;
import com.example.github.model.VotePK;
import com.example.github.repository.QuoteRepository;
import com.example.github.repository.UserRepository;
import com.example.github.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class VoteService {

    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, QuoteRepository quoteRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    public void updateVote(User user, Long quoteId, int val) {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        Optional<Quote> optionalQuote = quoteRepository.findById(quoteId);
        if (optionalUser.isEmpty() || optionalQuote.isEmpty())
            throw new ResourseNotFoundException("Quote of user not found");
        if (Objects.equals(user.getUserId(), optionalQuote.get().getUser().getUserId()))
            throw new IllegalStateException("Do not vote for self quotes");

        Optional<Vote> vote = voteRepository.findByPK(optionalQuote.get(), optionalUser.get());
        if (vote.isEmpty()) {
            var newVote = new Vote();
            newVote.setPk(new VotePK(optionalQuote.get(), optionalUser.get()));
            newVote.setVal(val);
            newVote.setDate(Date.valueOf(LocalDate.now()));
            voteRepository.save(newVote);
            return;
        }

        var newVal = vote.get().getVal() + val;
        if (newVal == 0) {
            voteRepository.delete(vote.get());
            return;
        }

        throw new IllegalStateException("Illegal multiple vote");
    }
}
