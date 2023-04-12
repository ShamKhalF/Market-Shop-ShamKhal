insert into products (name, barcode, description, price, cost, available_quantity, register_date) values 
('product1', 123465, 'product1 desc', 26.0, 20.5, 22, '2023-04-07 10:40:40'), 
('product2', 123456, 'product2 desc', 36.0, 30.5, 14, '2023-04-06 02:54:47'), 
('product3', 321456, 'product3 desc', 29.0, 24.5, 21, '2023-04-03 21:25:51'),
('product4', 148965, 'product4 desc', 56.0, 51, 12, '2023-04-05 17:36:10'), 
('product5', 356214, 'product5 desc', 95.0, 75.5, 15, '2023-04-08 23:29:58'), 
('product6', 478569, 'product6 desc', 47.0, 40.7, 17, '2023-04-10 11:11:11');


insert into product_cashier (name, barcode, description, price,  available_quantity) values 
('product1', 123465, 'product1 desc', 26.0, 22), 
('product2', 123456, 'product2 desc', 36.0, 14), 
('product3', 321456, 'product3 desc', 29.0, 21),
('product4', 148965, 'product4 desc', 56.0, 12), 
('product5', 356214, 'product5 desc', 95.0, 15), 
('product6', 478569, 'product6 desc', 47.0, 17);



insert into users (username, password, enabled) values 
('admin', '{noop}12', 1), 
('cashier', '{noop}12', 1);
insert into authorities(username, authority) values 
('admin', 'admin'), 
('cashier', 'cashier');

