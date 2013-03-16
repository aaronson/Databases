USE `TP1BD`;
DROP procedure IF EXISTS `Selecciones_Usaron_Todos_Jugs`;

DELIMITER $$
USE `TP1BD`$$
CREATE PROCEDURE `TP1BD`.`Selecciones_Usaron_Todos_Jugs` ()
BEGIN

select 
    p.nombre
from
    Seleccion s,
    Pais p
where
    s.idPais = p.idPais
        and not exists( select 
            i.idSeleccion
        from
            Jugador j, Integrante i
        where
            s.idSeleccion = i.idSeleccion
                and j.nroPasaporte = i.nroPasaporte
                and j.nroPasaporte not in (select distinct
                    p.nroPasaporte
                from
                    participacion p
                where
                    p.esTitular))
and exists (select * from Partido p  where p.idSeleccionLocal = s.idSeleccion or p.idSeleccionVisitante = s.idSeleccion);


END$$

DELIMITER ;

