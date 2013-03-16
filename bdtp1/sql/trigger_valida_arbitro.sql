-- Define un nuevo caracter delimitador en lugar del ';'
DELIMITER $$
CREATE
    TRIGGER `validarAsignacionArbitro` BEFORE INSERT
    ON `tp1bd`.`dirigidopor`
    
	FOR EACH ROW BEGIN
		DECLARE idPaisArbitro INT(11);
        DECLARE idPaisLocal INT(11);
		DECLARE idPaisVisita INT(11);

		-- Id del Pais del Arbitro
		SELECT a.idPais INTO @idPaisArbitro
		FROM arbitro a
		WHERE a.idArbitro = NEW.idArbitro;

		-- Ids de los paises de los equipos local y visit
		SELECT s1.idPais, s2.idPais
		INTO @idPaisLocal, @idPaisVisita
		FROM seleccion s1, seleccion s2, partido p
		WHERE
			p.nroPartido = NEW.nroPartido
			AND s1.idPais = p.idSeleccionLocal
			AND s2.idPais = p.idSeleccionVisitante;

        IF @idPaisArbitro = @idPaisLocal OR @idPaisArbitro = @idPaisVisita THEN
            -- Para invalidar la operacion, hacemos un INSERT de NULL invalido
            SET NEW.nroPartido = NULL;
        END IF;
    END;$$
DELIMITER ;
-- Se repone el caracter delimitar por defecto