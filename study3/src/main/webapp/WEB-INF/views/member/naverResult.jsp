<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header_.jsp" />
	<!-- Home -->

	<div class="home">
		<div class="home_background_container prlx_parent">
			<div class="home_background prlx" style="background-image:url(${pageContext.request.contextPath}/resources/images/news_background.jpg)"></div>
		</div>
		<div class="home_content">
			<h1>Login</h1>
		</div>
	</div>
	<div class="row" >
		<div class="col-4"></div>
		<div class="col-4 mt-3" style="text-align: center;" >
		<p>권한인증</p>
			<%-- <form action="${pageContext.request.contextPath}/login" method="post">
		        <div style="font-size:2vw;">로그인</div>
		        <table class="table table-boardered">
		            <tr>
		                <th>아이디</th>
		                <td><input class="form-control" name="username" type="text" placeholder="id" value="aaaa"></td>     
		            </tr>
		            <tr>
		                <th>패스워드</th>
		                <td><input class="form-control" name="password" type="password" placeholder="password" value="1111"></td>      
		            </tr>
		            <tr>
		                <td colspan="2">
		                <button class="btn" type="submit">Login</button>
		                <div class="g-signin2" data-onsuccess="onSignIn"></div>
		                <a href="" onclick="signOut();">구글 로그아웃</a>
		                </td>
		            </tr>
		        </table>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		    </form> --%>
		    <%-- <p class=" medeiun">Don't have an account? <a href="${pageContext.request.contextPath}/member/join">Sing up here!</a></p> --%>
	    </div>
		<div class="col-4"></div>
	</div>
<jsp:include page="../includes/footer.jsp" />
