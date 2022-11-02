INSERT INTO
    t_user_info(person_id, person_name, phone_number, wx_openid, gender, health_code_color)
VALUES
       ('35341719961123514X', '张三', '14751432158', 'oHbzp5xs8kZP0nMKMyJ6Wb0VKiNI', 0, 0),
       ('457342192208107100', '姜洋', '18133654025', 'oHbzp5yIBhroac3en14rw9zhYVcI' , 0, 0),
       ('725341186604107764', '贾芳', '18641528264', null, 1, 1),
       ('73151418061117368X', '唐秀兰', '18655727623', null, 0, 1),
       ('745221196308105087', '傅强', '18626544578', null, 1, 2);

INSERT INTO
    t_user_relation(person_id_a, person_id_b, relation)
VALUES
       ('35341719961123514X', '457342192208107100', 0),
       ('725341186604107764', '73151418061117368X', 2);

INSERT INTO
    t_nucleic_acid_info(nucleic_acid_id)
VALUES
       ('70673882276605554562'),
       ('71744404055443795171'),
       ('65742548416687554825'),
       ('14127479139855609858'),
       ('96696542038824254204'),
       ('30446016663405972408'),
       ('83228500583705034446');

INSERT INTO
    t_covid_test_institution(institution_id, institution_locate_area, institution_name)
VALUES
       ('04466936245247713632', '江西省 萍乡市 上栗县', 'A医院'),
       ('46312353322876452624', '广西壮族自治区 防城港市 上思县', 'B医院'),
       ('74280262060094563874', '海南省 三沙市 中沙群岛的岛礁及其海域', 'C医院'),
       ('65527919204620368784', '山东省 菏泽市 定陶县',  'D医院');

INSERT INTO
    t_nucleic_acid_test_info(person_id, test_time, test_institution_id, nucleic_acid_id, test_result)
VALUES
       ('35341719961123514X', '1992-07-06 15:12:09', '04466936245247713632', '70673882276605554562', '0'),
       ('457342192208107100', '1994-03-18 18:03:04', '46312353322876452624', '71744404055443795171', '1'),
       ('457342192208107100', '2008-09-24 13:56:58', '74280262060094563874', '30446016663405972408', '0');

INSERT INTO t_account(account_id, username, password,category)
VALUES ('1','admin','123456',2);