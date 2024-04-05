create table if not exists room (
	room_id uuid primary key,
	room_type text,
	price numeric
);

create table if not exists reservation (
	reservation_id uuid primary key,
	room_id uuid,
	email text,
	checkin_date timestamp,
	checkout_date timestamp,
	price numeric,
	status text,
	duration numeric
);

delete from room;
insert into room (room_id, room_type, price) values ('aa354842-59bf-42e6-be3a-6188dbb5fff8', 'DAY', 1000);
insert into room (room_id, room_type, price) values ('d5f5c6cb-bf69-4743-a288-dafed2517e38', 'HOUR', 100);