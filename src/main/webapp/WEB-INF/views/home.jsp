<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="textCenter">
		<h1>課金は家賃まで！</h1>
		<h2>（ﾉ∀＼*）ｷｬ</h2>
		<h1>そんな生活やめませんか？</h1>
		<h1>(・_・)</h1>
		<form method="post" style="position: relative;" action="/simulate">
			<table class="list">
				<c:forEach items="${gameMasterList}" var="game">
					<tr>
						<!-- ラジオボタン -->
						<td><input name="gacha" type="radio" value="${game.gameCd}" /></td>
						<!-- イメージ画像 -->
						<td><img src="${game.imageName}" /></td>
					</tr>
	            </c:forEach>
			</table>
			<input checked="checked" name="times" value="10" type="radio" />
				10連
			<input name="times" type="radio" value="1"/>
				単発
			<br />
			<input name="simulate" type="submit" value="ガチャ欲を満たそう！課金を避けよう！" />
		</form>
  		<fieldset style="height: 100px">
			結果
			<br />
			<c:forEach items="${cards}" var="card">
				<c:out value="${card}" />
				<br />
			</c:forEach>
		</fieldset>
	</div>
	<p>${pageContext.request.contextPath}</p>
	<p>  The time on the server is ${serverTime}. </p>
</body>
</html>