create table oauth2_user
(
    open_id       varchar(255) not null comment 'oauth2-唯一用户标识'
        primary key,
    user_account  varchar(66)  null,
    provider_id   varchar(66)  null,
    access_token  varchar(255) null,
    refresh_token varchar(255) null,
    expire_time   bigint       null,
    register_time date         null
);

INSERT INTO oauth2_user (open_id, user_account, provider_id, access_token, refresh_token, expire_time, register_time) VALUES ('6B19A5599D302141FF504640BAE2BEE4', '5e33bc5ca35', 'qq', '5D3A7E95322E7343F5EADA459EE2AC7B', 'FFE09D73872D3C882D95768EAA436444', 7776000, '2021-01-16');
