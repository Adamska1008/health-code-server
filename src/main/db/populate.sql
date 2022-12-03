-- 用户样例信息
INSERT INTO
    t_user_info(person_id, person_name, phone_number, position, wx_openid, gender, health_code_color)
VALUES
       ('35341719961123514X', '张三', '14751432158', '湖南省:长沙市:岳麓区','oHbzp5xs8kZP0nMKMyJ6Wb0VKiNI', 0, 0),
       ('457342192208107100', '姜洋', '18133654025', '湖南省:长沙市:芙蓉区','oHbzp5yIBhroac3en14rw9zhYVcI' , 0, 0),
       ('725341186604107764', '贾芳', '18641528264','湖南省:长沙市:天心区', null, 1, 1),
       ('73151418061117368X', '唐秀兰', '18655727623','湖南省:长沙市:开福区', null, 0, 1),
       ('745221196308105087', '傅强', '18626544578','湖南省:长沙市:雨花区', null, 1, 2),
       ('341435234546546544', 'fy','13317278715','湖南省:长沙市:岳麓区','oHbzp53Ax8GW3td4vmkV2Pp3H904',0,0);

-- 用户关系样例信息
INSERT INTO
    t_user_relation(person_id_a, person_id_b, relation)
VALUES
       ('35341719961123514X', '457342192208107100', 0),
       ('725341186604107764', '73151418061117368X', 2);

-- institution_locate_area同样需要满足 :: 结构，具体根据查看地图api的情况进行修改
-- 疫苗检测信息
INSERT INTO
    t_covid_test_institution(institution_id, institution_locate_area, institution_name)
VALUES
       ('04466936245247713632', '江西省:萍乡市:上栗县', 'A医院'),
       ('46312353322876452624', '广西壮族自治区:防城港市:上思县', 'B医院'),
       ('74280262060094563874', '海南省:三沙市:中沙群岛的岛礁及其海域', 'C医院'),
       ('65527919204620368784', '山东省:菏泽市:定陶县',  'D医院');

-- 转运码信息
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


-- 核酸测试人员信息
INSERT INTO
    t_tester(open_id, person_id, name, phone)
VALUES
    ('ojKoj52igjq_xw7MpIKZ4LUZJnH8','341435234546546544','fy',14726539856),
    ('oHuj10yIBhfyjn14rw9gskjsajkiI','321656489621646658','孙权',13317278715),
    ('oHujp9yIBhfyjn14rw9gskjsajkiI','341435234546546544','曹孟德',13546879568),
    ('oHujp6yIBhfyjn14rw9gskjsajkiI','654653426845625632','董卓',16578946253);


-- 异常信息申诉表
INSERT INTO
    t_abnormal_info_application(application_id, person_name, person_phone, additional_information, type, is_investigated, is_processed, result)
VALUES
       ('341414893546542344', 'fy', '114514514', null, '健康码', 0, 0, null);

-- 异地报备
INSERT INTO
    t_remote_reporting(report_id, person_name, person_id, img_url, _from, _to, is_checked, is_allowed, additional_info)
VALUES
       ('413687893546542344', 'fy', '341435234546546544', '', '湖南省','湖北省', 0, 0, null),
       ('1596867897987297281', '姜洋', '457342192208107100', '', '河南省', '北京市', 0, 0, null),
       ('1597117024788553730', '姜洋', '457342192208107100', '','北京市', '湖南省', 0, 0, null);

-- 绑定家属健康码
INSERT INTO
    t_bind_family_application(application_id, applicant_name, applicant_person_id, relative_name, relative_person_id, additional_information, relation_type, is_processed, is_succeed, result_info)
VALUES
       ('1597569829328482306', 'fy', '341435234546546544', '姜洋', '457342192208107100', null, 0, 0, 0, null),
       ('413676711737197344', 'abc', '49878464876376487','efg', '182347486976884025', null, 1, 0, 0, null),
       ('534676711737197344', 'yq', '6543378348768467' ,'cv', '4123474987840257', null, 2, 0, 0, null);


-- 场所码申请
INSERT INTO
    t_venue_code_application(application_id, applicant_name, applicant_person_id, position, location, type, place_name, is_solved, is_passed, result_info, venue_id)
VALUES
       ('1234687897161', 'fy', '341435234546546544', '湖南省:长沙市:岳麓区', '', '饭店', 'A饭店', 0, 0, null, null),
       ('4684861378766', '姜洋', '457342192208107100', '湖南省:长沙市:岳麓区', '阜埠河路', '饭店', 'B饭店', 1, 1, null, null);

-- 场所码表
INSERT INTO
    t_venue_code_info(code_id, venue_name, venue_type, venue_position, venue_location)
VALUES
       ('67681357681345', 'B饭店', '饭店', '湖南省:长沙市:岳麓区', '阜埠河路');

-- 行程信息
INSERT INTO
    t_itinerary_information(person_id, venue_id, record_time)
VALUES
       ('457342192208107100', '67681357681345', '2022-8-9 12:13:14');

-- 采集点信息
INSERT INTO
    t_collection_point_information(collection_point_id, collection_point_position, collection_point_location, collection_point_institution, collection_point_principal, collection_point_contact_phone)
VALUES
       ('1597569829328482306', '湖南省:长沙市:岳麓区', '湖南大学天马学生公寓', '篮球场', '负责人', '18157815593');