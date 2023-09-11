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
<%@ include file="../common/smarteditor.jsp"%>
<link rel="stylesheet" href="../../resources/main/css/recommend.css" />
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
						<strong>Campfire</strong> 게시물 수정
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

					<form action="/user/board/updateBoard.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="postNum" value="${board.postNum }">
						<div class="mb-3 justify-content-center">
							<label for="exampleFormControlInput1" class="form-label">제목</label>
							<input type="text" class="form-control" name="postTitle" id="exampleFormControlInput1" placeholder="제목을 입력하세요." value="${board.postTitle }">
						</div>

						<div class="mb-3 justify-content-center">
							<label for="categorySelect" class="form-label">카테고리</label>
							<select class="form-select" id="categorySelect" name="postCategory">
								<option value="recommend" ${board.postCategory == 'recommend' ? 'selected' : ''}>추천</option>
								<option value="sharingInfo" ${board.postCategory == 'sharingInfo' ? 'selected' : ''}>정보공유</option>
							</select>
						</div>

						<div id="smarteditor mb-3 justify-content-center">
							<label for="postContent" class="form-label">내용</label>
							<!-- <textarea name="content" id="editorTxt" class="form-control" value=""
								placeholder="내용을 입력해주세요"></textarea> -->
							<textarea name="postContent" id="postContent" rows="10" cols="20" style="width: 100%; height: 412px; min-width: 500px;">${board.postContent }</textarea>
						</div>
						<div class="btn-box">
							<button type="button" class="button small right-btn" onclick="history.back()">취소</button>
							<button type="submit" class="button small primary right-btn" onclick="save()" class="btn btn-primary">수정</button>
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
	<script src="../../resources/main/js/smarteditor.js"></script>

</body>

</html>