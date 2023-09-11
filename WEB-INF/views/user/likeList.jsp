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
<link rel="stylesheet" href="/resources/main/css/campSearch.css" />
<link rel="stylesheet" href="/resources/main/css/tag.css" />
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

					<c:choose>
						<c:when test="${empty likeList }">
							<div>
								<h4 class="empty">등록된 글이 없습니다.</h4>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="item" items="${likeList }">
								<div class="camping_info" onclick="goToDetailPage('/campInfo/campInfo.do?campNum=${item.campNum}')">
									<!-- 여기서 '/detail_page_url'은 상세 정보 페이지의 URL을 넣어주어야 합니다. -->
									<img src="${item.campPhotoURL }${item.campPhotoName}" alt="캠핑장 이미지">
									<div class="camping_data">
										<h4>${item.campName }</h4>
										<p>
											<i class="fa-regular fa-heart fa2xs" style="color: #ff8fb6;"></i> ${item.likeCamp}
										</p>
										<p>
											<i class="fa-solid fa-eye fa-xs" style="color: #5c5c5c; margin-right: 3px;"></i> ${item.campView }
										</p>
										<p>${item.campCreateDate }</p>
									</div>
								</div>
								<hr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
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
	<script src="../../resources/main/js/campSearch.js"></script>
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