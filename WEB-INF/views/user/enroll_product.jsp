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
		<!-- Main -->
		<div id="main">
			<div class="inner">
				<!-- Header -->
				<%@ include file="../common/header.jsp" %>

				<!-- Content -->
				<!-- [Section → (Divider) → (Wrapper) → (Divider) → Container → (Divider) → Block or etc.] -->
				<!-- 'Divider' have no id -->
				<section>
					<strong style="font-size: 20px;">상품 등록</strong>
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
									<input class="form" type="text" name="demo-name" id="demo-name" value=""
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
							<input type="text" class="form" name="demo-name" id="demo-name" value="" placeholder="제목">
						</div>
					</div>


					<div class="container" id="contentContainer">
						<textarea name="demo-message" id="demo-message" placeholder="내용" rows="6"></textarea>
					</div>

					
						<div class="container" id="actionButtonContainer">
							<div class="buttonBlock">
								<a href="history.back()" class="button">취소</a>
								<a onclick="submitForm()" class="button primary">등록</a>
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
	<script src="${pageContext.request.contextPath}/resources/js/tw/enroll_product.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tw/categorySelect.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tw/regionSelect.js"></script>
</body>

</html>