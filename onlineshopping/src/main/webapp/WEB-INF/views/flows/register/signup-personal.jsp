<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>

		<!-- Page Content -->
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4>Sign Up-Personal</h4>
						</div>
						<div class="panel-body">
						<sf:form method="POST" class="form-horizontal" id="registerForm"
						 modelAttribute="user">
							<div class="form-group">
							<label class="control-label col-md-4">First Name</label>
								<div class="col-md-8">
								<sf:input type="text" path="firstName" class="form-control"
								 placeholder="First Name"/>
								 <sf:errors cssClass="help-block" element="em" path="firstName"></sf:errors>
								</div>	
							</div>
							<div class="form-group">
							<label class="control-label col-md-4">Last Name</label>
								<div class="col-md-8">
								<sf:input type="text" path="lastName" class="form-control"
								 placeholder="Last Name"/>
								 <sf:errors cssClass="help-block" element="em" path="lastName"></sf:errors>
								</div>	
							</div>
							<div class="form-group">
							<label class="control-label col-md-4">Email</label>
								<div class="col-md-8">
								<sf:input type="text" path="email" class="form-control"
								 placeholder="xyz@gmail.com"/>
								 <sf:errors cssClass="help-block" element="em" path="email"></sf:errors>
								</div>	
							</div>
							<div class="form-group">
							<label class="control-label col-md-4">Contact Number</label>
								<div class="col-md-8">
								<sf:input type="text" path="contactNumber" class="form-control"
								 placeholder="xxxxxxxxx" maxlength="10"/>
								 <sf:errors cssClass="help-block" element="em" path="contactNumber"></sf:errors>
								</div>	
							</div>
							<div class="form-group">
							<label class="control-label col-md-4">Password</label>
								<div class="col-md-8">
								<sf:input type="password" path="password" class="form-control"
								 placeholder="Password"/>
								 <sf:errors cssClass="help-block" element="em" path="password"></sf:errors>
								</div>	
							</div>
							<div class="form-group">
							<label class="control-label col-md-4">Confirm Password</label>
								<div class="col-md-8">
								<sf:input type="password" path="confirmPassword" class="form-control"
								 placeholder="Password"/>
								 <sf:errors cssClass="help-block" element="em" path="confirmPassword"></sf:errors>
								</div>	
							</div>
							
							<!-- RADIO BUTTON USING BOOTSTRAP CLASS OF RADIO INLINE  -->
							<div class="form-group">
								<label class="control-label col-md-4">Select Role</label>
								<div class="col-md-8">
								<label class="radio-inline">
								<sf:radiobutton path="role" value="USER" checked="checked"/>User
								</label>
								<label class="radio-inline">
								<sf:radiobutton path="role" value="SUPPLIER" />Supplier
								</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" class="btn btn-primary"
									name="_eventId_billing">
									Next Billing
									</button>
								</div>
							</div>
						</sf:form>
						</div>
					</div>
				</div>
			</div>
		</div>
<%@include file="../shared/flows-footer.jsp" %>
		

