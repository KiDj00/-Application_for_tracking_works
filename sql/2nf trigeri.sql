-- 1.	Triger koji prilikom izmene vrednosti kolone nazivTipaRada u tabeli TipRadova, postavlja
--      vrednost kolone nazivTipaRada odgovarajucim rekordima tabele StavkaPodtipa.

CREATE OR REPLACE TRIGGER TR_TIPRADOVA_UPDATE_NAZIV_TIPA_RADA 
BEFORE UPDATE OF nazivTipaRada ON TipRadova
FOR EACH ROW
DECLARE 
  v_noviNaziv VARCHAR2(50); 
  v_tipRadovaId NUMBER; 
BEGIN 
  v_noviNaziv := :NEW.nazivTipaRada; 
  v_tipRadovaId := :NEW.tipRadovaId;
  DBMS_OUTPUT.PUT_LINE('TR_TIPRADOVA_UPDATE_NAZIV_TIPA_RADA is executing. tipRadovaId: ' || v_tipRadovaId);
 
  UPDATE StavkaPodtipaRadova 
  SET nazivTipaRada = v_noviNaziv
  WHERE tipRadovaId = v_tipRadovaId; 
  -- Nastavite sa izmenama samo ako je dozvoljeno
END;
/
UPDATE TipRadova SET nazivTipaRada='Radovi za molere 123' where tipRadovaId=1;
SELECT spr.tipRadovaId,spr.nazivTipaRada FROM StavkaPodtipaRadova spr WHERE tipRadovaId=1;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 2.	Triger koji postavlja vrednost kolone nazivTipaRada, tabele StavkaPodtipa upisuje unetu 
--      vrednost kolone nazivTipaRada tabele TipRadova na osnovu vrednosti SifraTipaRadova

CREATE OR REPLACE TRIGGER TR_STAVKAPODTIPARADOVA_INSERT_NAZIV_TIPA_RADA 
BEFORE INSERT 
ON StavkaPodtipaRadova
FOR EACH ROW 
DECLARE 
v_nazivTipaRada varchar2 (50); 
BEGIN 
SELECT tp.nazivTipaRada
INTO v_nazivTipaRada
FROM TipRadova tp 
WHERE tp.tipRadovaId = :NEW.tipRadovaId; 
 
:NEW.nazivTipaRada:= v_nazivTipaRada; 
END; 


INSERT INTO StavkaPodtipaRadova (tipradovaid,podtipradovaid,stavkaid,opisradova,kolicina,jedinicnacena,NAZIVJEDINICEMERE) VALUES(1,1,7,'Zidna tehnika',5,100,'M2');
SELECT * FROM StavkaPodtipaRadova WHERE stavkaid=7;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 3.	Triger koji spre?ava direktnu izmenu kolone nazibTipaRada tabele StavkaPodtipa

CREATE OR REPLACE TRIGGER TR_STAVKAPODTIPARADOVA_FORBID_UPDATE_NAZIV_TIPA_RADA 
BEFORE UPDATE OF nazivTipaRada ON StavkaPodtipaRadova 
FOR EACH ROW
DECLARE
  v_callStack VARCHAR2(4000); -- Pretpostavljena maksimalna dužina call stack-a
  v_allowUpdate NUMBER;
BEGIN
  v_callStack := DBMS_UTILITY.FORMAT_CALL_STACK;
 
  DBMS_OUTPUT.PUT_LINE('TR_STAVKAPODTIPARADOVA_FORBID_UPDATE_NAZIV_TIPA_RADA is executing. Call Stack: ' || v_callStack);
 
  -- Proverite da li je drugi trigger u call stack-u
  IF INSTR(v_callStack, 'TR_TIPRADOVA_UPDATE_NAZIV_TIPA_RADA') > 0 THEN
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
    RAISE_APPLICATION_ERROR (-20000, 'Zabranjeno azuriranje kolone nazivTipaRada u okviru tabele StavkaPodtipaRadova!!!'); 
  END IF; 
END;

UPDATE StavkaPodtipaRadova SET nazivTipaRada='Radovi za molere 1' where stavkaid=1;
