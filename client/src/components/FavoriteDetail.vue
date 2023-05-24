<!--<template>
    <div> 
        ddd안녕..
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
</script>-->

<template>
    <div>sfsd</div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'FavoriteDetail',
    data() {
        return {
            data: {},
            list: [],
        };
    },
    created() {
        this.listFavorites();
    },
    methods: {
        listFavorites() {
            axios({
                method: 'get',
                url: 'http://localhost:80/favorite/lists',
            }).then((response) => {
                this.data = response.data;
                if (this.data.length != 0) {
                    for (var i=0; i< this.data.length; i++) {
                        this.getAptList(this.data[i].aptCode);
                    }
                }
            });
        },

        getAptList(aptCode) {
            axios({
                method:'post',
                url:"http://localhost:80/houses/search/house",
                params: {
                    aptCode: aptCode,
                },
                responseType: "json",
            }).then((response) => {
                this.list.push(response.data);
                
            });
        }
    },
};
</script>

<style scoped></style>