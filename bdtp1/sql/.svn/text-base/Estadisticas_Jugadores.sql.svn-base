USE `tp1bd`;
DROP procedure IF EXISTS `Estadisticas_Jugadores`;

DELIMITER $$
USE `tp1bd`$$
CREATE PROCEDURE `tp1bd`.`Estadisticas_Jugadores` ()
BEGIN
select 
    Nombre,
    Partidos_Jugados,
    Minutos,
    Puntos,
    Rebotes,
    Asistencias,
    (Puntos / Partidos_Jugados) Ptos_por_partido,
    (Asistencias / Partidos_Jugados) Asistencias_por_partido,
    (Rebotes / Partidos_Jugados) Rebotes_por_partido
from
    (select 
        p.nroPasaporte,
            i.Nombre,
            sum(minutos) Minutos,
            sum(rebotes) Rebotes,
            sum(puntos) Puntos,
            sum(asistencias) Asistencias,
            count(1) Partidos_Jugados
    from
        integrante i, estadistica e, participacion p
    where
        p.idParticipacion = e.idParticipacion
            and i.nroPasaporte = p.nroPasaporte
    group by p.nroPasaporte , i.Nombre
    order by Partidos_Jugados) Total
order by partidos_jugados desc;
END$$

DELIMITER ;

