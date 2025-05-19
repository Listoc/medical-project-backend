--liquibase formatted sql

--changeset listok:1
CREATE TYPE role AS ENUM ('ROLE_PATIENT', 'ROLE_DOCTOR');

--changeset listok:2
CREATE TABLE IF NOT EXISTS message
(
    message_id bigserial primary key,
    message_text text not null,
    message_date timestamp with time zone not null,
    producer role not null,
    patient_id bigint not null,
    doctor_id bigint not null
)