<template>
    <v-container fluid grid-list-md>
        <v-layout>
            <v-flex>
         <center class="display-1 font-weight-bold"> 회원가입</center>

        <v-card class="flex pa-10 ma-10 " xl5>
            <div>

            <v-form
                ref="form"
                lazy-validation
            >
            <div class="idField">
            <v-text-field
                v-model="id"
                label="아이디"
                :error="idCheck"
                hint="4자 이상"
                required
            >

            </v-text-field>
            </div>
            <v-btn
                color="white"
                class="mr-4 checkBtn"
                @click="idValid"
                >
                    중복확인
            </v-btn>

            <v-text-field
                v-model="password"
                type="password"
                name="password"
                label="비밀번호"
                :error="pwdCheck"
                hint="숫자+문자+특수문자 8글자 이상"
                required
                ></v-text-field>
            <v-text-field
                v-model="passwordCheck"
                type="password"
                name="password"
                :error="pwdValidCheck"
                label="비밀번호 확인"
            ></v-text-field>
            <v-text-field
                v-model="nickName"
                label="닉네임"
                required
            ></v-text-field>
            <v-btn
            color="white"
            class="mr-4"
            @click="createUser"
            >
                가입
            </v-btn>
            </v-form>
        </div>
        </v-card>
        </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import sha256 from 'sha256'
import { mapActions } from 'vuex'
import router from '../router'
export default {
  data () {
    return {
      id: '',
      password: '',
      passwordCheck: '',
      nickName: '',
      idCheck: false,
      passwordRules: /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/,
      passwordRulesCheck: false,
      passwordValidCheck: false
    }
  },
  computed: {
    pwdCheck () {
      return !this.passwordRules.test(this.password)
    },
    pwdValidCheck () {
      return this.password !== this.passwordCheck
    }
  },
  methods: {
    ...mapActions(['insertUser', 'idDuplCheck']),
    idInputClickChangeRed () {
      this.idCheck = true
    },
    createUser () {
      this.insertUser({
        id: this.id,
        password: sha256(this.password),
        nickName: this.nickName
      }).then((res) => {
        alert('회원가입에 성공했습니다.')
        router.replace('/login')
      })
    },
    idValid () {
      this.idDuplCheck({ id: this.id }).then((res) => {
        if (!res) {
          alert('사용 가능한 아이디 입니다.')
        } else {
          alert('아이디가 중복됩니다. 다른 아이디를 사용해주세요.')
        }
      })
    }

  }
}
</script>

<style scoped>
    .idField {
        display: inline-block ;
        width: 90% ;
        margin-right: 20px
    }
    .checkBtn {
        display: inline-block;
        width:10px
    }
</style>
