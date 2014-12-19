# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table choix (
  id                        bigint not null,
  name                      varchar(255),
  question_id               bigint,
  constraint pk_choix primary key (id))
;

create table question (
  id                        bigint not null,
  name                      varchar(255),
  multiple                  boolean,
  constraint pk_question primary key (id))
;

create table vote (
  ip                        varchar(255) not null,
  constraint pk_vote primary key (ip))
;


create table choix_vote (
  choix_id                       bigint not null,
  vote_ip                        varchar(255) not null,
  constraint pk_choix_vote primary key (choix_id, vote_ip))
;

create table vote_choix (
  vote_ip                        varchar(255) not null,
  choix_id                       bigint not null,
  constraint pk_vote_choix primary key (vote_ip, choix_id))
;
create sequence choix_seq;

create sequence question_seq;

create sequence vote_seq;

alter table choix add constraint fk_choix_question_1 foreign key (question_id) references question (id);
create index ix_choix_question_1 on choix (question_id);



alter table choix_vote add constraint fk_choix_vote_choix_01 foreign key (choix_id) references choix (id);

alter table choix_vote add constraint fk_choix_vote_vote_02 foreign key (vote_ip) references vote (ip);

alter table vote_choix add constraint fk_vote_choix_vote_01 foreign key (vote_ip) references vote (ip);

alter table vote_choix add constraint fk_vote_choix_choix_02 foreign key (choix_id) references choix (id);

# --- !Downs

drop table if exists choix cascade;

drop table if exists choix_vote cascade;

drop table if exists question cascade;

drop table if exists vote cascade;

drop table if exists vote_choix cascade;

drop sequence if exists choix_seq;

drop sequence if exists question_seq;

drop sequence if exists vote_seq;
