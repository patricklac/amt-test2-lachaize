DROP DATABASE jury;
CREATE DATABASE jury;
create user if not exists 'adm'@'localhost' identified by 'adm'; 
grant all privileges on jury.* to 'adm'@'localhost'; 

use jury;
CREATE TABLE themes (
  codTh CHAR(2) not null,
  nom VARCHAR(20),
  PRIMARY KEY(codTh),
  UNIQUE(nom)
);

insert into themes values('RH','Ressources humaines');
insert into themes values('PG','Programmation');
insert into themes values('RO','Roman');

CREATE TABLE critiques (
  codcri INT NOT NULL AUTO_INCREMENT,
  prenom VARCHAR(10),
  nom VARCHAR(10),
  codTh CHAR(2),
  PRIMARY KEY(codcri),
  UNIQUE(prenom,nom),
  FOREIGN KEY(codTH) REFERENCES themes (codth)
);

insert into critiques(prenom,nom,codTH) values('Jean','Alembert','RH');
insert into critiques(prenom,nom,codTH) values('Michele','Baudet','RH');
insert into critiques(prenom,nom,codTH) values('Thierry','Bass','PG');
insert into critiques(prenom,nom,codTH) values('Margaret','Barrandon','PG');
insert into critiques(prenom,nom,codTH) values('Julie','Chaves','RO');
insert into critiques(prenom,nom,codTH) values('Denis','Couget','RO');

CREATE TABLE livres (
  codLiv INT NOT NULL AUTO_INCREMENT,
  titre VARCHAR(40),
  codTh CHAR(2),
  note INT(1),
  selection INT(1),
  PRIMARY KEY(codLiv),
  UNIQUE(titre),
  FOREIGN KEY(codTH) REFERENCES themes (codTH)
);

insert into livres(titre,codTH,note,selection) values('Le coaching quotidien','RH',5,0);
insert into livres(titre,codTH,note,selection) values('Sachez evaluer votre personnel','RH',0,0);
insert into livres(titre,codTH,note,selection) values('Maitriser l entretien d evaluation','RH',0,0);
insert into livres(titre,codTH,note,selection) values('Advanced Java 2 Platform','PG',2,0);
insert into livres(titre,codTH,note,selection) values('Design Paterns','PG',6,1);
insert into livres(titre,codTH,note,selection) values('Mastering Delphi 6','PG',3,0);
insert into livres(titre,codTH,note,selection) values('A la recherche du temps perdu','RO',0,0);
insert into livres(titre,codTH,note,selection) values('Le comte de Monte-Cristo','RO',0,0);
insert into livres(titre,codTH,note,selection) values('Les frères Karamazov','RO',0,0);

select * from critiques;
select * from livres;
