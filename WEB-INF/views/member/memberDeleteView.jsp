<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>

<head>
<title>Camp Search Page</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="/resources/login/css/search_id.css" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<!-- Header -->
				<header id="header">
					<a>
						<strong>Campfire</strong> ID·PW 찾기
					</a>
				</header>


				<section id="container">
					<div style="text-align: center; margin-top: 20px;">
						<h2 style="font-size: 24px;">회원탈퇴</h2>
					</div>

					<div class="w3-content w3-container w3-margin-top">
						<div class="w3-container w3-card-4">
							<form action="/member/memberDelete.do" method="post">

								<p>
									<label class="control-label" for="memberUserId">아이디</label>
									<input class="form-control" type="text" id="memberUserId" name="memberUserId" value="${memberUserId}" readonly="readonly" />
								</p>

								<p>
									<label class="control-label" for="memberPw">패스워드</label>
									<input class="form-control" type="password" id="memberPw" name="memberPw" placeholder="비밀번호:" required />
								</p>

								<p class="w3-center">
									<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
									<button type="button" onclick="history.go(-1);">취소</button>
								</p>
							</form>
						</div>

					</div>
					<div>
						<c:if test="${msg == false}">
					비밀번호가 맞지 않습니다.
				</c:if>
					</div>
				</section>
			</div>



		</div>

	</div>

	


	<!-- Scripts -->
	<script src="/resources/login/js/memberDelete.js"></script>

</body>

</html>