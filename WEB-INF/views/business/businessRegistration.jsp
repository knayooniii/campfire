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
<link rel="stylesheet" href="/resources/main/css/businessRegistration.css" />
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<a href="index.html" class="logo">
						<strong>Campfire</strong> 회원 관리
					</a>

				</header>

				<!-- Content -->
				<section>
					<h4>사업자 승인</h4>
					<form action="/business/insertBusinessRegistration.do" method="POST" id="searchForm">
						<div class="searchBox">
							<input type="text" id="businessNum" name="brNum" class="inputText" placeholder="사업자 등록 번호를 입력하세요">
						</div>
						<div class="searchBox">
							<input type="text" id="businessCreateDate" name="brCreateDate" class="inputText" placeholder="개업일자(YYYYMMDD)">
							<input type="text" id="businessRepName" name="brRepname" class="inputText" placeholder="대표자 성함">
						</div>
						<div class="searchBox">
							<input type="text" id="businessName" name="brCompany" class="inputText" placeholder="상호(주식회사 상호명 또는 상호명)">
							<button type="button" id="searchButton" class="small right-btn">검색</button>
						</div>
					</form>
					<br>
					<div class="exBox">
						<h4>등록 예시 보기</h4>
						<div class="exText">
							<p>사업자등록번호 : 1234567890</p>
							<p>'-'입력 없이 숫자만 입력</p>
							<br>

							<p>개업일자 : 20230320</p>
							<p>등록 되어있는 개업일자를 '-'입력 없이 숫자만 입력</p>
							<br>
							<p>대표명 : 황유빈</p>
							<p>등록 되어있는 대표자 이름을 적어주세요</p>
							<br>
							<p>개업일자 : 주식회사 모닥불 / (주)모닥불 / 모닥불</p>
							<p>주식회사일경우 주식회사 또는 (주)를 상호명 앞 또는 뒤에 붙여주세요, 일반 사업자인경우 상호명만 작성해주세요</p>
						</div>
					</div>

				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<!-- Sidebar -->
		<%@ include file="../common/sidebar.jsp"%>

	</div>

	<!-- Scripts -->
	<script src="/resources/main/js/jquery.min.js"></script>
	<script src="/resources/main/js/browser.min.js"></script>
	<script src="/resources/main/js/breakpoints.min.js"></script>
	<script src="/resources/main/js/util.js"></script>
	<script src="/resources/main/js/main.js"></script>
	<script src="/resources/main//js/api.js"></script>
	<script src="/resources/main//js/businessRegistration.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>

</html>