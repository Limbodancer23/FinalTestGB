create database Human_friends;
use Human_friends;
Create table Dogs(id SERIAL, petID INTEGER, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, Commands VARCHAR(100));
Create table Cats(id SERIAL, petID INTEGER, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, Commands VARCHAR(100));
Create table Hamsters(id SERIAL, petID INTEGER, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, Commands VARCHAR(100));

Create table Horses(id SERIAL, petID INTEGER, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, Commands VARCHAR(100));
Create table Donkeys(id SERIAL, petID INTEGER, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, Commands VARCHAR(100));
Create table Camels(id SERIAL, petID INTEGER, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, Commands VARCHAR(100));

create table Pets( id serial, Name VARCHAR(45) NOT NULL, birthDate DATE NOT NULL, kind VARCHAR(30), Commands VARCHAR(50));

insert into Pets (Name, birthDate, kind, Commands) values
 ('Charly', '2020-11-21', 'Dog', 'apport, sit, come, play dead'),
 ('Sam', '2014-08-14', 'Cat', 'sit, come'),
 ('Bigles', '2022-09-10', 'Hamster', NULL);
 insert into Pets (Name, birthDate, kind, Commands) values
 ('Lancelot', '2009-09-15', 'Dog', 'sit, come, give a paw'),
 ('Bosya', '2004-04-04', 'Cat', NULL),
 ('Jerry', '2023-03-13', 'Hamster', 'play dead');

insert into Dogs(PetID, Name, birthDate, Commands) select id, Name, birthDate, Commands from Pets where kind = 'Dog';
insert into Cats(PetID, Name, birthDate, Commands) select id, Name, birthDate, Commands from Pets where kind = 'Cat';
insert into Hamsters(PetID, Name, birthDate, Commands) select id, Name, birthDate, Commands from Pets where kind = 'Hamster';
create table Pack_animals(id serial, Name VARCHAR(45), birthDate DATE, kind VARCHAR(30) NOT NULL, Commands VARCHAR(100));

insert into Pack_animals(Name, birthDate, kind, Commands) values
 ('Mary', '2017-02-17', 'Horse', 'come, trot, gallop'),
 ('Storm', '2020-10-10', 'Donkey', 'walk, carry'),
 ('Sandy', '2016-06-16', 'Camel', 'come, gallop'),
 ('Plotva', '2015-05-20', 'Horse', 'trot, gallop'), 
 ('Carrot', '2022-12-07', 'Donkey', 'walk, come, carry'),
 ('Siren', '2021-01-01', 'Camel', 'gallop');
 
insert into Horses(PetID, Name, birthDate, Commands) select id, Name, birthDate, Commands from Pack_animals where kind = 'Horse';
insert into Donkeys(PetID, Name, birthDate, Commands) select id, Name, birthDate, Commands from Pack_animals where kind = 'Donkey';
insert into Camels(PetID, Name, birthDate, Commands) select id, Name, birthDate, Commands from Pack_animals where kind = 'Camel';
 
TRUNCATE TABLE Camels;

select * from Horses
UNION ALL
select * from Donkeys;

Create table youngAnimals
(id SERIAL, Name VARCHAR(30), birthDate DATE, kind VARCHAR(30),Commands VARCHAR(100), age VARCHAR(50));

insert into youngAnimals(Name, birthDate, kind, Commands, age)(
 Select Name, birthDate, kind, Commands, CONCAT_WS(
	' ',
    CONCAT_WS(' years', TIMESTAMPDIFF(year, birthDate, now()), ''),
    CONCAT_WS('', TIMESTAMPDIFF(month, birthDate, now()) - (TIMESTAMPDIFF(year, birthDate, now()) * 12), ' months')
    )
 from Pack_animals where TIMESTAMPDIFF(DAY, birthDate, now()) between 366 and 1097);
 
insert into youngAnimals(Name, birthDate, kind, Commands, age) (
Select Name, birthDate, kind, Commands, CONCAT_WS(
' ',
 CONCAT_WS(' years', TIMESTAMPDIFF(year, birthDate, now()), ''),
 CONCAT_WS('', TIMESTAMPDIFF(month, birthDate, now()) - (TIMESTAMPDIFF(year, birthDate, now()) * 12), ' months')
 ) from Pets where TIMESTAMPDIFF(DAY, birthDate, now()) between 366 and 1097);
 
 with CatData as (select id, Name as cat_name, birthDate, Commands from Cats),
 DogData as (select id, Name as dog_name, birthDate, Commands from Dogs),
 HamsterData as (select id, Name as hamster_name, birthDate, Commands from Hamsters),
 HorseData as (select id, Name as Horse_name, birthDate, Commands from Horses),
 DonkeyData as (select id, Name as donkey_name, birthDate, Commands from Donkeys)
 SELECT id, cat_name, NULL as dog_name, NULL as hamster_name, NULL as horse_name, NULL as donkey_name, birthDate, Commands FROM CatData
 UNION ALL SELECT id, NULL as cat_name, dog_name, NULL as hamster_name, NULL as horse_name, NULL as donkey_name, birthDate, Commands FROM DogData
 UNION ALL  SELECT id, NULL as cat_name, NULL as dog_name, hamster_name, NULL as horse_name, NULL as donkey_name, birthDate, Commands FROM HamsterData
 UNION ALL SELECT id, NULL as cat_name, NULL as dog_name, NULL as hamster_name, horse_name, NULL as donkey_name, birthDate, Commands FROM HorseData
 UNION ALL SELECT id, NULL as cat_name, NULL as dog_name, NULL as hamster_name, NULL as horse_name, donkey_name, birthDate, Commands FROM DonkeyData;
 