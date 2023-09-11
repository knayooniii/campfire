<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>campfire</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/main/css/main.css" />
<link rel="stylesheet" href="/resources/main/css/myList.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<script src="/resources/main/js/alert.js"></script>
<script>
	const msg = '<%=request.getAttribute("msg")%>'
	const status = '<%=request.getAttribute("status")%>'

	if ((msg !== null && msg !== "null") && (status !== null && status !== "null")) {
		$(document).ready(function() {
			alertFunction(msg, status);
		});
	}
</script>
</head>
