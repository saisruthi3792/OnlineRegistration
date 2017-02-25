<%@include file="header.jsp"%>
<!DOCTYPE html>
<br/>

<html>
    <body>
        <form class="form-horizontal" action="UserController" method="post">
        
            <input type="hidden" name="action" value="resetpasswordlink" />
            <div class="form-group">
            <label class="col-sm-4 control-label">Email *</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" name="email" value="${email}" readonly></input>
                
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Password *</label>
            <div class="col-sm-4">
            <input type="password" class="form-control" name="password" required/>
            </div>                  
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Confirm Password *</label> 
            <div class="col-sm-4">
            <input type="password" class="form-control" name="confirm_password" required />  
            <c:if test="${msg ne null }"><br/>
            <div class="alert alert-danger"> ${msg} <span class="glyphicon glyphicon-remove"></span></div>
            </c:if>
            </div>
	    </div>
	    <div class="form-group">
    <div class="col-sm-offset-5">
            <input type="submit" value="Reset Password" class="btn btn-primary">
            </div>
            </div>
            <br><br/><br/>
        </form>
    </body>
</html>
<%@include file="footer.jsp" %>