<template>
    <div id="page-top">
        <!-- Navigation-->
        <MainNav />
        <!-- Header-->
        <header class="masthead text-center text-white">
          <div class="masthead-content">
            <div class="container px-5">
              <h3 class="masthead-heading mb-0">OHNACO</h3>
              <h4 class="masthead-subheading mb-0">오늘도 나는 코딩한다</h4>
              <a class="btn btn-light btn-xl rounded-pill mt-5" style="color: #607d8b; font-family: GmarketSansMedium;" href="#scroll">How To Use</a>
              <router-link class="d-flex justify-content-center mt-2" style="color: white; font-family: GmarketSansLight;" to="/login">로그인</router-link>
            </div>
          </div>
        </header>
        <!-- intro -->
        <section id="scroll">
          <div class="container px-5 page-content">
            <div class="row gx-5 align-items-center">
              <div class="col-lg-6">
                <div class="d-flex justify-content-center"><img src="@/assets/images/full-logo.svg" alt="logo"></div>
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <h4 class="display-title">오나코는?</h4>
                  <p>개발자 취준생의 효율적인 공부를 위해 Todo 항목별 타이머 기능과 기록된 공부시간을 활용한 통계 기능을 제공합니다. 
                    그 뿐만 아니라, 최신 IT 정보를 담은 기술 블로그와 뉴스를 모아서 보여주고 사용자들끼리 질의응답을 할 수 있는 공간도 제공합니다.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </section>
        <!-- todo -->
        <section id="" style="background: #607D8B">
            <div class="container px-5 page-content">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6 order-lg-2">
                        <div class="">
                          <ToDo/>
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="p-5" style="color: white;">
                            <h4 class="display-title">To Do</h4>
                            <p>
                              개발자로서 공부해야할 항목을 제공하며 직접 Todo List를 작성하여 타이머 기능을 이용할 수 있습니다.
                              오늘 하루 GitHub commit을 실시간으로 확인 할 수 있으며 기록된 공부 시간은 통계 자료로 활용되어 제공됩니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- stats -->
        <section id="" style="">
          <div class="container px-5 page-content">
            <div class="row gx-5 align-items-center">
              <div class="col-lg-6">
                <div class="pa-3">
                  <img class="img-fluid" src="@/assets/main/statistics.gif" alt="statistics" />
                </div>
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <h4 class="display-title">Statistics</h4>
                  <p>
                    통계 페이지에서는 공부 시간에 대한 주간, 월간 통계 자료를 제공합니다. 
                    목표시간과 실제시간, 지난 2일간 공부시간, 전체 유저들과 같은 직무를 선택한 유저들의 공부시간을 비교하는 자료를 제공하며 일주일간 공부 기록을 비교하여 보여줍니다.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </section>
        <!-- tech -->
        <section id="" style="background: #607D8B">
            <div class="container px-5 page-content">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6 order-lg-2">
                        <div class="pa-3">
                          <Tech/>
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="p-5" style="color: white;">
                            <h4 class="display-title">Tech</h4>
                            <p>
                              유명 IT 기업들의 기술 블로그 목록을 통해 블로그 페이지로 이동할 볼 수 있으며, 
                              스크랩 기능을 통해 최신 IT 글을 모아볼 수 있어 개발자로서의 역량을 키우는데 도움이 됩니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- devtalk -->
        <section id="">
            <div class="container px-5 page-content">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6">
                        <div class="">
                          <Devtalk/>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="p-5">
                            <h4 class="display-title">Dev Talk</h4>
                            <p>
                              다양한 유저들과 질문을 하며 소통할 수 있는 공간입니다. 
                              공부를 하며 질문을 할 수 있고 태그를 비롯한 다양한 검색 기능과 편리한 코드 작성을 위한 markdown을 제공합니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- mypage -->
        <section id="" style="background: #607D8B">
          <div class="container px-5 page-content">
            <div class="row gx-5 align-items-center">
              <div class="col-lg-6 order-lg-2">
                <div class="pa-3">
                  <Mypage/>
                </div>
              </div>
              <div class="col-lg-6 order-lg-1">
                <div class="p-5" style="color: white;">
                  <h4 class="display-title">MyPage</h4>
                  <p>
                    GitHub API를 사용하여 나의 GitHub로 이동이 가능하며 일주일간 나의 commit 기록과 Dev Talk에서의 나의 모든 활동 및 스크랩을 확인 할 수 있습니다.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </section>
    <!-- Footer-->
    <footer class="py-5 bg-black">
      <div class="container px-5">
        <p class="m-0 text-center text-white small">SSAFY 5TH A101</p>
      </div>
    </footer>
  </div>
</template>

<script>
import "animate.css"
import MainNav from "@/components/main/MainNav.vue";
import ToDo from "@/components/main/ToDo.vue";
import Tech from "@/components/main/Tech.vue";
import Devtalk from "@/components/main/Devtalk.vue";
import Mypage from "@/components/main/Mypage.vue";

export default {
  name: "Main",
  components: {
    MainNav,
    ToDo,
    Tech,
    Devtalk,
    Mypage,
  }
};
</script>

<style scoped>
@import url("./main.css");
</style>
