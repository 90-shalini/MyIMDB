select yearOfRelease,duration,title,subject from film,genre where film.id=3 and genre.id in (select genre_id from film where id=3);
 

truncate table actor;
truncate table actress;
truncate table director;
truncate table genre;
truncate table image;
truncate table film;


CREATE TABLE animals (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     PRIMARY KEY (id)
);

INSERT INTO animals (name) VALUES
    ('dog'),('cat'),('penguin'),
    ('lax'),('whale'),('ostrich');

create table image(
id int NOT NULL AUTO_INCREMENT,
name VARCHAR(50),
PRIMARY KEY(id)) 

DROP TABLE customers;

CREATE TABLE film(
id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY(id),
yearOfRelease INT,
duration INT,
title VARCHAR(250),
genre_id INT,FOREIGN KEY(genre_id) REFERENCES genre(id),
actor_id INT,FOREIGN KEY(actor_id) REFERENCES actor(id),
actress_id INT,FOREIGN KEY(actress_id) REFERENCES actress(id),
director_id INT,FOREIGN KEY(director_id) REFERENCES director(id),
popularity int,
awards enum('Y','N'),
image_id INT,FOREIGN KEY(image_id) REFERENCES image(id)
)

insert into genre (id,subject) values('1000','Comedy1');

ALTER TABLE genre AUTO_INCREMENT=1;