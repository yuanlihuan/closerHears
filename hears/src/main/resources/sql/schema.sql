--使用数据库
USER hears
--创建秒杀库存表
CREATE TABLE pub_seckill(
	`seckill_id` BIGINT NOT NULL  AUTO_INCREMENT COMMENT '商品库存id',
	`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
	`number` INT NOT NULL COMMENT '库存数量',
	`start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
	`end_time` TIMESTAMP NOT NULL COMMENT'秒杀结束时间',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (seckill_id),
	KEY idx_start_time(start_time),
	KEY idx_end_time(end_time),
	KEY idx_create_time(create_time)
)ENGINE=INNODB 	AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8  COMMENT='秒杀库存';

--初始化数据
INSERT INTO pub_seckill (NAME,number,start_time, end_time)
VALUES
('1000元秒杀iphone8',1000,'2018-06-01 00:00:00','2018-06-02 00:00:00'),
('500元秒杀ipad8',600,'2018-06-01 00:00:00','2018-06-02 00:00:00'),
('300元秒杀小米6',300,'2018-06-01 00:00:00','2018-06-02 00:00:00'),
('200元秒杀红米',200,'2018-06-01 00:00:00','2018-06-02 00:00:00');

--秒杀成功明细表
CREATE TABLE pub_success_killed(
`seckill_id` BIGINT NOT NULL  COMMENT '秒杀商品id',
`user_phone` BIGINT NOT NULL COMMENT '用户手机号',
`state` TINYINT NOT NULL default 0 COMMENT '状态提示： -1 无效， 0：成功，1已付款',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id, user_phone), 
KEY idx_create_time (create_time)

)ENGINE=INNODB 	CHARSET=utf8  COMMENT='秒杀成功明细表';