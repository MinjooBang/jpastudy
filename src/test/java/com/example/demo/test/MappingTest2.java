package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DataJpaTest
public class MappingTest2 {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void 양방향_emTest(){

        Member member = Member.builder().memberNo(1l).memberId("asaa").build();
        Member memberB = Member.builder().memberNo(2l).memberId("bbbb").build();
        em.persist(member);
        em.persist(memberB);

        ReceiveAgree agree1 =  ReceiveAgree.builder()
                .receiveNo(1l)
                .receiveAgreeDivCd("수신1")
                .receveAgreeYn("Y")
                .build();
        agree1.setMember(member);
        em.persist(agree1);

        ReceiveAgree agree2 = ReceiveAgree.builder()
                .receiveNo(2l)
                .receiveAgreeDivCd("수신2")
                .receveAgreeYn("N")
                .build(); // agree2 -> member1
        agree2.setMember(member);
        em.persist(agree2);

        agree1.setMember(memberB);
        agree1.setReceveAgreeYn("N");

        assertSame(em.contains(member),em.contains(agree1));
        assertSame(em.contains(memberB),em.contains(agree1));
        assertSame(em.contains(memberB),em.contains(agree2));
        assertSame(em.contains(member),em.contains(agree2));

        //조회
        Member findMember =em.find(Member.class,2l);
        ReceiveAgree findReceives = em.find(ReceiveAgree.class,2l);


        assertEquals(agree1.getReceveAgreeYn(),findReceives.getReceveAgreeYn());
        assertEquals(agree1.getMember().getMemberNo(),findMember.getMemberNo());




    }
}
