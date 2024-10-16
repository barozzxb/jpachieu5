<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>

<form action="<c:url value='/admin/video/update'/>" method="post" enctype="multipart/form-data">
	<input type="text" name="videoid" value="${vid.videoId}"
		hidden="hidden"> 
	<label for="fname">Video title</label><br>
	<input type="text" id="videotitle" name="videotitle"
		value="${vid.title}"><br> 
		
	<label for="lname">Desciption:</label><br> 
	<input type="text" id="videodesc" name="videodesc"
		value="${vid.description}"><br> 
	
	<label for="lname">Link	images:</label>
	<input type="text" id="videoimages" name="videoimages"
		value="${vid.poster}"><br>
	<c:if test="${vid.poster.substring(0,5)=='https'}">
		<c:url value="${vid.poster }" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${vid.poster.substring(0,5)!='https'}">
		<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" /><br> <label
		for="lname">Upload images:</label><br> <input type="file"
		id="images1" name="images1"><br> 
		
	<label for="fname">Views: </label><br>
	<input type="text" id="videoviews" name="videoviews"
		value="${vid.views}"><br> 
		
	<label for="category">Category:</label>
		<select name="videocate" >
			<c:forEach items="${listcate}" var="cate" >
				<option value= "${cate.categoryId }" > ${cate.categoryId } - ${cate.categoryname}</option>
			</c:forEach>
		</select>

	<label for="html">Status</label><br>
	<input type="radio" id="ston" name="videoactive" value="1"
		${cate.status==1?'checked':'' }> <label for="css">Hoạt
		động</label><br> <input type="radio" id="stoff" name="videoactive" value="0"
		${cate.status!=1?'checked':'' }> <label for="javascript">Khóa</label>
	<br> <br> <input type="submit" value="Update">
</form>