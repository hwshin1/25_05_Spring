<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/doWrite" method="POST">
			<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th class="text-center">제목</th>
						<td class="text-center">
							<input class="input input-secondary" required="required" type="text" name="title" autocomplete="off" placeholder="제목" />
						</td>
					</tr>
					<tr>
						<th class="text-center">내용</th>
						<td class="text-center">
							<textarea autocomplete="off" required="required" name="body" placeholder="내용" class="textarea textarea-secondary"></textarea>
						</td>
					</tr>
					<tr>
						<th></th>
						<td class="text-center">
							<input class="btn btn-soft" type="submit" value="작성" />
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<button class="btn btn-soft" type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>