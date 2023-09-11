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

<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="/resources/main/css/campInfo.css" />
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
						<strong>Campfire</strong> 캠핑장 정보
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
					<div id="sub_title_wrap">

						<div class="layout">

							<span class="skip"> ${result.campName }</span>
							<div class="campinfo-title">
								<c:choose>
									<c:when test="${checkLike eq 1}">
										<i id="heart" class="fa-solid fa-heart fa-xl" data-jstl-value="${result.campNum}" style="color: #ff8fb6;"></i>
									</c:when>
									<c:otherwise>
										<i id="heart" class="fa-regular fa-heart fa-xl" data-jstl-value="${result.campNum}" style="color: #ff8fb6;"></i>
									</c:otherwise>
								</c:choose>
								<span id="heart-count">${result.likeCamp }</span> <i class="fa-solid fa-eye fa-lg" style="color: #5c5c5c; margin-right: 3px;"></i><span>${result.campView }</span>

								<c:choose>
									<c:when test="${checkWishlist eq 1}">
										<i id="star" class="fa-solid fa-star fa-xl" data-jstl-value="${result.campNum}" style="color: #fff370;"></i>
									</c:when>
									<c:otherwise>
										<i id="star" class="fa-regular fa-star fa-xl" data-jstl-value="${result.campNum}" style="color: #fff370;"></i>
									</c:otherwise>
								</c:choose>
								<span id="star-count">${result.wishlist }</span>

							</div>

							<br> <br>
							<!-- 다른 사용자들의 정보도 추가 가능 -->
							<!--사진-->
							<div class="carousel-container">
								<c:choose>
									<c:when test="${empty campPhotoList }">
										<div>
											<h4>등록된 사진이 없습니다.</h4>
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-slider">
											<c:forEach var="item" items="${campPhotoList }">
												<!-- 각 슬라이드 아이템들을 추가합니다. -->
												<div class="carousel-slide active">
													<img src="${item.campPhotoURL }${item.campPhotoName }" alt="${item.campPhotoName }" class="img-fluid">
												</div>

											</c:forEach>
										</div>
									</c:otherwise>
								</c:choose>

								<!-- 좌우 슬라이드 버튼 -->
								<button class="carousel-btn-prev" onclick="moveSlide(-1)">&#10094;</button>
								<button class="carousel-btn-next" onclick="moveSlide(1)">&#10095;</button>
							</div>


							<table class="table">
								<colgroup>
									<col style="width: 30%;">
									<col style="width: 70%;">
								</colgroup>
								<br>
								<tbody>
									<tr>
										<th scope="col">주소</th>
										<td>${result.campAddr }</td>
									</tr>
									<tr class="camp_call_pcVer">
										<th scope="col">문의처</th>
										<td>${result.campPhoneNum }</td>
									</tr>
									<tr>
										<th scope="col">캠핑장 유형</th>
										<td>${result.campType }</td>
									</tr>
									<tr>
										<th scope="col">홈페이지</th>
										<td>
											<c:choose>
												<c:when test="${not empty result.campLink}">
													<a href="${result.campLink}" target="_BLANK" title="새창열림">홈페이지 바로가기</a>

												</c:when>
												<c:otherwise>
													<!-- 링크가 없을 경우 아무것도 표시하지 않음 -->
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th scope="col">태그</th>
										<td>
											<c:forEach var="item" items="${campTagList}">
											${item.campTag}
										</c:forEach>
										</td>
									</tr>
									<tr>
										<th scope="col">주변이용가능시설</th>
										<td>산책로, 고복저수지, 송학산</td>
									</tr>
								</tbody>
							</table>
							<!--//타이틀-->

						</div>

					</div>
					<!-- 상세정보 바 -->
					<div class="campinfo-bar">
						<ul>
							<li id="c_intro">캠핑장 소개</li>
							<li id="c_amenity">시설정보</li>
							<li id="c_map">위치</li>
							<li id="c_review">캠핑 후기</li>
						</ul>
					</div>


					<div class="camp_intro">
						<p class="camp_intro_txt">
							<span> ${result.campIntro} <!-- 다온숲에 오신 것을 환영합니다. 다온 이란 뜻은 "좋은 모든 일들이 다 온다” 는 순
								우리말입니다. 저희 임직원 일동은 고객님께 항상 좋은 일들이 가득하시길 기원합니다. 더불어 사회와 가족과 개인적인
								일상속에서 항상 건강하고 유쾌한 삶이 되시길 바랍니다. -->
							</span>
						</p>
						<hr>
					</div>
					<div class="camp_intro_amenity">
						<div id="amenity">
							<h4 class="icon_h3">캠핑장 시설정보</h4>

							<div class="amenity-box">

								<c:forEach var="item" items="${campAmenityList}">
									<div class="div1">

										<img src="${item.campAmenity}"> <span style="margin: 0">${item.campAmenityName}</span>
									</div>
								</c:forEach>
							</div>
							<!-- <div class="div1">
									<img src="/resources/images/마트.png" alt=""> <span>마트</span>
								</div>
								<div class="div1">
									<img src="/resources/images/바베큐.png" alt=""> <span>바베큐</span>
								</div>
								<div class="div1">

									<img src="/resources/images/산책.png" alt=""> <span>산책</span>
								</div>
								<div class="div1">
									<img src="/resources/images/와이파이.png" alt=""> <span>와이파이</span>
								</div>
								<div class="div1">
									<img src="/resources/images/수영장.png" alt=""> <span>수영장</span>
								</div>
								<br>
							</div> -->
							<p class="camp_intro_txt">
								<span class="info_notice"> * campfire에 등록된 정보는 현장상황과 다소 다를 수 있으니 반려동물 동반 여부, 부가 시설물,추가차량 등 원활한 캠핑을 위해 꼭 필요한 사항은 해당 캠핑장에 미리 확인하시기 바랍니다. </span>
							</p>
						</div>
						<hr>
					</div>
					<div class="camp_intro_map">
						<div id="mapX" data-jstl-value="${result.campX}" style="display: none;"></div>
						<div id="mapY" data-jstl-value="${result.campY}" style="display: none;"></div>
						<div id="campName" data-jstl-value="${result.campName}" style="display: none;"></div>
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b7e3f9163cbe6490f97df41fe3fab55&libraries=services"></script>
						<div class="map_wrap">
							<div id="map" style="overflow: hidden;"></div>
						</div>
						<hr>
					</div>
					<div class="camp_intro_review">
						<div class="comment-container">
							<h2>댓글</h2>
							<c:choose>
								<c:when test="${empty replyList}">
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${replyList}">
										<div class="comment-list" id="comment-list-${item.postReplyNum }">
											<div class="comment">
												<div class="comment-user">
													<i class="fa-solid fa-tree fa-xl" style="color: #155b3a;"></i> ${item.memberId }
												</div>
												<div class="comment-date">${item.newDate }</div>
											</div>
											<div class="comment comment-content1">
												<div class="comment-content">${item.postReplyContent }</div>
												<div class="comment-like">
													<i class="fa-regular fa-thumbs-up" style="color: #1e6bf1;" onclick="likePostReply('${item.postReplyNum}')"></i> <span id="comment-like-count${item.postReplyNum}">${item.replyLikeCount }</span>
													<c:if test="${sessionScope.memberNum == item.memberNum}">
														<i class="fa-solid fa-trash-can" style="color: #8a8a8a;" onclick="deleteReply('${item.postReplyNum}')"></i>
													</c:if>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="comment-form">
							<input type="text" id="comment-content" data-campNum-value="${result.campNum }" placeholder="댓글을 입력하세요">
							<button type="button" class="button small insert-reqly">등록</button>
						</div>
						<hr>
					</div>
					<strong>* 가격정보</strong>
					<div class="camp_intro">
						<!--가격 테이블-->
						<div class="table_w">
							<table class="table camp_info_tb">
								<caption>
									<!-- <strong>글램핑 가격 테이블입니다.</strong> -->
									<!-- <p>글램핑의 평상시의 주중, 주말과 성수기의 주중, 주말로 나뉘어 설명합니다.</p> -->
								</caption>
								<colgroup>
									<col style="width: 20%">
									<col style="width: 20%">
									<col style="width: 20%">
									<col style="width: 20%">
									<col style="width: 20%">
								</colgroup>
								<thead>
									<tr>
										<th rowspan="2" scope="col">구분</th>
										<th colspan="2" scope="colgroup">비성수기</th>
										<th colspan="2" scope="colgroup">성수기</th>
									</tr>

									<tr>
										<th scope="col" class="gray">주중</th>
										<th scope="col" class="gray">주말</th>
										<th scope="col" class="gray">주중</th>
										<th scope="col" class="gray">주말</th>
									</tr>
								</thead>
								<tbody class="t_c">
									<tr>
										<th scope="col">글램핑</th>
										<td data-cell-header="비성수기 주중：">${result.campOffsdPrice }</td>
										<td data-cell-header="비성수기 주말：">${result.campOffswPrice }</td>
										<td data-cell-header="성수기 주중：">${result.campSdPrice }</td>
										<td data-cell-header="성수기 주말：">${result.campSwPrice }</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!--//가격 테이블-->
					</div>

					<hr>
					<div class="box_photo">
						<div class="grid-container">
							<c:choose>
								<c:when test="${empty campPhotoList }">
									<div>
										<h4>등록된 사진이 없습니다.</h4>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${campPhotoList }">
										<div class="grid-item">
											<img src="${item.campPhotoURL }${item.campPhotoName }" alt="${item.campPhotoName }" width="100%" height="auto" data-index="0">
										</div>

									</c:forEach>
								</c:otherwise>
							</c:choose>


							<!-- 확대 보기를 위한 모달 박스 -->
							<div id="modal" class="modal">
								<div class="close">&times;</div>
								<div class="modal-content">
									<div class="prev" onclick="changeImage(-1)">&#10094;</div>
									<img id="modal-image" src="" alt="확대 보기">
									<div class="next" onclick="changeImage(1)">&#10095;</div>
								</div>
							</div>
						</div>
					</div>
					<div>
						<hr>
						<button class="button small primary" style="float: right; margin-right: 15px;" onclick="history.back()">목록으로</button>
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
	<script src="../../resources/main/js/campInfo.js"></script>
	<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>
	<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script> -->
</body>

</html>