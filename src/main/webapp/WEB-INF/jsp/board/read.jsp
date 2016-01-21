<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>

<div class="center-container">
    <div class="center-row">
    
    <!-- Main content -->
    <section class="content" >
      <div class="row">
      <!-- left column -->
      <div class="col-md-12">
        <!-- general form elements -->
        <div class="box box-primary">
        <div class="box-header">
          <h3 class="box-title" >READ BOARD</h3>
        </div><!-- /.box-header -->


	<form role="form" action="modify" method="post">
		<input type='hidden' name='boardNo' value="${boardVO.boardNo}">
		<input type='hidden' name='page' value ="${cri.page}">
    	<input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
	</form>
	
	
<div class="box-body">
    <div class="form-group">
      <label for="exampleInputEmail1">Title</label>
      <input type="text" name='title' class="form-control" value="${boardVO.title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">Content</label>
      <textarea class="form-control"  name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
    </div>
    <div class="form-group">
      <label for="exampleInputEmail1" >Writer</label>
      <input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
    </div>
  </div><!-- /.box-body -->

  <div class="box-footer">
    <button type="submit" class="btn btn-modify">MODIFY</button>
    <button type="submit" class="btn btn-remove">REMOVE</button>
    <button type="submit" class="btn btn-list">GO LIST</button>
  </div>

	<script>
		$(document).ready(function() {

			var formObj = $("form[role='form']");

			console.log(formObj);

			$(".btn-modify").on("click", function() {
				formObj.attr("action", "/board/modify");
				formObj.attr("method", "get");
				formObj.submit();
			});

			$(".btn-remove").on("click", function() {
				formObj.attr("action", "/board/remove");
				formObj.submit();
			});

			$(".btn-list").on("click", function() {
				formObj.attr("method","get");
				formObj.attr("action","/board/listPage")
				formObj.submit();
			});

		})
	</script>
 </div><!-- /.box -->
      </div><!--/.col (left) -->
 </div>
      </div>   <!-- /.row -->
    </section><!-- /.content -->
    <!-- /.content-wrapper -->
    
    </div>
</div>

<%@include file="../include/footer.jsp"%>