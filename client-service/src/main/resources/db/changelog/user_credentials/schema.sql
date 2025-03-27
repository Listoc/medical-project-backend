--liquibase formatted sql

--changeset listok:4
CREATE TYPE role AS ENUM ('ROLE_PATIENT', 'ROLE_DOCTOR', 'ROLE_ADMIN');

--changeset listok:5
CREATE TABLE IF NOT EXISTS user_credentials
(
    user_id bigserial primary key,
    username text unique not null,
    password text not null,
    user_role role not null,
    patient_id bigint null unique references patient(patient_id) ON DELETE CASCADE,
    doctor_id bigint null unique references doctor(doctor_id) ON DELETE CASCADE,
    check (((patient_id IS NULL OR doctor_id IS NULL) AND (patient_id IS NOT NULL OR doctor_id IS NOT NULL)) OR (user_role = 'ROLE_ADMIN' AND (patient_id IS NULL AND doctor_id IS NULL)))
)