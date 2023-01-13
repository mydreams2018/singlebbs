create table user
(
    id                bigint auto_increment
        primary key,
    account           varchar(12) collate utf8mb4_bin  not null comment '用户',
    password          varchar(60) collate utf8mb4_bin  null,
    alias             varchar(6) collate utf8mb4_bin   not null comment '别名',
    phone             bigint                           null comment '电话',
    img               varchar(188) collate utf8mb4_bin null comment '图片',
    state             tinyint                          null,
    email             varchar(136) collate utf8mb4_bin null,
    description       varchar(255) collate utf8mb4_bin null,
    register_time     datetime                         null,
    vip_level         tinyint                          null,
    origin_from       varchar(66) collate utf8mb4_bin  null,
    register_year     smallint                         null,
    accumulate_points int                              null comment '飞吻',
    is_manager        bit                              null,
    from_city         varchar(16) collate utf8mb4_bin  null,
    authenticate      varchar(255) collate utf8mb4_bin null,
    constraint alias
        unique (alias),
    constraint uni
        unique (account)
)
    collate = utf8_bin;

INSERT INTO user (id, account, password, alias, phone, img, state, email, description, register_time, vip_level, origin_from, register_year, accumulate_points, is_manager, from_city, authenticate) VALUES (1, 'qepau888', '123456', '版主', null, '/api/userImg/default.jpg', 1, null, null, '2021-01-16 21:18:47', 0, 'default', 2021, 0, false, null, null);
INSERT INTO user (id, account, password, alias, phone, img, state, email, description, register_time, vip_level, origin_from, register_year, accumulate_points, is_manager, from_city, authenticate) VALUES (2, '5e33bc5ca35', '$2a$10$jjIaWj1l/Keo9sAr7vo8.ucqEaXAbl2LP6eKD2y2ANkN5wT8fmF.K', '死水', null, 'http://qzapp.qlogo.cn/qzapp/101923653/6B19A5599D302141FF504640BAE2BEE4/50', 1, null, null, '2021-01-16 21:56:19', 0, 'qq', 2021, 0, false, null, null);
INSERT INTO user (id, account, password, alias, phone, img, state, email, description, register_time, vip_level, origin_from, register_year, accumulate_points, is_manager, from_city, authenticate) VALUES (3, 'kungreat', '$2a$10$rEstCtaDP.Mrgk0wCU1yjetI1K9MfOkdsNczfIh/IdvCC85svRp/2', '刘大胖', null, '/api/userImg/user/kungreat.jpeg', 1, '$2a$10$mf5SyuRReMCIGEwX6bI7AeKO0YVP0fMnCkKFp6IMpJOdk/Tq5lIzy', '现在的技术发展、正在抹杀创新.yes', '2021-05-27 21:54:23', 1, 'default', 2021, 75, true, '地球', '刘大胖');
INSERT INTO user (id, account, password, alias, phone, img, state, email, description, register_time, vip_level, origin_from, register_year, accumulate_points, is_manager, from_city, authenticate) VALUES (16, 'qepau666', '$2a$10$rEstCtaDP.Mrgk0wCU1yjetI1K9MfOkdsNczfIh/IdvCC85svRp/2', '大胖111', null, '/api/userImg/user/qepau666.jpeg', 1, '$2a$10$dI6Y7JYX3u0m.VAMlZChE.chGf9hX2yQ5HdyDKVR5CMHX202H3z6S', '现在的技术发展、正在抹杀创新、有没有未来', '2022-07-09 10:57:57', 1, 'default', 2022, 83, false, '地球', null);
INSERT INTO user (id, account, password, alias, phone, img, state, email, description, register_time, vip_level, origin_from, register_year, accumulate_points, is_manager, from_city, authenticate) VALUES (17, 'qepau667', '$2a$10$rEstCtaDP.Mrgk0wCU1yjetI1K9MfOkdsNczfIh/IdvCC85svRp/2', '大胖222', null, '/api/userImg/user/qepau666.jpeg', 1, '$2a$10$dI6Y7JYX3u0m.VAMlZChE.chGf9hX2yQ5HdyDKVR5CMHX202H3z6S', '现在的技术发展、正在抹杀创新、有没有未来', '2022-07-09 10:57:57', 1, 'default', 2022, 83, true, '地球', null);
INSERT INTO user (id, account, password, alias, phone, img, state, email, description, register_time, vip_level, origin_from, register_year, accumulate_points, is_manager, from_city, authenticate) VALUES (18, 'qepau668', '$2a$10$rEstCtaDP.Mrgk0wCU1yjetI1K9MfOkdsNczfIh/IdvCC85svRp/2', '大胖333', null, '/api/userImg/user/qepau666.jpeg', 1, '$2a$10$dI6Y7JYX3u0m.VAMlZChE.chGf9hX2yQ5HdyDKVR5CMHX202H3z6S', '现在的技术发展、正在抹杀创新、有没有未来', '2022-07-09 10:57:57', 1, 'default', 2022, 93, true, '地球', null);
