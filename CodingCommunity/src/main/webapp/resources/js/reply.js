/**
 * 
 */

$(document).ready(function(){

	
	// 함수 호출
	var bnoVal=$("input[name='bno']").val();
	 //alert(bnoVal) // bno값 가져오는지 테스트
	
	var pageValue=1;
	// detail.jsp가 시작되자마자 댓글목록리스트(list) 함수를 호출
	
	console.log(bnoVal);
	console.log(pageValue);
	
	list({bno:bnoVal,page:pageValue}); 
	

	
	// 1. 댓글 쓰기 버튼을 클릭하면 
	$("#add1").on("click",function(){
		var replyVal=$("#reply").val();
		var idVal=$("input[name='loginId']").val();
		//alert(idVal); // 댓글값 가져오는지 테스트
		
		add({bno:bnoVal,reply:replyVal,id:idVal});

	})
	
	
	// 수정 버튼 클릭
	$("#chat").on("click",".modify",function(){
		var rno=$(this).data("rno");
		var reply=$("#replycontent"+rno).val();
		var id=$("input[name='loginId']").val();
		var pageValue = $("input[name='page']").val();
		//alert(pageValue)
		
		list({bno:bnoVal,page:pageValue,rno:rno,reply:reply,id:id});
		//modifylist({bno:bnoVal,rno:rno,reply:reply,id:id,page:pageValue});
	})
	
	
	
	// 3. 댓글 수정완료을 클릭하면
	$("#chat").on("click",".update",function(){
		// alert("aaa") 클릭했을때 뜨는지 테스트
		
		var rno=$(this).data("rno");
		var reply=$("#replycontent"+rno).val();

		modify({rno:rno,reply:reply});
	})
	
	// 4. 댓글 삭제버튼 클릭하면
	$("#chat").on("click",".remove",function(){		

		var rno=$(this).data("rno");
		var pageValue = $(this).attr("href");
		
		remove({rno:rno,bno:bnoVal,page:pageValue});
	})
	
	// 5. 페이징
	$("#replyPage").on("click","li a",function(e){
		e.preventDefault();
		var bnoValue=$("input[name='bno']").val();
		var pageValue = $(this).attr("href");
		console.log(bnoValue)
		console.log(pageValue)
		list({bno:bnoValue,page:pageValue});
	})
	
})


// 함수 선언
// 1. add 함수 선언 시작

function add(reply){   
	console.log(reply);

	if(reply.id==null || reply.id==""){
		alert("회원만 이용 가능합니다.");
		return false;
	}
	else if(reply.reply==null || reply.reply==""){
		alert("내용을 입력하세요.");
		return false;
	}
	else{
	$.ajax({  
		type:"post", 
		url:"/replies/new",
		data:JSON.stringify(reply),
		contentType:"application/json; charset=utf-8",
		success:function(result){

			if(result=="success"){
				alert("댓글이 등록되었습니다.");
				location.reload();
			}	
		}
	})
	}return true;
}   
// add 함수 선언 끝



function list(param){
	 //alert(param.bno);  // bno값 호출되는지 테스트
	 //alert("ㅍㅔ이지"+param.page)
	 console.log(param);
	 
	var bno = param.bno;
	var page = param.page;
	var rno = param.rno;
	
	$.getJSON("/replies/"+bno+"/"+page+".json",function(data){

		console.log(data.replycnt)
		console.log(data.list)
		
		var str="";
		var id=$("input[name='loginId']").val();

		//console.log(rno);
		
		for(var i=0;i<data.list.length;i++){
			str+="<li class='top'>"
			str+="<input type='hidden' value="+page+" name='page'>"
			str+= "&nbsp;&nbsp;"+data.list[i].id
			if(rno==data.list[i].rno){
				str+="<input class='update' type='button' id='remo' value='수정완료' data-rno="+data.list[i].rno+">"
				str+="<input class='back' type='button' id='rere' value='취소' onclick='location.reload()'>"
				str+="</li>"                                                                       
				str+="<div><li class='ba'><textarea rows='5' cols='128' class='rc' id='replycontent"+data.list[i].rno+"'>"+data.list[i].reply+"</textarea>"
			}
			else{
				if(id==data.list[i].id){
					str+="<input class='modify' type='button' id='remo' value='수정' data-rno="+data.list[i].rno+">"
					str+="<input class='remove' type='button' id='rere' value='삭제' data-rno="+data.list[i].rno+">"
				}
			str+="</li>"                                                                       
			str+="<div><li class='ba'><textarea readonly rows='5' cols='128' class='rct' id='replycontent"+data.list[i].rno+"'>"+data.list[i].reply+"</textarea>"
			}
			str+= "&nbsp;&nbsp;"+data.list[i].replaydate+"</li></div>"
		}
		
		$("#replyUL").html(str);
		
		showReplyPage(data.replycnt,page);
	})
}
// list 함수 선언 끝


function showReplyPage(replycnt,pageNum){
	
	//var pageNum = Math.ceil(replycnt/10.0);
	var endNum = Math.ceil(pageNum/10.0)*10;
	var startNum = endNum-9;
	//var startNum = endNum-9 > 0;
	
	var prev= startNum !=1;
	//var prev= startNum !=1 & startNum>0;
	var next=false;
	
	if(endNum * 10 >= replycnt){
		endNum = Math.ceil(replycnt/10.0)
	}
	if(endNum * 10 < replycnt){
		next=true;
	}
	var str="<ul>";
	
	if(prev){
		str+="<li><a href='"+(startNum-1)+"'><<</a></li>";
	}
	
	for(var i=startNum ; i<=endNum ; i++){
		str+="<li><a href='"+i+"'>"+i+"</a></li>"
	}
	
	if(next){
		str+="<li><a href='"+(endNum+1)+"'>>></a></li>";
	}
	str+="</ul><div>"
	console.log(str);
	$("#replyPage").html(str);
		
}


// 3. modify 함수 선언 시작
function modify(reply){
	console.log(reply);
	$.ajax({  // ajax 준비 (비동기식으로 처리)
		type:"put",  // method 방식(get, post, put, delete 등)
		url:"/replies/modify",
		data:JSON.stringify(reply),
		contentType:"application/json; charset=utf-8",
		success:function(result){
			if(result=="success"){
				alert("댓글이 수정되었습니다.");
				location.reload();
			}
		}
	})
}
// modify 함수 선언 끝

// 4. remove 함수 선언 시작
function remove(reply){
	console.log(reply);
	$.ajax({  // ajax 준비 (비동기식으로 처리)
		type:"delete",  // method 방식(get, post, put, delete 등)
		url:"/replies/remove/",
		data:JSON.stringify(reply),
		contentType:"application/json; charset=utf-8",
		success:function(result){
			if(result=="success"){
				alert("댓글이 삭제되었습니다.");
				location.reload();
				list(reply.bno,reply.page);
			}
		}
	})
}
// remove 함수 선언 끝