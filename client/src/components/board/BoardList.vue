<template>
  <div>
    <h1 class="underline">목록</h1>
    <div style="text-align: right">
      <button @click="movePage">도서 등록</button>
    </div>
    <div v-if="articles.length">
      <table id="article-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 65%" />
          <col style="width: 10%" />
          <col style="width: 5%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          <board-list-item
            v-for="article in articles"
            :key="article.articleno"
            :article="article"></board-list-item>
        </tbody>
      </table>
      <div>
        <div v-on:click="rightpage--"> 이이전페이지 </div>
        <div v-on:click="changePage(nowpage - 1)"> 이전페이지 </div>
        <div v-on:click="changePage(1 + 5*rightpage)">{{ 1 + 5*rightpage }}</div>
        <div v-on:click="changePage(2 + 5*rightpage)">{{ 2 + 5*rightpage }}</div>
        <div v-on:click="changePage(3 + 5*rightpage)">{{ 3 + 5*rightpage }}</div>
        <div v-on:click="changePage(4 + 5*rightpage)">{{ 4 + 5*rightpage }}</div>
        <div v-on:click="changePage(5 + 5*rightpage)">{{ 5 + 5*rightpage }}</div>
        <div v-on:click="changePage(nowpage + 1)">다음페이지</div>
        <div v-on:click="rightpage++"> 다다음페이지 </div>
      </div>
    </div>
    <div class="text-center" v-else>게시글이 없습니다.</div>
  </div>
</template>

<script>
import BoardListItem from "@/components/board/BoardListItem";
import http from "@/api/http";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      nowpage: 0,
      rightpage: 0,
      reset : 0,
    };
  },
  created() {
    console.log("test");
    http.get(`/board`, {}).then(response => {
      this.articles = response.data;
    });
  },
  methods: {
    movePage() {
      this.$router.push({name: "boardwrite"});
    },
    changePage(num){
      this.nowpage = num;
      http.get(`/board?pg=${num}`, {}).then(response => {
      this.articles = response.data;
    });
    }
  },
};
</script>

<style></style>
