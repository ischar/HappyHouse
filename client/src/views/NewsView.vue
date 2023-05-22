<template>
    <div style="margin-top: 100px;">
        <h2 v-html="$route.params.newss.title" style="text-align:center;"></h2>
        <div style="margin-left: 20%; margin-right: 20%;">
            <p style="color: #828282;">{{  $route.params.newss.pubDate}}</p>
        <p v-html="$route.params.newss.description"></p>
        <p style="color:#828282;" v-html="'원문보기:'+$route.params.newss.originallink"></p>
        </div>
        <div>

        </div>
    </div>  
</template>

