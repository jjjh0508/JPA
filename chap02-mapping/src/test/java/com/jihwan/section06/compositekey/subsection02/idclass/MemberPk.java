package com.jihwan.section06.compositekey.subsection02.idclass;



import java.io.Serializable;
import java.util.Objects;

//복합키를 지정 할 때 사용한다.

public class MemberPk implements Serializable {

    private int memberNo;

    private String memberId;

    public MemberPk() {
    }

    public MemberPk(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }


    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "MemberPk{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberPk memberPk = (MemberPk) o;
        return memberNo == memberPk.memberNo && Objects.equals(memberId, memberPk.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }
}
