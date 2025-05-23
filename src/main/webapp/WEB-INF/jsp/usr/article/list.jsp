<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="${board.code } LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-24 text-xl px-4">
	<div class="mx-auto">
		<div>${articlesCount }개</div>
		<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<thead>
				<tr>
					<th style="text-align: center;">번호</th>
					<th style="text-align: center;">작성 날짜</th>
					<th style="text-align: center;">제목</th>
					<th style="text-align: center;">작성자 닉네임</th>
					<th style="text-align: center;">게시판</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr class="hover:bg-base-300">
						<td style="text-align: center;">${article.id }</td>
						<td style="text-align: center;">${article.regDate.substring(0,10) }</td>
						<td style="text-align: center;"><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a></td>
						<td style="text-align: center;">${article.extra__write }</td>
						<td style="text-align: center;">${article.boardName }</td>
					</tr>
				</c:forEach>
				<c:if test="${empty articles }">
					<tr>
						<td colspan="4" style="text-align: center;">게시글이 없습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>