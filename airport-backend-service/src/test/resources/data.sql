INSERT INTO airport (id, altitude, continent, gps_code, iata_code, iso_country, iso_region, latitude, local_code, longitude, municipality, name, sheduled_service, type)
VALUES
(6525,
820,
'NA',
'00AL',
'',
'US',
'US-AL',
34.86479949951172,
'00AL',
-86.77030181884766,
'Harvest',
'Epps Airpark',
0,
'small_airport');

INSERT INTO airport (id, altitude, continent, gps_code, iata_code, iso_country, iso_region, latitude, local_code, longitude, municipality, name, sheduled_service, type)
VALUES
(6746,
50,
'NA',
'03TX',
'',
'US',
'US-TX',
29.581600189208984,
'03TX',
95.30719757080078,
'Pearland',
'M D K Field',
0,
'heliport');

INSERT INTO provider(currency, name, price, airport_id) VALUES('EUR', 'wizzair', 3311, 6525);
INSERT INTO provider(currency, name, price, airport_id) VALUES('EUR', 'wizzair', 3319, 6525);

INSERT INTO provider(currency, name, price, airport_id) VALUES('EUR', 'wizzair', 2261, 6746);
INSERT INTO provider(currency, name, price, airport_id) VALUES('EUR', 'airbaltic', 2565, 6746);
INSERT INTO provider(currency, name, price, airport_id) VALUES('EUR', 'lufthansa', 131, 6746);
INSERT INTO provider(currency, name, price, airport_id) VALUES('EUR', 'turkishairlines', 1799, 6746);

INSERT INTO country (code, continent, name) VALUES('LT', 'N/A', 'Lithuania');
INSERT INTO country (code, continent, name) VALUES('US', 'N/A', 'America');
INSERT INTO country (code, continent, name) VALUES('RU', 'N/A', 'Russia');

INSERT INTO region(id, code, continent, iso_country, name) VALUES(302821,'AE-DU','AS','AE','Dubai Emirate');
INSERT INTO region(id, code, continent, iso_country, name) VALUES(302822,'AE-DL','AK','AE','Dubai Emirate');
INSERT INTO region(id, code, continent, iso_country, name) VALUES(302824,'LT-SA','EU','LT','Šiauliai County');
INSERT INTO region(id, code, continent, iso_country, name) VALUES(302823,'LT-AL','EU','LT','Alytus County');



