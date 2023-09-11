<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<select name="" id="filterCategory1">
    <option value="전체" ${filterCategory1 eq '전체' ? 'selected' : '' }>전체</option>
    <option value="차박캠핑용품" ${filterCategory1 eq '차박캠핑용품' ? 'selected' : '' }>차박캠핑용품</option>
    <option value="텐트" ${filterCategory1 eq '텐트' ? 'selected' : '' }>텐트</option>
    <option value="타프/파라솔/천막" ${filterCategory1 eq '타프/파라솔/천막' ? 'selected' : '' }>타프/파라솔/천막</option>
    <option value="매트" ${filterCategory1 eq '매트' ? 'selected' : '' }>매트</option>
    <option value="랜턴/램프" ${filterCategory1 eq '랜턴/램프' ? 'selected' : '' }>랜턴/램프</option>
    <option value="아이스박스/웨건" ${filterCategory1 eq '아이스박스/웨건' ? 'selected' : '' }>아이스박스/웨건</option>
    <option value="의자/테이블/가구" ${filterCategory1 eq '의자/테이블/가구' ? 'selected' : '' }>의자/테이블/가구</option>
    <option value="버너/화로/스토브" ${filterCategory1 eq '버너/화로/스토브' ? 'selected' : '' }>버너/화로/스토브</option>
    <option value="침낭/침구" ${filterCategory1 eq '침낭/침구' ? 'selected' : '' }>침낭/침구</option>
    <option value="가스/장작/연료" ${filterCategory1 eq '가스/장작/연료' ? 'selected' : '' }>가스/장작/연료</option>
    <option value="코펠/취사도구" ${filterCategory1 eq '코펠/취사도구' ? 'selected' : '' }>코펠/취사도구</option>
    <option value="캠핑용파워뱅크" ${filterCategory1 eq '캠핑용파워뱅크' ? 'selected' : '' }>캠핑용파워뱅크</option>
    <option value="캠핑소품" ${filterCategory1 eq '캠핑소품' ? 'selected' : '' }>캠핑소품</option>
    <option value="기타" ${filterCategory1 eq '기타' ? 'selected' : '' }>기타</option>
</select>

<c:choose>
    <c:when test="${filterCategory1 eq '전체'}">
        <select name="" id="filterCategory2" class="disabled">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '차박캠핑용품'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="차박 텐트" ${filterCategory2 eq '차박 텐트' ? 'selected' : ''}>차박 텐트</option>
            <option value="차박 타프" ${filterCategory2 eq '차박 타프' ? 'selected' : ''}>차박 타프</option>
            <option value="자충식 / 에어매트" ${filterCategory2 eq '자충식 / 에어매트' ? 'selected' : ''}>자충식 / 에어매트</option>
            <option value="차량용매트" ${filterCategory2 eq '차량용매트' ? 'selected' : ''}>차량용매트</option>
            <option value="미니 테이블" ${filterCategory2 eq '미니 테이블' ? 'selected' : ''}>미니 테이블</option>
            <option value="충전식 랜턴" ${filterCategory2 eq '충전식 랜턴' ? 'selected' : ''}>충전식 랜턴</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>            
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '텐트'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="1인용~2인용" ${filterCategory2 eq '1인용~2인용' ? 'selected' : ''}>1인용~2인용</option>
            <option value="2인용~3인용" ${filterCategory2 eq '2인용~3인용' ? 'selected' : ''}>2인용~3인용</option>
            <option value="3인용~4인용" ${filterCategory2 eq '3인용~4인용' ? 'selected' : ''}>3인용~4인용</option>
            <option value="4인용~5인용" ${filterCategory2 eq '4인용~5인용' ? 'selected' : ''}>4인용~5인용</option>
            <option value="5인용~6인용" ${filterCategory2 eq '5인용~6인용' ? 'selected' : ''}>5인용~6인용</option>
            <option value="6인용 이상" ${filterCategory2 eq '6인용 이상' ? 'selected' : ''}>6인용 이상</option>
            <option value="차박텐트" ${filterCategory2 eq '차박텐트' ? 'selected' : ''}>차박텐트</option>
            <option value="돔텐트" ${filterCategory2 eq '돔텐트' ? 'selected' : ''}>돔텐트</option>
            <option value="터널형텐트" ${filterCategory2 eq '터널형텐트' ? 'selected' : ''}>터널형텐트</option>
            <option value="거실형텐트" ${filterCategory2 eq '거실형텐트' ? 'selected' : ''}>거실형텐트</option>
            <option value="티피텐트" ${filterCategory2 eq '티피텐트' ? 'selected' : ''}>티피텐트</option>
            <option value="캐빈텐트" ${filterCategory2 eq '캐빈텐트' ? 'selected' : ''}>캐빈텐트</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>            
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '타프/파라솔/천막'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="렉타(사각) 타프" ${filterCategory2 eq '렉타(사각) 타프' ? 'selected' : ''}>렉타(사각) 타프</option>
            <option value="헥사(육각) 타프" ${filterCategory2 eq '헥사(육각) 타프' ? 'selected' : ''}>헥사(육각) 타프</option>
            <option value="차량용타프" ${filterCategory2 eq '차량용타프' ? 'selected' : ''}>차량용타프</option>
            <option value="타프스크린" ${filterCategory2 eq '타프스크린' ? 'selected' : ''}>타프스크린</option>
            <option value="사이드/프론트월" ${filterCategory2 eq '사이드/프론트월' ? 'selected' : ''}>사이드/프론트월</option>
            <option value="텐트/타프소품" ${filterCategory2 eq '텐트/타프소품' ? 'selected' : ''}>텐트/타프소품</option>
            <option value="파라솔" ${filterCategory2 eq '파라솔' ? 'selected' : ''}>파라솔</option>
            <option value="파라솔소품" ${filterCategory2 eq '파라솔소품' ? 'selected' : ''}>파라솔소품</option>
            <option value="캐노피천막" ${filterCategory2 eq '캐노피천막' ? 'selected' : ''}>캐노피천막</option>
            <option value="천막소품" ${filterCategory2 eq '천막소품' ? 'selected' : ''}>천막소품</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '매트'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="에어매트" ${filterCategory2 eq '에어매트' ? 'selected' : ''}>에어매트</option>
            <option value="자충식매트" ${filterCategory2 eq '자충식매트' ? 'selected' : ''}>자충식매트</option>
            <option value="피크닉매트" ${filterCategory2 eq '피크닉매트' ? 'selected' : ''}>피크닉매트</option>
            <option value="엠보싱(발포)매트" ${filterCategory2 eq '엠보싱(발포)매트' ? 'selected' : ''}>엠보싱(발포)매트</option>
            <option value="에어소파" ${filterCategory2 eq '에어소파' ? 'selected' : ''}>에어소파</option>
            <option value="에어펌프" ${filterCategory2 eq '에어펌프' ? 'selected' : ''}>에어펌프</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '랜턴/램프'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="캠핑실내등" ${filterCategory2 eq '캠핑실내등' ? 'selected' : ''}>캠핑실내등</option>
            <option value="클래식랜턴" ${filterCategory2 eq '클래식랜턴' ? 'selected' : ''}>클래식랜턴</option>
            <option value="손전등" ${filterCategory2 eq '손전등' ? 'selected' : ''}>손전등</option>
            <option value="헤드랜턴" ${filterCategory2 eq '헤드랜턴' ? 'selected' : ''}>헤드랜턴</option>
            <option value="작업등" ${filterCategory2 eq '작업등' ? 'selected' : ''}>작업등</option>
            <option value="랜턴소품" ${filterCategory2 eq '랜턴소품' ? 'selected' : ''}>랜턴소품</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '아이스박스/웨건'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="웨건" ${filterCategory2 eq '웨건' ? 'selected' : ''}>웨건</option>
            <option value="아이스박스" ${filterCategory2 eq '아이스박스' ? 'selected' : ''}>아이스박스</option>
            <option value="쿨러백" ${filterCategory2 eq '쿨러백' ? 'selected' : ''}>쿨러백</option>
            <option value="워터저그/물통" ${filterCategory2 eq '워터저그/물통' ? 'selected' : ''}>워터저그/물통</option>
            <option value="아이스팩" ${filterCategory2 eq '아이스팩' ? 'selected' : ''}>아이스팩</option>
            <option value="쿨러스탠드" ${filterCategory2 eq '쿨러스탠드' ? 'selected' : ''}>쿨러스탠드</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '의자/테이블/가구'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="캠핑의자" ${filterCategory2 eq '캠핑의자' ? 'selected' : ''}>캠핑의자</option>
            <option value="캠핑테이블" ${filterCategory2 eq '캠핑테이블' ? 'selected' : ''}>캠핑테이블</option>
            <option value="의자/테이블세트" ${filterCategory2 eq '의자/테이블세트' ? 'selected' : ''}>의자/테이블세트</option>
            <option value="키친/BBQ테이블" ${filterCategory2 eq '키친/BBQ테이블' ? 'selected' : ''}>키친/BBQ테이블</option>
            <option value="미니/경량테이블" ${filterCategory2 eq '미니/경량테이블' ? 'selected' : ''}>미니/경량테이블</option>
            <option value="캠핑박스" ${filterCategory2 eq '캠핑박스' ? 'selected' : ''}>캠핑박스</option>
            <option value="쿨러스탠드/쉘프" ${filterCategory2 eq '쿨러스탠드/쉘프' ? 'selected' : ''}>쿨러스탠드/쉘프</option>
            <option value="야전침대" ${filterCategory2 eq '야전침대' ? 'selected' : ''}>야전침대</option>
            <option value="해먹" ${filterCategory2 eq '해먹' ? 'selected' : ''}>해먹</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '버너/화로/스토브'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="휴대용가스레인지" ${filterCategory2 eq '휴대용가스레인지' ? 'selected' : ''}>휴대용가스레인지</option>
            <option value="화로" ${filterCategory2 eq '화로' ? 'selected' : ''}>화로</option>
            <option value="훈제그릴" ${filterCategory2 eq '훈제그릴' ? 'selected' : ''}>훈제그릴</option>
            <option value="스토브(버너)" ${filterCategory2 eq '스토브(버너)' ? 'selected' : ''}>스토브(버너)</option>
            <option value="가스그릴" ${filterCategory2 eq '가스그릴' ? 'selected' : ''}>가스그릴</option>
            <option value="버너용품" ${filterCategory2 eq '버너용품' ? 'selected' : ''}>버너용품</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '침낭/침구'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="머미형침낭" ${filterCategory2 eq '머미형침낭' ? 'selected' : ''}>머미형침낭</option>
            <option value="사각형침낭" ${filterCategory2 eq '사각형침낭' ? 'selected' : ''}>사각형침낭</option>
            <option value="사각형침낭(후드)" ${filterCategory2 eq '사각형침낭(후드)' ? 'selected' : ''}>사각형침낭(후드)</option>
            <option value="2인용침낭" ${filterCategory2 eq '2인용침낭' ? 'selected' : ''}>2인용침낭</option>
            <option value="라이너/담요" ${filterCategory2 eq '라이너/담요' ? 'selected' : ''}>라이너/담요</option>
            <option value="베개" ${filterCategory2 eq '베개' ? 'selected' : ''}>베개</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '가스/장작/연료'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="숯" ${filterCategory2 eq '숯' ? 'selected' : ''}>숯</option>
            <option value="장작" ${filterCategory2 eq '장작' ? 'selected' : ''}>장작</option>
            <option value="착화제" ${filterCategory2 eq '착화제' ? 'selected' : ''}>착화제</option>
            <option value="가스" ${filterCategory2 eq '가스' ? 'selected' : ''}>가스</option>
            <option value="고체연료" ${filterCategory2 eq '고체연료' ? 'selected' : ''}>고체연료</option>
            <option value="연료통" ${filterCategory2 eq '연료통' ? 'selected' : ''}>연료통</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '코펠/취사도구'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="코펠" ${filterCategory2 eq '코펠' ? 'selected' : ''}>코펠</option>
            <option value="컵" ${filterCategory2 eq '컵' ? 'selected' : ''}>컵</option>
            <option value="수저/커트러리" ${filterCategory2 eq '수저/커트러리' ? 'selected' : ''}>수저/커트러리</option>
            <option value="캠핑용식기" ${filterCategory2 eq '캠핑용식기' ? 'selected' : ''}>캠핑용식기</option>
            <option value="냄비/팬" ${filterCategory2 eq '냄비/팬' ? 'selected' : ''}>냄비/팬</option>
            <option value="더치오븐" ${filterCategory2 eq '더치오븐' ? 'selected' : ''}>더치오븐</option>
            <option value="주방소품" ${filterCategory2 eq '주방소품' ? 'selected' : ''}>주방소품</option>
            <option value="설거지 용품" ${filterCategory2 eq '설거지 용품' ? 'selected' : ''}>설거지 용품</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '캠핑용파워뱅크'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="리튬이온파워뱅크" ${filterCategory2 eq '리튬이온파워뱅크' ? 'selected' : ''}>리튬이온파워뱅크</option>
            <option value="리튬인산철파워뱅크" ${filterCategory2 eq '리튬인산철파워뱅크' ? 'selected' : ''}>리튬인산철파워뱅크</option>
            <option value="패널/배터리" ${filterCategory2 eq '패널/배터리' ? 'selected' : ''}>패널/배터리</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '캠핑소품'}">
        <select name="" id="filterCategory2">
            <option value="전체" ${filterCategory2 eq '전체' ? 'selected' : ''}>전체</option>
            <option value="다용도칼/멀티툴" ${filterCategory2 eq '다용도칼/멀티툴' ? 'selected' : ''}>다용도칼/멀티툴</option>
            <option value="케이스/가방" ${filterCategory2 eq '케이스/가방' ? 'selected' : ''}>케이스/가방</option>
            <option value="도끼/손도끼" ${filterCategory2 eq '도끼/손도끼' ? 'selected' : ''}>도끼/손도끼</option>
            <option value="캠핑용선풍기" ${filterCategory2 eq '캠핑용선풍기' ? 'selected' : ''}>캠핑용선풍기</option>
            <option value="전선릴" ${filterCategory2 eq '전선릴' ? 'selected' : ''}>전선릴</option>
            <option value="핸드카트/트럭" ${filterCategory2 eq '핸드카트/트럭' ? 'selected' : ''}>핸드카트/트럭</option>
            <option value="캠핑용 보조배터리" ${filterCategory2 eq '캠핑용 보조배터리' ? 'selected' : ''}>캠핑용 보조배터리</option>
            <option value="핫팩/손난로" ${filterCategory2 eq '핫팩/손난로' ? 'selected' : ''}>핫팩/손난로</option>
            <option value="난로" ${filterCategory2 eq '난로' ? 'selected' : ''}>난로</option>
            <option value="전기장판" ${filterCategory2 eq '전기장판' ? 'selected' : ''}>전기장판</option>
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:when test="${filterCategory1 eq '기타'}">
        <select name="" id="filterCategory2">
            <option value="기타" ${filterCategory2 eq '기타' ? 'selected' : ''}>기타</option>
        </select>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>


