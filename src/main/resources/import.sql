CREATE TABLE IF NOT EXISTS orderservice (
  id INT PRIMARY KEY  AUTO_INCREMENT,
  customername VARCHAR (250) NOT NULL  ,
  address VARCHAR (250) NOT NULL  ,
  orderdate date NOT NULL ,
  total double NOT NULL
);

CREATE TABLE IF NOT EXISTS orderitem (
  productid INT  PRIMARY KEY AUTO_INCREMENT,
  productcode VARCHAR  (100) NOT NULL  ,
  productname VARCHAR  (250) NOT NULL  ,
  quantity int not null
);