<template>
  <div>
    <TopNavBar/> 
    <!-- 회원정보 수정 폼 -->
    <div class="update-profile-form mt-15">
      <div class="update-profile-name">정보 수정</div>
      <div class="update-profile-box">
        <div>
          <img src="@/assets/images/profile-img.svg" alt="ohnaco-logo" />
        </div>
        <!-- 닉네임 -->
        <input
          type="text"
          id="nickname"
          class="nickname"
          placeholder="닉네임*"
          @input="nicknameCheck"
          v-model="nickname"
          v-bind:class="{
            error: error.nickname && error.nicknameCheck,
            complete:
              !error.nickname && nickname.length !== 0 && error.nicknameCheck,
          }"
        />
        <div class="error-message" v-if="error.nickname">
          {{ error.nickname }}
        </div>
        <div class="error-message" v-if="error.nicknameCheck">
          {{ error.nicknameCheck }}
        </div>
        <!-- 깃헙 아이디 -->
        <input
          type="text"
          id="github"
          class="github"
          placeholder="Github ID"
          v-model="githubid"
        />
        <!-- 희망 직무 -->
        <select v-model="position" class="form-select">
          <option value="" disabled>희망직무</option>
          <option value="Backend">Backend</option>
          <option value="Frontend">Frontend</option>
          <option value="Android">Android</option>
          <option value="IOS">IOS</option>
          <option value="SI">SI</option>
          <option value="Embedded">Embedded</option>
          <option value="Game">Game</option>
          <option value="etc.">etc.</option>
        </select>
        <!-- 비밀번호 변경 -->
        <div class="mr-auto ml-10 mt-2 mb-8">
          <button @click="gochangePwd">
            <img src="@/assets/images/change-pwd-btn.svg" alt="change-pwd"/>
          </button>
        </div>
        <!-- 버튼 -->
        <div class="page-btn">
          <!-- 이전 페이지: 마이페이지 -->
          <button 
            class="mr-15" 
            @click="goMypage"
          >
            <img src="@/assets/images/back-btn.svg" alt="back" />
          </button>
          <!-- 다음 페이지 : 마이페이지 -->
          <button
            class="ml-15"
            @click="updateProfile"
          >
            <img src="@/assets/images/next-btn.svg" alt="next" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPage from "@/api/MyPage";
import TopNavBar from "@/components/common/TopNavBar.vue";

export default {
  name: "ChangeInfo",
  components: {
    TopNavBar,
  },
  data: function () {
    return {
      email:'',
      originnickname: '',
      nickname: '',
      githubid: '',
      position:'',
      error: {
        nickname: false,
        nicknameCheck: false,
      },
      isSubmit: false,
      isCheck: false,
    };
  },
  created() {
    this.component = this;
    this.getInfo()
  },
  watch: {
    nickname: function () {
      this.checkForm();
    },
  },
  methods: {
    goMypage: function () {
      this.$router.push({ name: "MyPage" });
    },
    gochangePwd: function () {
      this.$router.push({ name: "ChangePwd" });
    },
    checkForm: function () {
      if (this.nickname == this.originnickname)
        this.isCheck = true;
      else 
        if (this.nickname.length <= 1)
          this.error.nickname = "두글자 이상 닉네임을 입력해주세요"
        else this.error.nickname = false;
        let isSubmit = true;
        Object.values(this.error).map((v) => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
    },
    getInfo: function () {
      MyPage.requestMyInfo(
        (res) => {
          this.email = res.data.info.email
          this.originnickname = res.data.info.nickname
          this.nickname = res.data.info.nickname
          this.githubid = res.data.info.githubid
          this.position = res.data.info.position
        },
        (err) => {
          console.log(err)
        }
      )
    },
    nicknameCheck: function () {
      let data = {
        nickname: this.nickname,
      };
      if (this.nickname != this.originnickname) {
        MyPage.requestMypageNicknameCheck(
          data, 
          (res) => {
            console.log(res);
            if (res.data.status) {
              this.error.nicknameCheck = null;
              this.isCheck = res.data.status;
            } else 
              this.error.nicknameCheck = res.data.message;
              this.isCheck = res.data.status;
          }),
          (err) => {
            console.log(err);
          };
      }
    },
    updateProfile: function () {
      if (this.isSubmit && this.isCheck) {
        let data = {
          nickname: this.nickname,
          githubid: this.githubid,
          position: this.position,
        };
        this.isSubmit = false;
        MyPage.updateMyInfo(
          data,
          (res) => {
            console.log(res);
            this.isSubmit = true;
            alert("정보 수정이 완료되었습니다.");
            this.$router.push({ name: "MyPage" });
          },
          (err) => {
            console.log(err);
            this.isSubmit = true;
          }
        );
      }
    },
  },
};
</script>

<style scoped>
.update-profile-form {
  display: grid;
  place-items: center;
}
.update-profile-name {
  width: 109px;
  height: 27px;
  flex-grow: 0;
  margin: 0 178px 10px 177px;
  font-family: GmarketSansTTF;
  font-size: 18px;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: center;
  color: #607d8b;
}
.update-profile-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 300px;
  margin: 10px 0 0;
  padding: 32px 0px 19px;
  border-radius: 10px;
  border: solid 1px #607d8b;
  background-color: rgba(255, 255, 255, 0);
}
.nickname {
  width: 230px;
  height: 30px;
  margin: 30px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.error-message {
  font-family: GmarketSansTTF;
  font-size: 10px;
  color: crimson;
}
.github {
  width: 230px;
  height: 30px;
  margin: 10px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.form-select {
  width: 230px;
  height: 30px;
  margin: 10px 0 8px 0px;
  padding: 0px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  padding: 0px 16.6px 0px 17.2px;
  font-family: GmarketSansTTF;
  font-size: 15px;
  font-weight: 300;
  cursor: pointer;
}
input::placeholder {
  font-family: GmarketSansTTF;
  font-size: 15px;
  font-weight: 300;
  color: #c6c8c9;
}
.page-btn {
  margin: 5px;
  cursor: pointer;
}
</style>
