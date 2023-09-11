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
						<strong>Campfire</strong> 문의사항 수정
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
					<form action="/user/modifyUserInquiry.do" method="post">
						<input type="hidden" name="inquiryNum" value="${detail.inquiryNum }" />
						<input type="hidden" name="memberNum" value="${detail.memberNum }" />
						<div class="title-box">
							<input type="text" class="form-control" id="exampleFormControlInput1" name="inquiryTitle" value="${detail.inquiryTitle }" placeholder="제목을 입력하세요.">
						</div>

						<br>

						<div class="content-box">
							<textarea name="inquiryContext" id="editorTxt" class="form-control" rows="13" cols="10" placeholder="내용을 입력해주세요">${detail.inquiryContext }</textarea>
						</div>
						<br>
						<div class="btn-box">
							<div>
								<c:choose>
									<c:when test="${detail.inquiryPublicFL eq 'Y'}">
										<label>
											<input type="radio" name="inquiryPublicFL" value="Y" checked>
											공개
										</label>
										<label>
											<input type="radio" name="inquiryPublicFL" value="N">
											비공개
										</label>
									</c:when>
									<c:otherwise>
										<label>
											<input type="radio" name="inquiryPublicFL" value="Y" >
											공개
										</label>
										<label>
											<input type="radio" name="inquiryPublicFL" value="N" checked>
											비공개
										</label>
									</c:otherwise>
								</c:choose>

							</div>
							<div>
								<button type="button" class="button small" onclick="goBack()">취소</button>
								<button type="submit" class="button primary small">확인</button>
							</div>
						</div>
					</form>
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