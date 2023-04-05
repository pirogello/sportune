<template>
    <div>
        <div v-if="this.user">
            <div v-if="this.user.role === 'USER'">
                <UserProfile :findUsername='this.user.username'></UserProfile>
            </div>
            <div v-if="this.user.role === 'TRAINER'">
                <TrainerProfile :findUsername='this.user.username'></TrainerProfile>
            </div>
            <div v-if="this.user.role === 'ORGANIZER'">
            </div>
        </div>
    </div>
</template>

<script>
    import store from "../store/store.js"
    import roles from "../api/roles.js"
    import router from "../router/router.js";
    import UserProfile from "./UserProfile.vue";
    import TrainerProfile from "./TrainerProfile.vue";
    import http from "../api/http-common.js";

    export default {
        components: {UserProfile, TrainerProfile},
        data() {
            return {
                user: null,
            }

        },
        methods: {
            async findUser() {
                await http.get('/findUser/find/' + router.currentRoute.params.id, {})
                    .then((response) => {
                        console.log(JSON.parse(JSON.stringify(response.data)))
                        this.user = JSON.parse(JSON.stringify(response.data));
                    })
                    .catch(function (error) {
                        console.log("error")
                        console.log(error)
                    });
            },
        },
        created() {
            this.findUser();
        }

    }
</script>

<style scoped>

</style>