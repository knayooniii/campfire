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
<link rel="stylesheet" href="/resources/main/css/campSearch.css" />
<style>
.section-pagination>ul {
	text-align: center;
}

.text-center {
	text-align: center;
}
</style>
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
						<strong>Campfire</strong> 캠핑장 등록 관리
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
					<h4>캠핑장 등록 승인</h4>
					<c:choose>
						<c:when test="${empty campList }">
							<div>
								<h4>등록된 글이 없습니다.</h4>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="item" items="${campList }">
								<div class="camping_info" onclick="goToDetailPage('/adminRegistration/detailCampRegistration.do?campNum=${item.campNum}')">

									<c:set var="campPhotos" value="${item.campPhotoList}" />
									<c:choose>
										<c:when test="${empty campPhotos}">
											<img src="/resources/images/noimage.jpg" alt="기본사진대체텍스트">
										</c:when>
										<c:otherwise>
											<img src="${campPhotos[0].campPhotoURL}${campPhotos[0].campPhotoName}" alt="${campPhotos[0].campPhotoName}">
										</c:otherwise>
									</c:choose>

									<div class="camping_data">
										<h4>${item.campName }<span class="small-text">(${item.campType })</span>
										</h4>
										<div class="test">
											<p>캠핑장 정보 : ${item.campIntro }</p>
										</div>
										<div>
											<div style="display: inline-block; vertical-align: top; margin-right: 80px;">
												<p>캠핑장 주소: ${item.campAddr}</p>
												<p>등록 시간: ${item.newCampCreateDate}</p>
											</div>
											<div style="display: inline-block; vertical-align: top;">
												<p>등록 여부: ${item.campAddr}</p>
												<p>등록 시간: ${item.newCampCreateDate}</p>
											</div>
										</div>

									</div>
								</div>
								<hr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

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
										<a href="showCampRegistrationList.do?cpage=${ pi.currentPage - 1 }" class="button small">Prev</a>
									</li>
								</c:otherwise>
							</c:choose>

							<c:forEach var="page" begin="${ pi.startPage }" end="${ pi.endPage }">
								<li>
									<c:choose>
										<c:when test="${page eq pi.currentPage}">
											<a class="page active" href="showCampRegistrationList.do?cpage=${ page }">${ page }</a>
										</c:when>
										<c:otherwise>
											<a class="page" href="showCampRegistrationList.do?cpage=${ page }">${ page }</a>
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
										<a href="showCampRegistrationList.do?cpage=${ pi.currentPage + 1 }" class="button small">Next</a>
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
	<script src="../../resources/main/js/jquery.min.js"></script>
	<script src="../../resources/main/js/browser.min.js"></script>
	<script src="../../resources/main/js/breakpoints.min.js"></script>
	<script src="../../resources/main/js/util.js"></script>
	<script src="../../resources/main/js/main.js"></script>
	<script src="../../resources/main/js/detailCampRegistration.js"></script>
	<script src="../../resources/main/js/adminCampRegistration.js"></script>

</body>

</html>