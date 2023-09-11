<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<select name="" id="filterRegion1">
	<option value="전국" ${filterRegion1 eq '전국' ? 'selected' : ''}>전국</option>
	<option value="서울특별시" ${filterRegion1 eq '서울특별시' ? 'selected' : ''}>서울특별시</option>
	<option value="부산광역시" ${filterRegion1 eq '부산광역시' ? 'selected' : ''}>부산광역시</option>
	<option value="대구광역시" ${filterRegion1 eq '대구광역시' ? 'selected' : ''}>대구광역시</option>
	<option value="인천광역시" ${filterRegion1 eq '인천광역시' ? 'selected' : ''}>인천광역시</option>
	<option value="광주광역시" ${filterRegion1 eq '광주광역시' ? 'selected' : ''}>광주광역시</option>
	<option value="대전광역시" ${filterRegion1 eq '대전광역시' ? 'selected' : ''}>대전광역시</option>
	<option value="울산광역시" ${filterRegion1 eq '울산광역시' ? 'selected' : ''}>울산광역시</option>
	<option value="세종특별자치시" ${filterRegion1 eq '세종특별자치시' ? 'selected' : ''}>세종특별자치시</option>
	<option value="경기도" ${filterRegion1 eq '경기도' ? 'selected' : ''}>경기도</option>
	<option value="강원특별자치도" ${filterRegion1 eq '강원특별자치도' ? 'selected' : ''}>강원특별자치도</option>
	<option value="충청북도" ${filterRegion1 eq '충청북도' ? 'selected' : ''}>충청북도</option>
	<option value="충청남도" ${filterRegion1 eq '충청남도' ? 'selected' : ''}>충청남도</option>
	<option value="전라북도" ${filterRegion1 eq '전라북도' ? 'selected' : ''}>전라북도</option>
	<option value="전라남도" ${filterRegion1 eq '전라남도' ? 'selected' : ''}>전라남도</option>
	<option value="경상북도" ${filterRegion1 eq '경상북도' ? 'selected' : ''}>경상북도</option>
	<option value="경상남도" ${filterRegion1 eq '경상남도' ? 'selected' : ''}>경상남도</option>
	<option value="제주특별자치도" ${filterRegion1 eq '제주특별자치도' ? 'selected' : ''}>제주특별자치도</option>
</select>

<c:choose>
	<c:when test="${filterRegion1 eq '전국'}">
		<select name="전국" id="filterRegion2" class="disabled">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '서울특별시'}">
		<select name="서울특별시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="강남구" ${filterRegion2 eq '강남구' ? 'selected' : ''}>강남구</option>
			<option value="강동구" ${filterRegion2 eq '강동구' ? 'selected' : ''}>강동구</option>
			<option value="강북구" ${filterRegion2 eq '강북구' ? 'selected' : ''}>강북구</option>
			<option value="강서구" ${filterRegion2 eq '강서구' ? 'selected' : ''}>강서구</option>
			<option value="관악구" ${filterRegion2 eq '관악구' ? 'selected' : ''}>관악구</option>
			<option value="광진구" ${filterRegion2 eq '광진구' ? 'selected' : ''}>광진구</option>
			<option value="구로구" ${filterRegion2 eq '구로구' ? 'selected' : ''}>구로구</option>
			<option value="금천구" ${filterRegion2 eq '금천구' ? 'selected' : ''}>금천구</option>
			<option value="노원구" ${filterRegion2 eq '노원구' ? 'selected' : ''}>노원구</option>
			<option value="도봉구" ${filterRegion2 eq '도봉구' ? 'selected' : ''}>도봉구</option>
			<option value="동대문구" ${filterRegion2 eq '동대문구' ? 'selected' : ''}>동대문구</option>
			<option value="동작구" ${filterRegion2 eq '동작구' ? 'selected' : ''}>동작구</option>
			<option value="마포구" ${filterRegion2 eq '마포구' ? 'selected' : ''}>마포구</option>
			<option value="서대문구" ${filterRegion2 eq '서대문구' ? 'selected' : ''}>서대문구</option>
			<option value="서초구" ${filterRegion2 eq '서초구' ? 'selected' : ''}>서초구</option>
			<option value="성동구" ${filterRegion2 eq '성동구' ? 'selected' : ''}>성동구</option>
			<option value="성북구" ${filterRegion2 eq '성북구' ? 'selected' : ''}>성북구</option>
			<option value="송파구" ${filterRegion2 eq '송파구' ? 'selected' : ''}>송파구</option>
			<option value="양천구" ${filterRegion2 eq '양천구' ? 'selected' : ''}>양천구</option>
			<option value="영등포구" ${filterRegion2 eq '영등포구' ? 'selected' : ''}>영등포구</option>
			<option value="용산구" ${filterRegion2 eq '용산구' ? 'selected' : ''}>용산구</option>
			<option value="은평구" ${filterRegion2 eq '은평구' ? 'selected' : ''}>은평구</option>
			<option value="종로구" ${filterRegion2 eq '종로구' ? 'selected' : ''}>종로구</option>
			<option value="중구" ${filterRegion2 eq '중구' ? 'selected' : ''}>중구</option>
			<option value="중랑구" ${filterRegion2 eq '중랑구' ? 'selected' : ''}>중랑구</option>
			<option value=",111" ${filterRegion2 eq ',111' ? 'selected' : ''}>,111</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '부산광역시'}">
		<select name="부산광역시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="강서구" ${filterRegion2 eq '강서구' ? 'selected' : ''}>강서구</option>
			<option value="금정구" ${filterRegion2 eq '금정구' ? 'selected' : ''}>금정구</option>
			<option value="기장군" ${filterRegion2 eq '기장군' ? 'selected' : ''}>기장군</option>
			<option value="남구" ${filterRegion2 eq '남구' ? 'selected' : ''}>남구</option>
			<option value="동구" ${filterRegion2 eq '동구' ? 'selected' : ''}>동구</option>
			<option value="동래구" ${filterRegion2 eq '동래구' ? 'selected' : ''}>동래구</option>
			<option value="부산진구" ${filterRegion2 eq '부산진구' ? 'selected' : ''}>부산진구</option>
			<option value="북구" ${filterRegion2 eq '북구' ? 'selected' : ''}>북구</option>
			<option value="사상구" ${filterRegion2 eq '사상구' ? 'selected' : ''}>사상구</option>
			<option value="사하구" ${filterRegion2 eq '사하구' ? 'selected' : ''}>사하구</option>
			<option value="서구" ${filterRegion2 eq '서구' ? 'selected' : ''}>서구</option>
			<option value="수영구" ${filterRegion2 eq '수영구' ? 'selected' : ''}>수영구</option>
			<option value="연제구" ${filterRegion2 eq '연제구' ? 'selected' : ''}>연제구</option>
			<option value="영도구" ${filterRegion2 eq '영도구' ? 'selected' : ''}>영도구</option>
			<option value="중구" ${filterRegion2 eq '중구' ? 'selected' : ''}>중구</option>
			<option value="해운대구" ${filterRegion2 eq '해운대구' ? 'selected' : ''}>해운대구</option>
			<option value=",111" ${filterRegion2 eq ',111' ? 'selected' : ''}>,111</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '대구광역시'}">
		<select name="대구광역시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="군위군" ${filterRegion2 eq '군위군' ? 'selected' : ''}>군위군</option>
			<option value="남구" ${filterRegion2 eq '남구' ? 'selected' : ''}>남구</option>
			<option value="달서구" ${filterRegion2 eq '달서구' ? 'selected' : ''}>달서구</option>
			<option value="달성군" ${filterRegion2 eq '달성군' ? 'selected' : ''}>달성군</option>
			<option value="동구" ${filterRegion2 eq '동구' ? 'selected' : ''}>동구</option>
			<option value="북구" ${filterRegion2 eq '북구' ? 'selected' : ''}>북구</option>
			<option value="서구" ${filterRegion2 eq '서구' ? 'selected' : ''}>서구</option>
			<option value="수성구" ${filterRegion2 eq '수성구' ? 'selected' : ''}>수성구</option>
			<option value="중구" ${filterRegion2 eq '중구' ? 'selected' : ''}>중구</option>
			<option value=",111" ${filterRegion2 eq ',111' ? 'selected' : ''}>,111</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '인천광역시'}">
		<select name="인천광역시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="강화군" ${filterRegion2 eq '강화군' ? 'selected' : ''}>강화군</option>
			<option value="계양구" ${filterRegion2 eq '계양구' ? 'selected' : ''}>계양구</option>
			<option value="남동구" ${filterRegion2 eq '남동구' ? 'selected' : ''}>남동구</option>
			<option value="동구" ${filterRegion2 eq '동구' ? 'selected' : ''}>동구</option>
			<option value="미추홀구" ${filterRegion2 eq '미추홀구' ? 'selected' : ''}>미추홀구</option>
			<option value="부평구" ${filterRegion2 eq '부평구' ? 'selected' : ''}>부평구</option>
			<option value="서구" ${filterRegion2 eq '서구' ? 'selected' : ''}>서구</option>
			<option value="연수구" ${filterRegion2 eq '연수구' ? 'selected' : ''}>연수구</option>
			<option value="옹진군" ${filterRegion2 eq '옹진군' ? 'selected' : ''}>옹진군</option>
			<option value="중구" ${filterRegion2 eq '중구' ? 'selected' : ''}>중구</option>
			<option value=",111" ${filterRegion2 eq ',111' ? 'selected' : ''}>,111</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '광주광역시'}">
		<select name="광주광역시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="광산구" ${filterRegion2 eq '광산구' ? 'selected' : ''}>광산구</option>
			<option value="남구" ${filterRegion2 eq '남구' ? 'selected' : ''}>남구</option>
			<option value="동구" ${filterRegion2 eq '동구' ? 'selected' : ''}>동구</option>
			<option value="북구" ${filterRegion2 eq '북구' ? 'selected' : ''}>북구</option>
			<option value="서구" ${filterRegion2 eq '서구' ? 'selected' : ''}>서구</option>
			<option value=",111" ${filterRegion2 eq ',111' ? 'selected' : ''}>,111</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '대전광역시'}">
		<select name="대전광역시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="대덕구" ${filterRegion2 eq '대덕구' ? 'selected' : ''}>대덕구</option>
			<option value="동구" ${filterRegion2 eq '동구' ? 'selected' : ''}>동구</option>
			<option value="서구" ${filterRegion2 eq '서구' ? 'selected' : ''}>서구</option>
			<option value="유성구" ${filterRegion2 eq '유성구' ? 'selected' : ''}>유성구</option>
			<option value="중구" ${filterRegion2 eq '중구' ? 'selected' : ''}>중구</option>
			<option value=",111" ${filterRegion2 eq ',111' ? 'selected' : ''}>,111</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '울산광역시'}">
		<select name="울산광역시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="남구" ${filterRegion2 eq '남구' ? 'selected' : ''}>남구</option>
			<option value="동구" ${filterRegion2 eq '동구' ? 'selected' : ''}>동구</option>
			<option value="북구" ${filterRegion2 eq '북구' ? 'selected' : ''}>북구</option>
			<option value="울주군" ${filterRegion2 eq '울주군' ? 'selected' : ''}>울주군</option>
			<option value="중구" ${filterRegion2 eq '중구' ? 'selected' : ''}>중구</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '세종특별자치시'}">
		<select name="세종특별자치시" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="가람동" ${filterRegion2 eq '가람동' ? 'selected' : ''}>가람동</option>
			<option value="고운동" ${filterRegion2 eq '고운동' ? 'selected' : ''}>고운동</option>
			<option value="금남면" ${filterRegion2 eq '금남면' ? 'selected' : ''}>금남면</option>
			<option value="나성동" ${filterRegion2 eq '나성동' ? 'selected' : ''}>나성동</option>
			<option value="다정동" ${filterRegion2 eq '다정동' ? 'selected' : ''}>다정동</option>
			<option value="대평동" ${filterRegion2 eq '대평동' ? 'selected' : ''}>대평동</option>
			<option value="도담동" ${filterRegion2 eq '도담동' ? 'selected' : ''}>도담동</option>
			<option value="반곡동" ${filterRegion2 eq '반곡동' ? 'selected' : ''}>반곡동</option>
			<option value="보람동" ${filterRegion2 eq '보람동' ? 'selected' : ''}>보람동</option>
			<option value="부강면" ${filterRegion2 eq '부강면' ? 'selected' : ''}>부강면</option>
			<option value="새롬동" ${filterRegion2 eq '새롬동' ? 'selected' : ''}>새롬동</option>
			<option value="소담동" ${filterRegion2 eq '소담동' ? 'selected' : ''}>소담동</option>
			<option value="소정면" ${filterRegion2 eq '소정면' ? 'selected' : ''}>소정면</option>
			<option value="아름동" ${filterRegion2 eq '아름동' ? 'selected' : ''}>아름동</option>
			<option value="어진동" ${filterRegion2 eq '어진동' ? 'selected' : ''}>어진동</option>
			<option value="연기면" ${filterRegion2 eq '연기면' ? 'selected' : ''}>연기면</option>
			<option value="연동면" ${filterRegion2 eq '연동면' ? 'selected' : ''}>연동면</option>
			<option value="연서면" ${filterRegion2 eq '연서면' ? 'selected' : ''}>연서면</option>
			<option value="장군면" ${filterRegion2 eq '장군면' ? 'selected' : ''}>장군면</option>
			<option value="전동면" ${filterRegion2 eq '전동면' ? 'selected' : ''}>전동면</option>
			<option value="전의면" ${filterRegion2 eq '전의면' ? 'selected' : ''}>전의면</option>
			<option value="조치원읍" ${filterRegion2 eq '조치원읍' ? 'selected' : ''}>조치원읍</option>
			<option value="종촌동" ${filterRegion2 eq '종촌동' ? 'selected' : ''}>종촌동</option>
			<option value="집현동" ${filterRegion2 eq '집현동' ? 'selected' : ''}>집현동</option>
			<option value="한솔동" ${filterRegion2 eq '한솔동' ? 'selected' : ''}>한솔동</option>
			<option value="해밀동" ${filterRegion2 eq '해밀동' ? 'selected' : ''}>해밀동</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '경기도'}">
		<select name="경기도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="가평군" ${filterRegion2 eq '가평군' ? 'selected' : ''}>가평군</option>
			<option value="고양시 덕양구" ${filterRegion2 eq '고양시 덕양구' ? 'selected' : ''}>고양시 덕양구</option>
			<option value="고양시 일산동구" ${filterRegion2 eq '고양시 일산동구' ? 'selected' : ''}>고양시 일산동구</option>
			<option value="고양시 일산서구" ${filterRegion2 eq '고양시 일산서구' ? 'selected' : ''}>고양시 일산서구</option>
			<option value="과천시" ${filterRegion2 eq '과천시' ? 'selected' : ''}>과천시</option>
			<option value="광명시" ${filterRegion2 eq '광명시' ? 'selected' : ''}>광명시</option>
			<option value="광주시" ${filterRegion2 eq '광주시' ? 'selected' : ''}>광주시</option>
			<option value="구리시" ${filterRegion2 eq '구리시' ? 'selected' : ''}>구리시</option>
			<option value="군포시" ${filterRegion2 eq '군포시' ? 'selected' : ''}>군포시</option>
			<option value="김포시" ${filterRegion2 eq '김포시' ? 'selected' : ''}>김포시</option>
			<option value="남양주시" ${filterRegion2 eq '남양주시' ? 'selected' : ''}>남양주시</option>
			<option value="동두천시" ${filterRegion2 eq '동두천시' ? 'selected' : ''}>동두천시</option>
			<option value="부천시 소사구" ${filterRegion2 eq '부천시 소사구' ? 'selected' : ''}>부천시 소사구</option>
			<option value="부천시 오정구" ${filterRegion2 eq '부천시 오정구' ? 'selected' : ''}>부천시 오정구</option>
			<option value="부천시 원미구" ${filterRegion2 eq '부천시 원미구' ? 'selected' : ''}>부천시 원미구</option>
			<option value="성남시 분당구" ${filterRegion2 eq '성남시 분당구' ? 'selected' : ''}>성남시 분당구</option>
			<option value="성남시 수정구" ${filterRegion2 eq '성남시 수정구' ? 'selected' : ''}>성남시 수정구</option>
			<option value="성남시 중원구" ${filterRegion2 eq '성남시 중원구' ? 'selected' : ''}>성남시 중원구</option>
			<option value="수원시 권선구" ${filterRegion2 eq '수원시 권선구' ? 'selected' : ''}>수원시 권선구</option>
			<option value="수원시 영통구" ${filterRegion2 eq '수원시 영통구' ? 'selected' : ''}>수원시 영통구</option>
			<option value="수원시 장안구" ${filterRegion2 eq '수원시 장안구' ? 'selected' : ''}>수원시 장안구</option>
			<option value="수원시 팔달구" ${filterRegion2 eq '수원시 팔달구' ? 'selected' : ''}>수원시 팔달구</option>
			<option value="시흥시" ${filterRegion2 eq '시흥시' ? 'selected' : ''}>시흥시</option>
			<option value="안산시 단원구" ${filterRegion2 eq '안산시 단원구' ? 'selected' : ''}>안산시 단원구</option>
			<option value="안산시 상록구" ${filterRegion2 eq '안산시 상록구' ? 'selected' : ''}>안산시 상록구</option>
			<option value="안성시" ${filterRegion2 eq '안성시' ? 'selected' : ''}>안성시</option>
			<option value="안양시 동안구" ${filterRegion2 eq '안양시 동안구' ? 'selected' : ''}>안양시 동안구</option>
			<option value="안양시 만안구" ${filterRegion2 eq '안양시 만안구' ? 'selected' : ''}>안양시 만안구</option>
			<option value="양주시" ${filterRegion2 eq '양주시' ? 'selected' : ''}>양주시</option>
			<option value="양평군" ${filterRegion2 eq '양평군' ? 'selected' : ''}>양평군</option>
			<option value="여주시" ${filterRegion2 eq '여주시' ? 'selected' : ''}>여주시</option>
			<option value="연천군" ${filterRegion2 eq '연천군' ? 'selected' : ''}>연천군</option>
			<option value="오산시" ${filterRegion2 eq '오산시' ? 'selected' : ''}>오산시</option>
			<option value="용인시 기흥구" ${filterRegion2 eq '용인시 기흥구' ? 'selected' : ''}>용인시 기흥구</option>
			<option value="용인시 수지구" ${filterRegion2 eq '용인시 수지구' ? 'selected' : ''}>용인시 수지구</option>
			<option value="용인시 처인구" ${filterRegion2 eq '용인시 처인구' ? 'selected' : ''}>용인시 처인구</option>
			<option value="의왕시" ${filterRegion2 eq '의왕시' ? 'selected' : ''}>의왕시</option>
			<option value="의정부시" ${filterRegion2 eq '의정부시' ? 'selected' : ''}>의정부시</option>
			<option value="이천시" ${filterRegion2 eq '이천시' ? 'selected' : ''}>이천시</option>
			<option value="파주시" ${filterRegion2 eq '파주시' ? 'selected' : ''}>파주시</option>
			<option value="평택시" ${filterRegion2 eq '평택시' ? 'selected' : ''}>평택시</option>
			<option value="포천시" ${filterRegion2 eq '포천시' ? 'selected' : ''}>포천시</option>
			<option value="하남시" ${filterRegion2 eq '하남시' ? 'selected' : ''}>하남시</option>
			<option value="화성시" ${filterRegion2 eq '화성시' ? 'selected' : ''}>화성시</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '강원특별자치도'}">
		<select name="강원특별자치도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="강릉시" ${filterRegion2 eq '강릉시' ? 'selected' : ''}>강릉시</option>
			<option value="고성군" ${filterRegion2 eq '고성군' ? 'selected' : ''}>고성군</option>
			<option value="동해시" ${filterRegion2 eq '동해시' ? 'selected' : ''}>동해시</option>
			<option value="삼척시" ${filterRegion2 eq '삼척시' ? 'selected' : ''}>삼척시</option>
			<option value="속초시" ${filterRegion2 eq '속초시' ? 'selected' : ''}>속초시</option>
			<option value="양구군" ${filterRegion2 eq '양구군' ? 'selected' : ''}>양구군</option>
			<option value="양양군" ${filterRegion2 eq '양양군' ? 'selected' : ''}>양양군</option>
			<option value="영월군" ${filterRegion2 eq '영월군' ? 'selected' : ''}>영월군</option>
			<option value="원주시" ${filterRegion2 eq '원주시' ? 'selected' : ''}>원주시</option>
			<option value="인제군" ${filterRegion2 eq '인제군' ? 'selected' : ''}>인제군</option>
			<option value="정선군" ${filterRegion2 eq '정선군' ? 'selected' : ''}>정선군</option>
			<option value="철원군" ${filterRegion2 eq '철원군' ? 'selected' : ''}>철원군</option>
			<option value="춘천시" ${filterRegion2 eq '춘천시' ? 'selected' : ''}>춘천시</option>
			<option value="태백시" ${filterRegion2 eq '태백시' ? 'selected' : ''}>태백시</option>
			<option value="평창군" ${filterRegion2 eq '평창군' ? 'selected' : ''}>평창군</option>
			<option value="홍천군" ${filterRegion2 eq '홍천군' ? 'selected' : ''}>홍천군</option>
			<option value="화천군" ${filterRegion2 eq '화천군' ? 'selected' : ''}>화천군</option>
			<option value="횡성군" ${filterRegion2 eq '횡성군' ? 'selected' : ''}>횡성군</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '충청북도'}">
		<select name="충청북도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="괴산군" ${filterRegion2 eq '괴산군' ? 'selected' : ''}>괴산군</option>
			<option value="단양군" ${filterRegion2 eq '단양군' ? 'selected' : ''}>단양군</option>
			<option value="보은군" ${filterRegion2 eq '보은군' ? 'selected' : ''}>보은군</option>
			<option value="영동군" ${filterRegion2 eq '영동군' ? 'selected' : ''}>영동군</option>
			<option value="옥천군" ${filterRegion2 eq '옥천군' ? 'selected' : ''}>옥천군</option>
			<option value="음성군" ${filterRegion2 eq '음성군' ? 'selected' : ''}>음성군</option>
			<option value="제천시" ${filterRegion2 eq '제천시' ? 'selected' : ''}>제천시</option>
			<option value="증평군" ${filterRegion2 eq '증평군' ? 'selected' : ''}>증평군</option>
			<option value="진천군" ${filterRegion2 eq '진천군' ? 'selected' : ''}>진천군</option>
			<option value="청주시 상당구" ${filterRegion2 eq '청주시 상당구' ? 'selected' : ''}>청주시 상당구</option>
			<option value="청주시 서원구" ${filterRegion2 eq '청주시 서원구' ? 'selected' : ''}>청주시 서원구</option>
			<option value="청주시 청원구" ${filterRegion2 eq '청주시 청원구' ? 'selected' : ''}>청주시 청원구</option>
			<option value="청주시 흥덕구" ${filterRegion2 eq '청주시 흥덕구' ? 'selected' : ''}>청주시 흥덕구</option>
			<option value="충주시" ${filterRegion2 eq '충주시' ? 'selected' : ''}>충주시</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '충청남도'}">
		<select name="충청남도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="계룡시" ${filterRegion2 eq '계룡시' ? 'selected' : ''}>계룡시</option>
			<option value="공주시" ${filterRegion2 eq '공주시' ? 'selected' : ''}>공주시</option>
			<option value="금산군" ${filterRegion2 eq '금산군' ? 'selected' : ''}>금산군</option>
			<option value="논산시" ${filterRegion2 eq '논산시' ? 'selected' : ''}>논산시</option>
			<option value="당진시" ${filterRegion2 eq '당진시' ? 'selected' : ''}>당진시</option>
			<option value="보령시" ${filterRegion2 eq '보령시' ? 'selected' : ''}>보령시</option>
			<option value="부여군" ${filterRegion2 eq '부여군' ? 'selected' : ''}>부여군</option>
			<option value="서산시" ${filterRegion2 eq '서산시' ? 'selected' : ''}>서산시</option>
			<option value="서천군" ${filterRegion2 eq '서천군' ? 'selected' : ''}>서천군</option>
			<option value="아산시" ${filterRegion2 eq '아산시' ? 'selected' : ''}>아산시</option>
			<option value="예산군" ${filterRegion2 eq '예산군' ? 'selected' : ''}>예산군</option>
			<option value="천안시 동남구" ${filterRegion2 eq '천안시 동남구' ? 'selected' : ''}>천안시 동남구</option>
			<option value="천안시 서북구" ${filterRegion2 eq '천안시 서북구' ? 'selected' : ''}>천안시 서북구</option>
			<option value="청양군" ${filterRegion2 eq '청양군' ? 'selected' : ''}>청양군</option>
			<option value="태안군" ${filterRegion2 eq '태안군' ? 'selected' : ''}>태안군</option>
			<option value="홍성군" ${filterRegion2 eq '홍성군' ? 'selected' : ''}>홍성군</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '전라북도'}">
		<select name="전라북도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="고창군" ${filterRegion2 eq '고창군' ? 'selected' : ''}>고창군</option>
			<option value="군산시" ${filterRegion2 eq '군산시' ? 'selected' : ''}>군산시</option>
			<option value="김제시" ${filterRegion2 eq '김제시' ? 'selected' : ''}>김제시</option>
			<option value="남원시" ${filterRegion2 eq '남원시' ? 'selected' : ''}>남원시</option>
			<option value="무주군" ${filterRegion2 eq '무주군' ? 'selected' : ''}>무주군</option>
			<option value="부안군" ${filterRegion2 eq '부안군' ? 'selected' : ''}>부안군</option>
			<option value="순창군" ${filterRegion2 eq '순창군' ? 'selected' : ''}>순창군</option>
			<option value="완주군" ${filterRegion2 eq '완주군' ? 'selected' : ''}>완주군</option>
			<option value="익산시" ${filterRegion2 eq '익산시' ? 'selected' : ''}>익산시</option>
			<option value="임실군" ${filterRegion2 eq '임실군' ? 'selected' : ''}>임실군</option>
			<option value="장수군" ${filterRegion2 eq '장수군' ? 'selected' : ''}>장수군</option>
			<option value="전주시 덕진구" ${filterRegion2 eq '전주시 덕진구' ? 'selected' : ''}>전주시 덕진구</option>
			<option value="전주시 완산구" ${filterRegion2 eq '전주시 완산구' ? 'selected' : ''}>전주시 완산구</option>
			<option value="정읍시" ${filterRegion2 eq '정읍시' ? 'selected' : ''}>정읍시</option>
			<option value="진안군" ${filterRegion2 eq '진안군' ? 'selected' : ''}>진안군</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '전라남도'}">
		<select name="전라남도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="강진군" ${filterRegion2 eq '강진군' ? 'selected' : ''}>강진군</option>
			<option value="고흥군" ${filterRegion2 eq '고흥군' ? 'selected' : ''}>고흥군</option>
			<option value="곡성군" ${filterRegion2 eq '곡성군' ? 'selected' : ''}>곡성군</option>
			<option value="광양시" ${filterRegion2 eq '광양시' ? 'selected' : ''}>광양시</option>
			<option value="구례군" ${filterRegion2 eq '구례군' ? 'selected' : ''}>구례군</option>
			<option value="나주시" ${filterRegion2 eq '나주시' ? 'selected' : ''}>나주시</option>
			<option value="담양군" ${filterRegion2 eq '담양군' ? 'selected' : ''}>담양군</option>
			<option value="목포시" ${filterRegion2 eq '목포시' ? 'selected' : ''}>목포시</option>
			<option value="무안군" ${filterRegion2 eq '무안군' ? 'selected' : ''}>무안군</option>
			<option value="보성군" ${filterRegion2 eq '보성군' ? 'selected' : ''}>보성군</option>
			<option value="순천시" ${filterRegion2 eq '순천시' ? 'selected' : ''}>순천시</option>
			<option value="신안군" ${filterRegion2 eq '신안군' ? 'selected' : ''}>신안군</option>
			<option value="여수시" ${filterRegion2 eq '여수시' ? 'selected' : ''}>여수시</option>
			<option value="영광군" ${filterRegion2 eq '영광군' ? 'selected' : ''}>영광군</option>
			<option value="영암군" ${filterRegion2 eq '영암군' ? 'selected' : ''}>영암군</option>
			<option value="완도군" ${filterRegion2 eq '완도군' ? 'selected' : ''}>완도군</option>
			<option value="장성군" ${filterRegion2 eq '장성군' ? 'selected' : ''}>장성군</option>
			<option value="장흥군" ${filterRegion2 eq '장흥군' ? 'selected' : ''}>장흥군</option>
			<option value="진도군" ${filterRegion2 eq '진도군' ? 'selected' : ''}>진도군</option>
			<option value="함평군" ${filterRegion2 eq '함평군' ? 'selected' : ''}>함평군</option>
			<option value="해남군" ${filterRegion2 eq '해남군' ? 'selected' : ''}>해남군</option>
			<option value="화순군" ${filterRegion2 eq '화순군' ? 'selected' : ''}>화순군</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '경상북도'}">
		<select name="경상북도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="경산시" ${filterRegion2 eq '경산시' ? 'selected' : ''}>경산시</option>
			<option value="경주시" ${filterRegion2 eq '경주시' ? 'selected' : ''}>경주시</option>
			<option value="고령군" ${filterRegion2 eq '고령군' ? 'selected' : ''}>고령군</option>
			<option value="구미시" ${filterRegion2 eq '구미시' ? 'selected' : ''}>구미시</option>
			<option value="김천시" ${filterRegion2 eq '김천시' ? 'selected' : ''}>김천시</option>
			<option value="문경시" ${filterRegion2 eq '문경시' ? 'selected' : ''}>문경시</option>
			<option value="봉화군" ${filterRegion2 eq '봉화군' ? 'selected' : ''}>봉화군</option>
			<option value="상주시" ${filterRegion2 eq '상주시' ? 'selected' : ''}>상주시</option>
			<option value="성주군" ${filterRegion2 eq '성주군' ? 'selected' : ''}>성주군</option>
			<option value="안동시" ${filterRegion2 eq '안동시' ? 'selected' : ''}>안동시</option>
			<option value="영덕군" ${filterRegion2 eq '영덕군' ? 'selected' : ''}>영덕군</option>
			<option value="영양군" ${filterRegion2 eq '영양군' ? 'selected' : ''}>영양군</option>
			<option value="영주시" ${filterRegion2 eq '영주시' ? 'selected' : ''}>영주시</option>
			<option value="영천시" ${filterRegion2 eq '영천시' ? 'selected' : ''}>영천시</option>
			<option value="예천군" ${filterRegion2 eq '예천군' ? 'selected' : ''}>예천군</option>
			<option value="울릉군" ${filterRegion2 eq '울릉군' ? 'selected' : ''}>울릉군</option>
			<option value="울진군" ${filterRegion2 eq '울진군' ? 'selected' : ''}>울진군</option>
			<option value="의성군" ${filterRegion2 eq '의성군' ? 'selected' : ''}>의성군</option>
			<option value="청도군" ${filterRegion2 eq '청도군' ? 'selected' : ''}>청도군</option>
			<option value="청송군" ${filterRegion2 eq '청송군' ? 'selected' : ''}>청송군</option>
			<option value="칠곡군" ${filterRegion2 eq '칠곡군' ? 'selected' : ''}>칠곡군</option>
			<option value="포항시 남구" ${filterRegion2 eq '포항시 남구' ? 'selected' : ''}>포항시 남구</option>
			<option value="포항시 북구" ${filterRegion2 eq '포항시 북구' ? 'selected' : ''}>포항시 북구</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '경상남도'}">
		<select name="경상남도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="거제시" ${filterRegion2 eq '거제시' ? 'selected' : ''}>거제시</option>
			<option value="거창군" ${filterRegion2 eq '거창군' ? 'selected' : ''}>거창군</option>
			<option value="고성군" ${filterRegion2 eq '고성군' ? 'selected' : ''}>고성군</option>
			<option value="김해시" ${filterRegion2 eq '김해시' ? 'selected' : ''}>김해시</option>
			<option value="남해군" ${filterRegion2 eq '남해군' ? 'selected' : ''}>남해군</option>
			<option value="밀양시" ${filterRegion2 eq '밀양시' ? 'selected' : ''}>밀양시</option>
			<option value="사천시" ${filterRegion2 eq '사천시' ? 'selected' : ''}>사천시</option>
			<option value="산청군" ${filterRegion2 eq '산청군' ? 'selected' : ''}>산청군</option>
			<option value="양산시" ${filterRegion2 eq '양산시' ? 'selected' : ''}>양산시</option>
			<option value="의령군" ${filterRegion2 eq '의령군' ? 'selected' : ''}>의령군</option>
			<option value="진주시" ${filterRegion2 eq '진주시' ? 'selected' : ''}>진주시</option>
			<option value="창녕군" ${filterRegion2 eq '창녕군' ? 'selected' : ''}>창녕군</option>
			<option value="창원시 마산합포구" ${filterRegion2 eq '창원시 마산합포구' ? 'selected' : ''}>창원시 마산합포구</option>
			<option value="창원시 마산회원구" ${filterRegion2 eq '창원시 마산회원구' ? 'selected' : ''}>창원시 마산회원구</option>
			<option value="창원시 성산구" ${filterRegion2 eq '창원시 성산구' ? 'selected' : ''}>창원시 성산구</option>
			<option value="창원시 의창구" ${filterRegion2 eq '창원시 의창구' ? 'selected' : ''}>창원시 의창구</option>
			<option value="창원시 진해구" ${filterRegion2 eq '창원시 진해구' ? 'selected' : ''}>창원시 진해구</option>
			<option value="통영시" ${filterRegion2 eq '통영시' ? 'selected' : ''}>통영시</option>
			<option value="하동군" ${filterRegion2 eq '하동군' ? 'selected' : ''}>하동군</option>
			<option value="함안군" ${filterRegion2 eq '함안군' ? 'selected' : ''}>함안군</option>
			<option value="함양군" ${filterRegion2 eq '함양군' ? 'selected' : ''}>함양군</option>
			<option value="합천군" ${filterRegion2 eq '합천군' ? 'selected' : ''}>합천군</option>
		</select>
	</c:when>
	<c:when test="${filterRegion1 eq '제주특별자치도'}">
		<select name="제주특별자치도" id="filterRegion2">
			<option value="전 지역" ${filterRegion2 eq '전 지역' ? 'selected' : ''}>전 지역</option>
			<option value="서귀포시" ${filterRegion2 eq '서귀포시' ? 'selected' : ''}>서귀포시</option>
			<option value="제주시" ${filterRegion2 eq '제주시' ? 'selected' : ''}>제주시</option>
		</select>
	</c:when>
	<c:otherwise>

	</c:otherwise>
</c:choose>