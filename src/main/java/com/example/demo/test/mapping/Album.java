package com.example.demo.test.mapping;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;


}
