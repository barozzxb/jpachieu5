<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/video/insert"
	method="post" enctype="multipart/form-data">
	<label for="fname">Video title:</label><br> <input type="text"
		id="categoryname" name="categoryname"><br> 
	<label for="lname">Link images:</label><br> <input type="text"
		id="images" name="images"><br> 
	<label for="lname">Upload images:</label><br> <input type="file" id="images1" name="images1"><br>
	<label for="desc">Description:</label> <br> <input type="text" id="videodesc" name="videodesc"><br>
	<label for="view">Views:</label> <br> <input type="text" id="videoviews" name="videoviews"><br>
		
	<label for="category">Category:</label>
		<select name="videocate" >
			<c:forEach items="${listcate}" var="cate" >
				<option value= "${cate.categoryId }" > ${cate.categoryId } - ${cate.categoryname}</option>
			</c:forEach>
		</select>

	<label for="html">Status</label><br> <input type="radio" id="ston"
		name="videoactive" value="1"> <label for="css">Hoạt động</label><br>
	<input type="radio" id="stoff" name="videoactive" value="0"> <label
		for="javascript">Khóa</label> <br> <br> 
	<input type="submit" value="Insert">
		
</form>