-- 用户样例信息
INSERT INTO
    t_user_info(person_id, person_name, phone_number, wx_openid, gender, health_code_color)
VALUES
       ('35341719961123514X', '张三', '14751432158', 'oHbzp5xs8kZP0nMKMyJ6Wb0VKiNI', 0, 0),
       ('457342192208107100', '姜洋', '18133654025', 'oHbzp5yIBhroac3en14rw9zhYVcI' , 0, 0),
       ('725341186604107764', '贾芳', '18641528264', null, 1, 1),
       ('73151418061117368X', '唐秀兰', '18655727623', null, 0, 1),
       ('745221196308105087', '傅强', '18626544578', null, 1, 2),
       ('341435234546546544', 'fy','13317278715','oHbzp53Ax8GW3td4vmkV2Pp3H904',0,0);

-- 用户关系样例信息
INSERT INTO
    t_user_relation(person_id_a, person_id_b, relation)
VALUES
       ('35341719961123514X', '457342192208107100', 0),
       ('725341186604107764', '73151418061117368X', 2);

-- 疫苗检测信息
INSERT INTO
    t_covid_test_institution(institution_id, institution_locate_area, institution_name)
VALUES
       ('04466936245247713632', '江西省 萍乡市 上栗县', 'A医院'),
       ('46312353322876452624', '广西壮族自治区 防城港市 上思县', 'B医院'),
       ('74280262060094563874', '海南省 三沙市 中沙群岛的岛礁及其海域', 'C医院'),
       ('65527919204620368784', '山东省 菏泽市 定陶县',  'D医院');

INSERT INTO t_transfer_code_info(transfer_code, tester_open_id, test_time, person_number, is_transferred)
VALUES
    ('JSON123456789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON112322789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON145674789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON123789989','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON112333789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON154777789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON128579789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON124698789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON122537789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON128749789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0),
    ('JSON122543789','ojKoj52igjq_xw7MpIKZ4LUZJnH8','2022-11-13 16:58:14',1,0);

-- 核酸检测信息
INSERT INTO
    t_nucleic_acid_test_info(person_id, test_time, test_institution_id, transfer_code, test_result)
VALUES
       ('35341719961123514X', '1992-07-06 15:12:09', '04466936245247713632', 'JSON123456789', '0'),
       ('457342192208107100', '1994-03-18 18:03:04', '46312353322876452624', 'JSON112322789', '1'),
       ('457342192208107100', '2008-09-24 13:56:58', '74280262060094563874', 'JSON154777789', '0'),
       ('457342192208107100', '2021-03-02 14:14:14', '46312353322876452624', 'JSON122543789', '0');

-- 管理员信息
INSERT INTO
    t_account(account_id, username, password,category)
VALUES
       ('1','admin','123456',2);

-- 疫苗接种信息
INSERT INTO
    t_vaccine_inoculation_info(person_id, inoculation_time, inoculation_facility, vaccine_name, inoculation_number)
VALUES
       ('457342192208107100', '2008-01-15 08:06:34', '治之水', '么关最', 1),
       ('457342192208107100', '1990-08-25 09:41:56', '日问儿政龙按', '组队机美表', 2),
       ('341435234546546544', '2021-03-02 14:14:14', '治之水','么关最',1),
       ('341435234546546544', '2021-03-01 14:14:14', '治之水','么关最',2);

-- 核酸检测人员身份认证信息
INSERT INTO
    t_identity_application(application_id, applicant_name, applicant_person_id, applicant_phone, additional_information, apply_type, is_processed, is_succeed, result_info)
VALUES
    ('341435234546546544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341435234236546544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('343455231546546544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341435234516136544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341435434546546544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341435634546546544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341436634546543544','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341435234546542344','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341433234546523442','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341434234543425124','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null),
    ('341455234546542344','fy','341435234546546544','13317278716','2428935897293745',0,0,0,null);



INSERT INTO
    t_tester(open_id, person_id, name, phone)
VALUES
    ('ojKoj52igjq_xw7MpIKZ4LUZJnH8','341435234546546544','fy',14726539856),
    ('oHuj10yIBhfyjn14rw9gskjsajkiI','321656489621646658','孙权',13317278715),
    ('oHujp9yIBhfyjn14rw9gskjsajkiI','341435234546546544','曹孟德',13546879568),
    ('oHujp6yIBhfyjn14rw9gskjsajkiI','654653426845625632','董卓',16578946253);



INSERT INTO
    t_abnormal_info_application(application_id, person_name, person_phone, additional_information, type, is_investigated, is_processed, result)
VALUES
       ('341414893546542344', 'fy', '114514514', null, '健康码', 0, 0, null);