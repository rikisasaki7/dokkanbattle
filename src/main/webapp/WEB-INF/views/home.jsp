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
		<form method="post" action="/simulate">
			<table class="gachaList">
				<tr>
					<c:forEach items="${gameMasterList}" var="game">
						<!-- ラジオボタン -->
						<td><input name="gacha" type="radio" value="${game.gameCd}" /></td>
						<!-- イメージ画像 -->
						<td><img class="gachaImage" src="${game.imageName}" /></td>
		            </c:forEach>
				</tr>
				<tr>
					<!-- ラジオボタン -->
					<td><input checked="checked" name="times" value="10" type="radio" /></td>
					<!-- ガチャの種類 -->
					<td>10連</td>
					<!-- ラジオボタン -->
					<td><input name="times" type="radio" value="1"/></td>
					<!-- ガチャの種類 -->
					<td>単発</td>
				</tr>
			</table>
			<button class="submitButton" type="submit" >ガチャ欲を満たそう！<br />課金を避けよう！</button>
 		</form>
	</div>
	<c:if test="${!empty gachaResultList}">
		<div class="resultBox">
		    <p>結果</p>
			<table class="resultList">
	 			<tr>
	 				<c:if test="${!empty gachaResultList[0]}"><td>${gachaResultList[0].gachaDataName}</td></c:if>
	 				<c:if test="${!empty gachaResultList[1]}"><td>${gachaResultList[1].gachaDataName}</td></c:if>
	 			</tr>
 				<tr>
					<c:if test="${!empty gachaResultList[2]}"><td>${gachaResultList[2].gachaDataName}</td></c:if>
					<c:if test="${!empty gachaResultList[3]}"><td>${gachaResultList[3].gachaDataName}</td></c:if>
				</tr>
				<tr>
					<c:if test="${!empty gachaResultList[4]}"><td>${gachaResultList[4].gachaDataName}</td></c:if>
					<c:if test="${!empty gachaResultList[5]}"><td>${gachaResultList[5].gachaDataName}</td></c:if>
				</tr>
				<tr>
					<c:if test="${!empty gachaResultList[6]}"><td>${gachaResultList[6].gachaDataName}</td></c:if>
					<c:if test="${!empty gachaResultList[7]}"><td>${gachaResultList[7].gachaDataName}</td></c:if>
				</tr>
				<tr>
					<c:if test="${!empty gachaResultList[8]}"><td>${gachaResultList[8].gachaDataName}</td></c:if>
					<c:if test="${!empty gachaResultList[9]}"><td>${gachaResultList[9].gachaDataName}</td></c:if>
				</tr>
			</table>
		</div>	
	</c:if>
 </body>
</html>