package me.socuri.modules.test.member;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    /*@Column(name="member_id")
    private Long memberId;*/

    private String no;

    //@ManyToOne(targetEntity = Member.class,fetch = FetchType.LAZY)
    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    public Member member;

}
