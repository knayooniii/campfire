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
<title>Camp Search Page</title>
<meta charset="utf-8" />
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="/resources/main/css/campSearch.css" />
<link rel="stylesheet" href="/resources/main/css/map.css" />
<link rel="stylesheet" href="/resources/main/css/tag.css" />
<script src="../../resources/main/js/campSearch.js"></script>
<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>

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
						<strong>Campfire</strong> 캠핑장 찾기
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
				<section>
					<form id="campSearchForm" action="/campSearch/camping.do" method="post">
						<!--검색박스-->
						<div class="top_search_box">
							<div class="tab-btn-box">
								<button type="button" class="tab-button" data-tab-section="tab-section-1" onclick="toggleDetailSearch()">상세 조건 검색</button>
								<button type="button" class="tab-button" data-tab-section="tab-section-2" onclick="toggleTagSearch()">버튼 검색</button>
								<button type="button" class="tab-button" data-tab-section="tab-section-3" onclick="toggleKeywordSearch()">키워드 검색</button>
							</div>
							<!-- 상세 조건 검색 영역 -->

							<div id="campfindtop">
								<ul>
									<br>
									<li class="tt">
										<strong class="title">지역</strong>
										<div class="select_box">
											<!-- 지역 선택 -->
											<label for="c_addr">도/특별시</label>
											<select id="c_addr" name="campAddress" class="detail_select" title="도/특별시">

												<option value="" selected="selected">전체</option>
												<option value="서울" ${campAddress == '서울' ? 'selected' : ''}>서울특별시</option>
												<option value="부산" ${campAddress == '부산' ? 'selected' : ''}>부산광역시</option>
												<option value="대구" ${campAddress == '대구' ? 'selected' : ''}>대구광역시</option>
												<option value="인천" ${campAddress == '인천' ? 'selected' : ''}>인천광역시</option>
												<option value="광주" ${campAddress == '광주' ? 'selected' : ''}>광주광역시</option>
												<option value="대전" ${campAddress == '대전' ? 'selected' : ''}>대전광역시</option>
												<option value="울산" ${campAddress == '울산' ? 'selected' : ''}>울산광역시</option>
												<option value="세종" ${campAddress == '세종' ? 'selected' : ''}>세종특별자치시</option>
												<option value="경기도" ${campAddress == '경기도' ? 'selected' : ''}>경기도</option>
												<option value="강원도" ${campAddress == '강원도' ? 'selected' : ''}>강원도</option>
												<option value="충청북도" ${campAddress == '충청북도' ? 'selected' : ''}>충청북도</option>
												<option value="충청남도" ${campAddress == '충청남도' ? 'selected' : ''}>충청남도</option>
												<option value="전라북도" ${campAddress == '전라북도' ? 'selected' : ''}>전라북도</option>
												<option value="전라남도" ${campAddress == '전라남도' ? 'selected' : ''}>전라남도</option>
												<option value="경상북도" ${campAddress == '경상북도' ? 'selected' : ''}>경상북도</option>
												<option value="경상남도" ${campAddress == '경상남도' ? 'selected' : ''}>경상남도</option>
												<option value="제주도" ${campAddress == '제주도' ? 'selected' : ''}>제주도</option>
											</select>
										</div>
									</li>

									<li>
										<strong class="title"></strong>
										<div class="select_box">
											<!-- 캠핑 유형 선택 -->
											<label for="c_type">캠핑유형</label>
											<select id="c_type" name="campType" class="detail_select" title="테마선택">
												<option value="" selected="selected">전체</option>
												<option value="백패킹" ${campType == '백패킹' ? 'selected' : ''}>백패킹</option>
												<option value="오토캠핑" ${campType == '오토캠핑' ? 'selected' : ''}>오토캠핑</option>
												<option value="글램핑" ${campType == '글램핑' ? 'selected' : ''}>글램핑</option>
												<option value="카라반" ${campType == '카라반' ? 'selected' : ''}>카라반</option>
												<option value="차박" ${campType == '차박' ? 'selected' : ''}>차박</option>
											</select>
										</div>
									</li>


								</ul>
							</div>
							<!-- <nav id="tab-button-nav"></nav> -->
							<!-- 키워드 검색 영역 -->
						</div>
						<div id="keywordSearchBox" style="display: none;">
							<div class="input_search">

								<fieldset class="totalSearch">
									<label for="searchKwd" class="skip">키워드 검색어를 입력하세요.</label>
									<input id="searchKwd" class="m_search_in" name="campKeyword" style="vertical-align: middle;" title="검색어를 입력하세요." placeholder="검색어를 입력하세요." type="text" value="">
									<br>
								</fieldset>
							</div>
						</div>

						<!-- 버검색영역-->
						<div class="tagSearchBox" id="tagSearchBox" style="display: none;">
							<label class="tag_stt">버튼을 클릭하여 검색해보세요. 원하는 유형의 캠핑장 정보를 확인하실 수 있습니다.</label>

							<div class="tag_title"></div>
							<div id="tag">
								<div class="tag-box">
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#야경"><span>#야경</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#물맑은"><span>#물맑은</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#깨끗한"><span>#깨끗한</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#가족"><span>#가족</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#커플"><span>#커플</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#계곡"><span>#계곡</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#바다"><span>#바다</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#축제"><span>#축제</span>
										</label>
									</div>
									<div class="div2" onclick="toggleCheckbox(this)">
										<label onclick="toggleCheckbox(this)"> <input type="checkbox" name="tagItem" value="#힐링"><span>#힐링</span>
										</label>
									</div>
									<br>
								</div>
							</div>
							<br>
							<!--편의시설-->
							<div id="amenity">
								<div class="amenity-box">
									<div class="div1" id="mart" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="마트"><img src="/resources/images/마트.png" alt="" onclick="parentCheckBox('mart')">
										</label> <span>마트</span>
									</div>
									<div class="div1" id="bbq" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="바베큐"><img src="/resources/images/바베큐.png" onclick="parentCheckBox('bbq')" alt="">
										</label> <span>바베큐</span>
									</div>
									<div class="div1" id="walk" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="산책"><img src="/resources/images/산책.png" onclick="parentCheckBox('walk')" alt="">
										</label> <span>산책</span>
									</div>
									<div class="div1" id="swim" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="수영장"><img src="/resources/images/수영장.png" onclick="parentCheckBox('swim')" alt="">
										</label> <span>수영장</span>
									</div>
									<div class="div1" id="wifi" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="와이파이"><img src="/resources/images/와이파이.png" onclick="parentCheckBox('wifi')" alt="">
										</label> <span>와이파이</span>
									</div>
									<div class="div1" id="playground" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="운동장"><img src="/resources/images/운동장.png" onclick="parentCheckBox('playground')" alt="">
										</label> <span>운동장</span>
									</div>
									<div class="div1" id="breakfast" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="조식"><img src="/resources/images/조식.png" onclick="parentCheckBox('breakfast')" alt="">
										</label> <span>조식</span>
									</div>
									<div class="div1" id="tv" onclick="toggleCheckbox(this)">
										<label> <input type="checkbox" name="campAmenity" value="TV"><img src="/resources/images/티비.png" onclick="parentCheckBox('tv')" alt="">
										</label> <span>TV</span>
									</div>
								</div>
								<div class="ckeckbox-btn">
									<button type="button" class="small primary" onclick="showSelectedAmenity(1)">선택</button>
								</div>
							</div>
							<hr>
							<label id="selectedTag">선택된 태그: </label> <label id="selectedAmenity">선택된 편의시설: </label>

						</div>


						<div class="tagSearch">
							<a type="#" class="btn_search" onclick="submitForm()">
								<img src="/resources/images/btn_search.png" style="vertical-align: middle" alt="검색하기"> 검색하기
							</a>
							<a type="#" class="btn_reset" onclick="reset()">
								<img src="/resources/images/btn_reset.png" style="vertical-align: middle" alt="초기화"> 초기화
							</a>
						</div>

						<hr />
						<div class="selectList">
							<select class="form-select" name="selectCategory" id="category" onchange="submitCategory(this.value)" title="정렬하기">
								<option value="campLastUpdate" selected="selected">업데이트순</option>
								<option value="campFirstUpdate" ${category == 'campFirstUpdate' ? 'selected' : ''}>등록일순</option>
								<option value="campViews" ${category == 'campViews' ? 'selected' : ''}>조회순</option>
								<option value="likesCamp" ${category == 'likesCamp' ? 'selected' : ''}>하트순</option>
							</select>
						</div>

						<!-- 검색 및 캠핑장 정보를 표시하는 영역 -->
						<div id="campingInfo" style="display: none;">
							<!-- /resources. (기존 캠핑장 정보를 표시하는 HTML 코드) /resources. -->
						</div>

						<c:choose>
							<c:when test="${empty campList }">
								<div>
									<h4 class="empty">등록된 글이 없습니다.</h4>
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${campList }">
									<div class="camping_info" onclick="goToDetailPage('/campInfo/campInfo.do?campNum=${item.campNum}')">
										<!-- 여기서 '/detail_page_url'은 상세 정보 페이지의 URL을 넣어주어야 합니다. -->
										<img src="${item.campPhotoURL }${item.campPhotoName }" alt="캠핑장 이미지">
										<div class="camping_data">
											<h4>${item.campName }</h4>
											<p>
												<i class="fa-regular fa-heart fa2xs" style="color: #ff8fb6;"></i> ${item.likeCamp}
											</p>
											<p>
												<i class="fa-solid fa-eye fa-xs" style="color: #5c5c5c; margin-right: 3px;"></i> ${item.campView }
											</p>
											<p>${item.campCreateDate }</p>
										</div>
									</div>
									<hr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<div class="section-pagination">
							<ul class="pagination">
								<c:choose>
									<c:when test="${ pi.currentPage eq 1 }">
										<li>
											<a href="#" class="button">Prev</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="camping.do?cpage=${ pi.startPage -1 }" class="button">Prev</a>
										</li>

									</c:otherwise>
								</c:choose>

								<c:forEach var="page" begin="${pi.startPage}" end="${pi.endPage}">
									<li>
										<a class="page" href="camping.do?cpage=${page}">${page} </a>
									</li>
								</c:forEach>

								<c:choose>
									<c:when test="${pi.currentPage eq pi.maxPage}">
										<li>
											<a href="#" class="button">Next</a>
										</li>

									</c:when>
									<c:when test="${pi.endPage eq pi.maxPage}">
										<li>
											<a href="camping.do?cpage=${pi.maxPage}" class="button">Next</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="camping.do?cpage=${pi.currentPage + 1}&campAmenity=${campAmenity}&searchTxt=${searchTxt}&campTag=${campTag}&campAddress${campAdderess}&category${category}&campKeyword${campKeyword}&campType${campType}" class="button">Next</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</form>
					<hr>
					<h2>지도로 캠핑장 검색하기</h2>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b7e3f9163cbe6490f97df41fe3fab55&libraries=services"></script>
					<div class="map_wrap">
						<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
						<div id="menu_wrap" class="bg_white">
							<div class="option">
								<div>
									<form onsubmit="searchPlaces(); return false;">
										키워드 :
										<input type="text" value="캠핑장" id="keyword" size="15">
										<button type="submit">검색하기</button>
									</form>
								</div>
							</div>
							<hr>
							<ul id="placesList"></ul>
							<div id="pagination"></div>
						</div>
					</div>
					<div id="map"></div>
				</section>
			</div>
		</div>
		<%@ include file="../common/sidebar.jsp"%>
	</div>



	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/resources/main/js/jquery.min.js"></script>
	<script src="/resources/main/js/browser.min.js"></script>
	<script src="/resources/main/js/breakpoints.min.js"></script>
	<script src="/resources/main/js/util.js"></script>
	<script src="/resources/main/js/main.js"></script>
	<script src="/resources/main/js/map.js"></script>
	<script src="/resources/main/js/tag.js"></script>
</body>

</html>