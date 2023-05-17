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
        "
      >
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
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active text-black" aria-current="page" href="${root}"
                  >오늘의 뉴스</a
                >
              </li>

              <li class="nav-item"><router-link to="/board">공지사항</router-link></li>

              <div>
                <li class="nav-item">
                  <a
                    class="nav-link active text-black"
                    href="${root }/member/memberInsert.jsp"
                    id="mypage"
                    >마이페이지</a
                  >
                </li>
              </div>
            </ul>
            <ul class="navbar-nav mb-lg-0">
              <div>
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                  id="login"
                  @click="moveLogin"
                >
                  로그인
                </button>

                <router-link :to="{ name: 'signup' }">
                  <button
                    class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                    type="submit"
                    id="logout"
                  >
                    회원가입
                  </button>
                </router-link>
              </div>

              <div>
                <button
                  class="btn btn-default btn-sm btn-outline-black btn-lg col-auto m-2"
                  type="submit"
                  id="logout"
                >
                  로그아웃
                </button>
              </div>
            </ul>
          </div>
        </div>
      </nav>
    </nav>
  </header>
</template>

<script>
export default {
  name: "HeaderNavBar",
  data() {
    return {
      username: {
        message: decodeURIComponent(),
      },
    };
  },
  methods: {
    moveLogin() {
      console.log(this.$route.path + " vs " + "/user/login");
      if (this.$route.path != "/user/login") this.$router.push({ name: "userlogin" });
    },
  },
};
</script>
