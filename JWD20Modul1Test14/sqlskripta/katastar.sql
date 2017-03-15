create database katastar;
use katastar;

create table vlasnik (
	jmbg integer,
	ime_prezime varchar(255),
	adresa varchar(255),
	primary key (jmbg)
);

create table nekretnina (
	id integer, 
	adresa varchar(255),
	trzisna_vrednost double, 
	vlasnik_id integer,
	primary key(id),
	foreign key(vlasnik_id)
		references vlasnik(jmbg)
	
	
);

insert into vlasnik values (1111, 'Petar Petrovic', 'Petrova 1, Beograd');
insert into vlasnik values (2222, 'Jovan Jovanovic', 'Jovanova 2, Novi Sad');
insert into vlasnik values (3333, 'Ivan Ivanovic', 'Ivanova 3, Novi Sad');

insert into nekretnina values (1, 'Jovanova 2', 50000, 2222);
insert into nekretnina values (2, 'Milanova 14', 45000, 1111);
insert into nekretnina values (3, 'Mihajlova 6', 35000, 2222);
insert into nekretnina values (4, 'Brankova 14', 67000, 3333);
