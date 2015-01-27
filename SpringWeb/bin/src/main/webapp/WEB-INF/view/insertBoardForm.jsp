<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>자유게시판(글쓰기)</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="./com/css/bootstrap.min.css">
	<script src="./com/js/jquery-1.9.1.min.js"></script>
	<script src="./com/js/bootstrap.min.js"></script>
	<script src="./com/js/jquery.validate.js"></script>
	<script src="./com/js/board.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
 		$('#writeForm').validate({
 			rules : {
 				title: { required: true, maxlength: 50 },
				content: { required: true, maxlength: 500 }
 			},
 			messages: {
	 			title: {
					required: "제목을 입력하시오.",
					maxlength: jQuery.validator.format("{0}바이트 이상은 입력할 수 없습니다.")
				},
				content: {
					required: "내용을 입력하시오.",
					maxlength: jQuery.validator.format("{0}바이트 이상은 입력할 수 없습니다.")
				}
			},
/* 			submitHandler: function(writeForm) {
 				$.ajax({
					type: "POST",
					url: "insert.html",
					cache: false,
					data: $('#writeForm').serialize(),
					dataType: "json",
					success: onSuccess,	//통신성공시 처리할 함수
					beforeSend: function() {	//통신 시직전 작업
						$('#btn_write').attr('disabled', true);
					},
					complete: function() {		//통신 성공,실패와 관련없이 완료후 처리
						$('#btn_write').attr('disabled', false);
					},
					error: onError		//통신에러시 처리할 함수
				});
			}, */
 			onkeyup: false,
			onclick: false,
			onfocusout: false,
			//메시지창 표시.
			showErrors: function(errorMap, errorList) {
				if("" != errorList) {
					if (errorList && errorList) {
						alert(errorList[0].message);
						if ($(errorList[0].element).attr('id')=="title") {
							if (getValueByte($('#title').val()) > 50) {
								$('#title').val(getValueByteLimit($('#title').val(), 50));
							}
						}
						if ($(errorList[0].element).attr('id')=="content") {
							if (getValueByte($('#content').val()) > 500) {
								$('#content').val(getValueByteLimit($('#title').val(), 500));
							}
						}
						$(errorList[0].element).focus();
					}
				}
			}
		});
		$("#btn_write").on('click', function(event) {
			//$('#writeForm').valid();
			$('#writeForm').submit();
		});
	});
	//통신성공시
	function onSuccess(data) {
		/*
		data: object
		data.length: 뱌열의 경우 길이 표시 아닐경우 undefined
		data[0].id
		data[0].name
		**/
		for (var i=0; i<data.length; i++) {
			alert(data[i].id);
		}
	}
	//통신에러시
	function onError(request, status, error) {
		alert("code: " + request.status + "\r\nmessage: " + request.reponseText);
	}
	</script>
</head>
<body>
<div class="container">
	<h2>자유게시판</h2><h4>글쓰기</h4>
	<form id="writeForm" name="writeForm" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
	<div class="form-group">
		<label class="control-label col-sm-2" for="title">제목</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="title" name="title" placeholder="Enter title" />
		</div>
    </div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="content">내용</label>
		<div class="col-sm-10">
			<textarea class="form-control" id="content" name="content" placeholder="Enter content" name="content" rows="10" cols="70"></textarea>
		</div>
    </div>
    <div class="form-group">
		<label class="control-label col-sm-2" for="file">파일</label>
		<div class="col-sm-10">
			<input class="form-control" type="file" id="file" name="file" />
			<p class="help-block">크기가 10Mbye 이하의 파일 선택</p>
		</div>
    </div>
	<div class="form-group">        
		<div class="col-sm-offset-2 col-sm-10">
			<button type="button" class="btn btn-default" id="btn_write">등록</button>
			<button type="button" class="btn btn-default" onClick="javascript:reset();">다시쓰기</button>
			<!-- <button type="button" class="btn btn-default" onclick="history.go(-1)">취소</button> -->
		</div>
	</div>
	<input type="hidden" name="userid" value="BBM">
	<input type="hidden" name="count" value="0" />
	<input type="hidden" name="filename" value="" />
	</form>
	
	<button type="button" class="btn btn-primary" onclick="location.href='boardList.html'">목록</button>
</div>
</body>
</html>