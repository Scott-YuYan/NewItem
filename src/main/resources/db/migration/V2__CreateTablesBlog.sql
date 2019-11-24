create table blog(
id int primary key auto_increment,
user_id bigint,
title varchar(100),
description varchar(200),
content text,
create_at datetime,
modify_at datetime
)