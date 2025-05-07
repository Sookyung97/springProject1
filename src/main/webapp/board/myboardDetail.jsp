<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 상세화면</title>
	<link rel="stylesheet" href="../css/style.css" />
	<script src="../js/jquery-3.7.1.js"></script>
</head>

<script>

	function fn_delete(seqid) {
		let url = "/passWrite2/" + seqid;
		//팝업창 함수(주소, 별칭, 옵션)
		window.open(url,"popup","width=300, hieght=200");
	}

	function fn_modify(num,writer,star,seqid) {

		let cmmt = $("#cmmt"+num).text();
		cmmt = $.trim(cmmt);
		
		$("#seqid").val(seqid);
		$("#cmmt").val(cmmt);
		$("#writer").val(writer);
		$("#star").val(star).prop("selected",true);
		$("#btn_submit").text("수정");
		$("#url").val("reboardUpdate");
	}

	$(function(){
	
		$("#btn_list").click(function(){
			location = "/myboardList";
		});
		$("#btn_modify").click(function(){
			location = "/myboardModify/${dto.seqid}";
		});
		$("#btn_delete").click(function(){
			location = "/passWrite/${dto.seqid}";
		});
		
		$("#btn_submit").click(function(){

			let cmmt = $("#cmmt").val();
			let pass = $("#pass").val();
			let writer = $("#writer").val();
		
			if( cmmt == "" ) {
				alert("(댓글)내용을 입력해주세요.");
				$("#cmmt").focus();
				return false;
			}
			if( pass == "" ) {
				alert("(댓글)암호를 입력해주세요.");
				$("#pass").focus();
				return false;
			}
			if( writer == "" ) {
				alert("(댓글)글쓴이를 입력해주세요.");
				$("#writer").focus();
				return false;
			}
			
			let url = $("#url").val();		// 등록 : boardInsert ;; 수정 : boardUpdate
			
			let form = $("#frm2").serialize();
			$.ajax({
				type: "post",
				data: form,
				url : "/"+url,
				dataType: "text",
				success:function(data){ 
					if(data=="ok") {
						alert("저장완료");
						location.reload();
					} else {
						alert("저장실패");
					}
				},
				error:function(){ 
					alert("오류발생");
				}
			});
		});
	});

</script>

<style>
 .table1 td {
 	text-align:left;
 }
 .div_content {
 	width:98%;
 	height:150px;
 	overflow-x:auto;
 	overflow-y:auto;
 }
 .div_comment {
 	width:570px;
 	margin-top:20px;
 	margin-left:20px;
 }
 .textarea2 {
 	width:99%;
 }
 
</style>

<body>
 <div class="div_title">
    댓글 게시판 상세화면
 </div>
 
<form id="frm" >

<table class="table1">
	<colgroup>
		<col width="20%" />
		<col width="*" />
	</colgroup>
	<tr>
		<th><label for="title">제목</label></th>
		<td>${dto.title }</td>
	</tr>
	<tr>
		<th><label for="writer">글쓴이</label></th>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<th><label for="content">내용</label></th>
		<td>
			<div class="div_content">${dto.content }</div>
		</td>
	</tr>
	<tr>
		<th><label for="rdate">등록일</label></th>
		<td>${fn:substring(dto.rdate,0,10) }</td>
	</tr>
	<tr>
		<th><label for="udate">변경일</label></th>
		<td>${fn:substring(dto.udate,0,10) }</td>
	</tr>
</table>

 <div class="div_button_area">
    <button type="button" id="btn_before" >이전</button>
    <button type="button" id="btn_next" >다음</button>
 	 &nbsp;
    <button type="button" id="btn_list" >목록</button>
    <button type="button" id="btn_modify">수정</button>
    <button type="button" id="btn_delete">삭제</button>
 </div>
 
</form>
	<div class="div_comment">
		<div>
			<div id="star_hap"></div>
		</div>
		
		<form id="frm2">
		
		<input type="hidden" name="pseqid" value="${dto.seqid}">
		<input type="hidden" id="seqid" name="seqid" value="0">
		<input type="hidden" id="url" value="reboardInsert">
		
		<div>
			<textarea id="cmmt" name="cmmt" class="textarea2"></textarea>
		</div>
		<div>		
			<input  type="password" id="pass"   name="pass"   class="input2"
					placeholder="암호를 입력하세요." >
			<input  type="text" 	id="writer" name="writer" class="input2"
					placeholder="글쓴이를 입력하세요.">
			
			<c:set var="j" value="6" />
			<select id="star" name="star">
				<c:forEach var="i" begin="1" end="5">
				<option value="${j-i }">${j-i }점</option>
				</c:forEach>
			</select>
			<button type="button" 	id="btn_submit">등록</button>
		</div>
		</form>
	</div>

	<div style="margin-top:10px;margin-left:20px;margin-bottom:100px;">
		<table class="table2" style="width:580px;">
			<colgroup>
				<col width="7%" />
				<col width="*" />
			</colgroup>
			
			<c:set var="hap" value="0" />
			<c:set var="cnt" value="0" />
			
			<c:forEach var="result" items="${list}">
			<tr>
				<td style="text-align:center;"></td>
				<td style="padding:5px;">
					<div id="cmmt${cnt}">
						${result.CMMT }
					</div>
					<div style="text-align:right;padding-top:3px;">

					<c:forEach var="i" begin="1" end="${result.STAR }">
						<img src="/images/icon/star1.PNG" width="15" height="15">
					</c:forEach>
					
					<c:forEach var="i" begin="1" end="${5-result.STAR }">
						<img src="/images/icon/star3.PNG" width="15" height="15">
					</c:forEach>
					
						  ${result.WRITER } 
						  ${result.RDATE }   
						 [
						 <a href="#" onClick="fn_modify('${cnt}','${result.WRITER}','${result.STAR}','${result.SEQID }')">수정</a>
						 /
						 <a href="#" onClick="fn_delete('${result.SEQID }')">삭제</a>
						 ] 
					</div>
				</td>
			</tr>
			
				<c:set var="hap" value="${hap+result.STAR }" />
				<c:set var="cnt" value="${cnt+1 }" />
			
			</c:forEach>
			
		</table>
	</div>
	
	<script>
	
	$(function(){
		let a1  = ${hap};
		let a2  = ${cnt};
		let avg = a1/a2;
		
		let img = "<img src='/images/icon/star1.PNG' width='25' height='25'>";
		let value = "";
		// 2.5 -> 2 ,, 3.2 -> 3 
		// 1 ~ 5
		for(var i=1; i<=Math.floor(avg); i++) {
			value += img;
		}
		let len = (avg+"").length;
		if(a2 > 0 && len > 1) {
			value += "<img src='/images/icon/star2.PNG' width='25' height='25'>";
		}
		// 2.6 -> 2 ,, 4.2 -> 0
		// 1.7 -> 3
		// 3.9 -> 1 ==> 5-Math.ceil(3.9)  ==> 5-4 -> 1
		img = "<img src='/images/icon/star3.PNG' width='25' height='25'>";
		for(var i=1; i<=(5-Math.ceil(avg)); i++) {
			value += img;
		}
		$("#star_hap").html(value);
		
		// ex) 2.5 => 금별2개 반쪽1개 은별2개
		// ex) 2.1 => 금별2개 은별3개
		// ex) .1 ~ .9 => 반쪽1개
		// ex) 2.3 => 금별2개 반쪽1개 은별2개
	});
	
	</script>


</body>
</html>


