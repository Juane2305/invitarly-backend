-- Inserción de planes con precios
INSERT INTO plan (id, nombre, descripcion, imagen, precio) VALUES
(1, 'Gold', 'Incluye plantillas premium y exclusivas', 'gold.jpg', 500.0),
(2, 'Platinum', 'Plantillas elegantes para eventos destacados', 'platinum.jpg', 400.0);

-- Inserción de plantillas asociadas a los planes
INSERT INTO plantilla (id, nombre, descripcion, imagen, plan_id) VALUES
(1, 'Moderna', 'Diseño contemporáneo y elegante', 'moderna.jpg', 1),
(2, 'Vintage', 'Diseño clásico y romántico', 'vintage.jpg', 2);