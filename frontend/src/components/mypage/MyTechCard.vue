<template>
  <v-container>
    <v-card-actions>
      <div class="mr-4">
        내 스크랩 ({{ scrapCount }}) 
      </div>
      <v-btn 
        @click="goMyScraps"
        icon 
        style="margin-left:20px;"
      >
        <v-img src="@/assets/images/mypage-all-btn.svg" alt="show-all"></v-img>      </v-btn>
    </v-card-actions>
    <!-- 스크랩 간단 모아보기 -->
    <v-simple-table class="elevation-2">
      <template v-slot:default>
        <tbody>
          <tr
            v-for="(scrap, i) in scraps"
            :key="i"
            @click="goBlog(scrap)"
            style="cursor: pointer;"
          >
            <td align="center">
              <v-img 
                  :src="require(`@/assets/images/${ scrap.image }.png`)" 
                  alt="blog-img"
                  max-height="120"
                  max-width="120"
                ></v-img>
            </td>
            <td class="pl-0">
              <div class="mt-3">
                <b>{{ scrap.title }}</b>
              </div>
              <div class="mt-5" v-html="cutcontent(scrap.content)">
              </div>
              <div class="tech-date mt-2 mb-2">
                {{ scrap.publisheddate | datetime }}
              </div>
            </td>
            <v-divider></v-divider>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import moment from "moment"

export default {
  name: 'MyTechCard',
  props: {
    scrapCount: {
      type: Number
    },
    scraps: {
      type: Array
    }
  },
  filters: {
    datetime: function (value) {
      if (value)
        return moment(String(value)).format('YYYY.MM.DD')
    },
  },
  methods: {
    goMyScraps: function () {
      this.$router.push({ name: "MyTechScraps" });
    },
    goBlog: function (scrap) {
      window.open(scrap.link)
    },
    cutcontent: function (value) {
      return value.substr(0, 50) + '...'
    }
  },
}
</script>

<style scoped>
.tech-date {
  display: flex;
  justify-content: flex-end;
}
</style>