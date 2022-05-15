insert into books(id, name, page_number, description, publisher, price)
values (1, 'name1', 500, 'book about...', 'author1', 100),
       (2, 'name2', 240, 'book about...', 'author2', 120),
       (3, 'name3', 324, 'book about...', 'author3', 150);

insert into genres(id, name)
values (1, 'genre1'),
       (2, 'genre2'),
       (3, 'genre3');


insert into book_has_genres(book_id, genre_id)
values (1,1),
       (2,3),
       (3,2);