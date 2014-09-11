--
-- Database: `registro`
--

-- Drop obsolete database
DROP DATABASE IF EXISTS `registro`;

-- Create new schema
CREATE DATABASE IF NOT EXISTS `registro` DEFAULT CHARACTER SET=utf8;

-- Load the database
use `registro`;

-- DDL
CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Apellido` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
