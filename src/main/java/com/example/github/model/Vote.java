package com.example.github.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity(name = "votes")
public class Vote {

    @EmbeddedId
    @JsonIgnore
    private VotePK pk;

    private int val;

    private Date date;
}
