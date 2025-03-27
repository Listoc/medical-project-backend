--liquibase formatted sql

--changeset listok:1
CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');

--changeset listok:2
CREATE TABLE IF NOT EXISTS doctor
(
	doctor_id bigserial primary key,
	first_name text not null,
	last_name text not null,
	middle_name text null,
	email text unique not null,
	sex gender not null,
	birthday date not null,
	avatar bytea null,
	specialization text not null,
    category text not null
);