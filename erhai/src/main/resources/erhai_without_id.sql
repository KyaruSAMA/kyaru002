use
testdb001;

-- 用户表
drop table if exists t_user;
CREATE TABLE t_user
(
    id        int(11) auto_increment,
    username  varchar(40) NOT NULL,
    password  varchar(20) NOT NULL,
    sex       int(1) DEFAULT 0,
    id_number varchar(18),
    tel       varchar(11),
    addr      varchar(100),
    type      int(1) DEFAULT 0,
    CONSTRAINT t_user_id_pk PRIMARY KEY (id),
    CONSTRAINT t_user_username_uk UNIQUE (username),
    CONSTRAINT t_user_id_number_uk UNIQUE (id_number)
);

-- 品牌表
drop table if exists t_brand;
CREATE TABLE t_brand
(
    id   int(11) auto_increment,
    name varchar(50),
    CONSTRAINT t_brand_id_pk PRIMARY KEY (id),
    CONSTRAINT t_brand_name_uk UNIQUE (name)
);


-- 类型表
drop table if exists t_category;
CREATE TABLE t_category
(
    id   int(11) auto_increment,
    name varchar(50),
    CONSTRAINT t_category_id_pk PRIMARY KEY (id),
    CONSTRAINT t_category_name_uk UNIQUE (name)
);

-- 汽车表
-- model:汽车型号，t_comments:介绍
drop table if exists t_car;
CREATE TABLE t_car
(
    id          int(11) auto_increment,
    car_number  varchar(10),
    brand_id    int(9),
    model       varchar(40),
    color       varchar(20),
    category_id int(9),
    t_comments  varchar(100),
    price       float(11, 2
) ,
    rent        float(9, 2),
    status      int(1) DEFAULT 0,
    usable      int(1) DEFAULT 0,
    CONSTRAINT t_car_id_pk PRIMARY KEY (id),
    CONSTRAINT t_car_number_uk UNIQUE (car_number)
);

-- 租赁表
drop table if exists t_record;
CREATE TABLE t_record
(
    id          int(11) auto_increment,
    user_id     int(11),
    car_id      int(11),
    start_date  DATE,
    return_date DATE,
    payment     float(9, 2
) ,
    CONSTRAINT t_record_id_pk PRIMARY KEY (id)
);


-- 添加测试数据
INSERT INTO t_user
VALUES (null, 'xiaoming', '111111', 0, '15647641312345', '1515445554', '江苏', 0);
INSERT INTO t_user
VALUES (null, 'tom', '111111', 0, '15647641312346', '1515445555', '日本', 0);
INSERT INTO t_user
VALUES (null, 'lucy', '111111', 0, '15647641312347', '1515445556', '上海', 0);
INSERT INTO t_user
VALUES (null, 'sam', '111111', 0, '15647641312348', '1515445557', '北京', 0);
INSERT INTO t_user
VALUES (null, 'leo', '111111', 0, '15647641312349', '1515445558', '深圳', 0);
INSERT INTO t_user
VALUES (null, 'marry', '111111', 0, '15647641312340', '1515445559', '南京', 1);
INSERT INTO t_user (id, username, password, type)
VALUES (null, 'admin', 'admin', 1);

INSERT INTO t_brand
VALUES (null, '标致');
INSERT INTO t_brand
VALUES (null, '大众');
INSERT INTO t_brand
VALUES (null, '奥迪');
INSERT INTO t_brand
VALUES (null, '奔驰');
INSERT INTO t_brand
VALUES (null, '宝马');

INSERT INTO t_category
VALUES (null, '紧凑型');
INSERT INTO t_category
VALUES (null, '舒适型');
INSERT INTO t_category
VALUES (null, 'SUV');
INSERT INTO t_category
VALUES (null, '精英型');

INSERT INTO t_car (id, car_number, brand_id, model, color, category_id, t_comments, price, rent, status, usable)
VALUES (null, '沪A11C32', 2, '郎逸', '红色', 2, '自动1.6L', 140000, 72, 0, 0);
INSERT INTO t_car
VALUES (null, '沪B6888', 2, '途观', '黑色', 3, '自动1.8T', 220000, 200, 0, 0);
INSERT INTO t_car
VALUES (null, '沪A1686', 3, 'A4L', '红色', 4, '自动2.0T', 400000, 359, 0, 0);
INSERT INTO t_car
VALUES (null, '沪D11C32', 1, '308', '黑色', 1, '手动1.6L', 130000, 56, 0, 0);
INSERT INTO t_car
VALUES (null, '沪E11C99', 1, '308S', '蓝色', 2, '自动1.2T', 160000, 70, 0, 0);
INSERT INTO t_car
VALUES (null, '沪F11C21', 2, '高尔夫', '红色', 1, '自动1.4T', 200000, 69, 0, 0);
INSERT INTO t_car
VALUES (null, '沪F1324', 5, '320Li', '白色', 4, '自动2.0T', 380000, 500, 0, 0);
INSERT INTO t_car
VALUES (null, '沪F6666', 4, 'B200', '黑色', 4, '自动1.6T', 320000, 300, 0, 0);

INSERT INTO t_record
VALUES (null, 2, 2, '2017-01-24', '2017-01-26', 400);
INSERT INTO t_record
VALUES (null, 3, 4, '2017-02-20', '2017-02-28', 448);
INSERT INTO t_record
VALUES (null, 4, 5, '2017-03-01', '2017-03-03', 210);
INSERT INTO t_record
VALUES (null, 1, 1, '2017-03-02', '2017-03-06', 288);

