<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="row">



		<!--To display the sidebar  -->
		<div class="col-md-3">

			<%@include file="./shared/sidebar.jsp"%>

		</div>

		<!-- To display the actual products-->
		<div class="col-md-9">
			<!-- Added breadcrumb to test working -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true }">
					<script type="text/javascript">
					window.categoryId = '';					
					</script>
						<ol class="breadcrumb">

							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active" >All Products</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryProducts == true }">
					<script type="text/javascript">
					window.categoryId = '${category.id}';					
					</script>
						<ol class="breadcrumb">

							<li  class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
				</div>



			</div>

			<div class="row">
				<div class="col-xs-12">

					<table id="productListTable"
						class="table table-striped table-bordered">

						<thead>

							<tr>

								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th></th>
							</tr>
						</thead>
						<tfoot>

							<tr>

								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Unit Price</th>
								<th>Quantity</th>
								<th></th>
							</tr>
						</tfoot>
					</table>

				</div>

			</div>


		</div>
	</div>






</div>