import Vue from 'vue'
import Vuex from 'vuex'

import card from './modules/card/index.js'
import user from './modules/user/index.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    card,
    user
  }
})
