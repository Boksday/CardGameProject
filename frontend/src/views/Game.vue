<template>
  <div class="about">
    <h1>카드게임</h1>
    <v-btn @click="startGame">시작</v-btn>

    <div class="cardWrapper" v-if="started" v-for="n in cards.length/6">
      <card-component v-for="(card,index) in cards.slice(n*6-6,n*6)" :card="card" :key="index"></card-component>
    </div>


    <div>시도횟수 : {{tries}}</div>
    <div>초과시간 : {{timer}}</div>

    <div v-if="success">{{tries}}회 도전, {{timer}}초 만에 성공하셨습니다.</div>
  </div>
</template>

<script>
import CardImgConst from '../utils/CardImgConst.js'
import CardComponent from '../components/CardComponent.vue'
export default {
  components:{
    CardComponent
  },
  data() {
    return{
      cards: [],
      colors: [],
      started: false,
      stackCards: [],
      allToggleMode: true,
      tries:0,
      timer:0,
      timeout:null,
      interval:null,
      completeCards: 0,
    }
  },
  computed: {

    success() {
      if( this.completeCards === 12 ){
        clearInterval(this.interval)
        return true;
      }else{
        return false;
      }
    }
  },
  methods: {
    
    startGame() {
      this.completeCards = 0;
      this.timer=0;

      clearInterval(this.interval)
      this.interval = setInterval(() => {
        this.timer++;
      }, 1000);
      this.tries=0;
      this.started = true;
      this.cards =[]
      this.colors = [CardImgConst.JANUARY,
                     CardImgConst.JANUARY,
                     CardImgConst.FEBRUARY,
                     CardImgConst.FEBRUARY,
                     CardImgConst.MARCH,
                     CardImgConst.MARCH,
                     CardImgConst.APRIL,
                     CardImgConst.APRIL,
                     CardImgConst.MAY,
                     CardImgConst.MAY,
                     CardImgConst.JUNE,
                     CardImgConst.JUNE,
                     CardImgConst.JULY,
                     CardImgConst.JULY,
                     CardImgConst.AUGUST,
                     CardImgConst.AUGUST,
                     CardImgConst.SEPTEMBER,
                     CardImgConst.SEPTEMBER,
                     CardImgConst.OCTOBER,
                     CardImgConst.OCTOBER,
                     CardImgConst.NOVEMBER,
                     CardImgConst.NOVEMBER,
                     CardImgConst.DECEMBER,
                     CardImgConst.DECEMBER
                     ]

      for(var i=0  ; i < 24 ; i++){
         this.cards.push({color: this.colors.splice(Math.ceil(Math.random() * this.colors.length)-1,1)[0],
                           index: i,
                           flipped: false, 
                           toggleMode: true,
                          })
      }
      console.log(this.cards)
    },
    
  },
  beforeDestroy() {
    clearTimeout(this.timeout)
    clearInterval(this.interval)
  },

}
</script>

<style scoped>

</style>