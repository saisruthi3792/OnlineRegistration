<%-- Include tag is used to import header page --%>
<%@include file="header.jsp"%>
<%-- Section to input login details --%>
<br />
<%-- Code to create login form--%>
<form class="form-horizontal" action="UserController" method="post">
   

	<input type="hidden" name="action" value="login">
	<div class="form-group">
		<label class="col-sm-4 control-label">Email Address *</label> 
		<div class="col-sm-4">
			<input type="email" class="form-control" name="email" required />
	</div>
	</div>

	<div class="form-group">
		<label class="col-sm-4 control-label">Password *</label>
		<div class="col-sm-4">
			<input class="form-control" type="password" name="password" required />
			   <c:if test="${msg ne null }">
			   <br/>
            <div class="alert alert-danger"> ${msg} <span class="glyphicon glyphicon-remove"></span></div>
            </c:if>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-10">
			<input type="submit" value="Log in" class="btn btn-primary">
			<!-- <input type="submit" formaction="admin.jsp?user=Hello,Admin"
				class="btn btn-primary" value="Admin"> -->
		</div>
        </div>
        
        

</form>
<%--<form class="form-horizontal" action="UserController" method="post">
    <input type='hidden' name='action' value='forgotpassword'>
    <div class="form-group">	
        
		<div class="col-sm-offset-4 col-sm-10">
			<input type="submit" value="Forgot Password" class="btn btn-primary">
			<!-- <input type="submit" formaction="admin.jsp?user=Hello,Admin"
				class="btn btn-primary" value="Admin"> -->
		</div>
	</div>
</form>--%>
<div class="form-group">
    <div class="col-sm-offset-4 col-sm-10">
        <a href="forgotpassword.jsp" id='forgot_password'>Forgot Password</a>
			<!-- <input type="submit" formaction="admin.jsp?user=Hello,Admin"
				class="btn btn-primary" value="Admin"> -->
    </div>
</div>

<div class="row col-md-4 col-md-offset-4">
	<%-- Code to go to Sign up for a new account  --%>
       
	<a href="signup.jsp" id="sign_up_link">Sign up for a new account</a>
     
        
</div>
<br />
<br />
<br />


<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp"%>