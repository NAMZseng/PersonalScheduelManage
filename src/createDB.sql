
--�����û���
create table users(
  email varchar(20) not null primary key,
  paswd varchar(20) unique not null
  )

--�����û��嵥��
  create table list(
    userEmail  varchar(20) not null,
	content text not null,
	cTime varchar(25) not null default convert(varchar(25),getdate(),120),
    primary key(userEmail, cTime)
	)

--������Ŀ��
  create table project(
    userEmail  varchar(20) not null,
	--ID�Ŵ�1��ʼ������ÿ�μ�1
	projId int not null identity(1,1) primary key,
	projname varchar(40) not null,
	projStatus varchar(10) not null default '������' check (projStatus in('������','�����','�Ƴ�','����')),
	remark  text not null
	)

--���������
   create table task(
      projId int not null,
	  taskId int not null identity(1,1) primary key,
	  taskname varchar(40) not null,
	  taskStatus varchar(10) not null default '������' check (taskStatus in('������','�����','�Ƴ�','����')),
	  remark text
	  )

--�����û������ĵñ�
 create table ideas(
    userEmail  varchar(20),
	title varchar(40) not null,
	content text not null,
	-- 120 ������� 2006-05-16 10:57:49
	cTime varchar(25) not null  default convert(varchar(25),getdate(),120),
	primary key(userEmail, cTime)
	)