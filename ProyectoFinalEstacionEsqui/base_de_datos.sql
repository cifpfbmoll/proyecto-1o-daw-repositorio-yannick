CREATE TABLE clientes(
   id INT NOT NULL AUTO_INCREMENT,
   dni VARCHAR(9) NOT NULL,
   nombre VARCHAR(15) NOT NULL,
   apellidos VARCHAR(40) NOT NULL,
   fecha_nacimiento DATE,
   PRIMARY KEY(id)
)ENGINE = InnoDB;

CREATE TABLE tipo_forfait(
   id CHAR(3) NOT NULL,
   nombre VARCHAR(22) NOT NULL,
   precio DOUBLE NOT NULL,
   PRIMARY KEY(id)
)ENGINE = InnoDB;

CREATE TABLE forfait_cliente (
   id_forfait CHAR(3) NOT NULL,
   id_cliente INT NOT NULL,
   fecha_hora TIMESTAMP NOT NULL,
   precio DOUBLE NOT NULL,
   PRIMARY KEY (id_forfait, id_cliente, fecha_hora),
   FOREIGN KEY (id_forfait) REFERENCES tipo_forfait (id),
   FOREIGN KEY (id_cliente) REFERENCES clientes (id)
)ENGINE = InnoDB;

CREATE TABLE material(
   id VARCHAR(5) PRIMARY KEY NOT NULL,
   talla SMALLINT NOT NULL,
   tipo_material VARCHAR(11) NOT NULL,
   precio DOUBLE NOT NULL,
   disponibilidad BOOLEAN NOT NULL
)ENGINE = InnoDB;

CREATE TABLE material_cliente(
   id_material VARCHAR (5) NOT NULL,
   id_cliente INT NOT NULL,
   fecha_hora_inicio TIMESTAMP NOT NULL,
   fecha_hora_fin DATETIME NOT NULL,
   precio DOUBLE NOT NULL,
   PRIMARY KEY (id_material, id_cliente, fecha_hora_inicio),
   FOREIGN KEY (id_material) REFERENCES material (id),
   FOREIGN KEY (id_cliente) REFERENCES clientes (id)
)ENGINE = InnoDB;

CREATE TABLE pistas(
   id TINYINT PRIMARY KEY NOT NULL,
   nombre CHAR(25) NOT NULL,
   altura_inicio SMALLINT NOT NULL,
   altura_fin SMALLINT NOT NULL,
   pista_abierta BOOLEAN NOT NULL,
   temp DOUBLE NOT NULL,
   nivel VARCHAR(5) CHECK (nivel IN ('AZUL', 'VERDE', 'ROJA', 'NEGRA'))
)ENGINE = InnoDB;
