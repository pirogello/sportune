<template>
    <v-app>
        <div class="background"></div>
        <div class="header">
            <div class="header-text">SPORTUNE</div>
            <v-btn
                    elevation="2"
                    color="#3BACB6"
                    dark
                    style="margin-top: 10px"
                    href=""
            >
                Login
            </v-btn>
            <v-btn
                    elevation="2"
                    color="#3BACB6"
                    dark
                    style="margin-top: 10px"
                    href=""
            >
                Register
            </v-btn>
        </div>
    </v-app>
</template>

<script>
    export default {
        data: () => ({
            menu: false,
            overlay: false,
            users: []
        }),
        created: async function () {
            await this.$http.get('/profile/1').then(
                result => {
                    JSON.parse(
                        JSON.stringify(result.data))
                        .forEach(u => this.users.push(u))
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
    .header{
        height: 64px;
        background-color: #2F8F9D;
        box-shadow: 0px 2px 20px rgba(0, 0, 0, 0.4);
        display: flex;
        justify-content: space-between;
        padding: 5px 50px;

    }
    .header-text{
        color: white;
        font-family: 'Montserrat';
        font-style: normal;
        text-align: center;
        font-size: 32px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
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
            width: 200px;
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
        }
    }


</style>