/**
 * 
 */

$(document).ready(function(){
	
	var mo_bno= "<c:out value='${detail.bno}'/>";
	
	$.getJSON("/getAjaxAction", {mo_bno : mo_bno}, function(result){
		console.log(result);
	})
	
	// 정규식을 이용하여 확장자 제한
	var reg = new RegExp("(.*?)\.(exe|zip|alz)$");
	var maxSize = 5242888;   // 5MB

	function checkExtension(fileName, fileSize){

		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;  
		}
		if(reg.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;  
		}
		return true;  
	}
	
	
	// 수정 버튼 클릭하면
	$("#modifybtn").on("click",function(e){	
		e.preventDefault();
		alert("111");

		var formData = new FormData();		
		var inputFile = $("input[name='uploadFile']");		
			console.log(inputFile);		
		var files = inputFile[0].files;		
			console.log(files);
		
		for(var i=0; i<files.length; i++){

			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}

			formData.append("uploadFile",files[i]);
			console.log(formData)
		}
		
		$.ajax({			
			type:"post",
			url:"/uploadAjaxAction",
			data:formData,mo_bno,
			contentType:false,
			processData:false,
			dataType:"json",
			success:function(result){
				console.log(result);	
				
				var str="";	
				var input="";

				$(result).each(function(i,obj){
				
					input+="<input type='text' name='attach["+i+"].uploadPath' value='"+obj.uploadPath+"'>";
					input+="<input type='text' name='attach["+i+"].fileName' value='"+obj.fileName+"'>";
					input+="<input type='text' name='attach["+i+"].uuid' value='"+obj.uuid+"'>";
					input+="<input type='text' name='attach["+i+"].img' value='"+obj.img+"'>";
									
					if(obj.img){
						var filePath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);					
						str+="<li><img src='/display?fileName="+filePath+"'></li>";
					}
					else{  
						var filePath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);						
						str+="<li>"
						str+="<p>다운로드</p><a href='/download?fileName="+filePath+"'>"+obj.fileName+"</a>";
						str+="</li>"
					}					
				})
				
				$("#uploadResult").html(str);
				alert(mo_bno);
				return false;
				
				$("#form").append(input).submit();
			}
		})		
	})	
})
