<template>
    <v-container fluid grid-list-md>
        <v-layout rowwrap>
            <v-flex>

            <center class="display-1 font-weight-bold"> 로그인</center>
                <v-card class="flex pa-10 ma-10 " xl5>

            <div>

                <v-form
                    ref="form"
                    action="#"
                    method="post"
                    @submit.prevent="login"
                >
                <v-text-field
                    v-model="id"
                    label="아이디"
                    name="id"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="password"
                    type="password"
                    name="password"
                    label="비밀번호"
                    ref="password"
                    ></v-text-field>

                <v-btn
                type="submit"
                color="white"
                class="mr-4"
                >
                    로그인
                </v-btn>
                </v-form>

            </div>
            </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

import sha256 from 'js-sha256'
import { mapActions } from 'vuex'
import router from '../router'

export default {
  data () {
    return {
      id: '',
      password: ''
    }
  },

  methods: {
    ...mapActions(['userLogin']),
    login () {
      this.userLogin({
        id: this.id,
        password: sha256(this.password)
      }).then((res) => {
        if (res) {
          router.replace('/')
        }
      })
    }
  }
}
</script>
