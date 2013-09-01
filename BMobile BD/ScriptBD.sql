CREATE DATABASE BMobileBD;
USE BMobileBD;

CREATE TABLE Usuario(
CodUsuario varchar(4) NOT NULL,
nom varchar(25) NOT NULL,
apePat varchar(25) NOT NULL,
apeMat varchar(25) NOT NULL,
correo varchar(30) NOT NULL,
CONSTRAINT pk_Usuario PRIMARY KEY (CodUsuario)
);

CREATE TABLE Cuenta(
NumCel varchar(9) NOT NULL,
clave varchar(4) NOT NULL,
saldo float(6,2) NOT NULL,
CodUsuario varchar(4) NOT NULL,
CONSTRAINT pk_Cuenta PRIMARY KEY (NumCel),
CONSTRAINT fk_CuenxUsu FOREIGN KEY (CodUsuario) 
REFERENCES Usuario(CodUsuario)
);

CREATE TABLE TipoTransaccion(
CodTipTrans varchar(8) NOT NULL,
nomTrans varchar(15) NOT NULL,
CONSTRAINT pk_TipoTrans PRIMARY KEY (CodTipTrans) 
);

CREATE TABLE Transaccion(
CodTrans varchar(8) NOT NULL,
monTrans varchar(15) NOT NULL,
fecTrans Date NOT NULL,
CodTipTrans varchar(8) NOT NULL,
CONSTRAINT pk_Trans PRIMARY KEY (CodTrans),
CONSTRAINT fk_CodTipTransxTrans FOREIGN KEY (CodTipTrans) 
REFERENCES TipoTransaccion(CodTipTrans)
);

CREATE TABLE TipoMovimiento(
CodTipMov varchar(8) NOT NULL,
nomTip varchar(15) NOT NULL,
CONSTRAINT pk_TipoMov PRIMARY KEY (CodTipMov)
);

CREATE TABLE TransaccionXCuenta(
CodTrans varchar(8) NOT NULL,
NumCel varchar(9) NOT NULL,
CodTipMov varchar(8) NOT NULL,
des varchar(100) NOT NULL,
CONSTRAINT pk_TransXCuenta PRIMARY KEY (CodTrans,NumCel),
CONSTRAINT fk_TrasnXCuentaXTransaccion FOREIGN KEY (CodTrans) 
REFERENCES Transaccion(CodTrans),
CONSTRAINT fk_TrasnXCuentaXCuenta FOREIGN KEY (NumCel) 
REFERENCES Cuenta(NumCel),
CONSTRAINT fk_TrasnXCuentaXTipoMov FOREIGN KEY (CodTipMov) 
REFERENCES TipoMovimiento(CodTipMov)
);

--insert into Usuario values ('0000','Usuario01','ApePaterno01','ApeMaterno01','correo@example.pe');
--insert into Cuenta values('000000000','1234',0000.00,'0000');
--insert into TipoTransaccion values('00000000','Transaccion');
--insert into Transaccion values('00000000','500.00',curdate(),'00000000');
--insert into TipoMovimiento values ('00000000','Movimiento');
--insert into TransaccionXCuenta values ('00000000','000000000','00000000','Ninguna Descripcion');