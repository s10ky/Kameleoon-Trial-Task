package com.example.github.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Date createDate;

    //@Transient
    //private List<Quote> lastVotedQuotes;
}
