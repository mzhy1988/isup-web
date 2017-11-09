﻿--用户表
CREATE TABLE IUSP_COMMON_USER
( 
    ID    INT NOT NULL, 
    DLM   VARCHAR(30) NOT NULL, 
    DLMA  VARCHAR(60), 
    JSBS  VARCHAR(2), 
	CJSJ  TIMESTAMP, 
    CONSTRAINT COMMON_USER_ID_TRIGGER PRIMARY KEY (ID) 
); 

CREATE SEQUENCE IUSP_COMMON_USER_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_COMMON_USER_ID_TRIGGER BEFORE 
insert ON IUSP_COMMON_USER FOR EACH ROW 
begin 
select IUSP_COMMON_USER_ID_SEQ.nextval into:new.id from dual;
end; 

﻿--登录用户表
CREATE TABLE IUSP_COMMON_LOGIN_USER
( 
    ID    INT NOT NULL, 
    DLM   VARCHAR(30) NOT NULL, 
    XM 	  VARCHAR(60), 
    BMBM  VARCHAR(10), 
    CONSTRAINT COMMON_LOGIN_USER_TRIGGER PRIMARY KEY (ID) 
); 

CREATE SEQUENCE IUSP_COMMON_LOGIN_USER_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER COMMON_LOGIN_USER_TRIGGER BEFORE 
insert ON IUSP_COMMON_LOGIN_USER FOR EACH ROW 
begin 
select IUSP_COMMON_LOGIN_USER_SEQ.nextval into:new.id from dual;
end; 

--学生表
CREATE TABLE IUSP_COMMON_STUDENT (
  ID int NOT NULL,
  XH varchar(10),
  KSH varchar(14),
  XM varchar(60),
  XMJC varchar(8),
  SFZH varchar(20),
  XBM varchar(1),
  CSRQ varchar(8),
  JGM varchar(6),
  MZM varchar(2),
  XZZ varchar(60),
  LXDH varchar(30),
  DZXX varchar(30),
  XZ varchar(10),
  RXNY varchar(10),
  NJ varchar(4),
  BH varchar(10),
  CONSTRAINT IUSP_COMMON_STUDENT_ID_TRIGGER PRIMARY KEY  (ID)
);

CREATE SEQUENCE IUSP_COMMON_STUDENT_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_COMMON_STUDENT_ID_TRIGGER BEFORE 
insert ON IUSP_COMMON_STUDENT FOR EACH ROW 
begin 
select IUSP_COMMON_STUDENT_ID_SEQ.nextval into:new.id from dual;
end; 

--教师表
CREATE TABLE IUSP_COMMON_TEACHER (
  ID int NOT NULL,
  ZGH varchar(10),
  XM varchar(60),
  XMPY varchar(60),
  SFZH varchar(18),
  CSRQ varchar(8),
  XBM varchar(1),
  MZM varchar(2),
  HYZKM varchar(1),
  JGM varchar(6),
  JTZZ varchar(100),
  XZZ varchar(100),
  LXDH varchar(30),
  DZXX varchar(30),
  CONSTRAINT IUSP_COMMON_TEACHER_ID_TRIGGER PRIMARY KEY  (ID)
);

CREATE SEQUENCE IUSP_COMMON_TEACHER_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_COMMON_TEACHER_ID_TRIGGER BEFORE 
insert ON IUSP_COMMON_TEACHER FOR EACH ROW 
begin 
select IUSP_COMMON_TEACHER_ID_SEQ.nextval into:new.id from dual;
end; 



--组织表
CREATE TABLE IUSP_COMMON_ORG (
  ID int NOT NULL,
  BMBM varchar(2),
  BMMC varchar(60),
  BMMS varchar(200),
  FJBMBM varchar(2),
  ZZLX varchar(2),
  CONSTRAINT IUSP_COMMON_ORG_ID_TRIGGER PRIMARY KEY  (ID)
);

CREATE SEQUENCE IUSP_COMMON_ORG_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_COMMON_ORG_ID_TRIGGER BEFORE 
insert ON IUSP_COMMON_ORG FOR EACH ROW 
begin 
select IUSP_COMMON_ORG_ID_SEQ.nextval into:new.id from dual;
end; 

--教师组织表
CREATE TABLE IUSP_COMMON_TEACHER_ORG (
  ID int NOT NULL,
  ZZBM varchar(8),
  ZGH varchar(10),
  GWBM varchar(3),
  GWMC varchar(60),
  SFFZR varchar(2),
  CONSTRAINT IUSP_COMMON_ORG_ID_TRIGGER PRIMARY KEY  (ID)
)

CREATE SEQUENCE IUSP_COMMON_T_ORG_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_COMMON_T_ORG_ID_TRIGGER BEFORE 
insert ON IUSP_COMMON_TEACHER_ORG FOR EACH ROW 
begin 
select IUSP_COMMON_T_ORG_ID_SEQ.nextval into:new.id from dual;
end; 

--校领导分管组织表
CREATE TABLE IUSP_COMMON_PRESIDENT_ORG (
  ZGH varchar(20),
  BMBM varchar(10)
);

-- 日志表
CREATE TABLE IUSP_WORKING_LOG (
  ID int NOT NULL,
  RZZT varchar(2),
  RZLX varchar(2),
  CGZNR varchar2(2000),
  NGZJH varchar2(2000),
  WTHJY varchar2(2000),
  TXRZGH varchar(20),
  TXRXM varchar(60),
  RZSJ varchar(20),
  NF varchar(10),
  XQ varchar(10),
  DJZ varchar(10),
  TXSJ TIMESTAMP,
  SPRZGH varchar(20),
  SPRXM varchar(20),
  SPSJ varchar(10),
  PY varchar(500),
  PF varchar(20),
  THYY varchar(500),
  CONSTRAINT IUSP_WORKING_LOG_ID_TRIGGER PRIMARY KEY (ID)
) ;

CREATE SEQUENCE IUSP_WORKING_LOG_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_WORKING_LOG_ID_TRIGGER BEFORE 
insert ON IUSP_WORKING_LOG FOR EACH ROW 
begin 
select IUSP_WORKING_LOG_ID_SEQ.nextval into:new.id from dual;
end; 


--工作日志配置表
CREATE TABLE IUSP_WORKING_LOG_CONFIG (
  ID int NOT NULL,
  SFZDSP varchar(2),
  MRPF varchar(2),
  MRPJ varchar(100),
  ZGH varchar(10) NOT NULL,
   CONSTRAINT IUSP_WORKING_LOG_C_ID_TRIGGER PRIMARY KEY  (ID)
);

CREATE SEQUENCE IUSP_WORKING_LOG_CONFIG_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_WORKING_LOG_C_ID_TRIGGER BEFORE 
insert ON IUSP_WORKING_LOG_CONFIG FOR EACH ROW 
begin 
select IUSP_WORKING_LOG_CONFIG_ID_SEQ.nextval into:new.id from dual;
end;

--新闻文章表
CREATE TABLE IUSP_COMMON_ARTICLE_INFO (
  ID int NOT NULL,
  BT varchar2(200),
  ZY varchar2(1000),
  NR varchar2(4000),
  CJR varchar(20),
  LX varchar(2),
  CJSJ TIMESTAMP,
  CONSTRAINT IUSP_ARTICLE_INFO_ID_TRIGGER PRIMARY KEY  (ID)
);

CREATE SEQUENCE IUSP_ARTICLE_INFO_ID_SEQ
INCREMENT BY 1    
START WITH 1   
NOMAXVALUE       
NOCYCLE 
CACHE 10; 

CREATE TRIGGER IUSP_ARTICLE_INFO_ID_TRIGGER BEFORE 
insert ON IUSP_COMMON_ARTICLE_INFO FOR EACH ROW 
begin 
select IUSP_ARTICLE_INFO_ID_SEQ.nextval into:new.id from dual;
end;