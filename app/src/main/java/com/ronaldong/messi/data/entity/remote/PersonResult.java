package com.ronaldong.messi.data.entity.remote;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonResult extends Result {

	public long id;

	public String loginId;

	public String password;

	public String name;

	public String email;

	public String number;// 教师工号，学生学�?

	public String job;// 职务

	public String cellPhone;// 个人电话

	public String shortPhone;// 短号

	public String officePhone;// 办公电话

	public String room;// 房间�?

	public String intro;// 个�?签名

	public String accessToken;// oauth2.0

	public String pinyinName;// 拼音

	public String sortName;// name:"张三"--->sortName:"zhang �?  san �?

	public Date entryDate;// 入职时间

	public String avatarURL;// 头像地址

	public String type;// 类型 SysAdmin|Teacher｜Student

	public String sex;// 性别 男｜女｜未填�?

	public String department;// 部门
	public String depart;//班级

	public String role;// 角色 管理员|普�?成员

	public String subject;// 学科
	public String hasInstalled;// 是否已经安装移动客户�?
	public int personType;
	public int isFriend;//是否好友
	public int isBlack;//是否黑名�?
	public String college;//学院

	public boolean isStudent() {
		return "Student".equals(this.type);
	}

	public boolean isTeacher() {
		return "Teacher".equals(this.type);
	}

	public boolean isSysAdmin() {
		return "SysAdmin".equals(this.type);
	}
}
