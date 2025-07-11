package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.interceptor.BeforeArticleInterceptor;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {
	
	private final BeforeArticleInterceptor beforeArticleInterceptor;
	
	@Autowired
	private Rq rq;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private BoardService boardService;
	
	public UsrArticleController(BeforeArticleInterceptor beforeArticleInterceptor) {
		this.beforeArticleInterceptor = beforeArticleInterceptor;
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req) {
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body, String boardId) {
		rq = (Rq) req.getAttribute("rq");
		
		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-A", Ut.f("로그인 하고 사용하세요."));
		}
		
		if (Ut.isEmptyOrNull(title)) {
			return Ut.jsHistoryBack("F-1", Ut.f("제목을 입력하세요."));
		}
		
		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-2", Ut.f("내용을 입력하세요."));
		}
		
		if (Ut.isEmptyOrNull(boardId)) {
			return Ut.jsHistoryBack("F-3", Ut.f("게시판을 선택하세요"));
		}
		
		ResultData writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);
		
		int id = (int) writeArticleRd.getData1();
		
		Article article = articleService.getArticleById(id);
		
		return Ut.jsReplace(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), "/usr/article/detail?id=" + id);
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles(HttpServletRequest req, Model model, @RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "title") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword) throws IOException {
		rq = (Rq) req.getAttribute("rq");
		
		Board board = boardService.getBoardById(boardId);
		
		if (board == null) {
			return rq.historyBackOnView("존재하지 않는 게시판");
		}
		
		int articlesCount = articleService.getArticleCount(boardId, searchKeywordTypeCode, searchKeyword);
		
		int itemsInAPage = 10;
		
		int pagesCount = (int) Math.ceil(articlesCount / (double) itemsInAPage);
		
		List<Article> articles = articleService.getForPrintArticles(boardId, itemsInAPage, page, searchKeywordTypeCode, searchKeyword);
		
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("searchKeywordTypeCode", searchKeywordTypeCode);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("boardId", boardId);
		model.addAttribute("page", page);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		model.addAttribute("article", article);
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		model.addAttribute("article", article);
		
		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		rq = (Rq) req.getAttribute("rq");
		
		if (rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-A", Ut.f("로그인이 필요한 서비스 입니다."));
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);
		
		articleService.modifyArticle(id, title, body);
		
		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/detail?id=" + id);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		rq = (Rq) req.getAttribute("rq");
		
		if (rq.isLogined() == false) {
			return Ut.jsReplace("F-A", "로그인이 필요한 서비스 입니다.", "../member/login");
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);
		
		if (userCanDeleteRd.isFail()) {
			return Ut.jsHistoryBack(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
		}
		
		if (userCanDeleteRd.isSuccess()) {
			articleService.deleteArticle(id);
		}
		
		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");
	}
}
