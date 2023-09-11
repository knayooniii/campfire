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
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" href="/resources/main/css/amenityCheck.css">
	<link rel="stylesheet" href="/resources/main/css/tagCheck.css">
	<link rel="stylesheet" href="/resources/main/css/registrationForm.css">
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
						<strong>Campfire</strong> 캠핑장 등록
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
					<form id="myForm" action="/business/registration/insertCampRegistration.do" method="POST" enctype="multipart/form-data">
						<div class="camp-info">
							<div id="main-img-box">
								<p id="explanation">(아래에서 사진을 추가해 주세요)</p>
							</div>
							<div class="photo-box"></div>
							<div class="camp-table-box">
								<table class="table">
									<colgroup>
										<col style="width: 30%;">
										<col style="width: 70%;">
									</colgroup>
									<tbody>
									<tr class="camp_call_pcVer">
											<th scope="col">캠핑장 이름<span class="required-field">*</span></th>
											<td><input type="text" placeholder="" name="campName" ></td>
										</tr>
										<tr>
											<th scope="col">우편번호<span class="required-field">*</span></th>
											<td class="input-container">
												<input type="text" id="postcode" name="campPostCode" >
												<button type="button" class="button fit small"
													onclick="searchPostCode()">우편번호 찾기</button>
											</td>
										</tr>
										<tr>
											<th scope="col">주소<span class="required-field">*</span></th>
											<td>
												<input type="text" id="address" name="campAddr">
												<input type="text" id="x" name="campX" value="-1" style="display:none;">
												<input type="text" id="y" name="campY" value="-1" style="display:none;">
											</td>
										</tr>
										<tr class="camp_call_pcVer">
											<th scope="col">전화번호<span class="required-field">*</span></th>
											<td><input type="text" name="campPhoneNum" placeholder="'-' 빼고 입력해 주세요"></td>
										</tr>
										<tr>
											<th scope="col">캠핑장 유형<span class="required-field">*</span></th>
											<td>
												<select id="camp-type" name="campType">
													<option value="">캠핑장 유형을 골라주세요</option>
													<option value="글램핑">글램핑</option>
													<option value="카라반">카라반</option>
													<option value="오토캠핑">오토캠핑</option>
													<option value="백패킹">백패킹</option>
													<option value="차박">차박</option>
												</select>
											</td>
										</tr>

										<tr>
											<th scope="col">홈페이지</th>
											<td>
												<input type="text" value="" name="campLink" placeholder="링크를 입력해 주세요">
											</td>
										</tr>
										<tr>
											<th scope="col">주변 이용시설</th>
											<td>
												<input type="text" value="" name="campAPlace" placeholder="주변에 이용 가능한 시설">
											</td>
										</tr>
										<tr>
											<th scope="col">편의시설</th>
											<td>
												<button type="button" class="button fit small amenity-btn" id="d-btn"
													onclick="showAmenity()">편의시설 선택하기</button>
												<p class="no-margin" id="selectedAmenity">
												</p>
											</td>
										</tr>
										<tr>
											<th scope="col">태그</th>
											<td>
												<button type="button" class="button fit small tag-btn"
													onclick="showTag()">태그
													선택하기</button>
												<p class="no-margin" id="selectedTag"></p>
											</td>
										</tr>
									</tbody>
								</table>
								<!-- 편의시설 -->
								<div id="amenity">
									<div class="amenity-box">
										<div class="div1" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campAmenity" value="마트"><img
													src="/resources/images/mart.png" alt="">
											</label>
											<span>마트</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="바베큐"><img
													src="/resources/images/bbq.png" alt="">
											</label>
											<span>바베큐</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="산책"><img
													src="/resources/images/walk.png" alt="">
											</label>
											<span>산책</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="수영장"><img
													src="/resources/images/swimmingpool.png" alt="">
											</label>
											<span>수영장</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="와이파이"><img
													src="/resources/images/wifi.png" alt="">
											</label>
											<span>와이파이</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="운동장"><img
													src="/resources/images/ground.png" alt="">
											</label>
											<span>운동장</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="조식"><img
													src="/resources/images/breakfast.png" alt="">
											</label>
											<span>조식</span>
										</div>
										<div class="div1" onclick="toggleCheckbox(this)">
											<label>
												<input type="checkbox" name="campAmenity" value="TV"><img
													src="/resources/images/TV.png" alt="">
											</label>
											<span>TV</span>
										</div>
									</div>
									<button type="button" class="small primary right-btn"
										onclick="showSelectedAmenity()">선택</button>
									<button type="button" class="small right-btn"
										onclick="closeSelectedAmenity()">취소</button>
								</div>

								<!-- 태그 -->
								<div id="tag">
									<div class="tag-box">
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#야경"><span>#야경</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#등산"><span>#등산</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#깨끗한"><span>#깨끗한</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#가족"><span>#가족</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#커플"><span>#커플</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#계곡"><span>#계곡</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#바다"><span>#바다</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#축제"><span>#축제</span>
											</label>
										</div>
										<div class="div2" onclick="toggleCheckbox(this)">
											<label onclick="toggleCheckbox(this)">
												<input type="checkbox" name="campTag" value="#힐링"><span>#힐링</span>
											</label>
										</div>
									</div>
									<button type="button" class="small primary right-btn"
										onclick="showSelectedTag()">선택</button>
									<button type="button" class="small right-btn"
										onclick="closeSelectedTag()">취소</button>
								</div>
							</div>

						</div>
						<hr>
						<!-- 사진선택 박스 -->
						<div id="photo-select-box">
							<br>
							<div id="text-box">
								<h3>사진 추가하기</h3>
								<p>(첫번째 사진은 자동으로 메인 사진이 됩니다)</p>
							</div>
							<div id="previewContainer">
							</div>
							<div class="centered-container">
								<input type="file" id="fileInput" multiple style="display: none;">
								<button type="button" class="button small btn-a" onclick="addMorePhotos()">사진
									추가</button>
								<button type="button" class="button small btn-a" onclick="deleteSelectedPhotos()">사진
									지우기</button>
								<button type="button" class="button small btn-r" onclick="resetPhotos()">초기화</button>
							</div>
						</div>
						<hr>
						<div id="camp-text">
							<h3>캠핑장 소개</h3>
							<textarea name="campIntro" id="" cols="30" rows="5" placeholder="캠핑장 소개를 적어 주세요"></textarea>
						</div>

						<hr>
						<div id="camp-text">
							<h3>가격 정보</h3>
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
											<th colspan="2" scope="colgroup">평상시</th>
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
											<th scope="col">가격 입력</th>
											<td data-cell-header="평상시 주중："><input id="campOffsdPrice" name="campOffsdPrice" type="text"  placeholder="숫자만 입력해 주세요"></td>
											<td data-cell-header="평상시 주말："><input id="campOffswPrice" name="campOffswPrice" type="text" placeholder="숫자만 입력해 주세요"></td>
											<td data-cell-header="성수기 주중："><input id="campSdPrice" name="campSdPrice" type="text" placeholder="숫자만 입력해 주세요"></td>
											<td data-cell-header="성수기 주말："><input id="campSwPrice" name="campSwPrice" type="text" placeholder="숫자만 입력해 주세요"></td>
										</tr>
									</tbody>
								</table>
							</div>

						</div>
						<button type="button" class="button small right-btn" onclick="submitForm()">등록</button>
					</form>

				</section>
			</div>
		</div>



		<!-- Sidebar -->
		<%@ include file="../common/sidebar.jsp"%>

	</div>

	<!-- Scripts -->
	 <script src="/resources/main/js/jquery.min.js"></script>
	<script src="/resources/main/js/browser.min.js"></script>
	<script src="/resources/main/js/breakpoints.min.js"></script>
	<script src="/resources/main/js/util.js"></script>
	<script src="/resources/main/js/main.js"></script>
	<script src="/resources/main/js/amenityCheck.js"></script>
	<script src="/resources/main/js/tagCheck.js"></script>
	<script src="/resources/main/js/registrationForm.js"></script> 
	<!-- 우편번호 js -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/resources/main/js/postCode.js"></script>
	<script src="/resources/main/js/api.js"></script>

</body>

</html>