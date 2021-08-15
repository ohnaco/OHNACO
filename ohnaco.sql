-- MySQL dump 10.19  Distrib 10.3.31-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ohnaco
-- ------------------------------------------------------
-- Server version	10.3.31-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answerid` int(11) NOT NULL AUTO_INCREMENT,
  `answercontent` varchar(2000) DEFAULT NULL,
  `answerdate` datetime(6) DEFAULT NULL,
  `answertitle` varchar(200) DEFAULT NULL,
  `likes` int(11) NOT NULL,
  `questionid` int(11) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`answerid`),
  KEY `answer_ibfk_2` (`questionid`),
  KEY `answer_ibfk_1` (`userid`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (15,'백준문제에 대한 답변내용입니다','2021-08-12 15:37:44.753542','백준문제에 대한 답변입니다',0,21,'8HK63Z9CSQW10'),(16,'헉','2021-08-12 16:04:04.123274','',0,18,'8HK63Z9CSQW10'),(17,'gk...','2021-08-12 16:11:53.157716','',0,18,'8HK63Z9CSQW10'),(18,'gk2.....','2021-08-12 16:12:54.258277','',0,18,'8HK63Z9CSQW10'),(19,'댓글테스트@@','2021-08-12 18:45:25.200445','',0,18,'8HK63Z9CSQW10'),(20,'아...','2021-08-12 18:45:54.163235','',0,18,'8HK63Z9CSQW10'),(21,'gn...','2021-08-12 18:47:16.841589','',0,18,'8HK63Z9CSQW10'),(22,'1','2021-08-12 19:27:46.946242','',0,38,'8HK63Z9CSQW10'),(23,'2','2021-08-12 19:27:49.681054','',0,38,'8HK63Z9CSQW10'),(24,'3','2021-08-12 19:27:52.461837','',0,38,'8HK63Z9CSQW10');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `articleid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `link` varchar(500) NOT NULL,
  `blogid` int(11) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `publisheddate` datetime DEFAULT NULL,
  PRIMARY KEY (`articleid`),
  KEY `blogid` (`blogid`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (203,'Generative Adversarial Networks를 이용한 Nickface 개발','카카오 사내 기술 세미나 Techtalk 여섯 번째 이야기입니다.본 내용은 AI 기술 개발 및 연구를 위함으로 상업적 활동이 아니라는 것을 말씀드립니다.  안녕하세요, 카카오브레인에서 AI 기반 이미지 생성 모델을 연구하고 있는 Orca.na라고 합니다.&#1','https://tech.kakao.com/2021/08/04/nickface/',1,'kakao','2021-08-04 10:10:44'),(204,'[카.올.시.]  Front-End 개발자라면 카카오에 올 시간입니다.','내가 하고 있는 직무가 Front-End라면 주목! 이젠 카카오에 올 시간입니다. 카카오에서 3년 차 이상의 경력자를 대상으로 8월 5일까지 집중 영입을 진행합니다. [공고보기] 무슨 일을 하게 되는지? 어떤 문화를 가지고 있는지? 등 전형을 준비하면서 궁금하실 점이 ','https://tech.kakao.com/2021/07/30/frontend-recruitment/',1,'kakao','2021-07-30 09:26:00'),(205,'[카.올.시.]  Android 개발자라면 카카오에 올 시간입니다.','내가 하고 있는 직무가 Android라면 주목! 이젠 카카오에 올 시간입니다. 카카오에서 3년차 이상의 경력자를 대상으로 8월 5일까지 집중 영입을 진행합니다. [공고보기] 무슨 일을 하게 되는지 ? 어떤 문화를 가지고 있는지? 등 전형을 준비하면서 궁금하실 점이 많으','https://tech.kakao.com/2021/07/30/android-recruitment/',1,'kakao','2021-07-30 09:26:00'),(206,'실용주의 데브옵스 for MSA','안녕하세요, 콘텐츠서비스개발팀에서 카카오TV 동영상 빅데이터 수집 및 분석 업무와 동영상 추천 시스템을 개발하고 있는 Knight 라고 합니다. ‘실용주의 데브옵스 for MSA’는 저희가 카카오TV 실서비스를 오픈하기 전에 진행한 데브옵스 시스템','https://tech.kakao.com/2021/07/16/devops-for-msa/',1,'kakao','2021-07-16 10:08:46'),(207,'2021 카카오 인턴십 for Tech developers 코딩 테스트 해설','2021년 카카오의 여름 인턴십의 첫 번째 관문인 코딩 테스트가 지난 2021년 5월 8일에 4시간에 걸쳐 진행되었습니다. 이번 인턴 코딩 테스트에서는 5문제가 출제되었습니다. 이전과 동일하게 쉬운 문제를 앞쪽에, 어려운 문제를 뒤쪽에 배치하였으며, 일부 문제에는 효율','https://tech.kakao.com/2021/07/08/2021-%ec%b9%b4%ec%b9%b4%ec%98%a4-%ec%9d%b8%ed%84%b4%ec%8b%ad-for-tech-developers-%ec%bd%94%eb%94%a9-%ed%85%8c%ec%8a%a4%ed%8a%b8-%ed%95%b4%ec%84%a4/',1,'kakao','2021-07-08 10:00:37'),(208,'Open Work를 향한 작은 노력들 : 조직의 작은 변화 만들기','카카오 사내 기술 세미나 Techtalk 여섯 번째 이야기입니다. 안녕하세요. 광고개발팀에서 광고계정, 광고빌링을 담당하고 있는 cuddy입니다. 카카오의 일하는 방식인 Open Work가 지향하는 가치들을 달성하기 위해 많은 조직이 Open Work 코칭을 ','https://tech.kakao.com/2021/07/05/openwork-cuddy/',1,'kakao','2021-07-05 11:00:24'),(209,'OpenWork, 카카오의 일하는 방식','안녕하세요, 카카오 애자일코치 benedict입니다. 여러분의 팀은 얼마나 열려있으신가요? 더욱더 열린 문화와 열려있는 일하는 방식을 향한 카카오의 지난 여정의 발걸음에서 만들어진 카카오의 일하는 방식인 OpenWork에 대해서 소개하려 합니다. Why Open? 왜 ','https://tech.kakao.com/2021/07/02/openwork-agile/',1,'kakao','2021-07-02 10:15:33'),(210,'카카오 AI추천 : 토픽 모델링과 MAB를 이용한 카카오 개인화 추천','글 작성에는 추천팀 sasha.k와 marv.20가 함께해 주셨습니다. 머신러닝에 대한 기초 지식이 있고, 추천 알고리즘에 관심이 있는 분들에게 카카오 추천팀이 개인화 추천 기술을 활용하는 방법에 대해 공유합니다. 우리의 일상 속에는 수많은 추천이 함께합니다. 카카오 ','https://tech.kakao.com/2021/06/25/kakao-ai-recommendation-01/',1,'kakao','2021-06-25 10:39:42'),(211,'FE개발자의 성장 스토리 10 : 주니어 FE 개발자 오픈소스 성장기','안녕하세요! FE플랫폼팀에서 사내 NPM, 스타일 가이드, 그리고 최근에는 웹 성능과 관련된 업무를 담당하고 있는 joah입니다. 최근 카카오 사내 기술 세미나 ‘Techtalk’에서 발표했던 오픈소스 활동 소개와 오픈소스 활동을 통해 개발자로서 성장했던 이야기를 공유','https://tech.kakao.com/2021/06/16/frontend-growth-10/',1,'kakao','2021-06-16 10:31:53'),(212,'첫 회사를 우아한형제들에서 시작한 아가 개발자의 걸음마 떼기','배민주문서비스팀 신규 입사자의 파일럿 프로젝트 경험을 공유합니다. 안녕하세요! 6월 21일 배민주문서비스팀에 서버 개발자로 합류하게 된 김경준입니다. 기술 블로그의 글들을 보면서, 언젠간 사람들에게 도움이 될 수 있는 이야기를 공유해야지 꿈만 꾸던 학생이었는데 지','https://techblog.woowahan.com/5143/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%25b2%25ab-%25ed%259a%258c%25ec%2582%25ac%25eb%25a5%25bc-%25ec%259a%25b0%25ec%2595%2584%25ed%2595%259c%25ed%2598%2595%25ec%25a0%259c%25eb%2593%25a4%25ec%2597%2590%25ec%2584%259c-%25ec%258b%259c%25ec%259e%2591%25ed%2595%259c-%25ec%2595%2584%25ea%25b0%2580-%25ea%25b0%259c%25eb%25b0%259c%25ec%259e%2590%25ec%259d%2598',3,'woowahan','2021-08-04 16:04:05'),(213,'가정의 달 이벤트가 쏘아올린 배민선물하기 대란.ssul','안녕하세요. 우아한형제들 배민선물하기팀에서 서비스기획을 담당하고 있는 진예령입니다. 기술블로그는 처음이지만 저희만 알고 있기엔 살짝 억울한(?) 프로젝트가 있어서 스리슬쩍 프로젝트 하나를 공유해보려고 합니다. 😂 저는 원래 프랜차이즈시스템팀에서 브랜드관 기획을 ','https://techblog.woowahan.com/5073/?utm_source=rss&utm_medium=rss&utm_campaign=%25ea%25b0%2580%25ec%25a0%2595%25ec%259d%2598-%25eb%258b%25ac-%25ec%259d%25b4%25eb%25b2%25a4%25ed%258a%25b8%25ea%25b0%2580-%25ec%258f%2598%25ec%2595%2584%25ec%2598%25ac%25eb%25a6%25b0-%25eb%25b0%25b0%25eb%25af%25bc%25ec%2584%25a0%25eb%25ac%25bc%25ed%2595%2598%25ea%25b8%25b0-%25eb%258c%2580%25eb%259e%2580-ssul',3,'woowahan','2021-07-23 17:10:50'),(214,'우아~한 장애대응','안녕하세요. 우아한형제들 시스템신뢰성개발팀 박주희입니다. 얼마 전에 시스템신뢰성개발팀에 대해서 소개한 적이 있습니다. 그때, 팀 소개에서 시스템신뢰성개발팀에서 가장 중요한 업무 중 하나는 장애 대응이라고 설명했는데, 오늘은 이 장애 대응에 대해서 조금 더 자세하게','https://techblog.woowahan.com/4886/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%259a%25b0%25ec%2595%2584%25ed%2595%259c-%25ec%259e%25a5%25ec%2595%25a0%25eb%258c%2580%25ec%259d%2591',3,'woowahan','2021-06-30 18:13:16'),(215,'우테코에서 찾은 나만의 효과적인 공부법','안녕하세요. 테크코스교육개발팀 이원미입니다. 3기 크루들의 두 번째 방학 (부럽습니다..) 을 앞두고 레벨 2를 마무리하며 작성한 글을 공유합니다 : ) 이번 글쓰기의 주제는 \"우테코에서 찾은 나만의 효과적인 공부법\" 입니다. 우아한테크코스에서는 정','https://techblog.woowahan.com/4159/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%259a%25b0%25ed%2585%258c%25ec%25bd%2594%25ec%2597%2590%25ec%2584%259c-%25ec%25b0%25be%25ec%259d%2580-%25eb%2582%2598%25eb%25a7%258c%25ec%259d%2598-%25ed%259a%25a8%25ea%25b3%25bc%25ec%25a0%2581%25ec%259d%25b8-%25ea%25b3%25b5%25eb%25b6%2580%25eb%25b2%2595-2',3,'woowahan','2021-06-11 09:00:51'),(216,'우아한형제들 기획자 소개와 개발자와의 협업문화 인터뷰','끊임없이 도전하고 혁신하는 우아한형제들!그 중심에는 가치 있는 서비스를 설계하며‘좋은 것을 더 좋게’ 만들기 위해 도전하는 기획자분들이 있습니다. 이번 인터뷰에서는 기획자분들을 만나보겠습니다. 들어가기 전에 안녕하세요, 인터뷰로 다시 찾아온 인재영입팀 입니다.이번','https://techblog.woowahan.com/4122/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%259a%25b0%25ec%2595%2584%25ed%2595%259c%25ed%2598%2595%25ec%25a0%259c%25eb%2593%25a4-%25ea%25b8%25b0%25ed%259a%258d%25ec%259e%2590-%25ec%2586%258c%25ea%25b0%259c%25ec%2599%2580-%25ea%25b0%259c%25eb%25b0%259c%25ec%259e%2590%25ec%2599%2580%25ec%259d%2598-%25ed%2598%2591%25ec%2597%2585%25eb%25ac%25b8%25ed%2599%2594',3,'woowahan','2021-06-07 15:30:00'),(217,'우아한형제들 Developer Relations-Updated','2021년 4월 1일 만우절 거짓말같이 우아한형제들 조직도에 Developer Relations(DR 파트)가 등장합니다. (두둥!) DR 1호인 제가 입사한지 2년 하고도 3개월, DR 2호가 입사한지 만 1년 그리고 DR 3호의 입사가 한 달 남은 그 시점에서','https://techblog.woowahan.com/4091/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%259a%25b0%25ec%2595%2584%25ed%2595%259c%25ed%2598%2595%25ec%25a0%259c%25eb%2593%25a4-developer-relations-updated',3,'woowahan','2021-04-28 13:00:00'),(218,'[모집] 2021우아한테크캠프 4기','우아한개발자가 되고 싶은 이들을 위한 우아한테크캠프 4기를 소개합니다. 우아한테크캠프는 여름 방학기간을 활용해 진행되는 채용 연계형 교육 프로그램입니다. 교육은 사회적 상황에 따라 온라인 혹은 온/오프라인 병행 등 안전하고 유연한 교육방식을 준비하고 있습니다. 올','https://techblog.woowahan.com/2724/?utm_source=rss&utm_medium=rss&utm_campaign=%25eb%25aa%25a8%25ec%25a7%2591-2021%25ec%259a%25b0%25ec%2595%2584%25ed%2595%259c%25ed%2585%258c%25ed%2581%25ac%25ec%25ba%25a0%25ed%2594%2584-4%25ea%25b8%25b0',3,'woowahan','2021-04-20 13:00:00'),(219,'퇴사 부검','안녕하세요 (구) 배달의민족 정산시스템팀 개발자, (현) 유튜브 크리에이터 이동욱입니다. 제가 2021년 4월 19일(월) 퇴사를 합니다. 퇴사에 대한 이야기를 개인적으로 운영하는 유튜브 채널에 부검 발표를 했는데요. (배달의민족 개발자 퇴사썰 – 부검','https://techblog.woowahan.com/2723/?utm_source=rss&utm_medium=rss&utm_campaign=%25ed%2587%25b4%25ec%2582%25ac-%25eb%25b6%2580%25ea%25b2%2580',3,'woowahan','2021-04-18 08:30:00'),(220,'제모옥은 젠킨스 조회로직 개선으로 하겠습니다. 근데 이제 비동기를 곁들인','제목의 밈은 조림요정의 휴먼강록체입니다. Intro 평화로운 2020년 9월의 어느 날… 데일리 미팅을 마치고 일감을 정리하던 저에게 한 가지 요청이 들어왔습니다. \"우빈님 여기 로직이 오래 걸리면 90초 넘게 걸리고 있는데 한번 개선할 수 있을','https://techblog.woowahan.com/2722/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%25a0%259c%25eb%25aa%25a8%25ec%2598%25a5%25ec%259d%2580-%25ec%25a0%25a0%25ed%2582%25a8%25ec%258a%25a4-%25ec%25a1%25b0%25ed%259a%258c%25eb%25a1%259c%25ec%25a7%2581-%25ea%25b0%259c%25ec%2584%25a0%25ec%259c%25bc%25eb%25a1%259c-%25ed%2595%2598%25ea%25b2%25a0%25ec%258a%25b5%25eb%258b%2588%25eb%258b%25a4-%25ea%25b7%25bc',3,'woowahan','2021-04-06 13:00:00'),(221,'우아한테크코스 한 달 생활기','안녕하세요. 테크코스교육개발팀 이원미입니다. 우아한테크코스(이하, 우테코)도 어느덧 3년 차에 접어들었습니다 : ) 새로운 기수를 맞이할 때면 언제나 설레는 것 같아요. 아참, 3기에는 커다란 변화가 생겼는데요! (언제나 변화가 일상인 우테코지만..) 지난 기수는','https://techblog.woowahan.com/2721/?utm_source=rss&utm_medium=rss&utm_campaign=%25ec%259a%25b0%25ec%2595%2584%25ed%2595%259c%25ed%2585%258c%25ed%2581%25ac%25ec%25bd%2594%25ec%258a%25a4-%25ed%2595%259c-%25eb%258b%25ac-%25ec%2583%259d%25ed%2599%259c%25ea%25b8%25b0',3,'woowahan','2021-04-02 09:00:00'),(222,'모킹 통합관리와 cypress의 모킹 on/off 기능','# 모킹 통합관리와 cypress의 모킹 on/off 기능 \n\n모킹 데이터는 테스트를 실행하기 위해 실제 데이터가 아닌 개발자가 필요에 의해서 만든 데이터를 말한다. 그렇다면 모킹 데이터를 사용하는 목적은 무엇일까? \n\n- e2e 테스트 환경에서 실제 데이터가 유동적으','https://meetup.toast.com/posts/294',5,'nhn','2021-08-02 17:43:00'),(223,'Chromium이 발표한 RenderingNG가 무엇인가?','# Chromium이 발표한 RenderingNG가 무엇인가?\n\n필자는 현재 프런트엔드 개발자지만 Webkit 기반의 브라우저를 개발한 적이 있다. 주로 HTML5 스펙 지원과 각종 CSS 기능을 지원하기 위해 렌더링 엔진을 다양한 운영체제에 맞게 포팅하는 것이었다. ','https://meetup.toast.com/posts/293',5,'nhn','2021-07-16 10:53:46'),(224,'EasyCache의 Multi Replica 기능 소개','`EasyCache` 서비스는 사용자가 직접 `Redis`를 설치하지 않고도 클라우드 환경에서 쉽게 이용할 수 있도록 해주는 `NHN Cloud`의 KVS 서비스입니다.\n이번에 `EasyCache` 서비스에 새롭게 추가된 멀티 레플리카 기능에 대해 알아보도록 하겠습니다','https://meetup.toast.com/posts/292',5,'nhn','2021-07-15 16:55:38'),(225,'우리의 Thread는 왜 이렇게 부자가 되었을까?','안녕하세요. \nNHN Cloud 서비스에서 공통적으로 사용되는 Framework(결제, 인증, 권한 등)를 개발하고 있는 프레임워크개발팀 박시우입니다. \n최근 저희 팀에서 OOM 이슈 관련하여, 분석해 본 결과 병렬 처리 진행을 위해 ForkJoinPool을 사용하게 ','https://meetup.toast.com/posts/291',5,'nhn','2021-07-12 18:30:10'),(226,'게으른 개발자의 코딩 방법: AI주도 개발방법론','## 프롤로그 \n\n4차 산업혁명시대로 불리는 현재, 우리는 AI가 대중화된 시대에 살고 있다. 그리고 업무에도 깊숙하게 AI는 적용되고 있다. 과거부터 사무 처리 업무의 자동화는 관심거리였고, 매크로, RPA(Robotic Process Automation) 등으로 표','https://meetup.toast.com/posts/290',5,'nhn','2021-07-09 13:53:28'),(227,'뼈대 있는 가문의 데이터로 만들기','데이터 처리, 집계, 모델링 업무를 수행하다 보면 인지하지 못하는 복잡한 상관관계가 만들어지게 됩니다.\n특히 테이블이라고 표현되는 Relational Database의 DataSet들은 조회 용으로 쓰이는 경우도 있지만 \n많은 경우 다른 DataSet의 입력이 되거나 ','https://meetup.toast.com/posts/289',5,'nhn','2021-07-08 11:13:24'),(228,'Terraform: IaC의 단짝','## 목차\n\n* Terraform과 IaC\n* Terraform 설치\n* 준비\n* 인프라 구축\n* 인프라 변경\n* 인프라 제거\n* 그래프\n\n\n## 개요 \n\n훌륭한 IaC(Infrastructure as Code) 도구인 Terraform을 사용하여 NHN Cloud의 ','https://meetup.toast.com/posts/288',5,'nhn','2021-07-05 18:32:54'),(229,'NAT 게이트웨이 기능 소개','# NAT 게이트웨이 \n## 기본 사항 \n* NAT 게이트웨이를 이용하면 인터넷 게이트웨이가 연결되지 않은 인스턴스들이 특정 CIDR에 대해서 인터넷 액세스를 할 수 있습니다. \n* NAT 게이트웨이는 고정된 소스 IP로 인터넷을 액세스 합니다. 특정 소스 IP에 대해','https://meetup.toast.com/posts/287',5,'nhn','2021-07-02 11:27:11'),(230,'Github Actions으로 배포 자동화하기','# Github Actions으로 배포 자동화하기 \n\n![1.png](https://image.toast.com/aaaadh/real/2021/techblog/1%2818%29.png)\n\n## Github Actions이란? \n\n`Github Actions`이란 Git','https://meetup.toast.com/posts/286',5,'nhn','2021-07-02 10:48:10'),(231,'System Monitoring 서비스의 Advanced Monitoring(OpenMetrics) 기능 소개','# Advanced Monitoring (OpenMetrics) 기능 소개\n\n## 소개\n\n![1.png](https://image.toast.com/aaaadh/real/2021/techblog/1%2816%29.png)\n![2.png](https://image.toa','https://meetup.toast.com/posts/284',5,'nhn','2021-07-02 10:48:21'),(232,'검색 영역 탐색을 위한 인덱싱 플랫폼의 변천사','-<h3>프롤로그</h3>검색은 고객이 쿠팡에서의 여정을 시작하는 주요 통로다. 검색 경험은 고객이 ‘쿠팡 없이 어떻게 살았을까?’ 라고 묻는 세상을 만들기 위해, 쿠팡이 달성해야 하는 미션의 핵심이다. 쿠팡 검색엔진의 목표는 고객 검색 키워드를 기반','https://medium.com/coupang-tech/%EA%B2%80%EC%83%89-%EC%98%81%EC%97%AD-%ED%83%90%EC%83%89%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%9D%B8%EB%8D%B1%EC%8B%B1-%ED%94%8C%EB%9E%AB%ED%8F%BC%EC%9D%98-%EB%B3%80%EC%B2%9C%EC%82%AC-eec241758e84?source=rss----fb028911af07---4',6,'coupang','2020-05-07 15:59:50');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `blogid` int(11) NOT NULL AUTO_INCREMENT,
  `blogname` varchar(30) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  `rss` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`blogid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'kakao','카카오 기술블로그','https://tech.kakao.com/blog/','https://tech.kakao.com/feed/'),(3,'woowahan','우아한형제들 기술블로그','https://techblog.woowahan.com/','https://techblog.woowahan.com/feed/'),(5,'nhn','NHN 기술블로그','https://meetup.toast.com/','https://meetup.toast.com/rss'),(6,'coupang','쿠팡 기술블로그','https://medium.com/coupang-tech/technote/home','https://medium.com/feed/coupang-tech');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryid` varchar(20) NOT NULL,
  `categoryname` varchar(20) NOT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('algorithm','알고리즘'),('certificate','자격증'),('cs','CS'),('etc','기타'),('framework','프레임워크');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (233);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `positions` (
  `positionid` int(11) NOT NULL,
  `positionname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`positionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES (1,'Frontend'),(2,'Backend'),(3,'Android'),(4,'IOS'),(5,'SI'),(6,'Embedded'),(7,'Game'),(8,'etc.');
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionid` int(11) NOT NULL AUTO_INCREMENT,
  `likes` int(11) NOT NULL,
  `questioncontent` varchar(2000) DEFAULT NULL,
  `questiondate` datetime DEFAULT NULL,
  `questiontitle` varchar(200) DEFAULT NULL,
  `views` int(11) NOT NULL,
  `userid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`questionid`),
  KEY `question_ibfk_1` (`userid`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,0,'Docker 명령어에는 무엇이 있나요?!','2021-08-11 13:19:38','Docker명령어에는 어떤 것이 잇나요?',0,'8HK63Z9CSQW10'),(2,0,'Spring에서 new 생성자에 대해 의존성 주입을 하고 싶습니다!','2021-08-11 13:20:40','Spring new 생성자에 대해 의존성 주입 어떻게 하나요?',0,'8HK63Z9CSQW10'),(3,0,'안녕하세요! 코린이 입니다! 제가 java를 배우고 있는데요! 친구들이 javascript가 뭔지 아냐고 했는데.. 잘 몰라서요.. ','2021-08-11 13:21:47','Java와 JavaScript의 차이점이 뭔가요? 코린이입니다',0,'8HK63Z9CSQW10'),(4,0,'제가 프로젝트를 해보려고 하는데요!! SQL 기본 문법에는 뭐가 있을까요? 참고로 저는 MySQL 관계형 데이터베이스를 사용합니다!','2021-08-11 13:22:19','SQL 기본 문법에는 뭐가 있을까요?',0,'8HK63Z9CSQW10'),(5,0,'다음과 같은 포화 이진트리에서 전위 순회, 중위 순회, 후위 순회 한 값이 어떻게 되는지 알고 싶습니다! 학교에서 배우긴 했는데 너무나 헷갈리네요... !','2021-08-11 13:22:58','트리 순회 질문...',0,'8HK63Z9CSQW10'),(6,0,'https://www.acmicpc.net/problem/1157 질문 1. 런타임 에러 뜨는 이유 2. 배열을 1,000,000 size만큼 할당하는 방법','2021-08-11 13:23:32','백준 알고리즘 C언어 런타임에러 이유를 모르겠습니다.',0,'8HK63Z9CSQW10'),(7,0,'안드로이드 스튜디오 사용해서 앱을 만들려고 하는데 저 디자인 화면이 (흰색 파란색 창) 확대가 안돼요.. 검색해도 안나오고 저거 크게해서 보고싶은데 답답합니다ㅠㅠ 도와주세요','2021-08-11 13:24:07','안드로이드 스튜디오 툴',0,'8HK63Z9CSQW10'),(8,0,'백준 10951번 문제입니다.','2021-08-11 13:24:31','C에서 입력값의 끝을 판별할 수 있는 방법이 있나요?',0,'8HK63Z9CSQW10'),(9,0,'### ㅋㅋㅋ','2021-08-11 17:44:41','질문테스트',0,NULL),(10,0,'## 테스트','2021-08-11 19:07:23','테스트',0,NULL),(11,0,'### ㅋㅋㅋ2','2021-08-11 19:17:19','질문테스트2',0,NULL),(14,0,'### ㅋㅋㅋ2','2021-08-11 19:22:14','질문테스트2',0,NULL),(15,0,'## 태그테스트1','2021-08-11 19:24:15','태그테스트1',0,'8HK63Z9CSQW10'),(18,0,'시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다시간테스입니다','2021-08-11 21:06:18','시간테스트',0,'8HK63Z9CSQW10'),(21,0,'##백준 10951번 문제입니다.','2021-08-11 21:25:34','C에서 입력값의 끝을 판별할 수 있는 방법이 있나요?',0,'8HK63Z9CSQW10'),(38,0,'ㅌㅇㅁㄴㅇ','2021-08-12 19:27:41','댓글테스트',0,'8HK63Z9CSQW10');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribe`
--

DROP TABLE IF EXISTS `subscribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscribe` (
  `userid` varchar(20) NOT NULL,
  `blogid` int(11) NOT NULL,
  PRIMARY KEY (`userid`,`blogid`),
  KEY `subscribe_ibfk_1` (`blogid`),
  CONSTRAINT `subscribe_ibfk_1` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`),
  CONSTRAINT `subscribe_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribe`
--

LOCK TABLES `subscribe` WRITE;
/*!40000 ALTER TABLE `subscribe` DISABLE KEYS */;
INSERT INTO `subscribe` VALUES ('8HK63Z9CSQW10',1);
/*!40000 ALTER TABLE `subscribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL,
  `tagname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'Java'),(2,'Spring'),(3,'Javascript'),(4,'MySQL'),(5,'C++'),(6,'C'),(7,'Android'),(8,'Ajax');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_relation`
--

DROP TABLE IF EXISTS `tag_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag_relation` (
  `questionid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  KEY `tag_relation_ibfk_1` (`tagid`),
  KEY `tag_relation_ibfk_2` (`questionid`),
  CONSTRAINT `tag_relation_ibfk_1` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`),
  CONSTRAINT `tag_relation_ibfk_2` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_relation`
--

LOCK TABLES `tag_relation` WRITE;
/*!40000 ALTER TABLE `tag_relation` DISABLE KEYS */;
INSERT INTO `tag_relation` VALUES (1,1),(2,1),(2,2),(3,1),(3,3),(4,4),(5,1),(5,5),(6,6),(7,7),(8,6),(9,1),(9,6),(10,1),(10,6),(11,1),(11,6),(14,1),(14,6),(15,2),(15,6),(18,2),(18,6),(21,6),(38,1);
/*!40000 ALTER TABLE `tag_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo` (
  `todoid` varchar(40) NOT NULL,
  `date` date DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `goaltime` time DEFAULT NULL,
  `issuccess` bit(1) DEFAULT NULL,
  `completetime` time DEFAULT NULL,
  `categoryid` varchar(20) NOT NULL,
  `userid` varchar(20) NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `ongoing` bit(1) DEFAULT NULL,
  PRIMARY KEY (`todoid`),
  KEY `todo_ibfk_1` (`categoryid`),
  KEY `todo_ibfk_2` (`userid`),
  CONSTRAINT `todo_ibfk_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`),
  CONSTRAINT `todo_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES ('00AEE2Z4','2021-08-13','테스트1','01:10:10','\0','00:07:23','etc','8HK63Z9CSQW10','2021-08-13 13:49:00.526344',''),('0V97H8W8','2021-08-13','algo-0813-수정됨','01:05:10','\0','10:29:38','algorithm','8HK63Z9CSQW10','2021-08-12 23:06:17.233020','\0'),('2L2Z1A2P','2021-08-13','모아보기 테스트용','05:10:10','\0','00:00:00','algorithm','8HK63Z9CSQW10','2021-08-13 16:10:00.681101','\0'),('90740899','2021-08-12','운영체제','00:00:20','\0','00:00:00','cs','8HK63Z9CSQW10','2021-08-12 11:55:06.493484','\0'),('D6UO39DH','2021-08-11','프로세스와 스레드의 차이','01:00:00','\0','00:00:00','cs','8HK63Z9CSQW10','2021-08-11 11:58:07.101816','\0'),('MXC2M3L3','2021-08-10','백준 2131번 문제 풀기','00:40:00','\0','00:00:00','algorithm','8HK63Z9CSQW10','2021-08-11 11:58:32.728724','\0'),('Q8MY3R7P','2021-08-13','1','01:00:00','\0','00:00:00','algorithm','8HK63Z9CSQW10','2021-08-13 17:19:20.289500','\0'),('S59B42V2','2021-08-12','1','01:00:00','\0','00:00:00','algorithm','8HK63Z9CSQW10','2021-08-12 23:07:37.396316','\0'),('ZRG4TJH1','2021-08-13','정처기0813','01:00:00','\0','00:10:05','certificate','8HK63Z9CSQW10','2021-08-12 23:06:33.164608','\0');
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `githubId` varchar(30) DEFAULT NULL,
  `positionid` int(11) DEFAULT NULL,
  `social` varchar(20) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `user_ibfk_1` (`positionid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`positionid`) REFERENCES `positions` (`positionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('8HK63Z9CSQW10','sonch12@naver.com','sonch12','$2a$10$5L9rvG9kf3j1.ygSSAMfm..4z5jTyurkFsjVmKkhoFDCCjWaEvX1e','Changhwan-Son',2,NULL,NULL),('9WL0WA4L1UQ1E','sonch96@hanmail.net','한메일','$2a$10$v3GLMGI/iUs5drVqnBnaMOWtJms0w0aGEKZGv9azdBfU0xUHbfuem','Changhwan-Son',4,NULL,'https://ohnaco.s3.ap-northeast-2.amazonaws.com/defaultProfile'),('EQ91W5BBX7SJ4','allkites324@gmail.com','ekdus','$2a$10$bYwrtD8ggHmRQeZn8zGPnu6XKazQQxfaOShTpobU42yOk1uAcgVLO','dayeonyang0324',1,NULL,'https://ohnaco.s3.ap-northeast-2.amazonaws.com/ekdus.jpg'),('W963651S7589J','kimho1995@naver.com','kimho','$2a$10$PV.wJOlhZEafvrndyySDsuOZd9zuj8P35G2AiGcLrjv7aF.T4Zt0m','kimho1995',2,NULL,'https://ohnaco.s3.ap-northeast-2.amazonaws.com/defaultProfile'),('ZP4XBO036G25W','sonch7132@gmail.com','asdfasdf1234','$2a$10$xy8aNUSYYATZmEKk1ZsbAelcP2kN9/OD3SwPHMfM51jpLH6c8GLDm','Changhwan-Son',2,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-15 13:14:29
