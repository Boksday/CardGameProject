<template>
    <div  class="game-card" >
        <div class="card-inner">
            <div :class="filpCheck" @click="cardFlip" :style="{background: classCheck}"></div>
        </div>
    </div>
</template>

<script>
export default {
  props: ['card'],
  computed: {
    filpCheck () {
      return this.card.flipped === true ? 'card-front' : 'card-back'
    },
    classCheck () {
      if (this.card.flipped) {
        return this.card.color
      } else {
        return 'black'
      }
    }
  },
  methods: {
    cardFlip () {
      const parent = this.$parent
      if (!this.card.toggleMode) return

      if (!parent.allToggleMode) return
      this.card.flipped = !this.card.flipped
      this.card.toggleMode = false
      parent.stackCards.push(this.card)

      if (parent.stackCards.length > 1) {
        parent.tries++
        if (parent.stackCards[0].color === parent.stackCards[1].color) {
          parent.completeCards++
          parent.stackCards = []
        } else {
          parent.allToggleMode = false

          var index1stackCard = parent.stackCards[0].index
          var index2stackCard = parent.stackCards[1].index
          parent.timeout = setTimeout(() => {
            parent.cards[index1stackCard].flipped = false
            parent.cards[index2stackCard].flipped = false
            parent.cards[index1stackCard].toggleMode = true
            parent.cards[index2stackCard].toggleMode = true
            parent.allToggleMode = true
          }, 800)

          parent.stackCards = []
        }
      }
    }
  }

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
