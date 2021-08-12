<template>
  <v-container>
    <v-card-actions>
      <div class="mr-4">
        내 스크랩 ({{ scrapCount }}) 
      </div>
      <v-btn 
        @click="goMyScraps"
        icon 
        style="margin-left:20px"
      >
        <v-img src="@/assets/images/mypage-all-btn.svg" alt="show-all"></v-img>      </v-btn>
    </v-card-actions>
    <v-simple-table class="elevation-2">
      <template v-slot:default>
        <tbody>
          <tr
            v-for="(scrap, i) in scraps"
            :key="i"
          >
            <td align="center" class="pr-0">
              <v-list-item-avatar
                tile
                size="80"
              >
              <img src=""  alt="">
              </v-list-item-avatar>
            </td>
            <td class="pl-0">
              <v-list-item two-line>
                <v-list-item-content>
                  <v-list-item-title class="text-h5 mb-1">
                    {{ scrap.title }}
                  </v-list-item-title>
                  <v-list-item-subtitle>{{ scrap.content }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </td>
            <td>{{ scrap.answerdate | datetime }}</td>
            <v-divider></v-divider>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import moment from 'moment'

export default {
  name: 'MyQuestionCard',
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
    }
  },
  methods: {
    goMyScraps: function () {
      this.$router.push({ name: "MyTechScraps" });
    },
  }
}
</script>

<style>

</style>