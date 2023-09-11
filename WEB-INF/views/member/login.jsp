<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
	Dimension by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>모닥불</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/login/css/main.css" />


<!-- 네이버 로그인 연동 api start -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<script src="/resources/main/js/alert.js"></script>
<script>
	const msg = '<%=request.getAttribute("msg")%>'
	const status = '<%=request.getAttribute("status")%>'

	if ((msg !== null && msg !== "null")
			&& (status !== null && status !== "null")) {
		$(document).ready(function() {
			alertFunction(msg, status);
		});
	}
</script>
<!-- 네이버 로그인 연동 api end-->
<style>
/* .kakao_i {
	background: url(resources/icons/kakao.png) no-repeat;
	width: 40px;
	height: 100%;
	background-size: 90%;
	background-position: 50%;
	margin: 0 20px;
}

.kakao_txt {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 16px;
	padding-right: 60px;
}

.naverBtn {
	width: 300px;
	height: 45px;
	background-color: rgb(3, 199, 90);
	border-radius: 6px;
	display: flex; 
	justify-content: center;
	align-items: center;
	text-decoration: none; 
}

.naver {
	height: 45px;
}

a {
	text-decoration: none;
} */
.txt_c {
	margin: 10px;
}

.sns_login_form {
	width: 100%;
}

.btn-kakao {
	background-color: #FFEB00 !important;
	background-image: url(https://vendor-cdn.imweb.me/images/kakao_icon.png);
	background-size: 20px;
	background-position: 12px 49%;
	background-repeat: no-repeat;
	color: #3c1e1e !important;
	border-color: #FFEB00 !important;
	font-size: 14px;
	display: block !important;
	padding: 10px 25px 10px 25px;
	width: 100%;
	text-align: center;
	border-radius: 6px;
}

.btn-naver {
	background-color: #27d34a !important;
	border-color: #27d34a !important;
	background-image:
		url(https://vendor-cdn.imweb.me/images/naver_login2x.png);
	background-size: 16px;
	background-position: 15px 50%;
	background-repeat: no-repeat;
	color: #fff !important;
	font-size: 14px;
	display: block !important;
	padding: 10px 25px 10px 25px;
	width: 100%;
	text-align: center;
	border-radius: 6px;
	padding: 10px 25px 10px 25px;
	width: 100%;
}

.btn-google {
	background-color: #ffffff !important;
	background-image:
		url(https://vendor-cdn.imweb.me/images/google_icon.png);
	background-size: 19px;
	background-position: 13px 50%;
	background-repeat: no-repeat;
	color: #000 !important;
	border-color: #ccc !important;
	font-size: 14px;
	display: block !important;
	padding: 10px 25px 10px 25px;
	width: 100%;
	font-family: Sans-Serif;
	text-align: center;
	border-radius: 6px;
	padding: 10px 25px 10px 25px;
	width: 100%;
	border: 1px solid #ccc;
	border-color: #ccc !important;
}
</style>
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<div class="logo">
				<i class="fa-solid fa-fire fa-beat-fade fa-2xl" style="color: #ffffff;"></i>
			</div>
			<div class="content">
				<div class="inner">
					<h1>campfire</h1>
					<p>
						campfire는 늘어나는 캠퍼들에게 다양한 정보를 제공하는 웹사이트 입니다. <br /> 사람들과 소통하며 휴식과 모험의 즐거움을 함께 나눠보세요 <br /> Enjoy nature in various ways!
					</p>
				</div>
			</div>
			<nav>
				<ul>
					<li>
						<a href="/member/go.do">Go</a>
					</li>
					<li>
						<a href="#contact">Login</a>
					</li>
					<!--<li><a href="#elements">Elements</a></li>-->
				</ul>
			</nav>
		</header>

		<!-- Main -->
		<div id="main">
			<!-- Contact -->

			<article id="contact">

				<h2 align="center">Login</h2>

				<form method="post" action="/member/login.do">
					<div class="fields">
						<div class="field">
							<label for="id">ID</label>
							<input type="text" name="memberUserId" id="id" />
						</div>

						<div class="field">
							<label for="password">Password</label>
							<input type="password" name="memberPw" id="password" />
						</div>


						<div style="display: flex; justify-content: center; margin-top: 10px">
							<button type="submit" class="btn_login" id="log.login">
								<span class="btn_text">로그인</span>
							</button>
						</div>

						<div style="display: flex; justify-content: center; margin-top: -15px">
							<a href="#joinj" style="font-size: 0.6rem; margin-left: 6rem; margin-top: 0.5rem">
								<span>회원가입</span>
							</a>
							&nbsp; &nbsp;
							<a href="/member/idpw.do" style="font-size: 0.6rem; margin-right: 5.5rem; margin-top: 0.5rem;">
								<span>ID·PW 찾기</span>
							</a>
							&nbsp; &nbsp;
						</div>
						<!-- 	<a class="kakaoBtn" href="https://kauth.kakao.com/oauth/authorize?client_id=f56f8461b6c1bd3d445e592479cd7a79&redirect_uri=http://localhost/member/kakaoLogin&response_type=code">
							REST_API키 및 REDIRECT_URI는 본인걸로 수정하세요
							<img src="/resources/images/kakao_login_medium_wide.png" />
						</a> -->
						<!-- 						<a class="naverBtn" href="https://kauth.kakao.com/oauth/authorize?client_id=f56f8461b6c1bd3d445e592479cd7a79&redirect_uri=http://localhost/member/kakaoLogin&response_type=code">
							REST_API키 및 REDIRECT_URI는 본인걸로 수정하세요
							
							<img class="naver" src="/resources/images/btnG_완성형.png" />
						</a> -->



					</div>


				</form>
				<div class="sns_login_form">
					<p class="txt_c">
						<a class="btn btn-kakao" id="custom-login-btn" href="https://kauth.kakao.com/oauth/authorize?client_id=f56f8461b6c1bd3d445e592479cd7a79&redirect_uri=http://campfire.com/member/kakaoLogin&response_type=code">카카오로 시작하기</a>
					</p>
					<p class="txt_c">
						<a class="btn btn-naver" href="https://nid.naver.com/oauth2.0/authorize?response_type=code&redirect_uri=http://campfire.com/member/naverLogin&client_id=KqnzzV13lgmW6NT6QATF">네이버로 시작하기</a>
					</p>
					<p class="txt_c">
						<a class="btn-google" href="https://accounts.google.com/o/oauth2/v2/auth?client_id=40148344416-p4n6jrj21bhl3uk774ji4kr9uje2898t.apps.googleusercontent.com&redirect_uri=http://localhost/member/googleLogin&response_type=code&scope=email%20profile%20openid&access_type=offline">Google로 시작하기 </a>
					</p>
				</div>
			</article>
			<article id="joinj">
				<div>
					<a id="business" onclick="handleButtonClick1()" onmouseover="darkenImage(this)" onmouseout="resetImage(this)" style="cursor: pointer">
						<img src="/resources/images/tie-svgrepo-com.svg" alt="" style="border-radius: 60%; border: 1px solid white; width: 50px; height: 50px; object-fit: contain;" />
					</a>
					<p>사업자</p>
				</div>

				<div>
					<a id="personal" onclick="handleButtonClick2()" onmouseover="darkenImage(this)" onmouseout="resetImage(this)" style="cursor: pointer">
						<img src="/resources/images/camping-svgrepo-com.svg" alt="" style="border-radius: 60%; border: 1px solid white; width: 50px; height: 50px; object-fit: contain;" class="brighten-on-hover" />
					</a>

					<p>개인</p>
				</div>


			</article>



	
			<!-- 사업자 회원가입 -->


			<article id="businessjoin">
				<h3>정보입력</h3>
				<div class="form-container">
					<form class="businessjoinf" action="/member/signup.do"  method="post" onsubmit="return checkForm()">
						<input type="hidden" name="memberDivision" value="사업자">
						<section>
							<div class="info" id="info__id">
								<div id="id-input">
									<input class="box" type="text" id="memberUserId" placeholder="아이디 입력(6~20자)"
										name="memberUserId" />
									<button type="button" id="idCheck">중복 확인</button>
									
								<p class="result">
								 	<span class="msg">아이디를 확인해주십시오.</span>
								</p>
								</div>
								<div class="error-msg"></div>
							</div>
							<div class="info" id="info__pw">
								<input class="box" type="password" id="memberPw"
									placeholder="비밀번호 입력 (문자, 숫자, 특수문자 포함 8~20자)" name="memberPw" required/>
								<div class="error-msg"></div>
							</div>
							<div class="info" id="info__pwRe">
								<input class="box" type="password" placeholder="비밀번호 재입력"
									id="memberPwChk" name="memberPwChk" required/>
								<div class="error-msg"></div>
							</div>

						<!-- 	<input type="text" class="box" type="busineessnub"
								placeholder="사업자번호 :" name="memberBiznum" /> -->
								
						 <input type="text"
								class="box" id="name" required placeholder="이름:"
								name="memberName" />

							<div style="display: flex; gap: 10px; align-items: baseline;">
								<input type="text" class="box" id="sample6_postcode"
									placeholder="우편번호" name="memberPostalcode"
									style="flex-basis: 70%" /> 
								<input type="button" id="search"
									onclick="sample6_execDaumPostcode()" value="우편번호 찾기" />
							</div>

							<input type="text" class="box" id="sample6_address"
								placeholder="주소" name="memberAdd1" />
							 <input type="text" id="sample6_detailAddress" placeholder="상세주소"
								style="flex-basis: 70%" name="memberAdd2" /> <br />
							
							<div class="gender-container">
								<input type="radio" id="male" name="memberGender" value="남성" />
										<label for="male">남성</label>
									 <input type="radio" id="female" name="memberGender" value="여성" />
										 <label for="female">여성</label>
							</div>
							<br />

							<div class="info" id="info__birth">
								<div id="birth-flex">
									<select class="box" id="birth-year" name="birth-year" style="width: 33%">
										<option>년</option>
									</select> <select class="box" id="birth-month" name="birth-month" style="width: 33%">
										<option>월</option>
									</select> <select class="box" id="birth-day"  name="birth-day" style="width: 33%">
										<option>일</option>
									</select>
								</div>
								<div class="error-msg"></div>
							</div>
						</section>

						<br />
						<button type="submit" id="submit1" disabled>가입하기</button>
					</form>
				</div>
			</article>


			<!-- 개인 회원가입 -->


			<article id="personaljoin">
				<h3>정보입력</h3>
				<div class="form-container">

					<form class="personaljoinf" action="member/signup.do" method="post" onsubmit="return checkForm1()">
						<input type="hidden" name="memberDivision" value="개인">
						<section>
							<div class="info" id="info__id1">
								<div id="id-input1">
									<input class="box" type="text" placeholder="아이디 입력(6~20자)" id="memberUserId1" name="memberUserId"/>
									<button type="button" id="id-check1">중복 확인</button>
						
								<p class="result">
								 	<span class="msg">아이디를 확인해주십시오.</span>
								</p>
									
								</div>
								<div class="error-msg"></div>
							</div>
							<div class="info" id="info__pw1">
								<input class="box" type="password"
									placeholder="비밀번호 입력 (문자, 숫자, 특수문자 포함 8~20자)"  id="memberPw1" required name="memberPw"/>
								<div class="error-msg"></div>
							</div>
							<div class="info" id="info__pwRe1">
								<input class="box" type="password" placeholder="비밀번호 재입력"  id="memberPwChk1"  required name="memberPwChk" />
								<div class="error-msg"></div>
							</div>
							<input type="hidden" class="box" type="busineessnub"
								placeholder="사업자번호 :" name="memberBiznum" value="" /> <input
								type="text" class="box" id="name" required placeholder="이름:" name="memberName"/>


							<div style="display: flex; gap: 10px; align-items: baseline;">
								<input type="text" class="box" id="sample6_postcode1"
									placeholder="우편번호" name="memberPostalcode"
									style="flex-basis: 70%" /> <input type="button" id="search"
									onclick="sample6_execDaumPostcode1()" value="우편번호 찾기" />
							</div>

							<input type="text" class="box" id="sample6_address1"
								placeholder="주소" name="memberAdd1" /> 
						<input type="text" id="sample6_detailAddress1" placeholder="상세주소"
								style="flex-basis: 70%" name="memberAdd2" /> <br />
							<div class="gender-container">
								<input type="radio" id="male1" name="memberGender" value="남성" />
								<label for="male1">남성</label> 
								<input type="radio" id="female1" name="memberGender" value="여성" /> 
									<label for="female1">여성</label>
							</div>
							<br />

							<div class="info" id="info__birth1">
								<div id="birth-flex">
									<select class="box" id="birth-year1" name="birth-year" style="width: 33%">
										<option>년</option>
									</select> <select class="box" id="birth-month1" name="birth-month" style="width: 33%">
										<option>월</option>
									</select> <select class="box" id="birth-day1" name="birth-day" style="width: 33%">
										<option>일</option>
									</select>
								</div>
								<div class="error-msg"></div>
							</div>
						</section>

						<br />
						<button type="submit" id="submit2" disabled>가입하기</button>
					</form>
					
				</div>
			</article>

			<!-- Elements -->
		</div>

		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; Untitled. Design:
				<a href="https://html5up.net">HTML5 UP</a>
				.
			</p>
		</footer>
	</div>

	<!-- BG -->
	<div id="bg"></div>

	<!-- Scripts -->
	<script src="https://accounts.google.com/gsi/client" async defer></script>
	<script src="/resources/login/js/jquery.min.js"></script>
	<script src="/resources/login/js/browser.min.js"></script>
	<script src="/resources/login/js/breakpoints.min.js"></script>
	<script src="/resources/login/js/util.js"></script>
	<script src="/resources/login/js/main.js"></script>
	<script src="/resources/login/js/section.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://kit.fontawesome.com/0cf27f7ac1.js" crossorigin="anonymous"></script>

</body>
</html>

