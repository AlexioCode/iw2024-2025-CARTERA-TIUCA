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

INSERT INTO `justificacion_proyecto` (`actualizar_oferta`, `alta_calidad`, `aumentar_investigacion`,
                                      `conseguir_transparencia`, `consolidar_gobierno_sostenible`,
                                      `fecha_puesta_en_marcha`, `generar_valor_compartido`, `reforzar_papeluca`,
                                      `version`, `id`, `alcance`, `normativa`)
VALUES (1, 1, 1, 1, 1, '2024-12-14', 1, 1, 1, '7d5df433-4dc9-4da6-9139-d5833937b592',
        'mucho y muy seguido', 'no pare sigue sigue');

INSERT INTO `proyecto` (`coste`, `estado`, `financiacion_interesado`, `grado_avance`, `importancia_promotor`,
                        `interesados`, `num_empleados`, `version`, `id`, `justificacion_id`, `solicitante_id`,
                        `nombre_corto`, `titulo`, `especificaciones_tecnicas`, `presupuesto`, `promotor`,
                        `estado_avalacion`, `estado_valoracioncio`)
VALUES ('50000', 'REGISTRADO', '10000', '0', '10', '10000',
        '2', '1', '1db86b5b-b33b-11ef-baa2-00ffcf6a3272',
        '7d5df433-4dc9-4da6-9139-d5833937b592', '01f6e929-98d7-431a-9ded-c121ff299676',
        'asdasda', 'asdasdas', NULL, NULL,
        'juan carlos', 'PORDETERMINAR',
        'PORDETERMINAR');

INSERT INTO `convocatoria` (`id`, `nombre`, `fecha_inicial`, `fecha_final`)
VALUES ('1db86b5b-b33b-11ef-baa2-00ffcf6a3273', 'convocatoria1', '2024-12-14', '2025-12-14');