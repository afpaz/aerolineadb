/*
Created: 04/05/2014
Modified: 17/05/2014
Model: Oracle 12c
Database: Oracle 12c
*/

-- Create tables section -------------------------------------------------

-- Table Pantallas

CREATE TABLE Pantallas(
  pantalla_id Varchar2(30 ) NOT NULL,
  descripcion Varchar2(500 )
)
;

-- Add keys for table Pantallas

ALTER TABLE Pantallas ADD CONSTRAINT Key_pantallas PRIMARY KEY (pantalla_id)
;

-- Table Roles

CREATE TABLE Roles(
  rol_id Varchar2(30 ) NOT NULL,
  descripcion Varchar2(500 )
)
;

-- Add keys for table Roles

ALTER TABLE Roles ADD CONSTRAINT Key_Roles PRIMARY KEY (rol_id)
;

-- Table Rol_Pantalla

CREATE TABLE Rol_Pantalla(
  rol_id Varchar2(30 ) NOT NULL,
  pantalla_id Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Rol_Pantalla

ALTER TABLE Rol_Pantalla ADD CONSTRAINT KeyRol_Pantalla PRIMARY KEY (rol_id,pantalla_id)
;

-- Table Usuarios

CREATE TABLE Usuarios(
  usuario Varchar2(30 ) NOT NULL,
  email Varchar2(30 ) NOT NULL,
  telefono Varchar2(30 ) NOT NULL,
  direccion Varchar2(30 ) NOT NULL,
  contrasena Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Usuarios

ALTER TABLE Usuarios ADD CONSTRAINT KeyUsuarios PRIMARY KEY (usuario)
;

-- Table Ventas

CREATE TABLE Ventas(
  venta_id Varchar2(30 ) NOT NULL,
  fecha Date NOT NULL,
  vendedor Varchar2(30 ) NOT NULL,
  comprador Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Ventas

ALTER TABLE Ventas ADD CONSTRAINT Key_Venta PRIMARY KEY (venta_id)
;

-- Table Tiquetes

CREATE TABLE Tiquetes(
  tiquete_id Varchar2(30 ) NOT NULL,
  valor Number(15,5) NOT NULL,
  venta_id Varchar2(30 ) NOT NULL,
  viaje_id Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Tiquetes

ALTER TABLE Tiquetes ADD CONSTRAINT Key_Tiquetes PRIMARY KEY (tiquete_id)
;

-- Table Rutas

CREATE TABLE Rutas(
  ruta_id Varchar2(30 ) NOT NULL,
  fecha Date NOT NULL,
  viaje_id Varchar2(30 ) NOT NULL,
  tarifa_id Varchar2(12 ) NOT NULL
)
;

-- Add keys for table Rutas

ALTER TABLE Rutas ADD CONSTRAINT KeyRutas PRIMARY KEY (ruta_id)
;

-- Table Viajes

CREATE TABLE Viajes(
  viaje_id Varchar2(30 ) NOT NULL,
  sentido Varchar2(30 ) NOT NULL,
  clase_id Varchar2(12 ) NOT NULL,
  descuento_id Varchar2(12 ),
  origen Varchar2(30 ) NOT NULL,
  destino Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Viajes

ALTER TABLE Viajes ADD CONSTRAINT KeyViajes PRIMARY KEY (viaje_id)
;

-- Table Vuelos

CREATE TABLE Vuelos(
  vuelo_id Varchar2(30 ) NOT NULL,
  fecha Date NOT NULL,
  cupoMax Number(5,0) NOT NULL,
  origen Varchar2(30 ) NOT NULL,
  destino Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Vuelos

ALTER TABLE Vuelos ADD CONSTRAINT KeyVuelo PRIMARY KEY (vuelo_id)
;

-- Table Lugares

CREATE TABLE Lugares(
  nombre_lugar Varchar2(30 ) NOT NULL,
  descripcion Varchar2(500 ) NOT NULL,
  latitud Number(10,5) NOT NULL,
  longitud Number(10,5) NOT NULL
)
;

-- Add keys for table Lugares

ALTER TABLE Lugares ADD CONSTRAINT KeyLugar PRIMARY KEY (nombre_lugar)
;

-- Table Descuentos

CREATE TABLE Descuentos(
  descuento_id Varchar2(12 ) NOT NULL,
  porcentaje_desc Number(2,0) NOT NULL,
  limInfDias Date NOT NULL,
  limSupDias Date NOT NULL,
  limInfCupos Number(3,0) NOT NULL,
  limSupCupos Number(3,0) NOT NULL
)
;

-- Add keys for table Descuentos

ALTER TABLE Descuentos ADD CONSTRAINT KeyDescuento PRIMARY KEY (descuento_id)
;

-- Table Clases

CREATE TABLE Clases(
  clase_id Varchar2(12 ) NOT NULL,
  descripcion Varchar2(500 ) NOT NULL,
  multiplicador Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Clases

ALTER TABLE Clases ADD CONSTRAINT KeyClase PRIMARY KEY (clase_id)
;

-- Table Ruta_Vuelo

CREATE TABLE Ruta_Vuelo(
  ruta_id Varchar2(30 ) NOT NULL,
  vuelo_id Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Ruta_Vuelo

ALTER TABLE Ruta_Vuelo ADD CONSTRAINT Key1 PRIMARY KEY (ruta_id,vuelo_id)
;

-- Table Tarifas

CREATE TABLE Tarifas(
  tarifa_id Varchar2(12 ) NOT NULL,
  vlr_km Number(10,0) NOT NULL,
  limInfKm Number(10,1) NOT NULL,
  limSupKm Number(10,1) NOT NULL
)
;

-- Add keys for table Tarifas

ALTER TABLE Tarifas ADD CONSTRAINT Key2 PRIMARY KEY (tarifa_id)
;

-- Table Usuario_Rol

CREATE TABLE Usuario_Rol(
  usuario Varchar2(30 ) NOT NULL,
  rol_id Varchar2(30 ) NOT NULL
)
;

-- Add keys for table Usuario_Rol

ALTER TABLE Usuario_Rol ADD CONSTRAINT Key3 PRIMARY KEY (usuario,rol_id)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE Tiquetes ADD CONSTRAINT Relationship47 FOREIGN KEY (venta_id) REFERENCES Ventas (venta_id)
;

ALTER TABLE Tiquetes ADD CONSTRAINT Relationship49 FOREIGN KEY (viaje_id) REFERENCES Viajes (viaje_id)
;

ALTER TABLE Viajes ADD CONSTRAINT Relationship51 FOREIGN KEY (clase_id) REFERENCES Clases (clase_id)
;

ALTER TABLE Rutas ADD CONSTRAINT Relationship52 FOREIGN KEY (viaje_id) REFERENCES Viajes (viaje_id)
;

ALTER TABLE Ruta_Vuelo ADD CONSTRAINT Relationship53 FOREIGN KEY (ruta_id) REFERENCES Rutas (ruta_id)
;

ALTER TABLE Ruta_Vuelo ADD CONSTRAINT Relationship54 FOREIGN KEY (vuelo_id) REFERENCES Vuelos (vuelo_id)
;

ALTER TABLE Rutas ADD CONSTRAINT Relationship55 FOREIGN KEY (tarifa_id) REFERENCES Tarifas (tarifa_id)
;

ALTER TABLE Viajes ADD CONSTRAINT Relationship58 FOREIGN KEY (descuento_id) REFERENCES Descuentos (descuento_id)
;

ALTER TABLE Viajes ADD CONSTRAINT origen2 FOREIGN KEY (origen) REFERENCES Lugares (nombre_lugar)
;

ALTER TABLE Viajes ADD CONSTRAINT destino2 FOREIGN KEY (destino) REFERENCES Lugares (nombre_lugar)
;

ALTER TABLE Vuelos ADD CONSTRAINT origen FOREIGN KEY (origen) REFERENCES Lugares (nombre_lugar)
;

ALTER TABLE Vuelos ADD CONSTRAINT destino FOREIGN KEY (destino) REFERENCES Lugares (nombre_lugar)
;

ALTER TABLE Usuario_Rol ADD CONSTRAINT Relationship65 FOREIGN KEY (usuario) REFERENCES Usuarios (usuario)
;

ALTER TABLE Usuario_Rol ADD CONSTRAINT Relationship66 FOREIGN KEY (rol_id) REFERENCES Roles (rol_id)
;

ALTER TABLE Ventas ADD CONSTRAINT vendedor FOREIGN KEY (vendedor) REFERENCES Usuarios (usuario)
;

ALTER TABLE Ventas ADD CONSTRAINT comprador FOREIGN KEY (comprador) REFERENCES Usuarios (usuario)
;

ALTER TABLE Rol_Pantalla ADD CONSTRAINT Relationship69 FOREIGN KEY (rol_id) REFERENCES Roles (rol_id)
;

ALTER TABLE Rol_Pantalla ADD CONSTRAINT Relationship70 FOREIGN KEY (pantalla_id) REFERENCES Pantallas (pantalla_id)
;

-- Tablas aux

CREATE TABLE ranking(	posicion NUMBER, nombre VARCHAR2(30), dinero_recaudado	NUMBER(10,2), PRIMARY KEY (posicion) )';

CREATE TABLE ranking2(	posicion NUMBER, nombre VARCHAR2(30), cantidad_Tiquetes	NUMBER(10,0), PRIMARY KEY (posicion) );


