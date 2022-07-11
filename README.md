# singlebbs

#### 介绍
开源论坛项目、目前正在配合cpdogbbs前端准备改造
数据库设计.
后端是基于springboot2.7搭建.
集成免费的SSL认证.集成spring security安全框架.
集成oauth2-client (QQ第三方oauth用户登录认证).
数据库MYSQL8.X版本.
集成mybatis框架.实现了前端入参动态表查询.
mybatis-generator插件一键生成基本CURD代码
手动实现查询分页算法不集成分页插件.效率高很多.
前端使用nginx转发.完整的用户注册,发贴,回贴.签到.权限认证.
配合spring security rememberMe实现认证cookie缓存
一次认证可默认保存一个月时间.不删除浏览器cookie情况下.
完整的登录认证系统.前后端分离的架构.


#### 安装教程

1. 下载此项目源代码.
2. 修改application.properties 配置文件 最少修改user.imgPath图片保存地址为你本地的一个目录
3. 创建数据库flybbs 
4. 运行sql脚本、sql.txt
5. 使用maven打成jar包运行/开发工具打开项目主方法运行