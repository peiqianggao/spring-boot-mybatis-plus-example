CREATE TABLE `user` (
    `id`      int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`    varchar(32)      NOT NULL COMMENT '用户名',
    `phone`   varchar(32)      NOT NULL DEFAULT '' COMMENT '手机号',
    `city`    varchar(32)      NOT NULL COMMENT '城市',
    `created` datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated` datetime                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uidx_phone` (`phone`)
) COMMENT ='用户信息'
    AUTO_INCREMENT = 1000000;

CREATE TABLE `user_extension` (
    `id`           int(11) UNSIGNED NOT NULL COMMENT '用户id, user.id',
    `email`        varchar(32)      NOT NULL DEFAULT '' COMMENT '邮箱',
    `introduction` varchar(512)     NOT NULL DEFAULT '' COMMENT '用户简介',
    `eng_name`     varchar(32)      NOT NULL COMMENT '英文名称',
    `created`      datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated`      datetime                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='用户扩展信息';
