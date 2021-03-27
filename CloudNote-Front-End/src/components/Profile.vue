<template>
  <div>
      <el-image
          style="height: 50px;width: 50px;border-radius: 25px;"
          :src="url"
          fit="cover"></el-image>
  </div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      msg: 'zzzz'
    }
  }
}
</script>

<style scoped>
</style>