<template>
  <section>
    <div class="py-lg-5">
      <div class="row col-md-12 justify-content-center mb-2">
        <div class="form-group col-md-2">
          <select class="form-select bg-secondary text-light"   id="sido" name="sido">
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
          <button type="submit" id="list-btn" class="btn btn-dark">아파트매매정보</button>
        </div>
      </div>
    </div>

    <section>
      <div style="text-align: center">
        <h1>
          {{ $route.params.sido }} {{ $route.params.gugun }} {{ $route.params.dong }} 거래내역
        </h1>
      </div>
      <div class="row justify-content-center vh-100">
        <KakaoMap refs="kakao" v-bind:houses="datas" />
        <div
          id="scroll-box"
          class="col-md-6"
          style="width: 400px; height: 400px; overflow-y: scroll  
          background: rgba(255, 255, 255, 0.2);
          border-radius: 16px;
          box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
          backdrop-filter: blur(5px);
          -webkit-backdrop-filter: blur(5px);
          border: 1px solid rgba(255, 255, 255, 0.3);
        "
        >
          <ul id="apt-list" v-for="data in datas" :key="data.no">
            <div>
              <li>
                <h3>{{ data.apartmentName }}</h3>
              </li>
              <li>가격 : {{ data.dealAmount }}원</li>
              <li>면적 : {{ data.area }}</li>
              <li>건축년도<br />{{ data.dealYear }}.{{ data.dealMonth }}.{{ data.dealDay }}</li>
              <hr />
            </div>
          </ul>
        </div>
      </div>
    </section>
  </section>
</template>

<script>
import axios from "axios";
import KakaoMap from "../components/KakaoMap.vue";

export default {
  name: "SearchView",
  components: {
    KakaoMap,
  },
  props: {
    msg: String,
  },

  data() {
    return {
      title: "",
      map: null,
      positions: [],
      markers: [],
      datas: {
        lat: "",
        lng: "",
        apartmentName: "",
        dealAmount: "",
        area: "",
        dealYear: "",
        dealMonth: "",
        dealDay: "",
      },
    };
  },

  created() {
    // window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
    this.getAptList();
    this.$refs.KakaoMap.loadMaker();
  },

  mounted() {},

  methods: {
    showside(title) {
      console.log(title);
      this.title = title;
    },
    // addKakaoMapScript() {
    //   const script = document.createElement("script");
    //   /* global kakao */
    //   script.onload = () => kakao.maps.load(this.initMap);
    //   script.src =
    //     "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4b239f7d8ede01160eac66cfc322badf";
    //   document.head.appendChild(script);
    // },
    // initMap() {
    //   var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    //   var options = {
    //     //지도를 생성할 때 필요한 기본 옵션
    //     center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
    //     level: 3, //지도의 레벨(확대, 축소 정도)
    //   };

    //   map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    // },

    getAptList() {
      axios({
        method: "post",
        url: "http://localhost:80/houses/search",
        responseType: "json",

        data: {
          sidoName: this.$route.params.sido,
          gugunName: this.$route.params.gugun,
          dongName: this.$route.params.dong,
        },
      })
        .then((response) => {
          this.datas = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    // setCenter(lat, lng) {
    //   var moveLatLon = new kakao.maps.LatLng(lat, lng);
    //   map = this.map;
    //   // 지도 중심을 이동 시킵니다
    //   map.setCenter(moveLatLon);

    //   var imageSize = new kakao.maps.Size(24, 35);
    //   // 마커 이미지를 생성합니다
    //   var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    //   var markerPosition = new kakao.maps.LatLng(lat, lng);
    //   // 마커를 생성합니다
    //   var marker = new kakao.maps.Marker({
    //     map: map, // 마커를 표시할 지도
    //     position: markerPosition, // 마커를 표시할 위치
    //     //		title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    //     image: markerImage, // 마커 이미지
    //   });
    //   marker.setMap(map);
    // },
  },
};
</script>

<style scoped></style>
