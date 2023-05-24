<template>
  <header>
    <nav class="header-nav">
      <nav
        class="navbar navbar-light navbar-expand-lg"
        style="background-color: white; margin: 0">
        <div class="container-fluid" style="margin: 0">
          <a class="navbar-brand text-black" href="/">
            <img
              src="../../assets/logo.png"
              style="height: 80px; weight: 80px; margin: 0" />
          </a>
          <button
            class="navbar-toggler"
            style="border-color: black"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <b
                  ><a
                    class="nav-link active text-black"
                    aria-current="page"
                    href="${root}"
                    style="font-size: 18px"
                    >오늘의 뉴스</a
                  ></b
                >
              </li>
                <li class="nav-item" style="font-size: 18px; font-weight: 700">
                  <router-link class="nav-link active text-black"
                    style="text-decoration: none; color: black"
                    to="/board"
                    >공지사항</router-link
                  >
                </li>
              <div>
                <router-link
                  style="text-decoration: none"
                  :to="{name: 'userinfo'}">
                  <li class="nav-item" style="padding: 0px; margin:0px;">
                    <a
                      class="nav-link active text-black text-decoration-none"
                      style="
                        text-decoration: none;
                        font-size: 18px;
                        font-weight: 700;
                      "
                      id="mypage"
                      >마이페이지</a
                    >
                  </li>
                </router-link>
              </div>
            </ul>
            <div v-if="isLogin">{{ loginName }} 님 환영합니다.</div>
            <ul class="navbar-nav mb-lg-0">
              <div>
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                  style="font-size: 18px; font-weight: 700"
                  id="login"
                  @click="moveLogin"
                  v-if="!isLogin">
                  로그인
                </button>
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                  style="font-size: 18px; font-weight: 700"
                  id="logout"
                  @click="onClickLogout"
                  v-else>
                  로그아웃
                </button>
              </div>
              <router-link :to="{name: 'signup'}">
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                  style="font-size: 18px; font-weight: 700"
                  type="submit"
                  id="logout">
                  회원가입
                </button>
              </router-link>
            </ul>
          </div>
        </div>
      </nav>
    </nav>
  </header>
</template>

<script>
import {mapState, mapActions} from "vuex";

export default {
  name: "HeaderNavBar",
  data() {
    return {
      username: {
        message: decodeURIComponent(),
      },
    };
  },
  computed: {
    ...mapState(["isLogin", "loginName"]),
  },
  methods: {
    ...mapActions(["reset"]),
    moveLogin() {
      console.log(this.$route.path + " vs " + "/user/login");
      console.log("로그인...");
      if (this.$route.path != "/user/login")
        this.$router.push({name: "userlogin"});
    },
    onClickLogout() {
      console.log("로그아웃");
      this.reset();

      //this.$store.dispatch("logout");
    },
  },
};
</script>
