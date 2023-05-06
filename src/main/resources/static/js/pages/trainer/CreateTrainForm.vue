<template>
    <div>
        <form>
            <label for="start">Start train date:</label>
            <input type="date" id="start" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2025-12-31" required
                   v-model="startDateTrain">

            <label for="appt">Start train time:</label>
            <input type="time" id="appt" name="appt"
                   min="00:00" max="23:59" required
                   v-model="startTimeTrain">
            <label for="location"></label>
            <input type="text" id="location" v-model="location">
            <Button
                    @click.native="createTrain"
                    name="Создать"
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
        data() {
            return {
                location: "",
                startDateTrain: null,
                startTimeTrain: null
            }
        },
        methods: {
            async createTrain() {
                let d = this.startDateTrain.split(/\D/);
                let t = this.startTimeTrain.split(":");
                const utcDate = new Date(Date.UTC(d[0], --d[1], d[2], t[0], t[1]))

                await http.post('/train/create', {
                    startTrain: utcDate,
                    location:this.location,
                    trainerUsername:store.getters.getUsername
                })
                    .then((response) => {
                        router.push("/trainer/" + store.getters.getUsername + "/trainings")
                        window.location.reload()
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            }
        }
    }
</script>

<style scoped>
    label {
        display: block;
        font: 1rem 'Fira Sans', sans-serif;
    }

    input,
    label {
        margin: 0.4rem 0;
    }

</style>