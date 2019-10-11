<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
    >
      <v-list dense>
        <div class="nav-item" v-for="(item,index) in items" :key="index">
              <v-list-item @click="goRouter(item.to)">
                  <v-icon>{{item.icon}}</v-icon>{{item.text}}
              </v-list-item>
        </div>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="black darken-3"
      dark
    >
      <v-toolbar-title
        style="width: 300px"
        class="ml-0 pl-4"
      >

        <span class="hidden-sm-and-down"><v-icon>android</v-icon> 카드게임</span>

      </v-toolbar-title>
      <div class="flex-grow-1"></div>

      <span v-if="user.nickName">{{user.nickName}}님 환영합니다</span>
      <span v-else> 로그인 정보가 없습니다.</span>
      <v-btn icon v-if="!user.nickName" @click="goRouter('/signin')">
         <v-icon>person_add</v-icon>
      </v-btn>
      <v-btn icon v-if="!user.nickName" @click="goRouter('/login')">
         <v-icon>power_settings_new</v-icon>
      </v-btn>
      <v-btn icon v-else @click="logoutUser">
         <v-icon>power_settings_new</v-icon>
      </v-btn>

    </v-app-bar>

    <v-content class="content">

      <router-view></router-view>

    </v-content>

    <v-dialog
      v-model="dialog"
      width="800px"
    >
      <v-card>

      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
import router from './router.js'
import { mapState, mapActions } from 'vuex'
export default {

  props: {
    source: String
  },

  data: () => ({
    dialog: false,
    drawer: null,
    items: [
      { icon: 'mdi-home', text: ' Home', to: '/' },
      { icon: 'mdi-gamepad', text: ' 게임하기', to: '/game' },
      { icon: 'emoji_events', text: ' 랭킹', to: '/rank' }
    ]

  }),
  computed: {
    ...mapState({
      user: (store) => store.user.user
    })
  },
  methods: {
    ...mapActions(['loadSession', 'logout']),
    goRouter (path) {
      router.push({ path: path })
    },
    logoutUser () {
      if (confirm('정말 로그아웃?')) {
        this.logout().then((res) => {
          router.replace('/login')
        })
      }
    }
  },
  created () {
    this.loadSession()
  }

}
</script>
<style>
  .nav-item {
    font-size:20px;
    margin:15px;
  }
  .nav-item:hover {
    background-color: #e2e2e2
  }
  .content{
    background-color: #e1e1e1
  }
</style>
