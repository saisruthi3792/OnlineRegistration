<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${theUser.numCoins}</span>) </li>
        <li>Participants (<span class="count">${par}</span>) </li>
        <li>Participation (<span class="count">${theUser.numParticipation}</span>) </li>
        <li><br></li>
        <li><a href="UserController?action=main">Home</a></li>
        <li><a href="StudyController?action=participate">Participate</a></li>
        <li><a href="StudyController?action=studies">My Studies</a></li>
        <li><a href="recommend.jsp">Recommend</a></li>
        <li><a href="contact.jsp">Contact</a></li>
    </ul>

</nav>
<%-- Section to display studies and participate in that study--%>
<div>
       <c:set var="email" value="${theUser.email }" scope="request"/>
     <h3 class="text-left"><span class="label label-default " >Studies</span>
     <span ><a class="label label-default" href="StudyController?action=report">Report History</a></span></h3>
     </div>
     
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
     <div class="table-responsive">
    <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>      
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>
    
        <c:forEach var="studi" items="${studies}">
        
        <tr>
            <%-- First study details --%>
            <td> <c:out value="${studi.studyName}"/></td>
            <td><img src="${studi.getImageURL()}" alt="Tree"></td>
            <td> <c:out value="${studi.question}"/></td>
            <td><form action="StudyController" method="post"><input type="submit" class="participate_button"
                                                                                value="Participate" />
                                                                                <input type="hidden" name="action" value="participate">
                                                                                <input type="hidden" name="StudyCode" value="${studi.studyCode}">
                                                                                </form></td>
            <td><form action="StudyController" method="post"><input type="submit" class="participate_button"
                                                                                value="Report" />
                                                                            <input type="hidden" name="action" value="report">
                                                                               <input type ="hidden" name="email"  value="${theUser.email}"/>
                                                                                 <input type="hidden" name="ques" value="${studi.question}">
                                                                               <input type="hidden" name="StudyCode" value="${studi.studyCode}">     
                                                                                </form></td>

        </tr>
        
          </c:forEach>
       <%--  <tr> 
            Second study details
            <td>Sec</td>
            <td><img src="images/computer.jpg" alt="Computer"></td>
            <td>I use computers on a daily basis</td> 
            <td><form action="StudyController" method="post"><input type="submit" class="participate_button"
                                                                                value="Participate" />
                                                                                <input type="hidden" name="action" value="Participate">
                                                                                </form></td>
            <td><form action="StudyController" method="post"><input type="submit" class="participate_button"
                                                                                value="Report" /></form></td>

        </tr> --%>
        <tr>
            <td></td>
            <td></td>
            <td></td> 
             <td></td>
             <td></td> 
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td> 
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