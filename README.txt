
INSERT INTO `test_task`.`system_user` (`id`, `is_active`, `password`, `login`) VALUES ('1', '1', 'admin', 'admin');

INSERT INTO `test_task`.`user_roles` (`id`, `roles`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `test_task`.`user_roles` (`id`, `roles`) VALUES ('2', 'ROLE_USER');


http://localhost:8080/token?login=admin&password=admin

