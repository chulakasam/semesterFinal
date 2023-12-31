CREATE TABLE user (useName VARCHAR(20)PRIMARY KEY,
                    password VARCHAR(12),
                    email VARCHAR(30)NOT NULL );

CREATE TABLE staff(staffId VARCHAR(5)PRIMARY KEY,
                    name VARCHAR(20),
                    userName VARCHAR(5),
                    FOREIGN KEY(userName) REFERENCES user(useName)ON UPDATE CASCADE ON DELETE CASCADE);


CREATE TABLE trainer(trainerId VARCHAR(5)PRIMARY KEY,
                    name VARCHAR(20),
                    telephone INT(11),nic VARCHAR(15)unique,email VARCHAR(30),gender VARCHAR(7),dob DATE,description VARCHAR(30));

CREATE TABLE workoutPlan(workPlanId VARCHAR(5)PRIMARY KEY,
                        description VARCHAR(100),
                        trainerId VARCHAR(5),
                        FOREIGN KEY (trainerId)REFERENCES trainer(trainerId)ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE client(clientId VARCHAR(5)PRIMARY KEY ,
                    name VARCHAR(20),
                    Address VARCHAR(30),
                    contactNo INT(11),email VARCHAR(30),height INT(4),weight INT(4),gender VARCHAR(10),dob DATE);

CREATE TABLE orders(orderId  VARCHAR(5)PRIMARY KEY,
                    date DATE,
                    clientId VARCHAR(5),
                    CONSTRAINT FOREIGN KEY (clientId)REFERENCES client(clientId)ON DELETE CASCADE ON UPDATE CASCADE ,
                    amount DECIMAL(6,2));

CREATE TABLE payment(paymentId VARCHAR(5),
                    date DATE,
                    amount VARCHAR(50),
                    clientId VARCHAR(5),
                    CONSTRAINT FOREIGN KEY (clientId)REFERENCES client(clientId)ON UPDATE CASCADE ON DELETE CASCADE,
                    orderId VARCHAR(5),
                    CONSTRAINT FOREIGN KEY (orderId)REFERENCES orders(orderId)ON UPDATE CASCADE ON DELETE CASCADE ,
                    type VARCHAR(10));

CREATE TABLE item(itemCode VARCHAR(5)PRIMARY KEY,
                 name VARCHAR(20),
                 unitPrice DECIMAL(5,2),
                 qtyOnHand INT(3));

CREATE TABLE supplier(supplierId VARCHAR(5)PRIMARY KEY,
                      name VARCHAR(20),
                      Address VARCHAR(30),
                      contactNo INT(11));

CREATE TABLE trainerClientDetails(trainerId VARCHAR(5),
                                  FOREIGN KEY (trainerId)REFERENCES trainer(trainerId)ON UPDATE CASCADE ON DELETE CASCADE ,
                                  clientId VARCHAR(5),
                                  FOREIGN KEY (clientId)REFERENCES client(clientId)ON UPDATE CASCADE ON DELETE CASCADE ,
                                  date DATE);

CREATE TABLE orderItemDetails(orderId VARCHAR(5),
                              CONSTRAINT FOREIGN KEY (orderId)REFERENCES orders(orderId)ON UPDATE CASCADE ON DELETE CASCADE ,
                               itemId VARCHAR(5),
                               CONSTRAINT FOREIGN KEY (itemId)REFERENCES item(itemCode)ON UPDATE CASCADE ON DELETE CASCADE ,
                               qty INT(4),amount DECIMAL(7,2));

CREATE TABLE supplierItemDetails(supplierId VARCHAR(5),
                                FOREIGN KEY (supplierId)REFERENCES supplier(supplierId)ON UPDATE CASCADE ON DELETE CASCADE,
                                itemCode VARCHAR(5),
                                FOREIGN KEY (itemCode)REFERENCES item(itemCode)ON UPDATE CASCADE ON DELETE CASCADE );
















