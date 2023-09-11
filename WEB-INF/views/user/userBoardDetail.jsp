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
						<strong>Campfire</strong> 게시물 상세보기
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

					<div class="board-info">

						<c:choose>
							<c:when test="${ckeckLike eq 1}">
								<i id="heart" class="fa-solid fa-heart fa-xl" data-jstl-value="${board.postNum}" style="color: #ff8fb6;"></i>
							</c:when>
							<c:otherwise>
								<i id="heart" class="fa-regular fa-heart fa-xl" data-jstl-value="${board.postNum}" style="color: #ff8fb6;"></i>
							</c:otherwise>
						</c:choose>
						<span id="like-count">${likeCount }</span> <i class="fa-solid fa-eye fa-lg" style="color: #5c5c5c; margin-right: 3px;"></i><span>${board.postViewCount }</span> <span>등록 날짜 : ${board.newCreateDate }</span>
					</div>
					<div class="board-title">
						<c:choose>
							<c:when test="${board.postCategory eq 'recommend'}">
								<p>추천</p>
							</c:when>
							<c:otherwise>
								<p>정보공유</p>
							</c:otherwise>
						</c:choose>
						<h2>${board.postTitle }</h2>
					</div>

					<p class="user-id">
						<i class="fa-solid fa-tree fa-xl" style="color: #155b3a;"></i><strong> ${board.memberId}</strong>
					</p>
					<div id="main-text">
						<br> ${board.postContent }
					</div>

					<div class="comment-container">
					<br>
						<h3>댓글</h3>
						<c:choose>
							<c:when test="${empty replyList}">
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${replyList}">
									<div class="comment-list" id="comment-list-${item.postReplyNum }">
										<div class="comment">
											<div class="comment-user"><i class="fa-solid fa-tree fa-lg" style="color: #155b3a;"></i> ${item.memberId }</div>
											<div class="comment-date">${item.newDate }
												<c:if test="${sessionScope.memberNum == item.memberNum}">
													<i class="fa-solid fa-trash-can" style="color: #8a8a8a;"onclick="deleteReply('${item.postReplyNum}')"></i>
												</c:if>
											</div>
										</div>
										<div class="comment">
											<div class="comment-content">${item.postReplyContent }</div>
											<div class="comment-like">
												<i class="fa-regular fa-thumbs-up" style="color: #1e6bf1;" onclick="likePostReply('${item.postReplyNum}')"></i> <span id="comment-like-count${item.postReplyNum}">${item.replyLikeCount }</span>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</div>
					<div class="comment-form">
						<input type="text" id="comment-content" data-postNum-value="${board.postNum }" placeholder="댓글을 입력하세요">
						<button type="button" class="button small insert-reqly">등록</button>
					</div>

					<div class="btn-box">
						<br>
						<c:if test="${sessionScope.memberNum == board.memberNum}">
							<button type="button" class="button small right-btn" onclick="location.href = '/user/board/showModifyBoard.do?postCategory=${board.postCategory}&memberNum=${board.memberNum}&postNum=${board.postNum}'" class="btn btn-primary">수정</button>
							<button type="button" class="button small right-btn" onclick="location.href = '/user/board/deleteBoard.do?postCategory=${board.postCategory}&memberNum=${board.memberNum}&postNum=${board.postNum}'" class="btn btn-primary">삭제</button>
						</c:if>
						<c:if test="${sessionScope.memberNum != board.memberNum && sessionScope.memberDivision eq 'admin'}">
							<button type="button" class="button small right-btn" onclick="location.href = '/user/board/deleteBoard.do?postCategory=${board.postCategory}&memberNum=${board.memberNum}&postNum=${board.postNum}'" class="btn btn-primary">삭제</button>
						</c:if>
						<button type="button" class="button small primary right-btn" onclick="location.href = '/user/board/showBoardList.do?postCategory=${board.postCategory}'">목록</button>
					</div>
					<br>
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
	<script src="../../resources/main/js/board.js"></script>
	<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>
</body>

</html>