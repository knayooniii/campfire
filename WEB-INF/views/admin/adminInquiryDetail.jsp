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
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="../../resources/main/css/inquiry.css" />
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
						<strong>Campfire</strong> 문의사항 글쓰기
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

				<!-- Content -->
				<section>
					<div class="post-info">
						<p>
							<span class="info-user">작성자:${ detail.memberId }</span><span class="info-time">작성일:${ detail.inquiryNewDate }</span>
						</p>
					</div>
					<div class="title-box">
						<input type="text" class="form-control" id="exampleFormControlInput1" name="inquiryTitle" value="${ detail.inquiryTitle }" disabled>
					</div>

					<br>

					<div class="content-box">
						<textarea name="inquiryContext" id="editorTxt" class="form-control" rows="13" cols="10" disabled>${ detail.inquiryContext }</textarea>
					</div>
					<c:if test="${detail.inquiryAnswerFL eq 'Y'}">
						<br>
						<div class="reply-wrapper">
							<div class="reply-line"></div>
							<span>답글</span>
							<div class="reply-line"></div>
						</div>
						<br>
						<div class="post-info">
							<p>
								<span class="info-user">작성자:${ detail.answerAdmin }</span><span class="info-time">작성일:${ detail.answerNewDate }</span>
							</p>
						</div>
						<div class="title-box">
							<input type="text" class="form-control" id="exampleFormControlInput1" name="title" value="${ detail.answerTitle }">
						</div>

						<br>

						<div class="content-box">
							<textarea name="content" id="editorTxt" class="form-control" rows="13" cols="10">${ detail.answerContext }</textarea>
						</div>
					</c:if>
					<br>
					<div class="right-btn-box">
						<button type="button" class="button small" onclick="goBack()">취소</button>
						<c:choose>
							<c:when test="${detail.inquiryAnswerFL eq 'Y'}">
								<button type="button" class="button primary small" onclick="location.href='/admin/deleteAdminInquiry.do?inNum=${detail.inquiryNum}'">문의사항 삭제</button>
								<button type="button" class="button primary small" onclick="location.href='/admin/deleteAdminAnswer.do?inNum=${detail.inquiryNum}'">답글 삭제</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="button primary small" onclick="location.href='/admin/deleteAdminInquiry.do?inNum=${detail.inquiryNum}'">문의사항 삭제</button>
								<button type="button" class="button primary small" onclick="location.href='/admin/answerForm.do?inNum=${detail.inquiryNum}'">답글 달기</button>
							</c:otherwise>
						</c:choose>

					</div>
				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<%@ include file="../common/sidebar.jsp"%>

	</div>

	<!-- Scripts -->
	<script src="../../resources/main/js/jquery.min.js"></script>
	<script src="../../resources/main/js/browser.min.js"></script>
	<script src="../../resources/main/js/breakpoints.min.js"></script>
	<script src="../../resources/main/js/util.js"></script>
	<script src="../../resources/main/js/main.js"></script>

</body>

</html>