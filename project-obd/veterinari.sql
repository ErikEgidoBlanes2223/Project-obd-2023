create database veterinari;

use veterinari;

CREATE TABLE especies (
    id int auto_increment PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE propietari (
    id int auto_increment PRIMARY KEY,
    full_name VARCHAR(100),
    age INT,
    email VARCHAR(120)
);

CREATE TABLE animals (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    escape_attempts INT NOT NULL,
    neutered BOOLEAN NOT NULL,
    weight_kg DECIMAL(10,2) NOT NULL,
    especies_id INT NOT NULL,
    propietari_id INT NOT NULL,
    FOREIGN KEY (especies_id) REFERENCES especies(id),
    FOREIGN KEY (propietari_id) REFERENCES propietari(id)
);

CREATE TABLE vets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    date_of_graduation DATE NOT NULL
);

CREATE TABLE specializations (
    id SERIAL PRIMARY KEY,
    vet_id INTEGER REFERENCES vets(id) ON UPDATE CASCADE ON DELETE CASCADE,
    especies_id INTEGER REFERENCES especies(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE visits (
    id SERIAL PRIMARY KEY,
    animal_id INTEGER REFERENCES animals(id) ON UPDATE CASCADE ON DELETE CASCADE,
    vet_id INTEGER REFERENCES vets(id) ON UPDATE CASCADE ON DELETE CASCADE,
    visit_date DATE NOT NULL
);