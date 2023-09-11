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


</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<!-- Header -->
				<header id="header">
					<a href="/campSearch/camping.do" class="logo">
						<strong>Campfire</strong> ID/PW 찾기
					</a>
					<c:choose>
						<c:when test="${sessionScope.memberNum != null}">
							<ul class="list-bar">
								<li>
									<a href="/member/mypage.do">마이페이지</a>
								</li>
								<li>
									<a href="/myList/likeList.do"> 찜 목록 </a>
								</li>
								<li>
									<a href="/myList/wishList.do"> 즐겨찾기 목록 </a>
								</li>
								<li>
									<a href="/member/logout.do"> 로그아웃 </a>
								</li>

							</ul>
						</c:when>
						<c:otherwise>
							<ul class="list-bar">

								<li>
									<a href="/">로그인 </a>
								</li>

							</ul>
						</c:otherwise>
					</c:choose>

				</header>


				<section class="bg-light">
					<div style="text-align: center; margin-top: 20px;">
						<h2 style="font-size: 24px;">ID찾기</h2>
					</div>

					<div class="w3-content w3-container w3-margin-top">
						<div class="w3-container w3-card-4">
							<form action="find_id.do" method="post">

								<div>
									<p>
										<label>이름</label>
										<input class="w3-input" type="text" id="name" name="memberName" placeholder="이름:" required>
									</p>

									<p>
										<label>생일</label>
										<input class="w3-input" type="text" id="birth" name="memberDateBirth" placeholder="격식: 0000-00-00" required>
									</p>

									<p class="w3-center">
										<button type="submit" id='findBtn'>find</button>
										<button type="button" onclick="history.go(-1);">Cancel</button>
									</p>
								</div>
							</form>
						</div>
					</div>

				</section>

			</div>

		</div>

	</div>


	<!-- Scripts -->


</body>

</html>