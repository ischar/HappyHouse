<template>
  <div class="test">
    <section class="py-5 text-center container">
      <div style="margin-top: 100px">
        <h1 style="font-size: 40px">
          <b>원하는 집을 찾으세요.</b>
        </h1>
      </div>
      <div class="py-lg-5"
        style="fixed; position: relative; z-index:2 ;
                                                                                                                                      margin: 20px; 
                                                                                                                                      background: rgba(255, 255, 255, 0.2);
                                                                                                                                      border-radius: 16px;
                                                                                                                                      box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
                                                                                                                                      backdrop-filter: blur(5px);
                                                                                                                                      -webkit-backdrop-filter: blur(5px);
                                                                                                                                      border: 3px solid rgba(216, 96, 87, 0.3);
                                                                                                                                      padding: 25px;
                                                                                                                                    ">

        <div>
          <div class="input-group col-6 d-flex justify-content-center">
            <div class="w-50 justify-content-center text-center">
              <input type="text" id="word" class="form-control" autocomplete="off" v-model="state" @mouseout="deleteList"
                @input="filterStates" @focus="modal = true" placeholder="원하는 시, 군, 구를 입력하세요."
                aria-label="원하는 시,구,동을 입력하세요." aria-describedby="button-addon2" />
              <!-- <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button> -->
            </div>
          </div>
          <div v-if="filteredStates && modal" class="w-100" style="absolute;">
            <div class="col-6 d-flex w-100 justify-content-center" style="width: 90%">
              <ul class="list-group justify-content-center w-50"
                style="
                                                                                                                                list-style-image: url('https://i.postimg.cc/Zqq5xL0k/pin.png');
                                                                                                                                border-radius: 0px;
                                                                                                                                width: 400px;
                                                                                                                                margin-left: 0.75%;
                                                                                                                                position: fixed;
                                                                                                                                z-index: 2;
                                                                                                                              ">
                <li class="list-group-item"
                  style="
                                                                                                                                  border-width: 0.5px;
                                                                                                                                  border-color: #F3CFCC;
                                                                                                                                  font-weight: 700;
                                                                                                                                  color: #828282;
                                                                                                                                  width: 95%; 
                                                                                                                                  text-align: left;
                                                                                                                                  margin-left: 10px;
                                                                                                                                  font-size:14px;
                                                                                                                                "
                  v-for="filteredState in filteredStates" @click="setState(filteredState)">
                  {{ filteredState }}
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="row col-md-12 justify-content-center mb-2" style="margin: 0px; margin-top: 10px;">
          <div class="form-group col-md-2">
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
            <select v-model="dongValue" @change="searchMap()" class="form-select" id="dong" name="dong">
              <option value="">동 선택</option>
            </select>
          </div>
          <!-- <div class="form-group col-md-2">
            <button @click="searchMap()" type="submit" id="list-btn" class="btn"
              style="background-color: #d86057; color: white">
              검색
            </button>
          </div> -->
        </div>

      </div>
    </section>
    <section class="text-center container">
      <div style="text-align: center;">
        <div style=" width: 370px; float: left; display: inline-block">
          <li style="list-style-type: none; font-size: 18px; margin-bottom: 0px"><b>즐겨찾기</b></li>
          <hr style="color: #d86057" />
          <div style="text-align: left; margin-left: 10px" v-for="favorite in favorites" :key="favorite.userid"
            :favorite="favorite">
            <!-- <router-link style="text-decoration: none; color:black;" :to="`/board/view/${favorite."> -->
            <ul style="list-style-image: url(https://i.postimg.cc/66zrhk3F/star.png)">
              <router-link style="text-decoration: none; color: black;" :to="`/board/view/favorite/${favorite.aptCode}`">
              <!-- <li @click="aptDetail(favorite)"> -->
                <li><b>{{ favorite.apartmentName }}</b></li>
              <!-- </li> -->
              </router-link>
            </ul>
            <!-- <outer-link> -->
          </div>
        </div>

      </div>

      <div style="margin-left: 80px; width: 370px; float:left; display:inline-block">
        <li style="list-style-type: none; font-size:18px; margin-bottom: 0px;"><b>공지사항</b></li>
        <hr style="color: #d86057;">


        <div style="text-align:left; margin-left: 10px;" v-for="(article, index) in articles.slice(0, 5)"
          :key="article.articleno" :article="article">
          <router-link style="text-decoration: none; color:black;" :to="`/board/post/${article.articleno}`">

            {{ article.subject }}
          </router-link>
        </div>
      </div>
      <div style="margin-left: 80px; float:left; width: 370px; display:inline-block">
        <li style="list-style-type: none; font-size:18px; margin-bottom: 0px;"><b>오늘의뉴스</b></li>
        <hr style="color: #d86057;">
        <div style="text-align:left; margin-left: 10px;" v-for="newss in news" :key="newss.link" :newss="newss">
          <li style="list-style-type: none;" v-html="newss.title" @click="navigateToLink(newss.originallink)"></li>
          <!-- <router-link style="text-decoration: none; color:black;" :to="/local"> {{ newss.title }}</router-link> -->

        </div>
      </div>
    </section>
  </div>
</template>

<style scoped></style>

<script>
import axios from "axios";
import BoardListItem from "@/components/board/BoardListItem";
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  created() {
    this.getSiGunGu();
    http.get(`/board`, {}).then((response) => {
      this.articles = response.data;
    });
    this.listFavorites();
  },
  computed: {
    ...mapState(["loginId"]),
  },
  mounted() {
    this.getData();
    this.showNews();
  },

  data() {
    return {
      state: "",
      states: [],
      filteredStates: [],
      id: this.loginId,
      favorites: [],
      news: [],
      articles: [],
      nowpage: 0,
      rightpage: 0,
      reset: 0,
      sidoValue: "",
      gugunValue: "",
      dongValue: "",
      searchTerm: "",
      isFocus: false,
      selectedObj: null,
    };
  },

  methods: {
    deleteList() {
      this.filteredStates = [];
    },
    getSiGunGu() {
      axios({
        method: "get",
        url: "http://localhost:80/address/sigungu",
        responseType: "json",
      }).then((response) => {
        var data = response.data;
        for (var i = 0; i < data.length; i++) {
          var text = data[i].sidoName + " " + data[i].gugunName + " " + data[i].dongName;
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
    setDate() {
    for (var param in this.$route.params) {
        var x = param.newss.pubDate;
        var year = x.getFullYear();
        var month = ('0' + (x.getMonth() + 1)).slice(-2);
        var day = ('0' + today.getDate()).slice(-2);
        var dateString = year + '년 ' + month + '월 ' + day + '일';
        param.newss.pubDate = dateString;
    }
},
    setState(state) {
      this.state = state;
      this.modal = false;

      var text = state.split(" ");
      this.sidoValue = text[0];
      this.gugunValue = text[1];
      this.dongValue = text[2];
      this.searchMap();
    },
    movePage() {
      this.$router.push({ name: "boardwrite" });
    },
    setSearchTerm(e) {
      this.searchTerm = e.target.value;
    },
    setListOpen(isOpen) {
      this.isFocus = isOpen;
    },
    setSelectedNumber(numObj) {
      this.selectedObj = numObj;
      this.searchTerm = numObj.name;
    },
    removeSelectedNumber() {
      this.selectedObj = null;
      this.searchTerm = "";
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

    searchByBar() {
      this.$router.push({
        name: "searchBar",
        params: {
          word: this.searchBar,
          searchOption: this.searchOption,
        },
      });
    },

    navigateToLink(link) {
      window.open(link, "_blank");
    },




    aptDetail(apt) {
      this.$router.push({
        name: "apt",
        params: {
          apt: apt,
        },
      });
    },


    showNews() {
      axios({
        method: "get",
        url: "http://localhost:80/news",
        params: {
          num: 5,
        },
        responseType: "json",
      }).then((response) => {
        console.log("response", response.data.items);
        this.news = response.data.items;
      });
    },
    searchMap() {
      this.$router.push({
        name: "search",
        params: {
          sido: this.sidoValue,
          gugun: this.gugunValue,
          dong: this.dongValue,
        },
      });
    },

    listFavorites() {
      axios({
        method: "get",
        url: "http://localhost:80/favorite/list",
        params: {
          userId: this.loginId,
        },
      }).then((response) => {
        console.log(this.loginId);
        this.favorites = response.data;
      });
    },
  },
};
</script>
<style scoped>
.form-control,
.form-select {
  border-width: 3px;
}

.vueautocomplete,
.vueautocomplete,
.suggestionlist {
  width: 300px;
}

.vueautocomplete input[type="text"] {
  width: 100%;
  padding: 5px;
}

.form-control:focus,
.form-select:valid:focus {
  box-shadow: none;
  border: 3px solid #d86057;
}
</style>
