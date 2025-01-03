insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active)
values (1, '01f6e929-98d7-431a-9ded-c121ff299676', 'user',
        '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe', '', 'unidad1', 'emailprueba@uwu.es', 1);
insert into user_roles (user_id, roles)
values ('01f6e929-98d7-431a-9ded-c121ff299676', 'USER');
insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active)
values (1, '40960360-a730-4cfe-8776-ced6192e8398', 'admin',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.', '', 'unidad2', 'emailprueba2@uwu.es', 1);
insert into user_roles (user_id, roles)
values ('40960360-a730-4cfe-8776-ced6192e8398', 'USER');
insert into user_roles (user_id, roles)
values ('40960360-a730-4cfe-8776-ced6192e8398', 'ADMIN');
insert into user_roles (user_id, roles)
values ('40960360-a730-4cfe-8776-ced6192e8398', 'PROMOTOR');
insert into user_roles (user_id, roles)
values ('40960360-a730-4cfe-8776-ced6192e8398', 'CIO');
insert into user_roles (user_id, roles)
values ('40960360-a730-4cfe-8776-ced6192e8398', 'OTP');
INSERT INTO `proyecto` (`coste`, `estado`, `financiacion_interesado`, `grado_avance`, `importancia_promotor`,
                        `interesados`, `num_empleados`, `version`, `id`, `justificacion_id`, `solicitante_id`,
                        `nombre_corto`, `titulo`, `especificaciones_tecnicas`, `presupuesto`)
VALUES ('50000', '1', '10000', '0', '10', '10000', '2', '1', '1db86b5b-b33b-11ef-baa2-00ffcf6a3272', NULL,
        '01f6e929-98d7-431a-9ded-c121ff299676', 'asdasda', 'asdasdas', NULL, NULL);