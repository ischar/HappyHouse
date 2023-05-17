<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body style="background-size: cover;"
	background="../background/background.jpg">
	<c:import url="../include/nav.jsp"></c:import>
	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
		<section class="py-5 text-center container">
			<div class="container-fluid vh-100" style="margin-top: 100px">
				<div class="" style="margin-top: 100px">
					<div class="rounded d-flex justify-content-center">
						<div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
							<div class="text-center">
								<h3 class="text-dark">Sign Up</h3>
							</div>
							<div class="p-4">
									<input type="hidden">
									<!-- id -->
									<div class="input-group mb-3">
										<span class="input-group-text"
											style="background-color: #929292;"><img
                                    src="/img/user.png" style="width: 20px; height: 20px;"><i
											class="bi bi-person-plus-fill text-white"></i></span> <input
											id="id" type="text" class="form-control"
											placeholder="아이디" name="id">
									</div>

									<!-- password-->
									<div class="input-group mb-3">
										<span class="input-group-text"
											style="background-color: #929292;"><img
                                    src="/img/lock.png" style="width: 20px; height: 20px;"><i
											class="bi bi-key-fill text-white"></i></span> <input id="pwd"
											type="password" class="form-control" placeholder="비밀번호"
											name="pwd">
									</div>
									<!-- 이름 -->
									<div class="input-group mb-3">
										<span class="input-group-text"
											style="background-color: #929292;"><img
                                    src="/img/name.png" style="width: 20px; height: 20px;"><i
											class="bi bi-person-plus-fill text-white"></i></span> <input
											id="name" type="text" class="form-control"
											placeholder="이름" name="name">
									</div>

									<!-- 주소 -->
									<div class="input-group mb-3">
										<span class="input-group-text"
											style="background-color: #929292;"> <img
                                    src="/img/address.png" style="width: 20px; height: 20px;"><i
											class="bi bi-person-plus-fill text-white"></i></span> <input
											id="address" type="text" class="form-control"
											placeholder="주소" name="address">
									</div>

									<div class="input-group mb-3">
										<span class="input-group-text"
											style="background-color: #929292;"><img
                                    src="/img/phone.png" style="width: 20px; height: 20px;"><i
											class="bi bi-person-plus-fill text-white"></i></span> <input
											id="phone" type="tel" class="form-control"
											title="전화번호를 입력하세요." placeholder="전화번호"
											pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13"
											name="phone">
										<!-- <input id="phoneNum" type="text" class="form-control" placeholder="전화번호"> -->
									</div>

									<button type="submit" id = "submitBtn" class="btn btn-primary text-center mt-2"  >Sign
										Up</button>
 							</div>
						</div>
					</div>
				</div>

			</div>
		</section>
	</main>
	<script src="/js/signup.js"></script>
</body>
</html>
