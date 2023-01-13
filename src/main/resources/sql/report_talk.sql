create table report_talk
(
    id             bigint auto_increment
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
    partition_name varchar(125) null,
    auth_describe  varchar(255) null comment '审核情况说明',
    update_time    bigint       null
);

create index account_port
    on report_talk (user_account);

