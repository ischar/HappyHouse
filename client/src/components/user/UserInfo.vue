<template>
  <div>
    <section class="py-5 text-center container">
      <div class="container-fluid vh-100" style="margin-top: 100px">
        <div class="" style="margin-top: 200px">
          <div class="rounded d-flex justify-content-center">
            <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light" style="width: 600px">
              <div class="text-center">
                <h3 style="margin-bottom:0px;" class="text-dark">MyPage</h3>
                <img src="../../assets/users.png"
                  style="width: 100px; height: 100px; margin-top: 20px; margin-bottom: 0px;">
              </div>
              <div class="p-7">
                <!-- id -->
                <div class="input-group mb-3">
                  <span class="input-group-text" style="background-color: #d86057">
                    <img src="../../assets/users.png" style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i></span><b>
                    <!--<input
                    id="id"
                    type="text"
                    class="form-control"
                    value="${ loginName }"
                    placeholder="${userinfo.id}"
                    disabled >{{ loginName }}</input>-->
                    아이디 : {{ loginId }}</b>
                </div>

                <!-- 비밀번호 -->


                <!-- 이름 -->
                <div class="input-group mb-3">
                  <span class="input-group-text" style="background-color: #d86057">
                    <img src="../../assets/name.png" style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i></span><b>
                    이름 : {{ loginName }}</b>
                </div>

                <!-- 주소 -->
                <div class="input-group mb-3">
                  <span class="input-group-text" style="background-color: #d86057">
                    <img src="../../assets/address.png" style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i></span><b>
                    주소 : {{ loginAddr }}</b>
                </div>

                <div class="input-group mb-3">
                  <span class="input-group-text" style="background-color: #d86057">
                    <img src="../../assets/phone.png" style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i></span><b>
                    폰 번호 : {{ loginPhone }}</b>
                </div>
                <div class="justify-content-center text-center">
                  <router-link style="float:left" :to="{ name: 'userupdate' }">
                    <div class="btn btn-primary">회원수정</div>
                  </router-link>

                  <form action="" method="post" style="float:left">
                    <input type="hidden" name="_method" value="delete" />
                    <input type="hidden" name="id" id="id" value="${userinfo.id}" />


                    <div class="btn btn-danger" @click="deleteAccount(loginId)">회원탈퇴 </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</div></template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";
import router from '@/router/index';

export default {
  name: "UserInfo",
  components: {},
  computed: {
    ...mapState(["loginId", "loginName", "loginAddr", "loginPhone", "loginPwd"]),
  },
  data() {
    return {
      message: "",
    };
  },
  created() { },
  methods: {
    ...mapActions(["reset"]),
    deleteAccount: function (loginId) {
      console.log(loginId);
      http.delete('/delete/' + this.loginId).then(() => {
        alert("탈퇴 성공하였습니다.");
        this.reset();
        router.push({ name: 'home' });
      });
    }
  },
};
</script>

<style scoped></style>
