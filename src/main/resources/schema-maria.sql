-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- DROP SCHEMA IF EXISTS `wasup` ;

-- CREATE SCHEMA IF NOT EXISTS `wasup` DEFAULT CHARACTER SET utf8 ;
-- USE `wasup` ;


-- -----------------------------------------------------
-- Table `cluster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cluster` CASCADE ;

CREATE TABLE IF NOT EXISTS `cluster` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `delete_yn` VARCHAR(1) NULL DEFAULT 'N',
  `create_user` BIGINT NULL,
  `create_date` DATETIME NULL,
  `update_user` BIGINT NULL,
  `update_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `domain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `domain` CASCADE ;

CREATE TABLE IF NOT EXISTS `domain` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cluster_id` BIGINT NOT NULL,
  `name` VARCHAR(255) NOT NULL COMMENT 'Domain Alias',
  `description` VARCHAR(255) NULL,
  `delete_yn` VARCHAR(1) NULL DEFAULT 'N',
  `create_user` BIGINT NULL,
  `create_date` DATETIME NULL,
  `update_user` BIGINT NULL,
  `update_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_domain_cluster1_idx` (`cluster_id` ASC),
  CONSTRAINT `fk_domain_cluster1`
    FOREIGN KEY (`cluster_id`)
    REFERENCES `cluster` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `host`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `host` CASCADE ;

CREATE TABLE IF NOT EXISTS `host` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `scouter_server_id` BIGINT NULL,
  `name` VARCHAR(255) NOT NULL COMMENT 'Host Alias',
  `ip_address` VARCHAR(255) NOT NULL,
  `port` INT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(512) NULL,
  `sudoer_yn` VARCHAR(1) NULL,
  `agent_install_path` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `key_file_path` VARCHAR(255) NULL,
  `key_string` VARCHAR(4096) NULL,
  `monitoring_yn` VARCHAR(1) NOT NULL DEFAULT 'Y',
  `delete_yn` VARCHAR(1) NOT NULL DEFAULT 'N',
  `create_user` BIGINT NULL,
  `create_date` DATETIME NULL,
  `update_user` BIGINT NULL,
  `update_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `host_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `host_detail` CASCADE ;

CREATE TABLE IF NOT EXISTS `host_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `host_id` BIGINT NULL,
  `hostname` VARCHAR(255) NULL,
  `vendor` VARCHAR(255) NULL,
  `cpu` INT NULL,
  `cores` INT NULL,
  `kernel` VARCHAR(255) NULL,
  `architecture` VARCHAR(255) NULL,
  `os` VARCHAR(255) NULL,
  `memory` BIGINT NULL,
  `swap` BIGINT NULL,
  `disk` BIGINT NULL,
  `ip_address` VARCHAR(255) NULL,
  `gateway` VARCHAR(255) NULL,
  `dns` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_host_detail_host1_idx` (`host_id` ASC),
  CONSTRAINT `fk_host_detail_host1_idx`
    FOREIGN KEY (`host_id`)
    REFERENCES `host` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `host_monitor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `host_monitor` CASCADE ;

CREATE TABLE IF NOT EXISTS `host_monitor` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `host_id` BIGINT NOT NULL,
  `type` VARCHAR(255) NOT NULL DEFAULT '5M',
  `monitor_date` DATETIME NOT NULL,
  `cpu_avg` DOUBLE NULL COMMENT 'Percentage',
  `cpu_max` DOUBLE NULL COMMENT 'Percentage',
  `mem_total` BIGINT NULL,
  `mem_avg` BIGINT NULL,
  `mem_max` BIGINT NULL,
  `mem_usage_avg` DOUBLE NULL COMMENT 'Percentage',
  `mem_usage_max` DOUBLE NULL COMMENT 'Percentage',
  `net_tx_avg` BIGINT NULL,
  `net_tx_max` BIGINT NULL,
  `net_rx_avg` BIGINT NULL,
  `net_rx_max` BIGINT NULL,
  `disk_read_avg` BIGINT NULL,
  `disk_read_max` BIGINT NULL,
  `disk_write_avg` BIGINT NULL,
  `disk_write_max` BIGINT NULL,
  `disk_usage` DOUBLE NULL COMMENT 'Path, Total, Used, Percentage가 포함된 json string이 저장된다.\n\n[{\"path\":\"/\",\"total\":499963170816,\"used\":343038304256,\"available\":149763457024,\"percentage\":70.0}]',
  INDEX `fk_host_monitor_host1_idx` (`host_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_host_monitor_host1`
    FOREIGN KEY (`host_id`)
    REFERENCES `host` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` CASCADE ;

CREATE TABLE IF NOT EXISTS `member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(255) NOT NULL,
  `password` VARCHAR(512) NOT NULL,
  `user_name` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `view_mode` VARCHAR(255) NULL DEFAULT 'dark',
  `description` VARCHAR(255) NULL,
  `last_login_date` DATETIME NULL,
  `fail_limit_count` INT NOT NULL DEFAULT 0,
  `block_yn` VARCHAR(1) NULL DEFAULT 'N',
  `delete_yn` VARCHAR(1) NULL DEFAULT 'N',
  `create_user` BIGINT NULL,
  `create_date` DATETIME NULL,
  `update_user` BIGINT NULL,
  `update_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` CASCADE ;

CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `members_roles_domains`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `members_roles_domains` CASCADE ;

CREATE TABLE IF NOT EXISTS `members_roles_domains` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  `domain_id` BIGINT NULL,
  INDEX `fk_table1_domain1_idx` (`domain_id` ASC),
  INDEX `fk_table1_member1_idx` (`member_id` ASC),
  INDEX `fk_table1_role1_idx` (`role_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_table1_domain1`
    FOREIGN KEY (`domain_id`)
    REFERENCES `domain` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Table `history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `history` CASCADE ;

CREATE TABLE IF NOT EXISTS `history` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `process_uuid` VARCHAR(255) NOT NULL,
  `code` INT NOT NULL COMMENT 'Cluster 생성, Domain 생성, Host 생성, WAS 생성, WEB 생성, Session Server 생성\nenv.sh 수정, server.xml 수정, context.xml 수정, config 수정 \n등을 코드화',
  `title` VARCHAR(255) NOT NULL,
  `wizard_id` BIGINT NULL,
  `cluster_id` BIGINT NULL,
  `domain_id` BIGINT NULL,
  `host_id` BIGINT NULL,
  `engine_id` BIGINT NULL,
  `web_app_server_id` BIGINT NULL,
  `web_server_id` BIGINT NULL,
  `session_server_id` BIGINT NULL,
  `scouter_server_id` BIGINT NULL,
  `atlassian_server_id` BIGINT NULL,
  `datasource_id` BIGINT NULL,
  `application_id` BIGINT NULL,
  `access_control_id` BIGINT NULL,
  `config_file` VARCHAR(255) NULL,
  `status_code` VARCHAR(255) NOT NULL,
  `message` LONGTEXT NULL,
  `task_user` BIGINT NULL,
  `read_yn` VARCHAR(1) NOT NULL DEFAULT 'N',
  `delete_yn` VARCHAR(1) NOT NULL DEFAULT 'N',
  `create_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;