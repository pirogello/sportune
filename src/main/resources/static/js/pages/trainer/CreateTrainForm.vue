<template>
    <div>
        <form class="qwerty">
            <div class="row">
                <label for="dateStart">Дата: </label>
                <input type="date" id="dateStart" name="trip-start"
                       min="2018-01-01" max="2025-12-31" required
                       v-model="startDateTrain">
            </div>
            <div class="row">
                <label for="timeStart">Начало: </label>
                <input type="time" id="timeStart" name="appt"
                       min="00:00" max="23:59" required
                       v-model="startTimeTrain">
            </div>
            <div class="row">
                <label for="timeEnd">Конец: </label>
                <input type="time" id="timeEnd" name="appt"
                       min="00:00" max="23:59" required
                       v-model="endTimeTrain">
            </div>
            <label for="location">Место проведения: </label>
            <input type="text" id="location" v-model="location">
            <Button
                    @click.native="createTrain"
                    name="Создать"
                    color="#3BACB6"
                    width="150px"
            ></Button>
            <Button
                    @click.native="$emit('closeTab')"
                    name="Отменить"
                    color="#3BACB6"
                    width="150px"
            ></Button>


        </form>
    </div>
</template>

<script>
    import Button from "../../components/Button.vue"
    import http from "../../api/http-common.js"
    import store from "../../store/store.js"
    import router from "../../router/router";

    export default {
        name: "CreateTrainForm",
        components: {Button},
       // props: ['createTime', 'createDate'],
        data() {
            return {
                location: "",
                startDateTrain: null,
                //endDateTrain: null,
                startTimeTrain: "00:00",
                endTimeTrain: null
            }
        },
        methods: {
            async createTrain() {
                let d1 = this.startDateTrain.split(/\D/);
                let t1 = this.startTimeTrain.split(":");
                let d2 = this.startDateTrain.split(/\D/);
                let t2 = this.endTimeTrain.split(":");
                const utcDateStart = new Date(Date.UTC(d1[0], --d1[1], d1[2], t1[0], t1[1]))
                const utcDateEnd = new Date(Date.UTC(d2[0], --d2[1], d2[2], t2[0], t2[1]))

                await http.post('/train/create', {
                    startTrain: utcDateStart,
                    endTrain: utcDateEnd,
                    location: this.location,
                    trainerUsername: store.getters.getUsername
                })
                    .then((response) => {
                        router.push("/trainer/" + store.getters.getUsername + "/calendar")
                        window.location.reload()
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            setStartDateAndTime(date, time){
                this.startTimeTrain = time;
                this.startDateTrain = date
                console.log("UPDATEEE: " +  this.startDateTrain)
            }
        }
    }
</script>

<style scoped>
    label {
        display: block;
    }

    input,
    label {
        margin: 0.4rem 0;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    .qwerty {
        width: 300px;
        height: 400px;
        z-index: 1000;
        background: #2F8F9D;
        display: flex;
        flex-direction: column;
        align-items: center;
        border-radius: 10px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
        padding: 10px;
    }

    .row {
        display: flex;
        flex-direction: row;
    }
</style>