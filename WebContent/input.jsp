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

td {
	width: 40%;
}

textarea {
	width: 98%;
	height: 200px;
}
</style>
</head>
<body>

	<jsp:useBean id="fileManager" class="postmaker.FileManager" scope="session"/>

	<form action="/PostsMaker/controller" method="post">
		Tags: <input type="text" name="tags" style="width:95%"> 
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
						<input type="radio" name="genre" value="rimming">Rimming 
						<input type="radio" name="genre" value="bukakke">Bukakke
						<input type="radio" name="genre" value="javuncensored">jav uncensored
						<input type="radio" name="genre" value="swedish-xxx">swedish
						<input type="radio" name="genre" value="shemale">shemale
						<input type="radio" name="genre" value="asian-webcam">asian webcam
						<input type="radio" name="genre" value="semi-amateur">semi-amateur
						<input type="radio" name="genre" value="anal-sex">anal sex
						<input type="radio" name="genre" value="female-orgasm">femorg
						<input type="radio" name="genre" value="spanish-sex">spanish
						<input type="radio" name="genre" value="jerkoff-instructions">joi
						<input type="radio" name="genre" value="softcore">softcore
						<input type="radio" name="genre" value="spanking">spanking
						<input type="radio" name="genre" value="creampie">creampie
						<input type="radio" name="genre" value="interracial">interracial
						<input type="radio" name="genre" value="hairy">hairy
						<input type="radio" name="genre" value="femdom">femdom
						<input type="radio" name="genre" value="cosplay">cosplay 
						<input type="radio" name="genre" value="camshow">camshow
						<input type="radio" name="genre" value="shemale">shemale
						<input type="radio" name="genre" value="gangbang">gangbang
						<input type="radio" name="genre" value="incest">incest
						<input type="radio" name="genre" value="scat">scat
						<input type="radio" name="genre" value="bdsm">bdsm
						<input type="radio" name="genre" value="latex">latex
						<input type="radio" name="genre" value="pegging">pegging
						<input type="radio" name="genre" value="yoga">yoga
						<input type="radio" name="genre" value="oral">oral
						<input type="radio" name="genre" value="foot">foot
						<input type="radio" name="genre" value="milf">milf
						<input type="radio" name="genre" value="rough">rough
					</p>
					<p>
						<input type="radio" name="cover-pichost" value="cover-pimpandhost">Cover PimpAndHost
						<input type="radio" name="cover-pichost" value="cover-pixhost">Cover PixHost
						<input type="radio" name="screenshot-pichost" value="screenshot-pimpandhost">Screenshot PimpAndHost
						<input type="radio" name="screenshot-pichost" value="screenshot-pixhost">Screenshot PixHost
					</p>
<!-- 					<p>
						<input type="radio" name="filehost" value="filejoker">Filejoker
						<input type="radio" name="filehost" value="rapidgator">Rapidgator
						<input type="radio" name="filehost" value="keep2share">Keep2share
					</p>
 -->
 
 					<p>
						<input type="checkbox" name="write" value="write">Write
					</p>
					<p>
						<input type="submit" value="Make posts">
					</p>
				</td>
			</tr>
			<tr>
				<td>
					<label for="delete">Delete:</label><br>
					<textarea id="delete" name="delete"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>