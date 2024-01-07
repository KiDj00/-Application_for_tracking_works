-- 1.	Triger koji prilikom promene vrednosti kolone nazivKompanije tabele Kompanija, 
--      postavlja vrednost kolone nazivKompanije odgovarajucim rekordima tabele 
--      StavkaZapisnika na osnovu spoljnog kljuca kompanijaId 

CREATE OR REPLACE TRIGGER TR_KOMPANIJA_UPDATE
AFTER UPDATE OF nazivKompanije ON Kompanija  
FOR EACH ROW    
DECLARE 
    v_noviNaziv VARCHAR2(50); 
    v_kompanijaid NUMBER; 
BEGIN  
  v_noviNaziv := :NEW.nazivKompanije; 
  v_kompanijaid := :NEW.kompanijaid;
  DBMS_OUTPUT.PUT_LINE('TR_KOMPANIJA_UPDATE is executing. kompanijaid: ' || v_kompanijaid);
UPDATE StavkaZapisnika 
SET nazivKompanije = v_noviNaziv
WHERE kompanijaid = v_kompanijaid;  
END;
 
UPDATE Kompanija SET nazivKompanije='Peki' where kompanijaId=2;
SELECT K.kompanijaId,K.nazivKompanije FROM Kompanija K WHERE kompanijaId=2;
SELECT sz.nazivKompanije FROM StavkaZapisnika sz WHERE kompanijaId=2;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 2.	Triger koji postavlja vrednost kolone nazivKompanije u tabeli StavkaZapisnika
--      na osnovu vrednosti kompanijaId  

CREATE OR REPLACE TRIGGER TR_STAVKAZAPISNIKA_INSERT  
BEFORE INSERT ON StavkaZapisnika 
FOR EACH ROW  
DECLARE  
v_nazivKompanije VARCHAR2(50);  
BEGIN  
SELECT nazivKompanije INTO v_nazivKompanije
FROM Kompanija 
WHERE kompanijaId = :NEW.kompanijaId;  
 
:NEW.nazivKompanije:=v_nazivKompanije;  
END;

INSERT INTO StavkaZapisnika (zapisnikId,stavkaId,vrednost,rokzaobavljanje,opcijaponude,garantnirok,kompanijaid) VALUES (1,3,ukupna_cena_tip(1312122,0.18),TO_DATE('19/11/2023','dd/mm/yyyy'),90,TO_DATE('19/11/2025','dd/mm/yyyy'),1);
SELECT * FROM StavkaZapisnika WHERE stavkaId=3;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 3.	Triger koji nakon izmene vrednosti kolone kompanijaId, pokrece se triger
--      koji menja vrednost u koloni NazivKompanije

CREATE OR REPLACE TRIGGER TR_STAVKAZAPISNIKA_UPDATE_KOMPANIJAID  
BEFORE UPDATE OF kompanijaId ON StavkaZapisnika 
FOR EACH ROW  
DECLARE  
v_nazivKompanije VARCHAR2(30);  
BEGIN  
SELECT k.nazivKompanije INTO v_nazivKompanije  
FROM Kompanija k  
WHERE k.kompanijaid = :NEW.kompanijaId;  
 
:NEW.nazivKompanije  := v_nazivKompanije;  
END;

UPDATE StavkaZapisnika SET kompanijaId=2 where stavkaid=3;
Select sz.kompanijaid,sz.nazivkompanije,sz.stavkaid FROM StavkaZapisnika  sz WHERE stavkaid=3;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 4.	Triger koji spre?ava direktnu izmenu kolone nazivKompanije tabele StavkaZapisnika

CREATE OR REPLACE TRIGGER TR_FORBID_KOMPANIJA_UPDATE 
BEFORE UPDATE OF nazivKompanije ON StavkaZapisnika 
FOR EACH ROW
DECLARE
  v_callStack VARCHAR2(4000); -- Pretpostavljena maksimalna dužina call stack-a
  v_allowUpdate NUMBER;
BEGIN
  v_callStack := DBMS_UTILITY.FORMAT_CALL_STACK;
 
  DBMS_OUTPUT.PUT_LINE('TR_FORBID_KOMPANIJA_UPDATE is executing. Call Stack: ' || v_callStack);
 
  -- Proverite da li je drugi trigger u call stack-u
  IF INSTR(v_callStack, 'TR_KOMPANIJA_UPDATE') > 0 THEN
    -- Trigger zabrane je aktiviran od strane drugog triggera, dozvolite izmenu
    v_allowUpdate := 1;
  ELSE
    -- Drugi trigger nije u call stack-u, zabranite izmenu
    v_allowUpdate := 0;
  END IF;
 
  -- Proverite da li je dozvoljena izmena
  IF v_allowUpdate = 1 THEN
    NULL;
  ELSE
    RAISE_APPLICATION_ERROR (-20000, 'Nije dozvoljena direktna izmena naziva kompanije u okviru tabele StavkaZapisnika!!!'); 
  END IF;
END;


UPDATE StavkaZapisnika SET nazivKompanije= 'radovi ' where stavkaId=1;



