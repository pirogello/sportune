<template>
    <v-app>
        <div class="background"></div>
        <div class="header">
            <div class="header-text">SPORTUNE</div>
            <router-link to="/login">Go to login</router-link>
            <div class="user-together">
                <div class="user-select" @click="userMenu">
                    <div class="text">{{user?.name}}</div>
                    <v-spacer></v-spacer>
                    <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5" alt="" class="round">
                </div>
                <div class="hide-overlay" @click="userMenu"></div>
                <div class="overlay-menu"
                     ref="overlay"
                >
                    <div class="line-name-photo">
                        <div class="name-line">{{user?.name}}</div>
                        <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5" alt="" class="round">

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
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                href=""
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
                            Settings
                        </v-btn>
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                href=""
                        >
                            Help
                        </v-btn>
                        <v-btn
                                elevation="2"
                                color="#3BACB6"
                                dark
                                style="margin-top: 10px"
                                href=""
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
    </v-app>

</template>

<script>
    export default {
        data() {
            return {
                menu: false,
                overlay: false,
                user: null
            }
        },
        created: async function () {
            await this.$http.get('/profile/acebe0fb-5050-43a3-9e10-63b435474d69').then(
                result => {
                    this.user = JSON.parse(JSON.stringify(result.data));
                }
            )
        },
        methods:{
            userMenu () {
                if(this.overlay) {
                    document.getElementsByClassName("overlay-menu")[0].style.display = "none";
                    document.getElementsByClassName("user-select")[0].style.display = "flex";
                    document.getElementsByClassName("hide-overlay")[0].style.display = "none";
                    this.overlay = false;
                }
                else {
                    document.getElementsByClassName("overlay-menu")[0].style.display = "block";
                    document.getElementsByClassName("user-select")[0].style.display = "none";
                    document.getElementsByClassName("hide-overlay")[0].style.display = "block";
                    this.overlay = true;
                }
            },
        }
    }
</script>

<style>

    *{
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }
    .user-together{
        height: 100%;
    }
    /*.header{*/
    /*    height: 64px;*/
    /*    background-color: #2F8F9D;*/
    /*    box-shadow: 0px 2px 20px rgba(0, 0, 0, 0.4);*/
    /*    display: flex;*/
    /*    justify-content: space-between;*/
    /*    padding: 5px 50px;*/

    /*}*/
    /*.header-text{*/
    /*    color: white;*/
    /*    font-family: 'Montserrat';*/
    /*    font-style: normal;*/
    /*    text-align: center;*/
    /*    font-size: 32px;*/
    /*    display: flex;*/
    /*    justify-content: center;*/
    /*    align-items: center;*/
    /*}*/
    .hr-line {
        height: 0;
        border: none;
        border-top: 1px solid #176A76;
        width: 75%;
        margin-top: 5px;
        justify-content:center;
        flex-direction: row;
    }
    .name-line{
        margin: auto 0;
        color: white;
    }

    .btn-group{
        display: flex;
        justify-content:center;
        flex-direction: column;
        padding: 0px 40px;
        margin-bottom: 10px;
    }
    .overlay-menu{
        width: 400px;
        display: none;
        z-index: 100;
        padding: 5px 5px 10px 0;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);

        border-radius: 5px 25px 5px 5px;
    }
    .header{
        z-index: 1000;
        position: sticky;
        top: 0;
    }
    .background-card{
        align-self: center;
    }
    .user-select{
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

    .text{
        margin: auto;
    }
    .round {
        border-radius: 50%;
        overflow: hidden;
        aspect-ratio:1/1;
    }
    img{
        width:44px;
        height:44px;
        object-fit: cover;
        z-index: 10;
    }
    .line-name-photo{
        z-index: 10;
        height:44px;
        display: flex;
        justify-content: space-between;
        padding: 0px  0px 0px 40px;
    }
    .hide-overlay{
        /*background-color: red;*/
        position: absolute;
        z-index: 10;
        height:54px;
        width: 400px;
        display: none;
    }
    .background{
        width: 100%;
        height: 100%;
        background-color: #3BACB6;
        position: absolute;
    }

    @media (max-width: 700px) {
        .user-select{
            border-radius: 50%;;
            width: 54px;
            padding: 5px;
        }
        .text{
            display: none;
        }
        .user-together{
            width: auto;
            border-radius: 100px 100px 100px 100px;

        }

        .overlay-menu{
            width: 200px;
            display: none;
            z-index: 100;
            padding: 5px;
            /*position: absolute;*/
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
            backdrop-filter: blur(5px);

            border-radius: 5px 25px 5px 5px;
        }
        .name-line{
            display: none;
        }
        .line-name-photo{
            justify-content: flex-end;
        }
        .hide-overlay{
            width: 200px;
            /*display: none;*/
        }
    }


</style>