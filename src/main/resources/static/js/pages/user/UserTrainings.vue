<template>
    <div class="userTrains">
        <table id="tableID">
            <tr class="headerTable">
                <th>Тип</th>
                <th>Время</th>
                <th>Дата</th>
                <th>Место проведения</th>
                <th>Тренер</th>
                <!--                <th></th>-->
            </tr>
            <tr v-for="train in this.trains" class="row">
                <td>
                    LOGO
                </td>
                <td>
                    {{getTime(train.startTrain)}}
                </td>
                <td>
                    {{getDate(train.startTrain)}}
                </td>
                <td>
                    {{train.location}}
                </td>
                <td>
                    {{train.trainer.username}}
                </td>
                <td>
                    <div class="train_menu" :id="'train_menu' + train.id">
                        <div class="close" :id="'close_menu' + train.id" @click="setCurrentTrainId(train.id)">&#10006;
                        </div>
                        <Button v-if="!ownerOfProfile"
                                @click.native="enterToTrain(train.id)"
                                name="Отменить запись"
                                color="#3BACB6"
                                width="148px"
                        ></Button>
                        <Button
                                @click.native=""
                                name="Участники"
                                color="#3BACB6"
                                width="148px"
                        ></Button>
                    </div>
                    <div class="train_settings" :id="'open_menu' + train.id">
                        <input class="checkbox" v-model="checked" @click="setCurrentTrainId(train.id)" id="checkbox"
                               type="checkbox"/>
                        <div class="dot"></div>
                        <div class="dot"></div>
                        <div class="dot"></div>
                    </div>
                </td>


            </tr>

        </table>
    </div>
</template>

<script>
    import http from "../../api/http-common.js";
    import store from "../../store/store.js"
    import Button from "../../components/Button.vue";

    export default {
        name: "UserTrainings",
        props: ['username1'],
        components:{Button},
        data() {
            return {
                trains: [],
                checked: false,
            }
        },
        methods: {
            getTrains() {
                console.log(this.username1)
                http.get('/findUser/' + this.username1 + '/trains', {})
                    .then((response) => {
                        console.log("response get")
                        console.log(response)
                        this.trains = response.data

                    })
                    .catch((error) => {
                        const response = error.response
                        console.log(response.data.errors)
                        console.log(error);
                    });
            },
            getTime(date) {
                let time = new Date(date).toLocaleTimeString();
                return time;
            },
            getDate(date) {
                let retDate = new Date(date).toLocaleDateString();
                return retDate;
            },
            setCurrentTrainId(id) {
                if (!this.checked) {
                    // for(let i = 0; i < this.trains.length; i++){
                    //     if(this.trains[i].id === id){
                    //         for(let j = 0; j < this.trains[i].users.length; j++){
                    //             if(this.trains[i].users[j].username === store.getters.getUsername){
                    //                 this.nameOfEnterIntoTrainButton = "Отменить запись"
                    //                 break;
                    //             }
                    //         }
                    //     }
                    // }
                    document.getElementById('train_menu' + id).style.display = "block";
                    document.getElementById('close_menu' + id).style.display = "block";
                    //document.getElementById('open_menu' + id).style.display = "none";

                } else {
                    document.getElementById('train_menu' + id).style.display = "none";
                    document.getElementById('close_menu' + id).style.display = "none";
                    //document.getElementById('open_menu' + id).style.display = "flex";
                    // this.nameOfEnterIntoTrainButton = "Записаться"
                }
                this.checked = !this.checked
            },
            async enterToTrain(trainId) {
                console.log(this.username1)
                await http.get('/train/' + trainId + '/changeUser/' + store.getters.getUsername, {})
                    .then((response) => {
                        // console.log("response get")
                        // console.log(response.data)
                        window.location.reload()
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
        },
        computed: {
            ownerOfProfile() {
                return this.usernameOfUser === this.username1;
            }
        },
        created() {
            this.getTrains();
        }
    }
</script>

<style scoped>
    .userTrains {
        background: #3BACB6;
        margin: 10px 50px 0 50px;
        padding: 10px;
        min-height: 500px;
    }

    table {
        width: 100%;
        border-spacing: 1px 15px;
    }

    .row {
        background: #2F8F9D;
        height: 50px;

    }

    .train_settings {
        display: flex;
        flex-direction: column;
        align-items: center;
        position: relative;
    }

    .checkbox {
        position: absolute;
        display: block;
        opacity: 0;
        width: 12px;
        height: 24px;
        cursor: pointer;
    }

    .dot {
        width: 5px;
        height: 5px;
        border-radius: 5px;
        background: #B3E8E5;
        margin: 2px;
    }

    td {
        text-align: center;
        vertical-align: middle;
        color: #B3E8E5;
        position: relative;
    }

    .train_menu {
        width: 200px;
        height: 110px;
        position: absolute;
        z-index: 1000;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        backdrop-filter: blur(5px);
        background: rgb(23, 106, 118);
        border-radius: 25px 5px 5px 5px;
        padding: 5px;
        display: none;
        top: 0;
        right: 0;
    }

    .close {
        z-index: 3000;
        position: absolute;
        top: 0;
        right: 4px;
        font-size: 24px;
        display: none;
        cursor: pointer;
    }
</style>