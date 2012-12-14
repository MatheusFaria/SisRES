CREATE DATABASE IF NOT EXISTS sisres_db;
CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'password';
USE sisres_db;
GRANT ALL ON sisres_db.* TO 'testuser'@'localhost';