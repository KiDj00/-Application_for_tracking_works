-- KREIRANJE distinik tipa za dobijanje cene

CREATE OR REPLACE TYPE PIB_TIP AS OBJECT ( 
    pib_oznaka VARCHAR2(9) 
) INSTANTIABLE FINAL;

--- obrisi ovo jer nije struktuirani


------------------------------------------------

-- Primena struktuiranog tipa u tabelama

CREATE TABLE Kompanija ( 
kompanijaId NUMBER, 
pib PIB_TIP, 
nazivKompanije VARCHAR2 (50),
grad VARCHAR2 (30), 
constraint PK_Kompanija PRIMARY KEY (kompanijaId), 
check (REGEXP_LIKE (PIB.pib_oznaka,'[0-9]{9}'))  
); 
------------------------------------------------

-- Testiranje distinik tipa

INSERT INTO Kompanija(kompanijaId,pib,nazivkompanije,grad) VALUES (1, PIB_TIP('123123123'), 'Marko', 'Beograd' );
INSERT INTO Kompanija(kompanijaId,pib,nazivkompanije,grad) VALUES (2, PIB_TIP('126789345'), 'Zile +', 'Novi Sad' );
UPDATE Kompanija SET pib=pib_tip('987654321') where kompanijaid = 1;
SELECT * FROM Kompanija;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- KREIRANJE Struktuiranog tipa za dobijanje cene

CREATE OR REPLACE TYPE UKUPNA_CENA_TIP AS OBJECT ( 
vrednost DECIMAL(12,2), 
pdv DECIMAL(3,2),
ukupna_cena DECIMAL(12,2),
CONSTRUCTOR FUNCTION UKUPNA_CENA_TIP(vrednost DECIMAL, pdv DECIMAL) RETURN SELF AS RESULT,
MEMBER FUNCTION getCenaBezPdva RETURN DECIMAL, 
MEMBER FUNCTION getPdv RETURN DECIMAL, 
MEMBER FUNCTION getCenaSaPdvom RETURN DECIMAL
)INSTANTIABLE NOT FINAL; 


CREATE OR REPLACE TYPE BODY UKUPNA_CENA_TIP AS
    CONSTRUCTOR FUNCTION UKUPNA_CENA_TIP(vrednost DECIMAL, pdv DECIMAL) RETURN SELF AS RESULT IS
    BEGIN
    IF vrednost <0  or pdv<0 THEN
    RAISE_APPLICATION_ERROR(-20000,'Vrednost cene i PDV ne mogu biti negativni brojevi');
    END IF;
       IF pdv>=1 THEN
    RAISE_APPLICATION_ERROR(-20000,'PDV ne moze biti veci od 100% cene bez PDV-a');
    END IF;
        SELF.vrednost:=vrednost;
        SELF.pdv:=pdv;
        SELF.ukupna_cena:= vrednost+ vrednost*pdv;
        RETURN;
    END;
MEMBER FUNCTION getCenaBezPdva RETURN DECIMAL IS 
    BEGIN 
        RETURN SELF.vrednost; 
    END; 

MEMBER FUNCTION getPdv RETURN DECIMAL IS 
    BEGIN 
        RETURN SELF.pdv; 
    END;

MEMBER FUNCTION getCenaSaPdvom RETURN DECIMAL IS 
    BEGIN 
        RETURN SELF.ukupna_cena; 
    END;
END; 

------------------------------------------------

-- Primena struktuiranog tipa u tabelama

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
); 


CREATE TABLE Zapisnik ( 
zapisnikId NUMBER,
vrednost UKUPNA_CENA_TIP, 
datum DATE,
pozivId NUMBER,
postanskiBroj NUMBER,
timId NUMBER,
constraint PK_Zapisnik PRIMARY KEY (zapisnikId),
constraint FK_Zapisnik FOREIGN KEY (pozivId) REFERENCES PozivZaDostavljanjePounde(pozivId),
FOREIGN KEY (postanskiBroj) REFERENCES Mesto(postanskiBroj),
FOREIGN KEY (timId) REFERENCES StrucniTim(timId)
);


CREATE TABLE StavkaZapisnika ( 
zapisnikId NUMBER,
stavkaID NUMBER,
nazivKompanije VARCHAR2 (50),
vrednost UKUPNA_CENA_TIP,
rokZaObavljanje DATE,
opcijaPonude NUMBER,
garantniRok DATE,
kompanijaId NUMBER,
constraint PK_StavkaZapisnika PRIMARY KEY (zapisnikId,stavkaID),
constraint FK_StavkaZapisnika FOREIGN KEY (zapisnikId) REFERENCES Zapisnik(zapisnikId),
FOREIGN KEY (kompanijaId) REFERENCES Kompanija(kompanijaId)
); 
------------------------------------------------

-- Testiranje struktuiranog tipa

INSERT INTO StavkaZapisnika VALUES (1,5,'VIRING',ukupna_cena_tip(1231231,-0.1),TO_DATE('19/11/2023','dd/mm/yyyy'),90,TO_DATE('19/11/2025','dd/mm/yyyy'),1);
INSERT INTO StavkaZapisnika VALUES (1,5,'VIRING',ukupna_cena_tip(-999999,0.15),TO_DATE('19/11/2023','dd/mm/yyyy'),90,TO_DATE('19/11/2025','dd/mm/yyyy'),1);
INSERT INTO StavkaZapisnika VALUES (1,9,'VIRING',ukupna_cena_tip(999999,0.2),TO_DATE('19/11/2023','dd/mm/yyyy'),90,TO_DATE('19/11/2025','dd/mm/yyyy'),1);
INSERT INTO StavkaZapisnika VALUES (1,2,'VIRING',ukupna_cena_tip(1010100,0.21),TO_DATE('19/11/2023','dd/mm/yyyy'),90,TO_DATE('19/11/2025','dd/mm/yyyy'),2);
INSERT INTO StavkaZapisnika VALUES (1,8,'VIRING',ukupna_cena_tip(999,0.21),TO_DATE('19/11/2023','dd/mm/yyyy'),90,TO_DATE('19/11/2025','dd/mm/yyyy'),2);

SELECT sz.nazivKompanije,sz.vrednost.getCenaSaPdvom(),sz.vrednost.getCenaBezPdva(),sz.vrednost.getPdv() FROM StavkaZapisnika sz;
SELECT * FROM StavkaZapisnika;
Update StavkaZapisnika Set vrednost = ukupna_cena_tip(999999,0.15) , kompanijaid =1 where stavkaid=1;

INSERT INTO Zapisnik VALUES(1,ukupna_cena_tip(1000000,0.15),TO_DATE('19/11/2023','dd/mm/yyyy'),1,11000,1);
INSERT INTO Zapisnik VALUES(2,ukupna_cena_tip(3000000,0.15),TO_DATE('19/11/2023','dd/mm/yyyy'),1,21000,2);

SELECT z.zapisnikId,z.vrednost.getCenaSaPdvom(),z.vrednost.getCenaBezPdva(),z.vrednost.getPdv() FROM Zapisnik z;
SELECT * FROM Zapisnik;


INSERT INTO Narudzbenica VALUES(1,ukupna_cena_tip(1000000,0.15),TO_DATE('23/12/2023','dd/mm/yyyy'),TO_DATE('23/12/2024','dd/mm/yyyy'),'3 rate', TO_DATE('30/11/2018','dd/mm/yyyy'),11000,1,1,1);
INSERT INTO Narudzbenica VALUES(2,ukupna_cena_tip(999.99,0.20),TO_DATE('23/12/2022','dd/mm/yyyy'),TO_DATE('23/12/2024','dd/mm/yyyy'),'3 rate', TO_DATE('30/11/2022','dd/mm/yyyy'),11000,1,1,1);
INSERT INTO Narudzbenica VALUES(3,ukupna_cena_tip(999.99,0.8),TO_DATE('23/11/2023','dd/mm/yyyy'),TO_DATE('23/12/2024','dd/mm/yyyy'),'3 rate', TO_DATE('30/11/2023','dd/mm/yyyy'),11000,1,1,1);
INSERT INTO Narudzbenica VALUES(5,ukupna_cena_tip(999.99,0.8),TO_DATE('23/11/2023','dd/mm/yyyy'),TO_DATE('23/12/2024','dd/mm/yyyy'),'3 rate', TO_DATE('30/11/2023','dd/mm/yyyy'),11000,1,1,1);


SELECT n.narudzbenicaId,n.cena.getCenaSaPdvom(),n.cena.getCenaBezPdva(),n.cena.getPdv(),n.datum FROM Narudzbenica n;
SELECT * FROM Narudzbenica;





	



