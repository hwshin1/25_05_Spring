package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
	public int doJoin(String loginId, String loginPw, String name, String nickName, String email);
}
