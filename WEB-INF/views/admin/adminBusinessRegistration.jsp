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
<style>
.section-pagination > ul{
    text-align: center;
}
.text-center{
text-align: center;
}
#refuseForm{
display: flex;
align-items: center;
margin-bottom: 0;
}
#refuseForm > input{
margin-left: 10px;
margin-right: 10px;
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
						<strong>Campfire</strong> 사업자 등록증 승인
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
					<h4>사업자 승인</h4>
					<div class="table-wrapper">

						<table>
							<thead>
								<tr>
									<th class="th-brNum">사업자 번호</th>
									<th class="th-brCompany">회사명</th>
									<th class="th-brRepname">대표명</th>
									<th class="th-brSttCd">사업자 상태</th>
									<th class="th-brCreateDate">개업 일자</th>
									<th class="th-brArSttCd">승인 상태</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty businessList}">
										<tr>
											<td colspan="6">
												<h3 class="text-center">등록된 사업자 등록증이 없습니다.</h3>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${businessList}">
											<tr>
												<td class="td-brNum">${item.brNum }</td>
												<td class="td-brCompany">${item.brCompany }</td>
												<td class="td-brRepname">${item.brRepname }</td>
												<c:if test="${item.brSttCd eq '01'}">
													<td class="td-brSttCd">계속사업자</td>
												</c:if>
												<c:if test="${item.brSttCd eq '02'}">
													<td class="td-brSttCd">휴업자</td>
												</c:if>
												<c:if test="${item.brSttCd eq '03'}">
													<td class="td-brSttCd">폐업자</td>
												</c:if>
												<td class="td-brCreateDate">${item.brCreateDate }</td>
												<c:if test="${item.brArSttCd eq 'Y'}">
													<td class="td-brArSttCd">승인</td>
												</c:if>
												<c:if test="${item.brArSttCd eq 'N'}">
													<td class="td-brArSttCd">승인 대기</td>
												</c:if>
											</tr>
											<tr>
												<td class="td-approval" colspan="3">
													<button type="button" class="button primary small" onclick="goToApproval(${item.brNum})">승인</button>
												</td>
												<td class="td-refuse" colspan="3">
												<form id="refuseForm" action="/adminRegistration/refuseBusinessRegistration.do" method="post" >
													<button type="submit" class="button small">거절</button>
													<input type="hidden" name="brNum" value="${item.brNum }" />
													<input type="text" name="brReason" value="" placeholder="거절 사유 입력하세요"/>
												</form>
												</td>
											</tr>
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
										<a href="showBusinessRegistrationList.do?cpage=${ pi.currentPage - 1 }" class="button small">Prev</a>
									</li>
								</c:otherwise>
							</c:choose>

							<c:forEach var="page" begin="${ pi.startPage }" end="${ pi.endPage }">
								<li>
									<c:choose>
										<c:when test="${page eq pi.currentPage}">
											<a class="page active" href="showBusinessRegistrationList.do?cpage=${ page }">${ page }</a>
										</c:when>
										<c:otherwise>
											<a class="page" href="showBusinessRegistrationList.do?cpage=${ page }">${ page }</a>
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
										<a href="showBusinessRegistrationList.do?cpage=${ pi.currentPage + 1 }" class="button small">Next</a>
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
	<script src="../../resources/main/js/adminBusinessRegistration.js"></script>
	
</body>

</html>