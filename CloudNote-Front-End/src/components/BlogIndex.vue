<template>
  <el-main>
    <el-row :gutter="10">
      <el-col :xs="24" :sm="12" :md="8" :lg="8" :xl="6" v-for="ele in blog" :key="ele">
        <div id="con" class="grid-content bg-purple border">
          <el-container>
            <el-header>
              <h1 class="border">{{ele.title}}</h1>
            </el-header>
            <el-main class="border">
              <h3 class="border">{{ele.introduction}}</h3>
              <h3>{{ele.content}}</h3>
            </el-main>
          </el-container>
        </div>
      </el-col>
    </el-row>
  </el-main>
</template>

<script>
export default {
name: "BlogIndex",
  mounted(){
    this.axios.post("http://localhost:9501/blog/getBlogsOfAll",
        {
          pageAndSizeDTO: {
            index:1,
            stepSize:8
          }
        }).then((response)=> {
      this.blog=response.data;
    })
  },
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',

      blog:{
        "id": null,
        "title": null,
        "introduction": null,
        "content": null,
        "createTime": null,
        "updateTime": null,
        "image": null,
        "ownerId": null,
        "crowdId": null
      }

    }
  },
}
</script>

<style scoped>
.bg-purple {
  background: #FFFFFF;
}
.grid-content {
  border-radius: 4px;
  margin: 10px;
  height: 400px;
}
.border {
  border: 2px solid #39F;
}
</style>