/**
 * 
 */
$(document).ready(function(){
	$("#modify").on("click",function(e){	
	e.preventDefault();
	alert("수정하시겠습니까?");
	})
	
	$("#modifybtn").on("click",function(e){	
	alert("수정 완료 되었습니다.");
	})
	
	$("#removebtn").on("click",function(e){	
	alert("삭제하시겠습니까?");
	})
})