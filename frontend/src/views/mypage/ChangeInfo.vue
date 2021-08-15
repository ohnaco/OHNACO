<template>
  <div>
    <TopNavBar/> 
    <!-- 회원정보 수정 폼 -->
    <div class="update-profile-form mt-15">
      <div class="update-profile-name">정보 수정</div>
      <div class="update-profile-box">
        <!-- 사진 미리보기 및 변경 -->
        <div class="profile_circle">
          <label for="profile" class="imagebtn">
            <img src="@/assets/images/profile-btn.svg" alt="sample_profile">
          </label>
          <input
            type="file"
            id="profile"
            @change="previewImage"
            accept="image/*"
            style="display:none"
          />
          <div v-if="image.length > 0">
            <img class="preview" :src="image" />
          </div>
        </div>
        <button @click="resetProfile">
          <img src="@/assets/images/reset-profile.svg" alt="">
        </button>
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
        <div class="d-flex justify-space-between mt-3 mb-8">
          <!-- 회원탈퇴 -->
          <div class="mr-13">
            <button @click="deleteUser">
              <img src="@/assets/images/delete-user.svg" alt="delete-user"/>
            </button>
          </div>
          <!-- 비밀번호 변경 -->
          <div class="ml-13">
            <button @click="gochangePwd">
              <img src="@/assets/images/change-pwd-btn.svg" alt="change-pwd"/>
            </button>
          </div>
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
            @click="imageUpload"
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
import AWS from 'aws-sdk';

export default {
  name: "ChangeInfo",
  components: {
    TopNavBar,
  },
  data: function () {
    return {
      file: null,
      albumBucketName: process.env.VUE_APP_ALBUMBUCKETNAME,
      bucketRegion: process.env.VUE_APP_AWS_CONFIG_REGION,
      IdentityPoolId: process.env.VUE_APP_IDENTITYPOOLID,
      email:'',
      originnickname: '',
      nickname: '',
      githubid: '',
      position:'',
      image: '',
      error: {
        nickname: false,
        nicknameCheck: false,
      },
      isSubmit: false,
      isCheck: false,
      isUpload: false,
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
          console.log("test" + res.data.info.image)
          if(res.data.info.image != null) {
            this.image = res.data.info.image
          }
        },
        (err) => {
          console.log(err)
        }
      )
    },
    previewImage: function(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        this.file = input.files[0];
        var reader = new FileReader();
        reader.onload = (e) => {
          this.image = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
        this.isUpload = true;
      }
    },
    imageUpload() {
      if (this.isSubmit && this.isCheck) {
        if(this.isUpload) {
          AWS.config.update({
            region: this.bucketRegion,
            credentials: new AWS.CognitoIdentityCredentials({
              IdentityPoolId: this.IdentityPoolId
            })
          });
          var s3 = new AWS.S3({
            apiVersion: '2006-03-01',
            params: {
              Bucket: this.albumBucketName
            }
          });
          let photoKey = this.email + ".jpg";
          console.log("3=>" + this.albumBucketName)
          if (!this.isDefault) {
            s3.upload({
              Key: photoKey,
              Body: this.file,
              ACL: 'public-read'
            }, (err, data) => {
              if(err) {
                console.log(err)
              } else {
                console.log(data)
                this.image = "https://ohnaco.s3.ap-northeast-2.amazonaws.com/" + this.email + ".jpg"
                this.updateProfile()
              }
            });
          }
        } else {
          this.image = "https://ohnaco.s3.ap-northeast-2.amazonaws.com/defaultProfile"
          this.updateProfile()
        }
      }
    },
    updateProfile: function () {
      let data = {
        nickname: this.nickname,
        githubid: this.githubid,
        position: this.position,
        image: this.image,
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
    },
    resetProfile: function () {
      this.image = "https://ohnaco.s3.ap-northeast-2.amazonaws.com/defaultProfile"
      this.isUpload = false
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
    deleteUser: function (res) {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });

      var s3 = new AWS.S3({
        apiVersion: '2006-03-01',
        params: {
          Bucket: this.albumBucketName
        }
      });

      s3.deleteObject({
        Key: this.email+".jpg"
      }, (err, data) => {
        if(err) {
          console.log(err)
        } else {
          console.log(data)
        }
      });
      console.log(res)
    },
  },
};
</script>

<style scoped>
.update-profile-form {
  display: flex;
  flex-direction: column;
  align-items: center;
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
.profile_circle {
  position: relative;
  width: 169px;
  height: 169px;
  border-radius: 50%;
  background: #eceff1;
  border: 1px solid #eceff1;
  display: inline-block;
}
.imagebtn {
  position: absolute;
  left: 75%;
  top: 85%;
  z-index: 2;
  cursor: pointer;
}
.preview {
  position: absolute;
  width: 169px;
  height: 169px;
  border-radius: 50%;
  background: #eceff1;
  border: 1px solid #eceff1;
}
.profile_circle input[type="file"] {
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
}
.nickname {
  width: 230px;
  height: 30px;
  margin: 20px 1px 10px 0;
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
