create table collaboration_company
(
    id              int auto_increment comment '主键'
        primary key,
    only_status     int          null comment '1:首页面轮播展示2:区域',
    company_images  varchar(128) null comment '图片地址',
    description     varchar(255) null comment '介绍文字',
    avtive_time     datetime     null comment '激活时间',
    end_time        datetime     null comment '结束时间',
    is_active       tinyint(1)   null comment '是否激活中',
    business_people varchar(128) null comment '业务人员',
    link_url        varchar(255) null comment '链接的页面地址',
    base_order      int          null comment '排序字段'
)
    comment '合作公司,首页,详情页面展示';

INSERT INTO collaboration_company (id, only_status, company_images, description, avtive_time, end_time, is_active, business_people, link_url, base_order) VALUES (1, 2, 'https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA157Ai0.img?w=1920&h=1080&q=60&m=2&f=jpg', '死水公司', '2022-12-04 23:00:15', '2022-12-08 23:00:17', 1, 'kun', 'https://space.bilibili.com/384704339', 1);
INSERT INTO collaboration_company (id, only_status, company_images, description, avtive_time, end_time, is_active, business_people, link_url, base_order) VALUES (2, 1, '/api/userImg/collaboration/vjYLVhEV.png', '11', '2033-01-01 00:00:00', '2044-01-01 00:00:00', 0, null, '22', 20221219);
INSERT INTO collaboration_company (id, only_status, company_images, description, avtive_time, end_time, is_active, business_people, link_url, base_order) VALUES (3, 1, '/api/userImg/collaboration/vjYLVhEV.png', '11', '2033-01-01 00:00:00', '2044-01-01 00:00:00', 0, null, '22', 20221219);
INSERT INTO collaboration_company (id, only_status, company_images, description, avtive_time, end_time, is_active, business_people, link_url, base_order) VALUES (4, 1, '/api/userImg/collaboration/xXwozTVF.png', '11', '2020-12-12 00:00:00', '2022-12-16 00:00:00', 0, 'kungreat', '22', 20221219);
