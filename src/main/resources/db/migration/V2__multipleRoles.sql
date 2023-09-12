rename table user_entity to user;
alter table user drop column role;

create table user_role (id bigint not null, user_id bigint not null, role enum ('ADMIN','USER'), primary key (id)) engine=InnoDB;

alter table user_role add constraint FK_user_role_user foreign key (user_id) references user (id);
