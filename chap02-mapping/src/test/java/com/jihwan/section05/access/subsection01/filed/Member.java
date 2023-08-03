package com.jihwan.section05.access.subsection01.filed;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "member_section05_subsection01")
@Table(name = "tbl_member_sercion05_subsection01")
//클래스 레벨 설정 방식
@Access(AccessType.FIELD)
public class Member {

    @Id
    @Column(name = "member_no")
    @Access(AccessType.FIELD)
    private int memberNo;


    @Column(name = "member_id", length = 10)
//    @Access(AccessType.FIELD)
    private String memberId;

    @Column(name = "member_pwd")
//    @Access(AccessType.FIELD)
    private String memberPwd;

    @Column(name = "nickname")
//    @Access(AccessType.FIELD)
    private String memberNick;
    //기본값 설정
    @Column(name = "phone", columnDefinition = "varchar(200) default '010-0000-0000'")
//    @Access(AccessType.FIELD)
    private String phone;

    @Column(name = "email", unique = true)
//    @Access(AccessType.FIELD)
    private String email;

    @Column(name = "address", nullable = false)
//    @Access(AccessType.FIELD)
    private String address;

    @Column(name = "enroll_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollDate;

    @Column(name = "member_role")
//    @Enumerated(EnumType.ORDINAL) //전달된 enum의 숫자를 데이터 베이스에 저장한다.
    @Enumerated(EnumType.STRING) //
    private RoleType memberRole;

    @Column(name = "status")
    private String status;

    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String memberNick, String phone, String email, String address, Date enrollDate, RoleType memberRole, String status) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberNick = memberNick;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    public int getMemberNo() {

        System.out.println("getNo");
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        System.out.println("getId");
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        System.out.println("getPwd");
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberNick() {
        return memberNick;
    }

    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public RoleType getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(RoleType memberRole) {
        this.memberRole = memberRole;
    }

    public String getStatus() {
        System.out.println("getStatus");
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberNick='" + memberNick + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", enrollDate=" + enrollDate +
                ", memberRole=" + memberRole +
                ", status='" + status + '\'' +
                '}';
    }
}
