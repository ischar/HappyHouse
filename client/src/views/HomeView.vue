<template>
  <div class="test">
    <section class="py-5 text-center container vh-100">
      <div style="margin-top: 150px">
        <h1 style="font-size: 80px">
          <b>Where is My Home</b>
        </h1>
      </div>
      <div
        class="py-lg-5"
        style="
          background: rgba(255, 255, 255, 0.2);
          border-radius: 16px;
          box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
          backdrop-filter: blur(5px);
          -webkit-backdrop-filter: blur(5px);
          border: 1px solid rgba(255, 255, 255, 0.3);
        "
      >
        <div class="row col-md-12 justify-content-center mb-2">
          <div class="form-group col-md-2">
            <select
              v-model="sidoValue"
              @change="changeGugun()"
              class="form-select bg-secondary text-light"
              id="sido"
              name="sido"
            >
              <option value="">시도 선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select
              v-model="gugunValue"
              @change="changeDong()"
              class="form-select bg-secondary text-light"
              id="gugun"
              name="gugun"
            >
              <option value="">구군 선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select
              v-model="dongValue"
              class="form-select bg-secondary text-light"
              id="dong"
              name="dong"
            >
              <option value="">동 선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <button @click="searchMap()" type="submit" id="list-btn" class="btn btn-dark">
              아파트매매정보
            </button>
          </div>
        </div>
      </div>
      <div class="input-group mb-3">
        <select>
          <option id="dong" value="dong">동</option>
          <option id="apt" value="apt">아파트</option>
        </select>
        <input
          v-model="searchBar"
          type="text"
          class="form-control"
          placeholder="찾고자 하는 아파트나 동을 입력하세요."
          aria-label="Recipient's username"
          aria-describedby="basic-addon2"
        />
        <div class="input-group-append">
          <button
            @click="searchByBar()"
            class="btn btn-outline-secondary"
            style="background-color: darkgrey"
            type="button"
          >
            Button
          </button>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped></style>

<script>
import axios from "axios";
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  mounted() {
    this.getData();
  },

  data() {
    return {
      sidoValue: "",
      gugunValue: "",
      dongValue: "",
    };
  },
  methods: {
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
    searchMap() {
      this.$router.push({
        name: "search",
        params: { sido: this.sidoValue, gugun: this.gugunValue, dong: this.dongValue },
      });
    },
  },
};
</script>
