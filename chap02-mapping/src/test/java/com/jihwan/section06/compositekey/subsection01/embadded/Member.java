package com.jihwan.section06.compositekey.subsection01.embadded;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "member_section06_subsection01")
@Table(name = "tbl_member_section06_subsection01")
public class Member {


    @EmbeddedId
    private MemberPk memberPk;

    @Column
    private String phone;
    @Column
    private String address;

    public Member() {
    }

    public Member(MemberPk memberPk, String phone, String address) {
        this.memberPk = memberPk;
        this.phone = phone;
        this.address = address;
    }

    public MemberPk getMemberPk() {
        return memberPk;
    }

    public void setMemberPk(MemberPk memberPk) {
        this.memberPk = memberPk;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberPk=" + memberPk +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
