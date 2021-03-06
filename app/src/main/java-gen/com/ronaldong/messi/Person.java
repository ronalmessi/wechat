package com.ronaldong.messi;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PERSON".
 */
public class Person {

    private Long id;
    private String name;
    private String pinyin;
    private String avatar;
    private String room;
    private String hasInstalled;
    private Integer personType;
    private String mobileNum;
    private String workNum;
    private String shortNum;
    private String jobNum;
    private String email;
    private String duty;
    private String gender;
    private String deleted;
    private Long lut;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String name, String pinyin, String avatar, String room, String hasInstalled, Integer personType, String mobileNum, String workNum, String shortNum, String jobNum, String email, String duty, String gender, String deleted, Long lut) {
        this.id = id;
        this.name = name;
        this.pinyin = pinyin;
        this.avatar = avatar;
        this.room = room;
        this.hasInstalled = hasInstalled;
        this.personType = personType;
        this.mobileNum = mobileNum;
        this.workNum = workNum;
        this.shortNum = shortNum;
        this.jobNum = jobNum;
        this.email = email;
        this.duty = duty;
        this.gender = gender;
        this.deleted = deleted;
        this.lut = lut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getHasInstalled() {
        return hasInstalled;
    }

    public void setHasInstalled(String hasInstalled) {
        this.hasInstalled = hasInstalled;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getShortNum() {
        return shortNum;
    }

    public void setShortNum(String shortNum) {
        this.shortNum = shortNum;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Long getLut() {
        return lut;
    }

    public void setLut(Long lut) {
        this.lut = lut;
    }

}
