CREATE TABLE IF NOT EXISTS Entity (
    entity_id INT PRIMARY KEY,
    name TEXT,
    description TEXT
);
CREATE TABLE IF NOT EXISTS LinkedEntities (entity_id INT, i INT, linked_entity_id INT);
CREATE TABLE IF NOT EXISTS Player (
    player_id INT PRIMARY KEY,
    platform_id TEXT,
    platform TEXT,
    entity_id INT
);
CREATE TABLE IF NOT EXISTS Role (role_name TEXT PRIMARY KEY);
CREATE TABLE IF NOT EXISTS PlayerRoles (id INT, role_name TEXT);
INSERT INTO Role
VALUES ('admin');
INSERT INTO Role
VALUES ('player');
INSERT INTO Player
VALUES (1, '1', 'console', NULL);