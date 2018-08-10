INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price) VALUES (111,1,'Bathrom, TV, 2 Windows, Towels available', 'Mariott', 'Warszawa', 500);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price) VALUES (112,2,'Bathrom, TV, 2 Windows, Towels available', 'Mariott', 'Warszawa',1000);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price) VALUES (113,1,'2 Bathrom, TV, 2 Windows, Towels available', 'Lysogory Hotel', 'Kielce',200);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price) VALUES (114,1,'4 Bathrom, TV, 2 Windows, Dirty Towels available', 'Dal Hotel', 'Kielce',250);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price) VALUES (115,2,'5 Bathrom, TV, 2 Windows, Towels available', 'Dal Hotel', 'Kielce',200);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price) VALUES (116,1,'Bathrom, 4 TV, 1 Windows, Many Towels available', 'Matrix Hotel', 'Kilonia',1500);

INSERT INTO MYBOOKING_ROLE (ID, NAME) VALUES (101, 'TEST_CUSTOMER');

INSERT INTO MYBOOKING_USER (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (100, 'Michal', 'Cholewinski', 'cholewinski@onet.eu');
INSERT INTO MYBOOKING_USERS_ROLES (USER_ID, ROLE_ID) VALUES (100, 101);

INSERT INTO MYBOOKING_USER (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (101, 'Kacper', 'Karpinski', 'karpinski@onet.eu');
INSERT INTO MYBOOKING_USERS_ROLES (USER_ID, ROLE_ID) VALUES (101, 101);

INSERT INTO MYBOOKING_USER (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (102, 'Tomasz', 'Andrzejczak', 'andrzejczak@onet.eu');
INSERT INTO MYBOOKING_USERS_ROLES (USER_ID, ROLE_ID) VALUES (102, 101);

INSERT INTO MYBOOKING_USER (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (103, 'Marcin', 'Kowalski', 'kowalski@onet.eu');
INSERT INTO MYBOOKING_USERS_ROLES (USER_ID, ROLE_ID) VALUES (103, 101);

INSERT INTO mybooking_reservation(id, from_time, to_time, room_id, customer_id) VALUES (101,'2018-09-16 00:00:00.000000','2018-09-18 00:00:00.000000', 111,100);
INSERT INTO mybooking_reservation(id, from_time, to_time, room_id, customer_id) VALUES (102,'2018-09-18 00:00:00.000000','2018-09-20 00:00:00.000000', 111,101);
INSERT INTO mybooking_reservation(id, from_time, to_time, room_id, customer_id) VALUES (103,'2018-09-19 00:00:00.000000','2018-09-21 00:00:00.000000', 112,102);
INSERT INTO mybooking_reservation(id, from_time, to_time, room_id, customer_id) VALUES (104,'2018-09-21 00:00:00.000000','2018-09-25 00:00:00.000000', 112,103);