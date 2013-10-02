-- SQL file desribing the code --

CREATE DATABASE test1;

USE test1;

CREATE TABLE links(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	link VARCHAR(100),
	origin INTEGER,
	timestamp DATE,
	g_target VARCHAR(100),
	u_target INTEGER);

CREATE TABLE users(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) UNIQUE);

CREATE TABLE groups_users(
	id_user INTEGER,
	id_group INTEGER,
	PRIMARY KEY(id_user, id_group));

CREATE TABLE groups(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50));

ALTER TABLE links ADD FOREIGN KEY(origin) REFERENCES users(id);
ALTER TABLE links ADD FOREIGN KEY(u_target) REFERENCES users(id);
ALTER TABLE groups_users ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE groups_users ADD FOREIGN KEY(id_group) REFERENCES groups(id);
