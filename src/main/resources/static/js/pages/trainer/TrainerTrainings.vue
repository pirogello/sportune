<template>
    <div class="userTrains">
        <Button v-if="ownerOfProfile"
                @click.native="createTrain"
                name="Создать тренировку"
                color="#3BACB6"
                width="132px"
        ></Button>
        <table id="tableID">
            <tr class="headerTable">
                <th>Тип</th>
                <th>Время</th>
                <th>Дата</th>
                <th>Место проведения</th>
                <th>Тренер</th>
                <th>Заполненность</th>
                <th></th>
            </tr>
            <tr v-for="train in this.trains" class="row" :id="train.id">
                <td>LOGO</td>
                <td>{{getTime(train.startTrain)}}</td>
                <td>{{getDate(train.startTrain)}}</td>
                <td>{{train.location}}</td>
                <td>{{train.trainer.username}}</td>
                <td>{{train.users.length}} / 10</td>
                <td>
                    <div class="train_menu" :id="'train_menu' + train.id">
                        <div class="close" :id="'close_menu' + train.id" @click="setCurrentTrainId(train.id)">&#10006;</div>
                        <Button v-if="!ownerOfProfile"
                                @click.native="enterToTrain(train.id)"
                                :name="nameOfEnterIntoTrainButton"
                                color="#3BACB6"
                                width="148px"
                        ></Button>
                        <Button v-if="ownerOfProfile"
                                @click.native=""
                                name="Отменить(-)"
                                color="#3BACB6"
                                width="148px"
                        ></Button>
                        <Button
                                @click.native=""
                                name="Участники(-)"
                                color="#3BACB6"
                                width="148px"
                        ></Button>
                    </div>
                    <div class="train_settings" :id="'open_menu' + train.id">
                        <input class="checkbox" v-model="checked" @click="setCurrentTrainId(train.id)" id="checkbox" type="checkbox"/>
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
    import Button from "../../components/Button.vue";
    import store from "../../store/store.js"
    import router from "../../router/router.js";

    export default {
        name: "TrainerTrainings",
        components: {Button},
        props: ['username1'],
        data() {
            return {
                trains: [],
                currentTrainsId: "",
                checked:false,
                nameOfEnterIntoTrainButton:"Записаться"
            }
        },
        methods: {
            async getTrains() {
                console.log(this.username1)
               await http.get('/findUser/' + this.username1 + '/trains', {})
                    .then((response) => {
                        console.log("response get")
                        this.trains = response.data
                        console.log(response.data)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            getTime(date) {
                return new Date(date).toLocaleTimeString();
            },
            getDate(date) {
                return new Date(date).toLocaleDateString();
            },
            setCurrentTrainId(id) {
                this.currentTrainsId = id;
                this.nameOfEnterIntoTrainButton = "Записаться"
                if(!this.checked){
                    for(let i = 0; i < this.trains.length; i++){
                        if(this.trains[i].id === id){
                            for(let j = 0; j < this.trains[i].users.length; j++){
                                if(this.trains[i].users[j].username === store.getters.getUsername){
                                    this.nameOfEnterIntoTrainButton = "Отменить запись"
                                    break;
                                }
                            }
                        }
                    }
                    document.getElementById('train_menu'+ id).style.display = "block";
                    document.getElementById('close_menu'+ id).style.display = "block";
                    //document.getElementById('open_menu' + id).style.display = "none";

                }
                else {
                    document.getElementById('train_menu'+ id).style.display = "none";
                    document.getElementById('close_menu'+ id).style.display = "none";
                    //document.getElementById('open_menu'+ id).style.display = "flex";
                    // this.nameOfEnterIntoTrainButton = "Записаться"
                }
                this.checked = !this.checked
            },
            async enterToTrain(trainId){
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
            createTrain() {
                router.push("/trainer/" + this.usernameOfUser + "/createTrain")
            }
        },
        computed: {
            usernameOfUser() {
               return store.getters.getUsername;
            },
            ownerOfProfile(){
                return this.usernameOfUser===this.username1;
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
    .checkbox{
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
        top:0;
        right: 0;
    }
    .close{
        z-index: 3000;
        position: absolute;
        top: 0;
        right: 4px;
        font-size: 24px;
        display: none;
        cursor: pointer;
    }


</style>