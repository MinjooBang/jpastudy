package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.assertEquals;


@SpringBootTest
@Rollback(false)
public class MappingTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReceiveAgreeRepository receiveRepository;


    @Test
    @Transactional
    @Rollback(false)
    public void 양방향_test_3() {

        Member member = Member.builder().memberNo(1l).memberId("asaa").build();
        Member memberB = Member.builder().memberNo(2l).memberId("bbbb").build();
        memberRepository.save(member);
        memberRepository.save(memberB);

        ReceiveAgree agree1 = ReceiveAgree.builder()
                .receiveAgreeDivCd("수신1")
                .receveAgreeYn("Y")
                .build();
        agree1.setMember(member);
        receiveRepository.save(agree1);

        ReceiveAgree agree2 = ReceiveAgree.builder()
                .receiveAgreeDivCd("수신2")
                .receveAgreeYn("Y")
                .build(); // agree2 -> member1
        agree2.setMember(member);
        receiveRepository.save(agree2);
        //영속성 관리 여부
        agree1.setReceveAgreeYn("N");
        agree1.setMember(memberB);
        //receiveRepository.flush();
        //receiveRepository.save(agree1);
        ReceiveAgree findagree = receiveRepository.findById(1l).get();

        assertEquals(agree1.getReceveAgreeYn(), findagree.getReceveAgreeYn());
        assertEquals(agree1.getMember().getMemberNo(), findagree.getMember().getMemberNo());

        System.out.println("member agree size = " + member.getReceiveAgrees().size()); // 2-> 1
        System.out.println("memberB agree size = " + memberB.getReceiveAgrees().size());


       /* assertEquals(agree1.getReceveAgreeYn(),receiveRepository.findById(1L).get().getReceveAgreeYn());
        assertEquals(agree1.getMember().getMemberNo(),receiveRepository.findById(1L).get().getMember().getMemberNo());*/

    }


}