<template>
    <v-app>
        <div class="background"></div>
        <div class="header">
            <div class="header-text">SPORTUNE</div>

            <div class="buttons">
                <Button
                        @click.native="rout('/test')"
                        name="Test auth"
                        color="#3BACB6"
                        width="150px"
                ></Button>
                <Button
                        @click.native="rout('/models')"
                        name="check models"
                        color="#3BACB6"
                        width="120px"
                ></Button>
                <Button v-if="!this.token"
                        @click.native="rout('/login')"
                        name="Login"
                        color="#3BACB6"
                        width="120px"
                ></Button>
                <Button v-if="this.token"
                        @click.native="logout"
                        name="Logout"
                        color="#3BACB6"
                        width="120px"
                ></Button>

            </div>

        </div>
        <div class="background-card">
            <v-card
                    color="#2F8F9D"
                    height="100vh"
                    width="80vw"
            >
            </v-card>
        </div>

    </v-app>
</template>

<script>
    import Button from '../components/Button.vue'
    import router from "../router/router";
    import store from "../store/store.js"


    export default {
        components: {Button},
        data() {
            return {}
        },

        methods: {
            rout(path) {
                router.push(path);
            },
            async logout() {
                store.dispatch('onLogout').then(() => {
                }).then(() => {
                    this.token = null
                });
            },
        },
        computed: {
            token() {
                return store.getters.getToken;
            },
        }
    }
</script>

<style>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    .header {
        height: 64px;
        background-color: #2F8F9D;
        box-shadow: 0px 2px 20px rgba(0, 0, 0, 0.4);
        display: flex;
        justify-content: space-between;
        padding: 5px 50px;

    }

    .header-text {
        color: white;
        font-family: 'Montserrat';
        font-style: normal;
        text-align: center;
        font-size: 32px;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .buttons {
        height: 54px;
        display: flex;
        align-items: center;
    }

    .background {
        width: 100%;
        height: 100%;
        background-color: #3BACB6;
        position: absolute;
    }

    @media (max-width: 625px) {
        .header-text {
            font-size: 24px;
        }

        .header {
            padding: 5px 20px;
        }

        .my-button {
            min-width: 80px;
            font-size: 8px !important;
        }
    }

</style>