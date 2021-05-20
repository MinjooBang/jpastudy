package com.example.demo.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DataJpaTest
@Rollback(false)
public class MemberTest {

    @PersistenceContext
    EntityManager em;


    @Test
    @DisplayName("엔티티 조회")
   public void selectTest(){

        Member member = new Member();
        member.setMemberNo(1l);
        member.setMemberId("회원1");

        em.persist(member);
        Member findMember = em.find(Member.class,1l);

        // 동등성 (equality)
        assertEquals(findMember, member);
        // 동일성 (identity)
        assertSame(findMember, member);
        System.out.println("findMember "+findMember);
        System.out.println("Member "+member);

    }

    @Test
    @DisplayName("엔티티 등록")
    public void insertTest(){

        Member memberA = new Member();
        memberA.setMemberNo(1L);
        memberA.setMemberId("쓰기회원");

        Member memberB = new Member();
        memberB.setMemberNo(2L);
        memberB.setMemberId("회원");

        System.out.println("---------1");
        em.persist(memberA);
        System.out.println("---------2");
        em.persist(memberB);
        System.out.println("---------3");


    }

    @Test
    @DisplayName("엔티티 수정")
    public void updateTest(){

        Member memberA = new Member();
        memberA.setMemberNo(1L);
        memberA.setMemberId("수정");

        em.persist(memberA);

        memberA.setMemberId("수정2");

        Member findMember = em.find(Member.class,1L);

        System.out.println(findMember.getMemberId());
    }

    @Test
    @DisplayName("엔티티 삭제")
    public void deleteTest(){

        Member memberA = new Member();
        memberA.setMemberNo(1L);
        memberA.setMemberId("삭제");

        em.persist(memberA);

        Member deleteMember = em.find(Member.class,1l);
        em.remove(deleteMember);

        System.out.println(deleteMember.getMemberId());
    }

    @Test
    @DisplayName("플러시")
    public void flushTest(){

        Member memberA = new Member();
        memberA.setMemberNo(1L);
        memberA.setMemberId("저장1");

        Member memberB = new Member();
        memberB.setMemberNo(2L);
        memberB.setMemberId("저장2");

        System.out.println("--------1");
        em.persist(memberA);
        System.out.println("--------flush");
        em.flush();
        System.out.println("--------2");
        em.persist(memberB);
        System.out.println("--------3");


    }

    @Test
    @DisplayName("준영속")
    public void detachTest(){
        Member memberA = new Member();
        memberA.setMemberNo(1L);
        memberA.setMemberId("저장1");

        em.persist(memberA);
        //em.detach(memberA);
        em.clear();
        em.close();
        memberA.setMemberId("수정");
    }
}
