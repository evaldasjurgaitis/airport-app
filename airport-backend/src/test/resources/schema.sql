CREATE TABLE airport (
    id int NOT NULL,
    type VARCHAR(50),
    name VARCHAR(100),
	latitude FLOAT(10),
	longitude FLOAT(10),
	altitude int,
    continent VARCHAR(50),
	iso_country VARCHAR(50),
	iso_region VARCHAR(50),
	municipality VARCHAR(50),
	sheduled_service BOOLEAN,
	gps_code VARCHAR(50),
	iata_code VARCHAR(50),
	local_code VARCHAR(50),
	UNIQUE (id),
	PRIMARY KEY (id)
);

CREATE TABLE provider (
	id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    currency VARCHAR(50),
    price int,
	airport_id int,
	FOREIGN KEY (airport_id) REFERENCES airport(id),
	PRIMARY KEY (id)
);

CREATE TABLE country (
    id int NOT NULL AUTO_INCREMENT,
    code VARCHAR(50),
    name VARCHAR(50),
    continent VARCHAR(50),
    UNIQUE (code),
	PRIMARY KEY (id)
);

CREATE TABLE region (
    id int NOT NULL AUTO_INCREMENT,
    code VARCHAR(50),
    name VARCHAR(100),
    continent VARCHAR(50),
	iso_country VARCHAR(50),
	UNIQUE (id),
	PRIMARY KEY (id)
);