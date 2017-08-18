<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="postmaker.FileManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	width: 100%;
	height: 400px;
}

textarea {
	width: 98%;
	height: 200px;
}
</style>
</head>
<body>

	<jsp:useBean id="fileManager" class="postmaker.FileManager" />

	<form action="/PostsMaker/controller" method="post">
		<table>
			<tr>
				<td>Covers:<br> <textarea name="covers">${fileManager.covers}</textarea>
				</td>
				<td>Screenshots:<br>
				<textarea name="screenshots">${fileManager.screenshots}</textarea></td>
			</tr>
			<tr>
				<td>Mediainfo:<br>
				<textarea name="mediainfo">${fileManager.mediainfo}</textarea></td>
				<td>Titles:<br>
				<textarea name="titles">${fileManager.titles}</textarea></td>
			</tr>
			<tr>
				<td>URLs:<br>
				<textarea name="urls"></textarea></td>
				<td>
					<p>
						<input type="radio" name="genre" value="rimming"> <label
							for="rimming">Rimming</label> <input type="radio" name="genre"
							value="rimming">
					</p>
					<p>
						<input type="checkbox" name="write" value="write">
					</p>
					<p>
						<input type="submit" value="Make posts">
					</p>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>