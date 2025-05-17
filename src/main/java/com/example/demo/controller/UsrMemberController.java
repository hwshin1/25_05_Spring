package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickName, String email) {
		// 입력하는 값이 비어 있을때 예외처리
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", Ut.f("아이디를 입력해주세요."));
		}
		
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", Ut.f("비밀번호를 입력해주세요."));
		}
		
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", Ut.f("이름을 입력해주세요."));
		}
		
		if (Ut.isEmptyOrNull(nickName)) {
			return ResultData.from("F-4", Ut.f("닉네임을 입력해주세요."));
		}
		
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-5", Ut.f("이메일을 입력해주세요."));
		}
		
		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickName, email);
		
		Member member = memberService.getMemberById((int) doJoinRd.getData1());
		
		return ResultData.newData(doJoinRd, member);
	}
}
