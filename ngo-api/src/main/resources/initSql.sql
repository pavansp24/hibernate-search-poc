-- Seed Data goes here

CREATE TABLE TBL_BOOK_HEALTH (name varchar(20) , version varchar);

insert into TBL_BOOK_HEALTH values ('Book Catalog' , '1.0');

CREATE TABLE BOOK (id number primary key,isbn NUMBER(*,0), name varchar(30) , author varchar(20) , org varchar(30));

CREATE TABLE CHAPTER (id NUMBER(*,0) primary key , name varchar(20) ,desc varchar(30) ,isbn NUMBER(*,0) FOREIGN KEY REFERENCES BOOK(isbn));