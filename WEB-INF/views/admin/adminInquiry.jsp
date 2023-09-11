<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="/resources/main/css/inquiry.css" />
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<!-- Header -->
				<header id="header">
					<a href="/campSearch/camping.do" class="logo">
						<strong>Campfire</strong> 문의사항 답변
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
					<div class="section-header">
						<h4>문의사항 답변</h4>
						<select name="category" id="category" onchange="handleCategoryChange(this)">
							<c:choose>
								<c:when test="${allList eq 'Y'}">
									<option value="/admin/showAdminInquiry.do?allList=N">답변할 목록 보기</option>
									<option value="/admin/showAdminInquiry.do?allList=Y" selected>전체 목록 보기</option>
								</c:when>
								<c:otherwise>
									<option value="/admin/showAdminInquiry.do?allList=N" selected>답변할 목록 보기</option>
									<option value="/admin/showAdminInquiry.do?allList=Y">전체 목록 보기</option>
								</c:otherwise>
							</c:choose>


						</select>
						<script>
							function handleCategoryChange(selectElement) {
								var selectedValue = selectElement.value;
								if (selectedValue) {
									window.location.href = selectedValue;
								}
							}
						</script>
					</div>
					<div class="table-wrapper">
						<!-- <ul class="list_tap">
							<li>전체</li>
							<li>처리할 목록</li>
						</ul> -->
						<table>
							<thead>
								<tr>
									<th class="td-num">번호</th>
									<th class="td-title">제목</th>
									<th class="td-id">아이디</th>
									<th class="td-time">시간</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty list}">
										<tr>
											<td colspan="4">
												<h3 class="text-center">등록된 글이 없습니다.</h3>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${list}">
											<tr onclick="location.href='/admin/detailAdminInquiry.do?inNum=${item.inquiryNum}'">
												<td class="td-num">${row}</td>
												<td class="td-title">${item.inquiryTitle}</td>
												<td class="td-id">${item.memberId}</td>
												<td class="td-time">${item.inquiryNewDate}</td>
											</tr>
											<c:if test="${item.inquiryAnswerFL eq 'Y'}">
												<tr class="tr-reply" onclick="location.href='/admin/detailAdminInquiry.do?inNum=${item.inquiryNum}'">
													<td class="td-num"></td>
													<td class="td-title">&rarr;[답글]${item.answerTitle }</td>
													<td class="td-id">${item.answerAdmin }</td>
													<td class="td-time">${item.answerNewDate }</td>
												</tr>
											</c:if>
											<c:set var="row" value="${row-1}" />
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
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
										<a href="showAdminInquiry.do?cpage=${ pi.currentPage - 1 }&allList=${allList}" class="button small">Prev</a>
									</li>
								</c:otherwise>
							</c:choose>

							<c:forEach var="page" begin="${ pi.startPage }" end="${ pi.endPage }">
								<li>
									<c:choose>
										<c:when test="${page eq pi.currentPage}">
											<a class="page active" href="showAdminInquiry.do?cpage=${ page }&allList=${allList}">${ page }</a>
										</c:when>
										<c:otherwise>
											<a class="page" href="showAdminInquiry.do?cpage=${ page }&allList=${allList}">${ page }</a>
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
										<a href="showAdminInquiry.do?cpage=${ pi.currentPage + 1 }&allList=${allList}" class="button small">Next</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</section>

			</div>
		</div>

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