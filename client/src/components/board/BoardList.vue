<template>
  <div class="py-5 text-center container justify-content-center">
    <h1 class="underline" style="font-size: 28px; margin-bottom:15px;">목록</h1>

    <!-- 추가 -->
    <b-form-group class="mb-0 w-75 justify-content-center" style="float:left; margin-left: 10%;">
      <b-input-group size="mb">
        <b-form-input id="filter-input" v-model="filter" type="search" placeholder="Type to Search"></b-form-input>

        <b-input-group-append>
          <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
        </b-input-group-append>
      </b-input-group>
    </b-form-group>
    <div style="text-align: right; float:left;">
      <button class="btn btn-primary" @click="movePage" style="margin-left: 15px;">글작성</button>
    </div>

    <!-- 추가 끝 -->

    <!-- board list area -->
    <b-container fluid class="my-table-container">
      <b-table style="margin-top: 120px;" class="my-table" hover :items="articles" :per-page="perPage"
        :current-page="currentPage" :fields="fields" sort-icon-left label-sort-asc="" label-sort-desc=""
        label-sort-clear="" :filter="filter" @filtered="onFiltered">
        <template #cell(subject)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <a :href="`/board/post/${data.item.articleno}`">{{ data.value }}</a>
        </template></b-table>
    </b-container>
    <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table"
      align="center"></b-pagination>
  </div>
</template>

<script>
import BoardListItem from "@/components/board/BoardListItem";
import http from "@/api/http";

export default {
  name: "BoardList",
  components: {
    //BoardListItem,
  },
  data() {
    return {
      articles: [],
      nowpage: 0,
      rightpage: 0,
      reset: 0,
      postCount: 0,
      totalCount: 0,
      currentPage: 1,
      perPage: 10,
      fields: [
        {
          key: "articleno",
          label: "글번호",
          sortable: false,
        },
        {
          key: "subject",
          label: "제목",
          sortable: false,
        },
        {
          key: "userid",
          label: "작성자",
          sortable: false,
        },
        {
          key: "regtime",
          label: "작성일",
          sortable: false,
        },
        {
          key: "hit",
          label: "조회수",
          sortable: true,
        },
      ],
      filter: null,
    };
  },
  created() {
    console.log("test");
    http.get(`/board`, {}).then(response => {
      this.articles = response.data;
      console.log(this.articles);
    });
    http.get("/board/count").then(response => {
      this.totalCount = response.data.pagenavigation.totalCount;
      this.pagenavigation = response.data.pagenavigation;
      console.log(this.pagenavigation);
    });
  },
  methods: {
    movePage() {
      this.$router.push({ name: "boardwrite" });
    },
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
  onFiltered(filteredItems) {
    // Trigger pagination to update the number of buttons/pages due to filtering
    this.totalRows = filteredItems.length;
    this.currentPage = 1;
  },
};
</script>

<style scoped>
.my-table-container {
  /* 컨테이너에 대한 좌우 여백 설정 */
  /* 예시: 좌우 여백 20px 설정 */
  margin-left: 5%;
  margin-right: 5%;
}

.my-table-container .b-table {
  /* 테이블 요소에 대한 스타일 */
  /* 예시: 여백 제거 */

  /* 예시: 테이블 요소 내부에 좌우 패딩 추가 */
  /*padding-left: 10px;*/
  /*padding-right: 10px;*/
}

.my-table {
  /* 테이블에 대한 스타일 */
  width: 100%;
  /* 테이블의 너비를 100%로 설정 */
}
</style>
