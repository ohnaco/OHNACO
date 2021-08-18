<template>
    <div id="page-top">
        <!-- Navigation-->
        <MainNav/>
        <!-- Header-->
        <header class="masthead text-center text-white">
          <div class="masthead-content">
            <div class="container px-5">
              <h3 class="masthead-heading mb-0">OHNACO</h3>
              <h4 class="masthead-subheading mb-0">오늘도 나는 코딩한다</h4>
              <a class="btn btn-light btn-xl rounded-pill mt-5" style="color: #607D8B" href="#scroll">How To Use</a>
            </div>
          </div>
        </header>
        <!-- Content section 1-->
        <section id="scroll">
          <div class="container mt-5 mb-5">
            <div class="row gx-5 align-items-center">
              <div class="col-lg-6">
                <div class="p-5 d-flex justify-content-center"><img src="@/assets/images/full-logo.svg" alt="logo"></div>
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <h4 class="display-6">오나코 서비스를 소개합니다.</h4>
                  <p>개발자 취준생의 효율적인 공부를 위해 Todo 항목별 타이머 기능과 기록된 공부시간을 활용한 통계 기능을 제공합니다. 
                    그 뿐만 아니라, 최신 IT 정보를 담은 기술 블로그와 뉴스를 모아서 보여주고 사용자들끼리 질의응답을 할 수 있는 공간도 제공됩니다.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </section>
        <!-- Content section 2-->
        <section id="" style="background: #607D8B">
            <div class="container px-5">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6 order-lg-2">
                        <div class="p-5">

                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="p-5">
                            <h4 class="display-6">To Do</h4>
                            <p>

                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Content section 3-->
        <section id="" style="">
            <div class="container px-5">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6">
                        <div class="">
                          <img class="img-fluid" src="@/assets/images/statistics-page.gif" alt="..." />
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="p-5">
                            <h4 class="display-6">Statistics</h4>
                            <p>
                              통계 페이지에서는 공부 시간에 대한 주간, 월간 통계 자료를 제공합니다. 
                              목표시간과 실제시간, 지난 2일간 공부시간, 전체 유저들과 같은 직무를 선택한 유저들의 공부시간을 비교하는 자료를 제공하며 일주일간 공부 기록을 비교하여 보여줍니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Content section 4-->
        <section id="" style="background: #607D8B">
            <div class="container px-5">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6 order-lg-2">
                        <div class="">
                          <Tech/>
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="p-5">
                            <h4 class="display-6">Tech</h4>
                            <p>
                              다양한 기술 블로그를 구독하고 스크랩을 통해 최신 글을 보며 개발자로서의 역량을 키울 수 있습니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Content section 5-->
        <section id="">
            <div class="container px-5">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6">
                        <div class="">
                          <Devtalk/>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="p-5">
                            <h4 class="display-6">Dev Talk</h4>
                            <p>
                              다양한 유저들과 질문을 하며 소통할 수 있는 공간입니다. 
                              공부를 하며 질문을 할 수 있고 태그를 제공하며 개발자를 위한 markdown을 제공합니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Content section 5-->
        <section id="" style="background: #607D8B">
            <div class="container px-5">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-6 order-lg-2">
                        <div class="">
                          <img class="img-fluid" src="@/assets/images/mypage-page.png" alt="mypage" />
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="p-5">
                            <h4 class="display-6">MyPage</h4>
                            <p>
                              GitHub API를 사용하여 일주일간 나의 commit 기록과 페이지에서 나의 모든 활동 및 스크랩 기록을 확인 할 수 있습니다.
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
import MainNav from "@/components/main/MainNav.vue";
import Tech from "@/components/main/Tech.vue";
import Devtalk from "@/components/main/Devtalk.vue";

export default {
  name: "Main",
  components: {
    MainNav,
    Tech,
    Devtalk,
  }
};
</script>

<style scoped>
@import url("./main.css");
</style>
