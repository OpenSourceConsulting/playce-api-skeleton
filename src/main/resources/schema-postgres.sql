-- DROP SCHEMA IF EXISTS public CASCADE ;

-- CREATE SCHEMA IF NOT EXISTS public ;


-- -----------------------------------------------------
-- Table cluster
-- -----------------------------------------------------
DROP TABLE IF EXISTS cluster CASCADE ;

CREATE TABLE IF NOT EXISTS cluster (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL,
    delete_yn VARCHAR(1) NULL DEFAULT 'N',
    create_user BIGINT NULL,
    create_date TIMESTAMP NULL DEFAULT current_timestamp,
    update_user BIGINT NULL,
    update_date TIMESTAMP NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

ALTER SEQUENCE cluster_id_seq RENAME TO cluster_generator;


-- -----------------------------------------------------
-- Table domain
-- -----------------------------------------------------
DROP TABLE IF EXISTS domain CASCADE ;

CREATE TABLE IF NOT EXISTS domain (
    id BIGSERIAL NOT NULL,
    cluster_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL,
    delete_yn VARCHAR(1) NULL DEFAULT 'N',
    create_user BIGINT NULL,
    create_date TIMESTAMP NULL DEFAULT current_timestamp,
    update_user BIGINT NULL,
    update_date TIMESTAMP NULL DEFAULT current_timestamp,
    PRIMARY KEY (id),
    CONSTRAINT fk_domain_cluster1
    FOREIGN KEY (cluster_id)
    REFERENCES cluster (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX domain_cluster_id_index ON domain (cluster_id ASC);

ALTER SEQUENCE domain_id_seq RENAME TO domain_generator;


-- -----------------------------------------------------
-- Table host
-- -----------------------------------------------------
DROP TABLE IF EXISTS host CASCADE ;

CREATE TABLE IF NOT EXISTS host (
    id BIGSERIAL NOT NULL,
    scouter_server_id BIGINT NULL,
    name VARCHAR(255) NOT NULL,
    ip_address VARCHAR(255) NOT NULL,
    port INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(512) NULL,
    sudoer_yn VARCHAR(1) NULL,
    agent_install_path VARCHAR(255) NULL,
    description VARCHAR(255) NULL,
    key_file_path VARCHAR(255) NULL,
    key_string VARCHAR(4096) NULL,
    monitoring_yn VARCHAR(1) NOT NULL DEFAULT 'Y',
    delete_yn VARCHAR(1) NOT NULL DEFAULT 'N',
    create_user BIGINT NULL,
    create_date TIMESTAMP NULL DEFAULT current_timestamp,
    update_user BIGINT NULL,
    update_date TIMESTAMP NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

ALTER SEQUENCE host_id_seq RENAME TO host_generator;


-- -----------------------------------------------------
-- Table host_detail
-- -----------------------------------------------------
DROP TABLE IF EXISTS host_detail CASCADE ;

CREATE TABLE IF NOT EXISTS host_detail (
    id BIGSERIAL NOT NULL,
    host_id BIGINT NULL,
    hostname VARCHAR(255) NULL,
    vendor VARCHAR(255) NULL,
    cpu INT NULL,
    cores INT NULL,
    kernel VARCHAR(255) NULL,
    architecture VARCHAR(255) NULL,
    os VARCHAR(255) NULL,
    memory BIGINT NULL,
    swap BIGINT NULL,
    disk BIGINT NULL,
    ip_address VARCHAR(255) NULL,
    gateway VARCHAR(255) NULL,
    dns VARCHAR(255) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_host_detail_host1_idx
    FOREIGN KEY (host_id)
    REFERENCES host (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX host_detail_host_id_index ON host_detail (host_id ASC);

ALTER SEQUENCE host_detail_id_seq RENAME TO hd_generator;


-- -----------------------------------------------------
-- Table host_monitor
-- -----------------------------------------------------
DROP TABLE IF EXISTS host_monitor CASCADE ;

CREATE TABLE IF NOT EXISTS host_monitor (
    id BIGSERIAL NOT NULL,
    host_id BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL DEFAULT '5M',
    monitor_date TIMESTAMP NOT NULL,
    cpu_avg DOUBLE PRECISION NULL,
    cpu_max DOUBLE PRECISION NULL,
    mem_total BIGINT NULL,
    mem_avg BIGINT NULL,
    mem_max BIGINT NULL,
    mem_usage_avg DOUBLE PRECISION NULL,
    mem_usage_max DOUBLE PRECISION NULL,
    net_tx_avg BIGINT NULL,
    net_tx_max BIGINT NULL,
    net_rx_avg BIGINT NULL,
    net_rx_max BIGINT NULL,
    disk_read_avg BIGINT NULL,
    disk_read_max BIGINT NULL,
    disk_write_avg BIGINT NULL,
    disk_write_max BIGINT NULL,
    disk_usage DOUBLE PRECISION NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_host_monitor_host1
    FOREIGN KEY (host_id)
    REFERENCES host (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX host_monitor_host_id_index ON host_monitor (host_id ASC);

ALTER SEQUENCE host_monitor_id_seq RENAME TO host_monitor_generator;


-- -----------------------------------------------------
-- Table member
-- -----------------------------------------------------
DROP TABLE IF EXISTS member CASCADE ;

CREATE TABLE IF NOT EXISTS member (
    id BIGSERIAL NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    password VARCHAR(512) NOT NULL,
    user_name VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    view_mode VARCHAR(255) NULL DEFAULT 'dark',
    description VARCHAR(255) NULL,
    last_login_date TIMESTAMP NULL,
    fail_limit_count INT NOT NULL DEFAULT 0,
    block_yn VARCHAR(1) NULL DEFAULT 'N',
    delete_yn VARCHAR(1) NULL DEFAULT 'N',
    create_user BIGINT NULL,
    create_date TIMESTAMP NULL DEFAULT current_timestamp,
    update_user BIGINT NULL,
    update_date TIMESTAMP NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

ALTER SEQUENCE member_id_seq RENAME TO member_generator;


-- -----------------------------------------------------
-- Table role
-- -----------------------------------------------------
DROP TABLE IF EXISTS role CASCADE ;

CREATE TABLE IF NOT EXISTS role (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER SEQUENCE role_id_seq RENAME TO role_generator;


-- -----------------------------------------------------
-- Table members_roles_domains
-- -----------------------------------------------------
DROP TABLE IF EXISTS members_roles_domains CASCADE ;

CREATE TABLE IF NOT EXISTS members_roles_domains (
    id BIGSERIAL NOT NULL,
    member_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    domain_id BIGINT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_table1_domain1
    FOREIGN KEY (domain_id)
    REFERENCES domain (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_table1_member1
    FOREIGN KEY (member_id)
    REFERENCES member (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_table1_role1
    FOREIGN KEY (role_id)
    REFERENCES role (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX members_roles_domains_domain_id_index ON members_roles_domains (domain_id ASC);
CREATE INDEX members_roles_domains_member_id_index ON members_roles_domains (member_id ASC);
CREATE INDEX members_roles_domains_role_id_index ON members_roles_domains (role_id ASC);

ALTER SEQUENCE members_roles_domains_id_seq RENAME TO members_roles_domains_generator;

-- -----------------------------------------------------
-- Table history
-- -----------------------------------------------------
DROP TABLE IF EXISTS history CASCADE ;

CREATE TABLE IF NOT EXISTS history (
    id BIGSERIAL NOT NULL,
    process_uuid VARCHAR(255) NOT NULL,
    code INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    wizard_id BIGINT NULL,
    cluster_id BIGINT NULL,
    domain_id BIGINT NULL,
    host_id BIGINT NULL,
    engine_id BIGINT NULL,
    web_app_server_id BIGINT NULL,
    web_server_id BIGINT NULL,
    session_server_id BIGINT NULL,
    scouter_server_id BIGINT NULL,
    atlassian_server_id BIGINT NULL,
    datasource_id BIGINT NULL,
    application_id BIGINT NULL,
    access_control_id BIGINT NULL,
    config_file VARCHAR(255) NULL,
    status_code VARCHAR(255) NOT NULL,
    message TEXT NULL,
    task_user BIGINT NULL,
    read_yn VARCHAR(1) NOT NULL DEFAULT 'N',
    delete_yn VARCHAR(1) NOT NULL DEFAULT 'N',
    create_date TIMESTAMP NULL DEFAULT current_timestamp,
    end_date TIMESTAMP NULL,
    PRIMARY KEY (id)
);

ALTER SEQUENCE history_id_seq RENAME TO history_generator;