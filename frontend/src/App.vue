<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
    >
      <v-list dense>

        <template v-for="item in items">

              <v-list-item @click="" :to="item.to">
                  <v-icon>{{item.icon}}</v-icon>{{item.text}}
              </v-list-item>

        </template>

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

      <v-btn icon to="signin">
         <v-icon @click="">person_add</v-icon>
      </v-btn>
      <v-btn icon to="login">
         <v-icon @click="">power_settings_new
</v-icon>
      </v-btn>

    </v-app-bar>

    <v-content>

      <router-view> </router-view>

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
  })

}
</script>
