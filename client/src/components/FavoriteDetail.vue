<template>
  <div class="container">
    <div class="left-section">
      <b-card :title="choiceApt" img-src="https://www.goodmorningcc.com/news/photo/202305/288610_317435_1152.jpg" img-alt="Image" img-top tag="article" style="max-width: 100%;" class="mb-2">
        <b-card-text>
         건설년도 : {{ this.choiceYear }}<br>
          거래금액 : {{ this.choiceMoney }}<br>
          주소: {{ this.choiceDong }} {{this.choiceApt}}<br>
        </b-card-text>
      </b-card>
    </div>

    <div class="right-section">
      <b-list-group>
        <b-list-group-item :href="`http://localhost:8080/board/view/favorite/${item[6]}`" v-for="item in items" :key="item.id">{{ item[0] }}</b-list-group-item>
      </b-list-group>
    </div>
  </div>
</template>


<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6754b48bc8e82c049cd891e19dd5f56c"></script>
<script>
  import axios from "axios";
  export default {
    name: "FavoriteDetail",
    data() {
      return {
        data: {},
        list: [],
        mySet: new Set(),
        aptNameSet: new Set(),
        tmp: [],
        items: [],
        fields: [{
          key: "0",
          label: "아파트명"
        }, {
          key: "1",
          label: "건축년도"
        }, {
          key: "2",
          label: "거래금액"
        }, {
          key: "3",
          label: "동"
        }, {
          key: "4",
          label: "lat"
        }, {
          key: "5",
          label: "lng"
        }, ],
        showAptCode: 0,
        choiceApt: '',
        choiceYear: '',
        choiceMoney: '',
        choiceDong: '',
        choicelat: '',
        choicelng: '',
        map:null,
      };
    },
    created() {
      this.loadScript();
      this.listFavorites();
      this.showAptCode = this.$route.params.aptCode;
      console.log("헬로!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      console.log(this.showAptCode);
      console.log(this.items);
      console.log("안녕하세요ㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛㅛ");
      console.log(this.choiceApt);
      this.loadMap();
    },
    mounted() {
// if(window.kakao&&window.kakao.maps){
// this.loadMap();
// }else{
// this.loadScript();
// }
    },
    methods: {
      listFavorites() {
        axios({
          method: "get",
          url: "http://localhost:80/favorite/lists",
        }).then(response => {
          this.data = response.data;
          if (this.data.length != 0) {
            for (var i = 0; i < this.data.length; i++) {
              this.getAptList(this.data[i].aptCode);
            }
          }
        });
      },
      getAptList(aptCode) {
        axios({
          method: "post",
          url: "http://localhost:80/houses/search/favorite/house",
          params: {
            aptCode: aptCode,
          },
          responseType: "json",
        }).then(response => {
          this.list.push(response.data);
          console.log(this.list);
          console.log("잠와");
          for (var i = 0; i < this.list.length; i++) {
            console.log("wow");
            console.log(this.list[i][0].apartmentName);
            if (!this.aptNameSet.has(this.list[i][0].apartmentName)) {
              this.aptNameSet.add(this.list[i][0].apartmentName);
              this.tmp.push(this.list[i][0].apartmentName);
              this.tmp.push(this.list[i][0].buildYear);
              this.tmp.push(this.list[i][0].dealAmount);
              this.tmp.push(this.list[i][0].dong);
              this.tmp.push(this.list[i][0].lat);
              this.tmp.push(this.list[i][0].lng);
              this.tmp.push(this.list[i][0].aptCode);
              this.mySet.add(this.tmp);
              this.tmp = [];
            }
          }
          console.log(this.mySet);
          this.items = Array.from(this.mySet); // Set을 배열로 변환하여 items에 할당
          console.log("하이");
          console.log(this.items);
          for (var a = 0; a < this.items.length; a++) {
            if (this.items[a][6] == this.showAptCode) {
              console.log(this.items[a][6]);
              this.choiceApt = this.items[a][0];
              this.choiceYear = this.items[a][1];
              this.choiceMoney = this.items[a][2];
              this.choiceDong = this.items[a][3];
              this.choicelat = this.items[a][6];
            }
          }
          console.log("for문 " + this.choicelat);
        });
      },

       loadScript() {
      const script = document.createElement("script");
      script.src =
        // "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4b239f7d8ede01160eac66cfc322badf";
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6754b48bc8e82c049cd891e19dd5f56c&libraries=services,clusterer,drawing";
          script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    loadMap() {
          var roadviewContainer = document.getElementById("roadview");
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
          var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
          var latlng = new kakao.maps.LatLng(3, 3);
          roadviewClient.getNearestPanoId(latlng, 50, panoId => {
              roadview.setPanoId(panoId, latlng);
          });
}}
};

</script>
<style scoped>
.container {
  display: flex;
}

.left-section {
  flex: 1;
   margin-right: 10px; /* 오른쪽 간격 조정 */
    margin-top: 10%; /* 오른쪽 간격 조정 */

}

.right-section {
  flex: 1;
   margin-left: 10px; /* 오른쪽 간격 조정 */
    margin-top: 10%; /* 오른쪽 간격 조정 */

}
</style>