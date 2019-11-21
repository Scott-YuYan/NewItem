

create table user(
id int primary key auto_increment,
username varchar(20) not null,
password varchar(100),
avatar varchar(100),
create_time datetime,
modify_time datetime
)

insert into user(id,username,password,avatar,create_time,modify_time)
values (1,"zhangsan","123",null,now(),now())