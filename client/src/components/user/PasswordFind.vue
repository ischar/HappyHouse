<template>
  <section class="py-5 text-center container">
    <div class="container-fluid vh-100" style="margin-top: 150px">
      <div class="" style="margin-top: 100px">
        <div class="rounded d-flex justify-content-center">
          <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
            <div class="text-center">
              <h3 class="text-dark">비밀번호 찾기</h3>
            </div>
            <div class="p-4">
              <!--<input type="hidden" name="action" />-->
              <div class="input-group mb-3">
                <span
                  class="input-group-text"
                  style="background-color: #929292">
                  <img src="/img/user.png" style="width: 20px; height: 20px" />
                  <i class="bi bi-person-plus-fill text-white"></i>
                </span>
                <input
                  id="userid"
                  type="text"
                  class="form-control"
                  placeholder="ID"
                  name="id"
                  v-model="id" />
              </div>
              <div class="input-group mb-3">
                <span
                  class="input-group-text"
                  style="background-color: #929292">
                  <img src="/img/user.png" style="width: 20px; height: 20px" />
                  <i class="bi bi-person-plus-fill text-white"></i>
                </span>
                <input
                  id="username"
                  type="text"
                  class="form-control"
                  placeholder="Name"
                  name="name"
                  v-model="name" />
              </div>
              <div class="input-group mb-3">
                <span
                  class="input-group-text"
                  style="background-color: #929292">
                  <img src="/img/phone.png" style="width: 20px; height: 20px" />
                  <i class="bi bi-phone-fill text-white"></i>
                </span>
                <input
                  id="userphone"
                  type="text"
                  class="form-control"
                  placeholder="Phone"
                  name="phone"
                  v-model="phone" />
              </div>
              <button
                id="submitBtn"
                class="btn btn-primary text-center mt-2"
                @click="recoverPassword"
                style="background-color: #d86057">
                비밀번호 찾기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import http from "@/api/http";

export default {
  name: "PasswordFind",
  components: {},
  data() {
    return {
      message: "",
      id: "",
      name: "",
      phone: "",
      result: {},
    };
  },
  created() {},
  methods: {
    recoverPassword() {
      if (this.id === "" || this.name === "" || this.phone === "") {
        alert("Please fill in all the fields.");
        return;
      }
      (this.result = {
        id: this.id,
        name: this.name,
        phone: this.phone,
      }),
        http.post("/search", this.result).then(response => {
          alert("비밀번호는 " + response.data + "입니다.");
          this.$router.push({name: "userlogin"});
        });
    },
  },
};
</script>

<style scoped></style>
