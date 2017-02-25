<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/newstudy.js">
</script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Adding a study</h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to create new study --%>
<section>
    <form class="form-horizontal" action="StudyController?action=add"  method="post" enctype="multipart/form-data" >
    
    	<div class="form-group">
        <label class="col-sm-4 control-label">Study Name *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="study_name" required />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Question Text *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="question_text" required/>
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Image *</label>
        <div class="col-sm-4">
        <input id="imageurl" type="file" accept="image/*" class="form-control" name="imageURL" required />
         </div>
            </div>
        
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Participants *</label>
        <div class="col-sm-4">
        <input type="text" class="form-control" name="participant_text" id="participant_text" maxlength="2" required/>
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Answers *</label>
        <div class="col-sm-4">
        <select name="answers" class="form-control" id="new_study_answers" >
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select> <br>
         </div>
            </div>
              <input type ="hidden" name="email" id="email" value="${theUser.email}"/>
        
        <div id="TextBoxContainer">
    	<!-- Textboxes will be added here -->
		</div>
       
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Description *</label>
        <div class="col-sm-4">
        <textarea name="description" class="form-control" required></textarea>
         </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
        <br/><br/><br/>
         </div>
            </div>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>