create table user_collect
(
    id           bigint auto_increment
        primary key,
    user_account varchar(255) null,
    class_id     int          null,
    port_id      bigint       null,
    collect_time date         null,
    port_title   varchar(255) null
);

INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (27, 'kungreat', 1, 14, '2022-07-28', 'ttttttttttt');
INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (33, 'kungreat', 1, 15, '2022-08-07', '赏积分了');
INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (35, 'kungreat', 1, 12, '2022-09-06', '机会来到、');
INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (38, 'qepau666', 2, 5, '2022-09-12', '组件的思想还是很好的');
INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (43, 'kungreat', 2, 5, '2022-11-22', '组件的思想还是很好的');
INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (44, 'kungreat', 1, 16, '2022-11-23', '就在这里等你');
INSERT INTO user_collect (id, user_account, class_id, port_id, collect_time, port_title) VALUES (45, 'qepau668', 1, 19, '2022-11-28', '你这是老学生吗');
