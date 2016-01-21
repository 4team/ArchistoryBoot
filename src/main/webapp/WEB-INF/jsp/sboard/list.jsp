<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>


<style type= "text/css">
.box{
position : fix;
margin-left :200px;
margin-right:200px;
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

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">SEARCH</h3>
				</div>

				<h1>${cri.searchType}</h1>
				<div class='box-body'>

					<select name="searchType" id="searchType">
						<option value="n"
							<c:out value="${cri.searchType == null?'selected':''}"/>>
							---</option>
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							Title</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							Content</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							Writer</option>
						<option value="tc"
							<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
							Title OR Content</option>
						<option value="cw"
							<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
							Content OR Writer</option>
						<option value="tcw"
							<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							Title OR Content OR Writer</option>
					</select> <input type="text" name='keyword' id="keywordInput"
						value='${cri.keyword }'>
							<button id='searchBtn'>Search</button>						
			</div>
		</div>
		
				<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
				
<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BoardNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>


<c:forEach items="${list}" var="list">
<tr>
<td>${list.boardNo}</td>
<td><a href='/sboard/read${pageMaker.makeSearch(pageMaker.cri.page) }&boardNo=${list.boardNo}'>${list.title}
    <strong> [${list.replycnt}]</strong></a></td>
	<td>${list.writer}</td>
	<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.regdate}" /></td>
	<td><span class="badge bg-red">${list.vcount}</span></td>
</tr>
</c:forEach>

<%-- 
<c:forEach items="${list}" var="list">

	<tr>
		<td>${list.boardNo}</td>
		<td><a href='/board/read?boardNo=${list.boardNo}&page=${cri.page}&perPageNum=${cri.perPageNum}'>${list.title}</a></td>
		<td>${list.writer}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.regdate}" /></td>
		<td><span class="badge bg-red">${list.vcount}</span></td>
	</tr>
</c:forEach> --%>

</table>

    
    <div class="text-center">
						<ul class="pagination">
						<c:if test="${pageMaker.prev}">
						<li><a href='javascript:goPage(${pageMaker.startPage-1});'><<</a>
						</c:if>
						
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li class='${pageMaker.cri.page==idx?"active":""}'><a href='javascript:goPage(${idx});'>${idx}</a></li>
							</c:forEach>
						
						<c:if test="${pageMaker.next}">
						<li><a href='javascript:goPage(${pageMaker.endPage+1});'>>></a>
						</c:if>
						</ul>
						
						<%-- <ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
							</c:if>

						</ul> --%>
					</div>
			
<div class="box-footer" style="float: right">
	<button type="submit" class="btn btn-write">WRITE</button>	
</div>


<form id="jobForm" method="get">
  <input type='hidden' name="boardNo" value=''>
  <input type='hidden' name="page" value='${pageMaker.cri.page}'>
  <input type='hidden' name="perPageNum" value='${pageMaker.cri.perPageNum}'>
  <input type='hidden' name="searchType" value='${cri.searchType}'>
  <input type='hidden' name="keyword" value='${cri.keyword}'>
</form>


<script>
var result='${msg}';
if(result=='SUCCESS'){
	alert("처리가 완료되었습니다.")
}

$(document).ready(function() {

	$(".btn-write").on("click", function() {
		self.location = "/sboard/register";
	});
});

function viewPage(boardNo){
	var jobForm=$("#jobForm");
	
	jobForm.attr("action","/sboard/read");
	jobForm.find("[name='boardNo']").val(boardNo);
	jobForm.submit();
}

function goPage(num){
	console.log(num);
	var jobForm=$("#jobForm");
	
	jobForm.attr("action","/sboard/list");
	jobForm.find("[name='page']").val(num);
	jobForm.submit();	
}


$("#searchBtn").on("click",function(event){
	event.preventDefault();
	var jobForm=$("#jobForm");
	var sType = $("#searchType option:selected").val();
	var keyword = $("[name='keyword']").val();
	console.log(sType+" : " + keyword);
	
	jobForm.attr("action","/sboard/list");
	jobForm.find("[name='keyword']").val(keyword);
	jobForm.find("[name='searchType']").val(sType);
	jobForm.find("[name='page']").val(1);
	jobForm.submit();
});


</script>

				</div>
				<!-- /.box-body -->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>
</div>    
    
<%@include file="../include/footer.jsp"%>