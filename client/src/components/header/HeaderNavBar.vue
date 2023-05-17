<template>
  <header>
    <nav class="header-nav">
      <nav
        class="navbar navbar-light navbar-expand-lg"
        style="
          background: rgba(255, 255, 255, 0.2);
          box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
          backdrop-filter: blur(5px);
          -webkit-backdrop-filter: blur(5px);
          border: 1px solid rgba(255, 255, 255, 0.3);
        ">
        <div class="container-fluid">
          <a class="navbar-brand text-black" href="/">Home</a>
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
                <a
                  class="nav-link active text-black"
                  aria-current="page"
                  href="${root}"
                  >오늘의 뉴스</a
                >
              </li>

              <li class="nav-item">
                <router-link to="/board">공지사항</router-link>
              </li>

              <div>
                <router-link :to="{name: 'userinfo'}">
                  <li class="nav-item">
                    <a
                      class="nav-link active text-black"
                      href="${root }/member/memberInsert.jsp"
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
                  id="login"
                  @click="moveLogin"
                  v-if="!isLogin">
                  로그인
                </button>
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                  id="logout"
                  @click="onClickLogout"
                  v-else>
                  로그아웃
                </button>
              </div>
              <router-link :to="{name: 'signup'}">
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
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
    ...mapActions(["logout"]),
    moveLogin() {
      console.log(this.$route.path + " vs " + "/user/login");
      console.log("로그인...");
      console.log(login);
      if (this.$route.path != "/user/login")
        this.$router.push({name: "userlogin"});
    },
    onClickLogout() {
      console.log("로그아웃");
      this.logout();
      console.log(this.isLogin);
      this.$store.dispatch("logout");
    },
  },
};
</script>
