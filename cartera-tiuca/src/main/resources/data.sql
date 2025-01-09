insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active)
values (1, '01f6e929-98d7-431a-9ded-c121ff299676', 'user',
        '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe', '', 'unidad1', 'emailprueba@uwu.es', 1);
insert into user_roles (user_id, roles)
values ('01f6e929-98d7-431a-9ded-c121ff299676', 'USER');

insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active, cargo)
values (1, '40960360-a730-4cfe-8776-ced6192e8398', 'admin',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.', '', 'unidad2',
        'emailprueba2@uwu.es', 1, 'Vicerrectorado de Investigación y Transferencia');
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

insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active, cargo)
values (1, '54237a9f-7702-47be-8dea-08ead213f558', 'promotor',
        '$2a$10$AcL50laejxK0ffV8fUjXsOCpcYQWNI5rcUbjP1tkVKARUgpBUVoXq', '', 'unidad2',
        'emailprueba4@uwu.es', 1, 'Vicerrectorado de Transformación para la Universidad Digital');
insert into user_roles (user_id, roles)
values ('54237a9f-7702-47be-8dea-08ead213f558', 'PROMOTOR');

insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active)
values (1, '40c9d0c7-3b97-486a-88a7-7ceabd13531a', 'otp',
        '$2a$10$/iPk7iQiPg0YQNupHMu89O7iMxUNjaV/IKX2FH0HNiVqwRn2BXpby', '', 'unidad2',
        'emailprueba5@uwu.es', 1);
insert into user_roles (user_id, roles)
values ('40c9d0c7-3b97-486a-88a7-7ceabd13531a', 'OTP');

insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active, cargo)
values (1, '1ca47a50-0890-4387-99b3-c827cd2a4395', 'otp2',
        '$2a$10$LyqOeRlwAVZwRO.jX5J3nel2zXwywiD1GWO7R0ndXPUi6Hp7dQ1XS', '', 'unidad2',
        'emailprueba6@uwu.es', 1, 'Vicerrectorado de Ciencias de la Salud y Bienestar de la Comunidad Universitaria');
insert into user_roles (user_id, roles)
values ('1ca47a50-0890-4387-99b3-c827cd2a4395', 'OTP');
insert into user_roles (user_id, roles)
values ('1ca47a50-0890-4387-99b3-c827cd2a4395', 'PROMOTOR');

insert into carteratiuca.app_users (version, id, username, password, profile_picture, unit, email, active)
values (1, 'e851f686-2cc3-44fc-a2ea-bca270ebc5ad', 'cio',
        '$2a$10$oOiHyCXwQgJ85gUpPwG5f.mP2Tz0OhiGlpa9JNtP5kje2FWxbwudq', '', 'unidad2',
        'emailprueba7@uwu.es', 1);
insert into user_roles (user_id, roles)
values ('e851f686-2cc3-44fc-a2ea-bca270ebc5ad', 'CIO');
insert into user_roles (user_id, roles)
values ('e851f686-2cc3-44fc-a2ea-bca270ebc5ad', 'PROMOTOR');

INSERT INTO `justificacion_proyecto` (`actualizar_oferta`, `alta_calidad`, `aumentar_investigacion`,
                                      `conseguir_transparencia`, `consolidar_gobierno_sostenible`,
                                      `fecha_puesta_en_marcha`, `generar_valor_compartido`, `reforzar_papeluca`,
                                      `version`, `id`, `alcance`, `normativa`)
VALUES (1, 1, 1, 1, 1, '2024-12-14', 1, 1, 1, '7d5df433-4dc9-4da6-9139-d5833937b592',
        '300 estudiantes de la Escuela Superior de Ingeniería del Campus de Puerto Real', '2341234321xhdxh');

INSERT INTO `convocatoria` (`id`, `nombre`, `fecha_inicial`, `fecha_final`, `activo`)
VALUES ('1db86b5b-b33b-11ef-baa2-00ffcf6a3273', 'convocatoria1', '2024-12-14', '2025-12-14', '1');

INSERT INTO `proyecto` (`coste`, `estado`, `financiacion_interesado`, `grado_avance`, `importancia_promotor`,
                        `interesados`, `num_empleados`, `version`, `id`, `justificacion_id`, `solicitante_id`,
                        `nombre_corto`, `titulo`, `especificaciones_tecnicas`, `presupuesto`, `promotor_id`,
                        `estado_avalacion`, `estado_valoracioncio`, `estado_valoracionotp`, `convocatoria_id`)
VALUES ('50000', 'REGISTRADO', '10000', '0', '10', '10000',
        '2', '1', '1db86b5b-b33b-11ef-baa2-00ffcf6a3272',
        '7d5df433-4dc9-4da6-9139-d5833937b592', '01f6e929-98d7-431a-9ded-c121ff299676',
        'proy1', 'ejemplo_proyecto', NULL, NULL,
        '40960360-a730-4cfe-8776-ced6192e8398', 'PORDETERMINAR',
        'PORDETERMINAR', 'PORDETERMINAR', '1db86b5b-b33b-11ef-baa2-00ffcf6a3273');