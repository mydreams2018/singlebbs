create table persistent_logins
(
    username  varchar(64) collate utf8mb4_bin not null,
    series    varchar(64) collate utf8mb4_bin not null
        primary key,
    token     varchar(64) collate utf8mb4_bin not null,
    last_used timestamp                       not null
)
    collate = utf8_bin;

