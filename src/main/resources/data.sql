TRUNCATE TABLE address CASCADE;
ALTER SEQUENCE address_id_seq RESTART;
INSERT INTO address (region, city, street, house_number, housing_number)
    VALUES ('Смоленская обл', null, 'Севастопольская', 35, null);
INSERT INTO address (region, city, street, house_number, housing_number)
    VALUES (null, 'Тольятти', 'Северная', 16, 3);

TRUNCATE TABLE person CASCADE;
ALTER SEQUENCE person_id_seq RESTART;
INSERT INTO person (last_name, first_name, middle_name, phone_number, email)
    VALUES ('Алхимов', 'Андрей', 'Андреевич', '821321321', 'mdk@ya.ru');
INSERT INTO person (last_name, first_name, middle_name, phone_number, email)
    VALUES ('Иванова', 'Ирина', 'Ивановна', '879876546', 'ivanova@mail.ru');
INSERT INTO person (last_name, first_name, middle_name, phone_number, email)
    VALUES ('Петров', 'Петр', 'Петрович', null, null);
INSERT INTO person (last_name, first_name, middle_name, phone_number, email)
    VALUES ('Сидоров', 'Иван', 'Иванович', '895465111', 'sid@mail.ru');
INSERT INTO person (last_name, first_name, middle_name, phone_number, email)
    VALUES ('Абдулов', 'Юрий', 'Владимирович', '89878465435', null);

TRUNCATE TABLE contractor CASCADE;
ALTER SEQUENCE contractor_id_seq RESTART;
INSERT INTO contractor (name, full_name, type, inn, legal_address_id, phone_number, advertising_id, email, responsible_id)
    VALUES ('Алхимов А.А.', 'ОАО "Алхимов А.А."', 'Юр. лицо', 0461111101, 1, '89846543135', 2, 'Alhimov@ya.ru', 5);

TRUNCATE TABLE contractor_person CASCADE;
INSERT INTO contractor_person (contractor_id, person_id) VALUES (1, 1);
INSERT INTO contractor_person (contractor_id, person_id) VALUES (1, 2);
INSERT INTO contractor_person (contractor_id, person_id) VALUES (1, 3);
INSERT INTO contractor_person (contractor_id, person_id) VALUES (1, 4);