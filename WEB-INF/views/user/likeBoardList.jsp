<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UPaagfgfygh 
	html5up.net | @ajlknaaa
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>

<head>

<meta charset="utf-8" />
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
					<a href="/campSearch/camping.do" class="logo">
						<strong>Campfire</strong> my <i class="fa-solid fa-heart fa2xl" style="color: #ff8fb6;"></i> list
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

				<section>
					<div class="like-category">
						<h2>
							my <i class="fa-solid fa-heart fa2xl" style="color: #ff8fb6;"></i> list
						</h2>

						<ul class="like-category-btn">
							<li>
								<a href="/myList/likeList.do" class="button">캠핑장</a>
							</li>
							<li>
								<a href="/myList/likeBoardList.do" class="button">커뮤니티</a>
							</li>
						</ul>
					</div>

					<div class="box alt" id="ls1">
						<div class="row gtr-50 gtr-uniform">
							<c:choose>
								<c:when test="${empty boardList}">
									<h3 class="text-center">등록된 글이 없습니다.</h3>

								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${boardList}">
										<div class="col-6 items" onclick="location.href='/user/board/showBoardDetail.do?postNum=${item.postNum}'">
											<div class="img-box ls1Img"">
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
					<div class="section-pagination">
						<ul class="pagination">
							<c:choose>
								<c:when test="${ pi.currentPage eq 1 }">
									<li>
										<a href="#" class="button">Prev</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="camping.do?cpage=${ pi.startPage -1 }" class="button">Prev</a>
									</li>

								</c:otherwise>
							</c:choose>

							<c:forEach var="page" begin="${pi.startPage}" end="${pi.endPage}">
								<li>
									<a class="page" href="camping.do?cpage=${page}">${page} </a>
								</li>
							</c:forEach>

							<c:choose>
								<c:when test="${pi.currentPage eq pi.maxPage}">
									<li>
										<a href="#" class="button">Next</a>
									</li>

								</c:when>
								<c:when test="${pi.endPage eq pi.maxPage}">
									<li>
										<a href="camping.do?cpage=${pi.maxPage}" class="button">Next</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="camping.do?cpage=${pi.currentPage + 1}&campAmenity=${campAmenity}&searchTxt=${searchTxt}&campTag=${campTag}&campAddress${campAdderess}&category${category}&campKeyword${campKeyword}&campType${campType}" class="button">Next</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>


				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<%@ include file="../common/sidebar.jsp"%>
		</div>

		<!-- Scripts -->
		<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>
		<script src="../../resources/main/js/jquery.min.js"></script>
		<script src="../../resources//main/js/browser.min.js"></script>
		<script src="../../resources/main/js/breakpoints.min.js"></script>
		<script src="../../resources/main/js/util.js"></script>
		<script src="../../resources/main/js/main.js"></script>
		<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>
		<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script> -->
</body>

</html>