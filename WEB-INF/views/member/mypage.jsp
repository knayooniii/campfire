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
<link rel="stylesheet" href="/resources/login/css/mypage.css" />
<link rel="stylesheet" href="/resources/main/css/campSearch.css" />
<link rel="stylesheet" href="/resources/main/css/tag.css" />
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
						<strong>Campfire</strong> 마이페이지
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

					<h2 style="text-align: center; margin-top: 20px;">회원정보</h2>
					<div class="container py-4">
						<div class="row align-items-center justify-content-between"></div>

						<form action="mypageup.do">

							<div class="info" id="info__id">
								<div id="id-input">
									<label for="id">아이디</label>
									<input class="box" id="id" type="text" name="memberUserId" value="${member.memberUserId}" disabled />
								</div>
								<div class="error-msg"></div>
							</div>



							<label for="id">이름</label>
							<input type="text" class="box" id="name" required name="memberName" value="${member.memberName}" disabled />



							<label for="id">주소</label>

							<div style="display: flex; gap: 10px; align-items: baseline;">

								<input type="text" class="box" id="sample6_postcode" placeholder="우편번호" name="memberPostalcode" style="flex-basis: 70%" value="${member.memberPostalcode}" disabled />
								<input type="button" id="search" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" disabled />
							</div>

							<input type="text" class="box" id="sample6_address" placeholder="주소" name="memberAdd1" value="${member.memberAdd1}" disabled />
							<input type="text" id="sample6_detailAddress" placeholder="상세주소" style="flex-basis: 70%" name="memberAdd2" value="${member.memberAdd2}" disabled />
							<br>

							<div style="text-align: center;">
								<div class="gender-container">
									<input type="radio" id="male" name="memberGender" value="남성" ${member.memberGender == '남성' ? 'checked' : ''} disabled />
									<label for="male">남성</label>
									<input type="radio" id="female" name="memberGender" value="여성" ${member.memberGender == '여성' ? 'checked' : ''} disabled />
									<label for="female">여성</label>
								</div>
							</div>

							<br>

							<div class="info" id="info__birth">
								<div id="birth-flex">
									<select class="box" id="birth-year" disabled>
										<option>년</option>
										<option value="${birthYear}" selected>${birthYear}</option>
									</select>
									<select class="box" id="birth-month" disabled>
										<option>월</option>
										<option value="${birthMonth}" selected>${birthMonth}</option>
									</select>
									<select class="box" id="birth-day" disabled>
										<option>일</option>
										<option value="${birthDay}" selected>${birthDay}</option>
									</select>
								</div>
								<div class="error-msg"></div>
							</div>



							<br>

							<div class="d-grid gap-2">
								<button class="btn btn-primary btn-lg" type="submit">수정 하기</button>
								<button type="button" id="pwupButton">비밀번호 변경</button>
								<button type="button" id="deleteButton">탈퇴하기</button>
							</div>
						</form>


					</div>
				</section>

			</div>
		</div>
	<%@ include file="../common/sidebar.jsp"%>

	</div>

	<!-- Scripts -->
	<script src="/resources/login/js/mypage.js"></script>
	<script src="/resources/main/js/jquery.min.js"></script>
	<script src="/resources/main/js/browser.min.js"></script>
	<script src="/resources/main/js/breakpoints.min.js"></script>
	<script src="/resources/main/js/util.js"></script>
	<script src="/resources/main/js/main.js"></script>
	<script src="/resources/main/js/tag.js"></script>
	<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>

</html>