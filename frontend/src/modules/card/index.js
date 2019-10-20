import actions from './actions'
import mutations from './mutations'
import getters from './getters'

const state = {
  cards: [],
  personalRecord: {},
  records: [],
  completeCards: 0,
  stackCards: [],
  started: false,
  allToggleMode: true,
  tries: 0
}
export default {
  state,
  actions,
  mutations,
  getters
}
