ALTER TABLE STAVKAPODTIPARADOVA ADD nazivJediniceMere VARCHAR2(30);
ALTER TABLE STAVKAPODTIPARADOVA ADD CONSTRAINT CHECKJM CHECK(nazivJediniceMere in ('KOM','M','M2','PAUSALNO'));
ALTER TABLE STAVKAPODTIPARADOVA DROP CONSTRAINT CHECKJM;

ALTER TABLE STAVKAPODTIPARADOVA
DROP COLUMN JEDINICAID;
DROP TABLE JedinicaMere;

insert into STAVKAPODTIPARADOVA values (1,1,9,'a','ugradnja',2,1500,'CM'); 

CREATE OR REPLACE NONEDITIONABLE PROCEDURE DODAVANJE_NOVE_JEDINICE_MERE (
    JM IN VARCHAR2
) AS 
    l_search_condition VARCHAR2(500 CHAR);
BEGIN
    -- Dobijanje trenutnog search_condition za CHECKJM constraint
    SELECT search_condition 
    INTO l_search_condition
    FROM all_constraints
    WHERE constraint_name = 'CHECKJM';

    -- Brisanje postoje?eg CHECKJM constraint-a
    EXECUTE IMMEDIATE 'ALTER TABLE STAVKAPODTIPARADOVA DROP CONSTRAINT CHECKJM';

    -- Dodavanje novog CHECKJM constraint-a sa dodatkom novog JM
    EXECUTE IMMEDIATE 'ALTER TABLE STAVKAPODTIPARADOVA ADD CONSTRAINT CHECKJM CHECK (' || l_search_condition || ' OR (NAZIVJEDINICEMERE = ''' || JM || '''))';
END DODAVANJE_NOVE_JEDINICE_MERE;
/
BEGIN
dodavanje_nove_jedinice_mere('M3');
END;

/*CREATE OR REPLACE PROCEDURE DodajNovuJedinicuMere (novaJedinicaMere IN NVARCHAR2)
IS
    trenutneJediniceMere NVARCHAR2(4000);

BEGIN
    -- Dobijamo trenutne uslove ograni?enja
    SELECT LISTAGG(column_name, ',') WITHIN GROUP (ORDER BY position) 
    INTO trenutneJediniceMere
    FROM ALL_CONS_COLUMNS
    WHERE CONSTRAINT_NAME = 'CHECKJM' AND TABLE_NAME = 'STAVKAPODTIPARADOVA';

    -- Dodajemo novu jedinicu mere u trenutne uslove ograni?enja
    trenutneJediniceMere := trenutneJediniceMere || ',''' || novaJedinicaMere || '''';

    -- Ažuriramo ograni?enje
    EXECUTE IMMEDIATE 'ALTER TABLE STAVKAPODTIPARADOVA DROP CONSTRAINT CHECKJM';
    EXECUTE IMMEDIATE 'ALTER TABLE STAVKAPODTIPARADOVA ADD CONSTRAINT CHECKJM CHECK (nazivJediniceMere IN (' || trenutneJediniceMere || '))';
END;

BEGIN
dodajnovujedinicumere('M3');
END;
*/


SELECT
    constraint_name,
    table_name,
    search_condition
FROM
    all_constraints
WHERE
    table_name = 'STAVKAPODTIPARADOVA'
    AND constraint_name = 'CHECKJM'
    AND constraint_type = 'C';
