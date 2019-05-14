CREATE TABLE IF NOT EXISTS address (
    id              SERIAL PRIMARY KEY,
    region          varchar(255),
    city            varchar(50),
    street          varchar(100),
    house_number    int,
    housing_number  int
);

ALTER TABLE address DROP CONSTRAINT IF EXISTS address_constraint;
ALTER TABLE address ADD CONSTRAINT address_constraint UNIQUE(region, city, street, house_number, housing_number);

CREATE TABLE IF NOT EXISTS person (
    id              SERIAL PRIMARY KEY,
    last_name       varchar(100) NOT NULL,
    first_name      varchar(100) NOT NULL,
    middle_name     varchar(100) NOT NULL,
    phone_number    varchar(11),
    email           varchar(100)
);

CREATE TABLE IF NOT EXISTS contractor (
    id                  SERIAL PRIMARY KEY,
    name                varchar(100) NOT NULL,
    full_name           varchar(255) NOT NULL,
    type                varchar(100) NOT NULL,
    inn                 varchar(12) NOT NULL,
    legal_address_id    int REFERENCES address(id),
    phone_number        varchar(11) NOT NULL,
    advertising_id      int REFERENCES address(id),
    email               varchar(100),
    responsible_id      int REFERENCES person(id)
);

CREATE TABLE IF NOT EXISTS contractor_person (
    contractor_id   int REFERENCES contractor(id),
    person_id       int REFERENCES person(id),
    CONSTRAINT contractor_person_pkey PRIMARY KEY (contractor_id, person_id)
);

CREATE INDEX IF NOT EXISTS IX_legal_address_id ON contractor (legal_address_id);
CREATE INDEX IF NOT EXISTS IX_advertising_id ON contractor (advertising_id);
CREATE INDEX IF NOT EXISTS IX_responsible_id ON contractor (responsible_id);
