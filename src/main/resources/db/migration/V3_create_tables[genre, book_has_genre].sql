create table genres
(
    id   integer not null
        primary key,
    name varchar(255)
);

create table book_has_genres
(
    book_id  integer not null
        constraint fk_book foreign key(book_id)
            references books(id),
    genre_id integer not null
        constraint fk_genre foreign key(genre_id)
        references genres(id),

);
