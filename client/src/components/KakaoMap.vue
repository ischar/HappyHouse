<template>
<!--  <div style="width: 100%"> -->
    <div style="position: relative; width: 100%;  overflow: hidden;">
      <div
        style="
          width: 400px;
          height: 100%;
          background-color: white;
          position: absolute;
          text-align: center;
          z-index: 2;
          border: 0.5px solid #d86057;

        "
      >
        <div id="showdetail"></div>
        <div id="roadview" style="width: 400px; height: 250px;"></div>
        <div style="text-align:right">
          <img src="../assets/favoriteon.png" style="width: 30px; height: 30px;">
        </div>
        <h3>거래내역</h3>
         <div id="content" style=" height: 100%; overflow: auto"></div> 
      </div>
      <div id="map" style="width: 100%; height: 100%; border: 0.5px solid #d86057;"></div>
    </div>
<!--  </div> -->
</template>

<script>
import { left } from "@popperjs/core";
import axios from "axios";

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
      },
    ],
  },
  name: "KakaoMap",
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
    };
  },

  setup() {},
  created() {},
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
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4b239f7d8ede01160eac66cfc322badf";
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
       // center: new window.kakao.maps.LatLng(this.houses[0].lat, this.houses[0].lng),
       center: new window.kakao.maps.LatLng(12, 12),
        level: 3,
      };
      this.map = new window.kakao.maps.Map(container, options);

      this.loadMaker(this.houses);
    },

    addFavorite(userid, aptCode) {
      axios({
        method: "get",
        params: {
          title: title,
        },
        url: "http://localhost:80/houses/search/house",
        responseType: "json", 
      })
    },

    loadMaker(houses) {
      var imageSrc = "https://i.postimg.cc/ZRcqsDZ9/location.png";
      var imageSize = new kakao.maps.Size(36, 42); // 마커이미지의 크기입니다
      var imageOption = {offset: new kakao.maps.Point(14, 39)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
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
          title: positions[i].content.apartmentName,
          image:markerImage, // 마커이미지 설정
        });
        bounds.extend(positions[i].latlng);
        // infowindow = new kakao.maps.InfoWindow({
        //   content: marker.getTitle(),
        // });
        kakao.maps.event.addListener(marker, "click", () => {
          var text = marker.getTitle();
          const element = document.getElementById("showdetail");
          element.innerText = text;

          var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
          var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
          var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

          roadviewClient.getNearestPanoId(marker.getPosition(), 50, (panoId) => {
            if (panoId == null) {
              alert("로드뷰 정보가 없는 지역입니다.");
            } else {
              roadview.setPanoId(panoId, marker.getPosition());

              this.showContent(marker.getTitle());
            }
            // roadview.setPanoId(panoId, marker.getPosition()); //panoId와 중심좌표를 통해 로드뷰 실행
          });
        });
      }
      this.map.setBounds(bounds);
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
        .then((response) => {
          var data = response.data;
          var options = "";

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
        .catch((error) => {
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
  border:0.5px solid #d86057;
}
</style>
