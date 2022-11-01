INSERT INTO
    t_user_info(person_id, person_name, phone_number, gender, health_code_color)
VALUES
       ('35341719961123514X', '张三', '14751432158', 0, 0),
       ('457342192208107100', '姜洋', '18133654025', 0, 0),
       ('725341186604107764', '贾芳', '18641528264', 1, 1),
       ('73151418061117368X', '唐秀兰', '18655727623', 0, 1),
       ('745221196308105087', '傅强', '18626544578', 1, 2);

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
       ('04466936245247713632', '江西省 萍乡市 上栗县', null),
       ('46312353322876452624', '广西壮族自治区 防城港市 上思县', null),
       ('74280262060094563874', '海南省 三沙市 中沙群岛的岛礁及其海域', null),
       ('65527919204620368784', '山东省 菏泽市 定陶县', null);

INSERT INTO
    t_nucleic_acid_test_info(person_id, test_time, test_institution_id, nucleic_acid_id, test_result)
VALUES ();


