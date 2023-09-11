<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="memberDivision" scope="session">
	<c:choose>
		<c:when test="${not empty sessionScope.memberDivision}">
			<c:out value="${sessionScope.memberDivision}" />
		</c:when>
		<c:otherwise>
            user
        </c:otherwise>
	</c:choose>
</c:set>
<div id="sidebar">
	<div class="inner">
		<!-- Menu -->
		<nav id="menu">
			<header class="major">
				<h2>Menu</h2>
			</header>
			<ul>
				<li>
					<a href="/campSearch/camping.do">캠핑장 찾기</a>
				</li>
				<li>
					<a href="/trading/list.do">당근</a>
				</li>
				<li>
					<span class="opener">커뮤니티</span>
					<ul>
						<li>
							<a href="/user/board/showBoardList.do?postCategory=recommend">추천</a>
						</li>
						<li>
							<a href="/user/board/showBoardList.do?postCategory=sharingInfo">정보공유</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="/user/showUserInquiry.do">문의 사항</a>
				</li>

				<!--관리자만 보이게-->
				<c:if test="${sessionScope.memberDivision eq 'admin'}">
					<li>
						<span class="opener">admin</span>
						<ul>
							<li>
								<a href="/adminRegistration/showBusinessRegistrationList.do">사업자 등록증 등록</a>
							</li>
							<li>
								<a href="/adminRegistration/showCampRegistrationList.do">캠핑장 등록</a>
							</li>
							<li>
								<a href="/admin/showAdminInquiry.do">문의사항 답변</a>
							</li>
						</ul>
					</li>
				</c:if>
				<!--사업자만 보이게-->
				<c:if test="${sessionScope.memberDivision eq 'business'}">

					<li>
						<span class="opener">businessman</span>
						<ul>
							<li>
								<a href="/business/showBusinessRegistration.do">사업자 등록 요청</a>
							</li>
							<li>
								<a href="/business/showMyCampList.do">캠핑장 등록 요청</a>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		</nav>



		<!-- Section -->
		<section>
			<header class="major">
				<h2>Get in touch</h2>
			</header>
			<p>CAMPFIRE는 늘어나는 캠퍼들에게 다양한 정보를 제공하는 웹사이트 입니다. 사람들과 소통하며 휴식과 모험의 즐거움을 함께 나눠보세요 ENJOY NATURE IN VARIOUS WAYS!.</p>
			<ul class="contact">
				<li class="icon solid fa-envelope">nayooningg@gmail.com</li>
				<li class="icon solid fa-envelope">sconscon2021@gmail.com</li>
				<li class="icon solid fa-envelope">bbddbqls@gmail.com</li>
				<li class="icon solid fa-envelope">jin900508@gmail.com</li>
				<li class="icon solid fa-home">
					<a href="https://github.com/bbddbqls/campfire/tree/yoobin">github.com/bbddbqls/campfire </a>
				</li>
			</ul>
		</section>

		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; Untitled. All rights reserved. Demo Images:
				<a href="https://unsplash.com">Unsplash</a>
				. Design:
				<a href="https://html5up.net">HTML5 UP</a>
				.
			</p>
		</footer>

	</div>
</div>