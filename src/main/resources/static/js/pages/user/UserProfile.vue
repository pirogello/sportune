<template>
    <div>
        <div class="background-card">
            <div class="main_block">
                <div class="info_block">
                    <img class="profile_img"
                         src="https://sportishka.com/uploads/posts/2022-11/1667437638_24-sportishka-com-p-sportivnaya-pobeda-vkontakte-27.jpg"
                         alt="">
                    <div class="info_block2">
                        <div class="profile_img_round_background round">
                            <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5"
                                 alt="" class="round profile_img_round">
                        </div>
                        <div class="usertag">{{this.findUsername}}</div>
                        <div class="nav-container">
                            <input class="checkbox" @click="menuOpen" id="checkbox" type="checkbox"/>
                            <div class="hamburger-lines">
                                <span class="line line1"></span>
                                <span class="line line2"></span>
                                <span class="line line3"></span>
                            </div>
                            <div class="profileMenu" id="profileMenu">
                                <Button
                                        @click.native="(usernameOfUser===findUsername)?rout('/profil/posts') : rout('/user/'+findUsername+'/posts')"
                                        name="Публикации"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button v-if="this.token"
                                        @click.native="(usernameOfUser===findUsername)?rout('/profil/trainings') : rout('/user/'+findUsername+'/trainings')"
                                        name="Тренировки"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button v-if="this.token"
                                        @click.native="(usernameOfUser===findUsername)?rout('/profil/trophies') : rout('/user/'+findUsername+'/trophies')"
                                        name="Награды"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button v-if="this.token"
                                        @click.native="(usernameOfUser===findUsername)?rout('/profil/coaches') : rout('/user/'+findUsername+'/coaches')"
                                        name="Тренеры"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button v-if="this.token"
                                        @click.native="(usernameOfUser===findUsername)?rout('/profil/competitions') : rout('/user/'+findUsername+'/competitions')"
                                        name="Соревнования"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                            </div>
                        </div>
                    </div>
                </div>
                <router-view :username1="findUsername"></router-view>

            </div>
        </div>
    </div>
</template>

<script>
    import store from "../store/store.js"
    import Button from '../components/Button.vue'
    import router from "../router/router";
    import Post from "../components/Post.vue";
    import http from "../api/http-common.js";


    export default {
        components: {Button, Post},
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
                console.log("find... " + this.findUsername)

                router.push("/user/" + this.findUsername);
                window.location.reload();
            },
            menuOpen(){
                if(document.getElementById('checkbox').checked){
                    document.getElementById('profileMenu').style.display = "block";
                }
                else {
                    document.getElementById('profileMenu').style.display = "none";
                }
            }

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

    .overlay-menu {
        width: 400px;
        display: none;
        z-index: 100;
        padding: 5px 5px 10px 0;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);

        border-radius: 5px 25px 5px 5px;
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

    .background-card {
        margin: 0 auto;
        width: 80vw;
        background: #176A76;
        min-height: 100vh;
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

    .hide-overlay {
        /*background-color: red;*/
        position: absolute;
        z-index: 10;
        height: 54px;
        width: 400px;
        display: none;
    }

    @media (max-width: 700px) {
        .user-select {
            border-radius: 50%;;
            width: 54px;
            padding: 5px;
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

        .hide-overlay {
            width: 200px;
        }
    }

    .main_block {

    }
    .info_block{
        background: rgba(59, 172, 182, 0.4);
        /*position: sticky;*/
        /*top:-250px;*/
        /*z-index: 20;*/
    }

    .profile_img {
        height: 255px;
        width: 100%;
    }

    .profile_img_round {
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
        z-index: 2000;
    }

    .nav-container .checkbox {
        position: absolute;
        display: block;
        height: 32px;
        width: 32px;
        top: 20px;
        left: 20px;
        opacity: 0;
        cursor: pointer;
        z-index: 2000;
    }

    .nav-container .hamburger-lines {
        display: block;
        height: 26px;
        width: 32px;
        position: absolute;
        top: 17px;
        left: 20px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        z-index: 1500;
    }

    .nav-container .hamburger-lines .line {
        display: block;
        height: 4px;
        width: 100%;
        border-radius: 10px;
        background: #fff;
        z-index: 2000;
    }

    .nav-container .hamburger-lines .line1 {
        transform-origin: 0% 0%;
        transition: transform 0.4s ease-in-out;
        z-index: 2000;
    }

    .nav-container .hamburger-lines .line2 {
        transition: transform 0.2s ease-in-out;
        z-index: 2000;
    }

    .nav-container .hamburger-lines .line3 {
        transform-origin: 0% 100%;
        transition: transform 0.4s ease-in-out;
        z-index: 2000;
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

    .posts{
        display: flex;
        flex-direction: column;
        padding: 20px 115px;
    }

    .profileMenu{
        width: 220px;
        height: 250px;
        background: #2F8F9D;
        position: relative;
        right: 160px;
        /*top:5px;*/
        z-index: 1000;
        display: none;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);
        background: rgba(23, 106, 118, 0.5);
        border-radius: 5px 25px 5px 5px;
        padding: 5px;
    }
</style>