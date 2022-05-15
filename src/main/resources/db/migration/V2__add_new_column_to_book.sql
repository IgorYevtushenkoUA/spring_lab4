alter table books
    add column page_number INTEGER NOT NULL,
    add column description VARCHAR(1024) NOT NULL ,
    add column publisher   VARCHAR(1024) NOT NULL ,
    add column price   DOUBLE PRECISION;