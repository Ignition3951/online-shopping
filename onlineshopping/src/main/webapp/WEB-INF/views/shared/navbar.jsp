<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav nav">

				<li id="about" class="nav-item"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>

				<li id="contact" class="nav-item"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>
				<li id="listProducts" class="nav-item"><a class="nav-link"
					href="${contextRoot}/show/all/products">View Products</a></li>
				<security:authorize access="hasAuthority('ADMIN')">
				<li id="manageProducts" class="nav-item"><a class="nav-link"
					href="${contextRoot}/manage/products">Manage Products</a></li>
					</security:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right ">
				<security:authorize access="isAnonymous()">
					<li id="register" class="nav-item"><a class="nav-link"
						href="${contextRoot}/register">Sign Up</a></li>
					<li id="login" class="nav-item"><a class="nav-link"
						href="${contextRoot}/login">Login</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown nav-item"><a href="javascript:void(0)"
						class="dropdown-toggle nav-link" id="dropdownMenu1"
						data-toggle="dropdown"> ${userModel.fullName}<span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<security:authorize access="hasAuthority('USER')">
								<li><a href="${contextRoot}/cart"> <span class="badge">${userModel.cart.cartLines}</span>
										-&#8377;${userModel.cart.grandTotal}
								</a></li>
								<li class="divider" role="separator"></li>
							</security:authorize>
							<li><a href="${contextRoot}/perform-logout">Logout</a></li>
						</ul></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>

<script>

window.userRole='${userModel.role}';
</script>