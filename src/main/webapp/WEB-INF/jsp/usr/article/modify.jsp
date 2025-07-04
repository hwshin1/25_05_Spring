<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/doModify" method="POST">
			<input type="hidden" name="id" value="${article.id}" />
			<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th class="text-center">번호</th>
						<td class="text-center">${article.id }</td>
					</tr>
					<tr>
						<th class="text-center">작성날짜</th>
						<td class="text-center">${article.regDate }</td>
					</tr>
					<tr>
						<th class="text-center">수정날짜</th>
						<td class="text-center">${article.updateDate }</td>
					</tr>
					<tr>
						<th class="text-center">작성자 닉네임</th>
						<td class="text-center">${article.extra__write }</td>
					</tr>
					<tr>
						<th class="text-center">제목</th>
						<td class="text-center">
							<input class="input input-secondary" required="required" type="text" name="title" value="${article.title }" autocomplete="off" placeholder="새 제목" />
						</td>
					</tr>
					<tr>
						<th class="text-center">내용</th>
						<td class="text-center">
							<textarea autocomplete="off" required="required" name="body" placeholder="새 내용" class="textarea textarea-secondary">${article.body }</textarea>
						</td>
					</tr>
					<tr>
						<th></th>
						<td class="text-center">
							<input class="btn btn-soft" type="submit" value="수정" />
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<button class="btn btn-soft" type="button" onclick="history.back();">뒤로가기</button>
				<c:if test="${article.userCanDelete }">
					<a class="btn btn-soft" href="../article/doDelete?id=${article.id }">삭제</a>
				</c:if>
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>