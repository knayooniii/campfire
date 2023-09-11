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
<link rel="stylesheet" href="/resources/main/css/businessList.css" />
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
						<strong>Campfire</strong> 사업자 등록증
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
					<h4>사업자 등록증 정보</h4>
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
									<c:if test="${businessList.brArSttCd eq 'R'}">
										<th class="th-brReason" colspan="2">거절 사유</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="td-brNum">${businessList.brNum }</td>
									<td class="td-brCompany">${businessList.brCompany }</td>
									<td class="td-brRepname">${businessList.brRepname }</td>
									<c:if test="${businessList.brSttCd eq '01'}">
										<td class="td-brSttCd">계속사업자</td>
									</c:if>
									<c:if test="${businessList.brSttCd eq '02'}">
										<td class="td-brSttCd">휴업자</td>
									</c:if>
									<c:if test="${businessList.brSttCd eq '03'}">
										<td class="td-brSttCd">폐업자</td>
									</c:if>
									<td class="td-brCreateDate">${businessList.brCreateDate }</td>
									<c:if test="${businessList.brArSttCd eq 'Y'}">
										<td class="td-brArSttCd">승인</td>
									</c:if>
									<c:if test="${businessList.brArSttCd eq 'N'}">
										<td class="td-brArSttCd">승인 대기</td>
									</c:if>
									<c:if test="${businessList.brArSttCd eq 'R'}">
										<td class="td-brArSttCd">거절</td>
										<td class="td-brReason" colspan="2">${businessList.brReason }</td>
									</c:if>
								</tr>
							</tbody>
						</table>
						<button class="button primary small right-btn" id="deleteBusiness">삭제</button>
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
	<script src="../../resources/main/js/businessList.js"></script>

</body>

</html>