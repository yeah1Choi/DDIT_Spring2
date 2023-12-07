<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.108.0">
<title>DDIT BOARD LIST</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/album/">
<link
	href="${pageContext.request.contextPath }/resources/assets/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<body>
	<header>
		<div class="collapse bg-dark" id="navbarHeader"></div>
		<div class="navbar navbar-dark bg-dark shadow-sm">
			<div class="container"></div>
		</div>
	</header>
	<main>
		<section class="py-1 text-center container">
			<div class="row py-lg-4">
				<div class="col-lg-6 col-md-8 mx-auto">
					<h1 class="fw-light">DDIT 상세보기</h1>
				</div>
			</div>
		</section>
		<section class="py-1 container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							${tagBoard.boTitle }
						</div>
						<div class="card-body">
							${tagBoard.boWriter }&nbsp;
							${tagBoard.boDate }&nbsp;
							${tagBoard.boHit }&nbsp;
						</div>
						<div class="card-body">
							${tagBoard.boContent }&nbsp;
						</div>
						<div class="card-body">
							<c:choose>
								<c:when test="${empty tagList }">
									<span class="badge bg-success">태그가 없습니다.</span>
								</c:when>
								<c:otherwise>
									<c:forEach items="${tagList }" var="tag">
										<span class="badge bg-success">${tag.tagName }</span>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="card-footer">
							<button type="button" class="btn btn-warning" id="modifyBtn">수정</button>
							<button type="button" class="btn btn-danger" id="delBtn">삭제</button>
							<button type="button" class="btn btn-info" id="listBtn">목록</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		<form action="/test/delete.do" method="post" id="delForm">
			<input type="hidden" name="boNo" value="${tagBoard.boNo }">
		</form>
	</main>
	<script src="${pageContext.request.contextPath }/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
</body>
<script type="text/javascript">
$(function(){
	var modifyBtn = $("#modifyBtn");
	var delBtn = $("#delBtn");
	var listBtn = $("#listBtn");
	
	var delForm = $("#delForm");	// 수정 및 삭제를 처리할 form element
	
	modifyBtn.on("click", function(){
		delForm.attr("action", "/test/modify.do");
		delForm.attr("method", "get");
		delForm.submit();
	});
	delBtn.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
	listBtn.on("click", function(){
		location.href = "/test/list.do";
	});
});
</script>
</html>