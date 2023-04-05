<template>
    <v-app>
        <div class="background"></div>
        <div class="header">
            <div class="header-text">SPORTUNE</div>
            <div>
                <input v-model="username" placeholder="введите имя">
                <v-btn
                        elevation="2"
                        color="#3BACB6"
                        dark
                        style="margin-top: 10px"
                        @click="findByUsername"
                >
                    find
                </v-btn>
            </div>
            <v-btn v-if="!this.token"
                   elevation="2"
                   color="#3BACB6"
                   dark
                   style="margin-top: 10px"
                   @click="rout('/login')"
            >
                Login
            </v-btn>
            <div v-if="this.token" class="user-together">
                <div class="user-select" @click="userMenu">
                    <div class="text">
                        {{usernameOfUser}}
                    </div>
                    <v-spacer></v-spacer>
                    <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5"
                         alt="" class="round">
                </div>
                <div class="hide-overlay" @click="userMenu"></div>
                <div class="overlay-menu"
                     ref="overlay"
                >
                    <div class="line-name-photo">
                        <div class="name-line">{{usernameOfUser}}</div>
                        <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5"
                             alt="" class="round">

                    </div>
                    <hr class="hr-line">
                    <v-overlay
                            color="#FFFFFF"
                            z-index="0"
                            :value="overlay"
                            opacity="0.1"
                    >
                    </v-overlay>
                    <div class="btn-group">
                        <div class="backdrop"></div>
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                @click="rout('/profile')"
                        >
                            Profile
                        </v-btn>
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                href=""
                        >
                            Settings(пока не работает)
                        </v-btn>
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                href=""
                        >
                            Help(пока не работает)
                        </v-btn>
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                @click="logout"
                        >
                            Logout
                        </v-btn>
                    </div>
                </div>
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
        <div class="main_block">
            <div class="info_block">
                <img class="profile_img"
                     src="https://sportishka.com/uploads/posts/2022-11/1667437638_24-sportishka-com-p-sportivnaya-pobeda-vkontakte-27.jpg"
                     alt="">
                <div class="info_block2">
                    <div class="usertag_trainer">trainer</div>
                    <div class="profile_img_round_background round">
                        <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5"
                             alt="" class="round profile_img_round">
                    </div>
                    <div class="usertag">{{this.findUsername}}</div>
                    <div class="nav-container">
                        <input class="checkbox" type="checkbox" name="" id=""/>
                        <div class="hamburger-lines">
                            <span class="line line1"></span>
                            <span class="line line2"></span>
                            <span class="line line3"></span>
                        </div>
                    </div>
                </div>
            </div>
            <v-btn v-if="this.findUsername==usernameOfUser"
                   elevation="2"
                   color="#3BACB6"
                   dark
                   style="margin-top: 10px"
                   @click=""
            >
                Мои тренировки(Пока не работает)
            </v-btn>
        </div>
    </v-app>

</template>

<script>
    import store from "../store/store.js"
    import Button from '../components/Button.vue'
    import router from "../router/router";
    import http from "../api/http-common.js";


    export default {
        components: {Button},
        props:['findUsername'],
        data() {
            return {
                menu: false,
                overlay: false,
                username: "",
            }
        },
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
                console.log("find... " + this.username)
                router.push("/user/" + this.username);
                window.location.reload();
            },

        },
        computed: {
            token() {
                console.log("token is: " + store.getters.getToken);
                return store.getters.getToken;
            },
            usernameOfUser() {
                console.log("username is: " + store.getters.getUsername);
                console.log("find username is: " + this.findUsername);
                return store.getters.getUsername;
            }
        }
    }
</script>

<style>

    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    .user-together {
        height: 100%;
    }

    .hr-line {
        height: 0;
        border: none;
        border-top: 1px solid #176A76;
        width: 75%;
        margin-top: 5px;
        justify-content: center;
        flex-direction: row;
    }

    .name-line {
        margin: auto 0;
        color: white;
    }

    .btn-group {
        display: flex;
        justify-content: center;
        flex-direction: column;
        padding: 0px 40px;
        margin-bottom: 10px;
    }

    .overlay-menu {
        width: 400px;
        display: none;
        z-index: 100;
        padding: 5px 5px 10px 0;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);

        border-radius: 5px 25px 5px 5px;
    }

    .header {
        z-index: 1000;
        position: sticky;
        top: 0;
    }
    .usertag{
        color: white;
        font-family: 'Montserrat';
        font-style: normal;
        text-align: center;
        font-size: 20px;
        justify-content: center;
        align-items: center;
    }
    .usertag_trainer{
        color: white;
        font-family: 'Montserrat';
        font-style: normal;
        text-align: center;
        font-size: 20px;
        justify-content: center;
        align-items: center;
        opacity: 0.7;
    }
    .background-card {
        align-self: center;
        position: absolute;
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
        }
    }

    .main_block {
        width: 80vw;
        align-self: center;
        z-index: 10;
        /*background: rgba(59, 172, 182, 0.4);*/
    }
    .info_block{
        background: rgba(59, 172, 182, 0.4);
    }

    .profile_img {
        height: 255px;
        width: 100%;
    }

    .profile_img_round {
        /*position: absolute;*/
        top: 50%;
        left: 50%;
        margin: 5px 0px 0px 5px;
        height: 140px;
        width: auto;

    }

    .profile_img_round_background {
        background: #2F8F9D;
        position: relative;
        bottom: 75px;
        left: 50px;
        height: 150px;
        width: auto;
    }

    @import url("https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap");


    .nav-container {
        display: block;
        position: relative;
        height: 60px;
        width: 60px;
        margin: 0 15px 0 0;
        /*left: 200px;*/
    }

    .nav-container .checkbox {
        position: absolute;
        display: block;
        height: 32px;
        width: 32px;
        top: 20px;
        left: 20px;
        z-index: 5;
        opacity: 0;
        cursor: pointer;
    }

    .nav-container .hamburger-lines {
        display: block;
        height: 26px;
        width: 32px;
        position: absolute;
        top: 17px;
        left: 20px;
        z-index: 2;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .nav-container .hamburger-lines .line {
        display: block;
        height: 4px;
        width: 100%;
        border-radius: 10px;
        background: #fff;
    }

    .nav-container .hamburger-lines .line1 {
        transform-origin: 0% 0%;
        transition: transform 0.4s ease-in-out;
    }

    .nav-container .hamburger-lines .line2 {
        transition: transform 0.2s ease-in-out;
    }

    .nav-container .hamburger-lines .line3 {
        transform-origin: 0% 100%;
        transition: transform 0.4s ease-in-out;
    }

    .nav-container input[type="checkbox"]:checked ~ .hamburger-lines .line1 {
        transform: rotate(45deg);
    }

    .nav-container input[type="checkbox"]:checked ~ .hamburger-lines .line2 {
        transform: scaleY(0);
    }

    .nav-container input[type="checkbox"]:checked ~ .hamburger-lines .line3 {
        transform: rotate(-45deg);
    }

    .info_block2 {
        display: flex;
        justify-content: space-between;
    }

</style>