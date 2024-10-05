CREATE TABLE IF NOT EXISTS users
(
    id bigserial NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email)
);

INSERT INTO users (id, email, password) VALUES
    (1, 'user1@example.com', 'password123'),
    (2, 'em@mail.ru', '1111');
