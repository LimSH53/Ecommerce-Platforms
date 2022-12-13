<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>         

<jsp:include page="/WEB-INF/views/admin/common/header.jsp">
	<jsp:param value="게시판 관리" name="title"/>
</jsp:include>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Ionicons -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
	<!-- jvectormap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-jvectormap.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/AdminLTE.min.css">
	<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/_all-skins.min.css">
	<link href="${pageContext.request.contextPath}/resources/css/admin.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet">
	<!-- Google Font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
	
	<style>
		.option_tab { display:inline-block; width:130px;}
	</style>
	
</head>
 
<div class="content-wrapper">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<section class="content-header">
	    <h1>
		    게시판 관리
		    <small>게시판 list</small>
	    </h1>
	
	    <ol class="breadcrumb">
	        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
	        <li>게시판 관리</li>
	        <li class="active">게시판 리스트</li>
	    </ol>
	</section>
	
	<section class="content">
	    <div class="row">
	        <div class="col-xs-12">
	            <div class="box">
	                <div class="box-body">
	                    <label style="margin-top:5px;">총 ${resultPage.totalCount} 건</label>
	                    <table class="table table-bordered table-hover">
		                    <form name="form_list" method="post" action="?tpf=admin/board/manage_process">
					            <input type="hidden" name="mode" id="mode">
					            <thead>
				                    <tr>
				                        <td style="width:30px;">
				                      		<div class="allCheck">
												<input type="checkbox" name="allCheck" id="allCheck" /><label for="allCheck"></label>
												<script>
													$("#allCheck").click(function() {
														var chk = $("#allCheck").prop("checked");
														if (chk) {
															$('.boardNo').prop("checked", true);
														} else {
															$('.boardNo').prop("checked", false);
														}
													});
												</script>
											</div>
				                        </td>
				                        <td style="width:60px;">NO</td>
				                        <td>제목</td>
				                        <td style="width:250px;">연결주소</td>
				                        <td style="width:100px;">type</td>
				                        <td style="width:80px;">등록 글수</td>
				                        <td style="width:220px;">명령</td>
				                    </tr>
			                    </thead>
			                    <tbody id="boardTable" >
			                    	<c:set var="i" value="0"/>
			                    	<c:forEach var="board" items="${list}" varStatus="status" >
				                    	<c:set var="i" value="${ i+1 }" />
										<tr>
					                        <td>
						                        <div>
						                        	<input type="checkbox" class="boardNo" name="boardNo"  value="${board.boardNo}" />
						                        	<script>
														$(".boardNo").click(function() {
															$("#allCheck").prop("checked", false);
														});
													</script>
												</div>
					                        </td>
										  <td>${i}</td>
										  <td align="left">${board.boardTitle}</td>
										  <td align="left">http://localhost:8080/admin/board/postList?boardNo=${board.boardNo}]</td>
										  <td>
											<c:if test="${board.boardType == 'list'}">
										  		일반
										  	</c:if>
										  	<c:if test="${board.boardType == 'gallery'}">
										  		갤러리
										  	</c:if>
										  	<c:if test="${board.boardType == 'qna'}">
										  		FAQ
										  	</c:if>
										  	<c:if test="${board.boardType == 'webzine'}">
										  		웹진
										  	</c:if>
										  	<c:if test="${board.boardType == 'movie'}">
										  		동영상
										  	</c:if>
											<c:if test="${board.boardType == 'event'}">
										  		이벤트
										  	</c:if>
										  	<c:if test="${board.boardType == 'manual'}">
										  		메뉴얼
										  	</c:if>
										  	<c:if test="${board.boardType == 'brochure'}">
										  		브로슈어
										  	</c:if>	
										  </td>
										  <td>${postCount[i-1]}</td>
										  <td>
					                        <button type="button" onclick="onclickView(${board.boardNo});" class="btn btn-success btn-xs">바로가기</button>
					                        <button type="button" onclick="copyURL(${board.boardNo});" class="btn btn-warning btn-xs" value="${board.boardNo}">
					                        	<input type="hidden" name="hiddenBoardNo2" value="${board.boardNo}" />링크복사</button>
					                        <button type="button" class="btn btn-primary btn-xs" name="updateBoard" data-toggle="modal" data-target="#updateBoardModal">
					                        	<input type="hidden" name="hiddenBoardNo" value="${board.boardNo}" />상세보기</button>							  
										  </td>
										</tr>
									</c:forEach>
			                    </tbody>
		                    </table>
	                    </form>
	                    <br>
	                    <button type="button" id="deleteChoiceBoard" class="btn btn-danger btn-sm"><i class="fa fa-minus-square"></i> 선택삭제</button>
	                    <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalRegister"><i class="fa fa-plus-square"></i> 게시판 생성</button>
	                </div><!-- /.box-body -->
	            </div><!-- /.box -->
	        </div><!-- /.col-xs-12 -->
	    </div><!-- /.row -->
	</section><!-- /.content -->
<!-- addBoard 모달 시작 -->
<jsp:include page="/WEB-INF/views/admin/board/addBoard.jsp"/>
<!-- updateBoard 모달 시작 -->
<jsp:include page="/WEB-INF/views/admin/board/updateBoard.jsp"/>
</div><!-- /.content-wrapper -->

<jsp:include page="/WEB-INF/views/admin/common/footer.jsp"></jsp:include>