--liquibase formatted sql

--changeset listok:2
CREATE TABLE IF NOT EXISTS patient_remind
(
    remind_id bigint generated always as identity primary key,
	patient_id bigint references patient(patient_id) ON DELETE CASCADE,
	remind_time time with time zone not null,
	last_check_at timestamp with time zone,
	last_send_at timestamp with time zone
);