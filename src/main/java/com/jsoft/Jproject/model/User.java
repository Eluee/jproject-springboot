package com.jsoft.Jproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "jusers")
@Data
public class User {
    @Id
    private String email;
    private String name;
    private int difflevel;
    private float correct_rate;
}

