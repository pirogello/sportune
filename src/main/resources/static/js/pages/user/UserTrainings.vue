<template>
    <div class="userTrains">
            <div v-for="train in this.trains" :key="train.id">
                <Train
                :time=getTime(train.startTrain)
                :date=getDate(train.startTrain)
                :place=train.location
                :trainerName=train.trainer.username
                ></Train>
            </div>
    </div>
</template>

<script>
    import http from "../api/http-common.js";
    import store from "../store/store.js"
    import Train from "../components/Train.vue";

    export default {
        name: "UserTrainings",
        components: {Train},
        props:['username1'],
        data() {
            return {
                trains:[]
            }
        },
        methods: {
            async getTrains() {
                console.log(this.username1)
                await http.get('/findUser/'+ this.username1 + '/trains', {})
                    .then((response) =>{
                        console.log("response get")
                        this.trains = response.data
                        console.log(response.data)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            getTime(date){
                let time = new Date(date).toLocaleTimeString();
                return time;
            },
            getDate(date){
                let retDate = new Date(date).toLocaleDateString();
                return retDate;
            },

        },
        computed: {

        },
        created() {
            this.getTrains();
        }
    }
</script>

<style scoped>
.userTrains{
    background: #3BACB6;
    margin: 10px 50px;
    padding: 10px;
    min-height: 400px;
}
</style>