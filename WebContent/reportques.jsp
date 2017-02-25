<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<h3><span id="studies">Reported Questions</span></h3><br/>
<a href="admin.jsp" id="back_to_page">&laquo;Back to the Main Page</a><br/>
<br/><br/><br/>


<!-- TODO: Add more code to get the table here.
  -->
  <div class="table-responsive">
  <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Action</th>		
        </tr>
        <c:forEach var="rep" items="${reports }">
        <tr>
            <%-- First study details --%>
            <td><c:out value="${rep.ques}"/></td>
            <c:choose>
            <c:when test="${rep.repStatus eq 'approved' }">
          <td>  Approved</td>
            </c:when>
            <c:when test="${rep.repStatus eq 'disapproved' }">
             <td>  Disapproved</td>
            </c:when>
            <c:otherwise>
 <td>
            <form action="StudyController" method="post">
            <input type="submit" class="btn btn-primary" formaction="StudyController?action=approve"  value="Approve">
            <input type="submit" class="btn btn-primary" formaction="StudyController?action=disapprove"  value="Dispprove">
              <input type="hidden" name="StudyCode" value="${rep.studyCode}">
               <input type="hidden" name="repemail" value="${rep.reporterEmail}"></form>
           </td>
</c:otherwise>
            </c:choose>
           
            
        </tr>
        </c:forEach>
        <!-- TODO Add one more for removal and not re -->
        
        <tr> 
            <%-- Second study details --%>
            <td></td>
            <td></td>

        </tr>
        </table>
        </div>
  <br/>
  <br/>
  <br/>
  <br/>
  <br/>
  
  
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>