SELECT users.firstName, users.lastName, o.item FROM users INNER JOIN "orders" o on o.user_id = users.id