;*
Created: 04;05;2014
Modified: 04;05;2014
Model: Oracle 12c
Database: Oracle 12c
*;


-- Create tables section -------------------------------------------------

-- Table Pantallas

CREATE TABLE "Pantallas"(
  "pantalla_id" Number(3,0) NOT NULL,
  "descripcion" Varchar2(500 )
);

-- Add keys for table Pantallas

ALTER TABLE "Pantallas" ADD CONSTRAINT "Key_pantallas" PRIMARY KEY ("pantalla_id");

-- Table Roles

CREATE TABLE "Roles"(
  "rol_id" Number(3,0) NOT NULL,
  "descripcion" Varchar2(500 )
);

-- Add keys for table Roles

ALTER TABLE "Roles" ADD CONSTRAINT "Key_Roles" PRIMARY KEY ("rol_id");

-- Table Rol_Pantalla

CREATE TABLE "Rol_Pantalla"(
  "rol_id" Number(3,0) NOT NULL,
  "pantalla_id" Number(3,0) NOT NULL
);

-- Add keys for table Rol_Pantalla

ALTER TABLE "Rol_Pantalla" ADD CONSTRAINT "KeyRol_Pantalla" PRIMARY KEY ("rol_id","pantalla_id");

-- Table Usuarios

CREATE TABLE "Usuarios"(
  "usuario_id" Number(3,0) NOT NULL,
  "nombre" Varchar2(30 ) NOT NULL,
  "email" Varchar2(30 ),
  "telefono" Varchar2(30 ) NOT NULL,
  "direccion" Varchar2(30 ) NOT NULL,
  "usuario" Varchar2(30 ) NOT NULL,
  "contrasena" Varchar2(30 ) NOT NULL
);

-- Add keys for table Usuarios

ALTER TABLE "Usuarios" ADD CONSTRAINT "KeyUsuarios" PRIMARY KEY ("usuario_id");

-- Table Ventas

CREATE TABLE "Ventas"(
  "venta_id" Number(3,0) NOT NULL,
  "fecha" Date NOT NULL,
  "usuario_id" Number(3,0) NOT NULL
);

-- Add keys for table Ventas

ALTER TABLE "Ventas" ADD CONSTRAINT "Key_Venta" PRIMARY KEY ("venta_id","usuario_id");

-- Table Tiquetes

CREATE TABLE "Tiquetes"(
  "tiquete_id" Number(3,0) NOT NULL,
  "valor" Number(10,10) NOT NULL,
  "usuario_id" Number(3,0) NOT NULL,
  "viaje_id" Number(3,0) NOT NULL
);

-- Add keys for table Tiquetes

ALTER TABLE "Tiquetes" ADD CONSTRAINT "Key_Tiquetes" PRIMARY KEY ("tiquete_id","usuario_id","viaje_id");

-- Table Pasabordos

CREATE TABLE "Pasabordos"(
  "pasabordo_id" Number(3,0) NOT NULL,
  "usuario_id" Number(3,0) NOT NULL
);

-- Add keys for table Pasabordos

ALTER TABLE "Pasabordos" ADD CONSTRAINT "Key_Pasabordos" PRIMARY KEY ("pasabordo_id","usuario_id");

-- Table Rutas

CREATE TABLE "Rutas"(
  "ruta_id" Number(3,0) NOT NULL,
  "fecha" Date NOT NULL
);

-- Add keys for table Rutas

ALTER TABLE "Rutas" ADD CONSTRAINT "KeyRutas" PRIMARY KEY ("ruta_id");

-- Table Viajes

CREATE TABLE "Viajes"(
  "viaje_id" Number(3,0) NOT NULL,
  "fecha" Date NOT NULL,
  "sentido" Varchar2(30 ) NOT NULL,
  "origen" Number(3,0) NOT NULL,
  "destino" Number(3,0) NOT NULL
);

-- Add keys for table Viajes

ALTER TABLE "Viajes" ADD CONSTRAINT "KeyViajes" PRIMARY KEY ("viaje_id");

-- Table Vuelo

CREATE TABLE "Vuelo"(
  "vuelo_id" Number(3,0) NOT NULL,
  "fecha" Date NOT NULL,
  "cupoMax" Number(5,0) NOT NULL,
  "viaje_id" Number(3,0) NOT NULL
);

-- Add keys for table Vuelo

ALTER TABLE "Vuelo" ADD CONSTRAINT "KeyVuelo" PRIMARY KEY ("vuelo_id");

-- Table Lugar

CREATE TABLE "Lugar"(
  "lugar_id" Number(3,0) NOT NULL,
  "descripcion" Varchar2(500 ) NOT NULL
);

-- Add keys for table Lugar

ALTER TABLE "Lugar" ADD CONSTRAINT "KeyLugar" PRIMARY KEY ("lugar_id");

-- Table Tarifas

CREATE TABLE "Tarifas"(
  "tarifa_id" Number(3,0) NOT NULL,
  "rangoKM" Number(10,10) NOT NULL,
  "valorKM" Number(10,10) NOT NULL
);

-- Add keys for table Tarifas

ALTER TABLE "Tarifas" ADD CONSTRAINT "KeyTarifas" PRIMARY KEY ("tarifa_id");

-- Table Descuentos

CREATE TABLE "Descuentos"(
  "descuento_id" Number(3,0) NOT NULL,
  "rango_dias" Number(10,0),
  "rango_ocupacion" Varchar2(30 ),
  "porcentaje_desc" Number(2,6),
  "viaje_id" Number(3,0) NOT NULL
);

-- Add keys for table Descuentos

ALTER TABLE "Descuentos" ADD CONSTRAINT "KeyDescuento" PRIMARY KEY ("descuento_id");

-- Table Clase

CREATE TABLE "Clase"(
  "clase_id" Number(3,0) NOT NULL,
  "descripcion" Varchar2(500 ) NOT NULL,
  "multiplicador" Varchar2(30 )
);

-- Add keys for table Clase

ALTER TABLE "Clase" ADD CONSTRAINT "KeyClase" PRIMARY KEY ("clase_id");

-- Table Usuario_Rol

CREATE TABLE "Usuario_Rol"(
  "usuario_id" Number(3,0) NOT NULL,
  "rol_id" Number(3,0) NOT NULL
);

-- Add keys for table Usuario_Rol

ALTER TABLE "Usuario_Rol" ADD CONSTRAINT "KeyUsuario_Rol" PRIMARY KEY ("usuario_id","rol_id");

-- Table Pasabordo_Viaje

CREATE TABLE "Pasabordo_Viaje"(
  "pasabordo_id" Number(3,0) NOT NULL,
  "usuario_id" Number(3,0) NOT NULL,
  "viaje_id" Number(3,0) NOT NULL
);

-- Add keys for table Pasabordo_Viaje

ALTER TABLE "Pasabordo_Viaje" ADD CONSTRAINT "KeyPasabordo_Viaje" PRIMARY KEY ("pasabordo_id","usuario_id","viaje_id");

-- Table Viaje_Tarifa

CREATE TABLE "Viaje_Tarifa"(
  "tarifa_id" Number(3,0) NOT NULL,
  "viaje_id" Number(3,0) NOT NULL
);

-- Add keys for table Viaje_Tarifa

ALTER TABLE "Viaje_Tarifa" ADD CONSTRAINT "KeyViaje_Tarifa" PRIMARY KEY ("tarifa_id","viaje_id");

-- Table Vuelo_Clase

CREATE TABLE "Vuelo_Clase"(
  "vuelo_id" Number(3,0) NOT NULL,
  "clase_id" Number(3,0) NOT NULL
);

-- Add keys for table Vuelo_Clase

ALTER TABLE "Vuelo_Clase" ADD CONSTRAINT "KeyVuelo_Clase" PRIMARY KEY ("vuelo_id","clase_id");

-- Table Ruta_Vuelo

CREATE TABLE "Ruta_Vuelo"(
  "ruta_id" Number(3,0) NOT NULL,
  "vuelo_id" Number(3,0) NOT NULL
);

-- Add keys for table Ruta_Vuelo

ALTER TABLE "Ruta_Vuelo" ADD CONSTRAINT "KeyRuta_Vuelo" PRIMARY KEY ("ruta_id","vuelo_id");

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "Rol_Pantalla" ADD CONSTRAINT "Relationship3" FOREIGN KEY ("rol_id") REFERENCES "Roles" ("rol_id");

ALTER TABLE "Rol_Pantalla" ADD CONSTRAINT "Relationship4" FOREIGN KEY ("pantalla_id") REFERENCES "Pantallas" ("pantalla_id");

ALTER TABLE "Usuario_Rol" ADD CONSTRAINT "Relationship7" FOREIGN KEY ("usuario_id") REFERENCES "Usuarios" ("usuario_id");

ALTER TABLE "Usuario_Rol" ADD CONSTRAINT "Relationship8" FOREIGN KEY ("rol_id") REFERENCES "Roles" ("rol_id");

ALTER TABLE "Ventas" ADD CONSTRAINT "Relationship9" FOREIGN KEY ("usuario_id") REFERENCES "Usuarios" ("usuario_id");

ALTER TABLE "Tiquetes" ADD CONSTRAINT "Relationship10" FOREIGN KEY ("usuario_id") REFERENCES "Usuarios" ("usuario_id");

ALTER TABLE "Pasabordos" ADD CONSTRAINT "Relationship11" FOREIGN KEY ("usuario_id") REFERENCES "Usuarios" ("usuario_id");

ALTER TABLE "Pasabordo_Viaje" ADD CONSTRAINT "Relationship12" FOREIGN KEY ("pasabordo_id", "usuario_id") REFERENCES "Pasabordos" ("pasabordo_id", "usuario_id")
;

ALTER TABLE "Pasabordo_Viaje" ADD CONSTRAINT "Relationship13" FOREIGN KEY ("viaje_id") REFERENCES "Viajes" ("viaje_id");

ALTER TABLE "Viaje_Tarifa" ADD CONSTRAINT "Relationship14" FOREIGN KEY ("tarifa_id") REFERENCES "Tarifas" ("tarifa_id");

ALTER TABLE "Viaje_Tarifa" ADD CONSTRAINT "Relationship15" FOREIGN KEY ("viaje_id") REFERENCES "Viajes" ("viaje_id");

ALTER TABLE "Descuentos" ADD CONSTRAINT "Relationship16" FOREIGN KEY ("viaje_id") REFERENCES "Viajes" ("viaje_id");

ALTER TABLE "Tiquetes" ADD CONSTRAINT "Relationship17" FOREIGN KEY ("viaje_id") REFERENCES "Viajes" ("viaje_id");

ALTER TABLE "Vuelo" ADD CONSTRAINT "Relationship18" FOREIGN KEY ("viaje_id") REFERENCES "Viajes" ("viaje_id");

ALTER TABLE "Vuelo_Clase" ADD CONSTRAINT "Relationship19" FOREIGN KEY ("vuelo_id") REFERENCES "Vuelo" ("vuelo_id");

ALTER TABLE "Vuelo_Clase" ADD CONSTRAINT "Relationship20" FOREIGN KEY ("clase_id") REFERENCES "Clase" ("clase_id");

ALTER TABLE "Ruta_Vuelo" ADD CONSTRAINT "Relationship21" FOREIGN KEY ("ruta_id") REFERENCES "Rutas" ("ruta_id");

ALTER TABLE "Ruta_Vuelo" ADD CONSTRAINT "Relationship22" FOREIGN KEY ("vuelo_id") REFERENCES "Vuelo" ("vuelo_id");

ALTER TABLE "Viajes" ADD CONSTRAINT "destino" FOREIGN KEY ("destino") REFERENCES "Lugar" ("lugar_id");

ALTER TABLE "Viajes" ADD CONSTRAINT "origen" FOREIGN KEY ("origen") REFERENCES "Lugar" ("lugar_id");



