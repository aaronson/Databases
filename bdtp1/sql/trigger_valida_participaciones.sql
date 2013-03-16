 -- Define un nuevo caracter delimitador en lugar del ';'
DELIMITER $$
CREATE
    TRIGGER `validarParticipaciones` BEFORE INSERT
    ON `tp1bd`.`participacion`
    
	FOR EACH ROW BEGIN
		DECLARE cantTitulares INT(11);
		DECLARE cantJugadores INT(11);
		DECLARE idPaisJugador INT(11);

		SELECT j.idSeleccion INTO @idPaisJugador
               FROM integrante j
               WHERE j.nroPasaporte = NEW.nroPasaporte;

		SELECT count(1) Cantidad_Jugadores 
		INTO @cantJugadores
		FROM participacion p , integrante i 
		WHERE p.nroPasaporte = i.nroPasaporte and p.nroPartido = NEW.nroPartido and i.idSeleccion = @idPaisJugador
		GROUP BY idSeleccion, nroPartido;

		SELECT count(1) Cantidad_Jugadores 
		INTO @cantTitulares
		FROM participacion p , integrante i 
		WHERE p.nroPasaporte = i.nroPasaporte and p.nroPartido = NEW.nroPartido and i.idSeleccion = @idPaisJugador
				and esTitular 
		GROUP BY idSeleccion, nroPartido;

        IF @cantJugadores >= 12  or (@cantTitulares >=5 and new.esTitular) THEN
            -- Para invalidar la operacion, hacemos un INSERT de NULL invalido
            SET NEW.idParticipacion = NULL;
        END IF;
    END;$$
DELIMITER ;
-- Se repone el caracter delimitar por defecto
