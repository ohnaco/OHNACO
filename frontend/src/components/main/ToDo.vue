<template>
  <div>
    <!-- 웹 -->
    <v-card style="width: 400; height: auto;" v-show="$vuetify.breakpoint.mdAndUp">
      <v-tabs
        v-model="tab"
        background-color="transparent"
        color="#607d8b"
        grow
      >
        <v-tabs-slider color="#607d8b"></v-tabs-slider>
        <v-tab
          v-for="web in webs"
          :key="web.title"
        >
          {{ web.title }}
        </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item
          v-for="web in webs"
          :key="web.title"
        >
          <v-card flat>
            <v-img :src="web.src"></v-img>
            <v-card-text>{{ web.content }}</v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
    <!-- 모바일 -->
    <!-- <v-card v-show="$vuetify.breakpoint.smAndDown">
      <v-tabs
        v-model="tab"
        background-color="transparent"
        color="#607d8b"
        grow
      >
        <v-tabs-slider color="#607d8b"></v-tabs-slider>
        <v-tab
          v-for="mobile in mobiles"
          :key="mobile.title"
        >
          {{ mobile.title }}
        </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item
          v-for="mobile in mobiles"
          :key="mobile.title"
        >
          <v-card flat>
            <v-card-text>{{ mobile.content }}</v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card> -->
  </div>
</template>

<script>
  export default {
    data () {
      return {
        tab: null,
        webs: [
          { 
            title: 'Todo생성', 
            src: require('@/assets/main/todo-update.gif'), 
            content: 'Todo 생성에서는 제공하는 카테고리를 선택 후 내용을 작성합니다. 목표 시간을 설정한 뒤 Todo 생성을 완료하면 재생버튼을 통해 타이머를 실행시킬 수 있습니다. 할일을 완료했다면 정지버튼을 클릭해 주세요.' 
          },
          { 
            title: 'todo이전', 
            src: require('@/assets/main/todo-past.gif'), 
            content: '과거의 Todo를 완료하지 못했거나 오늘 날짜에 추가하고 싶다면 해당 날짜의 Todo 리스트에서 플러스 버튼을 클릭해주세요. 오늘 날짜의 할일 목록에 추가됩니다.' 
          },
          { 
            title: '달력', 
            src: require('@/assets/main/todo-calendar.gif'), 
            content: '달력 아이콘을 클릭하면 나의 모든 할일 목록을 볼 수 있으며 해당 날짜의 Todo 리스트를 볼 수 있습니다.' 
          },
        ],
        // mobiles: [
        //   { title: 'Todo생성', src: require(), content: 'Tab 1 Content' },
        //   { title: '추가기능', src: require(), content: 'Tab 2 Content' },
        //   { title: '달력', src: require(), content: 'Tab 3 Content' },
        // ],
      }
    },
  }
</script>
