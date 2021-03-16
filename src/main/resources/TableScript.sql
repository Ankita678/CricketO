CREATE DATABASE orderManagement;
use orderManagement;

create table orderdetails(
ORDERID int AUTO_INCREMENT,
BUYERID int not null,
AMOUNT decimal(10,2) not null,
DATE date,
ADDRESS varchar(100) not null,
STATUS varchar(60) not null,
primary key(ORDERID));

create table productsordered(
ORDERID int,
PRODID int,
SELLERID int not null,
QUANTITY int not null,
STATUS varchar(60) not null,
price decimal(10,2),
primary key(ORDERID,PRODID));

show tables;

describe orderdetails;
describe productsordered;


INSERT INTO orderdetails (orderid,buyerid,amount,date,address,status)
VALUES (1,11,12345,'2020-01-28','HYD','ORDER PLACED');
INSERT INTO orderdetails (orderid,buyerid,amount,date,address,status)
VALUES (2,12,999.00,'2020-02-28','Delhi','ORDER PLACED');

INSERT INTO productsordered (orderid,prodid,sellerid,quantity,status,price)
VALUES (1,1,4,1,'ORDER PLACED',12345);
INSERT INTO productsordered (orderid,prodid,sellerid,quantity,status,price)
VALUES (2,3,7,1,'ORDER PLACED',999.00);


select * from orderdetails;

select * from productsordered;