INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 13.5, '8888', 0);

INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 15.5, '7777', 0);

INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 1.5, '6666', 0);

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 13.5, '8888', 0);
INSERT INTO buses(id, capacity) VALUES (4, 85);
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 15.5, '7777', 0);
INSERT INTO buses(id, capacity) VALUES (5, 20);
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 1.5, '6666', 0);
INSERT INTO buses(id, capacity) VALUES (6, 1);
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 13.5, '8888', 0);
INSERT INTO taxis(id, comfort) VALUES (7, 'BUSINESS');
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 15.5, '7777', 0);
INSERT INTO taxis(id, comfort) VALUES (8, 'ECONOMY');
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 1.5, '6666', 0);
INSERT INTO taxis(id, comfort) VALUES (9, 'UBER_BLACK');
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 13.5, '8888', 0);
INSERT INTO trucks(id, carrying) VALUES (10, 15);
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 15.5, '7777', 0);
INSERT INTO trucks(id, carrying) VALUES (11, 11);
COMMIT;

BEGIN;
INSERT INTO cars(brand, price, number, version)
VALUES ('Mercedes', 1.5, '6666', 0);
INSERT INTO trucks(id, carrying) VALUES (12, 89);
COMMIT;