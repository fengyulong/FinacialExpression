create table SYS_USER
(
  id            VARCHAR2(36) not null,
  user_code     VARCHAR2(60),
  user_name     VARCHAR2(60),
  user_password VARCHAR2(60),
  is_disable    CHAR(1)
);


create table DIC_DATASOURCE
(
  id            CHAR(36) not null,
  code          VARCHAR2(60) not null,
  name          VARCHAR2(60) not null,
  soft_version  VARCHAR2(60) not null,
  db_type       VARCHAR2(10) not null,
  instance      VARCHAR2(60) not null,
  host          VARCHAR2(120) not null,
  port          VARCHAR2(10) not null,
  user_name     VARCHAR2(60) not null,
  user_password VARCHAR2(60) not null
);