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
	email			varchar(20)		NOT NULL UNIQUE,
	pwd				varchar(100)	NOT NULL,
	nickname		varchar(20)		NOT NULL,
	gender			varchar(10)		NOT NULL,
	birth			date			NOT NULL,
	role			varchar(20)		NOT NULL,
	join_date		datetime		DEFAULT NOW(),
	member_active   boolean         DEFAULT TRUE,
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



INSERT INTO harugom.MEMBER (BIRTH,EMAIL,GENDER,JOIN_DATE,role,NICKNAME,PWD,MEMBER_ACTIVE)
    VALUES('1994-07-02', 'hirokazu@gmail.com', 'M', NOW(), 'ADMIN', 'HIROKAZU', 'hirokazu7', TRUE);
INSERT INTO harugom.MEMBER (BIRTH,EMAIL,GENDER,JOIN_DATE,role,NICKNAME,PWD,MEMBER_ACTIVE)
    VALUES('1993-02-14', 'kristal@gmail.com', 'F', NOW(), 'ADMIN', 'KRISTAL', 'kristal1', TRUE);
INSERT INTO harugom.ICON_CATEGORY(CATEGORY_NAME, PAY) VALUES('logo', FALSE);
INSERT INTO harugom.MISSION_ICON(ICON_FILE_NAME, ICON_CATEGORY_ID) VALUES('favicon.png', 1);
