package com.example.demo.test;

import com.example.demo.test.mapping.Base;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AttributeOverride(name = "etc" ,column = @Column(name = "member_etc"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends Base {

    @Id
    @Column(name = "member_no")
    private Long memberNo;

    private String memberId;

    @OneToMany(mappedBy = "member" , cascade = CascadeType.REMOVE)
    private List<ReceiveAgree> receiveAgrees = new ArrayList<ReceiveAgree>();

    @Builder
    public Member(Long memberNo,String memberId){
        this.memberNo = memberNo;
        this.memberId = memberId;
    }
}
