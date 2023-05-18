<template>
  <div id="map"></div>
</template>

<script>
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
        center: new window.kakao.maps.LatLng(this.houses[0].lat, this.houses[0].lng),
        level: 3,
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.loadMaker(this.houses);
    },

    loadMaker(houses) {
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < houses.length; i++) {
        var imageSize = new kakao.maps.Size(24, 35);
        var latlng = new kakao.maps.LatLng(houses[i].lat, houses[i].lng);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        var marker = new kakao.maps.Marker({
          map: this.map,
          position: latlng,
          title: houses[i].apartmentName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
      }
    },
  },
};
</script>
<style scoped>
#map {
  width: 100%;
  height: 700px;
}
</style>
