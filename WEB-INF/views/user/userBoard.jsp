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
<link rel="stylesheet" href="/resources/main/css/inquiry.css" />
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<c:choose>
						<c:when test="${postCategory eq 'recommend'}">
							<a href="/campSearch/camping.do" class="logo">
								<strong>Campfire</strong> 커뮤니티(추천)
							</a>
						</c:when>
						<c:otherwise>
							<a href="/campSearch/camping.do" class="logo">
								<strong>Campfire</strong> 커뮤니티(정보공유)
							</a>
						</c:otherwise>
					</c:choose>
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
					<div>
						<h4>인기글</h4>
					</div>
					<div>
						<div class="row gtr-50 gtr-uniform">
							<c:choose>
								<c:when test="${empty boardPopularList}">
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${boardPopularList}" begin="0" end="3">
										<div class="col-3">
											<div class="item-box" onclick="location.href='/user/board/showBoardDetail.do?postNum=${item.postNum}'">
												<c:choose>
													<c:when test="${empty item.imageName}">
														<span class="image fit my-margin"><img class="photoImage" src="/resources\images\noimage.jpg" alt="이미지 오류" /></span>
													</c:when>
													<c:otherwise>
														<span class="image fit my-margin"><img src="/resources/upload/${item.imageName }" alt="이미지 오류" /></span>
													</c:otherwise>
												</c:choose>
												<p>${item.postTitle }</p>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="row all-content">
						<div class="col-6">
							<h4>전체글</h4>
						</div>
						<div class="col-6 menu-container">
							<img class="menu-logo" id="menu-logo1" src="/resources/images/icon2-select.png" alt=""> <img class="menu-logo" id="menu-logo2" src="/resources/images/icon1.png" alt="">
						</div>
					</div>
					<!-- 리스트 스타일 1 -->
					<div class="box alt" id="ls1">
						<div class="row gtr-50 gtr-uniform">
							<c:choose>
								<c:when test="${empty boardList}">
									<h3 class="text-center">등록된 글이 없습니다.</h3>

								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${boardList}">
										<div class="col-6 items" onclick="location.href='/user/board/showBoardDetail.do?postNum=${item.postNum}'">
											<div class="img-box ls1Img">
												<c:choose>
													<c:when test="${empty item.imageName}">
														<img class="photoImage" src="/resources\images\noimage.jpg" alt="이미지 오류" />
													</c:when>
													<c:otherwise>
														<img src="/resources/upload/${item.imageName }" alt="이미지 오류" />

													</c:otherwise>
												</c:choose>
											</div>
											<div class="content-box">
												<p class="my-margin content-title">
													<strong>${item.postTitle }</strong>

												</p>
												<p class="my-margin content-main">${item.postContent }</p>
												<p class="my-margin content-function">
													<i id="heart" class="fa-solid fa-heart fa-sm" style="color: #ff8fb6;"></i> ${item.likeCount } <i class="fa-regular fa-comment fa-sm" style="color: #0e4812;"></i> ${item.replyCount } 작성자 : ${item.memberId} <span class="font-small content-time right-align1">${item.newCreateDate }</span> <span class="font-small content-view right-align2"><i class="fa-solid fa-eye fa-sm" style="color: #5c5c5c; margin-right: 3px;"></i>${item.postViewCount }</span>
												</p>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!-- 리스트 스타일 2 -->
					<div class="features" id="ls2" style="display: none;">
						<c:choose>
							<c:when test="${empty boardList}">
								<h3 class="text-center">등록된 글이 없습니다.</h3>

							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${boardList}">
									<div class="contents" onclick="location.href='/user/board/showBoardDetail.do?postNum=${item.postNum}'">
										<div class="ls2Img">
											<c:choose>
												<c:when test="${empty item.imageName}">
													<img class="photoImage" src="/resources\images\noimage.jpg" alt="이미지 오류" />
												</c:when>
												<c:otherwise>
													<img class="photoImage" src="/resources/upload/${item.imageName}" alt="이미지 오류" />
												</c:otherwise>
											</c:choose>
										</div>
										<div>
											<h3>${item.postTitle}</h3>
											<p class="content-text">${item.postContent }</p>
											<p class="no-margin content-function">
												<i id="heart" class="fa-solid fa-heart fa-sm" style="color: #ff8fb6;"></i> ${item.likeCount } <i class="fa-regular fa-comment fa-sm" style="color: #0e4812;"></i> ${item.replyCount } 작성자 : ${item.memberId} <span class="font-small content-time right-align1">${item.newCreateDate } </span> <span class="font-small content-view right-align2"><i class="fa-solid fa-eye fa-sm" style="color: #5c5c5c; margin-right: 3px;"></i>${item.postViewCount }</span>
											</p>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<br>
					<button class="button small primary right-btn" onclick="location.href='/user/board/showEnrollBoard.do'">글쓰기</button>

					<div class="section-pagination">
						<ul class="pagination">
							<c:choose>
								<c:when test="${pi.currentPage eq 1}">
									<li>
										<a href="#" class="button small disabled">Prev</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="showBoardList.do?postCategory=${postCategory }&cpage=${ pi.currentPage - 1 }&searchCtg=${ searchCtg }&searchTxt=${ searchTxt }" class="button small">Prev</a>
									</li>
								</c:otherwise>
							</c:choose>

							<c:forEach var="page" begin="${ pi.startPage }" end="${ pi.endPage }">
								<li>
									<c:choose>
										<c:when test="${page eq pi.currentPage}">
											<a class="page active" href="showBoardList.do?postCategory=${postCategory }&cpage=${ page }&searchCtg=${ searchCtg }&searchTxt=${ searchTxt }">${ page }</a>
										</c:when>
										<c:otherwise>
											<a class="page" href="showBoardList.do?postCategory=${postCategory }&cpage=${ page }&searchCtg=${ searchCtg }&searchTxt=${ searchTxt }">${ page }</a>
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
							<c:choose>
								<c:when test="${pi.currentPage eq pi.maxPage}">
									<li>
										<a href="#" class="button small disabled">Next</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="showBoardList.do?postCategory=${postCategory }&cpage=${ pi.currentPage + 1 }&searchCtg=${ searchCtg }&searchTxt=${ searchTxt }" class="button small">Next</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<form action="showBoardList.do">
						<div class="section-search">
							<input type="hidden" name="postCategory" value="${postCategory }">
							<select name="searchCtg" id="category">
								<option value="title">제목</option>
								<option value="context">내용</option>
								<option value="writer">작성자</option>
							</select>
							<input type="text" name="searchTxt" id="demo-name" value="" placeholder="검색어">
							<button type="submit" class="button primary small icon solid fa-search"></button>
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
	<script src="../../resources/main/js/changeListStyle.js"></script>
	<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>

</body>

</html>