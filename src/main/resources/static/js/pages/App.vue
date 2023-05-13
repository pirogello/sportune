<template>
    <v-app>
        <div class="header">
            <div class="header-text" @click="rout('/')">SPORTUNE</div>
            <div class="findUser">
                <input class="text-field__input" v-model="username" type="text" name="login" id="login"
                       placeholder="find user by username" value="itchief">
                <Button
                        @click.native="findByUsername"
                        name="find"
                        color="#3BACB6"
                >
                </Button>

            </div>
            <Button v-if="!this.token"
                    @click.native="rout('/login')"
                    name="Login"
                    color="#3BACB6"
            >
            </Button>
            <div class="user_menu_block">
<!--                <div src="" class="notifications" v-if="this.token">notification</div>-->
                <div v-if="this.token" class="user-together">
                    <div class="user-select" @click="userMenu">
                        <div class="text">
                            {{usernameOfUser}}
                        </div>
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
                        <div class="btn-group">
                            <div class="backdrop"></div>
                            <Button
                                    @click.native="profile(), userMenu()"
                                    name="Profile"
                                    color="#3BACB6"
                            >
                            </Button>
                            <Button
                                    @click.native="userMenu"
                                    name="Settings(пока не работает)"
                                    color="#3BACB6"
                            >
                            </Button>
                            <Button
                                    @click.native="userMenu"
                                    name="Help(пока не работает)"
                                    color="#3BACB6"
                            >
                            </Button>
                            <Button
                                    @click.native="logout(), userMenu()"
                                    name="Logout"
                                    color="#3BACB6"
                            >
                            </Button>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <router-view></router-view>

    </v-app>
</template>

<script>

    import store from "../store/store.js"
    import router from "../router/router";
    import Button from "../components/Button.vue";
    import http from "../api/http-common.js"

    export default {
        components: {Button},
        data() {
            return {
                menu: false,
                overlay: false,
                username: "",
                user: null,
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
            async findByUsername() {
                const url = '/findUser/find/' + this.username;
                http.get(url).then(async (res) => {
                    console.log(res.data.username)
                    this.user = res.data;
                    this.username = ""
                    if (this.user.role === 'USER') {
                        console.log("go to " + this.user.username)
                        await router.push("/sportsman/" + this.user.username);
                    }
                    if (this.user.role === 'TRAINER') {
                        await router.push("/trainer/" + this.user.username);
                    }
                    if (this.user.role === 'ORGANIZER') {
                        await router.push("/");
                    }
                    window.location.reload();
                }).catch(function (error) {
                    console.log(error)
                    alert("Нет пользователя с таким именем")
                });
                console.log("find... " + this.username)
            },
            profile() {
                if (store.getters.getRoles === 'USER') {
                    this.rout("/sportsman/" + store.getters.getUsername)
                }
                if (store.getters.getRoles === 'TRAINER') {
                    this.rout("/trainer/" + store.getters.getUsername)
                }
                if (store.getters.getRoles === 'ORGANIZER') {
                    this.rout("/")

                }
                window.location.reload()
            },
        },
        computed: {
            token() {
                console.log("token is: " + store.getters.getToken);
                return store.getters.getToken;
            },

            usernameOfUser() {
                console.log("username is: " + store.getters.getUsername);
                return store.getters.getUsername;
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

    body {
        background: #3BACB6;
    }

    .user-together {
        height: 100%;
    }

    .user_menu_block{
        display: flex;
        flex-direction: row;
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
        background: rgba(23, 106, 118, 0.5);

        border-radius: 5px 25px 5px 5px;
    }

    .header {
        z-index: 10000;
        position: sticky;
        top: 0;
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

    .findUser {
        display: flex;
        align-items: center;
    }

    .text-field__input {
        display: block;
        width: 100%;
        height: calc(2.25rem + 2px);
        padding: 0.375rem 0.75rem;
        font-family: inherit;
        font-size: 1rem;
        font-weight: 400;
        line-height: 1.5;
        color: #212529;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #bdbdbd;
        border-radius: 0.25rem;
        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    }

    .text-field__input::placeholder {
        color: #212529;
        opacity: 0.4;
    }

    .text-field__input:focus {
        color: #212529;
        background-color: #fff;
        border-color: #bdbdbd;
        outline: 0;
        box-shadow: 0 0 0 0.2rem rgba(158, 158, 158, 0.25);
    }

    .text-field__input:disabled,
    .text-field__input[readonly] {
        background-color: #f5f5f5;
        opacity: 1;
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
        cursor: pointer;
    }

    .notifications{
        width: auto;
        background: white;
        margin: 10px;
    }

    @media (max-width: 625px) {
        .header-text {
            font-size: 24px;
        }

        .header {
            padding: 5px 20px;
        }
    }
</style>