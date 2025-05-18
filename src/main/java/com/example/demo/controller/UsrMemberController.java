package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(HttpSession session, String loginId, String loginPw, String name, String nickName, String email) {
		boolean isLogined = false;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if (isLogined) {
			return ResultData.from("F-A", Ut.f("이미 로그인 하였습니다."));
		}
		
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
		
		return ResultData.newData(doJoinRd, "등록된 회원", member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {
		boolean isLogined = false;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if (isLogined) {
			return ResultData.from("F-A", Ut.f("이미 로그인 하였습니다."));
		}
		
		// 입력하는 값이 비어 있을때 예외처리
		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", Ut.f("아이디를 입력해주세요."));
		}
		
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", Ut.f("비밀번호를 입력해주세요."));
		}
		
		Member member = memberService.getMemberByloginId(loginId);
		
		if (member == null) {
			return ResultData.from("F-3", Ut.f("%s는 없는 아이디 입니다.", loginId));
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", Ut.f("비밀번호가 일치하지 않습니다."));
		}
		
		session.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("S-1", Ut.f("%s님 환영합니다!", member.getNickName()), "로그인 한 회원", member);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		boolean isLogined = false;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if (!isLogined) {
			return ResultData.from("F-A", Ut.f("이미 로그아웃 하였습니다."));
		}
		
		session.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1", Ut.f("로그아웃 되었습니다."));
	}
}
