<template>
  <div class="test">
    <section class="py-5 text-center container vh-100">
      <div style="margin-top: 150px">
        <h1 style="font-size: 60px">
          <b>원하는 집을 찾으세요.</b>
        </h1>
      </div>
      <div
        class="py-lg-5"
        style="
          margin: 20px;
          background: rgba(255, 255, 255, 0.2);
          border-radius: 16px;
          box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
          backdrop-filter: blur(5px);
          -webkit-backdrop-filter: blur(5px);
          border: 3px solid rgba(216, 96, 87, 0.3);
          padding: 25px;
        "
      >
        <div class="row col-md-12 justify-content-center mb-2">
          <div class="form-group col-md-2">
            <select
              v-model="sidoValue"
              @change="changeGugun()"
              class="form-select"
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
              class="form-select"
              id="gugun"
              name="gugun"
            >
              <option value="">구군 선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select
              v-model="dongValue"
              class="form-select"
              id="dong"
              name="dong"
            >
              <option value="">동 선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <button
              @click="searchMap()"
              type="submit"
              id="list-btn"
              class="btn"
              style="background-color: #d86057; color: white"
            >
              검색
            </button>

            <div class="input-group mb-3">
      
            </div>
          </div>
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
      searchTerm: "",
      isFocus: false,
      selectedObj: null,
      dataList: [
        {
          name: "One",
          value: "one",
        },
        {
          name: "Two",
          value: "two",
        },
        {
          name: "Three",
          value: "three",
        },
        {
          name: "Four",
          value: "four",
        },
      ],
    };
  },

  methods: {
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
            options +=
              '<option value="' + data[i] + '">' + data[i] + "</option>";
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
        params: {
          sido: this.sidoValue,
          gugun: this.gugunValue,
          dong: this.dongValue,
        },
      });
    },
  },
  computed: {
    filteredList() {
      if (this.searchTerm === "") {
        return this.dataList;
      }
      return (
        this,
        dataList.filter((num) => {
          if (num.value.includes(this.searchTerm)) {
            return num;
          }
        })
      );
    },
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
