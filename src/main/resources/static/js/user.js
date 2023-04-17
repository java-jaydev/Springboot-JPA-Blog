let index = {
	init: function() {
		// this를 바인딩하기 위해서 arrow function을 사용함
		$("#btn-save").on("click", () => {
			this.save();
		});
		
		$("#btn-update").on("click", () => {
			this.update();
		});
	},
	
	save: function() {
		// alert("user의 save함수 호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		// console.log(data);
		
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 post(insert) 요청
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json",  // 응답이 왔을때 javascript object 로 변경해줌
		}).done(function(resp) {
			if (resp.status === 500) {
				alert("회원가입에 실패하였습니다.");
			} else {
				alert("회원가입이 완료되었습니다.");
				location.href="/";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		// alert("user의 save함수 호출됨");
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		// console.log(data);
		
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 post(insert) 요청
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지 (MIME)
			dataType: "json",  // 응답이 왔을때 javascript object 로 변경해줌
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
}

index.init();