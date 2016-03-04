
/*労働種別*/
INSERT INTO workingtype VALUES (1, '通常労働制', 8);
INSERT INTO workingtype VALUES (2, '変形労働制', 7.5);
INSERT INTO workingtype VALUES (3, 'フレックス制', f);

/*会社情報*/
INSERT INTO company VALUES (1, '通常労働会社', 1);
INSERT INTO company VALUES (2, '変形労働会社', 2);
INSERT INTO company VALUES (3, 'フレックス制会社', 3);

/*従業員情報*/
INSERT INTO employee VALUES (1, '通常','従業員','rakus2000',1);
INSERT INTO employee VALUES (2, '変形','従業員','rakus2000',2);
INSERT INTO employee VALUES (3, 'フレックス','従業員','rakus2000',3);

/*週情報*/
INSERT INTO week VALUES (1, '日',1);
INSERT INTO week VALUES (2, '月',0);
INSERT INTO week VALUES (3, '火',0);
INSERT INTO week VALUES (4, '水',0);
INSERT INTO week VALUES (5, '木',0);
INSERT INTO week VALUES (6, '金',0);
INSERT INTO week VALUES (7, '土',1);
INSERT INTO week VALUES (8, '祝日',1);

/*労働時間*/
INSERT INTO working_day VALUES(1,'2016/12/1','8:06','23:32','1:00','0:00',1,0);
INSERT INTO working_day VALUES(2,'2016/12/2','8:58','19:46','1:00','0:00',1,0);
INSERT INTO working_day VALUES(3,'2016/12/3','8:48','19:19','1:00','0:00',1,0);
INSERT INTO working_day VALUES(4,'2016/12/4','8:52','19:27','1:00','0:00',1,0);
INSERT INTO working_day VALUES(5,'2016/12/5','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(6,'2016/12/6','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(7,'2016/12/7','8:52','21:08','1:00','0:00',1,0);
INSERT INTO working_day VALUES(8,'2016/12/8','8:50','0:00','1:00','0:00',1,0);
INSERT INTO working_day VALUES(9,'2016/12/9','8:56','20:17','1:00','0:00',1,0);
INSERT INTO working_day VALUES(10,'2016/12/10','8:41','21:30','1:00','0:00',1,0);
INSERT INTO working_day VALUES(11,'2016/12/11','8:49','21:25','1:00','0:00',1,0);
INSERT INTO working_day VALUES(12,'2016/12/12','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(13,'2016/12/13','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(14,'2016/12/14','8:54','20:06','1:00','0:00',1,0);
INSERT INTO working_day VALUES(15,'2016/12/15','8:53','21:26','1:00','0:00',1,0);
INSERT INTO working_day VALUES(16,'2016/12/16','8:48','21:31','1:00','0:00',1,0);
INSERT INTO working_day VALUES(17,'2016/12/17','8:55','18:49','1:00','0:00',1,0);
INSERT INTO working_day VALUES(18,'2016/12/18','8:53','18:51','1:00','0:00',1,0);
INSERT INTO working_day VALUES(19,'2016/12/19','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(20,'2016/12/20','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(21,'2016/12/21','8:56','20:16','1:00','0:00',1,0);
INSERT INTO working_day VALUES(22,'2016/12/22','9:00','18:00','1:00','0:00',1,0);
INSERT INTO working_day VALUES(23,'2016/12/23','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(24,'2016/12/24','8:52','18:29','1:00','0:00',1,0);
INSERT INTO working_day VALUES(25,'2016/12/25','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(26,'2016/12/26','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(27,'2016/12/27','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(28,'2016/12/28','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(29,'2016/12/29','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(30,'2016/12/30','0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(31,'2016/12/31','0:00','0:00','0:00','0:00',1,0);

/*残業時間*/
INSERT INTO overtime VALUES(1,'2:00',1);
INSERT INTO overtime VALUES(2,'1:46',1);
INSERT INTO overtime VALUES(3,'1:19',1);
INSERT INTO overtime VALUES(4,'1:27',1);
INSERT INTO overtime VALUES(5,'0:00',1);
INSERT INTO overtime VALUES(6,'0:00',1);
INSERT INTO overtime VALUES(7,'3:08',1);
INSERT INTO overtime VALUES(8,'0:00',1);
INSERT INTO overtime VALUES(9,'2:17',1);
INSERT INTO overtime VALUES(10,'3:30',1);
INSERT INTO overtime VALUES(11,'2:30',1);
INSERT INTO overtime VALUES(12,'0:00',1);
INSERT INTO overtime VALUES(13,'0:00',1);
INSERT INTO overtime VALUES(14,'2:06',1);
INSERT INTO overtime VALUES(15,'3:26',1);
INSERT INTO overtime VALUES(16,'3:31',1);
INSERT INTO overtime VALUES(17,'0:49',1);
INSERT INTO overtime VALUES(18,'0:51',1);
INSERT INTO overtime VALUES(19,'0:00',1);
INSERT INTO overtime VALUES(20,'0:00',1);
INSERT INTO overtime VALUES(21,'2:16',1);
INSERT INTO overtime VALUES(22,'1:27',1);
INSERT INTO overtime VALUES(23,'0:00',1);
INSERT INTO overtime VALUES(24,'0:29',1);
INSERT INTO overtime VALUES(25,'0:00',1);
INSERT INTO overtime VALUES(26,'0:00',1);
INSERT INTO overtime VALUES(27,'0:00',1);
INSERT INTO overtime VALUES(28,'0:00',1);
INSERT INTO overtime VALUES(29,'0:00',1);
INSERT INTO overtime VALUES(30,'0:00',1);
INSERT INTO overtime VALUES(31,'0:00',1);

/*総労働時間*/
INSERT INTO working_all VALUES(1,1,12,2015,'火','10:00','2:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(2,2,12,2015,'水','9:46','1:46','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(3,3,12,2015,'木','9:19','1:19','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(4,4,12,2015,'金','9:27','1:27','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(5,5,12,2015,'土','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(6,6,12,2015,'日','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(7,7,12,2015,'月','11:08','3:08','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(8,8,12,2015,'火','7:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(9,9,12,2015,'水','10:17','2:17','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(10,10,12,2015,'木','11:30','3:30','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(11,11,12,2015,'金','10:30','2:30','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(12,12,12,2015,'土','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(13,13,12,2015,'日','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(14,14,12,2015,'月','10:06','2:06','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(15,15,12,2015,'火','11:26','3:26','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(16,16,12,2015,'水','11:31','3:31','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(17,17,12,2015,'木','8:49','0:49','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(18,18,12,2015,'金','8:51','0:51','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(19,19,12,2015,'土','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(20,20,12,2015,'日','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(21,21,12,2015,'月','10:16','2:16','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(22,22,12,2015,'火','9:27','1:27','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(23,23,12,2015,'水','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(24,24,12,2015,'木','8:29','0:29','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(25,25,12,2015,'金','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(26,26,12,2015,'土','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(27,27,12,2015,'日','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(28,28,12,2015,'月','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(29,29,12,2015,'火','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(30,30,12,2015,'水','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);
INSERT INTO working_all VALUES(31,31,12,2015,'木','0:00','0:00','0:00','0:00','0:00','0:00','0:00',1);