create table user
(
    uid                  int auto_increment
        primary key,
    email                varchar(50)                        not null,
    nickname                 varchar(20)                        not null,
    name                 varchar(20)                        not null,
    password             varchar(20)                        not null,
    avatar               varchar(500)                       null comment '用户头像url',
    gender               tinyint  default 2                 not null comment '性别 0女 1男 2未知',
    role                 tinyint  default 0                 not null comment '角色类型 0 普通用户，1 科研人员，2 管理员',
    state                tinyint  default 0                 not null comment '状态 0正常 1封禁 2注销',
    create_date          datetime default CURRENT_TIMESTAMP null comment '创建时间',
    delete_date          datetime                           null comment '注销时间'
);
create table email
(
    email                varchar(50)                        not null primary key,
    activeCode                 varchar(20)                        not null,
    createDate          datetime default CURRENT_TIMESTAMP null comment '创建时间'
);