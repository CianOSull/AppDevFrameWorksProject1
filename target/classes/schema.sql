CREATE TABLE hero (
	heroId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	heroName varchar(30) NOT NULL
) ;

CREATE TABLE franchise (
	franchiseId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	franchiseName varchar(30) NOT NULL,
	heroId int(11) NOT NULL,
	FOREIGN KEY (heroId) REFERENCES hero(heroId) ON DELETE CASCADE
) ;