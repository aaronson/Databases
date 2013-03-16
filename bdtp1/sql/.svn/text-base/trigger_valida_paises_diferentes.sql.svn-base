-- Define un nuevo caracter delimitador en lugar del ';'
DELIMITER $$
CREATE
    TRIGGER `validarPaisesDiferentes` BEFORE INSERT
    ON `tp1bd`.`Partido`
    
	FOR EACH ROW BEGIN

        IF NEW.idSeleccionLocal = NEW.idSeleccionVisitante THEN
            -- Para invalidar la operacion, hacemos un INSERT de NULL invalido
            SET NEW.nroPartido = NULL;
        END IF;
    END;$$
DELIMITER ;
-- Se repone el caracter delimitar por defecto
