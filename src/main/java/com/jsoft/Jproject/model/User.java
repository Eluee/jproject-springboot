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
    private Integer difflevel;
    private float correct_rate;

    public void setDifflevel(Integer difflevel) {
        this.difflevel = (difflevel != null) ? difflevel : 1; // difflevel이 null이면 1로 설정
    }
}



