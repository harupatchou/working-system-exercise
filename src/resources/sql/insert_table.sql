/*会社情報*/
INSERT INTO company VALUES (1, 'A会社');
INSERT INTO company VALUES (2, 'B会社');
INSERT INTO company VALUES (3, 'C会社');

/*労働制*/
INSERT INTO labor_system VALUES (1, '通常労働制');
INSERT INTO labor_system VALUES (2, '変形労働制');
INSERT INTO labor_system VALUES (3, 'フレックス制');

/*従業員種別*/
INSERT INTO workingtype VALUES (1, '正社員', 1,1);
INSERT INTO workingtype VALUES (2, 'パート/アルバイト', 2,1);
INSERT INTO workingtype VALUES (3, 'フレックス社員', 3,1);

/*出退勤時間*/
INSERT INTO attendance_time VALUES (1,'09:00','18:00','','',1);

/*従業員情報*/
INSERT INTO employee VALUES (1, '通常','従業員','rakus2000',1,1);
INSERT INTO employee VALUES (2, '変形','従業員','rakus2000',1,2);
INSERT INTO employee VALUES (3, 'フレックス','従業員','rakus2000',1,3);

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
INSERT INTO working_day VALUES(1,'2015/12/1',3,'8:06','23:32','1:00','0:00',1,0);
INSERT INTO working_day VALUES(2,'2015/12/2',4,'8:58','19:46','1:00','0:00',1,0);
INSERT INTO working_day VALUES(3,'2015/12/3',5,'8:48','19:19','1:00','0:00',1,0);
INSERT INTO working_day VALUES(4,'2015/12/4',6,'8:52','19:27','1:00','0:00',1,0);
INSERT INTO working_day VALUES(5,'2015/12/5',7,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(6,'2015/12/6',1,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(7,'2015/12/7',2,'8:52','21:08','1:00','0:00',1,0);
INSERT INTO working_day VALUES(8,'2015/12/8',3,'8:50','0:00','1:00','0:00',1,0);
INSERT INTO working_day VALUES(9,'2015/12/9',4,'8:56','20:17','1:00','0:00',1,0);
INSERT INTO working_day VALUES(10,'2015/12/10',5,'8:41','21:30','1:00','0:00',1,0);
INSERT INTO working_day VALUES(11,'2015/12/11',6,'8:49','21:25','1:00','0:00',1,0);
INSERT INTO working_day VALUES(12,'2015/12/12',7,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(13,'2015/12/13',1,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(14,'2015/12/14',2,'8:54','20:06','1:00','0:00',1,0);
INSERT INTO working_day VALUES(15,'2015/12/15',3,'8:53','21:26','1:00','0:00',1,0);
INSERT INTO working_day VALUES(16,'2015/12/16',4,'8:48','21:31','1:00','0:00',1,0);
INSERT INTO working_day VALUES(17,'2015/12/17',5,'8:55','18:49','1:00','0:00',1,0);
INSERT INTO working_day VALUES(18,'2015/12/18',6,'8:53','18:51','1:00','0:00',1,0);
INSERT INTO working_day VALUES(19,'2015/12/19',7,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(20,'2015/12/20',1,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(21,'2015/12/21',2,'8:56','20:16','1:00','0:00',1,0);
INSERT INTO working_day VALUES(22,'2015/12/22',3,'8:53','19:27','1:00','0:00',1,0);
INSERT INTO working_day VALUES(23,'2015/12/23',4,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(24,'2015/12/24',5,'8:52','18:29','1:00','0:00',1,0);
INSERT INTO working_day VALUES(25,'2015/12/25',6,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(26,'2015/12/26',7,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(27,'2015/12/27',1,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(28,'2015/12/28',2,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(29,'2015/12/29',3,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(30,'2015/12/30',4,'0:00','0:00','0:00','0:00',1,0);
INSERT INTO working_day VALUES(31,'2015/12/31',5,'0:00','0:00','0:00','0:00',1,0);


/*残業時間*/
INSERT INTO overtime VALUES(1,'0:00','2:00',1);
INSERT INTO overtime VALUES(2,'0:00','1:46',2);
INSERT INTO overtime VALUES(3,'0:00','1:19',3);
INSERT INTO overtime VALUES(4,'0:00','1:27',4);
INSERT INTO overtime VALUES(5,'0:00','0:00',5);
INSERT INTO overtime VALUES(6,'0:00','0:00',6);
INSERT INTO overtime VALUES(7,'0:00','3:08',7);
INSERT INTO overtime VALUES(8,'0:00','0:00',8);
INSERT INTO overtime VALUES(9,'0:00','2:17',9);
INSERT INTO overtime VALUES(10,'0:00','3:30',10);
INSERT INTO overtime VALUES(11,'0:00','2:30',11);
INSERT INTO overtime VALUES(12,'0:00','0:00',12);
INSERT INTO overtime VALUES(13,'0:00','0:00',13);
INSERT INTO overtime VALUES(14,'0:00','2:06',14);
INSERT INTO overtime VALUES(15,'0:00','3:26',15);
INSERT INTO overtime VALUES(16,'0:00','3:31',16);
INSERT INTO overtime VALUES(17,'0:00','0:49',17);
INSERT INTO overtime VALUES(18,'0:00','0:51',18);
INSERT INTO overtime VALUES(19,'0:00','0:00',19);
INSERT INTO overtime VALUES(20,'0:00','0:00',20);
INSERT INTO overtime VALUES(21,'0:00','2:16',21);
INSERT INTO overtime VALUES(22,'0:00','1:27',22);
INSERT INTO overtime VALUES(23,'0:00','0:00',23);
INSERT INTO overtime VALUES(24,'0:00','0:29',24);
INSERT INTO overtime VALUES(25,'0:00','0:00',25);
INSERT INTO overtime VALUES(26,'0:00','0:00',26);
INSERT INTO overtime VALUES(27,'0:00','0:00',27);
INSERT INTO overtime VALUES(28,'0:00','0:00',28);
INSERT INTO overtime VALUES(29,'0:00','0:00',29);
INSERT INTO overtime VALUES(30,'0:00','0:00',30);
INSERT INTO overtime VALUES(31,'0:00','0:00',31);

/*総労働時間*/
/*通常労働時間*/
INSERT INTO working_all VALUES(1,'2015/12/1',3,'13:32','0:00','2:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(2,'2015/12/2',4,'9:46','0:00','1:46','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(3,'2015/12/3',5,'9:19','0:00','1:19','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(4,'2015/12/4',6,'9:27','0:00','1:27','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(5,'2015/12/5',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(6,'2015/12/6',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(7,'2015/12/7',2,'11:08','0:00','3:08','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(8,'2015/12/8',3,'7:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(9,'2015/12/9',4,'10:17','0:00','2:17','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(10,'2015/12/10',5,'11:30','0:00','3:30','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(11,'2015/12/11',6,'10:30','0:00','2:30','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(12,'2015/12/12',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(13,'2015/12/13',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(14,'2015/12/14',2,'10:06','0:00','2:06','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(15,'2015/12/15',3,'11:26','0:00','3:26','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(16,'2015/12/16',4,'11:31','0:00','3:31','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(17,'2015/12/17',5,'8:49','0:00','0:49','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(18,'2015/12/18',6,'8:51','0:00','0:51','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(19,'2015/12/19',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(20,'2015/12/20',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(21,'2015/12/21',2,'10:16','0:00','2:16','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(22,'2015/12/22',3,'9:27','0:00','1:27','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(23,'2015/12/23',4,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(24,'2015/12/24',5,'8:29','0:00','0:29','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(25,'2015/12/25',6,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(26,'2015/12/26',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(27,'2015/12/27',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(28,'2015/12/28',2,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(29,'2015/12/29',3,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(30,'2015/12/30',4,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);
INSERT INTO working_all VALUES(31,'2015/12/31',5,'0:00','0:00','0:00','0:00','0:00','0:00',0,1);

/*変形労働時間*/
INSERT INTO working_all VALUES(32,'2016/1/1',6,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(33,'2016/1/2',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(34,'2016/1/3',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(35,'2016/1/4',2,'7:52','0:22','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(36,'2016/1/5',3,'7:30','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(37,'2016/1/6',4,'11:26','0:30','3:26','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(38,'2016/1/7',5,'10:35','0:30','2:35','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(39,'2016/1/8',6,'8:59','0:30','0:59','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(40,'2016/1/9',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(41,'2016/1/10',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(42,'2016/1/11',2,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(43,'2016/1/12',3,'9:32','0:30','1:32','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(44,'2016/1/13',4,'8:52','0:30','0:52','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(45,'2016/1/14',5,'9:33','0:30','1:33','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(46,'2016/1/15',6,'11:00','0:30','3:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(47,'2016/1/16',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(48,'2016/1/17',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(49,'2016/1/18',2,'10:03','0:30','2:03','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(50,'2016/1/19',3,'9:08','0:30','1:08','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(51,'2016/1/20',4,'8:25','0:30','0:25','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(52,'2016/1/21',5,'11:22','0:30','3:22','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(53,'2016/1/22',6,'8:50','0:30','0:50','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(54,'2016/1/23',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(55,'2016/1/24',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(56,'2016/1/25',2,'10:40','0:30','2:40','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(57,'2016/1/26',3,'7:40','0:10','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(58,'2016/1/27',4,'11:01','0:30','3:01','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(59,'2016/1/28',5,'9:30','0:30','1:30','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(60,'2016/1/29',6,'8:35','0:30','0:35','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(61,'2016/1/30',7,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);
INSERT INTO working_all VALUES(62,'2016/1/31',1,'0:00','0:00','0:00','0:00','0:00','0:00',0,2);

ALTER SEQUENCE overtime_id_seq RESTART WITH 62;
ALTER SEQUENCE working_all_id_seq RESTART WITH 62;
ALTER SEQUENCE working_day_id_seq RESTART WITH 62;
