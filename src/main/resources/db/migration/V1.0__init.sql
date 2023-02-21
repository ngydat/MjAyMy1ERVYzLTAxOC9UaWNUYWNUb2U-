create sequence game_id_seq minvalue 2 increment 1;
create table
    games(
        game_id int not null,
        status character varying(255),
        winner character varying(255),
        primary key (game_id)
    );

