INSERT INTO users (`age`, `email`, `first_name`, `last_name`, `password`)
VALUES ('1', 'admin@mail.ru', 'admin', 'admin', '$2a$12$cm066YTzIjDk0C361x6u1eKYiBsX0e/3abc5UCm.FvD5xdq4aSwLG');
-- password = 1

INSERT INTO users (`age`, `email`, `first_name`, `last_name`, `password`)
VALUES ('2', 'user@mail.ru', 'user', 'user', '$2a$12$cm066YTzIjDk0C361x6u1eKYiBsX0e/3abc5UCm.FvD5xdq4aSwLG');
-- password = 1

INSERT INTO roles (`role`)
VALUES ('ADMIN');

INSERT INTO roles (`role`)
VALUES ('USER');

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 1);

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (2, 2);