create table orders
(
    id    bigint not null,
    no    varchar(255),
    total numeric(38, 2),
    primary key (id)
)
alter table if exists orders drop constraint if exists orders_no_unique
alter table if exists orders add constraint orders_no_unique unique (no)
create sequence orders_SEQ start with 1 increment by 50