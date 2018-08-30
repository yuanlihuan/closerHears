--用户信息表
CREATE TABLE pub_user(
	`id` BIGINT not null AUTO_INCREMENT comment '用户表id',
	`user_name` varchar(50) not null comment '用户名',
	`password` varchar(50) not null comment '用户名密码，md5加密',
	`email` varchar(50) default null comment '邮箱',
	`phone` BIGINT not null COMMENT '手机号', 
	`question` varchar(100) default null comment '找回密码问题',
	`auswer` VARCHAR(100) DEFAULT NULL COMMENT '找回密码答案',
	`role` int(4) not null comment '角色：0管理员，1是内部用户，2是普通用户 ',
	`update_time` TIMESTAMP NOT NULL COMMENT '修改时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_user_name(user_name),
	KEY idx_phone(phone)	
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='用户表';

--分类表
CREATE TABLE pub_category(
	`id` BIGINT not null AUTO_INCREMENT comment '类别id',
	`parent_id` bigint default null comment '父类别id，当id等于0，说明是根节点',	
	`name` varchar(50) not null comment '类别名称',
	`status` tinyint(1) default '1' comment '类别状态1正常，2作废',
	`sort_order` INT(4) NOT NULL COMMENT '排序编号，相同展示顺序，数值相同则自然排序',
	`update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_parent_id(parent_id)
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='分类表';

--商品表
CREATE TABLE pub_product(
	`id` BIGINT not null AUTO_INCREMENT comment '类别id',
	`category_id` bigint not null comment '分类id，对应pub_category表的主键',	
	`name` varchar(50) not null comment '商品名称',
	`subtitle` varchar(200) default null comment '商品副标题',
	`main_image` varchar(500) default null comment '产品主图，url相对路径',
	`images_id`bigint not null comment '图片详情id，pub_images的id', 
	`detail_id` bigint not null comment '商品详情id，pub_detail的id', 
	`price` decimal(20,2) not null comment '价格，单位-元保留两位小数',
	`stock` int(11) not null comment '库存数量',
	`status` tinyint(1) default '1' comment '商品状态1正常，2下架，3删除',
	`update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_category_id(category_id),
	KEY idx_images_id(images_id),
	KEY idx_detail_id(detail_id)	
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='商品表';

--购物车表
CREATE TABLE pub_cart(
	`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '购物车id',
	`user_id` BIGINT NOT NULL COMMENT '用户id，pub_user的id',	
	`product_id` BIGINT NOT NULL COMMENT '商品id，pub_product的id',
	`quantity` INT(11) DEFAULT NULL COMMENT '数量',
	`checked` TINYINT(1) DEFAULT '1' COMMENT '是否选择，1已勾选，0未勾选',
	`update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_user_id(user_id),
	KEY idx_product_id(product_id)	
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='购物车表';

--订单表
CREATE TABLE pub_pay_inf(
	`id` BIGINT not null AUTO_INCREMENT comment '购物车id',
	`user_id` bigint not null comment '用户id，pub_user的id',
	`order_no` BIGINT default NULL COMMENT '订单号',	
	`pay_platform` TINYINT(1) default null comment '支付平台1微信，2支付宝',
	`platform_number` varchar(200) default null comment '支付流水号',
	`platform_status` tinyint(1) default '0' comment '支付状态，1已支付，0未支付',
	`update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_user_id(user_id),
	KEY idx_order_no(order_no)	
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='支付信息表';

--订单表
CREATE TABLE pub_order(
	`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单id',
	`order_no` BIGINT NOT NULL COMMENT '订单号',
	`user_id` BIGINT NOT NULL COMMENT '用户id，pub_user的id',
	`shipping_id` BIGINT NOT NULL  COMMENT '收货地址id',
	`payment` DECIMAL(20,2) NOT NULL COMMENT '实际付款金额,单位元，保留两位小数',
	`payment_type` TINYINT(1) NOT NULL COMMENT '支付类型，1在线支付',
	`postage` DECIMAL(20,2) NOT NULL COMMENT '运费，单位元',
	`status` INT(6) NOT NULL COMMENT '订单状态：0已取消，10未支付，20已支付，40已发货，50交易成功，60交易失败',
	`payment_time` TIMESTAMP COMMENT '支付时间',
	`send_time` TIMESTAMP  COMMENT '发货时间',
	`end_time` TIMESTAMP COMMENT '交易完成时间',
	`close_time` TIMESTAMP COMMENT '交易关闭时间',
	`update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_order_no(order_no)	
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='订单表';

--订单明细
CREATE TABLE pub_order_item(
	`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单明细id',
	`order_no` BIGINT NOT NULL COMMENT '订单号',
	`user_id` BIGINT NOT NULL COMMENT '用户id，pub_user的id',
	`product_id` BIGINT NOT NULL  COMMENT '商品id',
	`product_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
	`product_image` VARCHAR(500) NOT NULL COMMENT '商品图片地址',
	`current_unit_price` DECIMAL(20,2) NOT NULL COMMENT '生成订单时的商品单价,单位元，保留两位小数',
	`quantity` INT(10) NOT NULL COMMENT '商品数量',	
	`total_price` DECIMAL(20,2) NOT NULL COMMENT '商品总价金额,单位元，保留两位小数',
	`update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY idx_order_no(order_no),
	KEY idx_current_unit_price(current_unit_price)

--收货地址表
CREATE TABLE pub_order_shipping(
	`id` BIGINT not null AUTO_INCREMENT comment '地址id',
	`user_id` bigint not null comment '用户id，pub_user的id',
	`receiver_name` varchar(20) not null comment '收货姓名',
	`receiver_phone` varchar(20) not null comment '收货固定电话',
	`receiver_mobile` varchar(20) default null comment '收货移动电话',
	`receiver_province` varchar(20) not null comment '省会',
	`receiver_city` varchar(20) not null comment '城市',
	`receiver_district` varchar(20) not null comment '区、县',
	`receiver_address` varchar(200) not null comment '详细地址',
	`receiver_zip` varchar(6) not null comment '邮编',
	PRIMARY KEY (id),
	KEY idx_user_id(user_id)
)ENGINE=INNODB 	AUTO_INCREMENT=1000000 DEFAULT CHARSET=UTF8  COMMENT='收货地址表';