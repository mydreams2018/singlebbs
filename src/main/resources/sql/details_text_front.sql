create table details_text_front
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
    on details_text_front (user_account);

INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (6, '<ul><li>Angular.js：出来最早的前端框架，学习曲线比较陡，NG1学起来比较麻烦，NG2开始，进行了一系列的改革，也开始启用组件化了；在NG中，也支持使用TS（TypeScript）进行编程；</li><li>Vue.js：最火的一门前端框架，它是中国人开发的，对我们来说，文档要友好一些；</li><li>React.js：最流行的一门框架，因为它的设计很优秀</li></ul>', false, 0, 4, 'kungreat', '2022-07-11', 1, true, null, null, null, null);
INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (7, '现在前端开发完全需要nodejs 来实现代码的模块化 依赖化 打包管理、
不过原生的html css js 也确实没有模块代的概念、需要nodejs 来实现模块代的管理 ', true, 0, 4, 'kungreat', '2022-07-11', 1, false, null, null, null, null);
INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (8, '<p>dfsdf</p><figure class="table"><table><tbody><tr><td>sd</td><td>sd</td></tr><tr><td>sdfs</td><td>sdfsdf</td></tr></tbody></table></figure>', false, 2, 5, 'kungreat', '2022-07-18', 1, true, 'null,刘大胖,大胖111', null, null, null);
INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (10, '是不是真的啊...', false, 0, 5, 'qepau666', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (11, '真的不是人的话', false, 0, 5, 'qepau666', '2022-08-04', 1, false, null, null, null, null);
INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (12, ' 层楼终就误少年', false, 0, 5, 'qepau666', '2022-08-04', 1, false, null, 10, 'success', null);
INSERT INTO details_text_front (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (13, '一生难道只为一层楼吗，还不到一层', false, 0, 5, 'qepau666', '2022-09-12', 1, false, null, null, null, null);
