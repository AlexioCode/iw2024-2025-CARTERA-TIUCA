insert into carteratiuca.app_users (version, id, username,password,profile_picture, unit) values (1, '1','user','$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe','', 'unidad1');
insert into user_roles (user_id, roles) values ('1', 'USER');
insert into carteratiuca.app_users (version, id, username,password,profile_picture, unit) values (1, '2','admin','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.','', 'unidad2');
insert into user_roles (user_id, roles) values ('2', 'USER');
insert into user_roles (user_id, roles) values ('2', 'ADMIN');