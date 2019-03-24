CREATE TABLE category(

id IDENTITY,
name VARCHAR(50),
description VARCHAR(255),
image_url VARCHAR(50),
is_active BOOLEAN,

CONSTRAINT pk_category_id PRIMARY KEY (id)
);

INSERT INTO category (name,description,image_url,is_active) VALUES ('Laptop','This is the description of laptop','CAT100_png',true);
INSERT INTO category (name,description,image_url,is_active) VALUES ('Mobile','This is the description of Mobile','CAT101_png',true);
INSERT INTO category (name,description,image_url,is_active) VALUES ('Television','This is the description of television','CAT102_png',false);

CREATE TABLE user_detail(
id IDENTITY,
first_name VARCHAR(50),
last_name VARCHAR(50),
role VARCHAR(50),
enabled BOOLEAN,
password VARCHAR(50),
email VARCHAR(100),
contact_number VARCHAR(15),
constraint pk_user_id primary key (id)
);

INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number)
VALUES
('Virat','Kohli','ADMIN',true,'admin','vk@gmail.com','888888888');

INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number)
VALUES
('Shikhar','Dhawan','SUPPLIER',true,'12345','sd@gmail.com','7777777777');

INSERT INTO user_detail
(first_name,last_name,role,enabled,password,email,contact_number)
VALUES
('Rohit','Sharma','SUPPLIER',true,'12345','rs@gmail.com','66666666');

CREATE TABLE product(
id IDENTITY,
code VARCHAR(20),
name VARCHAR(50),
brand VARCHAR(50),
description VARCHAR(255),
unit_price DECIMAL(10,2),
quantity INT,
is_active BOOLEAN,
category_id INT,
supplier_id INT,
purchases INT DEFAULT 0,
views INT DEFAULT 0,

constraint pk_product_id primary key (id),
constraint fk_product_category_id foreign key (category_id) references category (id),
constraint fk_product_supplier_id foreign key (supplier_id) references user_detail(id)

);

INSERT INTO product
(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id)
VALUES
('PRDABCDEF123','iphone 5s','apple','this is a phone',18000,10,true,3,2);

INSERT INTO product
(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id)
VALUES
('PRDABCDEF456','samsung a5','samsung','this is a phone',10000,10,true,3,2);

INSERT INTO product
(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id)
VALUES
('PRDABCDEF789','vivo v5','vivo','this is a phone',5000,10,true,3,3);

INSERT INTO product
(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id)
VALUES
('PRDABCDEF101','Macbook pro','apple','this is a laptop',100000,10,true,1,2);

INSERT INTO product
(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id)
VALUES
('PRDABCDEF456','DEll XPS','dell','this is a laptop',88000,10,true,1,1);


