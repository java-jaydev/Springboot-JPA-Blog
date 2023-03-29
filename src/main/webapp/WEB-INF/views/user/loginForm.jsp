<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" name="username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" name="password" id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember" id="remember"> Remember me
			</label>
		</div>
		<button class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=fc5b93a26b93a962108d58a33ede0b4d&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38" src="/image/kakao_login_medium.png" /></a>
	</form>

</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>