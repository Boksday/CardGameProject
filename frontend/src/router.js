import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

var router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'HOME',
      component: Home
    },
    {
      path: '/game',
      name: 'GAME',
      component: () => import('./views/Game.vue')
    },
    {
      path: '/login',
      name: 'LOGIN',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/signin',
      name: 'SIGN_IN',
      component: () => import('./views/Signin.vue')
    },
    {
      path: '/rank',
      name: 'RANK',
      component: () => import('./views/Rank.vue')
    }
  ]
})
const accessPages = [
  'LOGIN',
  'SIGN_IN'
]
router.beforeEach(function (to, from, next) {
  const session = sessionStorage.getItem('loginUser')

  if (!session && !accessPages.includes(to.name)) {
    next('/login')
 
    alert('로그인이 필요한 페이지입니다.')
  } else {
    next()
  }
})

export default router
