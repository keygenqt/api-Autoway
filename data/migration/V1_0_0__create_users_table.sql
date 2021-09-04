create table if not exists users
(
    id         integer primary key autoincrement,
    email      varchar(250) not null,
    nickname   varchar(250) not null,
    image      varchar(500),
    website    varchar(500),
    location   varchar(500),
    password   varchar(500) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into users (email, nickname, image, website, location, password)
values ('keygenqt',
        'keygenqt',
        'https://keygenqt.com/images/index/web.jpg',
        'https://keygenqt.com',
        null,
        'm8goPNHQSaH0cnFJmrfu');

insert into users (email, nickname, image, website, location, password)
values ('test1',
        'test1',
        'https://test1.com/images/index/test1.jpg',
        'https://test1.com',
        null,
        'XmjTdhsFGHyEYkRC71Ji');

insert into users (email, nickname, image, website, location, password)
values ('test2',
        'test2',
        'https://test2.com/images/index/test2.jpg',
        'https://test2.com',
        null,
        'DaF1Qje6lY4ey58R9CpM');

insert into users (email, nickname, image, website, location, password)
values ('test3',
        'test3',
        'https://test3.com/images/index/test3.jpg',
        'https://test3.com',
        null,
        'jUpLIldwdnJUqyLiqoSj');

insert into users (email, nickname, image, website, location, password)
values ('test4',
        'test4',
        'https://test4.com/images/index/test4.jpg',
        'https://test4.com',
        null,
        'hKQoPvhVqcSjQHm7t0R8');

insert into users (email, nickname, image, website, location, password)
values ('test5',
        'test5',
        'https://test5.com/images/index/test5.jpg',
        'https://test5.com',
        null,
        'MmBzvsOFdDLyzcbEPrm6');

insert into users (email, nickname, image, website, location, password)
values ('test6',
        'test6',
        'https://test6.com/images/index/test6.jpg',
        'https://test6.com',
        null,
        '64PNkzJxVHG2H9v2ZxNv');

insert into users (email, nickname, image, website, location, password)
values ('test7',
        'test7',
        'https://test7.com/images/index/test7.jpg',
        'https://test7.com',
        null,
        'HEX13MMmKC3EFHiiULOs');

insert into users (email, nickname, image, website, location, password)
values ('test7',
        'test7',
        'https://test7.com/images/index/test7.jpg',
        'https://test7.com',
        null,
        '5zlzMCCy3z9iOzewWjMb');

insert into users (email, nickname, image, website, location, password)
values ('test9',
        'test9',
        'https://test9.com/images/index/test9.jpg',
        'https://test9.com',
        null,
        'SGobU59ZEYhIwLWkFDqz');

insert into users (email, nickname, image, website, location, password)
values ('test10',
        'test10',
        'https://test10.com/images/index/test10.jpg',
        'https://test10.com',
        null,
        'B0k53kGiBrLINEjMjfTf');

insert into users (email, nickname, image, website, location, password)
values ('test11',
        'test11',
        'https://test11.com/images/index/test11.jpg',
        'https://test11.com',
        null,
        'WWnSk4OduwHVaF8YFuM0');

insert into users (email, nickname, image, website, location, password)
values ('test12',
        'test12',
        'https://test12.com/images/index/test12.jpg',
        'https://test12.com',
        null,
        'dnnUBbAnhjLEzz5lP4x5');
