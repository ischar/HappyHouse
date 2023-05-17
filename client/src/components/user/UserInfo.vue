<template>
  <div>
    {{ this.store.$state.isLogin }}
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
