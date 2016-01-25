package com.ronaldong.messi.data.entity.remote;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Update {
	public long lut;
	public String deleted;

	public String hasInstalled;

	public Long id;
	public String name;
	public String avatar;

	public Long parentId;
	public Integer indexOrder;
	public String jobNum;

	public String mobileNum;
	public String workNum;
	public String shortNum;
	public String room;
	public String email;
	public String duty;
	public String gender;
	public String signature;

	public Long chatId;
	public Long targetId;
	public String targetType;
	public String title;
	public String content;
	public String silent;

	public Long groupId;
	public Long userId;
	public String role;
	public String isPublic;
	public String isTmp;
	public String groupType;
	public String organizeType;
	public String tagId;
	public int personType;
	public int isFriend;
	public int isBlack;
	public String canEdit;

	public Update() {
	}

}
