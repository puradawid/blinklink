-- LOADS EXAMPLE DATA TO DB --
-- LOAD USERS 0,1,2,3

INSERT INTO users(name) VALUES('dawidpura');		-- 0
INSERT INTO users(name) VALUES('adrianpura');		-- 1
INSERT INTO users(name) VALUES('maciejkowalski');	-- 2
INSERT INTO users(name) VALUES('michal.rutkowski');	-- 3

-- Load groups

INSERT INTO groups(name) VALUES('programmers');
INSERT INTO groups(name) VALUES('testers');
INSERT INTO groups(name) VALUES('business');

-- Add users to groups

INSERT INTO groups_users(id_user, id_group) VALUES(1,2);
INSERT INTO groups_users(id_user, id_group) VALUES(2,1);
INSERT INTO groups_users(id_user, id_group) VALUES(2,2);

-- INSERT PREVIOUS ADDED DATA - LINKS

INSERT INTO links(link, origin, timestamp, g_target, u_target) VALUES('http://simplelink.com', 1, '2013-10-10 10:20', 'programmers', NULL);
INSERT INTO links(link, origin, timestamp, g_target, u_target) VALUES('http://google.com', 1, NOW(), 'programmers', NULL);
INSERT INTO links(link, origin, timestamp, g_target, u_target) VALUES('http://facebok.com', 1, NOW(), 'testers', NULL);
INSERT INTO links(link, origin, timestamp, g_target, u_target) VALUES('http://windows.is.shit.com', 1, NOW(), 'programmers', NULL);
INSERT INTO links(link, origin, timestamp, g_target, u_target) VALUES('http://windows.is.shit.com', 1, NOW(), NULL, 3);

