<template>
  <div class="py-5 text-center container">
    <h1 class="underline">기사 목록</h1>
    <!-- 검색 -->
    <b-form-group label-for="filter-input" class="mb-0">
      <b-input-group size="sm">
        <b-form-input
          id="filter-input"
          v-model="filter"
          type="search"
          placeholder="검색어를 입력하세요"></b-form-input>
        <b-input-group-append>
          <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
        </b-input-group-append>
      </b-input-group>
    </b-form-group>
    <!-- 검색 끝 -->
    <!-- board list area -->
    <b-container fluid class="my-table-container">
      <b-table
        class="my-table"
        hover
        :items="articles"
        :fields="fields"
        :current-page="currentPage"
        :per-page="perPage"
        :filter="filter"
        :filter-included-fields="filterOn"
        sort-icon-left
        label-sort-asc=""
        label-sort-desc=""
        label-sort-clear=""
        @filtered="onFiltered">
        <template #cell(title)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <a style="text-decoration:none; color:black;" :href="`${data.item.originallink}`">
            <p v-html="data.value"></p>
          </a>
        </template>
      </b-table>
    </b-container>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"></b-pagination>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "NewsList",
  components: {
    //BoardListItem,
  },
  data() {
    return {
      articles: [],
      filteredArticles: [],
      nowpage: 0,
      rightpage: 0,
      reset: 0,
      postCount: 0,
      totalCount: 0,
      currentPage: 1,
      perPage: 10,
      totalRows: 1,
      fields: [
        {
          key: "title",
          label: "기사제목",
          sortable: false,
        },
        {
          key: "pubDate",
          label: "작성일",
          sortable: false,
        },
      ],
      filter: null,
    };
  },
  created() {
    axios({
      method: "get",
      url: "http://localhost:80/news",
      params: {
        num: 100,
      },
      responseType: "json",
    }).then(response => {
      console.log("response", response.data.items);
      this.articles = response.data.items;
      this.filteredArticles = response.data.items; // 초기에 전체 기사 목록으로 설정

    });

  },
  methods: {
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },

  },
  mounted() {
    // Set the initial number of items
    this.totalRows = this.items.length;
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
};
</script>
<style scoped></style>
