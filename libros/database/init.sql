IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'LibrosDB')
BEGIN
    CREATE DATABASE LibrosDB;
END
GO

USE LibrosDB;
GO

IF OBJECT_ID('libro', 'U') IS NULL
BEGIN
    CREATE TABLE libro (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        nombre VARCHAR(150) NOT NULL UNIQUE,
        descripcion VARCHAR(300),
        autor VARCHAR(150) NOT NULL,
        fecha_publicacion DATE NOT NULL,
        numero_ejemplares INT NOT NULL,
        costo DECIMAL(10,4) NOT NULL
    );
END
GO
