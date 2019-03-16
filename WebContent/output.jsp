<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="postmaker.dto.Posts, postmaker.Post"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
		margin-top: 800px;
	}
	p {
		margin: 50px 0;
	}
</style>
</head>
<body>
	<table>
		<c:forEach var="elem" items="${posts.posts}" varStatus="status">
			<tr>
				<td><p>${elem.html}<hr></p></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>