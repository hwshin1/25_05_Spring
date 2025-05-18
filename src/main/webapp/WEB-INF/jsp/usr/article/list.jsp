<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ARTICLE LIST</h1>
	<hr />

	<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
		<thead>
			<tr>
				<th style="text-align: center;">번호</th>
				<th style="text-align: center;">작성 날짜</th>
				<th style="text-align: center;">제목</th>
				<th style="text-align: center;">작성자 번호</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles }">
				<tr>
					<td style="text-align: center;">${article.id }</td>
					<td style="text-align: center;">${article.regDate }</td>
					<td style="text-align: center;">${article.title }</td>
					<td style="text-align: center;">${article.memberId }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>