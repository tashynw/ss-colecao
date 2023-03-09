DROP DATABASE IF EXISTS ss_colecao;
CREATE DATABASE ss_colecao;
USE ss_colecao;

/* Creating users/credentials table */
CREATE TABLE Users(
    username varchar(255),
    password varchar(255)
);
INSERT INTO Users (username, password)
VALUES (
    "Admin", "password123"
);

/*Creating Customers table*/
CREATE TABLE Customers(
    customer_id int PRIMARY KEY,
    firstName varchar(255),
    lastName varchar(255),
    address varchar(255),
    phone_number varchar(255),
    email varchar(255)
);

INSERT INTO Customers (customer_id, firstName, lastName, address, phone_number, email)
VALUES (
    1, 'John', 'Doe', '123 Main St', '555-1234', 'johndoe@example.com'
),
(
    2, 'Jane', 'Doe', '456 Oak Ave', '555-5678', 'janedoe@example.com'
);

/* Creating Stocks table */
CREATE TABLE Stocks(
    stock_id int PRIMARY KEY,
    item_name varchar(255),
    item_color varchar(255),
    item_size varchar(255),
    price float
);

INSERT INTO Stocks (stock_id, item_name, item_color, item_size, price)
VALUES (
    1, 'Widget A', 'Red', 'Small', 9.99
),
(
    2, 'Widget B', 'Blue', 'Large', 19.99
);


/*Creating Orders table*/
CREATE TABLE Orders(
    order_id int PRIMARY KEY,
    customer_id int,
    stock_id int,
    item_count int,
    deliver_mode varchar(255),
    status varchar(255),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (stock_id) REFERENCES Stocks(stock_id)
);

-- Add an order for customer with ID 1, purchasing 2 units of stock item with ID 1
INSERT INTO Orders (order_id, customer_id, stock_id, item_count, deliver_mode, status)
VALUES (
    1, 1, 1, 2, 'Standard', 'Pending'
),(
    2, 2, 2, 1, 'Express', 'Pending'
);

