/* Categorias Comida*/

insert into db_springboot_tapas.categoriacomida(tipo) values("Carnica");
insert into db_springboot_tapas.categoriacomida(tipo) values("Vegetariana");
insert into db_springboot_tapas.categoriacomida(tipo) values("Vegana");

/* Categoria Bebida */
insert into db_springboot_tapas.categoriabebida(tipo) values("Refresco");
insert into db_springboot_tapas.categoriabebida(tipo) values("Cerveza");
insert into db_springboot_tapas.categoriabebida(tipo) values("Vino");
insert into db_springboot_tapas.categoriabebida(tipo) values("AllCeroAlcohol");
insert into db_springboot_tapas.categoriabebida(tipo) values("All");


/* COMIDA */

insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("albondigas con patatas", 1);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("san jacobo con verduras", 1);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("huevos rellenos de pimientos", 2);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("huevos rotos con pimientos", 2);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("papas bravas", 3);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("berenjena en su punto", 3);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("macedonia", 3);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("albondigas con patatas y salsas", 1);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("san jacobo con verduras y setas", 1);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("huevos rellenos de pimientos y rellenos de verduras", 2);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("huevos rotos con pimientos y tomate", 2);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("papas bravas con salsas", 3);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("berenjenas fritas y al toque", 3);
insert into db_springboot_tapas.comida(nombre, categoriacomida_id) values("macedonia con aguacate", 3);


/* BEBIDA */

insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Coca-Cola", 1);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Todo", 5);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Todos refrescos", 1);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Todas Cervezas", 2);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Todos los vinos", 3);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Viña Palacio Del Conde", 3);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("Licor de Manzana", 4);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("6 Coca-Cola", 1);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("6 Cervezas", 2);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("4 refrescos", 1);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("12 Cervezas", 2);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("8 copas de vinos", 3);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("2 Viña Palacio Del Conde", 3);
insert into db_springboot_tapas.bebida(nombre, categoriabebida_id) values("4 chupitos de Licor de Manzana", 4);

/* USUARIOS */
INSERT INTO users (username, password, enabled) VALUES ('QWERTYUIOP', '$2a$10$ViuYaC0gdn0fjo39iitQrey241cIcbRS/A0cz0SaFpIUxk/dHAlla', 1);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$4Xi3Abu6z/gPsazOleua8e3tgAGWsYZ6/QRHpa7khw3AlsStFHY3m', 1);

INSERT INTO users (username, password, enabled) VALUES ('QWERTYUIOP2', '$2a$10$UnZBFCKE55VDnVWffM0EHe.tL8I8D7cxAn3/0cKLEtekVinJiCI/6', 1);

insert into authorities (user_id, authority) values(1, 'ROLE_BAR');
insert into authorities (user_id, authority) values(3, 'ROLE_BAR');

insert into authorities (user_id, authority) values(2, 'ROLE_ADMIN');
insert into authorities (user_id, authority) values(2, 'ROLE_BAR');


/*BARES*/

insert into db_springboot_tapas.bar(nombre, ubicacion, descripcion, nif, create_at, foto, fotonif , user_id) values("El gusanito", "Calle Virgen de la Fuencisla", "Bar creado exclusivamente por y para la gente, te queremos cerca", 'QWERTYUIOP',  NOW(), '', '', 1);
insert into db_springboot_tapas.bar(nombre, ubicacion, descripcion, nif, create_at, foto, fotonif, user_id) values("La Post-Vegana", "Calle de Albacete", "La comida vegana más rica y saludable de la ciudad. Que no se os olvide que somo los mas modernos", 'QWERTYUIOP2', NOW(), '', '', 3);


/* TAPAS */

insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la abuela", 2.5, 70, TRUE, NOW(), 1, 5, 1);
insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la explosion", 3.5, 80, TRUE, NOW(), 2, 6, 1);
insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la navidenia", 4, 40, TRUE, NOW(), 3, 7, 2);
insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la del pueblo", 2.5, 90, TRUE, NOW(), 4, 1, 2);
insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la del curro", 2.5, 30, TRUE, NOW(), 5, 4, 2);
insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la golosa", 3.5, 90, '', NOW(), 6, 2, 2);
insert into db_springboot_tapas.tapa(nombre, precio, valoracion, preciovariable, create_at, comida_id, bebida_id, bar_id) values("la huerta", 2.5, 100, '', NOW(), 7, 3, 2);

/* RACIONES */

insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("El ebuelote", 2.5, 70, NOW(), 8, 8, 1);
insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("La Explosividad", 3.5, 80, NOW(), 9, 9, 1);
insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("La Navidul", 4, 40, NOW(), 10, 10, 2);
insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("La De Albacete", 2.5, 90, NOW(), 11, 11, 2);
insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("El Currazo", 8, 30, NOW(), 12, 12, 2);
insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("La Golosou", 6, 90, NOW(), 13, 13, 2);
insert into db_springboot_tapas.raciones(nombre, precio, valoracion, create_at, comida_id, bebida_id, bar_id) values("La Huertiña", 9, 100, NOW(), 14, 14, 2);

/*VOTES*/
insert into db_springboot_tapas.vote(user_id, tapa_id) values(1, 2);
insert into db_springboot_tapas.vote(user_id, tapa_id) values(1, 6);
insert into db_springboot_tapas.vote(user_id, tapa_id) values(3, 6);
insert into db_springboot_tapas.vote(user_id, tapa_id) values(3, 2);
insert into db_springboot_tapas.vote(user_id, tapa_id) values(1, 7);
insert into db_springboot_tapas.vote(user_id, tapa_id) values(3, 7);


