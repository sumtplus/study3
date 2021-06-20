<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<jsp:include page="../includes/header_.jsp" />
         <!-- Home -->
         <%-- <img src="${pageContext.request.contextPath}/resources/images/news_background.jpg"> --%>
         <div class="home">
            <div class="home_background_container prlx_parent">
               <div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
            </div>
            <div class="home_content">
               <h1>회원가입</h1>
            </div>
          </div>
           <form id="join_form" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/member/join">
              <div class="mt-3 mb-3" style="font-size:2vw; text-align: center">회원가입</div>
					<div>아이디</div>
					<div>
	                  	<div class="input-group">
						  	<input name="userId" id="userId" type="text" class="form-control my-2" placeholder="ex) example123">
						  	<div class="input-group-append">
						  		<button id="idck" class="btn btn-sm btn-success my-2" type="button">중복 확인</button>
						  	</div>
						</div>
						<span id="id-no" class="my-2" style="display: none; color: #d92742; font-weight: bold; ">4 ~10 자리의 영문, 숫자를 혼합하여 입력해주세요.</span>
					</div>
					<div>
						<div class="mt-1">비밀번호</div>
						<div><input id="userPw" name="userPw" id="userPw" class="form-control my-2" size="30" type="password" placeholder="ex) a11111111">
						<span id="pass" class="my-2" style="display: none; color: #d92742; font-weight: bold; ">6 ~ 15 자리의 영문, 숫자를 혼합하여 입력해주세요.</span>
						<span id="success" class="my-2" style="display: none;">사용가능한 비밀번호 입니다.</span></div>
						</div>
					<div>
						<div class="mt-1">비밀번호 확인</div>
						<div><input type="password" id="userPw2" class="form-control my-2">
							 <span id="alert-success" class="my-2" style="display: none;">비밀번호가 일치합니다.</span>
							 <span id="alert-danger" class="my-2" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
						</div>    
					</div>
		            <div>
		                <div class="mt-1">이름</div>
		                <div><input name="userName" id="name" class="form-control my-2" type="text" maxlength="10" placeholder="ex) 홍길동">
		                <span id="name-no" class="my-2" style="display: none; color: #d92742; font-weight: bold;">정확한 이름을 입력해주세요.</span></div>        
		            </div>
		            <div>
		                <div class="mt-1">이메일</div>
		                <div><input name="email" id="email" class="ch form-control my-2" type="email"  placeholder="ex) att@aaa.com"> 
		                <span id="email-no" class="my-2" style="display: none; color: #d92742; font-weight: bold;">잘못된 이메일 주소입니다.</span></div>       
		            </div>
		            <div>
		                <div class="mt-1">전화번호 ( - 제외하고 입력해주세요)</div>
		                <div><input name="tel" id="tel" class="form-control my-2" type="text" placeholder="ex) 01012341234">
		                <span id="tel-no" class="my-2" style="display: none; color: #d92742; font-weight: bold;">정확한 전화번호를 입력해주세요.</span></div>       
		            </div>
		            <div>
		                <div class="mt-1">주소</div>
					<div>
						<input type="button" onclick="postfind()" class="btn btn-secondary btn-sm my-2" value="우편번호 찾기" ><br>
						<input type="text" class="addr form-control my-2" id="post" placeholder="우편번호" readonly>
						<input type="text" class="addr form-control my-2"  id="addr" placeholder="주소" readonly >
						<input type="text" class="addr form-control my-2" id="detail" placeholder="상세주소" >
						<input type="text" class="addr form-control my-2" id="extra" placeholder="참고항목" readonly >
					</div>      
				</div>
				<div>
		            <div colspan="2">
		               <button class="btn btn-primary btn-block my-3" type="submit" id="signUp">가입하기 </button>
		            </div>
		        </div>
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		</form>
		<div style="text-align: center;">
		   <p class="mediuen">ZAKSAL의 회원이십니까? <a href="${pageContext.request.contextPath}/member/login">로그인 하기</a></p>
		</div>
<jsp:include page="../includes/footer.jsp" />



<script>
    //아이디 중복체크 - 버튼 팝업     
	   var idck = 0;
	   $("#idck").click(function() {
	        //userid 를 param.
	        var id =  $("#userId").val(); //
		    var num = id.search(/[0-9]/g);
		    var eng = id.search(/[a-z]/ig);
			var csrf = '${_csrf.headerName}';
			var csrfToken = "${_csrf.token}";
			$(document).ajaxSend(function(e, xhr) {
				xhr.setRequestHeader(csrf, csrfToken);
			})
	        $.ajax({
	            async: true,
	            type : 'POST',
	            data : JSON.stringify({userId:id}), //객체로 파라미터
	            url : "idcheck",
	            dataType : "json",
	            contentType: "application/json; charset=UTF-8", 
	            
	            success : function(data) {
	            	console.log(data);
	                if(id.length < 4 || id.length > 10){
	        		    $("#id-no").css('display', 'inline-block');
	    		        return false;
	    		    }
	    		    else if(id.search(/\s/) != -1){
	        		    $("#id-no").css('display', 'inline-block');
	    		        return false;
	    		    }else if(num < 0 || eng < 0){
	        		    $("#id-no").css('display', 'inline-block');
	    		        return false;
	    		    } else if (data.cnt > 0) { //아이디가 존재
	                	 alert("중복 아이디가 존재합니다.");
	                } else { 
	                	alert("사용가능한 아이디입니다.");
	                    //아이디가 중복하지 않으면  idck = 1 
	                    idck = 1;
	                }
	            },
	            error : function(error) {  
	                alert("error : " + error);
	            }
	        });
	    });
	   
	   
	   // 아이디 유효성 포커스
	   var fnId = function () {
		    var id = $("#userId").val();
		    var num = id.search(/[0-9]/g);
		    var eng = id.search(/[a-z]/ig);
		   
		    if(id.length < 4 || id.length > 10){
    		    $("#id-no").css('display', 'inline-block');
		        return false;
		    }
		    else if(id.search(/\s/) != -1){
    		    $("#id-no").css('display', 'inline-block');
		        return false;
		    }
		    else if(num < 0 || eng < 0){
    		    $("#id-no").css('display', 'inline-block');
		        return false;
		    }
		    $("#id-no").css('display', 'none');
		    return true;
		}   

      
		//비밀번호 유효성검사
		var fnPw = function () {
		    var pw = $("#userPw").val();
		    var num = pw.search(/[0-9]/g);
		    var eng = pw.search(/[a-z]/ig);
		   
		    if(pw.length < 6 || pw.length > 15){
		        $("#pass").css('display', 'inline-block');
		        $("#success").css('display', 'none');
		        return false;
		    }
		    else if(pw.search(/\s/) != -1){
		        $("#pass").css('display', 'inline-block');
		        $("#success").css('display', 'none');
		        return false;
		    }
		    else if(num < 0 || eng < 0){
		        $("#pass").css('display', 'inline-block');
		        $("#success").css('display', 'none');
		        return false;
		    }
		    $("#pass").css('display', 'none');
		    $("#success").css('display', 'inline-block');
		    return true;
		}
		

      //비밀번호 확인
      var fnPw2 = function (){ 
          var pw = $("#userPw").val();
          var pw2 = $("#userPw2").val();
          
          if (pw != "" || pw2 != "") {
              if (pw == pw2) {
            	  $("#alert-success").css('display', 'inline-block');
                  $("#alert-danger").css('display', 'none');
                  return true;
              } else {
            	  $("#alert-success").css('display', 'none');
                  $("#alert-danger").css('display', 'inline-block'); 
                  return false;
              }
          }
       };

      
   	 	// 이름 유효성 포커스
	   var fnName = function() {
		    var name = $("#name").val();
		    var namech = RegExp(/^[가-힣]{2,5}$/);
		   
		    if(namech.test($('#name').val())){
		    	$("#name-no").css('display', 'none'); 	
		        return true;
		    } 
		    $("#name-no").css('display', 'inline-block');
		    return false;
		}
   		
	   //이메일 유효성
	   var fnEmail = function() {
		    var email = $("#email").val();
	        var emailch = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		   
		    if(emailch.test($('#email').val())){
		    	$("#email-no").css('display', 'none'); 	
		        return true;
		    } 
		    $("#email-no").css('display', 'inline-block');
		    return false;
		}
      
      //전화번호 유효성
      var fnTel = function () {
    	  var tel = $("#tel").val();
    	  var num = tel.search(/[0-9]/g);
    	  
		    if(tel.length < 10 || tel.length > 12){
		    	$("#tel-no").css('display', 'inline-block');
		        return false;
		    }else if(num < 0){
		    	$("#tel-no").css('display', 'inline-block');
		    	return false;
		    }
		    $("#tel-no").css('display', 'none');
		    return true;
      	}
      
    

    	//아이디 유효성
		$('#userId').focusout(fnId);
		//비밀번호 유효성
		$('#userPw').focusout(fnPw);
		//비밀번호 확인
		$('#userPw2').focusout(fnPw2);
		//이름 
		$('#name').focusout(fnName);
		//전화번호 유효성
		$('#tel').focusout(fnTel);
		//이메일 유효성
		$('#email').focusout(fnEmail);
		
		
		$("#signUp").click(function() {
			event.preventDefault();
			if(fnPw() && fnPw2() && fnId() && fnName() && fnTel() && fnEmail()) {
		        if(idck==0){
		            alert('아이디 중복체크를 해주세요.');
		            return false;
		        } 
		        if($(".addr").val() == ""){
		        	alert("주소를 입력해주세요."); 
		        	return false; 
					}
					 $("<input>").attr({type:"hidden", name:"address.addr", value:$("#addr").val()}).appendTo("form");
				         $("<input>").attr({type:"hidden", name:"address.extra", value:$("#extra").val()}).appendTo("form");
				         $("<input>").attr({type:"hidden", name:"address.post", value:$("#post").val()}).appendTo("form");
				         $("<input>").attr({type:"hidden", name:"address.detail", value:$("#detail").val()}).appendTo("form");
				         alert("회원가입을 축하합니다.");
						 $("#join_form").submit();
				}
			})


	//주소 api
    function postfind() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extra").value = extraAddr;
                
                } else {
                    document.getElementById("extra").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('post').value = data.zonecode;
                document.getElementById("addr").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detail").focus();
                
                
            }
        }).open();
    }
    
   
</script>