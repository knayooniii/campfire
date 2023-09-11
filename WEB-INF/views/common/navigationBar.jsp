<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="pagination-box">
    <ul class="pagination">
        <c:choose>
            <c:when test="${pi.listCount ne 0}">
                <c:choose>
                    <c:when test="${(pi.firstPageOfPreviousBlock eq 0) or (pi.firstPageOfPreviousBlock eq '0')}">
                        <li><span data-value="${pi.firstPageOfPreviousBlock}" class="disabled navigation button small fit">Prev</span></li>
                    </c:when>
                    <c:otherwise>
                        <li><span onclick="pageButton(this)" data-value="${pi.firstPageOfPreviousBlock}" class="navigation button small fit">Prev</span></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="page" begin="${pi.startPage}" end="${pi.endPage}">
                    <li><a onclick="pageButton(this)" data-value="${page}" class="navigation page ${page eq pi.currentPage ? 'active' : ''}">${page}</a></li>
                </c:forEach>
                <c:choose>
                    <c:when test="${(pi.firstPageOfNextBlock eq 0) or (pi.firstPageOfNextBlock eq '0')}">
                        <li><span data-value="${pi.firstPageOfNextBlock}" class="disabled navigation button small fit">Next</span></li>
                    </c:when>
                    <c:otherwise>
                        <li><span onclick="pageButton(this)" data-value="${pi.firstPageOfNextBlock}" class="navigation button small fit">Next</span></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </ul>
</div>