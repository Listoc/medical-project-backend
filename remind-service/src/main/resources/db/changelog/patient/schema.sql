--liquibase formatted sql

--changeset listok:1
CREATE TABLE IF NOT EXISTS patient
(
	patient_id bigint primary key,
	first_name text not null,
	last_name text not null,
	middle_name text null,
	email text unique not null
);