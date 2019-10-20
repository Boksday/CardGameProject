<template>
  <div class="gameWrapper">
    <h1>카드게임</h1>
    <v-btn @click="startGame">시작</v-btn>
    <div v-if="started">
      <div class="cardWrapper"  v-for="n in cards.length/6" :key="n">
        <card-component v-for="(card,index) in cards.slice(n*6-6,n*6)" :card="card" :key="index"></card-component>
      </div>
    </div>
    <div>시도횟수 : {{tries}}</div>
    <div>초과시간 : {{timer}}</div>

    <div v-if="success">{{tries}}회 도전, {{timer}}초 만에 성공하셨습니다.</div>
  </div>
</template>

<script>
import cardImgs from '../utils/CardImgVar'
import CardComponent from '../components/CardComponent2.vue'
import { mapActions, mapState, mapMutations } from 'vuex'
export default {
  components: {
    CardComponent
  },
  data () {
    return {
      timer: 0,
      timeout: null,
      interval: null,
      cardImg: []
    }
  },
  computed: {
    ...mapState({
      tries: (store) => store.card.tries,
      user: (store) => store.user.user, // 유저 정보
      cards: (store) => store.card.cards, // 렌더링할 카드들
      completeCards: (store) => store.card.completeCards, // 완성된 카드들 숫자
      stackCards: (store) => store.card.stackCards, // 현재 선택한 카드들
      started: (store) => store.card.started, // 시작했나
      allToggleMode: (store) => store.card.allToggleMode // 전체를 선택할 수 있는 때인지
    }),

    // 게임 완료조건
    success () {
      if (this.completeCards === 12) {
        clearInterval(this.interval)
        this.addRecord({
          time: this.timer,
          tries: this.tries,
          id: this.user.id
        })
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    ...mapActions(['addRecord']),
    ...mapMutations(['pushCard', 'setCompleteCard', 'toggleStarted', 'clearCards', 'setCardImg', 'spliceCardImg', 'clearTries']),
    startGame () {
      this.setCompleteCard(0)
      this.timer = 0

      clearInterval(this.interval)
      this.interval = setInterval(() => {
        this.timer++
      }, 1000)
      this.clearTries()
      this.toggleStarted()
      this.clearCards()

      this.cardImg = cardImgs

      for (let i = 0; i < 24; i++) {
        this.pushCard({
          cardImg: this.cardImg.splice(Math.ceil(Math.random() * this.cardImg.length) - 1, 1)[0],
          index: i,
          flipped: false,
          toggleMode: true
        })
      }
    }

  },
  beforeDestroy () {
    clearTimeout(this.timeout)
    clearInterval(this.interval)
  }

}
</script>

<style scoped>
  .gameWrapper{
    padding: 30px;
  }
</style>
