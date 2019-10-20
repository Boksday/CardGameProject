<template>
  <div class="profile">
    <v-card>
      <v-card-title>{{user.nickName}} 님</v-card-title>

      <v-card-text>
        <v-card-actions>최고기록</v-card-actions>
        <div>시도 횟수 : {{record.tries}}</div>
        <div>걸린 시간 : {{record.time}}</div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
export default {
  computed: {
    ...mapState({
      user: (store) => store.user.user
    })
  },
  methods: {
    ...mapActions(['personalRecord'])
  },
  created () {
    this.personalRecord(this.user).then((res) => {
      this.record = res.data
    })
  },
  data () {
    return {
      record: {}
    }
  }
}
</script>
<style>
  .profile{
    margin:50px;
  }
</style>
