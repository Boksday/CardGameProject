export default {
  setRecords (state, payload) {
    state.records = payload
  },
  pushCard (state, payload) {
    state.cards.push(payload)
  },
  pushStackCard (state, payload) {
    state.stackCards.push(payload)
  },
  clearStackCard (state) {
    state.stackCards = []
  },
  setCompleteCard (state, payload) {
    state.completeCards = payload
  },
  toggleStarted (state) {
    state.started = !state.started
  },
  clearCards (state) {
    state.cards = []
  },
  setCardImg (state, payload) {
    state.cardImg.push(payload)
  },
  plusCompleteCards (state) {
    state.completeCards++
  },
  clearCompleteCards (state) {
    state.completeCards = 0
  },
  toggleAllToggleMode (state, payload) {
    state.allToggleMode = payload
  },
  clearTries (state) {
    state.tries = 0
  },
  plusTries (state) {
    state.tries++
  }

}
