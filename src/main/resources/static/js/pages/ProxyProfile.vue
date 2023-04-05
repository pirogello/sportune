<template>
    <div>
        <div v-if="role === 'USER'">
            <UserProfile :findUsername='username'></UserProfile>
        </div>
        <div v-if="role === 'TRAINER'">
            <TrainerProfile :findUsername='username'></TrainerProfile>
        </div>
        <div v-if="role === 'ORGANIZER'">

        </div>
    </div>
</template>

<script>
    import store from "../store/store.js"
    import roles from "../api/roles.js"
    import UserProfile from "./UserProfile.vue";
    import TrainerProfile from "./TrainerProfile.vue";

    export default {
        components: {UserProfile, TrainerProfile},
        data() {
            return {}
        },
        computed: {
            role() {
                return roles[store.getters.getRoles];
            },
            username(){
                return store.getters.getUsername;
            }
        }
    }
</script>

<style scoped>

</style>