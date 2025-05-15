package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public int doJoin(String loginId, String loginPw, String name, String nickName, String email) {
		Member member = getMemberByloginId(loginId);
		
		if (member != null) {
			return -1;
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickName, email);
		return memberRepository.getLastInsertId();
	}

	public Member getMemberByloginId(String loginId) {
		return memberRepository.getMemberByloginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
