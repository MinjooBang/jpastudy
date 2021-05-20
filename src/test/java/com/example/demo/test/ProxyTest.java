package com.example.demo.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Rollback(false)
public class ProxyTest {

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void insertDummyData() {
        Member member = Member.builder().memberNo(1l).memberId("asaa").build();
        em.persist(member);

        ReceiveAgree agree1 =  ReceiveAgree.builder()
                .receiveNo(1l)
                .receiveAgreeDivCd("수신1")
                .receveAgreeYn("Y")
                .build();
        agree1.setMember(member);
        ReceiveAgree agree2 = ReceiveAgree.builder()
                .receiveNo(2l)
                .receiveAgreeDivCd("수신2")
                .receveAgreeYn("N")
                .build(); // agree2 -> member1
        agree2.setMember(member);
        em.persist(agree1);
        em.persist(agree2);
        em.flush();
        em.clear();
    }

    @Test
    void proxytest(){
        ReceiveAgree agree = em.find(ReceiveAgree.class,1L);
        Member member = agree.getMember();
        //lazy로딩을 추천하는 이유??

    }

    @Test
    void removeTest(){
        Member member = em.find(Member.class,1l);
        em.remove(member);
    }


}
