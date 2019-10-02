<template>
  <div class="about">
    <h1>카드게임</h1>
    <v-btn @click="startGame">시작</v-btn>

    <div v-if="started" v-for="n in cards.length/6">
      
      <div  class="game-card" v-for="(card,index) in cards.slice(n*6-6,n*6)" @click="cardFlip(card)" :key="index" >
        <div class="card-inner">
          <div :class="filpCheck(card)" :style="{background: classCheck(card)}"></div>
        </div>
      </div>
    </div>

    <div>시도횟수 : {{tries}}</div>
    <div>초과시간 : {{timer}}</div>

    <div v-if="success">{{tries}}회 도전, {{timer}}초 만에 성공하셨습니다.</div>
  </div>
</template>

<script>

export default {
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
    filpCheck() {
      return (card) =>{
        return card.flipped == true ? 'card-front' : 'card-back'
      }
    },
    classCheck() {
      return (card)=>{
        if(card.flipped){
          return card.color
        }else{
          return 'black';
        }
      }
    },
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
      this.colors = ['url(http://cfile215.uf.daum.net/image/201711344F0607D221B068)',
                     'url(http://cfile215.uf.daum.net/image/201711344F0607D221B068)',

                     'url(http://cfile218.uf.daum.net/image/125C60384F06082E27B007)',
                     'url(http://cfile218.uf.daum.net/image/125C60384F06082E27B007)',

                     'url(http://cfile208.uf.daum.net/image/1978413C4F0608541BAA1B)',
                     'url(http://cfile208.uf.daum.net/image/1978413C4F0608541BAA1B)',

                     'url(http://cfile214.uf.daum.net/image/1125884E4F060AC71B4E74)',
                     'url(http://cfile214.uf.daum.net/image/1125884E4F060AC71B4E74)',

                     'url(http://cfile239.uf.daum.net/image/144C9B4D4F060AE31ABB4C)',
                     'url(http://cfile239.uf.daum.net/image/144C9B4D4F060AE31ABB4C)',

                     'url(http://cfile204.uf.daum.net/image/1217C04F4F060AFC1945E7)',
                     'url(http://cfile204.uf.daum.net/image/1217C04F4F060AFC1945E7)',

                     'url(http://cfile226.uf.daum.net/image/1753F44C4F060BDE194B18)',
                     'url(http://cfile226.uf.daum.net/image/1753F44C4F060BDE194B18)',

                     'url(http://cfile217.uf.daum.net/image/186F4C4A4F060BA72148D5)',
                     'url(http://cfile217.uf.daum.net/image/186F4C4A4F060BA72148D5)',

                     'url(http://cfile207.uf.daum.net/image/136814474F060B922EFF12)',
                     'url(http://cfile207.uf.daum.net/image/136814474F060B922EFF12)',

                     'url(http://cfile204.uf.daum.net/image/11177C4F4F060B7A1A017D)',
                     'url(http://cfile204.uf.daum.net/image/11177C4F4F060B7A1A017D)',

                     'url(http://cfile210.uf.daum.net/image/1859DF494F060B172092C1)',
                     'url(http://cfile210.uf.daum.net/image/1859DF494F060B172092C1)',

                     'url(http://cfile201.uf.daum.net/image/1127494E4F060B5F1ABF83)',
                     'url(http://cfile201.uf.daum.net/image/1127494E4F060B5F1ABF83)',
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
    cardFlip(c) {
      if(!c.toggleMode) return;
      if(!this.allToggleMode) return;
      c.flipped = !c.flipped
      c.toggleMode = false
      this.stackCards.push(c)
      
      if(this.stackCards.length > 1){
        this.tries++
        if(this.stackCards[0].color === this.stackCards[1].color){
          console.log('정답')
          this.completeCards++;
          this.stackCards = []
        }else{
          this.allToggleMode = false;
          console.log('땡')
          console.log(this.stackCards[0].flipped)
          
          'use strict'
          var index1stackCard = this.stackCards[0].index;
          var index2stackCard = this.stackCards[1].index;
          this.timeout = setTimeout(() => {
            this.cards[index1stackCard].flipped=false;
            this.cards[index2stackCard].flipped=false;

            this.cards[index1stackCard].toggleMode=true;
            this.cards[index2stackCard].toggleMode=true;
            this.allToggleMode=true
          }, 1200);


          this.stackCards = []

        }
      }
    },

  },
  beforeDestroy() {
    clearTimeout(this.timeout)
    clearInterval(this.interval)
  },

}
</script>

<style scoped>
  .game-card{
    display: inline-block;
    width : 70px;
    height : 100px;
    margin:20px;
    perspective: 140px;
  }
  .card-inner{
    position : relative;
    width : 100%;
    height : 100%;
    text-align : center;
    transition : transform 0.8s;
    transform-style : preserve-3d;
  }
  .card.flipped .card-inner{
    transform : rotateY(180deg);
  }

  .card-front, .card-back {
    position : absolute;
    width : 100%;
    height: 100%;
    border : 1px solid black;
  }
  .card-front{
    background:navy;
  }

  .card-back {
    transform : rotateY(180deg);
    background:tomato;
  }
</style>