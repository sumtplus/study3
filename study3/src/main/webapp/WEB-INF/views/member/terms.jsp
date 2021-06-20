<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<jsp:include page="../includes/header_.jsp" />
<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
		<div class="home_content">
			<h1>Terms</h1>
		</div>
	</div>
	<h1 class="mt-4 mb-4" style="text-align: center;">약관동의</h1>
		<form action="${pageContext.request.contextPath}/member/join" id="terms_form">
            <ul class="join_box pl-0">
                <li class="checkBox check02">
                    <ul class="clearfix pl-0">
                        <li>이용약관 동의(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk1" id="chk1"> 
                        </li>
                    </ul>
                    <textarea name="" id="">여러분을 환영합니다.
					독서실 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
					본 약관은 다양한 독서실 서비스의 이용과 관련하여 독서실 서비스를 제공하는 독서실 주식회사(이하 ‘독서실’)와 
					이를 이용하는 독서실 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 
					아울러 여러분의 독서실 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       				</textarea>
                </li>
                <li class="checkBox check03">
                    <ul class="clearfix pl-0">
                        <li>개인정보 수집 및 이용에 대한 안내(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk2" id="chk2">
                        </li>
                    </ul>
                	<textarea name="" id="">여러분을 환영합니다.
					독서실 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
					본 약관은 다양한 독서실 서비스의 이용과 관련하여 독서실 서비스를 제공하는 독서실 주식회사(이하 ‘독서실’)와 
					이를 이용하는 독서실 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 
					아울러 여러분의 독서실 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       				</textarea>
                </li>
                <li class="checkBox check03">
                    <ul class="clearfix pl-0">
                        <li>위치정보 이용약관 동의(선택)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk3" id="chk3">
                        </li>
                    </ul>
                    <textarea name="" id="">여러분을 환영합니다.
					독서실 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
					본 약관은 다양한 독서실 서비스의 이용과 관련하여 독서실 서비스를 제공하는 독서실 주식회사(이하 ‘독서실’)와 
					이를 이용하는 독서실 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 
					아울러 여러분의 독서실 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
      				</textarea>
                </li>
                <li class="checkBox check04">
                    <ul class="clearfix pl-0">
                        <li>이벤트 등 프로모션 알림 메일 수신(선택)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk4" id="chk4">
                        </li>
                    </ul>
                </li>
            </ul>
            <div class="footBtwrap clearfix pl-0 mb-5">
            	<label class="float-right"><input class="mr-2 chkAll" type="checkbox">전체 동의하기</label>
                <button id="nextBtn" class="fpmgBt2">다음</button>
            </div>
        </form>
<jsp:include page="../includes/footer.jsp" />

<script>
$(function() {
	//약관동의 
    $("#nextBtn").click(function(){   
    	event.preventDefault();
        if(!$("#chk1").is(":checked")){
            alert("필수 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
            return;
        }
        if (!$("#chk2").is(":checked")){
            alert("필수 약관에 동의 하셔야 다음 단계로 진행 가능합니다..");
            return;
        }
           $("#terms_form").submit();
    });    
	
  /* 전체 동의하기 체크박스 클릭 */
	$(".chkAll").click(function(){ 
		if($(".chkAll").prop("checked")) { 						//만약 전체 동의하기 체크박스가 체크된상태일경우 
			$("input[type=checkbox]").prop("checked",true); 	//해당화면의 모든 checkbox들을 체크해준다. 
		} else { 												// 전체 동의하기 체크박스가 해제된 경우 
			$("input[type=checkbox]").prop("checked",false); 	//해당화면의 모든 checkbox들의 체크를 해제시킨다.
		}  
	});
}); // ready 함수 끝

</script>