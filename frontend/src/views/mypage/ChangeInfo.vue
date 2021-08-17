<template>
  <div>
    <TopNavBar/> 
    <!-- 회원정보 수정 폼 -->
    <div class="update-profile-form mt-15">
      <div class="update-profile-name">정보 수정</div>
      <div class="update-profile-box">
        <!-- 사진 미리보기 및 변경 -->
        <div class="profile-circle">
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
        <div class="d-flex justify-space-between mb-8">
          <!-- 회원탈퇴 -->
          <div class="mr-8">
            <v-btn
              text
              @click="showDialog"
            >
              <img src="@/assets/images/delete-user.svg" alt="back" />
            </v-btn>
            <v-dialog
              max-width="500"
              v-model="isModal"
            >
              <DeleteUserModal
                @hide="hideDialog"
                @submit="deleteUserInfo"
              />
            </v-dialog>
          </div>
          <!-- 비밀번호 변경 -->
          <div class="ml-8">
            <v-btn 
              text 
              @click="gochangePwd">
              <img src="@/assets/images/change-pwd-btn.svg" alt="change-pwd"/>
            </v-btn>
          </div>
        </div>
        <!-- 버튼 -->
        <div class="page-btn">
          <!-- 이전 페이지: 마이페이지 -->
          <button 
            class="mr-15" 
            @click="goMypage"
          >
            <img src="@/assets/images/cancel-btn.svg" alt="back" />
          </button>
          <!-- 다음 페이지 : 마이페이지 -->
          <button
            class="ml-15"
            @click="imageUpload"
          >
            <img src="@/assets/images/complete-btn.svg" alt="next" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPage from "@/api/MyPage";
import TopNavBar from "@/components/common/TopNavBar.vue";
import DeleteUserModal from "@/components/mypage/DeleteUserModal.vue";
import AWS from 'aws-sdk';

export default {
  name: "ChangeInfo",
  components: {
    TopNavBar,
    DeleteUserModal,
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
      isUpload: 'nomal',
      isModal: false,
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
    showDialog() {
      this.isModal = true
    },
    hideDialog() {
      this.isModal = false
    },
    checkForm: function () {
      if (this.nickname == this.originnickname)
        this.isCheck = true;
      else 
        if (this.nickname.length <= 1 || this.nickname.length >= 9)
          this.error.nickname = "2자리 이상 8자리 이하 닉네임을 입력해주세요."
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
        this.isUpload = 'upload';
      }
    },
    imageUpload: function () {
      if (this.isSubmit && this.isCheck) {
        if(this.isUpload == 'upload') {
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

          s3.upload({
            Key: photoKey,
            Body: this.file,
            ACL: 'public-read'
          }, (err, data) => {
            if(err) {
              console.log(err)
            } else {
              console.log(data)
              this.image = "https://ohnaco.s3.ap-northeast-2.amazonaws.com/" + this.email + ".jpg?test="+Math.random().toString(36).substring(2, 11);
              this.updateProfile()
            }
          });
        } else if(this.isUpload == 'default') {
          this.image = "https://ohnaco.s3.ap-northeast-2.amazonaws.com/defaultProfile"
          this.updateProfile()
        } else {
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
      this.isUpload = 'default'
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
    deleteImage: function (res) {
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
        Key: this.email + ".jpg"
      }, (err, data) => {
        if(err) {
          console.log(err)
        } else {
          console.log(data)
        }
      });
      console.log(res)
    },
    deleteUserInfo: function () {
      MyPage.deleteUser(
        (res) => {
          console.log(res)
          this.hideDialog()
          this.deleteImage()
          localStorage.removeItem('jwt-access-token')
          alert('회원 탈퇴가 정상적으로 완료되었습니다.')
          this.$router.push({ name: 'Main' })
        },
        (err) => {
          console.log(err)
        }
      )
    }
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
.profile-circle {
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
.profile-circle input[type="file"] {
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
