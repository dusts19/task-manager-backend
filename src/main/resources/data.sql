INSERT INTO PERMISSION (name) VALUES ('READ') ON CONFLICT (NAME) DO NOTHING;
INSERT INTO PERMISSION (name) VALUES ('WRITE') ON CONFLICT (NAME) DO NOTHING;

INSERT INTO app_role (name) VALUES ('USER') ON CONFLICT (name) DO NOTHING;

INSERT INTO app_role (name) VALUES ('ADMIN') ON CONFLICT (name) DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM app_role r, Permission p 
WHERE r.name = 'ADMIN' and p.name='' 
ON CONFLICT (role_id, permission_id) DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM app_role r, Permission p 
WHERE r.name = 'ADMIN' and p.name='' 
ON CONFLICT (role_id, permission_id) DO NOTHING;

INSERT INTO app_user (username, password, email) VALUES ('admin', '$2a$10$oVfiGKiG06IvuKmFIMrwXeZEO4S24/ZE0yf8nDDlmYcmhN/wUaEeq', 'admin@example.com') ON CONFLICT (username) DO NOTHING;

INSERT INTO user_roles (user_id, role_id) 
SELECT u.id, r.id 
FROM app_user u, app_role r
WHERE u.username='admin' AND r.name = 'ADMIN'
ON CONFLICT (user_id, role_id) DO NOTHING;