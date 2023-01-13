create table user_message
(
    id            bigint unsigned auto_increment
        primary key,
    class_id      int          null,
    port_id       bigint       null,
    details_id    bigint       null,
    src_alias     varchar(255) null,
    receive_alias varchar(255) null,
    receive_date  date         null,
    auth_flag     int          null comment '0:审核中,1:已审核,2:不通过'
);

