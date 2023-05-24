<template>
  <div style="position: relative; width: 100%; overflow: hidden">
    <div
      style="
        width: 400px;
        height: 100%;
        background-color: white;
        position: absolute;
        text-align: center;
        z-index: 2;
        border: 0.5px solid #d86057;
      ">
      <div id="showdetail"></div>
      <div id="roadview" style="width: 400px; height: 300px"></div>
      <div v-if="favoriteFlag" style="text-align: right">
        <img
          v-if="favoriteOnOff"
          @click="deleteFavorite()"
          src="../assets/favoriteon.png"
          style="margin: 8px; width: 30px; height: 30px" />
        <img
          v-else
          @click="addFavorite()"
          src="../assets/favoriteoff.png"
          style="margin: 8px; width: 30px; height: 30px" />
      </div>
      <div v-if="favoriteFlag" style="text-align: center; margin: 10px">
        <div style="float: left">
          <img src="../assets/hospital.png" style="width: 40px; height: 40px" />
          <p><b>병원</b></p>
        </div>
        <div style="float: left; margin-top: 2.5%; margin-left: 5px">
          <p><b>0</b>개</p>
        </div>
        <div style="float: left">
          <img src="../assets/store.png" style="width: 40px; height: 40px" />
          <p><b>마트</b></p>
        </div>
        <div style="float: left; margin-top: 2.5%; margin-left: 5px">
          <p><b>0</b>개</p>
        </div>
        <div style="float: left">
          <img src="../assets/school.png" style="width: 40px; height: 40px" />
          <p><b>학교</b></p>
        </div>
        <div style="float: left; margin-top: 2.5%; margin-left: 5px">
          <p><b>0</b>개</p>
        </div>
        <div style="float: left">
          <img
            src="../assets/train-station.png"
            style="width: 40px; height: 40px" />
          <p><b>역</b></p>
        </div>
        <div style="float: left; margin-top: 2.5%; margin-left: 5px">
          <p><b>0</b>개</p>
        </div>
      </div>

      <h3 v-if="favoriteFlag">정보</h3>
      <h3 v-if="favoriteFlag">거래내역</h3>
      <div id="content" style="height: 100%; overflow: auto"></div>
    </div>
    <div
      id="map"
      style="width: 100%; height: 100%; border: 0.5px solid #d86057"></div>
  </div>
</template>

<script>
import {left} from "@popperjs/core";
import axios from "axios";
import {mapState, mapActions} from "vuex";
import store from "@/store";
import router from "@/router/index";
// 마커이미지의 크기입니다
export default {
  props: {
    houses: [
      {
        lat: String,
        lng: String,
        apartmentName: String,
        dealAmount: String,
        area: String,
        dealYear: String,
        dealMonth: String,
        dealDay: String,
        aptCode: String,
      },
    ],
  },
  name: "KakaoMap",
  data() {
    return {
      url: "",
      aptCode: "",
      map: null,
      positions: [],
      markers: [],
      favoriteFlag: false,
      favoriteOnOff: false,
    };
  },

  setup() {},
  created() {},
  computed: {
    ...mapState(["loginId"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  unmounted() {},

  watch: {
    houses() {
      console.log("test");
      this.loadMaker(this.houses);
    },
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        // "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4b239f7d8ede01160eac66cfc322badf";
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6754b48bc8e82c049cd891e19dd5f56c&libraries=services,clusterer,drawing";
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    loadMap() {
      this.favoriteFlag = false;
      const container = document.getElementById("map");
      const options = {
        // center: new window.kakao.maps.LatLng(this.houses[0].lat, this.houses[0].lng),
        center: new window.kakao.maps.LatLng(12, 12),
        level: 3,
      };
      this.map = new window.kakao.maps.Map(container, options);

      this.loadMaker(this.houses);
    },

    addFavorite() {
      const checkUserInfo = store.getters["checkUser"];

      if (!checkUserInfo) {
        alert("로그인이 필요한 서비스입니다.");
        // next({ name: "login" });
        router.push({name: "userlogin"}).catch(() => {});
      } else {
        axios({
          method: "post",
          params: {
            userId: this.loginId,
            aptCode: this.aptCode,
          },
          url: "http://localhost:80/favorite/add",
          responseType: "json",
        }).then(response => {
          this.favoriteOnOff = true;
          console.log("add");
        });
      }
    },
    deleteFavorite() {
      axios({
        method: "get",
        params: {
          userId: this.loginId,
          aptCode: this.aptCode,
        },
        url: "http://localhost:80/favorite/delete",
        responseType: "json",
      }).then(response => {
        this.favoriteOnOff = false;
        console.log("delete");
      });
    },

    checkFavorite(userid, aptCode) {
      axios({
        method: "get",
        params: {
          userId: userid,
          aptCode: aptCode,
        },
        url: "http://localhost:80/favorite/check",
        responseType: "json",
      }).then(response => {
        if (response.data == 1) {
          this.favoriteOnOff = true;
        }
      });
    },

    loadMaker(houses) {
      var imageSrc = "https://i.postimg.cc/ZRcqsDZ9/location.png";
      this.favoriteOnOff = false;
      var imageSize = new kakao.maps.Size(36, 42); // 마커이미지의 크기입니다
      var imageOption = {offset: new kakao.maps.Point(14, 39)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );
      let positions = [];

      for (var i = 0; i < houses.length; i++) {
        positions.push({
          content: houses[i],
          latlng: new kakao.maps.LatLng(houses[i].lat, houses[i].lng),
        });
      }

      let bounds = new kakao.maps.LatLngBounds();
      for (var i = 0; i < houses.length; i++) {
        let marker = new kakao.maps.Marker({
          map: this.map,
          position: positions[i].latlng,
          title:
            positions[i].content.apartmentName +
            " " +
            positions[i].content.aptCode,
          image: markerImage, // 마커이미지 설정
        });
        bounds.extend(positions[i].latlng);
        // infowindow = new kakao.maps.InfoWindow({
        //   content: marker.getTitle(),
        // });
        kakao.maps.event.addListener(marker, "click", () => {
          var text = marker.getTitle();
          var text2 = marker.getTitle().split(" ");
          const element = document.getElementById("showdetail");
          element.innerText = text2[0];

          var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
          var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
          var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
          roadviewClient.getNearestPanoId(marker.getPosition(), 50, panoId => {
            if (panoId == null) {
              alert("로드뷰 정보가 없는 지역입니다.");
            } else {
              roadview.setPanoId(panoId, marker.getPosition());
              this.showContent(text2[0]);
              this.aptCode = text2[1];

              this.showFacilities(
                "HP8",
                "https://i.postimg.cc/T13Ng1FP/hospital.png"
              );
              this.showFacilities(
                "MT1",
                "https://i.postimg.cc/zv4wvf16/store.png"
              );
              this.showFacilities(
                "SC4",
                "https://i.postimg.cc/s25LRt53/school.png"
              );
              this.showFacilities(
                "SW8",
                "https://i.postimg.cc/nz00FZrf/train-station.png"
              );
              this.showFacilities(
                "CS2",
                "https://i.postimg.cc/YCWn1JnQ/convenience-store.png"
              );
            }
            // roadview.setPanoId(panoId, marker.getPosition()); //panoId와 중심좌표를 통해 로드뷰 실행
          });
        });
      }
      this.map.setBounds(bounds);
    },

    showFacilities(category, url) {
      var ps = new kakao.maps.services.Places(this.map);
      this.url = url;
      ps.categorySearch(category, this.placesSearchCB, {useMapBounds: true});
    },

    displayMarker(place) {
      var imageSrc = this.url;
      var imageSize = new kakao.maps.Size(32, 32), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage,
      });
    },

    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
        }
      }
    },

    showContent(title) {
      axios({
        method: "post",
        params: {
          title: title,
        },
        url: "http://localhost:80/houses/search/house",
        responseType: "json",
      })
        .then(response => {
          if (this.loginId != null) {
            this.checkFavorite(this.loginId, this.aptCode);
          }
          var data = response.data;
          var options = "";
          this.favoriteFlag = true;
          for (var i = 0; i < data.length; i++) {
            options +=
              "<div><hr style='border: 0.5px solid #d86057;'><div style='float:left;''><img src='../house.png' style='width: 50px; height: 50px; margin-left: 30px; margin-top: 10px; margin-right: 30px;'></div><div style='text-align: left; margin-left:50px;'><li style='font-size: 18px;'> 거래금액 : <b>" +
              data[i].dealAmount +
              "만원</b> </li><li>거래년도 : <b style='color:#808080;'> " +
              data[i].dealYear +
              "년</b></li>  <li> 면적 :<b style='color: #808080;'> " +
              data[i].area +
              "</b></li></div><hr style='border: 0.5px solid #d86057;'></div>";
            // options +=
            //   "<li>" +
            //   data[i].buildYear +
            //   "</li>" +
            //   "<li>" +
            //   data[i].dealAmount +
            //   "</li>" +
            //   "<li>" +
            //   data[i].area +
            //   "</li>";
          }
          const element = document.getElementById("content");

          element.innerHTML = options;
        })
        .catch(error => {
          alert(error);
        });
    },
  },
};
</script>
<style scoped>
#map {
  width: 100%;
  height: 700px;
}

#showdetail {
  margin: 10px;
  font-size: 20px;
  font-weight: 900;
}

hr {
  border: 0.5px solid #d86057;
}
</style>
