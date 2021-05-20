package com.example.demo.test;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReceiveAgree {

    @Id
    @Column(name = "receive_no")
    private Long receiveNo;

    private String receiveAgreeDivCd;

    private String receveAgreeYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    public void setMember(Member member){

        if(this.member != null){
            this.member.getReceiveAgrees().remove(this);
        }
        this.member = member;
        member.getReceiveAgrees().add(this);
    }

    @Builder
    public ReceiveAgree(Long receiveNo,String receiveAgreeDivCd,String receveAgreeYn,Member member){
        this.receiveNo = receiveNo;
        this.receiveAgreeDivCd = receiveAgreeDivCd;
        this.receveAgreeYn = receveAgreeYn;
        this.member = member;
    }
}
