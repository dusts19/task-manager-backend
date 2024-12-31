CREATE TABLE IF NOT EXISTS Permission (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS app_role (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS role_permissions (
	role_id BIGINT NOT NULL,
	permission_id BIGINT NOT NULL, 
	PRIMARY KEY (role_id, permission_id),
	FOREIGN KEY (role_id) REFERENCES app_role(id),
	FOREIGN KEY (permission_id) REFERENCES Permission(id)
);

CREATE TABLE IF NOT EXISTS app_user (
	id SERIAL PRIMARY KEY,
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
	user_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL, 
	PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (user_id) REFERENCES app_user(id),
	FOREIGN KEY (role_id) REFERENCES app_role(id)
);
