create table categoria (
 id serial primary key,
 nombre varchar(50) not null unique
);

insert into categoria (nombre) values ('categoria1');
insert into categoria (nombre) values ('categoria2');
insert into categoria (nombre) values ('categoria3');

create table articulo (
 id serial primary key,
 nombre varchar(50) not null unique,
 precio numeric(10,2) not null,
 categoria bigint unsigned
);

alter table articulo add foreig nkey (categoria) references categoria (id);

insert into articulo (nombre, precio, categoria) values ('articulo 1',1.0,1);
insert into articulo (nombre, precio, categoria) values ('articulo 2',2.0,2);
insert into articulo (nombre, precio, categoria) values ('articulo 3',3.0,3);
insert into articulo (nombre, precio) values ('articulo 4',4.0);
