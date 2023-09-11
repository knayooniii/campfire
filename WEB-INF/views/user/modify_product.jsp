<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>

<html>

<head>
	<%@ include file="../common/headContent.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tw/enroll_product.css" />
</head>

<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">
		<%@ include file="../chat/chat.jsp" %>
		<!-- Main -->
		<div id="main">
			<div class="inner">
				<!-- Header -->
				<header id="header">
					<a href="/campSearch/camping.do" class="logo">
						<strong>Campfire</strong> 상품 수정
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
				<!-- [Section → (Divider) → (Wrapper) → (Divider) → Container → (Divider) → Block or etc.] -->
				<!-- 'Divider' have no id -->
				<section>
					<strong style="font-size: 20px;">상품 수정</strong>
					<div class="columnDivider">
						<div class="wrapper" id="firstColumnWrapper">
							<div class="container" id="categoryContainer">
								<div class="labelFormDivider">
									<h3 class="label">카테고리</h3>
									<div class="form">
										<%@ include file="../common/categorySelect.jsp" %>
									</div>
								</div>
							</div>
							<div class="container" id="regionContainer">
								<div class="labelFormDivider">
									<h3 class="label">지역</h3>
									<div class="form">
										<%@ include file="../common/regionSelect.jsp" %>
									</div>
								</div>
							</div>
							<div class="container" id="priceContainer">
								<div class="labelFormDivider">
									<h3 class="label">가격</h3>
									<input class="form" type="text" name="demo-name" id="demo-name" value="${trading.price}"
										placeholder="₩">
								</div>
							</div>
						</div>
						<div class="wrapper" id="secondColumnWrapper">
							<div class="container" id="fileContainer">
								<div class="labelFormDivider">
									<h3 class="label">사진 첨부</h3>
									<div class="form">
										<input type="file" onchange="addFile(this)" name="upload" multiple accept="image/*"/>
										<div class="fileList">
											<c:forEach items="${listOfFile}" var="fileItem" varStatus="loop">
												<div id="file${loop.index}" class="fileBox oldFile" data-file_idx="${fileItem.idx}">
													<p class="name">${fileItem.uploadOriginName}</p>
													<a class="delete" onclick="deleteFile('${loop.index}');">
														<i class="far fa-minus-square"></i>
													</a>
												</div>
											</c:forEach>
											<!-- <li><a onclick="pageButton(this)" data-value="${page}" class="navigation page ${page eq pi.currentPage ? 'active' : ''}">${page}</a></li> -->
											<!-- <div id="file99" class="fileBox">
												<p class="name">sample.png</p>
												<a class="delete" onclick="deleteFile(99);">
													<i class="far fa-minus-square"></i>
												</a>
											</div> -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="container" id="titleContainer">
						<div class="labelFormDivider">
							<h3 class="label">제목</h3>
							<input type="text" class="form" name="demo-name" id="demo-name" value="${trading.title}" placeholder="제목">
						</div>
					</div>


					<div class="container" id="contentContainer">
						<textarea name="demo-message" id="demo-message" placeholder="내용" rows="6">${trading.content}</textarea>
					</div>

					
						<div class="container" id="actionButtonContainer">
							<div class="buttonBlock">
								<a onclick="history.back()" class="button">취소</a>
								<a onclick="submitForm('${trading.idx}')" class="button primary">수정</a>
							</div>
						</div>
					
				</section>
			</div>
		</div>

		<!-- Sidebar -->
		<%@ include file="../common/sidebar.jsp" %>

	</div>
	<!-- Scripts -->
	<%@ include file="../common/scripts.jsp" %>
	<!-- <script src="${pageContext.request.contextPath}/resources/js/tw/enroll_product.js"></script> -->
	<script src="${pageContext.request.contextPath}/resources/js/tw/modify_product.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tw/categorySelect.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tw/regionSelect.js"></script>
</body>

</html>