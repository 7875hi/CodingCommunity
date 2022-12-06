<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 
    request.setCharacterEncoding("UTF-8");
 
%>

<%@ include file="../header.jsp"%>

<form id="searchForm" action="/tipboard/tiplist" method="get">
<div class="search">
  <input type="text" class="searchTerm" placeholder="키워드로 검색하세요" name="keyword"> 
  <button type="button" class="searchButton">
    <i class="fa fa-search"></i>
  </button>
 	<input type="hidden" name="pageNum" value="${paging.cri.pageNum}">
	<input type="hidden" name="amount" value="${paging.cri.amount}"> 
</div>
</form>

<h1 style="text-align: center;">Q & A</h1>
 
<div id = "list_container">

<table border=1 id = "list">
	<thead>
		<tr>
			<th>번호</th>
			<th class = "title">제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>	
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
 			<tr class = "tbody">
 				<td>${item.bno}</td>
  				<td class = "title"><a href="/detail?bno=${item.bno}">${item.title}</a></td>
 				<td>${item.id}</td>
 				<td>
 				<fmt:parseDate value="${item.regdate}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
 				<fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd"/>
 				</td>
 				<td>${item.cnt}</td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan = 5 style='letter-spacing:3px' id="page">
					<c:if test="${paging.prev}">
						<a href="/board/list?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.startPage-1}&amount=${paging.cri.amount}">◀</a>
					</c:if>

					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
 						<a href="/board/list?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
					</c:forEach>
					
					<c:if test="${paging.next}">
						<a href="/board/list?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">▶</a>
					</c:if>
				</td>
			</tr>
	</tbody>
</table>

</div>

<div id="btn">
	<c:if test="${sessionScope.login.id != null}">
	<input type="button" id="towrite" value="글쓰기" style="float: right;" onclick="location.href='/write'">
	</c:if>
</div>

</body>
</html>