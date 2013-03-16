-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `arbitros_habilitados`(IN numeroPartido INT)
BEGIN
	DECLARE fechaPartido DATETIME;
	DECLARE idPaisLocal INT(11);
	DECLARE idPaisVisitante INT(11);

	select fecha, idSeleccionLocal, idSeleccionVisitante 
	into @fechaPartido, @idPaisLocal, @idPaisVisitante
	from Partido p
    where p.nroPartido = numeroPartido;

		-- Division de partidos con arbitro y quien gano	
	create temporary table tempGanadosPorArbitro (
					SELECT nropartido, idArbitro, 
					case when Resultado_local > Resultado_visitante then idSeleccionLocal else idSeleccionVisitante end as idGanador

					from(
					select 
						p.nroPartido,d.idArbitro, 
						(primerCuartoLocal + segundoCuartoLocal + tercerCuartoLocal + cuartoCuartoLocal) Resultado_Local,
						(primerCuartoVisitante + segundoCuartoVisitante + tercerCuartoVisitante + cuartoCuartoVisitante) Resultado_Visitante,
						idSeleccionLocal,
						idSeleccionVisitante
					from
						Partido p, DirigidoPor d where d.nroPartido = p.nroPartido) InfoPartido);

	create temporary table tempGanadosPorArbitro2 (
					SELECT nropartido, idArbitro, 
					case when Resultado_local > Resultado_visitante then idSeleccionLocal else idSeleccionVisitante end as idGanador

					from(
					select 
						p.nroPartido,d.idArbitro, 
						(primerCuartoLocal + segundoCuartoLocal + tercerCuartoLocal + cuartoCuartoLocal) Resultado_Local,
						(primerCuartoVisitante + segundoCuartoVisitante + tercerCuartoVisitante + cuartoCuartoVisitante) Resultado_Visitante,
						idSeleccionLocal,
						idSeleccionVisitante
					from
						Partido p, DirigidoPor d where d.nroPartido = p.nroPartido) InfoPartido);

create temporary table tempGanadosPorArbitro3 (
					SELECT nropartido, idArbitro, 
					case when Resultado_local > Resultado_visitante then idSeleccionLocal else idSeleccionVisitante end as idGanador

					from(
					select 
						p.nroPartido,d.idArbitro, 
						(primerCuartoLocal + segundoCuartoLocal + tercerCuartoLocal + cuartoCuartoLocal) Resultado_Local,
						(primerCuartoVisitante + segundoCuartoVisitante + tercerCuartoVisitante + cuartoCuartoVisitante) Resultado_Visitante,
						idSeleccionLocal,
						idSeleccionVisitante
					from
						Partido p, DirigidoPor d where d.nroPartido = p.nroPartido) InfoPartido);

create temporary table tempGanadosPorArbitro4 (
					SELECT nropartido, idArbitro, 
					case when Resultado_local > Resultado_visitante then idSeleccionLocal else idSeleccionVisitante end as idGanador

					from(
					select 
						p.nroPartido,d.idArbitro, 
						(primerCuartoLocal + segundoCuartoLocal + tercerCuartoLocal + cuartoCuartoLocal) Resultado_Local,
						(primerCuartoVisitante + segundoCuartoVisitante + tercerCuartoVisitante + cuartoCuartoVisitante) Resultado_Visitante,
						idSeleccionLocal,
						idSeleccionVisitante
					from
						Partido p, DirigidoPor d where d.nroPartido = p.nroPartido) InfoPartido);

	-- Division de partidos jugados por seleccion con que arbitro
	create temporary table tempArbitrosPorEquipo  (select Seleccion,idArbitro , count(1) Cantidad_dirigidos from (select idSeleccionLocal Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido
	union all
	select idSeleccionVisitante Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido)PEPE
	group by seleccion, idArbitro);

	create temporary table tempArbitrosPorEquipo2  (select Seleccion,idArbitro , count(1) Cantidad_dirigidos from (select idSeleccionLocal Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido
	union all
	select idSeleccionVisitante Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido)PEPE
	group by seleccion, idArbitro);

	create temporary table tempArbitrosPorEquipo3  (select Seleccion,idArbitro , count(1) Cantidad_dirigidos from (select idSeleccionLocal Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido
	union all
	select idSeleccionVisitante Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido)PEPE
	group by seleccion, idArbitro);

create temporary table tempArbitrosPorEquipo4  (select Seleccion,idArbitro , count(1) Cantidad_dirigidos from (select idSeleccionLocal Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido
	union all
	select idSeleccionVisitante Seleccion , idArbitro from DirigidoPor d , partido p where  p.nroPartido=d.nroPartido)PEPE
	group by seleccion, idArbitro);

	create temporary table GA1 (
		select idArbitro, idGanador Seleccion, count(*) Gano_con_Arbitro 
			from tempGanadosPorArbitro
			group by idArbitro, idGanador
	);
	create temporary table GA2 (
		select idArbitro, idGanador Seleccion, count(*) Gano_con_Arbitro 
			from tempGanadosPorArbitro2
			group by idArbitro, idGanador
	);

	create temporary table DSL (
	select  ga.seleccion, ga.idArbitro, cantidad_dirigidos, gano_con_arbitro 
		from GA1 ga, tempArbitrosPorEquipo3 ja 
		WHERE 
			ga.seleccion = ja.seleccion 
			AND ga.idArbitro = ja.idArbitro 
			and ga.seleccion = @idSeleccionVisitante
	);

	create temporary table DSV (
	select  ga.seleccion, ga.idArbitro, cantidad_dirigidos, gano_con_arbitro 
		from GA2 ga, tempArbitrosPorEquipo4 ja1 
		WHERE 
			ga.seleccion = ja1.seleccion 
			AND ga.idArbitro = ja1.idArbitro 
			and ga.seleccion = @idSeleccionLocal
	);

	select * from Arbitro A where A.idArbitro NOT IN ( 
		-- Todos los arbitros tales que dirigieron a la SeleccionLocal 2 o mas partidos y gano o perdio todos
		select  ga.idArbitro from
		(select idArbitro, idGanador Seleccion, count(1) Gano_con_Arbitro from tempGanadosPorArbitro3
			group by idArbitro, idGanador) ga ,   tempArbitrosPorEquipo ja 
		WHERE ga.seleccion = ja.seleccion AND ga.idArbitro = ja.idArbitro
		AND cantidad_dirigidos >=2 and (cantidad_dirigidos = gano_con_arbitro or gano_con_arbitro = 0) and ga.seleccion = @idSeleccionLocal

		UNION
		-- Todos los arbitros tales que dirigieron a la SeleccionVisitante 2 o mas partidos y gano o perdio todos
		select  ga.idArbitro from
		(select idArbitro, idGanador Seleccion, count(1) Gano_con_Arbitro from tempGanadosPorArbitro4
			group by idArbitro, idGanador) ga ,   tempArbitrosPorEquipo2 ja 
		WHERE ga.seleccion = ja.seleccion AND ga.idArbitro = ja.idArbitro
		AND cantidad_dirigidos >=2 and (cantidad_dirigidos = gano_con_arbitro or gano_con_arbitro = 0) and ga.seleccion = @idSeleccionVisitante

		UNION
		-- todos los dirididos una vez y resultado opuestos
		select DSL.idArbitro from DSL, DSV	
		WHERE DSL.idArbitro = DSV.idArbitro
		AND DSL.cantidad_dirigidos = 1 AND DSV.cantidad_dirigidos = 1
		AND (DSL.gano_con_arbitro  + DSV.gano_con_arbitro) = 1

		UNION
		select d.idArbitro from partido p, dirigidopor d where p.nroPartido = d.nroPartido and p.fecha = @fechaPartido
	);

END