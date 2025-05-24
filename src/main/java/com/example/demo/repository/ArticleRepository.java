package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	public int writeArticle(int loginedMemberId, String title, String body, String boardId);
	
	public Article getArticleById(int id);
	
	public void modifyArticle(int id, String title, String body);
	
	public void deleteArticle(int id);
	
	public List<Article> getArticles();

	public int getLastInsertId();

	public Article getForPrintArticle(int id);

	public int getArticleCount(@Param("boardId") int boardId, @Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword);

	public List<Article> getForPrintArticles(@Param("boardId") int boardId, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake, @Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword);
}
