<template>
    <div class="rankWrapper">
    <div class="title">랭킹</div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">랭킹</th>
            <th class="text-left">ID</th>
            <th class="text-left"><v-btn icon @click="selectRecordsByTime">걸린 시간</v-btn></th>
            <th class="text-left"><v-btn icon @click="selectRecordsByTries">시도 횟수</v-btn></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item,index) in records" :key="index">
            <td>{{ index+1 }} </td>
            <td>{{ item.id }}</td>
            <td>{{ item.time }}</td>
            <td>{{ item.tries}}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
export default {
  created () {
    this.selectRecordsByTime()
  },
  methods: {
    ...mapActions(['selectRecordsByTime', 'selectRecordsByTries'])
  },
  computed: {
    ...mapState({
      records: (store) => store.card.records
    })
  }
}
</script>

<style scoped>
    .rankWrapper{
       padding: 30px;

    }
    .title{
        font-size: 32px !important;
        font-weight: bold;
        margin-bottom: 20px;
    }
</style>
