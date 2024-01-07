-- HORIZONTALNO PARTICIONISANJE
-- kreiranje opsega na osnovu datuma narudzbenice
CREATE TABLE Narudzbenica ( 
narudzbenicaId NUMBER,
cena UKUPNA_CENA_TIP,
rokIzvrsenja DATE, 
garantniRok DATE, 
nacinPlacanja VARCHAR2(50),
datum DATE,
postanskiBroj NUMBER,
zapisnikId NUMBER,
nacelnikId NUMBER,
kompanijaId NUMBER,

constraint PK_Narudzbenica PRIMARY KEY (narudzbenicaId),
constraint FK_Narudzbenica FOREIGN KEY (postanskiBroj) REFERENCES Mesto(postanskiBroj),
FOREIGN KEY (zapisnikId) REFERENCES Zapisnik(zapisnikId),
FOREIGN KEY (nacelnikId) REFERENCES Nacelnik(nacelnikId),
FOREIGN KEY (kompanijaId) REFERENCES Kompanija(kompanijaId)
)
PARTITION BY RANGE(DATUM)(

PARTITION StareNarudzbenice VALUES LESS THAN (TO_DATE('2021/01/01','yyyy/mm/dd')),
PARTITION Narudzbenice2021 VALUES LESS THAN (TO_DATE('2022/01/01','yyyy/mm/dd')),
PARTITION Narudzbenice2022 VALUES LESS THAN (TO_DATE('2023/01/01','yyyy/mm/dd')),
PARTITION Narudzbenice2023 VALUES LESS THAN (TO_DATE('2024/01/01','yyyy/mm/dd')),
PARTITION NoveNarudzbenice VALUES LESS THAN (TO_DATE('3000/01/01','yyyy/mm/dd'))
);

INSERT INTO Narudzbenica VALUES(5,ukupna_cena_tip(999.99,0.8),TO_DATE('23/11/2018','dd/mm/yyyy'),TO_DATE('23/12/2024','dd/mm/yyyy'),'3 rate', TO_DATE('30/10/2023','dd/mm/yyyy'),11000,1,1,1);
select * FROM Narudzbenica PARTITION (StareNarudzbenice); 
select * FROM Narudzbenica PARTITION (Narudzbenice2023);
---------------------------------------------------------------------------------------------------------
-- VERTIKALNO PARTICIONISANJE
-- kreiranje tabela radnik detalji koji ce sadrzati dodatne informacije o radniku

CREATE TABLE RadnikDetalji(
radnikId NUMBER PRIMARY KEY,
jmbg VARCHAR2(13), 
zvanje VARCHAR2(50),
telefon VARCHAR(15)
);


INSERT INTO RadnikDetalji(radnikId,jmbg,zvanje,telefon)
SELECT radnikId,jmbg,zvanje,telefon
FROM Radnik;

ALTER TABLE Radnik
SET UNUSED(jmbg,zvanje,telefon);

ALTER TABLE Radnik
DROP UNUSED COLUMNS;

CREATE VIEW RadnikView
AS SELECT * FROM Radnik NATURAL JOIN RadnikDetalji;
---------------------------------------------------------------------------------------------------------
-- pravljenje trigera za insert update i delete

CREATE OR REPLACE TRIGGER tr_RadnikView
INSTEAD OF INSERT OR UPDATE OR DELETE ON RadnikView
FOR EACH ROW
DECLARE
v_radnikId NUMBER;

BEGIN
IF INSERTING THEN
INSERT INTO Radnik(radnikId,ime,prezime) VALUES (:new.radnikId,:NEW.ime,:NEW.prezime); 
INSERT INTO RadnikDetalji(radnikId,jmbg,zvanje,telefon) VALUES (:new.radnikId,:new.jmbg,:new.zvanje,
:new.telefon);

ELSIF UPDATING THEN
UPDATE Radnik SET ime= :NEW.ime,prezime = :NEW.prezime WHERE radnikId=: new.radnikId;
UPDATE RadnikDetalji SET jmbg= :NEW.jmbg,zvanje = :NEW.zvanje,telefon= :NEW.telefon WHERE radnikId=: new.radnikId;


ELSIF DELETING THEN
DELETE FROM Radnik WHERE radnikId=:old.radnikId;
DELETE FROM RadnikDetalji WHERE radnikId=:old.radnikId;
END IF;
END;


INSERT INTO RadnikView VALUES (3,'Luka','Lukic','1112223334445','Majstor','06598765343');
Update RadnikView SET  ime='Aleksa'  WHERE radnikId=3 ;
Delete RadnikView WHERE radnikId=3;