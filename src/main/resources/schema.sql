-- Контрагент
--     id                  id контрагента
--     name                имя контрагента
--     full_name           полное имя контрагента
--     type                тип контрагента
--     inn                 инн
--     phone_number        телефонный номер
--     email               почта
CREATE TABLE IF NOT EXISTS contractor (
    id                  SERIAL PRIMARY KEY,
    name                varchar(100) NOT NULL,
    full_name           varchar(255) NOT NULL,
    type                varchar(100) NOT NULL,
    inn                 varchar(12) NOT NULL,
    phone_number        varchar(11) NOT NULL,
    email               varchar(100)
);
ALTER TABLE contractor DROP CONSTRAINT IF EXISTS contractor_constraint;
ALTER TABLE contractor ADD CONSTRAINT contractor_constraint UNIQUE(full_name, inn);

-- Адрес
--     id              id адреса
--     region          регион
--     city            город
--     street          улица
--     house_number    номер дома
--     housing_number  номер корпуса
--     address_type    тип адреса (1 - юридический, 2 - для рекламной продукции)
--     contractor_id   id контрагента
CREATE TABLE IF NOT EXISTS address (
    id              SERIAL PRIMARY KEY,
    region          varchar(255),
    city            varchar(50),
    street          varchar(100),
    house_number    int,
    housing_number  int,
    address_type    int,
    contractor_id   int REFERENCES contractor(id)
);

ALTER TABLE address DROP CONSTRAINT IF EXISTS address_constraint;
ALTER TABLE address ADD CONSTRAINT address_constraint UNIQUE(address_type, contractor_id);

-- Контакт
--     id              id контакта
--     last_name       фамилия
--     first_name      имя
--     middle_name     отчество
--     phone_number    номер телефона
--     email           почта
--     person_type     Тип контакта (1 - контактное лицо, 2 - ответственный)
--     contractor_id   id контрагента
CREATE TABLE IF NOT EXISTS person (
    id              SERIAL PRIMARY KEY,
    last_name       varchar(100) NOT NULL,
    first_name      varchar(100) NOT NULL,
    middle_name     varchar(100) NOT NULL,
    phone_number    varchar(11),
    email           varchar(100),
    person_type     int,
    contractor_id   int REFERENCES contractor(id)
);

ALTER TABLE person DROP CONSTRAINT IF EXISTS person_constraint;
ALTER TABLE person ADD CONSTRAINT person_constraint UNIQUE(last_name, first_name, middle_name);

