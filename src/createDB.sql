
--创建用户表
create table users(
  email varchar(20) not null primary key,
  paswd varchar(20) unique not null
  )

--创建用户清单表
  create table list(
    userEmail  varchar(20) not null,
	content text not null,
	cTime varchar(25) not null default convert(varchar(25),getdate(),120),
    primary key(userEmail, cTime)
	)

--创建项目表
  create table project(
    userEmail  varchar(20) not null,
	--ID号从1开始自增，每次加1
	projId int not null identity(1,1) primary key,
	projname varchar(40) not null,
	projStatus varchar(10) not null default '进行中' check (projStatus in('进行中','已完成','推迟','搁置')),
	remark  text not null
	)

--创建任务表
   create table task(
      projId int not null,
	  taskId int not null identity(1,1) primary key,
	  taskname varchar(40) not null,
	  taskStatus varchar(10) not null default '进行中' check (taskStatus in('进行中','已完成','推迟','搁置')),
	  remark text
	  )

--创建用户分享心得表
 create table ideas(
    userEmail  varchar(20),
	title varchar(40) not null,
	content text not null,
	-- 120 输出举例 2006-05-16 10:57:49
	cTime varchar(25) not null  default convert(varchar(25),getdate(),120),
	primary key(userEmail, cTime)
	)