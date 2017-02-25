<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/editstudy.js"></script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section>
    <form class="form-horizontal" action="StudyController?action=update" method="post" enctype="multipart/form-data">
    
    	<div class="form-group">
        <label class="col-sm-4 control-label">Study Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="study_name" value="${study.studyName}" required />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Question Text *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="question_text" value="${study.question}" required/>
         </div>
            </div>
        
        
        <%-- Img tag is used to import image --%>
        <div class="form-group">
        <label class="col-sm-4 control-label">Image *</label>
        <div class="col-sm-4">
        <img src="${study.getImageURL()}" class="img-responsive" height="50" width="75" alt="Edit"/>
        <input type="hidden" name="ogimg" value="${study.getImageURL()}"/>
        <input id="imageurl" type="file" accept="image/*" class="form-control" name="imageURL"/>
         </div>
            </div>
        
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Participants *</label>
         <div class="col-sm-4"> 
        <input type="text" class="form-control" name="participants" id="participants" maxlength="2" value="${study.requestedParticipants}" required/>
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Answers *</label>
        <div class="col-sm-4">
        <select class="form-control" id="edit_study_answers">
            <option value="3" <c:if test="${fn:length(study.answers)==3}">selected</c:if>>3</option>
            <option value="4" <c:if test="${fn:length(study.answers)==4}">selected</c:if>>4</option>
            <option value="5" <c:if test="${fn:length(study.answers)==5}">selected</c:if>>5</option>
        </select> 
         </div>
         </div>
        
            <!-- <input type ="hidden" name="numbers" id="numbers" value="${fn:length(study.answers)}"/> -->
           <c:forEach items="${study.answers}" var="km" varStatus="cnt">
<c:if test="${not empty km}">
               <c:set var="anscount" value = "${cnt.count}" />
     </c:if>
</c:forEach>
            <input type ="hidden" name="numbers" id="numbers" value="<%=pageContext.getAttribute("anscount") %>"/>
 	<c:forEach var="i" begin="0" end="4">
     <input type ="hidden" name="values" id="${i }" value="${study.answers.get(i)}"/>
     </c:forEach>
       <input type ="hidden" name="code" id="code" value="${study.studyCode}"/>
      <input type ="hidden" name="email" id="email" value="${study.email}"/>
      <input type ="hidden" name="status" id="status" value="${study.status}"/>
      <input type ="hidden" name="numparticipants" id="numparticipants" value="${study.numOfParticipants}"/>
        <div id="TextBoxContainer1">
    <!--Textboxes will be added here -->
		</div>
       
       
       <div class="form-group">
        <label class="col-sm-4 control-label">Description *</label>
         <div class="col-sm-4"> 
        <textarea name="description" class="form-control"  required>${study.description}</textarea>
         </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Update</button>
         </div>
            </div>
            <br/><br/><br/>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>