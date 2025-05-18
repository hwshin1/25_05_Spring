package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(HttpSession session, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		if (isLogined == false) {
			return ResultData.from("F-A", Ut.f("로그인 하고 사용하세요."));
		}
		
		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", Ut.f("제목을 입력하세요."));
		}
		
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", Ut.f("내용을 입력하세요."));
		}
		
		ResultData writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);
		
		int id = (int) writeArticleRd.getData1();
		
		Article article = articleService.getArticleById(id);
		
		return ResultData.newData(writeArticleRd, "새로 작성된 게시글", article);
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", Ut.f("Article List"), "전체 게시글 리스트", articles);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		return ResultData.from("S-1", Ut.f("%d번 게시글 입니다.", id), "상세 게시글 보기", article);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		ResultData loginedMemberCanModifyRd = articleService.loginedMemberCanModify(loginedMemberId, article);
		
		articleService.modifyArticle(id, title, body);
		
		return ResultData.from(loginedMemberCanModifyRd.getResultCode(), loginedMemberCanModifyRd.getMsg(), "수정된 글", article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", "권한없음");
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("S-1", Ut.f("게시글이 삭제되었습니다."));
	}
}
