<template>
  <div class="table-container">
    <b-table stacked :items="items" :fields="fields"></b-table>
  </div>
</template>

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
      fields: [
        {key: "0", label: "아파트명"},
        {key: "1", label: "건축년도"},
        {key: "2", label: "거래금액"},
        {key: "3", label: "동"},
        {key: "4", label: "lat"},
        {key: "5", label: "lng"},
      ],
    };
  },
  created() {
    this.listFavorites();
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
        console.log(this.items);
      });
    },
  },
};
</script>

<style scoped>
.table-container {
  max-width: 800px; /* 원하는 가로 크기로 조절 */
  margin: 0 auto; /* 가운데 정렬을 위해 필요한 경우 */
}
</style>
