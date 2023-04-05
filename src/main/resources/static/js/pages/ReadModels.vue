<template>
    <div>
        <Button
                @click.native="rout('/readUsers')"
                name="users"
                color="#3BACB6"
                width="120px"
        ></Button>

    </div>
</template>

<script>
    import Button from '../components/Button.vue'
    import router from "../router/router";

    export default {
        components: {Button},
        data(){
            return{
            }
        },
        methods: {
            rout(path) {
                router.push(path);
            },
        },
    }
</script>

<style scoped>

</style>