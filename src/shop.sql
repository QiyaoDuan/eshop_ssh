drop database if exists shop;
/*�������ݿ⣬�����ñ���*/
create database shop default character set utf8;

use shop;
/*ɾ������Ա��*/
drop table if exists account;
/*ɾ����Ʒ����*/
drop table if exists category;
/*ɾ����Ʒ��Ϣ��*/
drop table if exists product;
/*ɾ���������*/
drop table if exists forder;
/*ɾ�����ﳵ����������*/
drop table if exists sorder;
/*ɾ������״̬��*/
drop table if exists status;
/*ɾ���û���*/
drop table if exists user;

/*============================*/
/*      Table������Ա��ṹ                       */
/*============================*/
create table account
(
	/* ����Ա��ţ��Զ����� */
	id int primary key not null auto_increment,
	/* ����Ա��¼�� */
	login varchar(20),
	/* ����Ա���� */
	name varchar(20),
	/* ����Ա���� */
	pass varchar(20)
);

/*============================*/
/*     Table����Ʒ����ṹ                      */
/*============================*/
create table category
(
   /* ����ţ��Զ����� */
   id  int primary key not null auto_increment,
   /* ������� */
   type varchar(20),
   /* ����Ƿ�Ϊ�ȵ�����ȵ������п�����ʾ����ҳ*/
   hot  bool default false,
   /* ��������������λ����Ա���� */
   aid int,
   constraint aid_FK foreign key(aid) references account(id)
);

/*=============================*/
/* Table: ��Ʒ��ṹ	 		   */
/*=============================*/
create table product
(
   /* ��Ʒ���,�Զ����� */
   id                  int primary key not null auto_increment,
   /* ��Ʒ���� */
   name                varchar(50),
   /* ��Ʒ�۸� */
   price               decimal(8,2),
   /* ��ƷͼƬ */
   pic                 varchar(300),
   /* ��Ʒ�򵥽��� */
   remark              longtext,
   /* ��Ʒ��ϸ���� */
   xremark             longtext,
   /* ��Ʒ�������� */
   date                timestamp default CURRENT_TIMESTAMP,
   /* �Ƿ�Ϊ�Ƽ���Ʒ,�Ƽ���Ʒ���п�����ʾ���̳���ҳ */
   commend             bool,
   /* �Ƿ�Ϊ��Ч��Ʒ,��Ч��Ʒ���п�����ʾ���̳���ҳ */
   open                bool,
   /* ��Ʒ���ڵ������*/
   cid                  int,
   constraint cid_FK foreign key(cid) references category(id)
);

/*============================*/
/* Table: �û���ṹ 		      */
/*============================*/
create table user
(
   /* �û����,�Զ����� */
   id                  int primary key not null auto_increment,
   /* �û���¼�� */
   login               varchar(20),
   /* �û���ʵ���� */
   name                varchar(20),
   /* �û���¼���� */
   pass                varchar(20),
   /* �û��Ա� */
   sex                 varchar(20),
   /* �û��绰 */
   phone               varchar(20),
   /* �û�Email */
   email               varchar(20)
);

/*=============================*/
/* Table: ����״̬��ṹ 		       */
/*=============================*/
create table status
(
   /* ״̬���,�Զ����� */
   id                  int primary key not null auto_increment,
   /* ����״̬ */
   status               varchar(10)
);

/*=============================*/
/* Table: ���ﳵ����������ṹ		   */
/*=============================*/
create table forder
(
   /* �������,�Զ����� */
   id                  int primary key not null auto_increment,
   /* �ռ������� */
   name                varchar(20),
   /* �ռ��˵绰 */
   phone               varchar(20),
   /* ������Ϣ */
   remark              varchar(20),
   /* �µ����� */
   date                timestamp default CURRENT_TIMESTAMP,
   /* �����ܽ�� */
   total               decimal(8,2),
   /* �ռ����ʱ� */
   post                varchar(20),
    /* �ռ����ʱ� */
   address             varchar(200),
   /* ����״̬ */
   sid                 int default 1,
   /* ��Ա��� */
   uid                 int,
   constraint sid_FK foreign key(sid) references status(id),
   constraint uid_FK foreign key(uid) references user(id)
);

/* �޸��Զ������ĳ�ʼֵ */
ALTER TABLE forder AUTO_INCREMENT = 201605001;

/*=============================*/
/* Table: �������ṹ 		       */
/*=============================*/

create table sorder
(
   /* ��������,�Զ����� */
   id                  int primary key not null auto_increment,
   /* ��������Ʒ������ */
   name                varchar(20),
   /* ����ʱ��Ʒ�ļ۸� */
   price               decimal(8,2),
   /* ��������� */
   number              int not null,
   /* ������Ʒ��� */
   pid                  int,
   /* �˶�����,�����Ķ������ */
   fid                  int,
   constraint pid_FK foreign key(pid) references product(id),
   constraint fid_FK foreign key(fid) references forder(id)
);

/*�����������*/
insert into account(login,name,pass) values('admin','����Ա','admin');
insert into account(login,name,pass) values('user','�ͷ�A','user');

INSERT INTO category (type,hot,aid) VALUES ('��ʿ����',true,1);
INSERT INTO category (type,hot,aid) VALUES ('Ůʿ����',true,1);
INSERT INTO category (type,hot,aid) VALUES ('��ͯ����',true,2);
INSERT INTO category (type,hot,aid) VALUES ('��������',true,2);

/* ��Ʒ�������� */
INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('ʥ������',2999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��������',1999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��������',3999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����������',4999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,1);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����Ůװ',199.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('ѩ��ѥ',299.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('ŷ��Ůװ',3999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('Ů������',4999.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,2);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('���ܵ�����',3998.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��ʿ������',299.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����Ӳ��',599.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('��ˮţ����',399.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,3);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('������',150.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('�ϰ���',199.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('�յ�',3599.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

INSERT INTO product (name,price,pic,remark,xremark,commend,open,cid) VALUES 
('����',699.00,'test.jpg','�����Ǽ򵥽���','��������ϸ����',true,true,4);

/* �û��������� */
INSERT INTO user (login,name,pass,sex,phone,email)
VALUES ('user','С��','user','��','15216771570','nishengwus@163.com');

INSERT INTO user (login,name,pass,sex,phone,email)
VALUES ('user2','С��','user2','Ů','13812345679','20000@qq.com');

/*����״̬��������*/
INSERT INTO status (status) VALUES ('δ֧��');
INSERT INTO status (status) VALUES ('��֧��');
INSERT INTO status (status) VALUES ('������');
INSERT INTO status (status) VALUES ('�������');

/*���빺�ﳵ��������*/
INSERT INTO forder (name,phone,remark,date,total,address,post,uid) VALUES ('bb','123','��ע',default,200.3,'���������','1000',1);
INSERT INTO forder (name,phone,remark,date,total,address,post,uid) VALUES ('bb','123','��ע',default,200.3,'�Ϻ��ζ���','1000',2);
INSERT INTO forder (name,phone,remark,date,total,address,post,uid) VALUES ('bb','123','��ע',default,200.3,'�Ϻ���ɽ��','1000',2);

/*���빺�ﳵ���������*/
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('�յ�',200,3,15,201605001);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('����',0.3,5,16,201605001);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ɼɼ����',0.3,7,3,201605003);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ʥ������',0.3,12,1,201605003);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('����Ůװ',0.3,20,5,201605003);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ѩ��ѥ',0.3,10,6,201605003);
INSERT INTO sorder (name,price,number,pid,fid) VALUES ('ŷ��Ůװ',0.3,9,7,201605003);

SELECT * FROM account;
SELECT * FROM category;
SELECT * FROM product;
SELECT * FROM user;
SELECT * FROM status;
SELECT * FROM forder;
SELECT * FROM sorder;
select sum(number) as '����', name '����' from sorder group by pid