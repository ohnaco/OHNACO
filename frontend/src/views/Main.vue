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
          <a class="btn btn-light btn-xl rounded-pill mt-5" href="#scroll"
            >Learn More</a
          >
        </div>
      </div>
    </header>
    <!-- Content section 1-->
    <section id="scroll">
      <div class="container">
        <div class="row gx-5 align-items-center">
          <div class="col-lg-6">
            <div class="p-5">
              <img src="@/assets/images/full-logo.svg" alt="logo" />
            </div>
          </div>
          <div class="col-lg-6">
            <div class="p-5">
              <h4 class="display-6">오나코 서비스를 소개합니다.</h4>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod
                aliquid, mollitia odio veniam sit iste esse assumenda amet
                aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                voluptatum molestiae adipisci, beatae obcaecati.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Content section 2-->
    <section id="" style="background: #607d8b">
      <div class="container px-5">
        <div class="row gx-5 align-items-center">
          <div class="col-lg-6">
            <div class="p-5">
              <img
                class="img-fluid rounded-circle"
                src="assets/img/02.jpg"
                alt="..."
              />
            </div>
          </div>
          <div class="col-lg-6">
            <div class="p-5">
              <h4 class="display-6">To Do</h4>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod
                aliquid, mollitia odio veniam sit iste esse assumenda amet
                aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                voluptatum molestiae adipisci, beatae obcaecati.
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
          <div class="col-lg-6 order-lg-2">
            <div class="p-5">
              <img
                class="img-fluid rounded-circle"
                src="assets/img/03.jpg"
                alt="..."
              />
            </div>
          </div>
          <div class="col-lg-6 order-lg-1">
            <div class="p-5">
              <h4 class="display-6">Dev Talk</h4>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod
                aliquid, mollitia odio veniam sit iste esse assumenda amet
                aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                voluptatum molestiae adipisci, beatae obcaecati.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Content section 4-->
    <section id="" style="background: #607d8b">
      <div class="container px-5">
        <div class="row gx-5 align-items-center">
          <div class="col-lg-6">
            <div class="p-5">
              <img
                class="img-fluid rounded-circle"
                src="assets/img/02.jpg"
                alt="..."
              />
            </div>
          </div>
          <div class="col-lg-6">
            <div class="p-5">
              <h4 class="display-6">Tech</h4>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod
                aliquid, mollitia odio veniam sit iste esse assumenda amet
                aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                voluptatum molestiae adipisci, beatae obcaecati.
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
          <div class="col-lg-6 order-lg-2">
            <div class="p-5">
              <img
                class="img-fluid rounded-circle"
                src="assets/img/03.jpg"
                alt="..."
              />
            </div>
          </div>
          <div class="col-lg-6 order-lg-1">
            <div class="p-5">
              <h4 class="display-6">통계</h4>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod
                aliquid, mollitia odio veniam sit iste esse assumenda amet
                aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                voluptatum molestiae adipisci, beatae obcaecati.
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
import MainNav from "@/components/common/MainNav.vue";

export default {
  name: "Main",
  components: {
    MainNav,
  },
};
</script>

<style scoped>
@import url("./main.css");
</style>
