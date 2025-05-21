package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickName, String email) {
		Member member = getMemberByloginId(loginId);
		
		if (member != null) {
			return ResultData.from("F-6", Ut.f("이미 가입된 아이디(%s) 입니다.", loginId));
		}
		
		member = getMemberByNameAndEmail(name, email);
		
		if (member != null) {
			return ResultData.from("F-7", Ut.f("이미 사용중인 이름(%s)과 이메일(%s) 입니다.", name, email));
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickName, email);
		
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("회원가입이 완료되었습니다.", id), "가입 성공 id", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByloginId(String loginId) {
		return memberRepository.getMemberByloginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
