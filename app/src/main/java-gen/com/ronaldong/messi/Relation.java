package com.ronaldong.messi;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "RELATION".
 */
public class Relation {

    private Long id;
    private Integer indexOrder;
    private String deleted;
    private Long groupId;
    private Long userId;
    private String role;
    private String jobNum;
    private Long lut;

    public Relation() {
    }

    public Relation(Long id) {
        this.id = id;
    }

    public Relation(Long id, Integer indexOrder, String deleted, Long groupId, Long userId, String role, String jobNum, Long lut) {
        this.id = id;
        this.indexOrder = indexOrder;
        this.deleted = deleted;
        this.groupId = groupId;
        this.userId = userId;
        this.role = role;
        this.jobNum = jobNum;
        this.lut = lut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndexOrder() {
        return indexOrder;
    }

    public void setIndexOrder(Integer indexOrder) {
        this.indexOrder = indexOrder;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public Long getLut() {
        return lut;
    }

    public void setLut(Long lut) {
        this.lut = lut;
    }

}
