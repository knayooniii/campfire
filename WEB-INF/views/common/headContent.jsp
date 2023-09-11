<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>Generic - Editorial by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tw/mainTw.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tw/chat.css" />
<link rel="stylesheet" href="/resources/main/css/myList.css" />


<!-- alert.js (Alert Script) -->
<script src="${pageContext.request.contextPath}/resources/js/tw/alert.js"></script>

<!-- (alert.js) jQuery CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- (alert.js) sweetAlert2 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<script>

	// 데이터 보내기
	fetch('http://localhost/trading/alert.do', {
		method: 'POST',
		credentials: "same-origin" // 쿠키와 세션을 공유하기 위해 필요
    })
    .then(response => {
        return response.text();
    })
    .then(result => {
        if ((result !== null) && (result == "true")) {
			
			const tw_msg = '<%=request.getAttribute("tw_msg")%>';
			const tw_status = '<%=request.getAttribute("tw_status")%>';
			console.log("script src: " + "${pageContext.request.contextPath}/resources/js/tw/alert.js");
			console.log("tw_msg: " + tw_msg);
			console.log("tw_status: " + tw_status);
			if ((tw_msg !== null) && (tw_msg !== "null") && (tw_status !== null)&& (tw_status !== "null")) {
				$(document).ready(function() {
					toastFunction(tw_msg, tw_status);
				});
			}
			
		} else {
			// 없음
		}
    })
    .catch(error => {
        // 없음
    });

</script>


