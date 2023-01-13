create table user_reply_port
(
    id           bigint unsigned auto_increment
        primary key,
    user_account varchar(255) null,
    reply_year   int          null,
    reply_week   int          null,
    reply_number int          null
);

create index userAccount
    on user_reply_port (user_account);

INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (8, 'kungreat', 2022, 29, 4);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (9, 'kungreat', 2022, 30, 6);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (10, 'qepau666', 2022, 30, 3);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (11, 'kungreat', 2022, 31, 14);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (12, 'qepau666', 2022, 31, 7);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (13, 'qepau666', 2022, 32, 2);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (14, 'kungreat', 2022, 33, 6);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (15, 'kungreat', 2022, 37, 3);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (16, 'qepau666', 2022, 38, 1);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (17, 'qepau666', 2022, 41, 1);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (18, 'qepau666', 2022, 43, 5);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (19, 'qepau666', 2022, 44, 4);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (20, 'qepau666', 2022, 46, 2);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (21, 'kungreat', 2022, 48, 1);
INSERT INTO user_reply_port (id, user_account, reply_year, reply_week, reply_number) VALUES (22, 'qepau668', 2022, 49, 1);
