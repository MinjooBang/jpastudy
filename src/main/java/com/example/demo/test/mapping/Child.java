package com.example.demo.test.mapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Child {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_id1" , referencedColumnName = "parent_id1"),
            @JoinColumn(name = "parent_id2" , referencedColumnName = "parent_id2")

    })
    private IdParent parent;
}
