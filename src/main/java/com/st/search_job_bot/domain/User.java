package com.st.search_job_bot.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "looking_job")
    String looking_job;

    @Column(name = "experience")
    int experience;

    @Column(name = "last_name")
    String username;

    @Column(name = "location")
    String location;


}
