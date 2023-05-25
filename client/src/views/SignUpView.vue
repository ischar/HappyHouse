<template>
  <section class="py-5 text-center container">
    <div class="container-fluid vh-100" style="margin-top: 100px">
      <div class="" style="margin-top: 100px">
        <div class="rounded d-flex justify-content-center">
          <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
            <div class="text-center">
              <h3 class="text-dark">Sign Up</h3>
            </div>
            <div class="p-4">
              <input type="hidden" />
              <!-- id -->
              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292"><img src="../assets/users.png"
                    style="width: 20px; height: 20px" /><i class="bi bi-person-plus-fill text-white"></i></span>
                <input v-model="userid" id="id" type="text" class="form-control" placeholder="아이디" name="id" />
                <div>
                  <p v-if="idOk">아이디를 사용할 수 있습니다.</p>
                  <p v-else>5자 이상 10자 이하로 입력하세요.</p>
                </div>
                <button class="btn btn-primary sm" @click="checkId">중복확인</button>
              </div>

              <!-- password-->
              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292"><img src="../assets/lock.png"
                    style="width: 20px; height: 20px" /><i class="bi bi-key-fill text-white"></i></span>
                <input v-model="userpwd" id="pwd" type="password" class="form-control" placeholder="비밀번호" name="pwd" />
                <label v-if="pwdOk"> 비밀번호를 사용할 수 있습니다. </label>
                <label v-else> 5자 이상 10자 이하로 입력하세요. </label>
              </div>
              <!-- 이름 -->
              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292"><img src="../assets/name.png"
                    style="width: 20px; height: 20px" /><i class="bi bi-person-plus-fill text-white"></i></span>
                <input v-model="username" @change="ChangeNameValue" id="name" type="text" class="form-control"
                  placeholder="이름" name="name" />
              </div>

              <!-- 주소 -->
              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292">
                  <img src="../assets/address.png" style="width: 20px; height: 20px" /><i
                    class="bi bi-person-plus-fill text-white"></i></span>
                <input v-model="useraddress" @change="ChangeAddressValue" id="address" type="text" class="form-control"
                  placeholder="주소" name="address" />
              </div>

              <div class="input-group mb-3">
                <span class="input-group-text" style="background-color: #929292"><img src="../assets/phone.png"
                    style="width: 20px; height: 20px" /><i class="bi bi-person-plus-fill text-white"></i></span>
                <input v-model="userphone" @change="ChangePhoneValue" id="phone" type="tel" class="form-control"
                  title="전화번호를 입력하세요." placeholder="전화번호" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13"
                  name="phone" />
                <!-- <input id="phoneNum" type="text" class="form-control" placeholder="전화번호"> -->
              </div>

              <button @click="signup" type="submit" id="submitBtn" class="btn btn-primary text-center mt-2">
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import axios from "axios";
import http from "@/api/http";
export default {
  name: "SignUpView",
  props: {
    msg: String,
  },
  data() {
    return {
      userid: "",
      userpwd: "",
      username: "",
      useraddress: "",
      userphone: "",
      idOk: false,
      pwdOk: false,
      nameOk: false,
      phoneOk: false,
      addressOk: false,
    };
  },

  watch: {
    userid: function () {
      if (this.userid.length >= 5 && this.userid.length <= 10) {
        this.idOk = true;
      } else {
        this.idOk = false;
      }
    },
    userpwd: function () {
      if (this.userpwd.length >= 5 && this.userpwd.length <= 10) {
        this.pwdOk = true;
      } else {
        this.pwdOk = false;
      }
    },
  },

  methods: {
    signup() {
      if (
        this.idOk == true &&
        this.pwdOk == true &&
        this.nameOk == true &&
        this.phoneOk == true &&
        this.addressOk == true
      ) {
        axios({
          method: "post",
          url: "http://localhost:80/signup",
          responseType: "json",

          data: {
            id: this.userid,
            pwd: this.userpwd,
            name: this.username,
            address: this.useraddress,
            phone: this.userphone,
          },
        }).then(() => {
          this.$router.push({ name: "home" });
        });
      } else {
        console.log(
          this.idOk +
          " " +
          this.pwdOk +
          " " +
          this.nameOk +
          " " +
          this.addressOk +
          " " +
          this.phoneOk
        );
        alert("제대로 입력하세요!");
      }
    },

    ChangeNameValue() {
      if (this.username.length != 0) {
        this.nameOk = true;
      } else {
        this.nameOk = false;
      }
    },

    ChangePhoneValue() {
      if (this.userphone.length != 0) {
        this.phoneOk = true;
      }
    },
    ChangeAddressValue() {
      if (this.useraddress.length != 0) {
        this.addressOk = true;
      } else {
        this.addressOk = false;
      }
    },
    checkId() {
      http.get(`http://localhost:80/checkid/${this.userid}`).then(response => {
        console.log("응답");
        console.log(response);
        if (response) {
          alert("아이디를 사용할 수 있습니다.");
        } else {
          alert("아이디를 사용할 수 없습니다.");
        }
      });
    },
  },
};
</script>
