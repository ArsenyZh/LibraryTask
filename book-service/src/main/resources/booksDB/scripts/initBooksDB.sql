CREATE TABLE IF NOT EXISTS books
(
    id bigserial NOT NULL,
    isbn character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    genre character varying(255),
    description character varying(255),
    author character varying(255) NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id),
    CONSTRAINT books_isbn_key UNIQUE (isbn)
);

INSERT INTO books (id, isbn, name, genre, description, author) VALUES
    (1, '978-3-16-148410-0', 'The Great Gatsby', 'Fiction', 'A novel about the American dream.', 'F. Scott Fitzgerald'),
    (2, '978-1-56619-909-4', '1984', 'Dystopian', 'A story about totalitarianism and surveillance.', 'George Orwell'),
    (3, '978-0-7432-7356-5', 'To Kill a Mockingbird', 'Fiction', 'A novel about racial injustice in the Deep South.', 'Harper Lee'),
    (4, '978-0-452-28423-4', 'Pride and Prejudice', 'Romance', 'A story about love and social standing.', 'Jane Austen');

ALTER SEQUENCE books_id_seq RESTART WITH 10;  -- Задаем начальное значение 100