create table details_text_data
(
    id            bigint auto_increment
        primary key,
    details_text  text         null,
    is_adoption   bit          null,
    like_number   int          null,
    port_id       bigint       null,
    user_account  varchar(66)  null,
    create_data   date         null,
    auth_flag     int          null comment '0:审核中,1已审,2:不通过',
    is_port       bit          null,
    like_account  text         null,
    reply_parent  bigint       null,
    auth_describe varchar(255) null comment '审核情况说明',
    update_time   bigint       null
);

create index account_port
    on details_text_data (user_account);

