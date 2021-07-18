CREATE database airport
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
CREATE USER 'airport'@'%' IDENTIFIED BY 'airport_password';
GRANT ALL PRIVILEGES ON airport.* TO 'airport'@'%' WITH GRANT OPTION;

CREATE database airport_batch
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
CREATE USER 'airport_batch'@'%' IDENTIFIED BY 'airport_batch_password';
GRANT ALL PRIVILEGES ON airport.* TO 'airport_batch'@'%' WITH GRANT OPTION;
