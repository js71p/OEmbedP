/**
 * 
 */
 function oEmbed(){
	 if (!checkValidity()){}
	 else{
	 	var data = {};
		$.ajax({
		    type : 'post', // 타입 (get, post, put 등등)
		    url : '/oEmbed', // 요청할 서버url
		    dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
		    data :  "url="+$("#url").val(),
	    	success : function(result) { // 결과 성공 콜백함수
		     	var html = "";
		     	$("#content").empty();
		     	for (var key in result) {
		    		html += "<li>"
				    if(key == "html"){
				     	html += "<div>"+key+"<br>("+result.width+"/"+result.height+")</div>"
				    }else if( key =="thumbnail_url"){
				    	html += "<div>"+key+"<br>("+result.thumbnail_width+"/"+result.thumbnail_height+")</div>"
				    }else{
				    	html += "<div>"+key+"</div>"
				    }
				    if(key =="provider_url" || key=="author_url" || key=="thumbnail_url_with_play_button"){
				     	html += "<div><a href='"+result[key]+"'>"+result[key]+"</a></div>"
				    }else if(key == "thumbnail_url"){
				    	html += "<div><a href="+result[key]+"></a><img src="+result[key]+"></img></div>"
				    }else if(key == "html"){
				    	html += "<div>"+result[key]+"</div>"
				    }else{
				    	html += "<div>"+result[key]+"</div>"
				    }
						html += "</li>"   
					}
		    	$("#content").append(html);
		    },
		    error : function(request, status, error) { // 결과 에러 콜백함수
		        alert(status + error);
		    }
		});
	}
}

function checkValidity(){
	
	var url = $("#url").val();
	
	if(url == ""){
	alert("url을 입력해주세요.");
	return false;
	
	}else if (url.indexOf("http") < 0) {

	alert("잘못된 url 입니다.");
	return false;
	
	}
		
	return true;
}
