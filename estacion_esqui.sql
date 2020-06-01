-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-06-2020 a las 04:05:47
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `estacion_esqui`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellidos` varchar(40) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `forfait_cliente`
--

CREATE TABLE `forfait_cliente` (
  `id_forfait` char(3) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `fecha_hora` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material`
--

CREATE TABLE `material` (
  `id` varchar(5) NOT NULL,
  `talla` smallint(6) NOT NULL,
  `tipo_material` varchar(11) NOT NULL,
  `precio` double NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `material`
--

INSERT INTO `material` (`id`, `talla`, `tipo_material`, `precio`, `disponibilidad`) VALUES
('be1', 36, 'botas_esqui', 15, 1),
('be10', 43, 'botas_esqui', 15, 1),
('be11', 44, 'botas_esqui', 15, 0),
('be2', 37, 'botas_esqui', 15, 0),
('be3', 38, 'botas_esqui', 15, 0),
('be4', 39, 'botas_esqui', 15, 1),
('be5', 40, 'botas_esqui', 15, 1),
('be6', 41, 'botas_esqui', 15, 1),
('be7', 41, 'botas_esqui', 15, 0),
('be8', 42, 'botas_esqui', 15, 1),
('be9', 42, 'botas_esqui', 15, 1),
('bs1', 36, 'botas_snow', 20, 1),
('bs10', 43, 'botas_snow', 20, 1),
('bs11', 44, 'botas_snow', 20, 0),
('bs2', 37, 'botas_snow', 20, 0),
('bs3', 38, 'botas_snow', 20, 0),
('bs4', 39, 'botas_snow', 20, 1),
('bs5', 40, 'botas_snow', 20, 1),
('bs6', 41, 'botas_snow', 20, 1),
('bs7', 41, 'botas_snow', 20, 0),
('bs8', 42, 'botas_snow', 20, 1),
('bs9', 42, 'botas_snow', 20, 1),
('eq1', 148, 'esquis', 10, 1),
('eq10', 153, 'esquis', 10, 1),
('eq11', 154, 'esquis', 10, 0),
('eq12', 155, 'esquis', 10, 0),
('eq13', 156, 'esquis', 10, 0),
('eq14', 157, 'esquis', 10, 0),
('eq15', 150, 'esquis', 10, 1),
('eq16', 151, 'esquis', 10, 0),
('eq2', 149, 'esquis', 10, 1),
('eq3', 150, 'esquis', 10, 0),
('eq4', 150, 'esquis', 10, 0),
('eq5', 150, 'esquis', 10, 1),
('eq6', 151, 'esquis', 10, 1),
('eq7', 151, 'esquis', 10, 0),
('eq8', 152, 'esquis', 10, 0),
('eq9', 152, 'esquis', 10, 0),
('tb1', 146, 'tabla', 15, 0),
('tb10', 154, 'tabla', 15, 1),
('tb11', 150, 'tabla', 15, 1),
('tb2', 147, 'tabla', 15, 0),
('tb3', 148, 'tabla', 15, 0),
('tb4', 148, 'tabla', 15, 0),
('tb5', 149, 'tabla', 15, 0),
('tb6', 149, 'tabla', 15, 0),
('tb7', 150, 'tabla', 15, 0),
('tb8', 152, 'tabla', 15, 0),
('tb9', 153, 'tabla', 15, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `material_cliente`
--

CREATE TABLE `material_cliente` (
  `id_material` varchar(5) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `fecha_hora_inicio` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_hora_fin` datetime NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE `pistas` (
  `id` tinyint(4) NOT NULL,
  `nombre` char(25) NOT NULL,
  `altura_inicio` smallint(6) NOT NULL,
  `altura_fin` smallint(6) NOT NULL,
  `pista_abierta` tinyint(1) NOT NULL,
  `temp` double NOT NULL,
  `nivel` varchar(5) DEFAULT NULL CHECK (`nivel` in ('AZUL','VERDE','ROJA','NEGRA'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pistas`
--

INSERT INTO `pistas` (`id`, `nombre`, `altura_inicio`, `altura_fin`, `pista_abierta`, `temp`, `nivel`) VALUES
(1, 'La Font', 2000, 1000, 0, 6, 'AZUL'),
(2, 'La Rasa', 2000, 1000, 0, 5, 'ROJA'),
(3, 'L\'Esquiro', 2000, 1500, 0, 7, 'NEGRA'),
(4, 'L\'Estadi', 1500, 1000, 1, 5.5, 'NEGRA'),
(5, 'La Rasa Pauet', 1500, 1000, 1, 5.5, 'ROJA'),
(6, 'Solei', 1000, 0, 1, 5.5, 'VERDE'),
(7, 'El Forat', 1000, 0, 1, 5.5, 'ROJA'),
(8, 'L\'Orri', 1000, 0, 1, 5.7, 'AZUL'),
(9, 'L\'Obaga', 1000, 500, 1, 5.7, 'VERDE'),
(10, 'La Drecera', 500, 0, 1, 5.7, 'AZUL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_forfait`
--

CREATE TABLE `tipo_forfait` (
  `id` char(3) NOT NULL,
  `nombre` varchar(22) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_forfait`
--

INSERT INTO `tipo_forfait` (`id`, `nombre`, `precio`) VALUES
('f1d', 'Forfait de un dia', 20),
('f1s', 'Forfait de una semana', 100),
('f2d', 'Forfait de dos dias', 37),
('ftm', 'Forfait de temporada', 350);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `forfait_cliente`
--
ALTER TABLE `forfait_cliente`
  ADD PRIMARY KEY (`id_forfait`,`id_cliente`,`fecha_hora`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `material_cliente`
--
ALTER TABLE `material_cliente`
  ADD PRIMARY KEY (`id_material`,`id_cliente`,`fecha_hora_inicio`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_forfait`
--
ALTER TABLE `tipo_forfait`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `forfait_cliente`
--
ALTER TABLE `forfait_cliente`
  ADD CONSTRAINT `forfait_cliente_ibfk_1` FOREIGN KEY (`id_forfait`) REFERENCES `tipo_forfait` (`id`),
  ADD CONSTRAINT `forfait_cliente_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`);

--
-- Filtros para la tabla `material_cliente`
--
ALTER TABLE `material_cliente`
  ADD CONSTRAINT `material_cliente_ibfk_1` FOREIGN KEY (`id_material`) REFERENCES `material` (`id`),
  ADD CONSTRAINT `material_cliente_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
