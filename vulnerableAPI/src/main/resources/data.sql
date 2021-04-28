CREATE TABLE users (
                         id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
                         username VARCHAR(200) NOT NULL ,
                         password VARCHAR(200) NOT NULL ,
                         money INT NOT NULL
);

INSERT INTO users (username, password, money) VALUES
('secretUser@gmail.com', 'verySecretPassword', 1000000);