TRUNCATE TABLE contractor CASCADE;
ALTER SEQUENCE contractor_id_seq RESTART;
INSERT INTO contractor (name, full_name, type, inn, phone_number, email)
VALUES ('Алхимов А.А.', 'ОАО "Алхимов А.А."', 'Юр. лицо', 0461111101, '89846543135', 'Alhimov@ya.ru');
INSERT INTO contractor (name, full_name, type, inn, phone_number, email)
VALUES ('Алхимов А.А.1', 'ОАО "Алхимов А.А."1', 'Юр. лицо', 0461111101, '89846543135', 'Alhimov@ya.ru');

 TRUNCATE TABLE address CASCADE;
 ALTER SEQUENCE address_id_seq RESTART;
 INSERT INTO address (region, city, street, house_number, housing_number, address_type, contractor_id)
     VALUES ('Смоленская обл', null, 'Севастопольская', 35, null, 1, 1);
 INSERT INTO address (region, city, street, house_number, housing_number, address_type, contractor_id)
     VALUES (null, 'Тольятти', 'Северная', 16, 3, 2, 1);

 TRUNCATE TABLE person CASCADE;
 ALTER SEQUENCE person_id_seq RESTART;
 INSERT INTO person (last_name, first_name, middle_name, phone_number, email, person_type, contractor_id)
     VALUES ('Алхимов', 'Андрей', 'Андреевич', '821321321', 'mdk@ya.ru', 1, 1);
 INSERT INTO person (last_name, first_name, middle_name, phone_number, email, person_type, contractor_id)
     VALUES ('Иванова', 'Ирина', 'Ивановна', '879876546', 'ivanova@mail.ru', 1, 1);
 INSERT INTO person (last_name, first_name, middle_name, phone_number, email, person_type, contractor_id)
     VALUES ('Петров', 'Петр', 'Петрович', null, null, 1, 1);
 INSERT INTO person (last_name, first_name, middle_name, phone_number, email, person_type, contractor_id)
     VALUES ('Сидоров', 'Иван', 'Иванович', '895465111', 'sid@mail.ru', 1, 1);
 INSERT INTO person (last_name, first_name, middle_name, phone_number, email, person_type, contractor_id)
     VALUES ('Абдулов', 'Юрий', 'Владимирович', '89878465435', null, 2, 1);
