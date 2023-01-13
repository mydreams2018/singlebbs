create table permission_mapping
(
    id       bigint auto_increment
        primary key,
    account  varchar(126) collate utf8mb4_bin null,
    descript varchar(255) collate utf8mb4_bin null
)
    collate = utf8_bin;

