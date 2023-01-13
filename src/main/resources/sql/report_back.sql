create table report_back
(
    id             bigint auto_increment comment '主键'
        primary key,
    port_title     varchar(128) null,
    useides        varchar(66)  null,
    experience     tinyint      null,
    user_account   varchar(66)  null,
    is_essence     bit          null,
    port_state     varchar(6)   null,
    is_top         bit          null,
    reply_number   tinyint      null,
    look_number    tinyint      null,
    create_time    date         null,
    auth_flag      int          null comment '0:审核中,1已审,2:不通过',
    partition_name varchar(126) null comment '分区',
    auth_describe  varchar(255) null comment '贴子审核情况说明',
    update_time    bigint       null comment '更新时间毫秒值'
);

create index account_port
    on report_back (user_account);

INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (12, '机会来到、', null, 0, 'kungreat', false, '已结', false, 29, 2, '2022-07-19', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (13, '这是我发的贴子', null, 0, 'qepau666', false, '已结', false, 1, 2, '2022-07-19', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (14, 'ttttttttttt', null, 0, 'qepau666', false, '未结', false, 1, 2, '2022-07-19', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (15, '赏积分了', null, 5, 'kungreat', false, '已结', false, 6, 2, '2022-08-07', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (16, '就在这里等你', null, 5, 'kungreat', false, '未结', false, 4, 1, '2022-09-06', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (17, '111', null, 0, 'qepau666', false, '未结', false, 0, 0, '2022-10-05', 1, 'j第1章', null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (18, '测试代码块是否正常', null, 0, 'qepau666', false, '已结', true, 4, 1, '2022-10-07', 1, null, 'text', null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (19, '你这是老学生吗', null, 2, 'qepau666', false, '已结', false, 3, 1, '2022-10-16', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (20, '11111', null, 2, 'qepau666', false, '未结', false, 0, 0, '2022-10-30', 1, null, null, null);
INSERT INTO report_back (id, port_title, useides, experience, user_account, is_essence, port_state, is_top, reply_number, look_number, create_time, auth_flag, partition_name, auth_describe, update_time) VALUES (21, '测试用户删除', null, 0, 'qepau668', false, '未结', false, 0, 0, '2022-11-24', 1, null, null, null);
