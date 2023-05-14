create table categories
(
    id   serial8,
    name varchar not null,
    primary key (id)
);

create table products
(
    id          serial8,
    category_id int8    not null,
    name        varchar not null,
    price       int4    not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table options
(
    id          serial8,
    category_id int8 not null,
    name        varchar,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table values
(
    id         serial8,
    product_id int8 not null,
    option_id  int8 not null,
    name       varchar,
    primary key (id),
    foreign key (product_id) references products (id),
    foreign key (option_id) references options (id)
);

insert into categories (name)
values ('Процессоры'),
       ('Мониторы');

insert into products (category_id, name, price)
values (1, 'Intel Core I9 9900', 250000),
       (1, 'AMD Ryzen R7 7700', 270000),
       (2, 'Samsung SU556270', 150000),
       (2, 'AOC Z215S659', 180000);

insert into options (category_id, name)
values (1, 'Производитель'),
       (1, 'Количество ядер'),
       (1, 'Сокет'),
       (2, 'Производитель'),
       (2, 'Диагональ'),
       (2, 'Матрица'),
       (2, 'Разрешение');

insert into values (option_id, product_id, name)
values (1, 1, 'Intel'),
       (1, 2, 'AMD'),
       (2, 1, '8'),
       (2, 2, '12'),
       (3, 1, '1250'),
       (3, 2, 'AM4'),
       (4, 3, 'Samsung'),
       (4, 4, 'AOC'),
       (5, 3, '27'),
       (5, 4, '21.5'),
       (6, 3, 'TN'),
       (6, 4, 'AH-IPS'),
       (7, 3, '2560*1440'),
       (7, 4, '1920*1080');
