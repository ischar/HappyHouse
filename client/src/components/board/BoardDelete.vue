<template>
  <div class="regist">
    <img
      src="../../assets/delete.png"
      style="width: 300px; height: 300px opacity:80%;" />
    <h3 style="margin-left: 50px">삭제중..</h3>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardDelete",
  created() {
    console.log(this.$route.params.articleno);
    // 비동기
    // TODO : 글번호에 해당하는 글을 삭제.
    http.delete(`/board/${this.$route.params.articleno}`).then(({data}) => {
      let msg = "삭제 처리시 문제가 발생했습니다.";
      if (data === "success") {
        msg = "삭제가 완료되었습니다.";
      }
      alert(msg);
      // 현재 route를 /list로 변경.
      this.$router.push({name: "boardlist"});
    });
  },
};
</script>

<style scoped>
.regist {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
