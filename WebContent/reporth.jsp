<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<br>
 <div class="table-responsive">
<table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Report Date</th>
            <th>Report Question</th>		
            <th>Report Status</th>
            
        </tr>
        
        <c:forEach var="rep" items="${repList }">
        <tr>
            <%-- First study details --%>
            <td>${rep.repDate}</td>
            <td>${rep.ques }</td>
            <td>${rep.repStatus }</td>
            

        </tr></c:forEach>
       
       
       <%--  <tr> 
            Second study details
            <td>01/18/2016</td>
            <td>What do you do w...</td>
            <td>Pending</td> 
            

        </tr>
         --%>
        
    </table>
    </div>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>