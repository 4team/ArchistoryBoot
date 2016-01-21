<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<script type="text/javascript" src="/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<!-- Main content -->

<style type="text/css">
.popup {
   position: absolute;
}

.back {
   background-color: gray;
   opacity: 0.5;
   width: 100%;
   height: 300%;
   overflow: hidden;
   z-index: 1101;
}

.front {
   z-index: 1110;
   opacity: 1;
   boarder: 1px;
   margin: auto;
}

.show {
   position: relative;
   max-width: 1200px;
   max-height: 800px;
   overflow: auto;}
   
.box {
	position : relative;
margin-left :200px;
margin-right:200px;
}

.timeline{
margin-left :200px;
}

.has-img{
float:left;
}
.liii{
float:left;
padding :5px;}

li{
list-style:none;
}
</style>

 <div class='popup back' style="display: none;"></div>
      <div id="popup_front" class='popup front' style="display: none;">
         <img id="popup_img">
</div>


 <div class="center-container" >
    <div class="center-row"> 


<section class="content">
         <div class="row">
            <!-- left column -->
            <div class="col-md-12">
               <!-- general form elements -->
               <div class="box box-primary">
                  <div class="box-header">
                     <h3 class="box-title">READ BOARD</h3>
                  </div>
                  <!-- /.box-header -->


                  <form role="form" action="modify" method="post">
                     <input type='hidden' name='boardNo' value="${boardVO.boardNo}">
                     <input type='hidden' name='page' value="${cri.page}"> <input
                        type='hidden' name='perPageNum' value="${cri.perPageNum}">
                     <input type='hidden' name='searchType' value="${cri.searchType}">
                     <input type='hidden' name='keyword' value="${cri.keyword}">
                  </form>


                  <div class="box-body">
                     <div class="form-group">
                        <label for="exampleInputEmail1">Title</label> <input type="text"
                           name='title' class="form-control" value="${boardVO.title}"
                           readonly="readonly">
                     </div>
                     <div class="form-group">
                        <label for="exampleInputPassword1">Content</label>
                        <textarea class="form-control" name="content" rows="3"
                           readonly="readonly">${boardVO.content}</textarea>
                     </div>
                     <div class="form-group">
                        <label for="exampleInputEmail1">Writer</label> <input
                           type="text" name="writer" class="form-control"
                           value="${boardVO.writer}" readonly="readonly">
                     </div>
                     <div class="form-group">
                        <label for="exampleInputEmail1">Attached Files</label>
                  <li class="clearfix uploadedList"></li>
                  </div>
                  <div class="box-footer">                                   
                     <button type="submit" class="btn btn-modify">MODIFY</button>
                     <button type="submit" class="btn btn-remove">REMOVE</button>
                     <button type="submit" class="btn btn-list">GO LIST</button>
                  </div>
				</div>


               </div>
               <!-- /.box -->
            </div>
            <!--/.col (left) -->

         </div>
         <!-- /.row -->         

         <div class="row">
            <div class="col-md-12">

               <div class="box box-success">
                  <div class="box-header">
                  <br>
                     <h3 class="box-title">ADD NEW REPLY</h3>
                  </div>
                  <div class="box-body">
                     <label for="exampleInputEmail1">Writer</label> <input
                        class="form-control" type="text" placeholder="USER ID"
                        id="newReplyWriter"> <label for="exampleInputEmail1">Reply
                        Text</label> <input class="form-control" type="text"
                        placeholder="REPLY TEXT" id="newcontent">

                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                     <button type="button" class="btn btn-primary" id="replyAddBtn">ADD
                        REPLY</button>
                  </div>
               </div>


               <!-- The time line -->
               <ul class="timeline">
                  <!-- timeline time label -->
                  <li class="time-label" id="repliesDiv">
            <span class="bg-green">
          Replies List <small id='replycntSmall'> [${boardVO.replycnt}]</small>
          </span> 
                  </li>
               </ul>

               <div class='text-center'>
                  <ul id="pagination" class="pagination pagination-sm no-margin ">

                  </ul>
               </div>

            </div>
            <!-- /.col -->
         </div>
         <!-- /.row -->
         
         <!-- Modal -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body" data-replyNo>
        <p><input type="text" id="content" class="form-control"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>      

</section>

</div>
</div>
</div>
</div>
        </section>

<script id="templateAttach" type="text/x-handlebars-template">
<div class="liii">
<li data-src='{{fullName}}'>
  	<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
<div class="mailbox-attachment-info">
  	 <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</div>    
</li>
</div>
</script>


<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-replyNo={{replyNo}}>
<i class="fa fa-comments bg-blue"></i>
 <div class="timeline-item" >
  <span class="time">
    <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
  </span>
  <h3 class="timeline-header"><strong>{{replyNo}}</strong> -{{writer}}</h3>
  <div class="timeline-body">{{content}} </div>
    <div class="timeline-footer">
     <a class="btn btn-primary btn-xs" 
       data-toggle="modal" data-target="#modifyModal">Modify</a>
    </div>
  </div>         
</li>
{{/each}}
</script>

         <script>
            Handlebars.registerHelper("prettifyDate", function(timeValue) {
               var dateObj = new Date(timeValue);
               var year = dateObj.getFullYear();
               var month = dateObj.getMonth() + 1;
               var date = dateObj.getDate();
               return year + "/" + month + "/" + date;
            });

            var printData = function(replyArr, target, templateObject) {

               var template = Handlebars.compile(templateObject.html());

               var html = template(replyArr);
               $(".replyLi").remove();
               target.after(html);

            }

            var boardNo = ${boardVO.boardNo};
            var replyPage = 1;

            function getPage(pageInfo) {
               $.getJSON(pageInfo, function(data) {
                  printData(data.list, $("#repliesDiv"), $('#template'));
                  printPaging(data.pageMaker, $(".pagination"));
                  
                $("#modifyModal").modal('hide');
                $("#replycntSmall").html("[ " + data.pageMaker.totalCount +" ]");

               });
            }

            var printPaging = function(pageMaker, target) {

               var str = "";

               if (pageMaker.prev) {
                  str += "<li><a href='" + (pageMaker.startPage - 1)
                        + "'> << </a></li>";
               }

               for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
                  var strClass = pageMaker.cri.page == i ? 'class=active'
                        : '';
                  str += "<li "+strClass+"><a href='"+i+"'>" + i
                        + "</a></li>";
               }

               if (pageMaker.next) {
                  str += "<li><a href='" + (pageMaker.endPage + 1)
                        + "'> >> </a></li>";
               }

               target.html(str);
            };

            $("#repliesDiv").on("click", function() {

               if ($(".timeline li").size() > 1) {
                  return;
               }
               getPage("/replies/" + boardNo + "/1");

            });

            $(".pagination").on("click", "li a", function(event) {

               event.preventDefault();

               replyPage = $(this).attr("href");

               getPage("/replies/" + boardNo + "/" + replyPage);

            });
            
            $("#replyAddBtn").on("click", function(){
               
               var writerObj = $("#newReplyWriter");
               var contentObj = $("#newcontent");
               var writer = writerObj.val();
               var content = contentObj.val();
               
               $.ajax({
                  type:'post',
                  url:'/replies/',
                  headers:{
                     "Content-Type" : "application/json",
                     "X-HTTP-Method-Override" : "POST"},
                  dataType:'text',
                  data :JSON.stringify({boardNo : boardNo, writer:writer, content:content}),
                  success:function(result){
                     console.log("result: " + result);
                     if(result=='SUCCESS'){
                        alert("등록 완료");
                        replyPage=1;
                        getPage("/replies/"+boardNo+"/"+replyPage);
                        writerObj.val("");
                        contentObj.val("");
                     }
                  }               
                  
               });
            });
            

            $(".timeline").on("click", ".replyLi", function(event){
               
               var reply = $(this);
               
               $("#content").val(reply.find('.timeline-body').text());
               $(".modal-title").html(reply.attr("data-replyNo"));
               
            });
            
            
            $("#replyModBtn").on("click",function(){
                 
                 var replyNo = $(".modal-title").html();
                 var content = $("#content").val();
                 
                 $.ajax({
                     type:'put',
                     url:'/replies/'+replyNo,
                     headers: { 
                           "Content-Type": "application/json",
                           "X-HTTP-Method-Override": "PUT" },
                     data:JSON.stringify({content:content}), 
                     dataType:'text', 
                     success:function(result){
                        console.log("result: " + result);
                        if(result == 'SUCCESS'){
                           alert("수정 되었습니다.");
                           getPage("/replies/"+boardNo+"/"+replyPage );
                        }
                  }});
            });

            $("#replyDelBtn").on("click",function(){
                 
                 var replyNo = $(".modal-title").html();
                 var content = $("#content").val();
                 
                 $.ajax({
                     type:'delete',
                     url:'/replies/'+replyNo,
                     headers: { 
                           "Content-Type": "application/json",
                           "X-HTTP-Method-Override": "DELETE" },
                     dataType:'text', 
                     success:function(result){
                        console.log("result: " + result);
                        if(result == 'SUCCESS'){
                           alert("삭제 되었습니다.");
                           $(".modal-backdrop").hide('slow');
                           $("#modifyModal").hide('slow');
                           
                           getPage("/replies/"+boardNo+"/"+replyPage );
                           
                        }
                        
                  }});
            });
            
</script>


<script>
            $(document).ready(function() {

               var formObj = $("form[role='form']");

               console.log(formObj);

               $(".btn-modify").on("click", function() {
                  formObj.attr("action", "/sboard/modify");
                  formObj.attr("method", "get");
                  formObj.submit();
               });

               $(".btn-remove").on("click", function(){
           		
           		var replyCnt =  $("#replycntSmall").html();
           		
           		if(replyCnt > 0 ){
           			alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
           			return;
           		}	
           		
           		var arr = [];
           		$(".uploadedList li").each(function(index){
           			 arr.push($(this).attr("data-src"));
           		});
           		
           		if(arr.length > 0){
           			$.post("/deleteAllFiles",{files:arr}, function(){
           				
           			});
           		}
           		
           		formObj.attr("action", "/sboard/remove");
           		formObj.submit();
           	});	
           	

               $(".btn-list").on("click", function() {
                  formObj.attr("method", "get");
                  formObj.attr("action", "/sboard/list")
                  formObj.submit();
               });

            })
 
            var boardNo = ${boardVO.boardNo};
            var template = Handlebars.compile($("#templateAttach").html());

            $.getJSON("/sboard/getAttach/" + boardNo, function(list) {
           		 console.log("반복문 바깥");
               $(list).each(function() {
            	  console.log(this);
                  var fileInfo = getFileInfo(this);
                  var html = template(fileInfo);
    			  
    			  $(".uploadedList").append(html);
               });
            });

            $(".uploadedList").on("click", ".mailbox-attachment-info a",
                  function(event) {

                     var fileLink = $(this).attr("href");

                     if (checkImageType(fileLink)) {

                        event.preventDefault();

                        var imgTag = $("#popup_img");
                        imgTag.attr("src", fileLink);

                        console.log(imgTag.attr("src"));

                        $(".popup").show('slow');
                        imgTag.addClass("show");
                     }
                  });

            $("#popup_img").on("click", function() {

               $(".popup").hide('slow');

            });

            	
</script>


<%@include file="../include/footer.jsp"%>