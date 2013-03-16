CREATE DATABASE IF NOT EXISTS TP1BD;
USE TP1BD;

create table Estadio(
    idEstadio int,
    descripcion varchar(256),
    primary key (idEstadio)
);

create table Pais(
	idPais int,
	nombre varchar(50),
	primary key (idPais)
);

create table Fase(
    idFase int,
    descripcion varchar(256),
    primary key (idFase)
);

create table Seleccion(
	idSeleccion int,
	grupo varchar(1),
	hospedaje varchar(50),
	arribo datetime,
	idPais int,
	idEstadio int,
	primary key (idSeleccion),
	foreign key (idPais) references Pais(idPais),
	foreign key (idEstadio) references Estadio(idEstadio)
);


create table Posicion(
	idPosicion int,
	descripcion varchar(50),
	primary key (idPosicion)
);

create table Integrante(
	nroPasaporte int,
	nombre varchar(50),
	edad int,
	esUn varchar(1), #chequear esto!
	idSeleccion int,
	primary key (nroPasaporte),
	foreign key (idSeleccion) references Seleccion(idSeleccion)
);

create table CuerpoTecnico(
	nroPasaporte int,
	funcion varchar(50),
	primary key (nroPasaporte),
	foreign key (nroPasaporte) references Integrante(nroPasaporte)
);

create table Jugador(
	nroPasaporte int,
	equipo varchar(50),
	idPosicion int,
	primary key (nroPasaporte),
	foreign key (nroPasaporte) references Integrante(nroPasaporte),
	foreign key (idPosicion) references Posicion(idPosicion)
);

create table Arbitro(
	idArbitro int,
	nombre varchar(50),
	idPais int,
	primary key (idArbitro),
	foreign key (idPais) references Pais(idPais)
);

create table Partido(
    nroPartido int,
    primerCuartoLocal int,
    segundoCuartoLocal int,
    tercerCuartoLocal int,
    cuartoCuartoLocal int,
    primerCuartoVisitante int,
    segundoCuartoVisitante int,
    tercerCuartoVisitante int,
    cuartoCuartoVisitante int,
    fecha datetime,
    idFase int,
    idSeleccionLocal int,
    idSeleccionVisitante int,
    idEstadio int,
    primary key (nroPartido),
    foreign key (idFase) references Fase(idFase),
    foreign key (idSeleccionLocal) references Seleccion(idSeleccion),
    foreign key (idSeleccionVisitante) references Seleccion(idSeleccion),
    foreign key (idEstadio) references Estadio(idEstadio)
);

create table DirigidoPor(
	nroPartido int,
	idArbitro int,
	primary key (nroPartido, idArbitro),
	foreign key (nroPartido) references Partido(nroPartido),
	foreign key (idArbitro) references Arbitro(idArbitro)
);


create table Participacion(
	idParticipacion int, 
	esTitular bool,
	nroPasaporte int, 
	nroPartido int,
	primary key (idParticipacion),
	foreign key (nroPasaporte) references Jugador(nroPasaporte),
	foreign key (nroPartido) references Partido(nroPartido)
);

create table Sancion(
	idSancion int,
	Descripcion varchar(100),
	idParticipacion int,
	nroPartido int,
    primary key (idSancion),
	foreign key (idParticipacion) references Participacion(idParticipacion),
	foreign key (nroPartido) references Partido(nroPartido)
);

create table Estadistica(
    idEstadistica int,
    minutos int,
    asistencias int,
    rebotes int,
    puntos int,
    idParticipacion int,
    primary key (idEstadistica),
    foreign key (idParticipacion) references Participacion(idParticipacion)
);

commit;
