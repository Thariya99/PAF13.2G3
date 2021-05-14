<%@page import="model.Projects"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/projects.js"></script>
<script>

</script>
</head>

<body>
<div class="container"><div class="row"><div class="col-6">

<form id="formItem" name="formItem" method="post" action="projects.jsp">
 Project code: 
<input id="itemCode" name="itemCode" type="text" 
 class="form-control form-control-sm">
<br> Project name: 
<input id="itemName" name="itemName" type="text" 
 class="form-control form-control-sm">
<br> Project price: 
<input id="itemPrice" name="itemPrice" type="text" 
 class="form-control form-control-sm">
<br> Project description: 
<input id="itemDesc" name="itemDesc" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id ="divItemsGrid">
			<%
			Projects projectObj = new Projects();
				out.print(projectObj.readItems());
			%>
</div>

		
		
		<br>
</div>
</div> </div>
</body>
</html>
