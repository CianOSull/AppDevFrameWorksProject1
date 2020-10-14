CREATE TABLE household (
	householdId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	eircode varchar(30) NOT NULL,
	address varchar(30) NOT NULL
) ;

CREATE TABLE occupants (
	occupantId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	occupantName varchar(30) NOT NULL,
	occupantAge int(11) NOT NULL,
	occupation varchar(50) NOT NULL,
	householdId int(11) NOT NULL,
	FOREIGN KEY (householdId) REFERENCES household(householdId) ON DELETE CASCADE
) ;