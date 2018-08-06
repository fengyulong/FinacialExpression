prompt PL/SQL Developer import file
prompt Created on 2018年8月6日 by admin
set feedback off
set define off
prompt Creating DIC_DATASOURCE...
create table DIC_DATASOURCE
(
  code          VARCHAR2(60) not null,
  name          VARCHAR2(60) not null,
  soft_version  VARCHAR2(60) not null,
  db_type       VARCHAR2(10) not null,
  instance      VARCHAR2(60) not null,
  host          VARCHAR2(120) not null,
  port          NUMBER not null,
  user_name     VARCHAR2(60) not null,
  user_password VARCHAR2(60) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column DIC_DATASOURCE.code
  is '代码';
comment on column DIC_DATASOURCE.name
  is '名称';
comment on column DIC_DATASOURCE.soft_version
  is '软件版本';
comment on column DIC_DATASOURCE.db_type
  is '数据库类型';
comment on column DIC_DATASOURCE.instance
  is '实例名称';
comment on column DIC_DATASOURCE.host
  is '服务地址';
comment on column DIC_DATASOURCE.port
  is '端口号';
comment on column DIC_DATASOURCE.user_name
  is '用户名';
comment on column DIC_DATASOURCE.user_password
  is '密码';
alter table DIC_DATASOURCE
  add constraint PK_DIC_DATASOURCE primary key (CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating DIC_ORG_MAPPING...
create table DIC_ORG_MAPPING
(
  code            VARCHAR2(60) not null,
  name            VARCHAR2(60),
  remark          VARCHAR2(200),
  datasource_code VARCHAR2(60),
  parent_code     VARCHAR2(60),
  rep_code        VARCHAR2(60),
  rep_name        VARCHAR2(60),
  book_code       VARCHAR2(60)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column DIC_ORG_MAPPING.code
  is '核算机构代码';
comment on column DIC_ORG_MAPPING.name
  is '核算机构名称';
comment on column DIC_ORG_MAPPING.remark
  is '备注';
comment on column DIC_ORG_MAPPING.datasource_code
  is '数据源';
comment on column DIC_ORG_MAPPING.parent_code
  is '父节点';
comment on column DIC_ORG_MAPPING.rep_code
  is '报表机构代码';
comment on column DIC_ORG_MAPPING.rep_name
  is '报表机构名称';
comment on column DIC_ORG_MAPPING.book_code
  is '账簿';
alter table DIC_ORG_MAPPING
  add constraint PK_DIC_ORG_MAPPING primary key (CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_DICT...
create table SYS_DICT
(
  id        VARCHAR2(60) not null,
  text      VARCHAR2(60),
  parent_id VARCHAR2(32),
  remark    VARCHAR2(200),
  is_leaf   CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_DICT
  add constraint PK_SYS_DICT primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_DICT_ITEM...
create table SYS_DICT_ITEM
(
  dict_id VARCHAR2(60) not null,
  value   VARCHAR2(60) not null,
  text    VARCHAR2(60),
  remark  VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_DICT_ITEM
  add constraint PK_SYS_DICT_ITEM primary key (DICT_ID, VALUE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_PERMISSION...
create table SYS_PERMISSION
(
  id        VARCHAR2(32) not null,
  parent_id VARCHAR2(32),
  name      VARCHAR2(32) not null,
  type      VARCHAR2(20),
  sort      NUMBER,
  url       VARCHAR2(60),
  code      VARCHAR2(60),
  icon      VARCHAR2(60),
  remark    VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_PERMISSION.name
  is '名称';
comment on column SYS_PERMISSION.type
  is '类型';
comment on column SYS_PERMISSION.sort
  is '排序';
comment on column SYS_PERMISSION.url
  is '资源路径';
comment on column SYS_PERMISSION.code
  is '按钮权限码';
comment on column SYS_PERMISSION.icon
  is '图标';
comment on column SYS_PERMISSION.remark
  is '备注';
alter table SYS_PERMISSION
  add constraint PK_SYS_PERMISSION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_PERMISSION
  add constraint UK_SYS_PERMISSION unique (CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_ROLE...
create table SYS_ROLE
(
  id     VARCHAR2(32) not null,
  code   VARCHAR2(60),
  name   VARCHAR2(60),
  remark VARCHAR2(200),
  sort   NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_ROLE.code
  is '角色代码';
comment on column SYS_ROLE.name
  is '角色名称';
comment on column SYS_ROLE.remark
  is '备注';
comment on column SYS_ROLE.sort
  is '排序';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE
  add constraint UK_SYS_ROLE unique (CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_ROLE_PERMISSION...
create table SYS_ROLE_PERMISSION
(
  id            VARCHAR2(32) not null,
  role_id       VARCHAR2(32),
  permission_id VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_ROLE_PERMISSION.role_id
  is '角色id';
comment on column SYS_ROLE_PERMISSION.permission_id
  is '权限id';
alter table SYS_ROLE_PERMISSION
  add constraint PK_SYS_ROLE_PERMISSION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE_PERMISSION
  add constraint UK_SYS_ROLE_PERMISSION unique (ROLE_ID, PERMISSION_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_USER...
create table SYS_USER
(
  id          VARCHAR2(32) not null,
  username    VARCHAR2(60) not null,
  password    VARCHAR2(60),
  nickname    VARCHAR2(60),
  status      CHAR(1),
  sex         CHAR(1),
  email       VARCHAR2(60),
  mobile      VARCHAR2(11),
  create_user VARCHAR2(32),
  create_date DATE,
  modify_user VARCHAR2(32),
  modify_date DATE,
  theme       VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_USER.id
  is '主键';
comment on column SYS_USER.username
  is '用户名';
comment on column SYS_USER.password
  is '密码';
comment on column SYS_USER.nickname
  is '昵称';
comment on column SYS_USER.status
  is '是否禁用';
comment on column SYS_USER.sex
  is '是否锁定';
comment on column SYS_USER.email
  is '邮箱';
comment on column SYS_USER.mobile
  is '手机号';
comment on column SYS_USER.create_user
  is '创建人';
comment on column SYS_USER.create_date
  is '创建日期';
comment on column SYS_USER.modify_user
  is '修改人';
comment on column SYS_USER.modify_date
  is '修改日期';
comment on column SYS_USER.theme
  is '皮肤';
alter table SYS_USER
  add constraint PK_SYS_USER primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USER
  add constraint UK_SYS_USER unique (USERNAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_USER_ROLE...
create table SYS_USER_ROLE
(
  id      VARCHAR2(32) not null,
  user_id VARCHAR2(32),
  role_id VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_USER_ROLE.user_id
  is '用户id';
comment on column SYS_USER_ROLE.role_id
  is '角色id';
alter table SYS_USER_ROLE
  add constraint PK_SYS_USER_ROLE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USER_ROLE
  add constraint UK_SYS_USER_ROLE unique (USER_ID, ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for DIC_DATASOURCE...
alter table DIC_DATASOURCE disable all triggers;
prompt Disabling triggers for DIC_ORG_MAPPING...
alter table DIC_ORG_MAPPING disable all triggers;
prompt Disabling triggers for SYS_DICT...
alter table SYS_DICT disable all triggers;
prompt Disabling triggers for SYS_DICT_ITEM...
alter table SYS_DICT_ITEM disable all triggers;
prompt Disabling triggers for SYS_PERMISSION...
alter table SYS_PERMISSION disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_ROLE_PERMISSION...
alter table SYS_ROLE_PERMISSION disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling triggers for SYS_USER_ROLE...
alter table SYS_USER_ROLE disable all triggers;
prompt Loading DIC_DATASOURCE...
insert into DIC_DATASOURCE (code, name, soft_version, db_type, instance, host, port, user_name, user_password)
values ('1', '1', 'EBS', 'Oracle', 'orcl', '127.0.0.1', 1521, 'expression', 'orcl');
insert into DIC_DATASOURCE (code, name, soft_version, db_type, instance, host, port, user_name, user_password)
values ('sjjs_b', 'sjjs_b', 'TEST', 'Oracle', 'orcl', '127.0.0.1', 1521, 'sjjs_b', 'orcl');
commit;
prompt 2 records loaded
prompt Loading DIC_ORG_MAPPING...
insert into DIC_ORG_MAPPING (code, name, remark, datasource_code, parent_code, rep_code, rep_name, book_code)
values ('10101', '测试单位', null, 'sjjs_b', 'ROOT', '#601002160', '测试报表单位', null);
commit;
prompt 1 records loaded
prompt Loading SYS_DICT...
insert into SYS_DICT (id, text, parent_id, remark, is_leaf)
values ('ROOT', '字典类型', null, '根节点', null);
insert into SYS_DICT (id, text, parent_id, remark, is_leaf)
values ('eee', 'eee', 'ROOT', null, null);
insert into SYS_DICT (id, text, parent_id, remark, is_leaf)
values ('SYS_PERMISSION_TYPE', '权限类型', 'SYS', null, null);
commit;
prompt 3 records loaded
prompt Loading SYS_DICT_ITEM...
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('eee', 'ee', 'ee', 'eee');
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('SYS_PERMISSION_TYPE', 'MENU_GROUP', '菜单分组', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('SYS_PERMISSION_TYPE', 'MENU', '菜单', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('SYS_PERMISSION_TYPE', 'BUTTON', '按钮操作', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('USER_SEX', 'F', '女', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('USER_STATE', 'lock', '锁定', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('USER_STATE', 'disable', '禁用', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('USER_SEX', 'M', '男', null);
insert into SYS_DICT_ITEM (dict_id, value, text, remark)
values ('USER_STATE', 'enable', '可用', null);
commit;
prompt 9 records loaded
prompt Loading SYS_PERMISSION...
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('4bbd482859ce44feb5eb4cb63eaf7e41', '136e13108c174e23b8269ee16c9f5e9c', '删除', 'BUTTON', 3, null, 'sys:user:delete', 'icon-remove', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('489fd03662db4e798a259040b0fb490d', '136e13108c174e23b8269ee16c9f5e9c', '角色', 'BUTTON', 4, null, 'sys:user:role', 'icon-role', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('f31a651677de4585aed6289b9c8cec12', 'd281f1ec1ae6414a8d5b3dc32ce9af0b', '新增', 'BUTTON', 1, null, 'sys:dict:add', 'icon-add', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('3aa9b1557a864a23abb167e179761f7d', 'd281f1ec1ae6414a8d5b3dc32ce9af0b', '编辑', 'BUTTON', 2, null, 'sys:dict:edit', 'icon-edit', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('50c5ac8f44164028b70982cfc2c147a8', 'd281f1ec1ae6414a8d5b3dc32ce9af0b', '删除', 'BUTTON', 3, null, 'sys:dict:delete', 'icon-remove', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('dc79c03eeab2444f81523bdbd5e1aa21', 'a223fb611f0e4775b37841bbf627e6bc', '新增', 'BUTTON', 1, null, 'sys:permission:add', 'icon-add', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('feca7f8878924b959fc1dd8bbfd1e852', 'a223fb611f0e4775b37841bbf627e6bc', '编辑', 'BUTTON', 2, null, 'sys:permission:edit', 'icon-edit', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('828143347e854ce48a499b360e5c52d5', 'a223fb611f0e4775b37841bbf627e6bc', '删除', 'BUTTON', 3, null, 'sys:permission:delete', 'icon-remove', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('f4e0ed8bfec54b94b9baae4401391bec', 'e3d183dfe1ee4deaaead69ca95f3eae7', '新增', 'BUTTON', 1, null, 'sys:role:add', 'icon-add', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('271c7545e62b425b98d9ddea4bcf1729', 'e3d183dfe1ee4deaaead69ca95f3eae7', '编辑', 'BUTTON', 2, null, 'sys:role:edit', 'icon-edit', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('dc9dcd6d67524f0b8673c995572b4e81', 'e3d183dfe1ee4deaaead69ca95f3eae7', '删除', 'BUTTON', 3, null, 'sys:role:delete', 'icon-remove', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('e28616e07d424544abb5c76818a5f786', 'e3d183dfe1ee4deaaead69ca95f3eae7', '权限', 'BUTTON', 4, null, 'sys:role:permission', 'icon-permission', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('136e13108c174e23b8269ee16c9f5e9c', 'bbf58021ee87473b833a1ae2b101cf6b', '用户管理', 'MENU', null, '/sys/user/list', 'sys:user:list', 'icon-user', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('e3d183dfe1ee4deaaead69ca95f3eae7', 'bbf58021ee87473b833a1ae2b101cf6b', '角色管理', 'MENU', null, '/sys/role/list', 'sys:role:list', 'icon-role', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('a223fb611f0e4775b37841bbf627e6bc', 'bbf58021ee87473b833a1ae2b101cf6b', '菜单权限', 'MENU', null, '/sys/permission/list', 'sys:permission:list', 'icon-menu', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('d281f1ec1ae6414a8d5b3dc32ce9af0b', 'bbf58021ee87473b833a1ae2b101cf6b', '字典管理', 'MENU', null, '/sys/dict/list', 'sys:dict:list', 'icon-book', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('e8b8437ed7cb468fb0c7aeeff5ba084b', null, '取数接口', 'GROUP', null, null, 'datafetch:group', 'icon-interfase', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('e0df1659e4b24772af46cee7f41db66c', 'e8b8437ed7cb468fb0c7aeeff5ba084b', '数据源管理', 'MENU', 1, '/datafetch/datasource/list', 'datafetch:datasource:list', 'icon-database', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('261cac8beaeb4154beda43c49a5a785b', 'e0df1659e4b24772af46cee7f41db66c', '新增', 'BUTTON', 1, null, 'datafetch:datasource:add', 'icon-add', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('0255fa5272f24475a9b30a435e24e3c9', 'e0df1659e4b24772af46cee7f41db66c', '编辑', 'BUTTON', 2, null, 'datafetch:datasource:edit', 'icon-edit', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('8a78bc67268848288d9ad3a71dfc64ce', 'e0df1659e4b24772af46cee7f41db66c', '删除', 'BUTTON', 3, null, 'datafetch:datasource:delete', 'icon-remove', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('b2d122d1bad1447eaeb439ee5f2a63fa', 'e8b8437ed7cb468fb0c7aeeff5ba084b', '数据源监控', 'MENU', null, '/druid/', 'datafetch:druid', 'icon-application', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('bbf58021ee87473b833a1ae2b101cf6b', null, '系统设置', 'GROUP', null, null, 'sys:group', 'icon-cog', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('af60fde0ca5641678cdabcac91309dfd', '136e13108c174e23b8269ee16c9f5e9c', '新增', 'BUTTON', 1, null, 'sys:user:add', 'icon-add', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('782aab50a85b488db2b2448111784900', '136e13108c174e23b8269ee16c9f5e9c', '编辑', 'BUTTON', 2, null, 'sys:user:edit', 'icon-edit', null);
insert into SYS_PERMISSION (id, parent_id, name, type, sort, url, code, icon, remark)
values ('23e868f8a12346cd99a40f7fa3f1d623', 'e8b8437ed7cb468fb0c7aeeff5ba084b', '机构映射', 'MENU', 2, '/datafetch/orgmapping/list', 'datafetch:orgmapping:list', 'icon-application', null);
commit;
prompt 26 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (id, code, name, remark, sort)
values ('e80fd416fe3a47b98c24cebb6f7780e3', 'admin', '管理员', '管理员', 6);
insert into SYS_ROLE (id, code, name, remark, sort)
values ('a90c7eec49024cec9443679b35b40dbc', 'superAdmin', '超级管理员', null, 1);
insert into SYS_ROLE (id, code, name, remark, sort)
values ('e0e81169a8fb48e8b1e9cc54f29d1587', 'guest', '客户', null, null);
commit;
prompt 3 records loaded
prompt Loading SYS_ROLE_PERMISSION...
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('c8fbc062dd6147deb58cdbfd69b3e50c', 'a90c7eec49024cec9443679b35b40dbc', 'af60fde0ca5641678cdabcac91309dfd');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('44e88ed3373e4753a67ed44bb31efc67', 'a90c7eec49024cec9443679b35b40dbc', '782aab50a85b488db2b2448111784900');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('54cdc2f689874c32998a46e5950a398b', 'a90c7eec49024cec9443679b35b40dbc', '4bbd482859ce44feb5eb4cb63eaf7e41');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('b1ab3314f1c44de891509ae1840c3f73', 'a90c7eec49024cec9443679b35b40dbc', '489fd03662db4e798a259040b0fb490d');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('4897ad8dbcd14c949aae910fecb42ca4', 'a90c7eec49024cec9443679b35b40dbc', 'd281f1ec1ae6414a8d5b3dc32ce9af0b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('b1a24e1a5eca4c46ba86b4c2514dd85e', 'a90c7eec49024cec9443679b35b40dbc', 'f31a651677de4585aed6289b9c8cec12');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('d3925cf702af4fb68d131074d305ead1', 'a90c7eec49024cec9443679b35b40dbc', '3aa9b1557a864a23abb167e179761f7d');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('16a15af081c64fd1897ea19e4f047f85', 'a90c7eec49024cec9443679b35b40dbc', '50c5ac8f44164028b70982cfc2c147a8');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('16939d6855444aaba8f3537b36da3115', 'a90c7eec49024cec9443679b35b40dbc', 'a223fb611f0e4775b37841bbf627e6bc');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('36b2a160044345018d99dab2c6b92efe', 'a90c7eec49024cec9443679b35b40dbc', 'dc79c03eeab2444f81523bdbd5e1aa21');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('3080aea62ff74a99bba694949c2c5b96', 'a90c7eec49024cec9443679b35b40dbc', 'feca7f8878924b959fc1dd8bbfd1e852');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('c1db327014654de99869a5c15c9aefc4', 'a90c7eec49024cec9443679b35b40dbc', '828143347e854ce48a499b360e5c52d5');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('3bcaa706ce774067b1110bebb8f3d01b', 'a90c7eec49024cec9443679b35b40dbc', 'e3d183dfe1ee4deaaead69ca95f3eae7');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('5bcf4df680664b9db4abc82fe4122d44', 'a90c7eec49024cec9443679b35b40dbc', 'f4e0ed8bfec54b94b9baae4401391bec');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('601ddcb087264f1880022322ea402697', 'a90c7eec49024cec9443679b35b40dbc', '271c7545e62b425b98d9ddea4bcf1729');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('d6ef0c44a84e419e93204de9f70d9354', 'a90c7eec49024cec9443679b35b40dbc', 'dc9dcd6d67524f0b8673c995572b4e81');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('d0326a96eefc47108cf6f05b7e3b8a31', 'a90c7eec49024cec9443679b35b40dbc', 'e28616e07d424544abb5c76818a5f786');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('facd7e4085a04d40a5df6be3a337353b', 'a90c7eec49024cec9443679b35b40dbc', 'e8b8437ed7cb468fb0c7aeeff5ba084b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('f82f8f8d0c254919bea04dd38f04c470', 'a90c7eec49024cec9443679b35b40dbc', 'e0df1659e4b24772af46cee7f41db66c');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('7144d32f64a14f78af7fbe33f99d1fca', 'a90c7eec49024cec9443679b35b40dbc', '261cac8beaeb4154beda43c49a5a785b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('86df4e2319df4f32a55e47ebd5b33144', 'a90c7eec49024cec9443679b35b40dbc', '0255fa5272f24475a9b30a435e24e3c9');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('d80c2bd1d1394fe8835c913c41e082f8', 'a90c7eec49024cec9443679b35b40dbc', '8a78bc67268848288d9ad3a71dfc64ce');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('d718162a8f9945fab7352f142dea80b6', 'a90c7eec49024cec9443679b35b40dbc', '23e868f8a12346cd99a40f7fa3f1d623');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('57428e1e6bf74cb1959cce3b087cc9d6', 'a90c7eec49024cec9443679b35b40dbc', 'b2d122d1bad1447eaeb439ee5f2a63fa');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('ab1e83f8f3ec4efaa0554c2bc768db5c', 'a90c7eec49024cec9443679b35b40dbc', 'bbf58021ee87473b833a1ae2b101cf6b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('2d1afbd259eb47bda1b98ee0f938d69b', 'a90c7eec49024cec9443679b35b40dbc', '136e13108c174e23b8269ee16c9f5e9c');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('920cec8d46cd45d3a239972d457b8c68', 'e0e81169a8fb48e8b1e9cc54f29d1587', 'bbf58021ee87473b833a1ae2b101cf6b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('1eafb8e821214766baac47b47d47c556', 'e0e81169a8fb48e8b1e9cc54f29d1587', '136e13108c174e23b8269ee16c9f5e9c');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('3b48a589750b4e7086810e233cf3c16b', 'e0e81169a8fb48e8b1e9cc54f29d1587', 'e3d183dfe1ee4deaaead69ca95f3eae7');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('d267c4924a4248078fc2ff71e8488b4d', 'e80fd416fe3a47b98c24cebb6f7780e3', 'bbf58021ee87473b833a1ae2b101cf6b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('f18e3a943eb64224b4209b88e30b9adc', 'e80fd416fe3a47b98c24cebb6f7780e3', '136e13108c174e23b8269ee16c9f5e9c');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('b1d4a545473f48c0ba182f35d3b36124', 'e80fd416fe3a47b98c24cebb6f7780e3', 'd281f1ec1ae6414a8d5b3dc32ce9af0b');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('fab8c0dc5c534bd6b8d245feb517beee', 'e80fd416fe3a47b98c24cebb6f7780e3', 'a223fb611f0e4775b37841bbf627e6bc');
insert into SYS_ROLE_PERMISSION (id, role_id, permission_id)
values ('9cd2023dbe6942ec9215030f134c1b9f', 'e80fd416fe3a47b98c24cebb6f7780e3', 'e3d183dfe1ee4deaaead69ca95f3eae7');
commit;
prompt 34 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('da0f985d4b43454488d180dfd5841f98', 'guest', 'b7adeb40e8174f3564f3fc3f995c1f96', '客户', 'E', 'M', 'guest@136.com', '15632563652', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('60b21dbed01942d2a22c609bde74b5b0', '12', '15561fefee5841d11b46015dc399c178', '12', 'E', 'M', '12@12.c', '132', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('21356136546', 'admin', '9fec80fbcee7632ce78d1586332b299e', '管理员', 'E', 'M', 'admin@qq.com', '888', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('330c47f9fbed406fbddff92acd06ae3e', 'lily', '123', '丽丽', 'E', 'M', '123@qq.com', '888', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('bbab353c6d4e444984bd6e30159055f9', 'hanmeimei', '345', '韩梅梅', 'E', 'M', '123@12.com', '888', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('6cd4989053ed4485af8eaddee4ec81fc', 'green', 'asd', '格林', 'E', 'M', 'asd@asd.cn', '888', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('4162445b35f9451180bd7f379ff5641e', 'test', 'test', 'test', 'E', 'M', '12@qq.com', '888', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('f26af41e69ca4a02a58bcceee6f56ebc', 'tom', 'tom', '汤姆', 'E', 'M', 'tom@126.com', '888', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('878bcb11c95f4ef2a09533c5eedc0cc3', '11', 'fef10cd1325185dfed20645c3c035211', '11', 'E', 'M', '11@11.c', '11', null, null, null, null, null);
insert into SYS_USER (id, username, password, nickname, status, sex, email, mobile, create_user, create_date, modify_user, modify_date, theme)
values ('e338bccefdc845b89c26179c3938eeea', 'dong', 'ddd', '冬天', 'E', 'M', 'ddd@dd.c', '777', null, null, null, null, null);
commit;
prompt 10 records loaded
prompt Loading SYS_USER_ROLE...
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('7508a0e5d8844a82bcdbaf8f52b23bb1', '330c47f9fbed406fbddff92acd06ae3e', 'a90c7eec49024cec9443679b35b40dbc');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('0e226fb2bc6746ffa89741f509a45d7b', '330c47f9fbed406fbddff92acd06ae3e', 'e80fd416fe3a47b98c24cebb6f7780e3');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('c4d6fc2b7b7b411fb2058253d207e35e', '330c47f9fbed406fbddff92acd06ae3e', 'a09d47979dde466391879c27424272dc');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('a894ba703c8c48a782680488e1ec2b33', '330c47f9fbed406fbddff92acd06ae3e', '19cea7b08fd34584baca39b3e347a80a');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('4c73714cdea74a2eb228af916ba07159', 'da0f985d4b43454488d180dfd5841f98', 'e0e81169a8fb48e8b1e9cc54f29d1587');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('677380c4085a44ccb8313b1092f461ce', 'bbab353c6d4e444984bd6e30159055f9', 'a90c7eec49024cec9443679b35b40dbc');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('fc275ac6403c4504b7fb7a57665d8763', 'bbab353c6d4e444984bd6e30159055f9', 'e80fd416fe3a47b98c24cebb6f7780e3');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('bdc860f9409b4dc4966ca5f24311f640', 'bbab353c6d4e444984bd6e30159055f9', 'a09d47979dde466391879c27424272dc');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('59bd4012205c47acb4cdea3dcee67fae', 'bbab353c6d4e444984bd6e30159055f9', '19cea7b08fd34584baca39b3e347a80a');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('001d6c8ae8d848268ec61ab0a8a37913', 'bbab353c6d4e444984bd6e30159055f9', '46dc0bc5842d435da4b95770ff262432');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('bf8323622403497c9d1c692d3bde13c1', 'bbab353c6d4e444984bd6e30159055f9', '3f9e2ca4e3594006976b8780a07e1082');
insert into SYS_USER_ROLE (id, user_id, role_id)
values ('1bec56b9fc97473a8a66616c481d664d', '21356136546', 'a90c7eec49024cec9443679b35b40dbc');
commit;
prompt 12 records loaded
prompt Enabling triggers for DIC_DATASOURCE...
alter table DIC_DATASOURCE enable all triggers;
prompt Enabling triggers for DIC_ORG_MAPPING...
alter table DIC_ORG_MAPPING enable all triggers;
prompt Enabling triggers for SYS_DICT...
alter table SYS_DICT enable all triggers;
prompt Enabling triggers for SYS_DICT_ITEM...
alter table SYS_DICT_ITEM enable all triggers;
prompt Enabling triggers for SYS_PERMISSION...
alter table SYS_PERMISSION enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_ROLE_PERMISSION...
alter table SYS_ROLE_PERMISSION enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
prompt Enabling triggers for SYS_USER_ROLE...
alter table SYS_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
