create table user_entity (
	id       bigint       not null,
	email    varchar(255) not null,
	password varchar(255) not null,
	role     enum ('ADMIN','USER') not null,
	primary key (id)
) engine=InnoDB;

alter table user_entity add constraint UK_user_email unique (email);
