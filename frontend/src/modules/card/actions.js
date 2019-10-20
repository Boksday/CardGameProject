import RecordAPI from '../../axios/RecordInfo'
export default {
  addRecord (context, payload) {
    RecordAPI.insertRecord(payload).then((res) => {
      alert('모두 완성하셨습니다. 축하합니다!')
    })
  },
  selectRecordsByTime (context, payload) {
    RecordAPI.selectRecordsByTime().then((res) => {
      context.commit('setRecords', res.data)
    })
  },
  selectRecordsByTries (context, payload) {
    RecordAPI.selectRecordsByTries().then((res) => {
      context.commit('setRecords', res.data)
    })
  },
  personalRecord (context, payload) {
    return RecordAPI.personalRecord(payload).then((res) => {
      return res
    })
  }
}
