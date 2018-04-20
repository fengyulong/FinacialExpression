create table SYS_USER
(
  id            VARCHAR(36) not null,
  user_code     VARCHAR(60),
  user_name     VARCHAR(60),
  user_password VARCHAR(60),
  is_disable    CHAR(1)
);


create table DIC_DATASOURCE
(
  id            CHAR(36) not null,
  code          VARCHAR(60) not null,
  name          VARCHAR(60) not null,
  soft_version  VARCHAR(60) not null,
  db_type       VARCHAR(10) not null,
  instance      VARCHAR(60) not null,
  host          VARCHAR(120) not null,
  port          VARCHAR(10) not null,
  user_name     VARCHAR(60) not null,
  user_password VARCHAR(60) not null
);