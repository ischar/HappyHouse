<template>
    <div> 
        <p>{{ this.$route.params.favorite.aptCode }}</p>
        <p>{{  }}</p>
        <p>{{  }}</p>
        <p>{{  }}</p>
    </div>
</template>

<script>
    export default {
        name: 'FavoriteDetail',
    

    data() {
        return {
            favorite: {},
        }
    }
};
</script>