<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:import url="../include/head.jsp"/> --%>
<body style = "background-size: cover" background = "background/background.jpg" >
<c:import url="../include/nav.jsp"/>
	<!-- 선택 정보 확  -->
	<section class="py-5 text-center container">
  <!-- <form action="${root}/house" method="GET"> -->
    <div class="py-lg-5">
      <div class="row col-md-12 justify-content-center mb-2">
        <div class="form-group col-md-2">
          <select class="form-select bg-secondary text-light" id="sido" name="sido">
            <option value="">시도 선택</option>
          </select>
        </div>
        <div class="form-group col-md-2">
          <select class="form-select bg-secondary text-light" id="gugun" name="gugun">
            <option value="">구군 선택</option>
          </select>
        </div>
        <div class="form-group col-md-2">
          <select class="form-select bg-secondary text-light" id="dong" name="dong">
            <option value="">동 선택</option>
          </select>
        </div>
        <div class="form-group col-md-2">
          <button type="submit" id="list-btn" class="btn btn-dark">
            아파트매매정보
          </button>
        </div>
      </div>
    </div>
  <!--  </form>-->
</section>
	<div style="text-align: center">
			<h1> 🐢🐢 ${param.sido} ${param.gugun} ${param.dong}의 거래내역입니당 😎 </h1>	
	</div>
	<!-- 지도 -->
	<section>
        <div class="row justify-content-center vh-100">
          <div id="map" class="col-md-6" style="width: 700px; height: 400px">
          
          </div>
          <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b239f7d8ede01160eac66cfc322badf"></script>
          <div
            id="scroll-box"
            class="col-md-6"
            style="width: 400px; height: 400px; overflow-y: scroll; background-color: white"; 
          >
           <ul id="apt-list">
              <!-- 여기는 데이터가 들어옴 -->
            </ul>
          </div>
        </div>
      </section>
	<!-- 지도 끝 -->
<c:import url="../include/footer.jsp"/>

	<script type="text/javascript" src="${root}/js/search.js"></script>
	<script type="text/javascript" src="${root}/js/get_City_Value.js"></script>
	<script type="text/javascript" src="${root}/js/findapt_btn.js"></script>
	<script type="text/javascript" src="${root}/js/getaptlist.js"></script>

</body>
</html>
