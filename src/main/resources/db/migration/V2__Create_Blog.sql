create table blog(
id int primary key auto_increment,
user_id bigint,
title varchar(100),
description varchar(200),
content text,
create_at datetime,
modify_at datetime
)default charset=utf8;

insert into blog(
id,
user_id,
title,
description,
content,
create_at,
modify_at
)
values
(1,1,'半岛铁盒','周杰伦','您好，请问有卖半岛铁盒嘛...',now(),now()),
(2,2,'我像风一样自由','许巍','我像风一样自由...',now(),now());
