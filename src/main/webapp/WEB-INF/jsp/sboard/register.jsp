<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="/js/upload.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style type="text/css">
.fileDrop {
  width: 40%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
}
small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
.img{
float:left;
}
.button{
margin-left: 5px;
}
.x{
text-align:right;
}
.box{
position: relative;
margin-left :200px;
}
</style>



 <div class="center-container" >
    <div class="center-row"> 


<!-- Main content -->
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-primary" >
						<div class="box-header">
							<h3 class="box-title">REGISTER BOARD</h3>
						</div>

						<!-- /.box-header -->

		
						<form id='registerForm' role="form" method="post">
							<div class="box-body"  >
								<div class="form-group">
									<label for="exampleInputEmail1">Title</label> <input
										type="text" name='title' class="form-control"
										placeholder="Enter Title">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Content</label>
									<textarea class="form-control" name="content" rows="3"
										placeholder="Enter ..."></textarea>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Writer</label> <input
										type="text" name="writer" class="form-control"
										placeholder="Enter Writer">
								</div>

								<div class="form-group">
									<label for="exampleInputEmail1">File DROP Here</label>
									<br>
									<div class="fileDrop"></div>
								</div>
								

							</div>
							<!-- /.box-body -->
							<div class="box-footer">
							<ul class="mailbox-attachments clearfix uploadedList" style="display:inline"></ul>
							
								<button type="submit" class="btn btn-save">SAVE</button>
								<button type="submit" class="btn btn-cancel">CANCEL</button>
							</div>
							<!-- /.box-footer -->
							

					    </form>
					</div>
										
					</div>

				</div>
						</section>
       <!-- /.content -->
	</div>
	<!-- /.center-row -->
</div>
<!-- center-container --> 

	
		<script>
			$(document).ready(function() {

				var formObj = $("form[role='form']");
				console.log(formObj);

				$(".btn-cancel").on("click", function() {
					event.preventDefault();
					self.location = "/sboard/list";
				});

				$(".btn-save").on("click", function() {
					formObj.submit();
				});
			});
		</script>


<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
  </script>

<script>
var template = Handlebars.compile($("#template").html());

$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];
	console.log(file);
	var formData = new FormData();
	
	formData.append("file", file);	
	
	
	$.ajax({
		  url: '/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);			  
			  var html = template(fileInfo);
			  
			  var str ="";
			  
			  console.log(data);
			  console.log(checkImageType(data));
			  
			  if(checkImageType(data)){
				  str ="<div class='img'>"
				  		+"<a href='http://192.168.0.36:8080/displayFile?fileName="+getImageLink(data)+"'><img src='http://192.168.0.36:8080/displayFile?fileName="+data+"'/></a>"
						  +"<small data-src='"+data+"'><div class='x'>X</div></small><input type='hidden' name='files' value='"+data+"'>"
						  +"</div>";
				  
			  }else{
				  str = "<div class='img'>"
				  		+"<a href='http://192.168.0.36:8080/displayFile?fileName="+data+"'>"+ getOriginalName(data)+"</a>"
						  +"<small data-src='"+data+"'><div class='x'>X</div></small><input type='hidden' name='files' value='"+data+"'>"
						  +"</div>";
			  }
			  
			  
			  
			  $(".uploadedList").append(str);
		  }
		});	
});

$(".uploadedList").on("click", "small", function(event){
	
	 var that = $(this);
console.log("delete click");
  $.ajax({
	   url:"deleteFile",
	   type:"post",
	   data: {fileName:$(this).attr("data-src")},
	   dataType:"text",
	   success:function(result){
		   if(result == 'deleted'){
			   that.parent("div").remove();
		   }
	   }
  });
});


$("#registerForm").submit(function(event){
	event.preventDefault();
	
	var that = $(this);
	
	var str ="";
	$(".uploadedList .delbtn").each(function(index){
		 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
	});
	
	that.append(str);

	that.get(0).submit();
}); 


function getOriginalName(fileName){	

	if(checkImageType(fileName)){
		return;
	}	
	var idx = fileName.indexOf("_") + 1 ;
	return fileName.substr(idx);
	
}


function getImageLink(fileName){
	
	if(!checkImageType(fileName)){
		return;
	}
	var front = fileName.substr(0,12);
	var end = fileName.substr(14);
	
	return front + end;
	
}

	function checkImageType(fileName){
		var pattern = /jpg|gif|png|jpeg/i;		
		return fileName.match(pattern);
		
	}
</script>

<%@include file="../include/footer.jsp"%>