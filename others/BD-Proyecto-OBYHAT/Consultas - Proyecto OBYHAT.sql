-- --------------------------------------------------------

--
-- Consultas para la tabla `usuario`
--
-- Mostrara el nombre del usuario y el nombre del tipo de usuario.
SELECT usuario.nombre, tipo_de_usuario.nombre
FROM usuario INNER JOIN tipo_de_usuario USING(idTipo)

SELECT nombre FROM obra
INNER JOIN asignar ON asignar.idObra = obra.idObra

SELECT obra.nombre, asignar.fechaAsignacion 
FROM asignar
INNER JOIN obra USING (idObra)

-- Insertando una nueva Asignacion.
INSERT INTO `asignar`(`fechaAsignacion`, `idObra`) VALUES ("2017-5-3",1);

-- Obteniendo esa ultima asignacion hecha.
SELECT `idAsignacion` FROM `asignar`
ORDER BY idAsignacion DESC LIMIT 1

-- Obtener informacion de las asignaciones.
SELECT detalleAsignar.idDetalle, asignar.idAsignacion, asignar.idObra, asignar.fechaAsignacion, detalleAsignar.idMaterial,detalleAsignar.cantidad 
FROM detalleAsignar
INNER JOIN asignar USING (idAsignacion)
ORDER BY `asignar`.`idObra` ASC

INSERT INTO `detalleAsignar` (`cantidad`, `idMaterial`, `idAsignacion`) VALUES 
('50', '00004', '00001');