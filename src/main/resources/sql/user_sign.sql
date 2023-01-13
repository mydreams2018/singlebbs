create table user_sign
(
    id              bigint unsigned auto_increment comment '主键'
        primary key,
    user_account    varchar(18) null,
    last_sign_time  date        null,
    accumulate_sign int         null
);

create index userAccount
    on user_sign (user_account)
    comment '用户索引';

INSERT INTO user_sign (id, user_account, last_sign_time, accumulate_sign) VALUES (4, 'kungreat', '2022-11-23', 3);
INSERT INTO user_sign (id, user_account, last_sign_time, accumulate_sign) VALUES (5, 'qepau666', '2022-11-06', 1);
INSERT INTO user_sign (id, user_account, last_sign_time, accumulate_sign) VALUES (6, 'qepau668', '2022-11-30', 1);
