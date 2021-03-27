<template>
  <div class="hello">
    <header>
      <NavMenu/>
    </header>
    <el-container>
      <BlogIndex/>
    </el-container>
    <el-pagination
        style="margin: 0 auto;"
        small
        layout="prev, pager, next"
        :total="50">
    </el-pagination>
  </div>
</template>

<script>
import NavMenu from "@/components/NavMenu";
import BlogIndex from "@/components/BlogIndex";

export default {
  name: 'Main',
  components: {BlogIndex, NavMenu},
  comments: {
    NavMenu,
    BlogIndex
  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
