CREATE DATABASE ProyectoOBYHAT;
  USE ProyectoOBYHAT;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `rif` 		VARCHAR(20) NOT NULL,
  `razonSocial` VARCHAR(100) NOT NULL,
  `telefono` 	VARCHAR(25) NOT NULL,
  `direccion` 	VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY(rif),
  UNIQUE (razonSocial)) 
ENGINE INNODB;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `categoriaMaterial`
--

CREATE TABLE `categoriaMaterial` (
  `idCategoria` INTEGER(5)   UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombre`  	VARCHAR(80)  NOT NULL,
  `descripcion` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (idCategoria),
  UNIQUE (nombre)) 
ENGINE INNODB;

--
-- Volcado de datos para la tabla `categoriaMaterial`
--

INSERT INTO `categoriaMaterial` (`nombre`, `descripcion`) VALUES
('Seleccionar categoria...','-OPCIONAL-'),
('Piedra',    '-OPCIONAL-'),
('Arena',     '-OPCIONAL-'),
('Arcilla',   '-OPCIONAL-'),
('Metálicos', '-OPCIONAL-'),
('Orgánico',  '-OPCIONAL-'),
('Sintéticos','-OPCIONAL-'),
('Vidrios',	  '-OPCIONAL-');

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `material`
--
/*
*CREATE TABLE `material` (
 * `idMaterial` 	   INTEGER(5)   UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  *`nombreMaterial` VARCHAR(100) NOT NULL,
  *`existencia`	   INTEGER(5)   DEFAULT NULL,
  *`existenciaMIN`  INTEGER(5)   DEFAULT NULL,
  *`existenciaMAX`  INTEGER(5)   DEFAULT NULL,
  *`fechaRegistro`  DATE 	    DEFAULT NULL,
  *`idCategoria`    INTEGER(5)   UNSIGNED ZEROFILL NOT NULL,
  *UNIQUE (nombreMaterial),
  *PRIMARY KEY (`idMaterial`),
  *FOREIGN KEY(idCategoria) REFERENCES `categoriaMaterial`(idCategoria)) 
*ENGINE INNODB;*/

CREATE TABLE `material` (
  `idMaterial`      INTEGER(5)   UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombreMaterial`  VARCHAR(100) DEFAULT NULL,
  `cantidadMaterial`INTEGER(5)   DEFAULT NULL,
  `existenciaMIN`   INTEGER(5)   DEFAULT NULL,
  `existenciaMAX`   INTEGER(5)   DEFAULT NULL,
  `fechaRegistro`   DATE         DEFAULT NULL,
  UNIQUE (nombreMaterial),
  PRIMARY KEY (`idMaterial`)) 
ENGINE INNODB;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idOrdenLlegada` INTEGER(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fechaLlegada`   DATE	      NOT NULL,
  `cantidad` 	   INTEGER(4) NOT NULL,
  `rif` 		   VARCHAR(20)NOT NULL,
  `idMaterial` 	   INTEGER(5) UNSIGNED ZEROFILL NOT NULL,
   PRIMARY KEY(idOrdenLlegada,rif,idMaterial),
   FOREIGN KEY(rif) 	   REFERENCES `proveedor`(rif),
   FOREIGN KEY(idMaterial) REFERENCES `material`(idMaterial)) 
ENGINE INNODB;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_de_usuario`
--

CREATE TABLE `tipo_de_usuario` (
  `idTipo` INTEGER(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(40) NOT NULL,
  PRIMARY KEY (idTipo),
  UNIQUE (nombre)) 
ENGINE INNODB;

--
-- Volcado de datos para la tabla `tipo_de_usuario`
--

INSERT INTO `tipo_de_usuario` (`nombre`) VALUES
('Administrador'),
('Empleado'),
('Desarrollador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` INTEGER(5)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombre` 	  VARCHAR(40) NOT NULL,
  `password`  VARCHAR(20) NOT NULL,
  `idTipo` 	  INTEGER(5)  UNSIGNED ZEROFILL NOT NULL,
  UNIQUE (nombre),
  PRIMARY KEY (idUsuario,idTipo),
  FOREIGN KEY(idTipo) REFERENCES `tipo_de_usuario`(idTipo)) 
ENGINE INNODB;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre`, `password`,idTipo) VALUES
('Admin', 'Admin',  1),
('User',  'User',   2),
('Zophia', 'zophia',2),
('Pepe',  'pepe',   2),
('Desarrolladores', '123', 2);

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `obra`
--

CREATE TABLE `obra` (
  `idObra` 		INTEGER(5)   UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombre`  	VARCHAR(100) NOT NULL,
  `encargado` 	VARCHAR(80)  NOT NULL,
  `telefono` 	VARCHAR(20)  NOT NULL,
  `direccion` 	VARCHAR(100) NOT NULL,
  PRIMARY KEY (idObra),
  UNIQUE (nombre)) 
ENGINE INNODB;

--
-- Volcado de datos para la tabla `obra`
--

INSERT INTO `obra` (`nombre`, `encargado`, `telefono`, `direccion`) VALUES
('La hojarasca', 						 'G. G. Marquez', '1955', 'America Latina'),
('El coronel no tiene quien le escriba', 'G. G. Marquez', '1961', 'America Latina'),
('La mala hora', 						 'G. G. Marquez', '1962', 'America Latina'),
('Cien años de soledad', 				 'G. G. Marquez', '1967', 'America Latina'),
('El otoño del patriarca',  			 'G. G. Marquez', '1975', 'America Latina'),
('Crónica de una muerte anunciada',  	 'G. G. Marquez', '1981', 'America Latina'),
('El amor en los tiempos del cólera',  	 'G. G. Marquez', '1985', 'America Latina'),
('El general en su laberinto',  		 'G. G. Marquez', '1989', 'America Latina'),
('Del amor y otros demonios', 			 'G. G. Marquez', '1994', 'America Latina'),
('Ojos de perro azul', 					 'G. G. Marquez', '1972', 'America Latina'),
('Memoria de mis putas tristes', 		 'G. G. Marquez', '2004', 'America Latina'),
('Los cuentos de mi abuelo el coronel',  'G. G. Marquez', '1988', 'America Latina'),
('Doce cuentos peregrinos', 			 'G. G. Marquez', '1992', 'America Latina'),
('Relato de un náufrago', 				 'G. G. Marquez', '1970', 'America Latina');

--
-- Estructura de tabla para la tabla `asignar`
--

/*CREATE TABLE `asignar` (
  *`idOrdenSalida` 	INTEGER(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  *`fechaAsignacion` DATE 	   NOT NULL,
  *`cantidad`        INTEGER(4) NOT NULL,
  *`idMaterial` 	    INTEGER(5) UNSIGNED ZEROFILL NOT NULL,
  *`idUsuario`       INTEGER(5) UNSIGNED ZEROFILL NOT NULL,
  *`idObra` 		    INTEGER(5) UNSIGNED ZEROFILL NOT NULL,
  *PRIMARY KEY (idOrdenSalida,idMaterial,idUsuario),
  *FOREIGN KEY (idMaterial) REFERENCES `material`(idMaterial),
  *FOREIGN KEY (idUsuario)  REFERENCES `usuario`(idUsuario),
  *FOREIGN KEY (idObra)     REFERENCES `obra`(idObra)) 
*ENGINE INNODB;*/

--
-- Volcado de datos para la tabla `asignar`
--

CREATE TABLE `asignar` (
  `idOrdenSalida`   INTEGER(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fechaAsignacion` DATE     NOT NULL,
  `cantidad`        INTEGER(4) NOT NULL,
  PRIMARY KEY (idOrdenSalida)) 
ENGINE INNODB;

-- --------------------------------------------------------

