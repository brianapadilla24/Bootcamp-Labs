#Ccreate the database
CREATE DATABASE dealerships;

#Create your own tables. 
#T1: dealerships	T2: vehicles 	T3: Inventory 	T4: sales_contracts 
#T1: 
#Columns: dealership_id 	int, auto-increment, priamry key
#	name, address, (varchar(50)) phone varchar(12)

CREATE TABLE dealerships (
	dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50),
    ADDRESS VARCHAR(50),
    PHONE VARCHAR(12)
);

#you decide, vin should be the primary key, vin should NOT be auto-increment, include a column called SOLD
#make, model, year, color, price, 
CREATE TABLE vehicles (
	vin VARCHAR(20) PRIMARY KEY,
    make VARCHAR(50),
    model VARCHAR(50),
    year INT,
    color VARCHAR(30),
    price DECIMAL(10, 2)
); 

#inventory: columns: dealerhsip_id, VIN
#track which dealership has the vehicle 
CREATE TABLE inventory (
	dealerhsip_id INT,
    vin VARCHAR(20),
    PRIMARY KEY (dealership_id, vin),
    CONSTRAINT my_dealership
	FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id),
    CONSTRAINT my_vehicle
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

#table 4: sales_contracts
#columns: you decide, id should be auto-incremented, use a foreign key (VIN) to link to the vehicle 
#name, address, date, phone, price

CREATE TABLE sales_contracts ( 
	contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(20),
    customer_name VARCHAR(50),
    customer_address VARCHAR(100),
    customer_date DATE,
    sale_price DECIMAL(10, 2),
    CONSTRAINT dealer_vehicle_contract
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE lease_contracts (
lease_num INT AUTO_INCREMENT PRIMARY KEY,
vin VARCHAR(20),
customer_name VARCHAR(50),
customer_address VARCHAR(100),
customer_phone VARCHAR(15),
customer_date DATE,
lease_start_date DATE,
lease_end_date DATE,
total_lease DECIMAL(10,2),
monthly_lease DECIMAL(10,2),
CONSTRAINT my_vehicle_lease
FOREIGN KEY(vin) REFERENCES vehicles(vin)
);

#populate each of your tables with sample data 
#1,2,3,4,5
INSERT INTO dealership (name, address, phone) VALUES
('Carmax', '626 Main St', '111-111'),
('AutoHub', '010 Elm St', '123-1829'),
('Dallas Auto Plaza', '192 Bowie Rd', '373-1729'),
('CarNation', '192 Main St', '102-2921'),
('Classico Cars', '201 Park Row', '192-2810');

#insert Into the vehicles for the dealerships 
#vin, make, model, year, color, price, vehicleType, odometer
INSERT INTO vehicles (vin, make, model, year, color, price, vehicleType, odometer) VALUES
('083BFIABAE', 'Tesla', 'NewModel', 2024, 'Black', 70000, 'Sedan', 100),
('1892B3H29', 'Honda', 'Civic', 2017, 'Blue', 10000, 'Coupe', 100),
('129JHE210', 'Chevrolet', 'Tahoe', 2015, 'Tan', 20000, 'Sedan', 100),
('1298ADH289', 'Chevrolet', 'Malibu', 2018, 'Tan', 18000, 'Coupe', 100),
('12CHYTT210', 'Honda', 'Accord', 2018, 'Black', 8000, 'Sedan', 100);

INSERT INTO inventory (dealership_id, vin) VALUES
(1, '083BFIABAE'),
(2, '1892B3H29'),
(3, '129JHE210'),
(4, '1298ADH289'),
(5, '12CHYTT210');

INSERT INTO lease_contracts (vin, customer_name, customer_phone, customer_address, lease_start_date, lease_end_date, lease_price, monthly_payment) VALUES
('083BFIABAE', 'Peter Kin', '81881818888', '909 Main St.', '2024-03-21', '2027-03-21', 75000, 5000),
('1892B3H29', 'Olivia Watson', '8932846103', '065 Loving St.', '2024-02-10', '2027-02-10', 15000, 2000),
('129JHE210', 'Pamela Chu', '9872736401', '777 Willowgrace', '2024-05-05', '2027-05-05', 24000, 4000);


INSERT INTO sales_contracts (vin, customer_name, customer_phone, customer_address, sales_start_date, sales_end_date, sales_price) VALUES
('1298ADH289', 'Lilly Flores', '5725128712', '5203 Peoples', '2024-04-12', '2027-04-12', 20000),
('12CHYTT210', 'Lisa Avery', '214688811645', '781 Happiness', '2024-05-12', '2027-05-12', 10000);

SELECT * FROM dealership;

#vehicles dealership
SELECT myVehicles.*
FROM vehicles myVehicles
JOIN inventory myInventory ON myVehicles.vin = myInventory.vin
WHERE myInventory.dealership_id = 2;

#dealership
SELECT myDealership.*
FROM dealership myDealership
JOIN inventory myInventory ON myDealership.dealership_id = myInventory.dealerhsip_id
WHERE myInventory.vin = '083BFIABAE';

#dealership type
SELECT DISTINCT myDealership.*
FROM dealership myDealership
JOIN inventory myInventory ON myDealership.dealership_id = myInventory.dealership_id
JOIN vehicles myVehicles ON myInventory.vin = myVehicles.vin
WHERE myVehicles.vehicleType = 'Sedan';

#vin
SELECT * FROM vehicles
WHERE vin = '083BFIABAE';


#sales
SELECT mySalesContract.*
FROM sales_contract mySalesContract 
JOIN inventory myInventory ON mySalesContract.vin = myInventory.vin 
WHERE myInventory.dealership_id = 1 AND mySalesContract.sale_date BETWEEN '2024-01-10' AND '2024-06-09';







