<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
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
							<h3 class="box-title">REGISTER BOARD</h3>
						</div>
						<!-- /.box-header -->

<title> REGISTER PAGE</title>
						<form id='registerForm' role="form" method="post">
							<div class="box-body">
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

							</div>
		
						</form>
						<div class="box-footer">
	<button type="submit" class="btn btn-save">SAVE</button>
	<button type="submit" class="btn btn-cancel">CANCEL</button>
</div>
					</div>
				</div>
			</div>
		</section>
		
		
		

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-cancel").on("click", function() {
			self.location = "/board/listPage";
		});

		$(".btn-save").on("click", function() {
			formObj.submit();
		});

	});
</script>

    
    
    </div>
</div>

<%@include file="../include/footer.jsp"%>