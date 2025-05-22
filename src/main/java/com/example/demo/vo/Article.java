package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String title;
	private String body;
	
	private String extra__write;
	private boolean userCanModify;
	private boolean userCanDelete;

	public Article(String title, String body) {
		this.title = title;
		this.body = body;
	}
}
