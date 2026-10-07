-- Usuarios iniciales (passwords en BCrypt, costo 10)
--   ADMIN       admin@fastorder.com           / Admin123*
--   REPARTIDOR  repartidor@fastorder.com      / Repartidor123*
--   CLIENTE     cliente.demo@fastorder.com    / Cliente123*
-- (cliente@fastorder.com NO se siembra: lo registra el script de pruebas via /auth/register)
INSERT INTO usuarios (nombre, direccion, telefono, email, password, rol) VALUES
('Administrador FastOrder', 'Zona 1, Ciudad de Guatemala',  '22223333', 'admin@fastorder.com',
 '$2a$10$B31sHu6Q73btBHbUjyOWbu/JEweRgj/irYgLT0S3iTyrodAW/ph0K', 'ADMIN'),
('Repartidor Demo',         'Zona 7, Ciudad de Guatemala',  '55550001', 'repartidor@fastorder.com',
 '$2a$10$m5K0bWWeTC890qGc3WjCMeFe.G6qreQHzPv1xhP9zEfqQPxNaodxm', 'REPARTIDOR'),
('Cliente Demo',            'Zona 10, Ciudad de Guatemala', '55550002', 'cliente.demo@fastorder.com',
 '$2a$10$P1yDAbb/GifBn8a6UA9X..q5Sv0Cm5nxSsf537JGX3JQvhBUe1Wfm', 'CLIENTE')
ON CONFLICT (email) DO NOTHING;
