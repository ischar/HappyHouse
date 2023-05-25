<template>
  <section class="py-5 text-center container">
    <div class="container-fluid vh-100" style="margin-top: 150px">
      <div class="" style="margin-top: 100px">
        <div class="rounded d-flex justify-content-center">
          <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
            <div class="text-center">
              <h3 class="text-dark">Sign In</h3>
            </div>
            <div class="p-4">
              <!--<input type="hidden" name="action" />-->
              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292">
                  <img src="../../assets/users.png" style="width: 20px; height: 20px" /><i
                    class="bi bi-person-plus-fill text-white"></i></span>
                <input id="userid" type="text" class="form-control" placeholder="ID" name="id" v-model="id" />
              </div>
              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292"><img src="../../assets/lock.png"
                    style="width: 20px; height: 20px" /><i class="bi bi-key-fill text-white"></i></span>
                <input id="userpwd" type="password" class="form-control" placeholder="password" name="pwd"
                  v-model="pwd" />
              </div>
              <button id="submitBtn" class="btn btn-primary text-center mt-2" @click="loginSubmit"
                style="background-color: #d86057">
                Login
              </button>

              <div class="justify-content-center">
                <p style="color:#828282 font-size:12px;" class="text-center mt-5">
                  아이디가 없나요?
                  <router-link :to="{ name: 'signup' }">회원가입</router-link>
                </p>
                <div class="justify-content-center">
                  <p style="color:#828282 font-size:12px; float:left;" class="text-center">
                    비밀번호가 기억나지 않나요?
                  </p>
                  <router-link style="float:left;" :to="{ name: 'passwordfind' }">비밀번호찾기</router-link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
//import axios from "@/api/http";
import { mapActions } from "vuex";

export default {
  name: "UserLogin",
  components: {},
  data() {
    return {
      message: "",
      id: "",
      pwd: "",
    };
  },
  created() { },
  methods: {
    ...mapActions(["login"]),
    loginSubmit() {
      let saveData = {};
      saveData.id = this.id;
      saveData.pwd = this.pwd;
      this.$store.dispatch("login", saveData);
    },
  },
};
</script>

<style scoped></style>
