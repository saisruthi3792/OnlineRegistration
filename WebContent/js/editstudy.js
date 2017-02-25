
//edit_study_answers
var i =1;
 $(document).ready(function() {
	 var answers = document.getElementById('numbers').value;
	console.log(answers); 
     $("#edit_study_answers").val(answers);
	 while(answers > 0){
         var div = $("<div />");
     		div.html(GetDynamicTextBox(document.getElementById(answers-1).value));
     		$("#TextBoxContainer1").append(div);
     		answers--;
     		i++;
     }
	 
	      $('#edit_study_answers').change(function() {
	    	  	answers = $(this).val();
	            $("#TextBoxContainer1").html("");
	            i = 1;
	            while(answers > 0){
		            var div = $("<div />");
	            		div.html(GetDynamicTextBox(""));
	            		$("#TextBoxContainer1").append(div);
	            		answers--;
	            		i++;
	            }
	      });
	});
 
 function GetDynamicTextBox(value) {
	 
	    return '<div class="form-group"><label class="col-sm-4 control-label">Answer '+i+' *</label><div class="col-sm-4"><input name = "DynamicTextBox'+i+'" class="form-control" type="text" required value = "' + value + '" /></div></div>'
	}

