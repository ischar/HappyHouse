import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView'
import SearchView from '../views/SearchView'

import SignUpView from '../views/SignUpView'
import BoardView from '../views/BoardView';
import UserView from '../views/UserView';

import Vuebar from 'vuebar';

import BoardList from '../components/board/BoardList';
import BoardWrite from '../components/board/BoardWrite';
import BoardDetail from '../components/board/BoardDetail';
import BoardModify from '../components/board/BoardModify';
import BoardDelete from '../components/board/BoardDelete';
import UserLogin from '../components/user/UserLogin';

Vue.use(Vuebar);
Vue.use(VueRouter)

const routes = [  
  {
    path: '/',
    alias: ['/index', '/main'],
    name: 'home',
    component: HomeView
  },
  {
    path: '/search',
    name: 'search',
    component: SearchView
  },
  {
    path: '/searchBar',
    name: 'searchBar',
    component: SearchView
  },
  {
    path: '/signup',
    name: 'signup',
    component : SignUpView
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
        component: BoardWrite,
      },
      {
        path: 'view/:articleno',
        name: 'boarddetail',
        component: BoardDetail,
      },
      {
        path: 'modify/:articleno',
        name: 'boardmodify',
        component: BoardModify,
      },
      {
        path: 'delete/:articleno',
        name: 'boarddelete',
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
    ],
  },




]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
