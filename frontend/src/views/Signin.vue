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
                v-once="idInputClickChangeRed"
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
                @keyup="pwdCheck()"
                :error="passwordRulesCheck"
                hint="숫자+문자+특수문자 8글자 이상"
                required
                ></v-text-field>
            <v-text-field
                v-model="passwordCheck"
                type="password"
                name="password"
                @keyup="pwdValidCheck()"
                :error="passwordValidCheck"
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
import UserApi from '../axios/MembersInfo.js'
export default {
    data() {
        return {
            id: '',
            password: '',
            passwordCheck:'',
            nickName: '',
            idCheck: false,
            passwordRules: /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/,
            passwordRulesCheck: false,
            passwordValidCheck: false,
        }
    },
    computed:  {
        pwdCheck() {
            this.passwordRulesCheck = !this.passwordRules.test(this.password)
        },
        pwdValidCheck() {
            if( this.password === this.passwordCheck ){
                this.passwordValidCheck = false
            }else{
                this.passwordValidCheck = true
            }
        }
    },
    methods: {
        idInputClickChangeRed() {
            this.idCheck=true
        },
        createUser() {
            console.log(this.id , sha256(this.password), sha256(this.passwordCheck), this.nickName)
            UserApi.createUser({
                id: this.id,
                password: sha256(this.password),
                nickName: this.nickName,
            }).then((res) => {

            })
        },
        idValid() {
            UserApi.idCheck({
                id: this.id
            }).then((res) => {
                console.log(res.data)
                this.idCheck= res.data
                console.log(this.idCheck)
                if(res.data) {
                    alert('중복된 아이디입니다. 다른 아이디를 사용해주세요.')
                }else {
                    alert('사용가능한 아이디입니다.')
                }
            })
        },
        
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