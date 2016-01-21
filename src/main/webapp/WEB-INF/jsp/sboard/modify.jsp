<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>


<style type= "text/css">
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
.liii{
float:left;
padding :5px;
}
li{
list-style:none;
}
</style>

<div class="center-container">
    <div class="center-row">
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
      <!-- left column -->
      <div class="col-md-12">
        <!-- general form elements -->
        <div class="box box-primary">
        <div class="box-header">
          <h3 class="box-title">MODIFY PAGE</h3>
        </div><!-- /.box-header -->
        
    
<form role="form" action="modify" method="post">

<input type='hidden' name='page' value="${cri.page}"> 
<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
<input type='hidden' name='searchType' value ="${cri.searchType}">
<input type='hidden' name='keyword' value ="${cri.keyword}">



	<div class="box-body">

		<div class="form-group">
			<label for="exampleInputEmail1">BoardNo</label> <input type="text"
				name='boardNo' class="form-control" value="${boardVO.boardNo}"
				readonly="readonly">
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> <input type="text"
				name='title' class="form-control" value="${boardVO.title}">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> <input
				type="text" name="writer" class="form-control"
				value="${boardVO.writer}" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label for="exampleInputEmail1">File DROP Here</label>
			<br>
			<div class="fileDrop"></div>
		</div>
				
		<div class="form-group">
		 <label for="exampleInputEmail1">Attached Files</label>
		 <ul class="mailbox-attachments clearfix uploadedList"></ul>
		</div>
                  
	</div>
	<!-- /.box-body -->

<div class="box-footer">
	<button type="submit" class="btn btn-save">SAVE</button>
	<button type="submit" class="btn btn-cancel">CANCEL</button>
</div>

</form>


 </div><!-- /.box -->
      </div><!--/.col (left) -->
 
      </div>   <!-- /.row -->
    </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    
    </div>
    
    
<script id="template" type="text/x-handlebars-template">
<div class="liii">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>     
</div>           
</script> 


<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-cancel").on("click", function() {
            formObj.attr("method", "get");
            formObj.attr("action", "/sboard/list")
            formObj.submit();
         });

		$(".btn-save").on("click", function() {
			formObj.submit();
		});

	});
	


	var template = Handlebars.compile($("#template").html());


	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});


	$(".fileDrop").on("drop", function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];

		//console.log(file);
		
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
				  
				  $(".uploadedList").append(html);
			  }
			});	
	});


	$(".uploadedList").on("click", ".delbtn", function(event){
		
		event.preventDefault();
		
		var that = $(this);
		 
		$.ajax({
		   url:"/sboard/deleteFile",
		   type:"post",
		   data: {fileName:$(this).attr("href")},
		   dataType:"text",
		   success:function(result){
			   if(result == 'deleted'){
				   that.closest("li").remove();
			   }
		   }
	   });
	});


	var boardNo = ${boardVO.boardNo};
	var template = Handlebars.compile($("#template").html());

	$.getJSON("/sboard/getAttach/" + boardNo ,function(list){
		console.log("반복문 바깥");
		$(list).each(function(){
			
			var fileInfo = getFileInfo(this);
			
			var html = template(fileInfo);
			
			 $(".uploadedList").append(html);
			
		});
	});

	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		
		var fileLink = $(this).attr("href");
		
		if(checkImageType(fileLink)){
			
			event.preventDefault();
					
			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);
			
			console.log(imgTag.attr("src"));
					
			$(".popup").show('slow');
			imgTag.addClass("show");		
		}	
	});

	$("#popup_img").on("click", function(){
		
		$(".popup").hide('slow');
		
	});	

</script>
    
   


<%@include file="../include/footer.jsp"%>