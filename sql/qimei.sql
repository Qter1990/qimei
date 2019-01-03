ALTER TABLE `qimei_spec_good` DROP FOREIGN KEY `fk_qimei_specification_good_qimei_goods_1`;
ALTER TABLE `qimei_purchase_good` DROP FOREIGN KEY `fk_qimei_purchase_good_qimei_purchase_1`;
ALTER TABLE `qimei_purchase_good` DROP FOREIGN KEY `fk_qimei_purchase_good_qimei_spec_good_1`;
ALTER TABLE `qimei_order_good` DROP FOREIGN KEY `fk_qimei_order_good_qimei_order_1`;
ALTER TABLE `qimei_order_good` DROP FOREIGN KEY `fk_qimei_order_good_qimei_spec_good_1`;
ALTER TABLE `qimei_purchase_instock_record` DROP FOREIGN KEY `fk_qimei_purchase_stock_record_qimei_purchase_good_1`;
ALTER TABLE `qimei_order_gathering_record` DROP FOREIGN KEY `fk_qimei_order_gathering_record_qimei_order_1`;
ALTER TABLE `qimei_order_outstock_record` DROP FOREIGN KEY `fk_qimei_order_outstock_record_copy_1_qimei_order_good_1`;

DROP TABLE `qimei_goods`;
DROP TABLE `qimei_spec_good`;
DROP TABLE `qimei_purchase`;
DROP TABLE `qimei_purchase_good`;
DROP TABLE `qimei_order`;
DROP TABLE `qimei_order_good`;
DROP TABLE `qimei_purchase_instock_record`;
DROP TABLE `qimei_order_gathering_record`;
DROP TABLE `qimei_order_outstock_record`;

CREATE TABLE `qimei_goods` (
`id` int(11) NOT NULL,
`number` varchar(255) NOT NULL COMMENT '商品编号',
`name` varchar(255) NOT NULL COMMENT '商品名称',
`unit_id` int(11) NOT NULL COMMENT '单位-字典表 type=2的ID',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL DEFAULT 0 COMMENT '数据标记 0显示 1删除',
PRIMARY KEY (`id`) 
)
COMMENT = '商品表';
CREATE TABLE `qimei_spec_good` (
`id` int(11) NOT NULL,
`name` varchar(255) NOT NULL COMMENT '规格名称',
`goods_id` int(11) NOT NULL COMMENT '商品ID',
`number` varchar(255) NOT NULL COMMENT '商品编号',
`stock` int(11) NULL DEFAULT 0 COMMENT '库存数量',
`cost` decimal(10,2) NULL DEFAULT 0 COMMENT '成本价',
`book_count` int(11) NULL DEFAULT 0 COMMENT '预定数量',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` varchar(255) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '商品规格表，一对多';
CREATE TABLE `qimei_purchase` (
`id` int(11) NOT NULL,
`order` varchar(255) NOT NULL,
`amount_of_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '总金额',
`other_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '其他金额',
`status` int(2) NULL COMMENT '状态 1:待入库 2:部分入库 3:已入库',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '采购表';
CREATE TABLE `qimei_purchase_good` (
`id` int(11) NOT NULL,
`spec_good_id` int(11) NOT NULL COMMENT '商品规格表ID',
`purchase_id` int(11) NOT NULL COMMENT '订货ID',
`purchase_count` int(11) NULL DEFAULT 0 COMMENT '采购数',
`unit_price` decimal(11,2) NULL DEFAULT 0.00 COMMENT '采购单价',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '采购商品表';
CREATE TABLE `qimei_order` (
`id` int(11) NOT NULL,
`order` varchar(255) NOT NULL COMMENT '订单编号',
`amount_of_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '商品名称',
`status` int(2) NOT NULL DEFAULT 1 COMMENT '状态 1:已完成 2:已作废',
`stock_status` int(2) NULL DEFAULT 1 COMMENT '出库状态 1:备货中 2:部分出库 3:已出库',
`logistics_status` int(2) NULL,
`remarks` varchar(255) NULL COMMENT '备注',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL DEFAULT 0 COMMENT '数据标记 0显示 1删除',
PRIMARY KEY (`id`) 
)
COMMENT = '订单表';
CREATE TABLE `qimei_order_good` (
`id` int(11) NOT NULL,
`order_id` int(11) NOT NULL COMMENT '订单ID',
`spec_good_id` int(11) NOT NULL COMMENT '商品规格表ID',
`count` int(11) NULL DEFAULT 0 COMMENT '订购数',
`unit_price` decimal(11,2) NULL DEFAULT 0.00 COMMENT '采购单价',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '订单商品表';
CREATE TABLE `qimei_purchase_instock_record` (
`id` int(11) NOT NULL,
`purchase_good_id` int(11) NOT NULL COMMENT '订货商品ID',
`in_stock_count` int(11) NULL DEFAULT 0 COMMENT '入库数量',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '采购入库记录表';
CREATE TABLE `qimei_order_gathering_record` (
`id` int(11) NOT NULL,
`order_id` int(11) NOT NULL COMMENT '订单ID',
`gathering_money` decimal(11,2) NULL DEFAULT 0.00 COMMENT '付款金额',
`gathering_type` int(2) NULL DEFAULT 0 COMMENT '付款方式 1:微信 2:支付宝 3:银行转账 4:现金支付',
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '订单收款记录表';
CREATE TABLE `qimei_order_outstock_record` (
`id` int(11) NOT NULL,
`order_good_id` int(11) NOT NULL COMMENT '订货商品ID',
`out_stock_count` int(11) NULL DEFAULT 0 COMMENT '出库数量',
`logistics_number` varchar(255) NULL,
`logistics_company` int(2) NULL,
`remarks` varchar(255) NULL COMMENT '备注信息',
`create_date` datetime NOT NULL,
`update_date` datetime NOT NULL,
`flag` int(2) NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '订单出库发货记录表';

ALTER TABLE `qimei_spec_good` ADD CONSTRAINT `fk_qimei_specification_good_qimei_goods_1` FOREIGN KEY (`goods_id`) REFERENCES `qimei_goods` (`id`);
ALTER TABLE `qimei_purchase_good` ADD CONSTRAINT `fk_qimei_purchase_good_qimei_purchase_1` FOREIGN KEY (`purchase_id`) REFERENCES `qimei_purchase` (`id`);
ALTER TABLE `qimei_purchase_good` ADD CONSTRAINT `fk_qimei_purchase_good_qimei_spec_good_1` FOREIGN KEY (`spec_good_id`) REFERENCES `qimei_spec_good` (`id`);
ALTER TABLE `qimei_order_good` ADD CONSTRAINT `fk_qimei_order_good_qimei_order_1` FOREIGN KEY (`order_id`) REFERENCES `qimei_order` (`id`);
ALTER TABLE `qimei_order_good` ADD CONSTRAINT `fk_qimei_order_good_qimei_spec_good_1` FOREIGN KEY (`spec_good_id`) REFERENCES `qimei_spec_good` (`id`);
ALTER TABLE `qimei_purchase_instock_record` ADD CONSTRAINT `fk_qimei_purchase_stock_record_qimei_purchase_good_1` FOREIGN KEY (`purchase_good_id`) REFERENCES `qimei_purchase_good` (`id`);
ALTER TABLE `qimei_order_gathering_record` ADD CONSTRAINT `fk_qimei_order_gathering_record_qimei_order_1` FOREIGN KEY (`order_id`) REFERENCES `qimei_order` (`id`);
ALTER TABLE `qimei_order_outstock_record` ADD CONSTRAINT `fk_qimei_order_outstock_record_copy_1_qimei_order_good_1` FOREIGN KEY (`order_good_id`) REFERENCES `qimei_order_good` (`id`);

