<template>
    <v-container fluid grid-list-md>
        <v-layout rowwrap>
            <v-flex>

            <center class="display-1 font-weight-bold"> 로그인</center>
                <v-card class="flex pa-10 ma-10 " xl5>

            <div>

                <v-form
                    ref="form"
                    v-model="valid"
                    lazy-validation
                >
                <v-text-field
                    v-model="id"
                    :rules="idRules"
                    label="아이디"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="password"
                    type="password"
                    name="password"
                    label="비밀번호"
                    ></v-text-field>

                <v-btn
                color="white"
                class="mr-4"
                @click="login"
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
import UserApi from '../axios/MembersInfo.js'

export default {
    data() {
        return{
            id: '',
            password: '',

        }
    },
    mounted() {

    },

    methods: {
        login() {
            UserApi.loginValidCheck({
                 id: this.id, 
                 password: this.password
                 })
            .then((res) => {
                if(res){
                    alert('로그인성공')
                }else{
                    alert('로그인실패')
                }
            })
        }
    }
}
</script>