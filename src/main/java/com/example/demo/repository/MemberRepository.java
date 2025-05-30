package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {
	public int doJoin(String loginId, String loginPw, String name, String nickName, String email);

	public Member getMemberByloginId(String loginId);

	public Member getMemberById(int id);

	public int getLastInsertId();

	public Member getMemberByNameAndEmail(String name, String email);
}
