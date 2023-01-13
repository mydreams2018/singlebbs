create table permission
(
    id                 int auto_increment
        primary key,
    permission_methods varchar(255) collate utf8mb4_bin null,
    descript           varchar(126)                     null
)
    collate = utf8_bin;

INSERT INTO permission (id, permission_methods, descript) VALUES (1, 'cn.kungreat.singlebbs.controller.CollaborationCompanyController-insert', 'USER');
