<template>
    <div class="flip-card">
      <div class="flip-card-inner" :class="filpCheck" @click="cardFlip">
        <div class="flip-card-front">
        </div>
        <div class="flip-card-back">
          <div class="card" :style="{background: card.cardImg}"></div>
        </div>
      </div>
    </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex'
export default {
  props: {
    card: {
      type: Object,
      required: true
    }
  },
  computed: {
    ...mapState({
      cards: (store) => store.card.cards,
      completeCards: (store) => store.card.completeCards,
      stackCards: (store) => store.card.stackCards,
      allToggleMode: (store) => store.card.allToggleMode,
      tries: (store) => store.card.tries
    }),

    filpCheck () {
      return this.card.flipped === true ? 'selected' : ''
    },
    classCheck () {
      if (this.card.flipped) {
        return this.card.cardImg
      } else {
        return 'black'
      }
    }
  },
  methods: {
    ...mapActions(['']),
    ...mapMutations(['pushStackCard', 'clearStackCard', 'plusCompleteCards', 'clearCompleteCards', 'toggleAllToggleMode', 'plusTries']),
    cardFlip () {
      if (!this.card.toggleMode) return
      if (!this.allToggleMode) return

      this.card.flipped = !this.card.flipped
      this.card.toggleMode = false

      this.pushStackCard(this.card)

      if (this.stackCards.length <= 1) return

      this.plusTries()

      if (this.stackCards[0].cardImg === this.stackCards[1].cardImg) {
        this.plusCompleteCards()
        this.clearStackCard()
      } else {
        this.toggleAllToggleMode(false)

        var index1stackCard = this.stackCards[0].index
        var index2stackCard = this.stackCards[1].index
        parent.timeout = setTimeout(() => {
          this.cards[index1stackCard].flipped = false
          this.cards[index2stackCard].flipped = false
          this.cards[index1stackCard].toggleMode = true
          this.cards[index2stackCard].toggleMode = true
          this.toggleAllToggleMode(true)
        }, 800)

        this.clearStackCard()
      }
    }
  }

}
</script>

<style scoped>
.flip-card {
  display: inline-block;
  background-color: transparent;
  width: 70px;
  height: 100px;
  border: 1px solid #f1f1f1;
  perspective: 1000px; /* Remove this if you don't want the 3D effect */
  margin: 5px;
}

/* This container is needed to position the front and back side */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

/* Do an horizontal flip when you move the mouse over the flip box container */
.selected {
  transform: rotateY(180deg);
}

/* Position the front and back side */
.flip-card-front, .flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
}

/* Style the front side (fallback if image is missing) */
.flip-card-front {
  background-color: #bbb;
  color: black;
}

/* Style the back side */
.flip-card-back {
  background-color: dodgerblue;
  color: white;
  transform: rotateY(180deg);
}

.card {
  width: 70px;
  height: 100px;
}
</style>
