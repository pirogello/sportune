<template>
    <div>
        <table>
            <tr class="headerTable">
                <th class="time"></th>
                <th>{{currentDates[0]}}</th>
                <th>{{currentDates[1]}}</th>
                <th>{{currentDates[2]}}</th>
                <th>{{currentDates[3]}}</th>
                <th>{{currentDates[4]}}</th>
                <th>{{currentDates[5]}}</th>
                <th>{{currentDates[6]}}</th>
            </tr>
            <tr v-for="i in 23">
                <td>{{i < 10?'0'+i:i}} : 00 - {{i < 9 ? '0'+(i+1) : (i+1)}} : 00</td>
                <td v-for="c in currentDates.length" :class="'block_of_date_time column_' + c" :id="'block'+i+c">
                    <div class="t0min" :id="'block_' + c +'_'+ i + '_0_min'"
                         @mousedown="createBlock(c, (i < 10?'0'+i:i) +':00')"></div>
                    <div class="t15min" :id="'block_' + c +'_'+ i + '_15_min'"
                         @mousedown="createBlock(c,  (i < 10?'0'+i:i) +':15')"></div>
                    <div class="t30min" :id="'block_' + c +'_'+ i + '_30_min'"
                         @mousedown="createBlock(c,  (i < 10?'0'+i:i) +':30')"></div>
                    <div class="t45min" :id="'block_' + c +'_'+ i + '_45_min'"
                         @mousedown="createBlock(c, (i < 10?'0'+i:i) +':45')"></div>
                </td>
            </tr>
        </table>
        <CreateTrainForm class="create_form_block" id="create_form_block1"
                         @closeTab="closeCreateTrainForm"
                         ref="createFormBlock"
        ></CreateTrainForm>
        <!--        @mousedown.native="mouseDown" @mouseup.native="mouseUp"-->
        <div v-for="train in this.trains" @mouseover="trainInfo(train.id)" @mouseleave="hideTrainInfo"
             class="train_in_calendar" :id="train.id">
            {{getTime(train.startTrain).hour}}:{{getTime(train.startTrain).min ===0?'00':getTime(train.startTrain).min}}
            -
            {{getTime(train.endTrain).hour}}:{{getTime(train.endTrain).min ===0?'00':getTime(train.endTrain).min}}
        </div>
        <div class="train_info" id="train_info">
            <div>{{currentTrainInfo.location}}</div>
            <div>{{currentTrainInfo.fullness}}</div>
            <Button v-if="!ownerOfProfile"
                    @click.native="enterToTrain(currentTrainInfo.id)"
                    :name="nameOfEnterIntoTrainButton"
                    color="#3BACB6"
                    width="100px"
            ></Button>
            <Button v-if="ownerOfProfile"
                    @click.native=""
                    name="Удалить"
                    color="#3BACB6"
                    width="100px"
            ></Button>
        </div>
        <!--        <div class="train_in_calendar">{{this.trains.length}}</div>-->
    </div>
</template>

<script>
    import Button from "../../components/Button.vue"
    import CreateTrainForm from "./CreateTrainForm.vue"

    import http from "../../api/http-common.js";
    import store from "../../store/store.js"
    import router from "../../router/router.js";


    const monthNames = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];

    export default {
        name: "TrainerTrainingsCalendar",
        components: {Button, CreateTrainForm},
        props:["username1"],
        data() {
            return {
                currentDates: [],
                colIds: [],
                trains: [],
                currentTrainInfo: {
                    id: "",
                    location: "",
                    fullness: ""
                },
                nameOfEnterIntoTrainButton: "Записаться"
            }
        },
        methods: {
            createBlock(date, time) {
                let now = Date.now()
                let d = new Date(now)
                d.setDate((d.getDate() + date - 1))
                let stringDate = d.getFullYear() + "-" + (d.getMonth() < 10 ? "0" + d.getMonth() : d.getMonth()) + "-" + (d.getDate() < 10 ? "0" + d.getDate() : d.getDate())
                this.$refs.createFormBlock.setStartDateAndTime(stringDate, time);
                this.createTrainForm()
            },
            trainInfo(id) {
                this.nameOfEnterIntoTrainButton = "Записаться"
                if(!this.checked) {
                    for (let i = 0; i < this.trains.length; i++) {
                        if (this.trains[i].id === id) {
                            for (let j = 0; j < this.trains[i].users.length; j++) {
                                if (this.trains[i].users[j].username === store.getters.getUsername) {
                                    this.nameOfEnterIntoTrainButton = "Отменить запись"
                                    break;
                                }
                            }
                        }
                    }
                    document.getElementById("train_info").style.display = "flex"
                    let trainBlock = document.getElementById(id)
                    let t = trainBlock.getBoundingClientRect().top + window.pageYOffset
                    let l = trainBlock.getBoundingClientRect().left + window.pageXOffset - parseFloat(window.getComputedStyle(trainBlock).width) - 2
                    document.getElementById("train_info").style.top = t + "px"
                    document.getElementById("train_info").style.left = l + "px"
                    document.getElementById("train_info").style.width = (window.getComputedStyle(trainBlock).width)
                    for (let i = 0; i < this.trains.length; i++) {
                        if (this.trains[i].id === id) {
                            this.currentTrainInfo.location = this.trains[i].location
                            this.currentTrainInfo.fullness = this.trains[i].users.length + " / 10"
                            this.currentTrainInfo.id = id
                        }
                    }
                }
            },
            hideTrainInfo() {
                setTimeout(this.showTrainInfoBlock, 10);
                //document.getElementById("train_info").style.display = "none"
            },
            showTrainInfoBlock() {
                document.getElementById("train_info").onmouseout = () => {
                    document.getElementById("train_info").style.display = "none"
                    console.log("out from block")
                }
                document.getElementById("train_info").onmouseover = () => {
                    document.getElementById("train_info").style.display = "flex"
                    console.log("out from block")
                }
            },
            getTimeFromBlockClass(clas) {
                let mas = (clas + '').split("_")
                return {hour: mas[2], min: mas[3]}
            },
            getEndBlockTime(startTime, countOfClick) {
                let d = new Date()
                d.setHours(startTime.hour)
                d.setMinutes(startTime.min)
                d.setMinutes(d.getMinutes() + countOfClick * 15)
                return {hour: d.getHours(), min: d.getMinutes()}
            },
            mouseDown() {
                console.log("down")
                document.getElementById("create_form_block1").addEventListener('mousemove', this.listener)
            },
            mouseUp() {
                console.log("up")
                document.getElementById("create_form_block1").removeEventListener('mousemove', this.listener)
            },
            listener: function (e) {
                document.getElementById("create_form_block1").style.left = e.pageX + "px";
                document.getElementById("create_form_block1").style.top = e.pageY - 200 + "px";
            },
            createTrainForm() {
                document.getElementById("create_form_block1").style.display = "flex"
            },
            closeCreateTrainForm() {
                document.getElementById("create_form_block1").style.display = "none"
            },

            async getTrainerTrains() {
                await http.get('/findUser/' + this.username1 + '/trains', {})
                    .then((response) => {
                        console.log(response)
                        this.trains = response.data
                        console.log(response.data)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                return new Promise(function (resolve, reject) {
                    resolve('start of new Promise');
                });
            },
            getTime(date) {
                let d = new Date(date)
                return {hour: d.getHours(), min: d.getMinutes()}
            },
            async createTrainsBlocks() {
                await this.getTrainerTrains();
                for (let i = 0; i < this.trains.length; i++) {
                    let startTime = this.getTime(new Date(this.trains[i].startTrain));
                    let endTime = this.getTime(new Date(this.trains[i].endTrain));
                    let startTrainTime = new Date(this.trains[i].startTrain)
                    let endTrainTime = new Date(this.trains[i].endTrain)
                    let now = new Date()
                    let c = startTrainTime.getDate() - now.getDate() + 1
                    let countOf15min = ((endTime.hour * 60 + endTime.min) - (startTime.hour * 60 + startTime.min)) / 15

                    let start = document.getElementById("block_" + c + "_" + startTime.hour + "_" + startTime.min + "_min")
                    let end = document.getElementById("block_" + c + "_" + endTime.hour + "_" + endTime.min + "_min")
                    let element = document.getElementById(this.trains[i].id)
                    if (start && element) {
                        element.style.height = parseFloat(window.getComputedStyle(start).height) * countOf15min + 0.25 * countOf15min + "px"
                        start.insertAdjacentElement('beforeEnd', element)
                    }
                }
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
            let nextDate = new Date()
            for (let i = 0; i < 7; i++) {
                let dd = nextDate.getDate();
                let mm = nextDate.getMonth();
                if (dd < 10) dd = "0" + dd
                if (mm < 10) mm = "0" + mm
                console.log(dd + " " + mm)
                this.currentDates[i] = dd + "." + mm;
                nextDate.setDate(nextDate.getDate() + 1)
            }
            this.createTrainsBlocks();
        },
    }
</script>

<style scoped>
    table {
        width: 100%;
        border-spacing: 1px 1px;
        padding: 5px;
    }

    .train_in_calendar {
        width: 98%;
        position: absolute;
        top: 0;
        left: 1%;
        background: #176A76;
        z-index: 100;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        color: #B3E8E5;
    }

    .train_info {
        width: 200px;
        height: 300px;
        display: none;
        background: #3BACB6;
        position: absolute;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        border-radius: 3px;
        z-index: 300;
        /*display: flex;*/
        align-items: center;
        flex-direction: column;
        color: white;
        padding: 7px;
    }


    /*.train_in_calendar:hover .train_info {*/
    /*    display: block;*/
    /*}*/

    .create_form_block {
        /*width: 300px;*/
        /*height: 400px;*/
        display: none;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-left: -150px;
        /*position: absolute;*/
        /*top: 50%;*/
        /*left: 50%;*/
        /*z-index: 1000;*/
        /*margin-left: -150px;*/
        /*background: #2F8F9D;*/
        /*flex-direction: column;*/
        /*align-items: center;*/
        /*border-radius: 10px;*/
        /*box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);*/
        /*padding: 10px;*/
    }

    .block_of_date_time {
        background: #B3E8E5;
        width: 130px;
        height: 30px;
        position: relative;
    }

    td {
        text-align: center;
        vertical-align: middle;
        color: #B3E8E5;
        position: relative;
        /*cursor: pointer;*/
    }

    th {
        color: #B3E8E5;
    }

    .time {
        width: 100px;
    }

    .t0min {
        position: absolute;
        top: 0;
        width: 100%;
        height: 25%;
    }

    .t15min {
        position: absolute;
        top: 25%;
        width: 100%;
        height: 25%;
    }

    .t30min {
        position: absolute;
        top: 50%;
        width: 100%;
        height: 25%;
    }

    .t45min {
        position: absolute;
        top: 75%;
        width: 100%;
        height: 25%;
    }

</style>