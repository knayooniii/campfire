<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>

<head>
<title>Camp Search Page</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0" />
<%@ include file="../common/head.jsp"%>
 <link rel="stylesheet" href="/resources/login/css/search_id.css" />  
 <link rel="stylesheet" href="/resources/login/css/mypage.css" />  
<link rel="stylesheet" href="/resources/main/css/campSearch.css" />
<link rel="stylesheet" href="/resources/main/css/tag.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>

<body class="is-preload">

<div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>아이디 조회 결과</h2>
            </div>
            <div class="close-area">X</div>
            <div class="content" id="id_value"></div>
        </div>
    </div>

		<%@ include file="../common/sidebar.jsp"%>
		<!-- Scripts -->
	
		<script src="/resources/login/js/pwup.js"></script>
		<script src="/resources/main/js/jquery.min.js"></script>
		<script src="/resources/main/js/browser.min.js"></script>
		<script src="/resources/main/js/breakpoints.min.js"></script>
		<script src="/resources/main/js/util.js"></script>
		<script src="/resources/main/js/main.js"></script>
		<script src="/resources/main/js/tag.js"></script>
			<script
		src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>

</html>