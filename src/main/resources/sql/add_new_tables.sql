CREATE TABLE users (
    id int PRIMARY KEY auto_increment,
    first_name varchar(255) NOT NULL,
    second_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    phone varchar(255) NOT NULL UNIQUE,
    email varchar(255) NOT NULL UNIQUE
);

CREATE TABLE "orders" (
    id int PRIMARY KEY auto_increment,
    item varchar(255) NOT NULL,
    user_id int NOT NULL,
    foreign key (user_id) references users (id)
);

INSERT INTO users (first_name, second_name, last_name, phone, email) values ( 'Иван', 'Андреевич', 'Минеев', '89126144959', 'ivanmineev52@gmail.com');
INSERT INTO users (first_name, second_name, last_name, phone, email) values ( 'Артем', 'Васильевич', 'Шматков', '89552332054', 'nisare1337@gmail.com');
INSERT INTO users (first_name, second_name, last_name, phone, email) values ( 'Александр', 'Викторович', 'Вергус', '89045135358', 'polarxtreame@gmail.com');

-- INSERT INTO "orders" (item, user_id) values ('Test-item', 1);
INSERT INTO "orders" (item, user_id) values ('Test-item2', 2);
-- INSERT INTO "orders" (item, user_id) values ('Test-item3', 1);