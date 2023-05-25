<template>
  <section>
    <div class="input-group col-6 d-flex justify-content-center">
      <div class="w-50 justify-content-center text-center">
        <input type="text" id="word" class="form-control" onfocus="this.value = this.value;" autocomplete="off"
          v-model="state" @input="filterStates" @focus="modal = true" placeholder="원하는 시, 군, 구를 입력하세요."
          aria-label="원하는 시,구,동을 입력하세요." aria-describedby="button-addon2" />
      </div>
    </div>
    <div v-if="filteredStates && modal" class="w-100" style="absolute;">
      <div class="col-6 d-flex w-100 justify-content-center" style="width: 90%">
        <ul class="list-group justify-content-center w-50" style="
                                                      list-style-image: url('https://i.postimg.cc/Zqq5xL0k/pin.png');
                                                      border-radius: 0px;
                                                      position: fixed;
                                                      z-index: 3;
                                                    ">
          <li class="list-group-item" style="
                                                        border-width: 0.5px;
                                                        border-color: #f3cfcc;
                                                        font-weight: 700;
                                                        color: #828282;
                                                        width: 100%;
                                                        text-align: left;
                                                        font-size: 14px;
                                                      " v-for="filteredState in filteredStates"
            @click="setState(filteredState)">
            {{ filteredState }}
          </li>
        </ul>
      </div>
    </div>
    <div class="row col-md-12 justify-content-center mt-3 mb-2" style="margin: 0px; margin-bottom: 10px;">
      <div class="form-group col-md-2 justify-content-center">
        <select v-model="sidoValue" @change="changeGugun()" class="form-select" id="sido" name="sido">
          <option value="">시도 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select v-model="gugunValue" @change="changeDong()" class="form-select" id="gugun" name="gugun">
          <option value="">구군 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select v-model="dongValue" class="form-select" id="dong" name="dong">
          <option value="">동 선택</option>
        </select>
      </div>
      <!-- <div class="form-group col-md-2">
        <button
          @click="getAptList2()"
          type="submit"
          id="list-btn"
          class="btn"
          style="background-color: #d86057; color: white"
        >
          검색
        </button>
      </div> -->
    </div>

    <section>
      <!--
      <div style="text-align: center">
        <h1>
          {{ $route.params.sido }} {{ $route.params.gugun }} {{ $route.params.dong }} 거래내역 
        </h1>
      </div>
      -->
      <div class="row justify-content-center vh-100">
        <KakaoMap refs="kakao" v-bind:houses="datas" />

        <!--
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
        </div> -->
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
      state: "",
      states: [],
      filteredStates: [],
      sidoValue: "",
      gugunValue: "",
      dongValue: "",
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
        aptCode: "",
      },
    };
  },

  created() {
    // window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
    this.getAptList();
    this.getSiGunGu();
  },

  mounted() {
    // this.$refs.KakaoMap.loadMaker();

    this.getData();
  },

  methods: {
    getSiGunGu() {
      axios({
        method: "get",
        url: "http://localhost:80/address/sigungu",
        responseType: "json",
      }).then((response) => {
        var data = response.data;
        console.log(data);
        for (var i = 0; i < data.length; i++) {
          console.log(data[i]);
          var text = data[i].sidoName + " " + data[i].gugunName + " " + data[i].dongName;
          // console.log(text);
          this.states.push(text);
        }
      });
    },
    filterStates(e) {
      var count = 0;
      this.filteredStates = this.states.filter((state) => {
        if (state != "" && state != null && state != " ")
          return state.toLowerCase().includes(e.target.value.toLowerCase()) && count++ < 5;
      });
    },

    setState(state) {
      this.state = state;
      this.modal = false;

      var text = state.split(" ");
      this.sidoValue = text[0];
      this.gugunValue = text[1];
      this.dongValue = text[2];
      this.getAptList2();
    },
    showside(title) {
      console.log(title);
      this.title = title;
    },

    searchMap() {
      this.$refs.kakao.loadMap();
    },

    getData() {
      axios
        .get("http://localhost:80/address/sido") // 서블릿의 URL
        .then((response) => {
          var data = response.data;
          var options = '<option value="">시도 선택</option>';
          for (var i = 0; i < data.length; i++) {
            options += '<option value="' + data[i] + '">' + data[i] + "</option>";
          }
          sido.innerHTML = options; // city select 요소의 option 값을 업데이트
        })
        .catch((error) => {
          alert("An error occurred while processing your request: " + error);
        });
    },

    changeGugun() {
      axios({
        method: "get",
        params: { sido: this.sidoValue },
        url: "http://localhost:80/address/gugun",
        responseType: "json",
      }).then((response) => {
        var data = response.data;
        var options = '<option value="">구군 선택</option>';
        for (var i = 0; i < data.length; i++) {
          options += '<option value="' + data[i] + '">' + data[i] + "</option>";
        }
        gugun.innerHTML = options; // city select 요소의 option 값을 업데이트
      });
    },

    changeDong() {
      axios({
        method: "get",
        params: { gugun: this.gugunValue },
        url: "http://localhost:80/address/dong",
        responseType: "json",
      }).then((response) => {
        var data = response.data;
        var options = '<option value="">동 선택</option>';
        for (var i = 0; i < data.length; i++) {
          options += '<option value="' + data[i] + '">' + data[i] + "</option>";
        }
        dong.innerHTML = options;
      });
    },
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

    getAptList2() {
      axios({
        method: "post",
        url: "http://localhost:80/houses/search",
        responseType: "json",
        data: {
          sidoName: this.sidoValue,
          gugunName: this.gugunValue,
          dongName: this.dongValue,
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

<style scoped>
.form-control,
.form-select {
  border-width: 3px;
}

.form-control:focus,
.form-select:valid:focus {
  box-shadow: none;
  border: 3px solid #d86057;
}
</style>
