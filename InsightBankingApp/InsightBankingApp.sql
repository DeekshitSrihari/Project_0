
create table customers (
	  					customerCifId int unique not null,
	  					customerFirstName varchar(60),
	  					customerLastName varchar(60),
	                    customerUserName varchar(60) unique not null,
	  					customerPassword varchar(60),
	  					accountNumber int primary key,
	  					accountBalance int)
select*from customers ;
drop table customers;

delete from customers where accountNumber=0;
insert into customers values(97472,'PavanSai','Sheshetti','PavanSaiS','Pavan123',385995354,9000);
update customers set accountBalance=accountBalance-3000 where accountNumber=5
update customers set accountBalance=accountBalance+3000 where accountNumber=6;

create or replace procedure getCustomerBalance(
			 customerAccountNumber int,
			 inout customerAccountBalance  int
)
language plpgsql
as $$
begin
	select accountBalance into customerAccountBalance from iba.customers where accountNumber=customerAccountNumber;
commit;
end;
$$

create table Employee (
						employeeId int primary key,
						employeeFirstName varchar(30),
						employeeLastName varchar(30),
						employeeUserName varchar(40) unique not null,
						employeePassword varchar(40)
						)
select *from employee;

insert into iba.employee values(953761,'Bharath Sai','Maddela','BharathSaiM','Bharath789');
insert into iba.employee values(123456,'Lokesh','Mothukuri','LokeshM','Lokesh789');
insert into iba.employee values(75369,'Yusuf Pasha','Shaik','YusufPashaS','Yusuf789');

