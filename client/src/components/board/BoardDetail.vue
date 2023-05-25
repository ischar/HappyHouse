<template>
  <div>
    <div class="allcomponents">
      <b-card>
        <div class="content-detail-content-info">
          <div class="content-detail-content-info-left">
            <div class="content-detail-content-info-left-subject">
              <h1>{{ article.subject }}</h1>
            </div>
          </div>
          <div class="content-detail-content-info-right">
            <div class="content-detail-content-info-right-user">
              글쓴이: {{ article.userid }}
            </div>
            <div class="content-detail-content-info-right-created">
              {{ article.regtime }}
            </div>
          </div>
        </div>
        <div class="content-detail-content">{{ article.content }}</div>
        <div class="content-detail-button d-flex">
          <div v-if="loginId == article.userid">
            <b-button variant="primary" @click="updateData">
              <router-link
                :to="{
                  name: 'boardmodify',
                  params: {articleno: article.articleno},
                }"
                class="btn text-white"
                >수정</router-link
              ></b-button
            >&nbsp;
          </div>
          <div v-if="loginId == article.userid">
            <b-button variant="danger" @click="deleteData">
              <router-link
                :to="{
                  name: 'boarddelete',
                  params: {articleno: article.articleno},
                }"
                class="btn text-white"
                >삭제</router-link
              ></b-button
            >&nbsp;
          </div>
          <b-button variant="success"
            ><router-link :to="{name: 'boardlist'}" class="btn text-white"
              >목록</router-link
            ></b-button
          >
        </div>
        <div class="content-detail-comment">
          <CommentList :contentId="contentId"></CommentList>
          <div class="d-flex flex-row add-comment-section mt-4 mb-4">
            <input
              type="text"
              class="form-control mr-3"
              placeholder="댓글을 입력하세요"
              v-model="newComment" />
            <button class="btn btn-primary" type="button" @click="commentAdd">
              Comment
            </button>
          </div>
          <board-comment-item
            v-for="comment in comments"
            :key="comment.commentno"
            :comment="comment"
            :articleno="article.articleno"
            @refresh="refreshComments"></board-comment-item>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import {mapState} from "vuex";
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
    http.get(`/board/${this.$route.params.articleno}`).then(({data}) => {
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
      http.post("/comment", newcomment).then(({data}) => {
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

<style scoped>
.content-detail-content-info {
  display: flex;
  justify-content: space-between;
}

.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}

.content-detail-button {
  margin-top: 1rem;
  padding: 2rem;
  justify-content: flex-end;
}

.content-detail-comment {
  margin-top: 1rem;
  padding: 2rem;
}

.allcomponents {
  margin: 5px 30%;
}
</style>
