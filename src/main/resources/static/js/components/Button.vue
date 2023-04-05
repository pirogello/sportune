<template>
    <div class="my-button">
        <!--        <router-link :to="link" class="button" :style="style">-->
        <!--            {{name}}-->
        <!--        </router-link>-->
        <div class="button" :style="style">
            {{name}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "Button",
        props: ['name', 'color', 'width', 'height'],
        computed: {
            style() {
                return {
                    '--background-color': this.color,
                    '--min-width': this.width,
                    '--min-height' : this.height,
                }
            }
        }
    }
</script>

<style scoped>
    .button {
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.08);
        -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.08);
        box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.08);
        color: #fff;
        display: block;
        min-width: var(--min-width);
        min-height: var(--min-height);
        text-align: center;
        font-family: Arial, Helvetica, sans-serif;
        font-size: 14px;
        padding: 8px 16px;
        margin: 10px 10px;
        text-decoration: none;
        text-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
        -webkit-transition: background-color 0.1s linear;
        -moz-transition: background-color 0.1s linear;
        -o-transition: background-color 0.1s linear;
        transition: background-color 0.1s linear;
    }

    .button {
        background-color: var(--background-color);
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
    }

    .button:hover {
        background-color: #82DBD8 !important;
    }

    @media (max-width: 500px) {
        .button {
            font-size: 12px;
            min-width: 70px;
        }
    }
</style>
