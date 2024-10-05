CREATE TABLE IF NOT EXISTS library
(
    id bigserial NOT NULL,
    books_id bigint NOT NULL,
    time_taken date,
    time_due date,
    CONSTRAINT library_pkey PRIMARY KEY (id),
    CONSTRAINT library_books_id_key UNIQUE (books_id),
    CONSTRAINT library_check CHECK (time_taken < time_due)
);

INSERT INTO library (id, books_id, time_taken, time_due) VALUES
    (1, 1, '2024-10-01', '2024-10-15'),  -- The Great Gatsby
    (2, 2, '2024-10-02', '2024-10-16'),  -- 1984
    (3, 3, '2024-10-03', '2024-10-17'),  -- To Kill a Mockingbird
    (4, 4, '2024-10-04', '2024-10-18');  -- Pride and Prejudice

ALTER SEQUENCE library_id_seq RESTART WITH 10;  -- Задаем начальное значение 100