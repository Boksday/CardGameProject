import UserApi from '../../axios/MembersInfo'
export default {
  userLogin (context, payload) {
    return UserApi.loginValidCheck(payload)
      .then((res) => {
        if (res.data) {
          let user = { id: res.data.id, nickName: res.data.nickName, role: res.data.role }
          context.commit('setUser', user)
          window.sessionStorage.setItem('loginUser', JSON.stringify(user))
          return true
        } else {
          alert('아이디가 없거나 비밀번호가 틀립니다.')
          return false
        }
      })
      .catch((e) => {
        alert('아이디가 없거나 비밀번호가 틀립니다.')
        return false
      })
  },
  loadSession (context, payload) {
    let loginUser = sessionStorage.getItem('loginUser')
    if (loginUser) {
      context.commit('setSession', JSON.parse(loginUser))
    }
  },
  logout (context, payload) {
    context.commit('setSession', {})
    window.sessionStorage.clear()
  },
  insertUser (context, payload) {
    return UserApi.createUser(payload).then((res) => {

    })
  },
  idDuplCheck (context, payload) {
    return UserApi.idCheck(payload).then((res) => {
      return res.data
    })
  }
}
