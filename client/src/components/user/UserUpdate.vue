<template>
  <div>
    <section class="py-5 text-center container">
      <div class="container-fluid vh-100" style="margin-top: 100px">
        <div class="" style="margin-top: 200px">
          <div class="rounded d-flex justify-content-center">
            <div
              class="col-md-4 col-sm-12 shadow-lg p-5 bg-light"
              style="width: 600px">
              <div class="text-center">
                <h3 class="text-dark">마이페이지</h3>
              </div>
              <div class="p-7">
                <!-- id -->
                <div class="input-group mb-3">
                  <span
                    class="input-group-text"
                    style="background-color: #929292">
                    <img
                      src="/img/user.png"
                      style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i
                  ></span>
                  <!--<input
                    id="id"
                    type="text"
                    class="form-control"
                    value="${ loginName }"
                    placeholder="${userinfo.id}"
                    disabled >{{ loginName }}</input>-->
                  아이디 : {{ loginId }}
                </div>

                <!-- 비밀번호 -->
                <div class="input-group mb-3">
                  <span
                    class="input-group-text"
                    style="background-color: #929292">
                    <img
                      src="/img/lock.png"
                      style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i
                  ></span>
                  비밀번호 :
                  <input v-model="updatedPwd" :placeholder="loginPwd" />
                </div>

                <!-- 이름 -->
                <div class="input-group mb-3">
                  <span
                    class="input-group-text"
                    style="background-color: #929292">
                    <img
                      src="/img/name.png"
                      style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i
                  ></span>
                  이름 :
                  <input v-model="updatedName" :placeholder="loginName" />
                </div>

                <!-- 주소 -->
                <div class="input-group mb-3">
                  <span
                    class="input-group-text"
                    style="background-color: #929292">
                    <img
                      src="/img/address.png"
                      style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i
                  ></span>
                  주소 :
                  <input v-model="updatedAddr" :placeholder="loginAddr" />
                </div>

                <div class="input-group mb-3">
                  <span
                    class="input-group-text"
                    style="background-color: #929292">
                    <img
                      src="/img/phone.png"
                      style="width: 20px; height: 20px" />
                    <i class="bi bi-person-plus-fill text-white"></i
                  ></span>
                  휴대폰 번호 :
                  <input v-model="updatedPhone" :placeholder="loginPhone" />
                </div>

                <div @click="updateAccount()">수정</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  <!-- </div> -->
</template>

<script>
import {mapState} from "vuex";
import http from "@/api/http";
import router from "@/router/index";

export default {
  name: "UserUpdate",
  components: {},
  computed: {
    ...mapState([
      "loginId",
      "loginPwd",
      "loginName",
      "loginAddr",
      "loginPhone",
    ]),
  },
  data() {
    return {
      message: "",
      updatedName: "",
      updatedAddr: "",
      updatedPhone: "",
      updatedPwd: "",
    };
  },
  created() {
    console.log("ㅠ");
    (this.updatedName = this.loginName),
      (this.updatedAddr = this.loginAddr),
      (this.updatedPhone = this.loginPhone),
      (this.updatedPwd = this.loginPwd);
  },
  methods: {
    updateAccount: function () {
      const member = {
        id: this.loginId,
        pwd: this.updatedPwd,
        name: this.updatedName,
        address: this.updatedAddr,
        phone: this.updatedPhone,
      };
      console.log("들어와");

      http.put("/update", member).then(() => {
        alert("수정 성공하였습니다.");
        //this.reset();

        this.$store.commit("SET_NAME", member.name);
        this.$store.commit("SET_ADDR", member.address);
        this.$store.commit("SET_PHONE", member.phone);
        this.$store.commit("SET_PWD", member.pwd);
        router.push({name: "userinfo"});
      });
    },
  },
};
</script>

<style scoped></style>
