<template>
    <div class="post">
        <div class="post_header">
            <div class="posts_text">Today was my first day in the gym!</div>
            <div class="posts_settings">
                <div class="dot"></div>
                <div class="dot"></div>
                <div class="dot"></div>
            </div>
        </div>
        <img src="https://img.freepik.com/free-photo/sports-blonde-woman-in-a-sportswear-training-in-a-gym_1157-31068.jpg?w=740&t=st=1669175912~exp=1669176512~hmac=255c7da8bc0640090a38d180ab202ff5e688fec40ffe42268e40f3ec5d190af5"
             alt="" class="post_picture">
        <div class="post_footer">
            <div class="post_likes">
                <img src="https://s3-alpha-sig.figma.com/img/513b/981d/6bd5dac4ba3c377963b1bd86f71dc9e3?Expires=1682899200&Signature=KDmX64rQNXysk7Vhl46GiSPYg1v~080SfR2OXUjes4aW3Ijusi9vYQM-~b19iVL5qkb6kgrbwym4isa9T6XZpT9Bydm7ZDpSllXdXXc5YfbFtP11afgeDnUbNM1Bz7QgWYYQIU-FjOsq9Ck4gJTA~nzslRG62iArEiAG4RAzTMC5pe35KRf954zilj3K6MjENbehKby5uYJIAgGG5aSx64h5H6wlbjVFGv-9w67MBgejAH51DhxADa69WvdvS7xM4a0xS-me7-0Oowv3AZAX9ksNRWxooKFC9YTwCsuy2Bg45JGi2Y-rvWSiQfX-3KYOPQ6aa6rZZjybbe-2UX1t-w__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
                     alt="" class="like_picture">
                123
            </div>
            <div class="post_date">20.03.2021</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Post"
    }
</script>

<style scoped>

    .post{
        background-color: #3BACB6;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 0 0 20px 0;
    }
    .post_header{
        width: 95%;
        display: flex;
        justify-content: space-between;
        margin: 20px 20px;
    }

    .posts_text{
       color: #fff;
        font-size: 19px;
    }

    .post_picture{
        margin: 0 20px;
        width: 95%;
        height: 300px;
        border-radius: 10px;
    }

    .post_footer{
        width: 95%;
        display: flex;
        justify-content: space-between;
        margin: 10px 20px;
        color: #B3E8E5;
    }
    .posts_settings {
        display: flex;
        cursor: pointer;
    }
    .dot {
        width: 7px;
        height: 7px;
        border-radius: 7px;
        background: #B3E8E5;
        margin: 2px;
    }

    .like_picture{
        width: 24px;
        height: 24px;
        cursor: pointer;
    }
    .post_likes{
        font-size: 24px;
        padding: 0 5px;
        display: flex;
        justify-content: space-between;
        width: 75px;
    }
</style>