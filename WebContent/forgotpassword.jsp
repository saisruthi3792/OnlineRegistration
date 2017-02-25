<%@include file="header.jsp"%>
<!DOCTYPE html>
<br/>
<html>
    
    <body>
        <form class='form-horizontal' action='UserController' method='post'>
            <input type='hidden' name='action' value='forgotpassword'/>
            <div class="form-group">
		<label class="col-sm-4 control-label">Email Address *</label> 
		<div class="col-sm-4">
	           <input type="email" class="form-control" name="email" required />
                   <c:if test="${msg ne null }">
			   <br/>
                        <div class="alert alert-danger"> ${msg} <span class="glyphicon glyphicon-remove"></span></div>
                   </c:if>
	        </div>
	    </div>
            <div class="form-group">
		<div class="col-sm-offset-4 col-sm-10">
	            <input type="submit" value="Send Password Reset Link" class="btn btn-primary">
		</div>
            </div>
            <div class="col-sm-offset-4 col-sm-30">
            <c:if test="${msgfp ne null }">
			   
            <div class="col-sm-offset-0"> ${msgfp}</div>
            </c:if>
	</div>
	
            
        </form>

    </body>
</html>

<%@include file="footer.jsp"%>
