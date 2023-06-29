create table users
(
    id                serial8 primary key,
    role              int2,
    login             varchar,
    password          varchar,
    first_name        varchar,
    last_name         varchar,
    registration_date timestamp
);

create table categories
(
    id   serial8,
    name varchar not null,
    primary key (id)
);

create table products
(
    id          serial8,
    category_id int8 ,
    name        varchar not null,
    price       int4    not null,
    primary key (id),
    foreign key (category_id) references categories (id) on delete set null
);

create  table cart(
                      id serial4 primary key ,
                      id_users int4 references users(id),
                      id_product int4 references products(id),
                      count int,
                      id_shop int4 references shop(id)
);

create table options
(
    id          serial8,
    category_id int8,
    name        varchar,
    primary key (id),
    foreign key (category_id) references categories (id) on delete set null
);

create table values
(
    id         serial8,
    product_id int8,
    option_id  int8,
    value       varchar,
    primary key (id),
    foreign key (product_id) references products (id) on delete set null,
    foreign key (option_id) references options (id) on delete set null
);

create table orders
(
    id               serial8 PRIMARY KEY ,
    user_id          int8 references users (id) on delete set null,
    status           int2,
    delivery_address varchar,
    order_date       timestamp
);

CREATE TABLE order_items
(
    id SERIAL8 primary key ,
    order_id   INTEGER REFERENCES orders (id) on delete set null,
    product_id INTEGER REFERENCES products (id) on delete set null,
    count int
);

create table reviews
(
    id               serial8 primary key,
    user_id          int8 REFERENCES users (id) on delete set null ,
    product_id       int8 REFERENCES products (id) on delete set null,
    published        boolean,
    rating           int4,
    comment          varchar,
    publication_date timestamp
);

create table shop(
  id serial8 primary key,
  product_id int8 REFERENCES products (id) on delete set null,
  name varchar,
  address varchar,
  count int4
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

insert into values (option_id, product_id, value)
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

select products FROM  products join values v on products.id = v.product_id where value in ('Intel','AMD') or option_id in (1,2,4);