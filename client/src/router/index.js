import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/HomeView';
import SearchView from '../views/SearchView';

import SignUpView from '../views/SignUpView';
import BoardView from '../views/BoardView';
import UserView from '../views/UserView';
import NewsView from '../views/NewsView';
import Vuebar from 'vuebar';

import FavoriteDetail from '../components/FavoriteDetail';
import BoardList from '../components/board/BoardList';
import BoardWrite from '../components/board/BoardWrite';
import BoardDetail from '../components/board/BoardDetail';
import BoardModify from '../components/board/BoardModify';
import BoardDelete from '../components/board/BoardDelete';
import UserLogin from '../components/user/UserLogin';
import UserInfo from '../components/user/UserInfo';
import UserUpdate from '../components/user/UserUpdate';

import NewsList from '../components/news/NewsList';
import PasswordFind from '../components/user/PasswordFind'

Vue.use(Vuebar);
Vue.use(VueRouter);

import store from '@/store';

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters['checkUser'];
  console.log(checkUserInfo);
  console.log('로그인 확인');

  if (!checkUserInfo) {
    alert('로그인이 필요한 페이지입니다.');
    // next({ name: "login" });
    router.push({ name: 'userlogin' }).catch(() => {});
  } else {
    next();
  }
};

const routes = [
  {
    path: '/',
    alias: ['/index', '/main'],
    name: 'home',
    component: HomeView,
  },
  {
    path: '/search',
    name: 'search',
    component: SearchView,
  },
  {
    path: '/searchBar',
    name: 'searchBar',
    component: SearchView,
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignUpView,
  },
  {
    path: '/news',
    name: 'news',
    component: NewsView,
  },
  {
    path: '/newslist',
    name: 'newslist',
    component: NewsList,
  },

  {
    path: '/board',
    name: 'board',
    component: BoardView,
    redirect: '/board/list',
    children: [
      {
        path: 'list',
        name: 'boardlist',
        component: BoardList,
      },
      {
        path: 'write',
        name: 'boardwrite',
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: 'view/favorite/:aptCode',
        name: 'favoritedetail',
        beforeEnter: onlyAuthUser,
        component: FavoriteDetail,
      },
      {
        path: 'post/:articleno',
        name: 'boarddetail',
        beforeEnter: onlyAuthUser,
        component: BoardDetail,
      },
      {
        path: 'modify/:articleno',
        name: 'boardmodify',
        beforeEnter: onlyAuthUser,
        component: BoardModify,
      },
      {
        path: 'delete/:articleno',
        name: 'boarddelete',
        beforeEnter: onlyAuthUser,
        component: BoardDelete,
      },
    ],
  },
  {
    path: '/user',
    name: 'user',
    component: UserView,
    children: [
      {
        path: 'login',
        name: 'userlogin',
        component: UserLogin,
      },
      {
        path: 'info',
        name: 'userinfo',
        beforeEnter: onlyAuthUser,
        component: UserInfo,
      },
      {
        path: 'update',
        name: 'userupdate',
        component: UserUpdate,
      },
      {
        path: 'findpassword',
        name:'passwordfind',
        component: PasswordFind,
      }
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
