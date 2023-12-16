<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card my-4">
				<div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
					<div
						class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
						<h6 class="text-white text-capitalize ps-3">공지사항 게시판</h6>
					</div>
				</div>
				<div class="card-body px-0 pb-2">
					<div class="row">
						<div class="col-md-8"></div>
						<div class="col-md-4">
							<form class="input-group input-group-outline" id="searchForm">
								<input type="hidden" name="page" id="page" />
								<div class="col-md-2">
									<div class="input-group input-group-static mb-4">
										<select class="form-control" id="searchType" name="searchType">
											<option value="title"
												<c:if test="${searchType eq 'title' }">selected</c:if>>제목</option>
											<option value="writer"
												<c:if test="${searchType eq 'writer' }">selected</c:if>>작성자</option>
										</select>
									</div>
								</div>
								<div class="col-md-8">
									<div class="ms-md-auto">
										<label class="form-label"></label> <input type="text"
											class="form-control" name="searchWord" value="${searchWord }">
									</div>
								</div>
								<div class="col-md-2">
									<button type="submit" class="btn btn-outline-secondary">검색</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="card-body px-0 pb-2">
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0">
							<thead>
								<tr class="text-center">
									<th width="4%" class="text-dark font-weight-bolder">번호</th>
									<th width="px" class="text-dark font-weight-bolder">제목</th>
									<th width="14%" class="text-dark font-weight-bolder">작성자</th>
									<th width="14%" class="text-dark font-weight-bolder">작성일</th>
									<th width="6%" class="text-dark font-weight-bolder">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:set value="${pagingVO.dataList }" var="boardList" />
								<c:choose>
									<c:when test="${empty boardList }">
										<tr class="text-center">
											<td colspan="5" class="text-dark font-weight-bolder">조회하신 게시글이 존재하지 않습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${boardList }" var="board">
											<tr class="text-center">
												<td>${board.boNo }</td>
												<td class="text-dark"> 
													<a href="/board/detail.do?boNo=${board.boNo }">${board.boTitle }</a> 
												</td>
												<td>${board.boWriter }</td>
												<td><span class="text-dark text-xs font-weight-bold">${board.boDate }</span></td>
												<td><span class="text-dark text-xs font-weight-bold">${board.boHit }</span></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-footer">
					<button type="button" class="btn btn-outline-primary" id="addBtn" onclick="javascript:location.href='/board/form.do'">등록</button>
				</div>
				<nav aria-label="Page navigation example" id="pagingArea">${pagingVO.pagingHTML }</nav>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	var searchForm = $("#searchForm");
	var pagingArea = $("#pagingArea");
	
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		var pageNo = $(this).data("page");
		searchForm.find("#page").val(pageNo);
		searchForm.submit();
	});
});	
</script>