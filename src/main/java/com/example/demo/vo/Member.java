package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickName;
	private String email;
	private Integer delStatus;
	private LocalDateTime delDate;

	public Member(String loginId, String loginPw, String name, String nickName, String email) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.nickName = nickName;
		this.email = email;
	}
}
