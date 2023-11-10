CREATE DATABASE freight;

USE freight;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`(
    `id`                BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `driver_id`         BIGINT(20) NOT NULL COMMENT '司机id',
    `driver_name`       VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '司机姓名',
    `phone`             VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '电话号码',
    `hashed_password`   VARBINARY(64) NOT NULL COMMENT '哈希密码',
    `salt`              VARBINARY(16) NOT NULL COMMENT '随机盐',
    `register_date`     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `update_date`       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_driver_id`(`driver_id`),
    UNIQUE KEY `unique_phone`(`phone`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='司机配置';

-- ----------------------------
-- Table structure for boss
-- ----------------------------
DROP TABLE IF EXISTS `boss`;
CREATE TABLE `boss`(
    `id`                BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `boss_id`           BIGINT(20) NOT NULL COMMENT '老板id',
    `boss_name`         VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '老板姓名',
    `phone`             VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '电话号码',
    `hashed_password`   VARBINARY(64) NOT NULL COMMENT '哈希密码',
    `salt`              VARBINARY(16) NOT NULL COMMENT '随机盐',
    `register_date`     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `update_date`       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_boss_id`(`boss_id`),
    UNIQUE KEY `unique_phone`(`phone`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='老板配置';

-- ----------------------------
-- Table structure for cargo
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo`(
    `id`                BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `cargo_id`          BIGINT(20) NOT NULL COMMENT '货物id',
    `boss_id`           BIGINT(20) NOT NULL COMMENT '老板id',
    `cargo_name`        VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '货物名称',
    `cargo_weight`      BIGINT(20) NOT NULL COMMENT '货物重量',
    `begin_location`    VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '起点',
    `end_location`      VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '终点',
    `value`             BIGINT(20) NOT NULL COMMENT '运费价格，元/kg',
    `begin_time`        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `end_time`          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    `stock`             BIGINT(20) NOT NULL COMMENT '库存（kg）',
    `info`              VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_cargo_id`(`cargo_id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货物配置';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`(
    `id`                BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_id`          BIGINT(20) NOT NULL COMMENT '订单id',
    `cargo_id`          BIGINT(20) NOT NULL COMMENT '货物id',
    `boss_id`           BIGINT(20) NOT NULL COMMENT '老板id',
    `driver_id`         BIGINT(20) NOT NULL COMMENT '司机id',
    `cargo_weight`      BIGINT(20) NOT NULL COMMENT '单次运输货物重量',
    `value`             BIGINT(20) NOT NULL COMMENT '运费',
    `state`             INT NOT NULL DEFAULT 0 COMMENT '状态信息 ，0:已接单 1:正在运输 2:运输结束 3:确认到货 4:已付款',
    `info`              VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注信息',
    `created_at`        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
    `updated_at`        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_order_id`(`order_id`),
    INDEX `idx_cargo_id`(`cargo_id`),
    INDEX `idx_boss_id`(`boss_id`),
    INDEX `idx_driver_id`(`driver_id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单配置';


-- ----------------------------
-- Table structure for authentication
-- ----------------------------
DROP TABLE IF EXISTS `authentication`;

CREATE TABLE `authentication`(
    `id`                         BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `driver_id`                  BIGINT(20) NOT NULL COMMENT '司机id',
    `authentication_status`      INT NOT NULL DEFAULT 0 COMMENT '认证状态，0: 未认证, 1: 已认证, 2: 认证失败',
    `id_card_number`             VARCHAR(18) NOT NULL COMMENT '身份证号',
    `id_card_valid_from`         DATE NOT NULL COMMENT '身份证有效期开始日期',
    `id_card_valid_to`           DATE NOT NULL COMMENT '身份证有效期截止日期',
    `driver_license`             VARCHAR(20) NOT NULL COMMENT '驾驶证',
    `driver_license_valid_to`    DATE NOT NULL COMMENT '驾驶证截止日期',
    `qualification_certificate`   VARCHAR(20) NOT NULL COMMENT '从业资格证',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_driver_id`(`driver_id`),
    INDEX `idx_driver_id`(`driver_id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='验证信息配置';
