<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:requestEncoding value="UTF-8" />
<body style="background-size: cover"
	background="/img/background.jpg">
	<c:import url="../include/nav.jsp"></c:import>

		<section class="py-5 text-center container">
			<div class="container-fluid vh-100" style="margin-top: 100px">
				<div class="" style="margin-top: 200px">
					<div class="rounded d-flex justify-content-center">
						<div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light"
							style="width: 600px">
							<div class="text-center">
								<h3 class="text-dark">수정페이지</h3>
							</div>
							<div class="p-7">
								<!-- id -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"> <img
										src="/img/user.png" style="width: 20px; height: 20px;">
										<i class="bi bi-person-plus-fill text-white"></i>
									</span> <input type="text" name="id" id="id" value="${param.id}"
										class="form-control" readonly >
								</div>

								<!-- 비밀번호 -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"> <img
										src="/img/lock.png" style="width: 20px; height: 20px;">
										<i class="bi bi-person-plus-fill text-white"></i>
									</span> <input type="text" name="pwd" id="pwd" value="${param.pwd}"
										class="form-control">
								</div>

								<!-- 이름 -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"> <img
										src="/img/name.png" style="width: 20px; height: 20px;">
										<i class="bi bi-person-plus-fill text-white"></i>
									</span> <input type="text" name="name" id="name" value="${param.name}"
										class="form-control">
								</div>

								<!-- 주소 -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"> <img
										src="/img/address.png" style="width: 20px; height: 20px;">
										<i class="bi bi-person-plus-fill text-white"></i>
									</span> <input type="text" name="address" id="address" value="${param.address}"
										class="form-control">
								</div>

								<!-- 전화번호 -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"> <img
										src="/img/phone.png" style="width: 20px; height: 20px;">
										<i class="bi bi-person-plus-fill text-white"></i>
									</span> <input type="text" name="phone" id="phone" value="${param.phone}"
										class="form-control">
								</div>
									
								<input type="hidden" name="_method" value="put"/>
								<input type="submit" value="수정하기" id="submitBtn"
									class="btn btn-primary text-center mt-2"b>
								<input class="btn btn-danger text-center mt-2" type="reset" value="취소하기" onClick = "location.href='/user/userinfo'">
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<script src="/js/updateUser.js"></script>
</body>
</html>
