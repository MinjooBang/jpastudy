package com.example.demo.test.mapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Setter
@Getter
@Entity
@DiscriminatorValue("M")
@PrimaryKeyJoinColumn(name = "m_id")
public class Movie extends Item {
    private String director;
    private String actor;

}
