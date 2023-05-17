import Vue from 'vue';
import Vuex from 'vuex';
import axios from '@/api/http';
import router from '@/router/index';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false,
    loginId : "",
    loginPwd: "",
    loginName: "",
    loginAddr: "",
    loginPhone : "",
  },
  getters: {},
  mutations: { // 동기처리만
    GET_ID(state,playload){
      state.loginId = playload;
    },
    GET_PWD(state,playload){
      state.loginPwd = playload;
    },
    IS_LOGIN(state){
      state.isLogin = true;
    },
  },



  actions: {
    login({commit},context){
      try {
        axios
          .post("/login", JSON.stringify(context), {
            headers: {
              "Content-Type": `application/json`,
            },
          })
          .then(res => {
            if (res.status === 200) {
              // 로그인 성공시 처리해줘야할 부분
              console.log("성공이용");
              console.log(res);
              console.log(res.data.id);
              commit("GET_ID", res.data.id);
              commit("GET_PWD", res.data.pwd);
              commit("IS_LOGIN");
              router.push({name:'home'});
            }
          })
          .catch(error => {
            if (error.response.status === 401) {
              // 401 오류인 경우 알림 창 띄우기
              alert("아이디 혹은 비밀번호가 틀렸습니다.");
            }
          });
      } catch (error) {
        console.error(error);
      }
    }
  },
  modules: {},
});
