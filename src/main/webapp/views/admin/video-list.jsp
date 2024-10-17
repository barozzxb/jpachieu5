<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/admin/video/add">Add Video</a>
<br></br>
<form action="/jpachieu5/admin/video/findvideo" method=post>
	<input type="text" alt="Search by name..." class="form-control" id="findkey" name="findkey">
	<button type="submit" class="btn btn-primary">Search</button>
	<a href="${pageContext.request.contextPath}/admin/videos">Reset</a>
</form>
<table style="border: 1px solid black; width: 100%;">
	<tr>
		<th>STT</th>
		<th>Poster</th>
		<th>Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="vid" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			<c:if test="${vid.poster.substring(0,5)== 'https' }">
				<c:url value="${vid.poster}" var="imgUrl"></c:url>

			</c:if>
			<c:if test="${vid.poster.substring(0,5)!='https' }">
				<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>

			</c:if>

			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${vid.title}</td>
			<td>${vid.description}</td>
			<td>${vid.views}</td>
			<td>${vid.category.categoryname}</td>
<%-- 			<td>Image URL: ${imgUrl}</td> --%>
			<td><a
				href="${pageContext.request.contextPath}/admin/video/edit?id=${vid.videoId}">Sửa</a>
				<a
				href="${pageContext.request.contextPath}/admin/video/delete?id=${vid.videoId}">>Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>