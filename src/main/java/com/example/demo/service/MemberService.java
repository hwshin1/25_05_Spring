package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public Member doJoin(String loginId, String loginPw, String name, String nickName, String email) {
		memberRepository.doJoin(loginId, loginPw, name, nickName, email);
		return new Member(loginId, loginPw, name, nickName, email);
	}
}
