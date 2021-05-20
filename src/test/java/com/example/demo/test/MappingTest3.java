package com.example.demo.test;

import com.example.demo.test.mapping.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Rollback(false)
public class MappingTest3 {

    @PersistenceContext
    EntityManager em;

    @Test
    public void joinTest() {
        Album album = new Album();
        album.setArtist("가수");
        album.setName("가수1");
        album.setPrice(1000);
        em.persist(album);

        Movie movie = new Movie();
        movie.setActor("배우");
        movie.setName("배우1");
        movie.setPrice(3000);
        movie.setDirector("감독");
        em.persist(movie);

    }

    @Test
    public void superClassTest() {
        Member member = new Member();
        member.setMemberId("aaa");
        member.setMemberNo(1l);

        em.persist(member);

    }

    @Test
    public void parentTest(){
        IdParent parent = new IdParent();
        parent.setId1("myId11");
        parent.setId2("myId12");
        parent.setName("ParentName");
        em.persist(parent);
        ParentId parentId = new ParentId("myId11","myId12");
        IdParent findParent = em.find(IdParent.class , parentId);

        Child child = new Child();
        child.setId(1l);
        child.setParent(findParent);

    }
}
