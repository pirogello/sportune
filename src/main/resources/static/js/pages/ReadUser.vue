<template>
    <div>
        <input v-model="username" placeholder="type username">
        <Button
                @click.native="findByUsername"
                name="find"
                color="#3BACB6"
                width="120px"
        ></Button>
        <br>
        <div v-if="user">
            id: {{id}}
        </div>
        <div v-if="user">
            username: <input v-model="changeUsername">
        </div>
        <div v-if="user">
            info:  <input v-model="info">
        </div>
        <div v-if="user">
            password: {{user?user.password:"-"}}
        </div>
        <div v-if="user">
            followers:
            <ul>
            <li v-for="follower in user.followers" :key="follower.id">
                {{follower.username}}
            </li>
        </ul>
        </div>
        <div v-if="user">
            following:
            <ul>
                <li v-for="follow in user.following" :key="follow.id">
                    {{follow.username}}
                </li>
            </ul>
        </div>
        <div v-if="user">
            trainer: {{user?user.trainer.username:"-"}}
        </div>
        <div v-if="user">
            publications:
            <ul>
            <li v-for="publ in user.publications" :key="publ.id">
              {{publ.title}}
            </li>
        </ul>
        </div>
        <div v-if="user">
            trains:
            <ul>
                <li v-for="train in user.trains" :key="train.id">
                    {{train.title}}
                </li>
            </ul>
        </div>
        <div v-if="user">
            sports:
            <ul>
                <li v-for="sport in user.sports" :key="sport.id">
                    {{sport.title}}
                </li>
            </ul>
        </div>
        <div v-if="user">
            sport result:
            <ul>
                <li v-for="res in user.sportResults" :key="res.id">
                    competition: {{res.competition.title}} |
                    place: {{res.point}}

                </li>
            </ul>
        </div>
        <div v-if="user">
            role: {{user?user.role:"-"}}
        </div>
        <Button v-if="user"
                @click.native="save"
                name="save"
                color="#3BACB6"
                width="120px"
        ></Button>
    </div>
</template>

<script>
    import http from "../api/http-common.js";
    import Button from '../components/Button.vue'
    import router from "../router/router.js"
    import store from "../store/store.js"

    export default {
        components: {Button},
        data() {
            return {
                username: "",
                user: null,
                info:"",
                id:"",
                changeUsername: "",
            }

        },
        methods: {
            async save() {
                await http.post('/user/update', {
                    id: this.id,
                    username:  this.changeUsername,
                    prevUsername: this.username,
                    info: this.info
                })
                    .then((response) => {
                        console.log(response.data);
                    })
                    .catch(function (error) {
                        if(error.response.status == 401) {
                            store.dispatch('onLogout').then(() => {
                            }).then(() => {
                            });
                            router.push('/login');
                        }
                    });
            },
            async findByUsername() {
                await http.get('/findUser/find/' + this.username, {
                    username: this.username
                })
                    .then((response) => {
                        this.user = response.data.username;
                        //JSON.parse(JSON.stringify(response.data));
                        this.info = this.user.info
                        this.id = this.user.id
                        this.changeUsername = this.user.username
                    })
                    .catch(function (error) {
                        console.log(error)

                    });
            }
        }
    }
</script>

<style scoped>
    .buttons {
        height: 54px;
        display: flex;
        align-items: center;
    }
</style>