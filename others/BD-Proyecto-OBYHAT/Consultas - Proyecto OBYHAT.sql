-- --------------------------------------------------------

--
-- Consultas para la tabla `usuario`
--
-- Mostrara el nombre del usuario y el nombre del tipo de usuario.
SELECT usuario.nombre, tipo_de_usuario.nombre
FROM usuario INNER JOIN tipo_de_usuario USING(idTipo)