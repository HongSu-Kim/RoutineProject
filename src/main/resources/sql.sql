DROP TABLE IF EXISTS harugom.MISSION;
DROP TABLE IF EXISTS harugom.MISSION_ICON;
DROP TABLE IF EXISTS harugom.ICON_CATEGORY;
DROP TABLE IF EXISTS harugom.ROUTINE_SET;
DROP TABLE IF EXISTS harugom.ROUTINE;
DROP TABLE IF EXISTS harugom.REPLY;
DROP TABLE IF EXISTS harugom.BOARD_IMAGE;
DROP TABLE IF EXISTS harugom.BOARD;
DROP TABLE IF EXISTS harugom.MEMBER;


CREATE TABLE harugom.MEMBER (
	member_id		integer			NOT NULL	AUTO_INCREMENT,
	email			varchar(20)		NOT NULL	UNIQUE,
	pwd				varchar(100)	NOT NULL,
	nickname		varchar(20)		NOT NULL	UNIQUE,
	gender			varchar(10)		NOT NULL,
	birth			date			NOT NULL,
	role			varchar(20)		NOT NULL,
	join_date		datetime		DEFAULT NOW(),
	CONSTRAINT PK_MEMBER PRIMARY KEY (member_id)
);

CREATE TABLE harugom.BOARD (
	board_id		integer			NOT NULL	AUTO_INCREMENT,
	member_id		integer			NOT NULL,
	category		varchar(20)		NOT NULL,
	board_title		varchar(200)	NOT NULL,
	board_content	varchar(2048)	NOT NULL,
	board_create	datetime		DEFAULT NOW(),
	board_modify	datetime		DEFAULT NULL,
	board_hits		integer			DEFAULT 0,
	CONSTRAINT PK_BOARD PRIMARY KEY (board_id),
	CONSTRAINT FK_BOARD_MEMBER FOREIGN KEY (member_id) REFERENCES MEMBER (member_id)
);

CREATE TABLE harugom.BOARD_IMAGE (
	board_image_id	integer			NOT NULL	AUTO_INCREMENT,
	board_id		integer			NOT NULL,
	image_file_name	varchar(50)		NOT NULL,
	CONSTRAINT PK_BOARD_IMAGE PRIMARY KEY (board_image_id),
	CONSTRAINT FK_BOARD_IMAGE_BOARD FOREIGN KEY (board_id) REFERENCES BOARD (board_id)
);

CREATE TABLE harugom.REPLY (
	reply_id		integer			NOT NULL	AUTO_INCREMENT,
	member_id		integer			NOT NULL,
	board_id		integer			NOT NULL,
	reply_content	varchar(1024)	NOT NULL,
	reply_create	datetime		DEFAULT NOW(),
	CONSTRAINT PK_REPLY PRIMARY KEY (reply_id),
	CONSTRAINT FK_REPLY_MEMBER FOREIGN KEY (member_id) REFERENCES MEMBER (member_id),
	CONSTRAINT FK_REPLY_BOARD FOREIGN KEY (board_id) REFERENCES BOARD (board_id)
);

CREATE TABLE harugom.ROUTINE (
	routine_id		integer			NOT NULL	AUTO_INCREMENT,
	member_id		integer			NOT NULL,
	routine_name	varchar(50)		NOT NULL,
	routine_active	boolean			DEFAULT TRUE,
	total_time		time			NOT NULL,
	CONSTRAINT PK_ROUTINE PRIMARY KEY (routine_id),
	CONSTRAINT FK_ROUTINE_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE harugom.ROUTINE_SET (
	week			varchar(20)		NOT NULL,
	routine_id		integer			NOT NULL,
	week_active		boolean			NOT NULL,
	start_time		time			NOT NULL,
	CONSTRAINT PK_ROUTINE_SET PRIMARY KEY (week,routine_id),
	CONSTRAINT FK_ROUTINE_SET_ROUTINE FOREIGN KEY (routine_id) REFERENCES ROUTINE (routine_id)
);

CREATE TABLE harugom.ICON_CATEGORY (
	icon_category_id	integer			NOT NULL	AUTO_INCREMENT,
	category_name		varchar(50)		NOT NULL,
	icon_path			varchar(50)		NOT NULL,
	pay					boolean			DEFAULT TRUE,
	CONSTRAINT PK_ICON_CATEGORY PRIMARY KEY (icon_category_id)
);

CREATE TABLE harugom.MISSION_ICON (
	mission_icon_id		integer			NOT NULL	AUTO_INCREMENT,
	icon_category_id	integer			NOT NULL,
	icon_file_name		varchar(50)		NOT NULL,
	CONSTRAINT PK_MISSION_ICON PRIMARY KEY (mission_icon_id),
	CONSTRAINT FK_MISSION_ICON_ICON_CATEGORY FOREIGN KEY (icon_category_id) REFERENCES ICON_CATEGORY (icon_category_id)
);

CREATE TABLE harugom.MISSION (
	mission_id			integer			NOT NULL	AUTO_INCREMENT,
	routine_id			integer			NULL,
	mission_icon_id		integer			NOT NULL,
	mission_name		varchar(50)		NOT NULL,
	mission_order		integer			NULL,
	run_time			time			NOT NULL,
	mission_content		varchar(200)	NULL,
	CONSTRAINT PK_MISSION PRIMARY KEY (mission_id),
	CONSTRAINT FK_MISSION_ROUTINE FOREIGN KEY (routine_id) REFERENCES ROUTINE (routine_id),
	CONSTRAINT FK_MISSION_MISSION_ICON FOREIGN KEY (mission_icon_id) REFERENCES MISSION_ICON (mission_icon_id)
);



INSERT INTO harugom.MEMBER (BIRTH,EMAIL,GENDER,JOIN_DATE,role,NICKNAME,PWD)
    VALUES('1994-07-02', 'hirokazu@gmail.com', 'M', NOW(), 'ADMIN',
    'HIROKAZU', '{bcrypt}$2a$10$Ck98NMXjuxoBaJndWUUc3ufSns1PR4GOLmudp/Gaq519O3prScL5u'); -- PWD : hirokazu72
INSERT INTO harugom.MEMBER (BIRTH,EMAIL,GENDER,JOIN_DATE,role,NICKNAME)
    VALUES('1993-02-14', 'kristal@gmail.com', 'F', NOW(), 'ADMIN',
    'KRISTAL', '{bcrypt}$2a$10$7mOoW7aeU.qyt/5xxC.68eX43QPujsiC9.iN6KrKc7zFuULwr3Wwm'); -- PWD : kri111
INSERT INTO harugom.MEMBER (BIRTH,EMAIL,GENDER,JOIN_DATE,role,NICKNAME,PWD)
    VALUES('2000-10-07', 'gom@naver.com', 'M', NOW(), 'MEMBER',
    '곰돌이', '{bcrypt}$2a$10$sUALr9XA0GBHPpYsE8uZAu5sZp8oSaNa5yeLftkgiFEUgZAlwzwYe'); -- PWD : gom111
INSERT INTO harugom.MEMBER (BIRTH,EMAIL,GENDER,JOIN_DATE,role,NICKNAME,PWD)
    VALUES('1998-11-14', 'soo@naver.com', 'F', NOW(), 'MEMBER',
    '수정', '{bcrypt}$2a$10$DNHvNEBjyPG/2icIWHLDrudBbpn29wQ/osYN3A6haRUQxKZhHMd5i'); -- PWD : soo111

INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('반려동물', 'animal', FALSE); -- 1
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('클린', 'clean', FALSE); -- 2
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('커뮤니케이션', 'communication', FALSE); -- 3
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('요리', 'cook', FALSE); -- 4
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('전가기기', 'electronics', FALSE); -- 5
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('이모티콘', 'emoji', FALSE); -- 6
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('운동', 'exercise', FALSE); -- 7
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('음식', 'food', FALSE); -- 8
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('취미', 'hobby', FALSE); -- 9
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('집', 'home', FALSE); -- 10
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('의료', 'medical', FALSE); -- 11
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('아침', 'morning', FALSE); -- 12
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('장소', 'place', FALSE); -- 13
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('식물', 'plant', FALSE); -- 14
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('쇼핑', 'shopping', FALSE); -- 15
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('학습', 'study', FALSE); -- 16
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('교통', 'traffic', FALSE); -- 17
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('날씨', 'weather', FALSE); -- 18
INSERT INTO harugom.ICON_CATEGORY(category_name, icon_path, pay) VALUES('업무', 'work', FALSE); -- 19

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'BATH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'CAT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'FEEDER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'KOALA .png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'LOBSTER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'OCTOPUS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'PANDA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'PENGUIN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'PET FEEDER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'SHEEP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'SHRIMP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'TURTLE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(1, 'WHALE.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'BATH TUB.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'BATHUP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'CUT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'DELETE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'LAUNDRY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'LAUNDRY2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'RECYCLE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'SHOWER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'SHOWER HEAD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'SHOWER2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'TAP WATER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'TRASH BIN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'WASHING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'Washing Hand.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'Washing Hand Use Soap_1.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'Washing Hand Use Soap_2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'WATER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'WATER TAP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(2, 'WATER2.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'CONVERSATION.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'DIALOGUE BOX.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'EMAIL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'FEEDBACK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'INVITATION CARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'ORDER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(3, 'POSTBOX.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(4, 'COOKING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(4, 'COOKING POTS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(4, 'GRILL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(4, 'KITCHEN UTENSILS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(4, 'MICROWAVE OVEN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(4, 'OVEN.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'APPS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'BOOMBOX.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'CALCULATOR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'CALCULATOR2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'CALCULATOR3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'CHARGER2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'CHARGING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'ELECTRIC.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'MOBILE MESSAGE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'ONLINE BANKING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'PHONE CHARGER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'PROJECTOR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(5, 'VIDEO GAME.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, '24 HOURS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'BABY BOY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'BABY GIRL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'BULB.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'DIAMOND.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'FAVORITE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'HAPPY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'HEART.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'IDEA3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'PHONE CALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'RATING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'STAR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(6, 'TEDDY BEAR.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'AMERICAN FOOTBALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BADMINTON.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BASEBALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BASEBALL BALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BASEBALL2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BASKETBALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BEACH BALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BEACH VOLLEYBALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BICYCLE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BILLIARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BOATING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BOOMERANG.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BOWLING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'BOXING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'CANOE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'CROSSFIT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'CYCLING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'DART.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'DIVING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'DUMBBELL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'DUMBELL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'DUMBELL 2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'EXERCISE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'FOOTBALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'GOLF STICK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'GYM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'HOCKEY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'HOCKEY STICK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'ICE SKATE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'JETSKI.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'KAYAK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'MARATHON.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'PADDLEBOARDING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'PARACHUTE 2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'PING PONG.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'POOL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'ROLLERSKATE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'RUGBY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'RUNNING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SCUBBA DIVING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SKATEBOARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SNOOKER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SOCCER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SURFBOARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SWIMMING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SWIMMING POOL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'SWIMMING POOL2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'TENNIS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'TENNIS 2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'VOLLEY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(7, 'WINDSURFING.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'BEER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'BIRTHDAY CAKE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'BIRTHDAY CAKE2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CAKE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CAKE2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CANDY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CARROT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CHEERS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CHEESE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CHEESE BURGER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CHERRIES.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CHICKEEN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'COFFEE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'COFFEE2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CUP CAKE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'CUPCAKE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'DONUTS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'EGGPLANT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'FAST FOOD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'GLASS OF WATER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'GRAPES.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'ICE CREAM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'ICE CREAM CONE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'ICE CREAM2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'ICE CREAM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'PIZZA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'POPCORN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'SALAD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'SANDWICH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'TEA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(8, 'WEDDING CAKE.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'BRUSH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'CAMERA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'CANVAS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'CINEMA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'COLORED PENCILS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'CRAYONS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'DRAWING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'FISHING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'FRENCH HORN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'GUITAR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'GUITAR2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'GUITAR3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'HEADPHONE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'HEADPHONES2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'HEADSET.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'LEISURE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'MUSIC.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'PHOTOGRAPHY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'PLAYING CARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(9, 'SPRAY PAINT.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'ARMCHAIR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'DRILL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'FURNITURE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'HAMMER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'IRONING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'PLIERS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'SOFA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'STAY AT HOME.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(10, 'TELEVISION.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'FIRST AID KIT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'HOSPITAL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'HOSPITAL2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'HOSPITAL3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'INJECTION.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'INJURY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'INTRAVENOUS SALINE DRIP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'MEDICAL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'MEDICINE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'PHARMACY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'PHONENDOSCOPE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(11, 'PILLS.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'ALARM CLOCK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'BATH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'BIRD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'BOILED EGG.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'BREAKFAST.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'CALENDAR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'CEREAL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'CLEANING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'CLOTHES.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'COFFEE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'COFFEE MAKER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'COOKING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'COSMETICS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'DEODORANT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'DOG.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'DUMBBELLS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'EMAIL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'GET DRESSED.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'HAIR BRUSH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'HAIR COMB.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'IRONING BOARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'JOGGING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'LIST.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'MAKE UP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'MILK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'MIRROR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'MOISTURIZING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'NEWS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'NEWSPAPER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'PERFUME.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'RADIO.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'RAZOR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'SCHOOL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'SHOWER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'SOCIAL MEDIA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'SQUEEZER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'SWEEPING BROOM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'TOAST.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'TOOTHBRUSH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'TREADMILL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'TREADMILL_1.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'VACUUM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'VITAMIN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WAKE UP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WASH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WASHING MACHINE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WATERING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WEATHER FORECAST.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WINDOW.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(12, 'WORK.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'BANK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'BARBERSHOP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'BEACH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'COLLEGE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'CRUCH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'FERRIS WHEEL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'LAKE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'LOCATION.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'MAP3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'MUSEUM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'OCEAN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'OFFICE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'PARK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'RESTAURANT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'School.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(13, 'SLIDING.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'DIGGING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'FORK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'GARDENING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'SPRING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'SPROUT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'WATERING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'WATERING CAN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(14, 'WATERING CAN2.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'BOX.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'BOX3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'BUY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'CREDIT CARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'GIFT2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'MONEY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'MONEY WITHDRAWAL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'MONEY2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'PAYMENT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'PAYMENT2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'PIGGY BANK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'SHOP2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'SHOPPING BASKET.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'SHOPPING CART.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(15, 'WALLET.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'ART BOOK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'BAG.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'BLACKBOARD.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'BOOKS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'BOOKS2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'BOOKSHELF.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'CERTIFICATE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'DESK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'DESK LAMP2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'DESK2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'DESKTOP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'DRAWING2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'LIBRARY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'PENCIL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(16, 'SKETCHBOOK.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'AIRPLANE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'AIRPORT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'AMBULANCE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'BUS.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'BUS STOP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'BUS STOP2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'CAR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'CAR WASH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'DELIVERY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'DELIVERY TRUCK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'ELECTRIC CAR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'FAST DELIVERY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'FOOD DELIVERY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'FUEL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'FUEL PUMP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'GAS STATION.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'GAS STATION2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'GAS STATION3.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'GAS STATION4.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'MAP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'RAILWAY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'School Bus.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'SHARING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'SUBWAY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'TAXI.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'TRAIN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'TRAM.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(17, 'TRUCK.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(18, 'CLOUDY.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(18, 'MONSOON.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(18, 'RAIN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(18, 'RAINBOW.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(18, 'UMBRELLA.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(18, 'UMBRELLA3.png');

INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'BRIEFCASE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'CALENDAR2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'COMPUTER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'COMPUTER2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'DESK LAMP.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'EBOOK.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'EBOOK2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'FAX MACHINE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'HOME OFFICE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'LUGGAGE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'MONITOR.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'NOTEBOOK2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'PLAN.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'PRINTER.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'PROJECT.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'SEARCH.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'VIDEO CALL.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'VIDEO CONFERENCE.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'VIDEO CONFERENCE .png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'Webinar From Home.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'Work From Home.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'Work From Home_1.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'Work From Home_2.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'WORKING.png');
INSERT INTO harugom.MISSION_ICON(icon_category_id, icon_file_name) VALUES(19, 'WRITING.png');