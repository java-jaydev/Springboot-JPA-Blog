<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username" readonly="readonly" value="${principal.user.username}">
		</div>

		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>

		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}"  ${empty principal.user.oauth ? "" : "readonly"}>
		</div>

	</form>
	<button id="btn-update" class="btn btn-primary">수정</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>