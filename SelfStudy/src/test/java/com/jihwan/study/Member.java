package com.jihwan.study;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "study_member")
@Table(name = "stu_member")
@DynamicInsert
public class Member {

    @Id
    @Column(name = "member_no" ,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    @Column(name = "member_name",nullable = false)
    private String name;

    @Column(name = "nickname", length = 10 ,unique = true)
    private String nickName;
    @Column(name = "phone")
    @ColumnDefault("'010-0000-0000'")
    private String phone;

    @Column(name = "address_num" , nullable = false)
    private String addressNum;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name ="detail_address",nullable = false )
    private String detailAddress;

    @Column(name = "role" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Column(name = "status" ,nullable = false )
    private String status;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;


    @Column(name = "delteDate")
    @Temporal(TemporalType.DATE)
    private Date deleteDate;


    public Member() {
    }

    public Member(int memberNo, String name, String nickName, String phone, String addressNum, String address, String detailAddress, RoleType roleType, String status, Date createDate, Date modifyDate, Date deleteDate) {
        this.memberNo = memberNo;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.addressNum = addressNum;
        this.address = address;
        this.detailAddress = detailAddress;
        this.roleType = roleType;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressNum() {
        return addressNum;
    }

    public void setAddressNum(String addressNum) {
        this.addressNum = addressNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", addressNum='" + addressNum + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", roleType=" + roleType +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
