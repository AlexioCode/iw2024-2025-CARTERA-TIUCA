insert into users (version, id, username, password, email, active, profile_picture) values (1, '1','user','$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe', "pepe@example.com", true, '');
insert into user_roles (user_id, roles) values ('1', 'USER');
insert into users (version, id, username, password, email, active, profile_picture) values (1, '2','admin','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.', "pepa@example.com", true, '');
insert into user_roles (user_id, roles) values ('2', 'USER');
insert into user_roles (user_id, roles) values ('2', 'ADMIN');