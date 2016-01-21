<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>
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
	</div>
	<!-- /.box-body -->
</form>


<div class="box-footer">
	<button type="submit" class="btn btn-save">SAVE</button>
	<button type="submit" class="btn btn-cancel">CANCEL</button>
</div>


<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-cancel").on("click", function() {
			self.location = "/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
		});

		$(".btn-save").on("click", function() {
			formObj.submit();
		});

	});
</script>
    
     </div><!-- /.box -->
      </div><!--/.col (left) -->
 
      </div>   <!-- /.row -->
    </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    
    </div>


<%@include file="../include/footer.jsp"%>