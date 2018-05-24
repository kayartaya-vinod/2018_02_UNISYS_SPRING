drop table if exists phonebook;

create table phonebook (
	id INT primary key auto_increment,
	first_name VARCHAR(50) not null,
	last_name VARCHAR(50),
	gender VARCHAR(50),
	email VARCHAR(50) not null unique,
	phone VARCHAR(50) not null unique,
	address VARCHAR(50),
	city VARCHAR(50),
	state VARCHAR(50),
	country VARCHAR(50)
);
