--liquibase formatted sql

--changeset listok:3
CREATE TABLE IF NOT EXISTS patient
(
	patient_id bigserial primary key,
	first_name text not null,
	last_name text not null,
	middle_name text null,
	email text unique not null,
	sex gender not null,
	birthday date not null,
	avatar bytea null,
	diagnosis text not null,
	doctor_id bigint null references doctor(doctor_id) ON DELETE SET NULL (doctor_id)
);