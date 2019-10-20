export default {
  user: (state) => {
    let USER = {
      id: state.user.id,
      role: state.user.role,
      nickName: state.user.nickName
    }
    return USER
  }
}
