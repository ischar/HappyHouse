<template>
  <div>
    <div class="media">
      <a class="pull-left" href="#"
        ><img
          class="media-object"
          src="https://bootdey.com/img/Content/avatar/avatar1.png"
          alt=""
      /></a>
      <div class="media-body">
        <h4 class="media-heading">{{ comment.userid }}</h4>
        <div>
          <div v-if="updateClick">
            <input
              type="text"
              :placeholder="comment.content"
              v-model="newComment" />
            <button
              class="btn btn-primary"
              type="button"
              @click="updateCommentBtn">
              Comment
            </button>
          </div>
          <p v-else>{{ comment.content }}</p>
        </div>
        <ul class="list-unstyled list-inline media-detail pull-left">
          <li><i class="fa fa-calendar"></i> {{ comment.regtime }}</li>
          <li><i class="fa fa-thumbs-up"></i>{{ comment.hit }}</li>
        </ul>
        <ul class="list-unstyled list-inline media-detail pull-right">
          <li class="" v-if="loginId == comment.userid" @click="updateComment">
            수정
          </li>
          <li class="" v-if="loginId == comment.userid" @click="deleteComment">
            삭제
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import {mapState} from "vuex";
export default {
  name: "BoardCommentItem",
  props: {
    comment: Object,
    articleno: Number,
  },
  computed: {
    ...mapState(["loginId"]),
  },
  data() {
    return {
      updateClick: false,
      newComment: "", // 새로운 댓글 내용을 담을 변수
    };
  },
  methods: {
    async deleteComment() {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        await http.delete(`/comment/${this.comment.commentno}`);
        this.$emit("refresh");
      }
    },
    updateComment() {
      this.updateClick = true;
    },

    async updateCommentBtn() {
      this.updateClick = false;
      let updatecomment = {
        commentno: this.comment.commentno,
        articleno: this.articleno,
        userid: this.loginId,
        content: this.newComment,
      };
      await http.put("/comment", updatecomment).then(({data}) => {
        this.$emit("refresh");
      });
    },
  },
};
</script>

<style></style>
