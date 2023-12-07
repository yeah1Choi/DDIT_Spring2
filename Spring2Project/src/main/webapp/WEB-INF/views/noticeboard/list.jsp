<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>공지사항 게시판</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">DDIT HOME</a></li>
					<li class="breadcrumb-item active">공지사항 게시판</li>
				</ol>
			</div>
		</div>
	</div>
</section>

<section class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-dark card-outline">
					<div class="card-header">
						<div class="card-tools">
							<form id="searchForm" class="input-group input-group-sm" style="width: 440px;" method="post">
								<input type="hidden" name="page" id="page">
								<select class="form-control" name="searchType">
									<option value="title" <c:if test="${searchType eq 'title' }">selected</c:if>>제목</option>
									<option value="writer" <c:if test="${searchType eq 'writer' }">selected</c:if>>작성자</option>
								</select> 
								<input type="text" name="searchWord" value="${searchWord }" class="form-control float-right" placeholder="Search">
								<div class="input-group-append">
									<button type="submit" class="btn btn-default">
										<i class="fas fa-search"></i>검색
									</button>
								</div>
							</form>
						</div>
						<h3 class="card-title">공지사항</h3>
					</div>
					<div class="card-body">
						<table class="table table-bordered">
							<thead class="table-dark">
								<tr>
									<th style="width: 6%">#</th>
									<th style="width: px">제목</th>
									<th style="width: 12%">작성자</th>
									<th style="width: 12%">작성일</th>
									<th style="width: 10%">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:set value="${pagingVO.dataList }" var="noticeList" />
								<c:choose>
									<c:when test="${empty noticeList }">
										<tr>
											<td colspan="5">조회하신 게시글이 존재하지 않습니다 !</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${noticeList }" var="notice">
											<tr>
												<td>${notice.boNo }</td>
												<td> <a href="/notice/detail.do?boNo=${notice.boNo }">${notice.boTitle }</a> </td>
												<td><font class="badge badge-danger" style="font-size: 14px;">${notice.boWriter }</font></td>
												<td>${notice.boDate }</td>
												<td>${notice.boHit }</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<div class="card-footer" align="right">
						<button type="button" class="btn btn-dark" id="newBtn">등록</button>
					</div>
					<div class="card-footer clearfix" id="pagingArea">
						${pagingVO.pagingHTML }
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	$(function() {
		// 페이징을 처리할 때 사용할 Element
		// pagingArea div 안에 ul과 li로 구성된 페이징 정보가 존재
		// 그 안에는 a 태그로 구성된 페이지 정보가 들어있음
		// a 태그 안에 들어있는 page 번호를 가져와서 페이징 처리를 진행
		var pagingArea = $("#pagingArea");
		var searchForm = $("#searchForm");
		var newBtn = $("#newBtn");

		pagingArea.on("click", "a", function(event) {
			event.preventDefault(); // a태그의 이벤트를  block
			var pageNo = $(this).data("page");
			
			searchForm.find("#page").val(pageNo);
			searchForm.submit();
		});
		
		// 등록 버튼 클릭 시, 게시판 등록 페이지로 이동
		newBtn.on("click",function(){
			location.href = "/notice/form.do";
		});
	});
</script>






