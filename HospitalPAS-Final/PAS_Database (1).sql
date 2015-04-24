-- Jiang Zheheng
CREATE TABLE staff(staff_id VARCHAR(20) NOT NULL, 
					 title VARCHAR(5),
					 first_name VARCHAR(20) NOT NULL,
					 last_name VARCHAR(20) NOT NULL,
					
					 staff_password VARCHAR(20) NOT NULL,
					 staff_role VARCHAR(20) NOT NULL,
					 staff_team VARCHAR(20) NOT NULL,
					 email_address VARCHAR(40) NOT NULL,
					 telephone VARCHAR(20) NOT NULL,
					 PRIMARY KEY (staff_id),
					 INDEX (staff_id)
					 );







CREATE TABLE patient(NHS_number VARCHAR(20) NOT NULL, 
								title VARCHAR(5),
								first_name VARCHAR(20) NOT NULL,
								last_name VARCHAR(20) NOT NULL,
								address VARCHAR(40) NOT NULL,
								telephone VARCHAR(20) NOT NULL,
								allergies VARCHAR(100),
								blood_group VARCHAR(5),
								PRIMARY KEY (NHS_number),
								INDEX (NHS_number)
								);




