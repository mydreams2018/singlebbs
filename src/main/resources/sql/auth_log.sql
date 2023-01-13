create table auth_log
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    auth_account varchar(64) null comment '审核用户',
    port_id      bigint      null comment '贴子ID',
    port_type    int         null comment '1:主贴、2:回贴',
    auth_date    bigint      null comment '审核时间毫秒数',
    auth_flag    int         null,
    class_id     int         null
)
    comment '审核日志,贴子、回贴';

INSERT INTO auth_log (id, auth_account, port_id, port_type, auth_date, auth_flag, class_id) VALUES (1, 'kungreat', 19, 1, 1669195882637, 2, 1);
INSERT INTO auth_log (id, auth_account, port_id, port_type, auth_date, auth_flag, class_id) VALUES (2, 'kungreat', 91, 2, 1669195922141, 2, 1);
INSERT INTO auth_log (id, auth_account, port_id, port_type, auth_date, auth_flag, class_id) VALUES (3, 'kungreat', 19, 1, 1669196040107, 1, 1);
INSERT INTO auth_log (id, auth_account, port_id, port_type, auth_date, auth_flag, class_id) VALUES (4, 'kungreat', 91, 2, 1669196053430, 1, 1);
