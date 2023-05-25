<template>
  <div id="app">
    <header-nav-bar />
    <hr style="color: #d86057; margin-bottom: 0px;">
    <router-view />
  </div>
</template>

<style>
#app {
  /* background-image: url("assets/background.jpg");
  background-size: cover;
  @import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500&display=swap"); */
  font-family: "Sunflower", sans-serif;
  font-size: 16px;
}
</style>

<script>
import HeaderNavBar from "@/components/header/HeaderNavBar.vue";

export default {
  name: "App",
  components: {
    HeaderNavBar,
  },
};
</script>

