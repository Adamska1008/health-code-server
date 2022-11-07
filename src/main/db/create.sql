DROP DATABASE healthcode;
CREATE DATABASE healthcode;
USE healthcode;

DROP TABLE t_user_info;
-- gender 0:男 1:女
-- health_code_color 0:绿 1:黄 2:红
CREATE TABLE IF NOT EXISTS t_user_info (
    person_id           CHAR(20)    NOT NULL,
    person_name         CHAR(20),
    phone_number        CHAR(20),
    wx_openid           CHAR(40),
    gender              TINYINT     CHECK(gender IN(0, 1)),
    health_code_color   TINYINT    	CHECK (health_code_color IN (0,1,2)),
    PRIMARY KEY (person_id)
)DEFAULT CHARSET=utf8mb4;

DROP TABLE t_user_relation;
-- 用户关系表
CREATE TABLE IF NOT EXISTS t_user_relation (
    person_id_a     CHAR(20)    NOT NULL,
    person_id_b     CHAR(20)    NOT NULL,
    relation        TINYINT    	,
    PRIMARY KEY(person_id_a, person_id_b),
    FOREIGN KEY(person_id_a) REFERENCES t_user_info(person_id),
    FOREIGN KEY(person_id_b) REFERENCES t_user_info(person_id)
)DEFAULT CHARSET=utf8mb4;

-- 核酸检测试剂表
CREATE TABLE IF NOT EXISTS t_nucleic_acid_info(
    nucleic_acid_id         CHAR(20)    NOT NULL,
    PRIMARY KEY(nucleic_acid_id)
)DEFAULT CHARSET=utf8mb4;

DROP TABLE t_nucleic_acid_test_info;
-- 核酸检测结果表
-- test_result 0:阴性 1:阳性
CREATE TABLE IF NOT EXISTS t_nucleic_acid_test_info (
    person_id           	CHAR(20)    NOT NULL,
    test_time 	          	DATETIME    ,
    test_institution_id    	CHAR(20)    ,
    nucleic_acid_id        	CHAR(20)    NOT NULL,
    test_result         	TINYINT    	CHECK(test_result IN(0,1)),
    PRIMARY KEY(person_id, nucleic_acid_id),
    FOREIGN KEY(person_id) REFERENCES t_user_info(person_id),
    FOREIGN KEY(nucleic_acid_id) REFERENCES  t_nucleic_acid_info(nucleic_acid_id)
)DEFAULT CHARSET=utf8mb4;

DROP TABLE t_vaccine_inoculation_info;
-- 疫苗接种表
CREATE TABLE IF NOT EXISTS t_vaccine_inoculation_info(
    person_id               CHAR(20)    NOT NULL,
    inoculation_time        DATETIME    ,
    inoculation_facility    CHAR(20)    ,
    vaccine_name            CHAR(20)    ,
    inoculation_number      CHAR(20)    NOT NULL,
    PRIMARY KEY(person_id, inoculation_number)
)DEFAULT CHARSET=utf8mb4;

-- 核酸检测机构
CREATE table  IF NOT EXISTS t_covid_test_institution(
    institution_id          CHAR(20)    NOT NULL,
    institution_locate_area VARCHAR(40) ,
    institution_name        VARCHAR(40) ,
    PRIMARY KEY (institution_id)
)DEFAULT CHARSET=utf8mb4;

-- 场所码信息
CREATE table IF NOT EXISTS t_venue_code_info(
    code_id             CHAR(20)    NOT NULL,
    venue_type          VARCHAR(10) ,
    venue_locate_area   VARCHAR(40) ,
    venue_locate_type   VARCHAR(10) ,
    venue_name          VARCHAR(40) ,
    PRIMARY KEY (code_id)
)DEFAULT CHARSET=utf8mb4;

-- 场所码申请信息
-- is_solved 0: 未处理 1:已处理
-- code_application_result 0: 申请成功 1:申请失败
CREATE table IF NOT EXISTS t_venue_code_application(
    code_application_id     CHAR(20)    NOT NULL,
    code_application_person VARCHAR(10) ,
    code_application_locate VARCHAR(40) ,
    code_application_type   VARCHAR(10) ,
    code_application_name   VARCHAR(40) ,
    is_solved               TINYINT CHECK ( is_solved IN (0,1)),
    code_application_result TINYINT CHECK ( code_application_result IN (0,1)),
    result_info             VARCHAR(100),
    PRIMARY KEY (code_application_id)
)DEFAULT CHARSET=utf8mb4;

-- 行程信息
CREATE table IF NOT EXISTS t_itinerary_information(
    person_id           CHAR(20) DEFAULT '',
    venue_id            CHAR(20) DEFAULT '',
    record_time         TIMESTAMP,
    PRIMARY KEY (person_id,venue_id,record_time),
    FOREIGN KEY (person_id) REFERENCES t_user_info(person_id),
    FOREIGN KEY (venue_id) REFERENCES t_venue_code_info(code_id)
)DEFAULT CHARSET=utf8mb4;

#risk_level 0:低 1:中 2:高 3:常态化
CREATE TABLE IF NOT EXISTS t_regional_risk_profile(
    profile_id 			CHAR(10) 	NOT NULL,
    province 			VARCHAR(10),
    city 				VARCHAR(20),
    district 			VARCHAR(30),
    risk_level 			TINYINT CHECK ( risk_level IN (0,1,2,3)),
    high_risk_number 	INTEGER,
    general_risk_number INTEGER,
    red_code_number 	INTEGER,
    yellow_code_number 	INTEGER,
    infected_number 	INTEGER,
    PRIMARY KEY (profile_id)
)DEFAULT CHARSET=utf8mb4;

#risk_level 0:低 1:中 2:高 3:常态化
CREATE TABLE IF NOT EXISTS t_daily_risk_situation(
    situation_id                    CHAR(10)    NOT NULL ,
    profile_id                      CHAR(10),
    curr_date                       DATE,
    risk_level                      TINYINT     CHECK ( risk_level IN (0,1,2,3)),
    detection_point_number          INTEGER,
    yesterday_addition_positive     INTEGER,
    yesterday_addition_asymptomatic INTEGER,
    PRIMARY KEY (situation_id),
    FOREIGN KEY (profile_id) REFERENCES t_regional_risk_profile(profile_id)
)DEFAULT CHARSET=utf8mb4;

DROP TABLE t_account;
#category 0:用户 1:核酸检测人员 2:防疫管理人员
CREATE TABLE IF NOT EXISTS t_account(
    account_id CHAR(20) NOT NULL ,
    username VARCHAR(20),
    password VARCHAR(20),
    category TINYINT(10) CHECK ( category IN (0,1,2)),
    PRIMARY KEY (account_id)
)DEFAULT CHARSET=utf8mb4;

DROP TABLE t_tester;
#核酸检测人员表
CREATE TABLE IF NOT EXISTS t_tester(
    open_id     CHAR(20)        NOT NULL,
    person_id   VARCHAR(20)     NOT NULL,
    `name`      VARCHAR(20)     ,
    phone       VARCHAR(20)     ,
    PRIMARY KEY (open_id)
);

# is_investigated 0:已审核 1:未审核
# is_processed 0:已处理 1:未处理
CREATE TABLE IF NOT EXISTS t_abnormal_info_appeal_investigate(
    appeal_number CHAR(10) NOT NULL ,
    claimant_name VARCHAR(10),
    claimant_telephone VARCHAR(20),
    appeal_information VARCHAR(50),
    appeal_type VARCHAR(10),
    is_investigated TINYINT CHECK ( is_investigated IN (0,1)),
    is_processed TINYINT CHECK ( is_processed IN (0,1)),
    appeal_result VARCHAR(20) ,
    PRIMARY KEY (appeal_number)
)DEFAULT CHARSET=utf8mb4;

#apply_type 0:核酸检测人员 1:防疫管理人员
# is_succeed 0:申请失败 1:申请成功
# is_processed 0:已处理 1:未处理
# 方便起见如果是核酸检测人员申请，那么additional_information字段保存申请人身份证号
CREATE TABLE IF NOT EXISTS t_identity_application(
    application_id          CHAR(20)  NOT NULL,
    applicant_name          CHAR(20)  NOT NULL,
    applicant_person_id     CHAR(20)  NOT NULL,
    applicant_phone         CHAR(20)  NOT NULL,
    additional_information  VARCHAR(50)  NOT NULL,
    apply_type              TINYINT  CHECK ( apply_type in (0,1)),
    is_processed            TINYINT CHECK ( is_processed IN (0,1)),
    is_succeed              TINYINT CHECK ( is_succeed IN (0,1)),
    result_info             VARCHAR(50),
    PRIMARY KEY(application_id)
)DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS t_audit_family_code  (
    application_id char(20)  NOT NULL,
    applicant_name char(20)  NOT NULL,
    additional_information varchar(50)  NOT NULL,
    appeal_type char(20)  NOT NULL,
    is_processed TINYINT CHECK ( is_processed IN (0,1)),
    is_succeed TINYINT CHECK ( is_succeed IN (0,1)),
    result_info VARCHAR(50),
    PRIMARY KEY(application_id)
)DEFAULT CHARSET=utf8mb4;

# is_investigated 0:已审核 1:未审核
# is_processed 0:已处理 1:未处理
CREATE TABLE IF NOT EXISTS t_audit_journey_information  (
    application_id char(20)  NOT NULL,
    applicant_name char(20)  NOT NULL,
    additional_information VARCHAR(50)  NOT NULL,
    appeal_type char(20)  NOT NULL,
    is_investigated char(20)  NOT NULL CHECK(is_investigated IN(0,1)),
    is_processed TINYINT CHECK ( is_processed IN (0,1)),
    appeal_result VARCHAR(20),
    PRIMARY KEY(application_id) 
)DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_collectionpoint_information (
	collectionpoint_id            CHAR(20)    NOT NULL,
    collectionpoint_position      VARCHAR(20) NOT NULL,
    collectionpoint_institution   VARCHAR(20) NOT NULL,
    collectionpoint_principal     CHAR(20)    NOT NULL,
    collectionpoint_contact       CHAR(20)    NOT NULL,
    PRIMARY KEY (collectionpoint_id)
)DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_policy_information (
    policy_id                     CHAR(20) NOT NULL,
    release_time                  DATETIME NOT NULL,
    principal                     VARCHAR(10) NOT NULL,
    policy_region                 CHAR(20) NOT NULL,
    policy_institution            CHAR(20) NOT NULL,
    policy_title                  CHAR(20) NOT NULL,
    policy_link                   CHAR(20) NOT NULL,
    PRIMARY KEY (policy_id)
)DEFAULT CHARSET=utf8mb4;