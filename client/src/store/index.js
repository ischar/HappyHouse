import Vue from 'vue';
import Vuex from 'vuex';
import axios from '@/api/http';
import router from '@/router/index';
import createPersistedState from 'vuex-persistedstate';

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
    SET_ID(state, playload) {
      state.loginId = playload;
    },
    SET_PWD(state, playload) {
      state.loginPwd = playload;
    },
    SET_NAME(state, playload) {
      state.loginName = playload;
    },
    SET_ADDR(state, playload) {
      state.loginAddr = playload;
    },
    SET_PHONE(state, playload) {
      state.loginPhone = playload;
    },
    SET_IS_LOGIN(state, playload) {
      state.isLogin = playload;
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
              commit('SET_ID', res.data.id);
              commit('SET_PWD', res.data.pwd);
              commit('SET_NAME', res.data.name);
              commit('SET_ADDR', res.data.address);
              commit('SET_PHONE', res.data.phone);
              commit('SET_IS_LOGIN', true);
              router.push({ name: 'home' });
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
    },
    logout({ commit }) {
      commit('SET_IS_LOGIN', false);
      commit('SET_ID', null);
      commit('SET_PWD', null);
      commit('SET_NAME', null);
      commit('SET_ADDR', null);
      commit('SET_PHONE', null);
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
