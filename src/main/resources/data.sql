--  password testPass
INSERT INTO accounts (id, first_name, last_name, username, password, role, phone, created_date, updated_date)
VALUES (1, 'Lionell', 'Messi', 'messi@gmail.com', '$2a$10$lq2hLvcdly/qnaIjOeTNCev7igulM0Mp1DQdqTZnZkdJCutCSMkmi', 'USER', '+79112134567', now(), now()),
       (2, 'Sergio', 'Aguero', 'aguero@gmail.com', '$2a$10$lq2hLvcdly/qnaIjOeTNCev7igulM0Mp1DQdqTZnZkdJCutCSMkmi', 'USER', '+79452344567', now(), now()),
       (3, 'Thomas', 'Shelby', 'shelby@gmail.com', '$2a$10$lq2hLvcdly/qnaIjOeTNCev7igulM0Mp1DQdqTZnZkdJCutCSMkmi', 'USER', '+71452134567', now(), now());

INSERT INTO orders(id, start_location, end_location, created_date, updated_date)
VALUES (1, 'Moscow, Tverskaya Street, 5', 'Moscow, Profsouznaya Street, 7', now(), now()),
       (2, 'Moscow, Khimki, 7', 'Zelenograd, 12', now(), now()),
       (3, 'Moscow, Vosstaniya Square', 'rejected', now(), now());

INSERT INTO trips(id, price, order_id, offer_uuid, created_date, updated_date)
VALUES (1, '278.55', 1, '7ab31231-4c7f-40dd-aa81-bd27a465e5ef', now(), now()),
       (2, '892.24', 2, '313ed997-7375-33e1-a8bb-63dd04a5f0a7', now(), now());

INSERT INTO account_trips(account_id, trip_id)
VALUES (3, 1),
       (2, 2);

