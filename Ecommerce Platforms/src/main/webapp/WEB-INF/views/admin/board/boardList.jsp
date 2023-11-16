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
		                    <form name="form_list" method="post" action="${pageContext.request.contextPath }/admin/board/boardProcess?${_csrf.parameterName}=${_csrf.token}">
					            <input type="hidden" name="mode" id="mode">
					            <input type="hidden" name="locale" value="${locale}"/>
					            <thead>
				                    <tr>
				                        <td style="width:30px;">
				                      		<div class="allCheck">
												<input type="checkbox" name="select_all" onclick="selectAllCheckBox( 'form_list');" /></label>
												<script>
													/* $("#allCheck").click(function() {
														var chk = $("#allCheck").prop("checked");
														if (chk) {
															$('.boardNo').prop("checked", true);
														} else {
															$('.boardNo').prop("checked", false);
														}
													}); */
												</script>
											</div>
				                        </td>
				                        <td style="width:60px;">NO</td>
				                        <td>제목</td>
				                        <td style="width:250px; text-align:center;">URL</td>
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
					                        	<input type="checkbox" class="boardNo" name="list[]"  value="${board.boardNo}" />
					                        	<!-- <script>
													$(".boardNo").click(function() {
														$("#allCheck").prop("checked", false);
													});
												</script> -->
											</div>
				                          </td>
										  <td>${i}</td>
										  <td align="left">${board.boardTitle}</td>
										  <td align="left">
										  	<a href="/user/notice/noticeList/${board.boardNo}" target="_blank">/user/notice/noticeList/${board.boardNo}</a>
										  </td>
										  <td>${postCount[i-1]}</td>
										  <td>
					                        <button type="button" onclick="onclickView(${board.boardNo});" class="btn btn-success btn-xs">바로가기</button>
					                        <button type="button" onclick="copyURL(${board.boardNo});" class="btn btn-warning btn-xs" value="${board.boardNo}">
					                        	<input type="hidden" name="hiddenBoardNo2" value="${board.boardNo}" />링크복사</button>
					                        <button type="button" class="btn btn-primary btn-xs" onclick="onclickUpdate(${board.boardNo})">
					                        	<input type="hidden" name="hiddenBoardNo" value="${board.boardNo}" />상세보기</button>							  
										  </td>
										</tr>
									</c:forEach>
			                    </tbody>
		                    </table>
	                    </form>
	                    <br>
	                    <button type="button" id="deleteChoiceBoard2" onclick="selectDelete()" class="btn btn-danger btn-sm"><i class="fa fa-minus-square"></i> 선택삭제</button>
	                    <button type="button" class="btn btn-primary btn-sm" onclick="onclickInsert()"><i class="fa fa-plus-square"></i> 게시판 생성</button>
	                </div><!-- /.box-body -->
	            </div><!-- /.box -->
	        </div><!-- /.col-xs-12 -->
	    </div><!-- /.row -->
	</section><!-- /.content -->
	
	<div class="modal fade" id="modalContent" tabindex="-2" role="dialog" aria-labelledby="myModal" aria-hidden="true">
    <div class="modal-dialog" style="width:600px;">
        <div class="modal-content">
            <form name="form_register" method="post" action="${pageContext.request.contextPath }/admin/board/boardProcess?${_csrf.parameterName}=${_csrf.token}">
	            <input type="hidden" name="mode" id="mode" value="insert">
	            <input type="hidden" name="locale" value="${locale}"/>
	            <input type="hidden" name="boardNo" id="boardNo" value="0">
	            <input type="hidden" name="board_code" id="board_code">
	            <input type="hidden" name="cPage" id="cPage">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">게시판 생성</h4>
	            </div>
	            <div class="modal-body">
		            <h4><p class="text-light-blue"><i class="fa fa-fw fa-info-circle"></i>게시판 정보</p></h4>
		            <table class="table table-bordered">
			            <tr>
			                <td class="menu">제목</td>
			                <td align="left" colspan="3"><input type="text" name="boardTitle" id="boardTitle" class="form-control input-sm" placeholder="제목을 입력해주세요"></td>
			            </tr>
			            <tr>
			                <td class="menu">부제목</td>
			                <td align="left" colspan="3"><input type="text" name="boardSubTitle" id="boardSubTitle" class="form-control input-sm" placeholder="부제목을 입력해주세요"></td>
			            </tr>
			            <tr>
			                <td class="menu">내용</td>
			                <td colspan="2" style="text-align:left">
			                	<textarea name="boardContents" id="boardContents" rows="10" cols="80" style="width:450px; writing-mode: horizontal-tb;"placeholder="내용을 입력해주세요"></textarea>
			                </td>
			            </tr>
		            </table>
	            </div><!-- /.modal-body -->
	            <div class="modal-footer">
	            	<button type="button" onclick="register()"class="btn btn-primary">확인</button>&nbsp;&nbsp;&nbsp;
	            </div><!-- /.modal-footer -->
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal fade -->
	
	<script type="text/javascript">
	function register() {
	    if(form_register.boardTitle.value == '') { alert('제목이 입력되지 않았습니다.'); form_register.content.focus(); return false;}
	    if(form_register.boardSubTitle.value == '') { alert('부제목이 입력되지 않았습니다.'); form_register.content.focus(); return false;}
	    if(form_register.boardContents.value == '') { alert('내용이 입력되지 않았습니다.'); form_register.content.focus(); return false;}

	    if(!confirm("게시판 정보를 등록하시겠습니까?")){
			alert("취소 되었습니다.");
			return;
		}else{
			form_register.target = 'iframe_process';
		    form_register.submit();
		    setTimeout("location.reload()", 500);
			$('#modalContent').modal('hide');
		}
	}
	function setData(code) {
	    $.ajax({
			url:'${pageContext.request.contextPath}/admin/board/getBoard',
			type:'post',
			dataType:'json',
			headers: {
	            "${_csrf.headerName}" : "${_csrf.token}"
	        },
			data:{
				boardNo : code
			},
			success(data){
	            $('form[name="form_register"] #mode').val('update');
	            $('[name=boardNo]').val(data.boardNo);
	            $('[name=boardTitle]').val(data.boardTitle);
	            $('[name=boardSubTitle]').val(data.boardSubTitle);
	            $('[name=boardContents]').val(data.boardContents);
				 }
			},
	);
	}
	function onclickInsert() {
	    $('#modalContent').modal('show');
	    form_register.reset();
	    $('form[name="form_register"] #mode').val('insert');
	    $('#display_file').css('display','none')
	}
	
	function onclickUpdate(code) {
	    $('#modalContent').modal({backdrop:'static', show:true});
	    setData(code);
	}
	function onclickView(boardNo){
		location.href ="/admin/board/postList?boardNo="+boardNo
	}
	
	window.copyURL = function(boardNo){
		prompt("이 게시판의 URL 입니다. Ctrl+C를 눌러 복사하세요", "http://localhost:8080/admin/board/postList?boardNo="+boardNo)
	}
	
	</script>
<!-- addBoard 모달 시작 -->
<!-- updateBoard 모달 시작 -->
</div><!-- /.content-wrapper -->

<jsp:include page="/WEB-INF/views/admin/common/footer.jsp"></jsp:include>