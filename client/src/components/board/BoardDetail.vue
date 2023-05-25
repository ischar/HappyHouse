<template>
  <div class="justify-content-center">
    <div class="regist">
      <div class="regist_form">
        <!-- <label>글번호</label> -->
        <!-- <div class="view">{{ article.articleno }}</div> -->
        <!-- <br /> -->
        <label>글제목</label>
        <div class="view">{{ article.subject }}</div>
        <br />
        <label>작성자</label>
        <div class="view">{{ article.userid }}</div>
        <br />
        <label>조회수</label>
        <div class="view">{{ article.hit }}</div>
        <br />
        <label>작성시간</label>
        <div class="view">{{ article.regtime }}</div>
        <br />
        <label>내용</label>
        <div class="view">{{ article.content }}</div>

        <div style="padding-top: 15px">
          <div v-if="loginId == article.userid">
            <router-link :to="{ name: 'boardmodify', params: { articleno: article.articleno } }" class="btn">
              수정
            </router-link>
          </div>
          <div v-if="loginId == article.userid">
            <router-link :to="{ name: 'boarddelete', params: { articleno: article.articleno } }" class="btn">
              삭제
            </router-link>
          </div>
          <router-link :to="{ name: 'boardlist' }" class="btn">목록</router-link>
        </div>
      </div>
      <div class="d-flex flex-row add-comment-section mt-4 mb-4">
        <img class="img-fluid img-responsive rounded-circle mr-2" src="https://i.imgur.com/qdiP4DB.jpg" width="38" />
        <input type="text" class="form-control mr-3" placeholder="Add comment" v-model="newComment" />
        <button class="btn btn-primary" type="button" @click="commentAdd">
          Comment
        </button>
      </div>
      <board-comment-item v-for="comment in comments" :key="comment.commentno" :comment="comment"
        :articleno="article.articleno" @refresh="refreshComments"></board-comment-item>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import BoardCommentItem from "@/components/board/BoardCommentItem";

export default {
  computed: {
    ...mapState(["loginId"]),
  },
  components: {
    BoardCommentItem,
  },
  name: "BoardDetail",
  data() {
    return {
      article: Object,
      comments: [],
      newComment: "",
    };
  },
  created() {
    // 비동기
    // 글번호에 해당하는 글정보 얻기.
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      console.log(this.$route.params.articleno);
      this.article = data;
    });
    http.get(`/comment/${this.$route.params.articleno}`).then(response => {
      this.comments = response.data;
    });
  },
  methods: {
    commentAdd() {
      let newcomment = {
        articleno: this.article.articleno,
        userid: this.loginId,
        content: this.newComment,
      };
      http.post("/comment", newcomment).then(({ data }) => {
        console.log(this.article.articleno);
        console.log(this.article.userid);
        //console.log(data);
        http.get(`/comment/${this.$route.params.articleno}`).then(response => {
          this.comments = response.data;
        });
        this.newComment = ""; // 입력 필드를 초기화
      });
    },
    refreshComments() {
      //alert("refresh 실행!");
      http.get(`/comment/${this.$route.params.articleno}`).then(response => {
        this.comments = response.data;
      });
    },
  },
};
</script>

<style></style>
