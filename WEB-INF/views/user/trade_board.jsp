<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>

<html>

<head>
	<%@ include file="../common/headContent.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tw/trade_board.css" />
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
						<strong>Campfire</strong> 중고마켓
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
							<div>
								<strong style="font-size: 20px;">${filterInfoText}</strong>
							</div>
							<div class="searchFilterWrap" id="filterAndGoodsListForPush">
								<div class="searchFilterArea">
									<!-- 검색 결과 개수, 상세 검색 버튼 -->
									<div class="filter_default">
										<div class="filter_count">
											<strong class="num" id="totalFilterCount">${listCount}</strong>건
										</div>
										<div class="filter_search">
											<a class="btn_srh" onclick="addFilterBtnEvent(this)"> 상세검색
												<c:choose>
													<c:when test="${isOpenFilterDrawer eq 'true'}">
														<img src="${pageContext.request.contextPath}/resources/images/tw/arrow_drop_up.png"
														alt="arrow">
													</c:when>
													<c:otherwise>
														<img src="${pageContext.request.contextPath}/resources/images/tw/arrow_drop_down.png"
														alt="arrow">
													</c:otherwise>
												</c:choose>
											</a>
										</div>
									</div>
									<div class="filter_items ${isOpenFilterDrawer eq 'true' ? 'on' : ''}">
										<div class="filter_item">
											<h3 class="item_name">카테고리</h3>
											<div class="item_content_wrap">
												<div class="item_content_category">
													<%@ include file="../common/categorySelect.jsp" %>
												</div>
											</div>
										</div>
										<div class="filter_item">
											<h3 class="item_name">키워드</h3>
											<div class="item_content_wrap">
												<div class="item_content_keyword">
													<input type="text" name="demo-name" id="filterKeyword"
														value="${filterKeyword}" placeholder="제목, 내용">
												</div>
											</div>
										</div>
										<div class="filter_item">
											<h3 class="item_name">지역</h3>
											<div class="item_content_wrap">
												<div class="item_content_region">
													<%@ include file="../common/regionSelect.jsp" %>
												</div>
											</div>
										</div>
										<div class="filter_item">
											<h3 class="item_name">가격</h3>
											<div class="item_content_wrap">
												<div class="item_content_price">
													<input type="text" name="" id="filterMinPrice"
														value="${filterMinPrice}" placeholder="₩"> ~ <input
														type="text" name="" id="filterMaxPrice"
														value="${filterMaxPrice}" placeholder="₩">
												</div>
											</div>
										</div>
										<!-- 버튼 -->
										<div class="filter_item filter_btns">
											<a onclick="resetFilter()" class="button">초기화</a>
											<a onclick="applyFilter()" class="button primary">확인</a>
										</div>
									</div>
								</div>
							</div>
							<!-- 검색 결과 보기 방식 -->
							<div class="searchResultsOptContainer">
								<div class="numViewDiv">
									<select class="searchResultsNum" name="" id="filterResultMaxNumber">
										<option value="6" ${filterResultMaxNumber eq '6' ? 'selected' : ''}>6개씩 보기</option>
										<option value="12" ${filterResultMaxNumber eq '12' ? 'selected' : ''}>12개씩 보기</option>
										<option value="24" ${filterResultMaxNumber eq '24' ? 'selected' : ''}>24개씩 보기</option>
										<option value="48" ${filterResultMaxNumber eq '48' ? 'selected' : ''}>48개씩 보기</option>
									</select>
									<c:choose>
										<c:when test="${filterSearchResultsType eq 'grid'}">
											<img class="searchResultsTypeGrid on" id="filterSearchResultsTypeGrid"
											src="${pageContext.request.contextPath}/resources/images/icon2-select.png"
											alt="viewType: image" onclick="addGridViewBtnEvent(this)">
											<img class="searchResultsTypeList" id="filterSearchResultsTypeList"
											src="${pageContext.request.contextPath}/resources/images/icon1.png"
											alt="viewType: list" onclick="addListViewBtnEvent(this)">
										</c:when>
										<c:when test="${filterSearchResultsType eq 'list'}">
											<img class="searchResultsTypeGrid" id="filterSearchResultsTypeGrid"
											src="${pageContext.request.contextPath}/resources/images/icon2.png"
											alt="viewType: image" onclick="addGridViewBtnEvent(this)">
											<img class="searchResultsTypeList on" id="filterSearchResultsTypeList"
											src="${pageContext.request.contextPath}/resources/images/icon1-select.png"
											alt="viewType: list" onclick="addListViewBtnEvent(this)">
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="myTradeDiv">
									<c:choose>
										<c:when test="${sessionMemberIdx != null and sessionMemberIdx != 'null' and filterShowOnlyMine == 'true'}"> 
											<input class="myTradeOpt" type="checkbox" id="filterShowOnlyMine"
											name="demo-copy" checked>
											<label class="myTradeOptLabel"
											for="filterShowOnlyMine">내 물건만 보기</label>	
										</c:when>
										<c:when test="${sessionMemberIdx != null and sessionMemberIdx != 'null' and filterShowOnlyMine == 'false'}">
											<input class="myTradeOpt" type="checkbox" id="filterShowOnlyMine"
											name="demo-copy">
											<label class="myTradeOptLabel"
											for="filterShowOnlyMine">내 물건만 보기</label>	
										</c:when>
										<c:otherwise>
											<input class="myTradeOpt" type="checkbox" id="filterShowOnlyMine" style="visibility: hidden;"
											name="demo-copy">
											<label class="myTradeOptLabel" style="visibility: hidden;"
											for="filterShowOnlyMine">내 물건만 보기</label>	
										</c:otherwise>
									</c:choose>

								</div>
							</div>

							<!-- 게시글 목록 -->
							<div class="box alt">
								<div class="row gtr-50 gtr-uniform">
								<c:choose>
									<c:when test="${empty list}">
										<div class="emptyListTextContainer">
											<h3>등록된 글이 없습니다</h3>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${list}" varStatus="loop">
											<div onclick="detailTrading(this)" data-trading_idx="${list[loop.index].idx}" class="${filterSearchResultsType eq 'grid' ? 'grid-style-item col-6' : 'list-style-item col-12'}">
												<div class="img-box">
													<c:choose>
														<c:when test="${list[loop.index].sold eq false}">
															<img onerror="this.style.display='none'; this.classList.add('img404'); this.nextElementSibling.style.display='block';"
																onload="this.style.display='block'; this.classList.remove('img404'); this.nextElementSibling.style.display='none';"
																src="${pageContext.request.contextPath}/${listOfFileList[loop.index][0].uploadPath.toString()}${listOfFileList[loop.index][0].uploadName.toString()}"
															alt="">
															<h3 style="display: none;">. . . . .</h3>
														</c:when>
														<c:otherwise>
															<div>
																<img src="${pageContext.request.contextPath}/${listOfFileList[loop.index][0].uploadPath.toString()}${listOfFileList[loop.index][0].uploadName.toString()}" alt="">
																<h3>거래 완료</h3>
															</div>
														</c:otherwise>
													</c:choose>
												</div>
												<div class="content-box">
													<p class="my-margin content-title">
														<span class="font-large">${item.sold eq true? '[거래 완료] ' : ''}${item.title}</span>
														<span class="font-small content-view right-align2">${listOfCategory[loop.index].categoryName1}
															${((listOfCategory[loop.index].categoryName1 eq '전체') or (listOfCategory[loop.index].categoryName1 eq '기타')) ? '' : '&gt; '.concat(listOfCategory[loop.index].categoryName2)}</span>
													</p>
													<p class="my-margin content-main multi-line-ellipsis">
														${item.content}
													</p>
													<div class="my-margin content-function">
														<h4>
															${item.price}₩
														</h4>
														<div>
															<img src="${pageContext.request.contextPath}/resources/images/tw/location_on.svg"
															alt="">
															${listOfRegion[loop.index].regionName1} ${listOfRegion[loop.index].regionName1 eq '전국' ?  '' : listOfRegion[loop.index].regionName2}
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								</div>
							</div>
							<!-- 네비게이션 바 및 글쓰기 버튼 -->
							<div class="nav-writ-wrap">
								<%@ include file="../common/navigationBar.jsp" %>
								<a href="/trading/enrollForm.do" class="button primary write_button">
									상품 등록하기
								</a>
							</div>
						</section>
				</div>
			</div>

			<!-- Sidebar -->
			<%@ include file="../common/sidebar.jsp" %>

	</div>
	<!-- Scripts -->
	<%@ include file="../common/scripts.jsp" %>
	<script src="${pageContext.request.contextPath}/resources/js/tw/trade_board.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tw/categorySelect.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/tw/regionSelect.js"></script>

</body>

</html>

<script>
	let intervalId; // setInterval의 반환값을 저장할 변수(레퍼런스)

	document.addEventListener("DOMContentLoaded", function () {
		intervalId = setInterval(refresh404Images, 500); // 0.5초마다 refreshBrokenImages 함수 실행
    });

	function refresh404Images() {
		console.log("refresh404Images() 수행됨");
  		const img404s = document.querySelectorAll('.img404'); // 페이지 내 모든 이미지 선택

		if(img404s.length === 0) { // 더이상 404 이미지가 없으면
			clearInterval(intervalId); // 인터벌 중지
		} else {
			img404s.forEach(img404 => {
				const timestamp = new Date().getTime();
				img404.src = img404.src + '?t=' + timestamp;
			});
		}
	}
</script>

