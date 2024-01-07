
CREATE TABLE Mesto ( 
postanskiBroj NUMBER, 
nazivMesta VARCHAR2(30), 
constraint PK_Mesto PRIMARY KEY (postanskiBroj)
); 

CREATE TABLE RadniNalog ( 
radniNalogId NUMBER,
datum DATE,
nacelnikId NUMBER,
postanskiBroj NUMBER,
brojZahteva NUMBER,
constraint PK_RadniNalog PRIMARY KEY (radniNalogId),
constraint FK_RadniNalog FOREIGN KEY (nacelnikId) REFERENCES Nacelnik(nacelnikId),
FOREIGN KEY (postanskiBroj) REFERENCES Mesto(postanskiBroj),
FOREIGN KEY (brojZahteva) REFERENCES Zahtev(brojZahteva)
); 

CREATE TABLE Predmer ( 
predmerId NUMBER,
nazivPredmera VARCHAR2(100), 
radniNalogId NUMBER,
constraint PK_Predmer PRIMARY KEY (predmerId),
constraint FK_Predmer FOREIGN KEY (radniNalogId) REFERENCES RadniNalog(radniNalogId)
); 

CREATE TABLE PozivZaDostavljanjePounde ( 
pozivId NUMBER,
datum DATE,
nacelnikId NUMBER,
postanskiBroj NUMBER,
predmerId NUMBER,
constraint PK_PozivZaDostavljanjePounde PRIMARY KEY (pozivId),
constraint FK_PozivZaDostavljanjePounde FOREIGN KEY (nacelnikId) REFERENCES Nacelnik(nacelnikId),
FOREIGN KEY (postanskiBroj) REFERENCES Mesto(postanskiBroj),
FOREIGN KEY (predmerId) REFERENCES Predmer(predmerId)
);


CREATE TABLE StrucniTim ( 
timId NUMBER,
nazivTima VARCHAR2(30), 
constraint PK_StrucniTim PRIMARY KEY (timId)
); 

CREATE TABLE Sektor ( 
sektorId NUMBER,
nazivSektora VARCHAR2(30), 
nacelnikId NUMBER,
constraint PK_Sektor PRIMARY KEY (sektoriD),
constraint FK_Sektor FOREIGN KEY (nacelnikId) REFERENCES Nacelnik(nacelnikId)
); 


CREATE TABLE Radnik ( 
radnikId NUMBER,
jmbg VARCHAR2(13), 
ime VARCHAR2 (15),
prezime VARCHAR2 (30),
zvanje VARCHAR2(50),
telefon VARCHAR(15),

constraint PK_Radnik PRIMARY KEY (radnikId) 
); 

CREATE TABLE Nacelnik ( 
nacelnikId NUMBER,
jmbg VARCHAR2(13), 
ime VARCHAR2 (15),
prezime VARCHAR2 (30),
telefon VARCHAR(15),

constraint PK_Nacelnik PRIMARY KEY (nacelnikId) 
); 

CREATE TABLE Zahtev ( 
brojZahteva NUMBER,
nazivZahteva VARCHAR2(30), 
nacelnikId NUMBER,
postanskiBroj NUMBER,
constraint PK_Zahtev PRIMARY KEY (brojZahteva),
constraint FK_Zahtev FOREIGN KEY (nacelnikId) REFERENCES Nacelnik(nacelnikId),
FOREIGN KEY (postanskiBroj) REFERENCES Mesto(postanskiBroj)
); 

CREATE TABLE PredmetZahteva ( 
brojZahteva NUMBER,
predmetId NUMBER, 
nazivPredmeta VARCHAR2(100), 
opisPredmeta VARCHAR2(200), 
constraint PK_PredmetZahteva PRIMARY KEY (brojZahteva,predmetId),
constraint FK_PredmetZahteva FOREIGN KEY (brojZahteva) REFERENCES Zahtev(brojZahteva)
); 

CREATE TABLE PrijemZahteva ( 
brojZahteva NUMBER,
sektorId NUMBER, 
brojPrijema NUMBER, 
datumPrijema DATE, 
constraint PK_PrijemZahteva PRIMARY KEY (brojZahteva,brojPrijema,sektorId),
constraint FK_PrijemZahteva FOREIGN KEY (brojZahteva) REFERENCES Zahtev(brojZahteva),
FOREIGN KEY (sektorId) REFERENCES Sektor(sektorId)
); 

CREATE TABLE TipRadova ( 
tipRadovaId NUMBER,
nazivTipaRada VARCHAR2(30),
constraint PK_TipRadova PRIMARY KEY (tipRadovaId)
); 
Alter table tipradova add(predmerid number);
ALTER TABLE tipradova
ADD CONSTRAINT fk_tipradova
   FOREIGN KEY (predmerid)
   REFERENCES predmer (predmerid)
   ON DELETE CASCADE; -- ili odaberite drugu akciju ON DELETE ako je potrebno




CREATE TABLE PodtipRadova ( 
tipRadovaId NUMBER,
podtipRadovaId NUMBER, 
nazivPodtipaRada VARCHAR2(50),  
constraint PK_PodtipRadova PRIMARY KEY (podtipRadovaId,tipRadovaId), 
constraint FK_PodtipRadova FOREIGN KEY (tipRadovaId) REFERENCES TipRadova(tipRadovaId)
); 

CREATE TABLE StavkaPodtipaRadova ( 
tipRadovaId NUMBER,
podtipRadovaId NUMBER,
stavkaId NUMBER,
nazivTipaRada VARCHAR2(30),
opisRadova VARCHAR(200),
kolicina NUMBER,
jedinicnaCena DECIMAL(12,2),
jedinicaId NUMBER,
constraint PK_StavkaPodtipaRadova PRIMARY KEY (tipRadovaId,podtipRadovaId,stavkaId), 
constraint FK_StavkaPodtipaRadova FOREIGN KEY (podtipRadovaId,tipRadovaId) REFERENCES PodtipRadova(podtipRadovaId,tipRadovaId),
FOREIGN KEY (jedinicaId) REFERENCES JedinicaMere(jedinicaId)
); 


CREATE TABLE PredmetNaloga ( 
radniNalogId NUMBER,
predmetId NUMBER, 
opisPredmeta VARCHAR2(100), 
nazivPredmeta VARCHAR2(100), 
constraint PK_PredmetNaloga PRIMARY KEY (radniNalogId,predmetId),
constraint FK_PredmetNaloga FOREIGN KEY (radniNalogId) REFERENCES RadniNalog(radniNalogId)
); 

CREATE TABLE PredmetPoziva ( 
pozivId NUMBER,
predmetId NUMBER,
napomena VARCHAR2(300),
opisPredmeta VARCHAR2(100), 
kriterijum VARCHAR2(50), 
rokZaDostavu DATE,
constraint PK_PredmetPoziva PRIMARY KEY (pozivId,predmetId),
constraint FK_PredmetPoziva FOREIGN KEY (pozivId) REFERENCES PozivZaDostavljanjePounde(pozivId)
); 


CREATE TABLE JedinicaMere ( 
jedinicaId NUMBER,
nazivJedinice VARCHAR2(15), 
constraint PK_JedinicaMere PRIMARY KEY (jedinicaId)
);


INSERT INTO StrucniTim VALUES(1,'Tim intelektualaca');
INSERT INTO nacelnik VALUES(1,'1234567890123','Aleksa','Djordjevic','0631230123');
INSERT INTO zahtev VALUES(1010,'Zahtev za renoviranje WC-a',1,11000);
INSERT INTO radninalog VALUES(1,TO_DATE('11/11/2023','dd/mm/yyyy'),1,11000,1010);
INSERT INTO predmer VALUES(1,'Predmer za renoviranje WC-a',1);
INSERT INTO pozivzadostavljanjepounde VALUES(1,TO_DATE('19/11/2023','dd/mm/yyyy'),1,11000,1);
INSERT INTO Zapisnik VALUES(1,1000000,TO_DATE('19/11/2023','dd/mm/yyyy'),1,11000,1);

INSERT INTO mesto VALUES (11000,'Beograd');
INSERT INTO mesto VALUES (21000,'Novi Sad');
INSERT INTO TipRadova VALUES(1,'Gradjevinsko zanatski radovi');
INSERT INTO PodtipRadova VALUES(1,1,'Rusenje');
INSERT INTO jedinicaMere VALUES(1,'komad');
INSERT INTO jedinicaMere VALUES(2,'Metar kvadratni');
INSERT INTO jedinicaMere VALUES(3,'Metar');
INSERT INTO StavkaPodtipaRadova VALUES(1,1,1,'Gradjevinsko zanatski radovi','Demontaza sanitarija u dva kupatila, lavabo od keramike 4 komada, 2 metalna lavaboa...','2',45000,1);

