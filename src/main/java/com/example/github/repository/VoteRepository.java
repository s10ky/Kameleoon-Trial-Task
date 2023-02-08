package com.example.github.repository;

import com.example.github.model.Quote;
import com.example.github.model.User;
import com.example.github.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select v from votes v where v.pk.quote = ?1 and v.pk.user = ?2")
    Optional<Vote> findByPK(Quote quote, User user);
}
