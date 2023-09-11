<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>

<html>

<head>
	<%@ include file="../common/headContent.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tw/product_detail.css" />
</head>

<body class="is-preload">

    <!-- Wrapper -->
    <div id="wrapper">
        <%@ include file="../chat/chat.jsp" %>
        <div id="overlay">
            <div class="container" id="imageModalContainer">
                <img id="imageModal" onclick=""></img>
            </div>
            <!-- <c:choose>
                <c:when test="${fn:length(listOfFile) > 1}">
                    <a class="imageBackwardButton" onclick="prevImgBtnClick()"></a>
                    <a class="imageForwardButton" onclick="nextImgBtnClick()"></a>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose> -->
        </div>
        <!-- Main -->
        <div id="main">
            <div class="inner">

                	<!-- Header -->
				<header id="header">
					<a href="/campSearch/camping.do" class="logo">
						<strong>Campfire</strong> 상품 상세 보기
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
                    <!-- [Section → (Divider) → (Wrapper) → (Divider) → Container → (Divider) → Block or etc.] -->
                    <!-- 'Divider' have no id -->
                    <div class="wrapper" id="firstRowWrapper">
                        <div class="container" id="backButtonContainer">
                            <a class="button" href="/trading/list.do?currentPage=${backPageNumber}">뒤로가기</a>
                        </div>
                        <div class="titleSellerDivider">
                            <div class="container" id="titleContainer">
                                <strong>${trading.sold eq true? '[거래 완료] ' : ''}${trading.title}</strong>
                            </div>
                            <div class="container" id="sellerContainer">
                                <h4>판매자</h4>
                                    <h3>${member.name}</h3>
                            </div>
                        </div>
                    </div>
                    <div class="wrapper" id="categoryRegionWrapper">
                        <div class="container" id="categoryContainer">
                            <div class="labelFormDivider">
                                <h3 class="label">카테고리</h3>
                                <div class="form">
                                    <select class="disabled" name="" id="">
                                        <option value="1">${category.categoryName1}</option>
                                    </select>
                                    <select class="disabled" name="" id="">
                                        <option value="1">${category.categoryName2}</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="container" id="regionContainer">
                            <div class="labelFormDivider">
                                <h3 class="label">지역</h3>
                                <div class="form">
                                    <select class="disabled" name="" id="">
                                        <option value="1">${region.regionName1}</option>
                                    </select>
                                    <select class="disabled" name="" id="">
                                        <option value="1">${region.regionName2}</option>

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${fn:length(listOfFile) != 0}">
                            <div class="focusedImageImageListDivider">
                                <div class="container" id="focusedImageContainer">
                                    <div class="zIndexDivider">
                                        <div class="zIndex0">
                                            <!-- <img onclick="openImageModal()" src="${pageContext.request.contextPath}/${listOfFile.get(0).uploadPath.toString()}${listOfFile.get(0).uploadName.toString()}" alt="product_image"> -->
                                            <img onclick="openImageModal()" src="${pageContext.request.contextPath}/${listOfFile.get(0).uploadPath.toString()}${listOfFile.get(0).uploadName.toString()}" alt="product_image"
                                                onerror="this.style.display='none'; this.classList.add('img404'); this.nextElementSibling.style.display='block';"
                                                onload="this.style.display='block'; this.classList.remove('img404'); this.nextElementSibling.style.display='none';">
                                            <h3 style="display: none;"> . . . . . </h3>                                        
                                        </div>
                                        <div></div>
                                        <div class="zIndex1">
                                            <c:choose>
                                                <c:when test="${fn:length(listOfFile) > 1}">
                                                    <a class="imageBackwardButton" onclick="prevImgBtnClick()"></a>
                                                    <a class="imageForwardButton" onclick="nextImgBtnClick()"></a>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${fn:length(listOfFile) gt 1}">
                                        <div class="container" id="imageListContainer" data-lotate_image_max_index="${listOfFile.size() - 1}">
                                            <c:forEach var="i" begin="0" end="${listOfFile.size() - 1}">
                                                <!-- <img onclick="repositionFocusImage(this)" class="lotateImage ${i eq 0 ? 'focused' : ''}" data-lotate_image_index="${i}" src="${pageContext.request.contextPath}/${listOfFile.get(i).uploadPath.toString()}${listOfFile.get(i).uploadName.toString()}" alt=""> -->
                                                <div>
                                                    <img onclick="repositionFocusImage(this)" class="lotateImage ${i eq 0 ? 'focused' : ''}" data-lotate_image_index="${i}" src="${pageContext.request.contextPath}/${listOfFile.get(i).uploadPath.toString()}${listOfFile.get(i).uploadName.toString()}" alt=""
                                                        onerror="this.style.display='none'; this.classList.add('img404'); this.nextElementSibling.style.display='block';"
                                                        onload="this.style.display='block'; this.classList.remove('img404'); this.nextElementSibling.style.display='none';">
                                                    <h3 style="display: none;">. . . . .</h3>                                                   
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                    <div class="container" id="priceContainer">
                        <h2 data-trading_price="${trading.price}">${trading.price}₩</h2>
                    </div>
                    <div class="container" id="contentContainer">
                        <h3>
                            ${trading.content}
                        </h3>
                    </div>
                    <div class="container" id="editAndSoldContainer">
                        <c:choose>
                            <c:when test="${sessionMemberIdx != null and sessionMemberIdx != 'null' and sessionMemberIdx == trading.memberIdx}">
                                <a class="button" onclick="deleteSubmit('게시글을 삭제할까요?', '${trading.idx}')">상품 삭제</a>
                                <a class="button" onclick="location.href='/trading/modifyForm.do?tradingIdx=${trading.idx}'">상품 수정</a>
                                <c:choose>
                                    <c:when test="${trading.sold eq false}">
                                        <a class="button primary" onclick="soldToggleSubmit('거래를 완료할까요?', '${trading.idx}', 1)">거래 완료</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="button primary" onclick="soldToggleSubmit('거래 완료 상태를 해제할까요?', '${trading.idx}', 0)">거래 완료 해제</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:choose>
                        <c:when test="${sessionMemberIdx != null and sessionMemberIdx != 'null' and sessionMemberIdx == trading.memberIdx}">
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${trading.sold eq false}">
                                    <c:choose>
                                        <c:when test="${chatRoomIdx == null}">
                                            <div class="container" id="chatButtonContainer">
                                                <a onclick="makeChatRoom('${trading.idx}', '${sessionMemberIdx}', '${member.name}')" class="button primary">판매자 '${member.name}'님과 채팅하기</a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="container" id="chatButtonContainer" style="display: none;">
                                                <a href="#" class="button primary">새 메시지</a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <div class="container" id="chatButtonContainer">
                                        <a href="#" class="button primary disabled" style="opacity: 0.75;">완료된 거래에요.</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </section>
            </div>
        </div>
        <!-- Sidebar -->
        <%@ include file="../common/sidebar.jsp" %>

    </div>

    <!-- Scripts -->
    <%@ include file="../common/scripts.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/js/tw/product_detail.js"></script>

</body>


<script>
    // 엑박 관련
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

    // #priceContainer 관련
    document.addEventListener("DOMContentLoaded", function () {
    var numberWrap = document.querySelector('#priceContainer > :first-child');
    console.log('numberWrap.textContent: ' + numberWrap.textContent);
    var formattedNumber = new Intl.NumberFormat('en-US').format(numberWrap.dataset.trading_price);
    console.log('formattedNumber: ' + formattedNumber);
    numberWrap.textContent = formattedNumber + '₩';
    });

    // // 판매완료 처리 및 채팅 나감처리
    // document.addEventListener("DOMContentLoaded", function () {
    //     if(('${sessionMemberIdx}' != '${trading.memberIdx}') && ('${trading.sold}' != 'true')) {
    //         checkSold();
    //     }
    // });

//     async function checkSold() {
//     console.log("checkSold() 수행됨");

// 	var formData = new FormData();
// 	formData.append("tradingIdx", '${trading.idx}');

// 	// 데이터 보내기
// 	fetch('http://localhost/kenel/trading/checkSold.do', {
// 		method: 'POST',
// 		body: formData,
// 		redirect: 'manual',
// 		// headers: {
// 		//     'Accept': 'application/json'
// 		// }
// 	})
// 	.then(response => {        
// 		return response.text();
// 	})
// 	.then(result => {
//         console.log('(checkSold) result: ' + result)
//         if(result == 'sold') {
//             window.location.reload();
//             return;
//         }
// 	})
// 	.catch(error => {
// 	});

//     await new Promise(resolve => setTimeout(resolve, 500)); // 0.5초 대기
//     await checkSold(); // 재귀 호출

// } // End of checkSold()

</script>
</html>