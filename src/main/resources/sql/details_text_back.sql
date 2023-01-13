create table details_text_back
(
    id            bigint auto_increment
        primary key,
    details_text  text                 null,
    is_adoption   bit                  null,
    like_number   int                  null,
    port_id       bigint               null,
    user_account  varchar(66)          null,
    create_data   date                 null,
    auth_flag     int                  null comment '0:审核中,1已审,2:不通过',
    is_port       bit                  null,
    like_account  text charset utf8mb4 null,
    reply_parent  bigint               null comment '上级ID',
    auth_describe varchar(255)         null comment '审核情况说明',
    update_time   bigint               null
);

create index account_port
    on details_text_back (user_account);

INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (23, '<p>&nbsp;</p><figure class="table"><table><tbody><tr><td>y</td><td>e</td><td>s</td></tr></tbody></table></figure><p><img src="/api/userImg/rcNVtQyo.jpeg"></p>', false, 0, 5, 'kungreat', '2022-07-11', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (25, ' you are right', true, 0, 5, 'kungreat', '2022-07-11', 1, false, null, 24, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (28, '<p>11</p>', false, 0, 6, 'kungreat', '2022-07-18', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (29, '<p>11111</p>', false, 0, 7, 'kungreat', '2022-07-18', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (30, '<p>2222</p>', false, 0, 8, 'kungreat', '2022-07-18', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (31, '<p>全是垃圾项目兄弟们、不如打锣丝&nbsp;</p>', false, 0, 9, 'kungreat', '2022-07-18', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (34, '<p>222222</p>', false, 0, 10, 'kungreat', '2022-07-18', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (35, '<p>丢、全是垃圾项目&nbsp;</p>', false, 2, 12, 'kungreat', '2022-07-18', 1, true, ',刘大胖,大胖111', null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (38, '真的全是垃圾项目、没有好了', false, 0, 12, 'qepau666', '2022-07-19', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (39, ' yes ', false, 0, 12, 'qepau666', '2022-07-19', 1, false, null, 38, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (40, '<p>全是垃圾 、、、真的全垃&nbsp;</p>', false, 2, 13, 'qepau666', '2022-07-19', 1, true, 'null,刘大胖,大胖111', null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (41, '<p>不可能什么都具备、万物没有这么顺的。</p><figure class="image"><img src="/api/userImg/KeZpLTCt.jpeg"></figure>', false, 2, 14, 'qepau666', '2022-07-19', 1, true, 'null,刘大胖,大胖111', null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (42, 'yes or no', true, 0, 13, 'qepau666', '2022-07-20', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (43, '是说在坐的各位都是垃圾22', false, 0, 14, 'kungreat', '2022-07-20', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (44, '1111', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (45, '2222', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (49, '6666', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (50, '7777', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (51, '8888', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (52, '9999', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (53, '10', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (56, ' ts', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, 55, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (57, ' 9-2', false, 0, 12, 'kungreat', '2022-07-25', 1, false, null, 52, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (58, ' 都是垃项目 yes', false, 0, 12, 'qepau666', '2022-07-25', 1, false, null, 54, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (60, '12', false, 0, 12, 'qepau666', '2022-07-30', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (61, '13', false, 0, 12, 'qepau666', '2022-07-30', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (62, '14', false, 0, 12, 'qepau666', '2022-07-30', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (63, '  dddd', false, 0, 12, 'qepau666', '2022-07-30', 1, false, null, 61, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (64, ' ok', false, 0, 12, 'kungreat', '2022-08-07', 1, false, null, 39, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (65, '<p>我就在这里等你 披星戴月乘着风而来<br>我就在这里 埋好烈酒候你故事开</p>', false, 2, 15, 'kungreat', '2022-08-07', 1, true, 'null,刘大胖,大胖111', null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (66, '千千万万人海  灯火阑珊你多少次不在
走遍高高低低  一路辗转朝暮青丝己白
我在红尘等你  人间等你守繁华之外
揽尽星辰入怀  千川归来化一片沧海
我在九幽等你  极乐等你望彼岸花开
长对三生浮白  不畏不改渡过去将来', false, 0, 15, 'kungreat', '2022-08-07', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (67, ' aaaa', false, 0, 12, 'kungreat', '2022-08-07', 1, false, null, 64, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (68, '长对三生浮白  不畏不改渡过去将来 yes', false, 0, 15, 'kungreat', '2022-08-07', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (69, '[@68 ddffsdfsd', true, 0, 15, 'kungreat', '2022-08-07', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (70, '  zai', false, 0, 15, 'kungreat', '2022-08-07', 1, false, null, 68, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (71, ' 没有人发现磁抮', false, 0, 12, 'kungreat', '2022-09-06', 1, false, null, 62, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (72, '<p>我就在这里，。yes<img src="/api/userImg/bJJwCzUN.png"></p><figure class="table"><table><tbody><tr><th>ds</th><th>ss</th><td>ss</td></tr></tbody></table></figure>', false, 1, 16, 'kungreat', '2022-09-06', 1, true, 'null,大胖111', null, null, 1669207799549);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (73, '你要看是不是这样发民的', false, 0, 15, 'kungreat', '2022-09-09', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (74, 'dsfdsdf111', false, 0, 12, 'kungreat', '2022-09-09', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (75, '<p>11111</p><figure class="image"><img src="/api/userImg/pXkYJmDw.png"></figure>', false, 0, 17, 'qepau666', '2022-10-05', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (76, '<h2>dfsdfsdf&nbsp;</h2><p><img src="/api/userImg/fKuIdAkt.png"> &nbsp;dfsdfsdf</p><pre><code class="language-c"> &lt;Route path="/forget" element={&lt;Forget /&gt;} /&gt;</code></pre><figure class="table"><table><tbody><tr><td>1</td><td>2</td><td>3</td></tr></tbody></table></figure><pre><code class="language-java"> 	public Report selectByPrimaryKey(Report record) {
        Assert.isTrue(record.getClassId()!=null&amp;&amp;record.getClassId()&gt;=1&amp;&amp;record.getClassId()&lt;5,"类型ID异常");
        Assert.isTrue(record.getId() != null,"ID异常");
        record.setPortIsauth(1);
        Report report = reportMapper.selectByPrimaryKey(record);
        if(report != null){
            DetailsText de = new DetailsText();
            de.setPortId(record.getId());
            de.setClassId(record.getClassId());
            de.setPortIsauth(1);
            report.setDetails(detailsTextMapper.selectByPort(de));
        }
        return report;
    }</code></pre>', false, 1, 18, 'qepau666', '2022-10-07', 1, true, 'null,大胖111', null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (77, '可以了', true, 0, 18, 'qepau666', '2022-10-07', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (78, '经济都是房产支撑的', false, 0, 15, 'qepau666', '2022-10-16', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (79, '<pre><code class="language-plaintext">    switch (classId){
            case 1:
                return  "details_text_back";
            case 2:
                return  "details_text_front";
            case 3:
                return "details_text_data";
            case 4:
                return  "details_text_talk";
   }</code></pre><p>现在真的难搞啊,希望加机会,遇所有</p><figure class="image"><img src="/api/userImg/mRJXADEa.png"><figcaption>fsdd</figcaption></figure>', false, 1, 19, 'qepau666', '2022-10-16', 1, true, 'null,大胖111', null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (80, '唐胖子是学生吗', false, 0, 19, 'qepau666', '2022-10-16', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (81, '老学生了。', true, 0, 19, 'qepau666', '2022-10-16', 1, false, null, 80, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (82, '一些人的可 有 ', false, 0, 18, 'qepau666', '2022-10-22', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (83, '基本差不多了，就等审核机制完成', false, 0, 16, 'qepau666', '2022-10-22', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (84, 'yes', false, 0, 16, 'qepau666', '2022-10-24', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (85, ' 确实审核看怎么设计了', false, 0, 16, 'qepau666', '2022-10-26', 1, false, null, 83, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (86, '只做精品', false, 0, 12, 'qepau666', '2022-10-26', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (87, 'npm install --save-dev http-proxy-middleware
', false, 0, 12, 'qepau666', '2022-10-29', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (88, '<pre><code class="language-c">3123123123</code></pre>', false, 0, 20, 'qepau666', '2022-10-30', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (89, '真的吗，任务必将完成，不要太深入世俗', false, 0, 18, 'qepau666', '2022-11-06', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (90, '给了你机会，你能把握住吗。', false, 0, 16, 'qepau666', '2022-11-06', 1, false, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (91, '老观起又想吃饭了,ok', false, 0, 19, 'kungreat', '2022-11-22', 1, false, null, null, null, 1669207774719);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (92, '<p>大规模所使用的无可奈何</p>', false, 0, 21, 'qepau668', '2022-11-24', 1, true, null, null, null, null);
INSERT INTO details_text_back (id, details_text, is_adoption, like_number, port_id, user_account, create_data, auth_flag, is_port, like_account, reply_parent, auth_describe, update_time) VALUES (93, '时代的车轮', false, 0, 18, 'qepau668', '2022-11-28', 1, false, null, null, null, null);
