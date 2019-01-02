ALTER TABLE `qimei_spec_good` DROP FOREIGN KEY `fk_qimei_specification_good_qimei_goods_1`;
ALTER TABLE `qimei_purchase_good` DROP FOREIGN KEY `fk_qimei_purchase_good_qimei_purchase_1`;
ALTER TABLE `qimei_purchase_good` DROP FOREIGN KEY `fk_qimei_purchase_good_qimei_spec_good_1`;
ALTER TABLE `qimei_order_good` DROP FOREIGN KEY `fk_qimei_order_good_qimei_order_1`;
ALTER TABLE `qimei_order_good` DROP FOREIGN KEY `fk_qimei_order_good_qimei_spec_good_1`;

DROP TABLE `qimei_goods`;
DROP TABLE `qimei_dictionary`;
DROP TABLE `qimei_spec_good`;
DROP TABLE `table_1`;
DROP TABLE `qimei_purchase`;
DROP TABLE `qimei_purchase_good`;
DROP TABLE `qimei_order`;
DROP TABLE `qimei_order_good`;
DROP TABLE `qimei_purchase_stock_record`;

CREATE TABLE `qimei_goods` (
`id` int(11) NOT NULL,
`number` varchar(255) NOT NULL DEFAULT 商品编号,
`name` varchar(255) NOT NULL COMMENT '商品名称',
`unit_id` int(11) NOT NULL COMMENT '单位-字典表 type=2的ID',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL DEFAULT 0 COMMENT '数据标记 0显示 1删除',
PRIMARY KEY (`id`) 
)
COMMENT = '商品表';
CREATE TABLE `qimei_dictionary` (
`id` int(11) NOT NULL,
`type` int(255) NOT NULL COMMENT '类型 1单位',
`name` varchar(255) NOT NULL,
`remarks` varchar(255) NULL COMMENT '备注',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL DEFAULT 0,
PRIMARY KEY (`id`) 
)
COMMENT = '字典表';
CREATE TABLE `qimei_spec_good` (
`id` int(11) NOT NULL,
`name` varchar(255) NOT NULL COMMENT '规格名称',
`goods_id` int(11) NOT NULL COMMENT '商品ID',
`number` varchar(255) NOT NULL COMMENT '商品编号',
`stock` int(11) NULL DEFAULT 0 COMMENT '库存数量',
`cost` decimal(10,2) NULL DEFAULT 0 COMMENT '成本价',
`book_count` int(11) NULL DEFAULT 0 COMMENT '预定数量',
`create_date` datetime NULL,
`update_date` datetime NULL,
`flag` varchar(255) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '商品规格表，一对多';
CREATE TABLE `table_1` (
);
CREATE TABLE `qimei_purchase` (
`id` int(11) NOT NULL,
`order` varchar(255) NULL,
`amount_of_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '总金额',
`other_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '其他金额',
`status` int(2) NULL COMMENT '状态 1:待入库 2:部分入库 3:已入库',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NULL,
`update_date` datetime NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `qimei_purchase_good` (
`id` int(11) NOT NULL,
`spec_good_id` int(11) NOT NULL COMMENT '商品规格表ID',
`purchase_id` int(11) NULL COMMENT '订货ID',
`purchase_count` int(11) NULL DEFAULT 0 COMMENT '采购数',
`unit_price` decimal(11,2) NULL DEFAULT 0.00 COMMENT '采购单价',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NULL,
`update_date` datetime NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `qimei_order` (
`id` int(11) NOT NULL,
`order` varchar(255) NOT NULL DEFAULT 订单编号,
`amount_of_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '商品名称',
`status` int(2) NOT NULL DEFAULT 1 COMMENT '状态 1:已完成 2:已作废',
`stock_status` int(2) NULL DEFAULT 1 COMMENT '出库状态 1:备货中 2:已出库',
`logistics_status` int(2) NULL,
`logistics_number` varchar(255) NULL COMMENT '物流单号',
`logistics_company` int(2) NULL COMMENT '物流公司编号',
`remarks` varchar(255) NULL COMMENT '备注',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL DEFAULT 0 COMMENT '数据标记 0显示 1删除',
PRIMARY KEY (`id`) 
)
COMMENT = '商品表';
CREATE TABLE `qimei_order_good` (
`id` int(11) NOT NULL,
`order_id` int(11) NULL COMMENT '订单ID',
`spec_good_id` int(11) NOT NULL COMMENT '商品规格表ID',
`count` int(11) NULL DEFAULT 0 COMMENT '订购数',
`unit_price` decimal(11,2) NULL DEFAULT 0.00 COMMENT '采购单价',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NULL,
`update_date` datetime NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `qimei_purchase_stock_record` (
`id` int(11) NOT NULL,
`spec_good_id` int(11) NOT NULL COMMENT '商品规格表ID',
`purchase_id` int(11) NULL COMMENT '订货ID',
`purchase_count` int(11) NULL DEFAULT 0 COMMENT '采购数',
`unit_price` decimal(11,2) NULL DEFAULT 0.00 COMMENT '采购单价',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NULL,
`update_date` datetime NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
);

ALTER TABLE `qimei_spec_good` ADD CONSTRAINT `fk_qimei_specification_good_qimei_goods_1` FOREIGN KEY (`goods_id`) REFERENCES `qimei_goods` (`id`);
ALTER TABLE `qimei_purchase_good` ADD CONSTRAINT `fk_qimei_purchase_good_qimei_purchase_1` FOREIGN KEY (`purchase_id`) REFERENCES `qimei_purchase` (`id`);
ALTER TABLE `qimei_purchase_good` ADD CONSTRAINT `fk_qimei_purchase_good_qimei_spec_good_1` FOREIGN KEY (`spec_good_id`) REFERENCES `qimei_spec_good` (`id`);
ALTER TABLE `qimei_order_good` ADD CONSTRAINT `fk_qimei_order_good_qimei_order_1` FOREIGN KEY (`order_id`) REFERENCES `qimei_order` (`id`);
ALTER TABLE `qimei_order_good` ADD CONSTRAINT `fk_qimei_order_good_qimei_spec_good_1` FOREIGN KEY (`spec_good_id`) REFERENCES `qimei_spec_good` (`id`);

