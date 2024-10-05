CREATE TABLE IF NOT EXISTS users
(
    id bigserial NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email)
);

INSERT INTO users (id, email, password) VALUES
    (1, 'em@mail.ru', '$2a$10$r4LL5ZevfgSQlpJArn71yOkPEpdt.dCWCx8lDk4dAo3G7WQ081Q4e'); --1111

ALTER SEQUENCE users_id_seq RESTART WITH 10;  -- Задаем начальное значение 10