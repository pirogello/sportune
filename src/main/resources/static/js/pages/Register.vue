<template>
    <v-app>
        <div class="background"></div>
        <div class="header">
            <div class="header-text">SPORTUNE</div>

            <div class="buttons">
                <Button
                        @click.native="toAdminPage()"
                        name="Trainer page"
                        color="#3BACB6"
                        width="150px"
                        height="40px"
                ></Button>
                <Button
                        @click.native="TestAccess()"
                        name="Auth page"
                        color="#3BACB6"
                        width="150px"
                        height="40px"
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
    import http from "../api/http-common.js";
    import store from "../store/store.js"
    import Button from '../components/Button.vue'
    import router from "../router/router.js";

    export default {
        components:{Button},
        data() {
            return {
            }
        },
        methods: {
            async toAdminPage() {
                await http.get('/profile/admin', {})
                    .then(function (response) {
                        router.push("/admin");
                    })
                    .catch(function (error) {
                        console.log("in test admin")
                        console.log(error)
                        // if(error.response.status === 401){
                        //     router.push("/admin");
                        // } else if(error.response.status === 403){
                        //     router.push("/admin");
                        // }
                    });
            },
            async TestAccess() {
                await http.get('/profile/haveauth', {})
                    .then(function (response) {
                        router.push("/haveauth");
                    })
                    .catch(function (error) {
                        console.log("in test have auth")
                        console.log(error);
                    });
            },
        }
    }
</script>

<style scoped>

</style>