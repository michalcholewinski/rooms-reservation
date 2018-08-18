INSERT INTO MYBOOKING_ROLE (ID, NAME) VALUES (1, 'OWNER');
INSERT INTO MYBOOKING_ROLE (ID, NAME) VALUES (2, 'CUSTOMER');

INSERT INTO MYBOOKING_USER (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (1, 'Michal', 'Cholewinski', 'bulko@onet.eu');
INSERT INTO MYBOOKING_USERS_ROLES (USER_ID, ROLE_ID) VALUES (1, 1);

INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price)
VALUES (nextval('MYBOOKING_ROOM_ID_SEQ'),33,'TV, 2 Windows, Towels available', 'Mariott', 'Gdynia', 300);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price)
VALUES (nextval('MYBOOKING_ROOM_ID_SEQ'),32,'Beautiful room with view to see', 'Mariott', 'Gdynia',2000);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price)
VALUES (nextval('MYBOOKING_ROOM_ID_SEQ'),41,'Room with view to Railway Station', 'Lysogory Hotel', 'Gdynia',500);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price)
VALUES (nextval('MYBOOKING_ROOM_ID_SEQ'),51,'Nothing special, just room with bed', 'Dal Hotel', 'Łodź',550);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price)
VALUES (nextval('MYBOOKING_ROOM_ID_SEQ'),62,'6th floor by stairs. Recommended for old people', 'Dal Hotel', 'Łódź',5500);
INSERT INTO MYBOOKING_ROOM(id, number, details, hotel_name, city, price)
VALUES (nextval('MYBOOKING_ROOM_ID_SEQ'),13,'One bad, 3 Bathrooms and welcome drink for guests', 'Matrix Hotel', 'Berlin',1500);
