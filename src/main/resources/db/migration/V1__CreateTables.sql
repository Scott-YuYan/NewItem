create table user(
id int primary key auto_increment,
username varchar(20) not null unique,
password varchar(100),
avatar varchar(100),
create_time datetime,
modify_time datetime
)
