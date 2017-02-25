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
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="${study.getImageURL()}" class="img-responsive" height="250" width="250" alt="Tree"/>

<%--Code to rating the Question --%>
    <p class="text-left"><c:out value="${study.question}"/> </p>

        <form action="StudyController?action=answer" method="post">
<!--         <div class="radio">
            <input type="radio" name="number" value="three" required>3
            </div>
<div class="radio">
            <input type="radio" name="number" value="four">4
            </div>
            <div class="radio">
            <input type="radio" name="number" value="five">5
            </div>   -->
    
    <c:forEach var="studi" items="${study.answers}">
    <c:if test="${studi ne null}">
    <div class="radio">
            <input type="radio" name="radname" value="${studi}" required>${studi}
            </div>
    </c:if>
    </c:forEach>
    
<%-- Code to submit the Rating  --%>
    
         <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
         <input type="hidden" name="StudyCode" value="${study.studyCode}">
              <input type="hidden" name="email" id="email" value="${theUser.email}">
         </div>
            </div>
            <br/><br/><br/>   
        </form>
        
    
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>