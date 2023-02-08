package com.example.github.controller;

import com.example.github.model.User;
import com.example.github.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/quote")
public class VoteController {

    @Autowired
    VoteService voteService;

    @PutMapping("/{id}/vote{type}")
    HttpStatus upvoteQuote(@PathVariable Long id, @PathVariable String type, @RequestBody User user) {
        var val = type.equalsIgnoreCase("up") ? 1 : -1;
        voteService.updateVote(user, id, val);
        return HttpStatus.ACCEPTED;
    }
}
