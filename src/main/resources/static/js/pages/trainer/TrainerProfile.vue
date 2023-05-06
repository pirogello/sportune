<template>
    <div>
        <div class="background-card">
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
                            <input class="checkbox" @click="menuOpen" id="checkbox" type="checkbox"/>
                            <div class="hamburger-lines">
                                <span class="line line1"></span>
                                <span class="line line2"></span>
                                <span class="line line3"></span>
                            </div>
                            <div class="profileMenu" id="profileMenu">
                                <Button
                                        @click.native="rout('/trainer/'+findUsername+'/posts')"
                                        name="Публикации"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button
                                        @click.native="rout('/trainer/'+findUsername+'/trainings')"
                                        name="Тренировки"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button
                                        @click.native="rout('/')"
                                        name="Опыт работы(-)"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button v-if="ownerOfProfile"
                                        @click.native="rout('/')"
                                        name="Спортсмены(-)"
                                        color="#3BACB6"
                                        width="132px"
                                ></Button>
                                <Button
                                        @click.native="rout('/')"
                                        name="Отзывы(-)"
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
    import store from "../../store/store.js"
    import Button from '../../components/Button.vue'
    import router from "../../router/router";
    import http from "../../api/http-common.js";


    export default {
        components: {Button},
       // props:['findUsername'],
        data() {
            return {
                menu: false,
                overlay: false,
                username: "",
                findUsername:router.currentRoute.params.username,
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
            menuOpen(){
                if(document.getElementById('checkbox').checked){
                    document.getElementById('profileMenu').style.display = "block";
                }
                else {
                    document.getElementById('profileMenu').style.display = "none";
                }
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
            },
            ownerOfProfile(){
                return this.usernameOfUser===this.findUsername;
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
        width: 80vw;
        z-index: 10;
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

    .info_block2 {
        display: flex;
        justify-content: space-between;
    }

</style>