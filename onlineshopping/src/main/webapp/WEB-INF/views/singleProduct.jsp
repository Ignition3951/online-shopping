<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container">

	<div class="row">
	<div class="col-xs-12">
	<!-- Adding BreadCrumb -->
	<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
	<li class="breadcrumb-item"><a href="${contextRoot}/show/all/products">Products</a></li>
	<li class="breadcrumb-item active">${product.name}</li>
	</ol>
	</div>
	</div>
	<div class="row">
	<!-- Display the image of product -->
		<div class="col-xs-12 col-sm-8">
			<div class="thumbnail">
			<img src="${images}/${product.code}.jpg" class="singleProductImg"/>
			</div>
		</div>
		<!-- Display the description of the product -->
		<div class="col-xs-12 col-sm-4">
		<h3>${product.name}</h3>
		<hr/>
		
		<p>${product.description}</p>
		<hr/>
		
		<h4>Price: <strong> &#8377; ${product.unitPrice} /-</strong></h4>
		
		<!--  <h6>Qty. Available : ${product.quantity}</h6>-->
		<c:choose>
		<c:when test="${product.quantity<1}">Qty. Available : <span style="color: red">OUT OF STOCK</span></c:when>
		<c:otherwise><h6>Qty. Available : ${product.quantity}</h6></c:otherwise>
		</c:choose>
		
		<security:authorize access="hasAuthority('USER')">
		<c:choose>
		<c:when test="${product.quantity<1}">
		<a href="javascript:void(0)" class="btn btn-success disabled"><strike>Add to Cart</strike></a>
		</c:when>
		
		<c:otherwise>
		<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">Add to Cart</a>
		</c:otherwise>
		</c:choose>
		</security:authorize>
		<security:authorize access="hasAuthority('ADMIN')">
		<a href="${contextRoot}/manage/${product.id}/products" class="btn btn-warning">Edit</a>
		</security:authorize>
		
		<%-- <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">Add to Cart</a> --%>
		<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>
		</div>
	</div>



</div>