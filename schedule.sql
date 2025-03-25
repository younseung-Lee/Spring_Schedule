use schedule;

crate table schedule(
    id         bigint auto_increment
        primary key,
    todo       varchar(100) not null,
    username   varchar(20)  not null,
    password   varchar(10)  null,
    createtime datetime     null,
    updatetime datetime     null
);
