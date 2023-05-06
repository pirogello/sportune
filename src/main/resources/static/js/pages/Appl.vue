<template>
    <div>Main page</div>
</template>

<script>
    import store from "../store/store.js"
    import Button from '../components/Button.vue'
    import router from "../router/router";

    export default {
        components: {Button},
        data() {
            return {
                menu: false,
                overlay: false,
                username:"",
            }
        },
        // created: async function () {
        //     await this.$http.get('/profile/acebe0fb-5050-43a3-9e10-63b435474d69').then(
        //         result => {
        //             this.user = JSON.parse(JSON.stringify(result.data));
        //         }
        //     )
        // },
        methods: {
            userMenu() {
                if (this.overlay) {
                    document.getElementsByClassName("overlay-menu")[0].style.display = "none";
                    document.getElementsByClassName("user-select")[0].style.display = "flex";
                    document.getElementsByClassName("hide-overlay")[0].style.display = "none";
                    this.overlay = false;
                } else {
                    document.getElementsByClassName("overlay-menu")[0].style.display = "block";
                    document.getElementsByClassName("user-select")[0].style.display = "none";
                    document.getElementsByClassName("hide-overlay")[0].style.display = "block";
                    this.overlay = true;
                }
            },
            rout(path) {
                router.push(path);
            },
            async logout() {
                store.dispatch('onLogout').then(() => {
                }).then(() => {
                    this.token = null
                    router.push('/login');
                });
            },
            findByUsername() {
               router.push("/user/" + this.username);
            }
        },
        computed: {
            token() {
                console.log("token is: " + store.getters.getToken);
                return store.getters.getToken;
            },
            usernameOfUser() {
                console.log("username is: " + store.getters.getUsername);
                return store.getters.getUsername;
            }
        }
    }
</script>

<style>

    .overlay-menu {
        width: 400px;
        display: none;
        z-index: 100;
        padding: 5px 5px 10px 0;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);

        border-radius: 5px 25px 5px 5px;
    }


    .user-select {
        display: flex;
        justify-content: space-between;
        width: 400px;
        height: 100%;
        color: aliceblue;
        background: rgba(255, 255, 255, 0.15);
        box-shadow: inset 0px 0px 15px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);
        border-radius: 20px 100px 100px 20px;
        text-align: center;
        padding: 5px 5px 5px 40px;
    }

    .text {
        margin: auto;
    }

    .round {
        border-radius: 50%;
        overflow: hidden;
        aspect-ratio: 1/1;
    }

    img {
        width: 44px;
        height: 44px;
        object-fit: cover;
        z-index: 10;
    }

    .line-name-photo {
        z-index: 10;
        height: 44px;
        display: flex;
        justify-content: space-between;
        padding: 0px 0px 0px 40px;
    }

    .hide-overlay {
        /*background-color: red;*/
        position: absolute;
        z-index: 10;
        height: 54px;
        width: 400px;
        display: none;
    }

    .background {
        width: 100%;
        height: 100%;
        background-color: #3BACB6;
        position: absolute;
        z-index: 0;
    }

    @media (max-width: 700px) {
        .user-select {
            border-radius: 50%;;
            width: 54px;
            padding: 5px;
        }

        .text {
            display: none;
        }

        .user-together {
            width: auto;
            border-radius: 100px 100px 100px 100px;

        }

        .overlay-menu {
            width: 200px;
            display: none;
            z-index: 100;
            padding: 5px;
            /*position: absolute;*/
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(5px);

            border-radius: 5px 25px 5px 5px;
        }

        .name-line {
            display: none;
        }

        .line-name-photo {
            justify-content: flex-end;
        }

        .hide-overlay {
            width: 200px;
            /*display: none;*/
        }
    }


</style>