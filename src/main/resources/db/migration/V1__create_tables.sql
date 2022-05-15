create table if not exists books
(
    id   integer ,
    name varchar(255),
    primary key(id)

);

create table if not exists genres
(
    id   integer
        primary key,
    name varchar(255)
);

create table if not exists book_has_genres
(
    book_id  integer not null,
    genre_id integer not null,
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            references books (id) ON DELETE CASCADE,
    CONSTRAINT fk_genre
        foreign key (genre_id)
            references genres (id) ON DELETE CASCADE
);