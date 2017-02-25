<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
<div id="fb-root"></div>
<script>(function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id))
            return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >
 
<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>     
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
<c:forEach var="sList" items="${studyList}">
        <tr>   
            <%-- First study details --%>
            <td>${sList.studyName}</td>
            <td>${sList.requestedParticipants}</td>
            <td>${sList.numOfParticipants}</td>
            <c:choose>
            <c:when test="${sList.status eq 'start' }">
            <td> <form action="StudyController" method="post"><button type="submit" class="btn btn-primary">Stop</button>    <input type="hidden" name="action" value="stop"> 
             <input type="hidden" name="StudyCode" value="${sList.studyCode}">
              <input type="hidden" name="email" value="${theUser.email}">
            </form></td>
            </c:when>
            <c:otherwise>
            <td> <form action="StudyController" method="post"><button type="submit" class="btn btn-primary">Start</button>    <input type="hidden" name="action" value="start"> 
             <input type="hidden" name="StudyCode" value="${sList.studyCode}">
              <input type="hidden" name="email" id="email" value="${theUser.email}">
            </form></td>
            </c:otherwise></c:choose>
            <%-- Code to display edit page --%>
            <td><form action="StudyController" method="post">
                    <button type="submit" class="btn btn-primary">Edit</button>
                        <input type="hidden" name="action" value="edit">
                       <input type="hidden" name="StudyCode" value="${sList.studyCode}">
                       <input type="hidden" name="email" value="${theUser.email}">
                    
                    </form></td>
            <td>
                       <div class="fb-share-button" data-href="http://localhost:8084/StudyController?action=participate&studyCode=$study.getStudyID()" data-layout="button_count" data-mobile-iframe="false"></div>
            </td>

        </tr>
</c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td> 
            <td></td> 
            <td></td> 
        </tr>
    </table>
</div>
</section>
  <br/>
  <br/>
  <br/>
  <br/>
  <br/>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>