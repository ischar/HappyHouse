<template>
  <div>
    <div class="media comment-container">
      <a class="pull-left" href="#">
        <img
          class="media-object"
          src="https://bootdey.com/img/Content/avatar/avatar1.png"
          alt="" />
      </a>
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
        <ul class="list-unstyled list-inline media-detail">
          <li><i class="fa fa-calendar"></i> {{ comment.regtime }}</li>
        </ul>
        <ul class="list-unstyled list-inline media-detail">
          <li
            class="edit-button"
            v-if="loginId == comment.userid"
            @click="updateComment">
            수정
          </li>
          <li
            class="delete-button"
            v-if="loginId == comment.userid"
            @click="deleteComment">
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

<style>
.comment-container {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.comment-container .media-object {
  width: 64px;
  height: 64px;
  border-radius: 50%;
}

.comment-container .media-body {
  margin-left: 10px;
}

.comment-container .media-heading {
  margin: 0;
  font-size: 18px;
  font-weight: bold;
}

.comment-container p {
  margin: 0;
  font-size: 14px;
}

.comment-container .list-unstyled.list-inline {
  margin: 0;
  padding: 0;
}

.comment-container .list-unstyled.list-inline.media-detail li {
  display: inline-block;
  margin-right: 10px;
  font-size: 12px;
}

.comment-container .list-unstyled.list-inline.media-detail li i {
  margin-right: 5px;
}

.comment-container .list-unstyled.list-inline.media-detail li:last-child {
  margin-right: 0;
}

.comment-container .edit-button,
.comment-container .delete-button {
  cursor: pointer;
  font-size: 12px;
}

.comment-container .delete-button {
  margin-left: 10px;
  color: red;
}
</style>
