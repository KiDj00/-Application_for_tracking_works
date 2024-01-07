--Storing Derivable Values

alter table podtipradova
add (ukupnaCena decimal(15,2));




CREATE OR REPLACE PACKAGE UkupnaCenaPaket AS
SifraPodtip number;
Pom number:=0;
END UkupnaCenaPaket;



CREATE OR REPLACE TRIGGER tr_ZabranIzmeneUkupnaCena
BEFORE UPDATE OF ukupnaCena ON podtipradova
FOR EACH ROW
BEGIN
IF (UkupnaCenaPaket.Pom=0) THEN
RAISE_APPLICATION_ERROR(-20000,'Nije dozvoljena direktna izmena kolone Ukupna cena.');
END IF;
END;


update podtipradova Set ukupnaCena=1 where podtipradovaid=1; 

-----------------------------------------------------------------------------------------------------------
--TRIGER POMOCU KOG SE DODREDJUJE SIFRA I POSTAVLJA NA GLOBALNU PROMELNJIVU 

CREATE OR REPLACE TRIGGER tr_SacuvajSifreStavki
BEFORE INSERT OR UPDATE OR DELETE ON STAVKAPODTIPARADOVA
FOR EACH ROW 
BEGIN	
IF (INSERTING OR UPDATING) THEN 
BEGIN 	
UkupnaCenaPaket.SifraPodtip:=:NEW.podtipradovaid;
END;
ELSE 
BEGIN 
UkupnaCenaPaket.SifraPodtip:=:OLD.podtipradovaid;
END; 
END IF; 
END;

-----------------------------------------------------------------------------------------------------------
--TRIGER KOJI POZIVA PROCEDURU RACUNANJA

CREATE OR REPLACE TRIGGER tr_OdrediSumuKolicine
 AFTER INSERT OR UPDATE OR DELETE ON STAVKAPODTIPARADOVA
DECLARE dok1 NUMBER:= UkupnaCenaPaket.SifraPodtip; 
BEGIN
UkupnaCenaPaket.Pom:=1;
SumaKolicineStavki(dok1); 
UkupnaCenaPaket.Pom:=0;
END;

--PROCEDURA ZA RACUNANJE UKUPNE CENE

CREATE OR REPLACE PROCEDURE SumaKolicineStavki
(sifraPodtip IN NUMBER) AS 
suma NUMBER:=0; 
BEGIN 	
SELECT SUM(sp.kolicina*sp.jedinicnacena) INTO suma 
FROM STAVKAPODTIPARADOVA sp  
WHERE sp.podtipradovaid=sifraPodtip; 
UPDATE podtipradova
SET ukupnaCena=suma 
WHERE podtipradovaid=sifraPodtip; 
END;
-----------------------------------------------------------------------------------------------------------
--TESTIRANJE

insert into STAVKAPODTIPARADOVA values (1,1,7,'a','TEST',10,150,'M'); 
UPDATE STAVKAPODTIPARADOVA SET kolicina=1 where stavkaid=7; 
UPDATE STAVKAPODTIPARADOVA SET jedinicnacena=101 where stavkaid=7; 
DELETE STAVKAPODTIPARADOVA where stavkaid=7; 
