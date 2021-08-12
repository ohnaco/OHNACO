<template>
  <v-row>
    <v-col cols="2">
      <left-nav-bar></left-nav-bar>
    </v-col>
    <v-col cols="10" class="p-2" style="height: 100vh; overflow-y: scroll">
      <b-container class="bv-example-row" style="height: 120px">
        <h1
          style="
            font-family: 'GmarketSansMedium';
            font-size: 30px;
            color: #607d8b;
          "
        >
          질문 작성하기
        </h1>
        <div class="mb-3"></div>
        <span class="title_tag">제목</span>
        <b-form-input
          v-model="title"
          class="mb-3"
          placeholder="제목을 입력해주세요."
        ></b-form-input>
        <span class="title_tag">태그</span>
        <v-combobox
          v-model="chips"
          :items="items"
          chips
          clearable
          multiple
          solo
        >
          <template v-slot:selection="{ attrs, item, select, selected }">
            <v-chip
              v-bind="attrs"
              :input-value="selected"
              close
              color="#80DEEA"
              text-color="white"
              @click="select"
              @click:close="remove(item)"
            >
              <strong>{{ item }}</strong
              >&nbsp;
            </v-chip>
          </template>
        </v-combobox>
        <b-row class="mb-0">
          <b-col>
            <span class="md_exp">마크다운으로 작성할 수 있습니다.</span>
            <div>
              <b-form-textarea
                style="
                  height: 50vw;
                  border: 1px solid #ced4da;
                  border-radius: 5px;
                  background-color: #f3f3f3;
                  font-size: 12px;
                "
                v-model="content"
                placeholder="내용을 입력해주세요"
              ></b-form-textarea>
            </div>
          </b-col>
          <b-col>
            <span class="md_exp">미리보기</span>
            <div
              id="editor"
              style="
                position: relative;
                height: 50vw;
                border: 1px solid #ced4da;
                border-radius: 5px;
                font-size: 12px;
                overflow-y: scroll;
              "
              class="pt-2 pb-2"
            >
              <div
                v-html="compiledMarkdown"
                style="width: 100%; word-break: break-all"
              ></div>
            </div>
          </b-col>
        </b-row>
        <div
          class="mb-2"
          style="
            height: 15px;
            font-size: 12px;
            display: table-cell;
            vertical-align: middle;
          "
        >
          <img
            src="@/assets/images/qm.svg"
            style="
              width: 12px;
              height: 12px;
              float: left;
              margin: 2.8px 0;
              color: #607d8b;
            "
          />
          &nbsp;markdown 사용방법 링크
        </div>
        <v-btn
          elevation="2"
          class="mt-3 mb-3"
          style="font-family: GmarketSansMedium"
          @click="goBack()"
          >취소</v-btn
        >
        <v-btn
          elevation="2"
          class="mt-3 mb-3"
          style="font-family: GmarketSansMedium; float: right"
          @click="onCreateOK()"
          >등록</v-btn
        >
      </b-container>
    </v-col>
  </v-row>
</template>
<script>
import marked from "marked";
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import { createNamespacedHelpers } from "vuex";
const userHelper = createNamespacedHelpers("userStore");
const devtalkHelper = createNamespacedHelpers("devTalkStore");

export default {
  name: "App",
  components: {
    LeftNavBar,
  },
  data() {
    return {
      title: "",
      content: "",
      chips: [],
      items: ['JAVA', 'Python','Spring','C','Vue.js','React'],
    };
  },
  computed: {
    computed: {
    ...userHelper.mapState(["user"]),
  },
    compiledMarkdown: function () {
      return marked(this.content, { sanitize: true });
    },
  },
  methods: {
    ...devtalkHelper.mapActions(["addQuestion"]),
    remove (item) {
      this.chips.splice(this.chips.indexOf(item), 1)
      this.chips = [...this.chips]
    },
    goBack(){
      this.$router.push("devtalk");
    },
    onCreateOK() {
      const newQuestion = {
        questiontitle: this.title,
        questioncontent: this.content,
        tagName: this.chips,
      }
      this.addQuestion(JSON.stringify(newQuestion));
      setTimeout(() => {
  this.goBack();
}, 2000);
    },
  },
};
</script>
<style>
@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "GmarketSansBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "GmarketSansLight";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.filter_devTalk {
  font-family: GmarketSansMedium;
  font-size: 14px;
  font-weight: lighter;
  height: 100%;
  color: #607d8b;
}

.tag_section {
  font-family: GmarketSansMedium;
  font-size: 16px;
  font-weight: lighter;
  color: #607d8b;
}

.title_tag {
  font-family: GmarketSansMedium;
  font-size: 16px;
  font-weight: lighter;
  color: #607d8b;
}

.md_exp {
  font-family: GmarketSansLight;
  font-size: 14px;
  font-weight: lighter;
  color: #607d8b;
}
</style>
