--liquibase formatted sql

--changeset listok:1
CREATE TABLE IF NOT EXISTS image
(
    image_id bigserial primary key,
    image_data bytea not null,
    patient_id bigint not null,
    diagnosis text null
)