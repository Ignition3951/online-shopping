<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css"></spring:url>
<spring:url var="js" value="/resources/js"></spring:url>
<spring:url var="images" value="/resources/images"></spring:url>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>


<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>
<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable theme CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap data Tables theme CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<div class="content">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="jumbotron">
						<h1>${errorTitle}</h1>
						<hr/>
						
						<blockquote>
						${errorDescription}
						</blockquote>
						</div>
					</div>
				</div>

			</div>

		</div>

		<!-- Page Content -->
		<div class="content">
			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>
			<!--  Load if user clicks About  -->
			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Load if user clicks Contact us -->
			<c:if test="${userClickContactUs == true }">
				<%@include file="contact.jsp"%>
			</c:if>


			<!-- Load if user clicks show all products -->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- Load if user clicks Single Product -->
			<c:if test="${userClickShowProduct == true }">
				<%@include file="singleProduct.jsp"%>
			</c:if>

		</div>

		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- JQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- JQuery DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- JQuery DataTable Bootstrap Plugin -->
		<script src="${js}/dataTables.bootstrap4.js"></script>

		<!-- Self Coded Java Script  -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>
