<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%--@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="c" --%>
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
			<!-- TODO マスタみてテーブル化 -->
			<input name="gacha" type="radio" value="madoka" checked="checked" /><img src="img/01_magiReco.jpg" />
			<input name="gacha" type="radio" value="ma"/><img src="img/02_gruble.jpg" />
			<br/>
			<input checked="checked" name="times" value="10" type="radio" />
				10連
			<input name="times" type="radio" value="1"/>
				単発
			<br />
			<input name="simulate" type="submit" value="ガチャ欲を満たそう！課金を避けよう！" />
		</form>
 		<fieldset name="result" style="height: 100px">
			<c:forEach items="${cards}" var="card">
	            <c:out value="${cards}" />
	            <c:out value="${item}" />
            </c:forEach>
			結果
			<br />
		</fieldset>
	</div>
	<p>${pageContext.request.contextPath}</p>
	<p>  The time on the server is ${serverTime}. </p>
</body>
</html>