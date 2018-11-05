/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.27 : Database - hbtest1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hbtest1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hbtest1`;

/*Table structure for table `futurescrip` */

DROP TABLE IF EXISTS `futurescrip`;

CREATE TABLE `futurescrip` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `annualVolatility` double NOT NULL,
  `ask` double NOT NULL,
  `bid` double NOT NULL,
  `changeInPrice` double NOT NULL,
  `changeinOI` double NOT NULL,
  `closePrice` double NOT NULL,
  `dailyVolatility` double NOT NULL,
  `expiryDate` varchar(255) DEFAULT NULL,
  `high` double NOT NULL,
  `iv` double NOT NULL,
  `ivp` double NOT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `lotsize` double NOT NULL,
  `low` double NOT NULL,
  `ltp` double NOT NULL,
  `marginLimit` double NOT NULL,
  `mwpl` double NOT NULL,
  `oi` double NOT NULL,
  `open` double NOT NULL,
  `percentChange` double NOT NULL,
  `percentChangeInOI` double NOT NULL,
  `prevClose` double NOT NULL,
  `scripName` varchar(255) DEFAULT NULL,
  `sd` double NOT NULL,
  `spotPrice` double NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=latin1;

/*Data for the table `futurescrip` */

insert  into `futurescrip`(`Id`,`annualVolatility`,`ask`,`bid`,`changeInPrice`,`changeinOI`,`closePrice`,`dailyVolatility`,`expiryDate`,`high`,`iv`,`ivp`,`lastUpdate`,`lastUpdateTime`,`lotsize`,`low`,`ltp`,`marginLimit`,`mwpl`,`oi`,`open`,`percentChange`,`percentChangeInOI`,`prevClose`,`scripName`,`sd`,`spotPrice`) values (1,52.93,1419,1417.05,-109.7,0,1413.75,2.77,'25OCT2018',1466.8,0,0,NULL,NULL,400,1404.8,1417,0,17078428,1220400,1460,-7.19,8.77,1526.7,'ACC',0,1427.4),(2,52.93,1421,1416.15,-104.5,0,1417.3,2.77,'29NOV2018',1490.95,0,0,NULL,NULL,400,1410,1420,0,17078428,211600,1490.95,-6.85,451.04,1524.5,'ACC',0,1427.4),(3,75.68,169,168.8,6.75,0,169.1,3.96,'25OCT2018',170.75,0,0,NULL,NULL,4000,158.85,168.85,0,55169320,19864000,159.4,4.16,-12.25,162.1,'ADANIENT',0,168.55),(4,75.68,169.8,169.45,6.85,0,170.05,3.96,'29NOV2018',171.7,0,0,NULL,NULL,4000,160.65,169.85,0,55169320,21592000,161.4,4.2,25.56,163,'ADANIENT',0,168.55),(5,55.05,316.9,316.5,2.4,0,316.15,2.88,'25OCT2018',320.55,0,0,NULL,NULL,2500,306.2,316.9,0,156144401,11615000,313.95,0.76,-4.21,314.5,'ADANIPORTS',0,317.75),(6,55.05,319.15,318.05,2.25,0,317.85,2.88,'29NOV2018',321,0,0,NULL,NULL,2500,309.1,318.9,0,156144401,3112500,321,0.71,13.08,316.65,'ADANIPORTS',0,317.75),(7,127.22,33.65,33.5,-1,0,33.6,6.66,'25OCT2018',34.65,0,0,NULL,NULL,20000,33,33.65,0,193065274,96820000,33.85,-2.89,-12.3,34.65,'ADANIPOWER',0,33.45),(8,127.22,34.2,33.25,-0.7,0,33.95,6.66,'29NOV2018',34.25,0,0,NULL,NULL,20000,33.2,34.2,0,193065274,84960000,33.2,-2.01,56.29,34.9,'ADANIPOWER',0,33.45),(9,47.69,1002.5,1000.85,0.55,0,1004.05,2.5,'25OCT2018',1021.85,0,0,NULL,NULL,500,960.05,1002.5,0,5195248,517000,990.75,0.05,-11.4,1001.95,'AJANTPHARM',0,999.2),(10,47.69,1010.1,1005.8,4.9,0,1007.35,2.5,'29NOV2018',1028.7,0,0,NULL,NULL,500,992,1010,0,5195248,64000,1001.9,0.49,128.57,1005.1,'AJANTPHARM',0,999.2),(11,56.13,37.1,36.9,-0.75,0,37.1,2.94,'25OCT2018',38.25,0,0,NULL,NULL,11000,36.6,37.1,0,50696289,13398000,37.1,-1.98,-6.88,37.85,'ALBK',0,37),(12,56.13,36.95,36.8,-0.85,0,36.95,2.94,'29NOV2018',38.1,0,0,NULL,NULL,11000,36.5,36.9,0,50696289,6589000,37,-2.25,42.96,37.75,'ALBK',0,37),(13,42.75,746.9,741.95,2.7,0,734.6,2.24,'25OCT2018',747.05,0,0,NULL,NULL,700,725.05,741,0,16377009,1299200,730.75,0.37,-1.07,738.3,'AMARAJABAT',0,750.5),(14,42.75,736,734,6.75,0,731.3,2.24,'29NOV2018',741.2,0,0,NULL,NULL,700,719.5,741.2,0,16377009,93100,719.5,0.92,17.7,734.45,'AMARAJABAT',0,750.5),(15,47.41,209.75,209.55,-9.15,0,208.95,2.48,'25OCT2018',217.5,0,0,NULL,NULL,2500,207.3,209.6,0,143727657,11665000,217.5,-4.18,-9.92,218.75,'AMBUJACEM',0,211.2),(16,47.41,210.95,210.55,-9.35,0,210.1,2.48,'29NOV2018',214.2,0,0,NULL,NULL,2500,209.5,211,0,143727657,382500,214.05,-4.24,98.7,220.35,'AMBUJACEM',0,211.2),(17,40,1126.4,1120.9,4.45,0,1121.2,2.09,'25OCT2018',1134.1,0,0,NULL,NULL,500,1102.2,1126.4,0,18254499,925500,1111.25,0.4,-1.86,1121.95,'APOLLOHOSP',0,1124.2),(18,40,1123.2,1118.45,-0.15,0,1119.4,2.09,'29NOV2018',1126,0,0,NULL,NULL,500,1100,1118.65,0,18254499,90000,1122,-0.01,62.16,1118.8,'APOLLOHOSP',0,1124.2),(19,57.94,207.45,206.6,-1.95,0,206.85,3.03,'25OCT2018',211.9,0,0,NULL,NULL,3000,204.55,207,0,68195170,10158000,208.5,-0.93,-1.31,208.95,'APOLLOTYRE',0,206.55),(20,57.94,208.5,207.9,-1.6,0,207.95,3.03,'29NOV2018',212.9,0,0,NULL,NULL,3000,206.25,208.5,0,68195170,666000,210.55,-0.76,37.89,210.1,'APOLLOTYRE',0,206.55),(21,62.21,323.5,323,7.85,0,322.85,3.26,'25OCT2018',326.4,0,0,NULL,NULL,2000,309.4,323,0,29523385,6120000,313.05,2.49,-6.13,315.15,'ARVIND',0,322.75),(22,62.21,325.2,323.75,8.35,0,324.3,3.26,'29NOV2018',327.55,0,0,NULL,NULL,2000,311.55,325,0,29523385,726000,311.55,2.64,33.46,316.65,'ARVIND',0,322.75),(23,55.91,113.1,113,-1.1,0,112.8,2.93,'25OCT2018',115.55,0,0,NULL,NULL,4000,112,113.1,0,286893403,53060000,113.95,-0.96,-0.08,114.2,'ASHOKLEY',0,113),(24,55.91,113.8,113.7,-0.95,0,113.4,2.93,'29NOV2018',116.1,0,0,NULL,NULL,4000,112.7,113.8,0,286893403,5108000,113.35,-0.83,50.06,114.75,'ASHOKLEY',0,113),(25,34.22,1235,1233.95,-21.55,0,1232.3,1.79,'25OCT2018',1288.05,0,0,NULL,NULL,600,1218,1233.95,0,90562661,5787600,1253.6,-1.72,3.65,1255.5,'ASIANPAINT',0,1238),(26,34.22,1240.15,1234.85,-24.85,0,1236.35,1.79,'29NOV2018',1291.95,0,0,NULL,NULL,600,1224,1234.5,0,90562661,231000,1264.55,-1.97,51.57,1259.35,'ASIANPAINT',0,1238),(27,48.3,748,747.2,-7.5,0,749.3,2.53,'25OCT2018',765.9,0,0,NULL,NULL,1000,739.2,747.9,0,56396335,19812000,753.85,-0.99,-1.21,755.4,'AUROPHARMA',0,745.85),(28,48.3,752.4,749.4,-9.3,0,752.75,2.53,'29NOV2018',768.2,0,0,NULL,NULL,1000,742.8,748.85,0,56396335,410000,753.4,-1.23,37.12,758.15,'AUROPHARMA',0,745.85),(29,44.94,564.3,564.05,-11.65,0,563.45,2.35,'25OCT2018',577.3,0,0,NULL,NULL,1200,555.25,564.15,0,334080359,35646000,568.85,-2.02,-6.31,575.8,'AXISBANK',0,562.55),(30,44.94,567.85,567.15,-11.95,0,566.85,2.35,'29NOV2018',579.95,0,0,NULL,NULL,1200,558.7,567.2,0,334080359,5490000,573.35,-2.06,53.68,579.15,'AXISBANK',0,562.55),(31,39.84,2528,2525.1,-25.85,0,2513.45,2.09,'25OCT2018',2548.3,0,0,NULL,NULL,250,2481,2525.15,0,29341807,2461500,2543.15,-1.01,-1.7,2551,'BAJAJ-AUTO',0,2524),(32,39.84,2542.8,2534.6,-24.7,0,2528.05,2.09,'29NOV2018',2559.8,0,0,NULL,NULL,250,2495.6,2538.95,0,29341807,118750,2559.45,-0.96,49.84,2563.65,'BAJAJ-AUTO',0,2524),(33,63.75,5459,5450,73.85,0,5433.75,3.34,'25OCT2018',5529.9,0,0,NULL,NULL,125,5251.15,5458.9,0,13255977,729500,5370.95,1.37,-2.93,5385.05,'BAJAJFINSV',0,5437),(34,63.75,5493.95,5480,68.3,0,5464.1,3.34,'29NOV2018',5555,0,0,NULL,NULL,125,5285.2,5483.75,0,13255977,51000,5371.2,1.26,147.27,5415.45,'BAJAJFINSV',0,5437),(35,75.99,2167.95,2166.05,19.5,0,2143.25,3.98,'25OCT2018',2195.95,0,0,NULL,NULL,500,2055,2167,0,51422718,7708000,2150,0.91,2.58,2147.5,'BAJFINANCE',0,2173.9),(36,75.99,2182.4,2175.85,23.1,0,2158.55,3.98,'29NOV2018',2207,0,0,NULL,NULL,500,2067.25,2180.75,0,51422718,517000,2150,1.07,53.19,2157.65,'BAJFINANCE',0,2173.9),(37,55.33,1042,1037,5.75,0,1041.35,2.9,'25OCT2018',1055.7,0,0,NULL,NULL,800,1019.4,1037,0,16124234,1680000,1025.05,0.56,-4.93,1031.25,'BALKRISIND',0,1041),(38,55.33,1047.75,1044.45,11.75,0,1045,2.9,'29NOV2018',1058.3,0,0,NULL,NULL,800,1027.5,1047.75,0,16124234,108000,1030.6,1.13,22.73,1036,'BALKRISIND',0,1041),(39,69.87,102.9,102.8,3.4,0,102.4,3.66,'25OCT2018',102.9,0,0,NULL,NULL,4000,97.7,102.8,0,192019087,71864000,98.8,3.42,-2.61,99.4,'BANKBARODA',0,102.85),(40,69.87,103.6,103.45,3.5,0,103,3.66,'29NOV2018',103.5,0,0,NULL,NULL,4000,99.65,103.45,0,192019087,8536000,99.65,3.5,21.11,99.95,'BANKBARODA',0,102.85),(41,57.8,76.8,76.7,0.15,0,76.85,3.03,'25OCT2018',78.4,0,0,NULL,NULL,6000,75.8,76.95,0,55436059,18930000,76.1,0.2,-0.5,76.8,'BANKINDIA',0,76.75),(42,57.8,77.2,77.05,-0.15,0,77.15,3.03,'29NOV2018',78.7,0,0,NULL,NULL,6000,76.3,76.95,0,55436059,2700000,76.4,-0.19,9.22,77.1,'BANKINDIA',0,76.75),(43,49.02,894,891.35,-20.6,0,895.75,2.57,'25OCT2018',918.4,0,0,NULL,NULL,1100,886.6,891.6,0,12092405,1615900,905.25,-2.26,2.8,912.2,'BATAINDIA',0,891),(44,49.02,899.8,895.9,-22.25,0,900.85,2.57,'29NOV2018',922.75,0,0,NULL,NULL,1100,891.8,897.2,0,12092405,190300,917.45,-2.42,58.72,919.45,'BATAINDIA',0,891),(45,66.76,83.2,83.15,-1.8,0,83.3,3.49,'25OCT2018',86.3,0,0,NULL,NULL,4950,82.35,83.5,0,165630346,27413100,85.05,-2.11,-1.44,85.3,'BEL',0,83.15),(46,66.76,83.65,83.55,-2.2,0,83.7,3.49,'29NOV2018',86.75,0,0,NULL,NULL,4950,82.9,83.55,0,165630346,2514600,84.9,-2.57,44.32,85.75,'BEL',0,83.15),(47,69.46,586.2,585,-3.3,0,583.85,3.64,'25OCT2018',594.8,0,0,NULL,NULL,500,563.3,585.5,0,3828900,1472500,582.3,-0.56,-2.29,588.8,'BEML',0,584),(48,69.46,588.3,586.5,-4.25,0,586,3.64,'29NOV2018',596.05,0,0,NULL,NULL,500,566,587.1,0,3828900,240500,578,-0.72,71.17,591.35,'BEML',0,584),(49,39.23,283.15,282.4,-5.1,0,282.5,2.05,'25OCT2018',300.95,0,0,NULL,NULL,2200,280.2,282.4,0,48578062,2164800,300.95,-1.77,-1.89,287.5,'BERGEPAINT',0,283.3),(50,39.23,281.1,279.25,-4.75,0,279.5,2.05,'29NOV2018',287.15,0,0,NULL,NULL,2200,277,279.6,0,48578062,704000,284.7,-1.67,43.5,284.35,'BERGEPAINT',0,283.3),(51,35.64,969.7,968.05,-14.5,0,967.8,1.87,'25OCT2018',988.45,0,0,NULL,NULL,500,960.5,969.8,0,27439068,6061000,980.05,-1.47,-1.44,984.3,'BHARATFIN',0,970.65),(52,35.64,975.95,973.05,-15.8,0,972.45,1.87,'29NOV2018',993.35,0,0,NULL,NULL,500,967.15,974.05,0,27439068,353000,987.2,-1.6,18.06,989.85,'BHARATFIN',0,970.65),(53,48.75,565.25,564.55,3,0,565.5,2.55,'25OCT2018',572,0,0,NULL,NULL,1200,555.25,564.6,0,50517678,7094400,558.95,0.53,1.04,561.6,'BHARATFORG',0,562.6),(54,48.75,568.25,566.3,2.75,0,568.4,2.55,'29NOV2018',573.3,0,0,NULL,NULL,1200,558,566.15,0,50517678,254400,568.8,0.49,70.97,563.4,'BHARATFORG',0,562.6),(55,42.58,287.4,286.05,-1.55,0,286.95,2.23,'25OCT2018',293.4,0,0,NULL,NULL,1700,285.05,287,0,262371370,48113400,287,-0.54,-4.84,288.55,'BHARTIARTL',0,286.9),(56,42.58,288.9,288,-2.7,0,288.95,2.23,'29NOV2018',294.9,0,0,NULL,NULL,1700,286.9,287.7,0,262371370,7296400,287.7,-0.93,39.67,290.4,'BHARTIARTL',0,286.9),(57,48.6,73.7,73.55,-0.35,0,73.35,2.54,'25OCT2018',75.3,0,0,NULL,NULL,7500,72.4,73.55,0,271244400,34852500,73,-0.47,-0.43,73.9,'BHEL',0,73.8),(58,48.6,74.1,74,-0.2,0,73.8,2.54,'29NOV2018',75.5,0,0,NULL,NULL,7500,72.7,74,0,271244400,4687500,74.3,-0.27,53.56,74.2,'BHEL',0,73.8),(59,50.59,664,663,28.15,0,660.4,2.65,'25OCT2018',664.4,0,0,NULL,NULL,900,630.9,663.35,0,45405205,5756400,634.55,4.43,-6.18,635.2,'BIOCON',0,662.75),(60,50.59,668.45,666.05,28.95,0,665.25,2.65,'29NOV2018',668.45,0,0,NULL,NULL,900,645.85,668,0,45405205,548100,646.15,4.53,20.12,639.05,'BIOCON',0,662.75),(61,41.3,19098,19027.35,694.4,0,19055.25,2.16,'25OCT2018',19098.05,0,0,NULL,NULL,30,18322.05,19049.2,0,1801607,126540,18485.9,3.78,-1.33,18354.8,'BOSCHLTD',0,19032.4),(62,41.3,19238.85,19112.2,818.25,0,19223.05,2.16,'29NOV2018',19241.35,0,0,NULL,NULL,30,18498.45,19241.35,0,1801607,2040,18498.45,4.44,61.9,18423.1,'BOSCHLTD',0,19032.4),(63,107.01,283.7,283.6,-2.25,0,284.15,5.6,'25OCT2018',291.65,0,0,NULL,NULL,1800,281.15,283.7,0,200049995,14749200,290.5,-0.79,1.73,285.95,'BPCL',0,283),(64,107.01,284.95,284.55,-2.35,0,285.45,5.6,'29NOV2018',293.05,0,0,NULL,NULL,1800,282.5,284.9,0,200049995,1362600,292,-0.82,55.12,287.25,'BPCL',0,283),(65,40.15,5590,5589.9,52.95,0,5567.7,2.1,'25OCT2018',5591.4,0,0,NULL,NULL,200,5470,5589.9,0,11837250,1285000,5520,0.96,-1.97,5536.95,'BRITANNIA',0,5565.05),(66,40.15,5629.9,5595,34.75,0,5599.45,2.1,'29NOV2018',5607.65,0,0,NULL,NULL,200,5520,5605,0,11837250,90600,5604.05,0.62,6.34,5570.25,'BRITANNIA',0,5565.05),(67,36.61,374.35,373.2,-4.1,0,372.95,1.92,'25OCT2018',380.8,0,0,NULL,NULL,1600,365.9,373.4,0,51610474,9024000,375.95,-1.09,-0.67,377.5,'CADILAHC',0,372.75),(68,36.61,376.7,375.05,-3.75,0,375.25,1.92,'29NOV2018',382.95,0,0,NULL,NULL,1600,368.5,376,0,51610474,360000,379.65,-0.99,61.87,379.75,'CADILAHC',0,372.75),(69,65.21,221.2,220.85,0.6,0,221,3.41,'25OCT2018',226.35,0,0,NULL,NULL,2000,216.4,220.85,0,40260000,8282000,218.2,0.27,-0.34,220.25,'CANBK',0,221.1),(70,65.21,222.5,222.1,0.7,0,222.2,3.41,'29NOV2018',227.45,0,0,NULL,NULL,2000,218,222,0,40260000,852000,218,0.32,31.08,221.3,'CANBK',0,221.1),(71,80.22,231.55,231.05,-0.6,0,230.45,4.2,'25OCT2018',239.3,0,0,NULL,NULL,1250,225,230.85,0,18644752,4980000,230.95,-0.26,-8.39,231.45,'CANFINHOME',0,231.45),(72,80.22,232.8,232.4,-2.15,0,232,4.2,'29NOV2018',240,0,0,NULL,NULL,1250,226.6,231.35,0,18644752,848750,226.6,-0.92,188.94,233.5,'CANFINHOME',0,231.45),(73,58.63,476.85,476.25,2.95,0,476.7,3.07,'25OCT2018',478.8,0,0,NULL,NULL,800,461.7,475.1,0,12767558,5618400,467.95,0.62,-7.24,472.15,'CAPF',0,477.05),(74,58.63,480,479.5,3.2,0,479.7,3.07,'29NOV2018',481.15,0,0,NULL,NULL,800,465.45,478.4,0,12767558,568800,470.95,0.67,120.12,475.2,'CAPF',0,477.05),(75,33.45,141.3,140.9,1.4,0,140.9,1.75,'25OCT2018',141.8,0,0,NULL,NULL,2800,138.8,140.75,0,96933993,8887200,140,1,-1.79,139.35,'CASTROLIND',0,141),(76,33.45,141.65,141,1.65,0,140.95,1.75,'29NOV2018',141.8,0,0,NULL,NULL,2800,139.25,141,0,96933993,926800,140.25,1.18,26.34,139.35,'CASTROLIND',0,141),(77,48.18,1104.5,1101.1,12.3,0,1103.75,2.52,'25OCT2018',1110,0,0,NULL,NULL,350,1046,1101.3,0,3983255,900900,1048.05,1.13,-5.37,1089,'CEATLTD',0,1098.05),(78,48.18,1108.75,1104,10.85,0,1105.65,2.52,'29NOV2018',1110,0,0,NULL,NULL,350,1080,1104.95,0,3983255,75250,1098,0.99,2.38,1094.1,'CEATLTD',0,1098.05),(79,60.53,769,767.2,-16.5,0,767.3,3.17,'25OCT2018',798,0,0,NULL,NULL,550,759.5,769.2,0,11123542,5829450,776,-2.1,-1.73,785.7,'CENTURYTEX',0,766),(80,60.53,773.95,771.45,-18.4,0,771.35,3.17,'29NOV2018',801.95,0,0,NULL,NULL,550,764.55,771.4,0,11123542,220550,779.4,-2.33,28.12,789.8,'CENTURYTEX',0,766),(81,59.91,909.15,907.7,13.1,0,907.1,3.14,'25OCT2018',914.35,0,0,NULL,NULL,550,888,907.75,0,13275380,4936250,892.05,1.46,-2.73,894.65,'CESC',0,907.65),(82,59.91,909.35,907.8,15.5,0,908.2,3.14,'29NOV2018',915,0,0,NULL,NULL,550,892.55,910.35,0,13275380,169400,894,1.73,22.22,894.85,'CESC',0,907.65),(83,55.55,37.8,37.65,-1.25,0,37.7,2.91,'25OCT2018',39.3,0,0,NULL,NULL,12000,37.25,37.65,0,82103824,33456000,38.5,-3.21,-4.19,38.9,'CGPOWER',0,37.85),(84,55.55,38.1,37.9,-1.25,0,37.9,2.91,'29NOV2018',39.5,0,0,NULL,NULL,12000,37.75,37.85,0,82103824,5844000,38.75,-3.2,226.85,39.1,'CGPOWER',0,37.85),(85,75.11,263,262.05,6,0,265.3,3.93,'25OCT2018',268.55,0,0,NULL,NULL,1500,248.95,262.15,0,9742660,1143000,249,2.34,3.25,256.15,'CHENNPETRO',0,263.25),(86,75.11,267.3,265,6.6,0,266.05,3.93,'29NOV2018',270,0,0,NULL,NULL,1500,253.85,264.8,0,9742660,112500,255.6,2.56,70.45,258.2,'CHENNPETRO',0,263.25),(87,78.37,1119.6,1111.2,-19.05,0,1113.05,4.1,'25OCT2018',1163.35,0,0,NULL,NULL,500,1091,1112.6,0,14676781,812500,1130.1,-1.68,-9.01,1131.65,'CHOLAFIN',0,1117),(88,78.37,1125.9,1115.2,-16.35,0,1118.2,4.1,'29NOV2018',1167.85,0,0,NULL,NULL,500,1098.75,1118.75,0,14676781,32000,1150.4,-1.44,25.49,1135.1,'CHOLAFIN',0,1117),(89,31.07,634.8,634,-4.9,0,634.85,1.63,'25OCT2018',645.05,0,0,NULL,NULL,1000,626.85,634.05,0,101639067,10330000,639.2,-0.77,-3.02,638.95,'CIPLA',0,633.5),(90,31.07,637.5,636,-5.2,0,637.55,1.63,'29NOV2018',647,0,0,NULL,NULL,1000,629.35,636.1,0,101639067,626000,641,-0.81,14.03,641.3,'CIPLA',0,633.5),(91,38.42,277.35,277,-2.9,0,276.1,2.01,'25OCT2018',281.85,0,0,NULL,NULL,2200,275.3,277.35,0,269145789,19599800,280.4,-1.03,-1.19,280.25,'COALINDIA',0,276),(92,38.42,278.15,277.75,-2.7,0,277.2,2.01,'29NOV2018',282.5,0,0,NULL,NULL,2200,276.3,278.45,0,269145789,1760000,281,-0.96,4.58,281.15,'COALINDIA',0,276),(93,27.79,1101.85,1100.8,15.25,0,1101.05,1.45,'25OCT2018',1103.1,0,0,NULL,NULL,700,1087,1102.7,0,26654592,1624000,1092.95,1.4,-1.4,1087.45,'COLPAL',0,1100),(94,27.79,1102.95,1099.7,10.65,0,1100.25,1.45,'29NOV2018',1103.75,0,0,NULL,NULL,700,1089.7,1100,0,26654592,35000,1095,0.98,51.52,1089.35,'COLPAL',0,1100),(95,39.91,597,596,-0.95,0,593.15,2.09,'25OCT2018',606.85,0,0,NULL,NULL,1250,583.05,596.7,0,44065499,3200000,583.05,-0.16,1.35,597.65,'CONCOR',0,595),(96,39.91,595.25,593.35,-4.7,0,594.45,2.09,'29NOV2018',604.05,0,0,NULL,NULL,1250,589.95,593.6,0,44065499,107500,597.8,-0.79,72,598.3,'CONCOR',0,595),(97,43.34,697.85,695.35,11.4,0,697.45,2.27,'25OCT2018',700.6,0,0,NULL,NULL,700,674.95,695.55,0,27165463,1043700,675,1.67,-4.79,684.15,'CUMMINSIND',0,700),(98,43.34,702.7,698.3,16.75,0,700.7,2.27,'29NOV2018',703.9,0,0,NULL,NULL,700,680,702.4,0,27165463,41300,680,2.44,210.53,685.65,'CUMMINSIND',0,700),(99,48.02,406.65,405.9,2.8,0,405.25,2.51,'25OCT2018',413.4,0,0,NULL,NULL,2500,399.5,406.5,0,113452748,16870000,400,0.69,-0.46,403.7,'DABUR',0,406.4),(100,48.02,408,406.6,1.65,0,406.4,2.51,'29NOV2018',414.05,0,0,NULL,NULL,2500,401.15,406.6,0,113452748,232500,407.95,0.41,-1.06,404.95,'DABUR',0,406.4),(101,46.66,1999.4,1993.95,-49.55,0,1997.75,2.44,'25OCT2018',2062.6,0,0,NULL,NULL,300,1977.6,2006.55,0,6976232,407100,2028.15,-2.41,-1.88,2056.1,'DALMIABHA',0,2005),(102,46.66,1998.9,1981.5,-50.05,0,1990.95,2.44,'29NOV2018',2049.1,0,0,NULL,NULL,300,1977.3,1999.25,0,6976232,36300,2021.25,-2.44,65.75,2049.3,'DALMIABHA',0,2005),(103,58.03,158.6,157.25,-2.25,0,157.9,3.04,'25OCT2018',163.25,0,0,NULL,NULL,4500,155.25,157.25,0,52504138,4977000,156.95,-1.41,-3.57,159.5,'DCBBANK',0,158.1),(104,58.03,155.95,154.5,-1.05,0,154.85,3.04,'29NOV2018',161,0,0,NULL,NULL,4500,149.1,155.9,0,52504138,1179000,158.5,-0.67,56.89,156.95,'DCBBANK',0,158.1),(105,226.39,210.9,210.25,-19.45,0,210.9,11.85,'25OCT2018',241.25,0,0,NULL,NULL,1500,203.8,210.5,0,38121826,16614000,222.65,-8.46,-7.38,229.95,'DHFL',0,210.1),(106,226.39,210.55,206,-17.35,0,208.45,11.85,'29NOV2018',236.7,0,0,NULL,NULL,1500,203,209,0,38121826,2154000,220,-7.67,-0.49,226.35,'DHFL',0,210.1),(107,64.22,46.85,46.8,-3.55,0,47,3.36,'25OCT2018',50.2,0,0,NULL,NULL,8000,45.8,46.85,0,234099275,39448000,50,-7.04,4.6,50.4,'DISHTV',0,46.85),(108,64.22,47.1,46.95,-3.3,0,47.1,3.36,'29NOV2018',49.95,0,0,NULL,NULL,8000,46.05,47.35,0,234099275,1704000,49.7,-6.52,129.03,50.65,'DISHTV',0,46.85),(109,39.52,1283.85,1278.05,-20.4,0,1289.45,2.07,'25OCT2018',1340,0,0,NULL,NULL,800,1278.05,1278.05,0,25468469,2396000,1295.8,-1.57,-1.51,1298.45,'DIVISLAB',0,1280.05),(110,39.52,1293,1287,-18.9,0,1295.7,2.07,'29NOV2018',1347.25,0,0,NULL,NULL,800,1288,1288,0,25468469,153600,1305,-1.45,123.26,1306.9,'DIVISLAB',0,1280.05),(111,81.81,152.95,152.6,8.8,0,152.65,4.28,'25OCT2018',154.9,0,0,NULL,NULL,2500,141.75,152.95,0,89396379,27695000,142.6,6.1,-6.24,144.15,'DLF',0,152.95),(112,81.81,153.8,153.3,8.1,0,153.5,4.28,'29NOV2018',155.6,0,0,NULL,NULL,2500,142.9,153.25,0,89396379,2262500,144,5.58,86.98,145.15,'DLF',0,152.95),(113,40.73,2556.85,2550.45,-10.1,0,2558.2,2.13,'25OCT2018',2584.95,0,0,NULL,NULL,250,2536,2551,0,24302940,3981000,2554.9,-0.39,0.96,2561.1,'DRREDDY',0,2546),(114,40.73,2569,2562.9,-9.85,0,2572.8,2.13,'29NOV2018',2598.55,0,0,NULL,NULL,250,2551.95,2565.2,0,24302940,201250,2564.5,-0.38,23.47,2575.05,'DRREDDY',0,2546),(115,68.9,21780,21731.65,-283.2,0,21691.2,3.61,'25OCT2018',22349.25,0,0,NULL,NULL,25,21430.2,21733.7,0,2700823,306275,22075.85,-1.29,1.26,22016.9,'EICHERMOT',0,21718.95),(116,68.9,21933.4,21780.05,-276.2,0,21780.65,3.61,'29NOV2018',22448,0,0,NULL,NULL,25,21555.2,21880,0,2700823,16450,22100,-1.25,86.4,22156.2,'EICHERMOT',0,21718.95),(117,62.61,115.95,115.55,0.3,0,115.4,3.28,'25OCT2018',116,0,0,NULL,NULL,3500,112.1,115,0,60644337,6982500,114.3,0.26,0.15,114.7,'ENGINERSIN',0,115.5),(118,62.61,116.5,115.85,0.7,0,115.7,3.28,'29NOV2018',116.3,0,0,NULL,NULL,3500,112.5,116,0,60644337,1036000,115.8,0.61,34.55,115.3,'ENGINERSIN',0,115.5),(119,67.69,126,125.8,0.5,0,125.95,3.54,'25OCT2018',128.25,0,0,NULL,NULL,4000,121.1,125.6,0,68164988,10060000,121.1,0.4,-6.58,125.1,'EQUITAS',0,126),(120,67.69,126.7,126.35,-0.1,0,126.65,3.54,'29NOV2018',128.9,0,0,NULL,NULL,4000,124,125.8,0,68164988,284000,124,-0.08,57.78,125.9,'EQUITAS',0,126),(121,65.46,603.5,601.65,-16.2,0,604.6,3.43,'25OCT2018',617.95,0,0,NULL,NULL,1100,584.5,601.8,0,14692848,4224000,612.05,-2.62,-3.83,618,'ESCORTS',0,604),(122,65.46,607.8,604.8,-16.7,0,607.65,3.43,'29NOV2018',619.9,0,0,NULL,NULL,1100,588.5,604.85,0,14692848,261800,614,-2.69,60.81,621.55,'ESCORTS',0,604),(123,44.59,254.9,254.4,-6.95,0,254.5,2.33,'25OCT2018',262.45,0,0,NULL,NULL,4000,252.4,254.9,0,91809066,5232000,261,-2.65,0.77,261.85,'EXIDEIND',0,254.25),(124,44.59,255,254.3,-6.45,0,254.5,2.33,'29NOV2018',261.95,0,0,NULL,NULL,4000,252.1,255,0,91809066,424000,261.85,-2.47,21.84,261.45,'EXIDEIND',0,254.25),(125,60.75,81.3,81.15,2.6,0,81.15,3.18,'25OCT2018',81.5,0,0,NULL,NULL,5500,77.1,81.2,0,389129208,43477500,78.4,3.31,-5.7,78.6,'FEDERALBNK',0,81.4),(126,60.75,81.7,81.5,2.55,0,81.55,3.18,'29NOV2018',81.9,0,0,NULL,NULL,5500,77.5,81.55,0,389129208,5918000,79,3.23,67.6,79,'FEDERALBNK',0,81.4),(127,53.69,344.95,344.35,-1.7,0,345,2.81,'25OCT2018',358.7,0,0,NULL,NULL,2667,343.4,344.4,0,209171490,19111722,348.2,-0.49,-2.6,346.1,'GAIL',0,343.8),(128,53.69,346.75,345.9,-1.95,0,346.5,2.81,'29NOV2018',360.35,0,0,NULL,NULL,2667,345.05,346.4,0,209171490,1778889,351,-0.56,53.33,348.35,'GAIL',0,343.8),(129,32.96,601.5,599.3,-0.3,0,600.3,1.73,'25OCT2018',603.95,0,0,NULL,NULL,1000,591.4,599.4,0,30171674,4218000,595.05,-0.05,-0.38,599.7,'GLENMARK',0,599),(130,32.96,603.7,602.45,-0.45,0,603.2,1.73,'29NOV2018',606.85,0,0,NULL,NULL,1000,595,602.65,0,30171674,249000,604.25,-0.07,52.76,603.1,'GLENMARK',0,599),(131,64.47,15.75,15.7,-0.05,0,15.7,3.37,'25OCT2018',16,0,0,NULL,NULL,45000,15.3,15.7,0,450418739,177795000,15.75,-0.32,-1.42,15.75,'GMRINFRA',0,15.75),(132,64.47,15.9,15.8,0.1,0,15.85,3.37,'29NOV2018',16.1,0,0,NULL,NULL,45000,15.4,15.9,0,450418739,28710000,15.85,0.63,71.51,15.8,'GMRINFRA',0,15.75),(133,60.68,740,733.4,-6.05,0,738.5,3.18,'25OCT2018',744.8,0,0,NULL,NULL,700,726.25,733.4,0,2891826,490000,730.75,-0.82,-4.5,739.45,'GODFRYPHLP',0,737.9),(134,60.68,747,739.25,-2.7,0,741.15,3.18,'29NOV2018',747.55,0,0,NULL,NULL,700,730.35,740.05,0,2891826,46900,738.9,-0.36,81.08,742.75,'GODFRYPHLP',0,737.9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;