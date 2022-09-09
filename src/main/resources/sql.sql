DROP TABLE IF EXISTS `mission`;
DROP TABLE IF EXISTS `mission_icon`;
DROP TABLE IF EXISTS `icon_category`;
DROP TABLE IF EXISTS `routine_set`;
DROP TABLE IF EXISTS `ROUTINE`;
DROP TABLE IF EXISTS `REPLY`;
DROP TABLE IF EXISTS `board_image`;
DROP TABLE IF EXISTS `BOARD`;
DROP TABLE IF EXISTS `MEMBER`;

CREATE TABLE `MEMBER` (
	`MEMBERNUM`	INTEGER	NOT NULL,
	`ID`	VARCHAR(20)	NOT NULL,
	`PWD`	VARCHAR(20)	NOT NULL,
	`NICKNAME`	VARCHAR(20)	NOT NULL,
	`GENDER`	VARCHAR(10)	NOT NULL,
	`BIRTH`	DATE	NOT NULL,
	`GRADE`	VARCHAR(20)	NOT NULL,
	`JOINDATE`	DATETIME	NOT NULL,
	`LASTDATE`	DATETIME	NOT NULL,
	CONSTRAINT `PK_MEMBER` PRIMARY KEY (`MEMBERNUM`)
);

CREATE TABLE `BOARD` (
	`BOARDNUM`	INTEGER	NOT NULL,
	`MEMBERNUM`	INTEGER	NOT NULL,
	`CATEGORYNAME`	VARCHAR(20)	NOT NULL,
	`BOARDTITLE`	VARCHAR(200)	NOT NULL,
	`BOARDCONTENT`	VARCHAR(2048)	NOT NULL,
	`BOARDCREATE`	DATETIME	NOT NULL,
	`BOARDMODIFY`	DATETIME    NULL,
	`BOARDHITS`	INTEGER	NOT NULL,
	CONSTRAINT `PK_BOARD` PRIMARY KEY (`BOARDNUM`),
	CONSTRAINT `FK_MEMBER_TO_BOARD_1` FOREIGN KEY (`MEMBERNUM`) REFERENCES `MEMBER` (`MEMBERNUM`)
);

CREATE TABLE `BOARDIMAGE` (
	`IMAGENUM`	INTEGER	NOT NULL,
	`BOARDNUM`	INTEGER	NOT NULL,
	`IMAGEFILENAME`	VARCHAR(50)	NOT NULL,
	CONSTRAINT `PK_BOARDIMAGE` PRIMARY KEY (`IMAGENUM`),
	CONSTRAINT `FK_BOARD_TO_BOARDIMAGE_1` FOREIGN KEY (`BOARDNUM`) REFERENCES `BOARD` (`BOARDNUM`)
);

CREATE TABLE `REPLY` (
	`REPLYNUM`	INTEGER	NOT NULL,
	`MEMBERNUM`	INTEGER	NOT NULL,
	`BOARDNUM`	INTEGER	NOT NULL,
	`REPLYCONTENT`	VARCHAR(1024)	NOT NULL,
	`REPLYCREATE`	DATETIME	NOT NULL,
	CONSTRAINT `PK_REPLY` PRIMARY KEY (`REPLYNUM`),
	CONSTRAINT `FK_MEMBER_TO_REPLY_1` FOREIGN KEY (`MEMBERNUM`) REFERENCES `MEMBER` (`MEMBERNUM`),
	CONSTRAINT `FK_BOARD_TO_REPLY_1` FOREIGN KEY (`BOARDNUM`) REFERENCES `BOARD` (`BOARDNUM`)
);

CREATE TABLE `ROUTINE` (
	`ROUTINENUM`	INTEGER	NOT NULL,
	`MEMBERNUM`	INTEGER	NOT NULL,
	`ROUTINENAME`	VARCHAR(50)	NOT NULL,
	`ACTIVE`	BOOLEAN	NOT NULL,
	`TOTALTIME`	TIME	NOT NULL,
	 CONSTRAINT `PK_ROUTINE` PRIMARY KEY (`ROUTINENUM`),
	CONSTRAINT `FK_MEMBER_TO_ROUTINE_1` FOREIGN KEY (`MEMBERNUM`) REFERENCES `MEMBER` (`MEMBERNUM`)
);

CREATE TABLE `ROUTINESET` (
	`WEEK`	INTEGER	NOT NULL,
	`ROUTINENUM`	INTEGER	NOT NULL,
	`STARTTIME`	TIME	NOT NULL,
	CONSTRAINT `PK_ROUTINESET` PRIMARY KEY (`WEEK`,`ROUTINENUM`),
	CONSTRAINT `FK_ROUTINE_TO_ROUTINESET_1` FOREIGN KEY (`ROUTINENUM`) REFERENCES `ROUTINE` (`ROUTINENUM`)
);

CREATE TABLE `ICONCATEGORY` (
	`CATEGORYNUM`	INTEGER	NULL,
	`CATEGORYNAME`	VARCHAR(50)	NOT NULL,
	`PAY`	BOOLEAN	NOT NULL,
	CONSTRAINT `PK_ICONCATEGORY` PRIMARY KEY (`CATEGORYNUM`)
);

CREATE TABLE `MISSIONICON` (
	`ICONNUM`	INTEGER	NOT NULL,
	`CATEGORYNUM`	INTEGER	NULL,
	`ICONFILENAME`	VARCHAR(50)	NOT NULL,
 	CONSTRAINT `PK_MISSIONICON` PRIMARY KEY (`ICONNUM`),
	 CONSTRAINT `FK_ICONCATEGORY_TO_MISSIONICON_1` FOREIGN KEY (`CATEGORYNUM`) REFERENCES `ICONCATEGORY` (`CATEGORYNUM`)
);

CREATE TABLE `MISSION` (
	`MISSIONNUM`	INTEGER	NOT NULL,
	`ROUTINENUM`	INTEGER	NOT NULL,
	`ICONNUM`	INTEGER	NOT NULL,
	`MISSIONNAME`	VARCHAR(50)	NOT NULL,
	`MISSIONORDER`	INTEGER	NOT NULL,
	`RUNTIME`	TIME	NOT NULL,
	`MISSIONCONTENT`	VARCHAR(200)	NOT NULL,
	 CONSTRAINT `PK_MISSION` PRIMARY KEY (`MISSIONNUM`),
	CONSTRAINT `FK_ROUTINE_TO_MISSION_1` FOREIGN KEY (`ROUTINENUM`) REFERENCES `ROUTINE` (`ROUTINENUM`),
	CONSTRAINT `FK_MISSIONICON_TO_MISSION_1` FOREIGN KEY (`ICONNUM`) REFERENCES `MISSIONICON` (`ICONNUM`)
);

