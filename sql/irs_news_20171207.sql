-- MySQL dump 10.13  Distrib 5.5.57, for Win64 (AMD64)
--
-- Host: localhost    Database: irs_news
-- ------------------------------------------------------
-- Server version	5.5.57

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `coments`
--

DROP TABLE IF EXISTS `coments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `date_time` datetime NOT NULL,
  `content` varchar(145) NOT NULL,
  `emotion` int(11) DEFAULT NULL,
  `news__id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_coment_news__idx` (`news__id`),
  CONSTRAINT `fk_coment_news_` FOREIGN KEY (`news__id`) REFERENCES `news_` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coments`
--

LOCK TABLES `coments` WRITE;
/*!40000 ALTER TABLE `coments` DISABLE KEYS */;
/*!40000 ALTER TABLE `coments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_`
--

DROP TABLE IF EXISTS `news_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `url` varchar(255) NOT NULL DEFAULT '-1',
  `abstract_` varchar(155) NOT NULL,
  `content` mediumtext NOT NULL,
  `date` date NOT NULL,
  `heat` int(11) NOT NULL,
  `vector` blob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_`
--

LOCK TABLES `news_` WRITE;
/*!40000 ALTER TABLE `news_` DISABLE KEYS */;
INSERT INTO `news_` VALUES (1,'张卫健挤公交','http://news.ifeng.com/a/20171207/53947873_0.shtml','张卫健挤公交无人识 交通拥堵到明星都放弃自驾了? 大明星张卫健今日晒出挤公交的自拍照,并配文:“如果你们在公交车上发现我,欢迎过来索取签名,免得... ','大明星张卫健今日晒出挤公交的自拍照，并配文：“如果你们在公交车上发现我，欢迎过来索取签名，免得我站着无聊，请广而告之。”照片中，车上人流密集，十分拥挤。张卫健带着黑色帽子和墨镜把自己裹得十分严实，嘴角还露出一丝微笑，十分亲民。','2017-12-07',67,NULL),(2,'宾馆现针孔摄像头 负责人:不是酒店安装的','http://news.wmxa.cn/shehui/201712/523177.html','微博爆料说,朋友在南宁市火炬路某酒店入住后,晚上使用笔记本电脑充电时,发现墙上的插座插不进去,在仔细观察了一番后居然发现,这插座背后竟然安装了... ','11月22日中午，一位名叫“木可-Wings”的网友在微博爆料说，朋友在南宁市火炬路某酒店入住后，晚上使用笔记本电脑充电时，发现墙上的插座插不进去，在仔细观察了一番后居然发现，这插座背后竟然安装了一个针孔摄像头。该事件发生不久后，黑龙江牡丹江一宾馆内也发生一起针孔摄像头事件，目前警方介入调查。','2017-12-07',265,NULL),(3,'学区房成凶宅 房东诉租房公司索赔45万余元','http://www.thepaper.cn/newsDetail_forward_1895996','学区房成为凶宅,以后转手买卖也是问题,她将租房的某公司告上法庭,索赔45.86万元。近日,南京栖霞法院审理了此案,判该公司赔偿4.86万元。法院认为,发...','学区房成为凶宅,以后转手买卖也是问题,她将租房的某公司告上法庭,索赔45.86万元。近日,南京栖霞法院审理了此案,判该公司赔偿4.86万元。法院认为,发...','2017-12-07',1254,NULL),(4,'湖南一大一新生斗殴死亡 警方已拘6名涉案学生','http://www.rmzxb.com.cn/c/2017-12-07/1892662.shtml','大一新生斗殴死亡,图为死者照片(家属提供)12月5日凌晨,湖南电子科技职业学院发生大一新生在寝室斗殴事件,15岁学生姚旭超被打成重伤,经抢救无效死亡...','大一新生斗殴死亡,图为死者照片(家属提供)12月5日凌晨,湖南电子科技职业学院发生大一新生在寝室斗殴事件,15岁学生姚旭超被打成重伤,经抢救无效死亡...','2017-12-07',215,NULL),(5,'国网公司按期全面完成公司制改制工作,由全民所有制','http://news.xinhuanet.com/power/2017-12/07/c_1122075530.htm','国网公司按期全面完成公司制改制工作,由全民所有制企业改制为国有独资公司 ---改制工作的圆满完成标志着公司深化改革正式进入新的阶段。','国网公司按期全面完成公司制改制工作,由全民所有制企业改制为国有独资公司 ---改制工作的圆满完成标志着公司深化改革正式进入新的阶段。','2017-12-07',771,NULL),(6,'东京奥运会公布候选吉祥物 由日本小学生投票决定','http://world.huanqiu.com/exclusive/2017-12/11431911.html','(原标题:2020年东京奥运会公布候选吉祥物,由日本小学生投票决定) 本文图均为 东京奥运会官网 图 新华社东京12月7日消息,2020年东京奥运会当日正式公... ','(原标题:2020年东京奥运会公布候选吉祥物,由日本小学生投票决定) 本文图均为 东京奥运会官网 图 新华社东京12月7日消息,2020年东京奥运会当日正式公... ','2017-12-07',587,NULL),(7,'小偷栽赃外卖小哥 反被其摁倒在地 ','http://www.techweb.com.cn/internet/2017-12-07/2615915.shtml','【小偷栽赃外卖小哥】偷走手机被失主觉察后,小偷准备将手机塞进身边一辆外卖电动车的配送箱,外卖小哥迅速上前将小偷抓住,交警方处理。 华商报记者获...','【小偷栽赃外卖小哥】偷走手机被失主觉察后,小偷准备将手机塞进身边一辆外卖电动车的配送箱,外卖小哥迅速上前将小偷抓住,交警方处理。 华商报记者获...','2017-12-07',1254,NULL),(8,'女孩遭群鸟追逐','http://huaren.haiwainet.cn/n/2017/1207/c3541575-31200051.html','【环球网综合报道】据英国《每日邮报》12月4日报道,近日,一则记录一名女孩喂海鸥反遭群鸟追逐的搞笑视频走红网络。画面中,女孩先是友好地把一片薯片... ','【环球网综合报道】据英国《每日邮报》12月4日报道,近日,一则记录一名女孩喂海鸥反遭群鸟追逐的搞笑视频走红网络。画面中,女孩先是友好地把一片薯片... ','2017-12-07',444,NULL);
/*!40000 ALTER TABLE `news_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_news_similar`
--

DROP TABLE IF EXISTS `news_news_similar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_news_similar` (
  `news__id1` int(11) NOT NULL,
  `news__id2` int(11) NOT NULL,
  `similarity` double NOT NULL,
  PRIMARY KEY (`news__id1`,`news__id2`),
  KEY `fk_news_news_similar_news_2_idx` (`news__id2`),
  CONSTRAINT `fk_news_news_similar_news_1` FOREIGN KEY (`news__id1`) REFERENCES `news_` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_news_news_similar_news_2` FOREIGN KEY (`news__id2`) REFERENCES `news_` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_news_similar`
--

LOCK TABLES `news_news_similar` WRITE;
/*!40000 ALTER TABLE `news_news_similar` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_news_similar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary`
--

DROP TABLE IF EXISTS `vocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vocabulary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(45) NOT NULL,
  `idf` double NOT NULL DEFAULT '0',
  `inverted_list` blob,
  `vector` blob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
/*!40000 ALTER TABLE `vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_word_similar`
--

DROP TABLE IF EXISTS `word_word_similar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word_word_similar` (
  `vocabulary_id1` int(11) NOT NULL,
  `vocabulary_id2` int(11) NOT NULL,
  `similarity` double NOT NULL,
  PRIMARY KEY (`vocabulary_id1`,`vocabulary_id2`),
  KEY `fk_word_word_similar_vocabulary2_idx` (`vocabulary_id2`),
  CONSTRAINT `fk_word_word_similar_vocabulary1` FOREIGN KEY (`vocabulary_id1`) REFERENCES `vocabulary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_word_word_similar_vocabulary2` FOREIGN KEY (`vocabulary_id2`) REFERENCES `vocabulary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_word_similar`
--

LOCK TABLES `word_word_similar` WRITE;
/*!40000 ALTER TABLE `word_word_similar` DISABLE KEYS */;
/*!40000 ALTER TABLE `word_word_similar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word_word_synonymous`
--

DROP TABLE IF EXISTS `word_word_synonymous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `word_word_synonymous` (
  `vocabulary_id` int(11) NOT NULL,
  `vocabulary_id1` int(11) NOT NULL,
  `similarity` double NOT NULL,
  PRIMARY KEY (`vocabulary_id`,`vocabulary_id1`),
  KEY `fk_word_word_synonymous_vocabulary2_idx` (`vocabulary_id1`),
  CONSTRAINT `fk_word_word_synonymous_vocabulary1` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_word_word_synonymous_vocabulary2` FOREIGN KEY (`vocabulary_id1`) REFERENCES `vocabulary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word_word_synonymous`
--

LOCK TABLES `word_word_synonymous` WRITE;
/*!40000 ALTER TABLE `word_word_synonymous` DISABLE KEYS */;
/*!40000 ALTER TABLE `word_word_synonymous` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-07 23:26:36
