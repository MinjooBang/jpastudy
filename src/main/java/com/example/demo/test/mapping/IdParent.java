package com.example.demo.test.mapping;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Entity
@IdClass(ParentId.class)
public class IdParent {
    @Id
    @Column(name = "parent_id1")
    private String id1;
    @Id
    @Column(name = "parent_id2")
    private String id2;

    private String name;
}
